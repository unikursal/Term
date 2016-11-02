package Util;

import ForTerm.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 30.10.2016.
 */
public class Rul {
    public static List<Term> delEndWord(List<Term> terms) {
        List<Term> result = new ArrayList<Term>();

        for (Term term : terms) {
            boolean flag = true;

            do {
                List<Word> words = term.getWords();

                if (words.size() == 0)
                    break;

                Word word = words.get(words.size() - 1);

                List<TermTag> termTags = word.getTermTags();

                for (TermTag tag : termTags)
                    if (tag.getPosTag().contains(MorphologAnalyzer.NOUN)) {
                        result.add(term);
                        flag = false;
                        break;
                    }

                if (flag) {
                    words.remove(words.size() - 1);
                }

            } while (flag);
        }
        return result;
    }
}
