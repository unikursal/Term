package Util;

import ForTerm.Sentence;
import ForTerm.Term;
import ForTerm.TermBuff;
import ForTerm.Word;
import org.languagetool.tagging.MorfologikTagger;
import org.languagetool.tagging.TaggedWord;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 01.10.2016.
 */
public class MorphologAnalyzer {
    public static final String CONJUNCTION = "conj";
    public static final String NOUN = "noun";
    public static final String PRONOUN = "pron";

    private List<Sentence> sentenceList;
    private MyComparator comparator;
    private TermBuff termBuff;

    public MorphologAnalyzer(List<List<String>> stringList){
        this.sentenceList = Creator.creatSentenceLiat(stringList);
        comparator = new MyComparator();
        termBuff = new TermBuff();
    }

    private static MorfologikTagger tagger = new MorfologikTagger("/uk/ukrainian.dict");

    public void tagging(){

        for(Sentence sentence: sentenceList){
            List<String> words = sentence.getOrigWordList();
            for(String string: words) {
                List<TaggedWord> taggedWords = tagger.tag(string);

                if(taggedWords.isEmpty())
                    continue;

                Word word = Creator.createWordOfTaggWord(taggedWords);

                sentence.addWord(word);
            }

        }

        if(sentenceList.size() == 1){
            Term term = Creator.createTermOfSentence(sentenceList.get(0));
            termBuff.addTerm(term);
            return;
        }

        int size = sentenceList.size();
        boolean found;

        for(int i = 0; i < size; i++) {
            found = false;

            List<Term> tempListTerm = new ArrayList<Term>();

            for (int j = 0; j < size; j++) {
                if (i == j)
                    continue;

                List<Term> terms = comparator.comparable(sentenceList.get(i), sentenceList.get(j));

                terms =  Rul.delEndWord(terms);

                if (!terms.isEmpty()) {
                    checkUnity(tempListTerm, terms);
                    found = true;
                }
            }

            if(!found && !sentenceList.get(i).getListAfterTag().isEmpty())  {
                Term term = Creator.createTermOfSentence(sentenceList.get(i));

                termBuff.addTerm(term);
            }

            if(tempListTerm.isEmpty())
                continue;

            termBuff.addTerm(tempListTerm);
        }
    }

    private void checkUnity(List<Term> tempListTerm, List<Term> inpTerms){
        if(tempListTerm.isEmpty()) {
            tempListTerm.addAll(inpTerms);
            return;
        }

        for(Term term: inpTerms){
            boolean b = true;

            for(Term t: tempListTerm) {
                if (term.myEquals(t)) {
                    b = false;
                    break;
                }
            }

            if(b) {
                tempListTerm.add(term);
            }

        }
    }

    public List<Sentence> getSentenceList() {
        return sentenceList;
    }

    public static boolean haveConjunction(String str){
        List<TaggedWord> taggedWords = tagger.tag(str);

        for(TaggedWord taggedWord: taggedWords)
            if(taggedWord.getPosTag().contains(CONJUNCTION))
                return true;

        return false;
    }

    public TermBuff getTermBuff() {
        return termBuff;
    }
}
