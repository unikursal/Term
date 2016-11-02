package Util;

import ForTerm.Sentence;
import ForTerm.Term;
import ForTerm.TermTag;
import ForTerm.Word;
import org.languagetool.tagging.TaggedWord;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by User on 01.10.2016.
 */
public class Creator {
    public static List<Sentence> creatSentenceLiat(List<List<String>> inputList){
        List<Sentence> result = new ArrayList<Sentence>();
        for(List<String> list: inputList) {
            int erstConj = 0;

            if(list.isEmpty())
                continue;

            int size = list.size();
            for(int i = 0; i < size; i++)
                if(MorphologAnalyzer.haveConjunction(list.get(i)) ){
                    List ls = list.subList(erstConj, i);

                    erstConj = i + 1;

                    if(ls.isEmpty())
                        continue;

                    Sentence sentence = new Sentence(ls);
                    result.add(sentence);
                }

            if(erstConj  < size) {
                List ls = list.subList(erstConj, size);
                if(ls.isEmpty())
                    continue;

                Sentence sentence = new Sentence(ls);
                result.add(sentence);
            }
        }

        return result;
    }

    public static Set<String> createStringList(List<TaggedWord> taggedWords){
        Set<String> result = new HashSet<String>();

        for(TaggedWord tagW: taggedWords)
            result.add(tagW.getLemma());

        return result;
    }

    public static Term createTermOfSentence(Sentence sentence){
        List<Word> list = sentence.getListAfterTag();
        Term term = new Term();
        term.setWords(list);

        return term;
    }

    public static Word createWordOfTaggWord(List<TaggedWord> taggedWords){
        Word word = new Word();

        for(TaggedWord tw: taggedWords) {

            word.addTermTag(new TermTag(tw.getLemma(), tw.getPosTag()));
        }

        return word;
    }
}
