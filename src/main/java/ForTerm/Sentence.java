package ForTerm;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 01.10.2016.
 */
public class Sentence {
    private List<String> origWordList;
    private List<Word> listAfterTag;

    public Sentence(List<String> list){
        origWordList = new ArrayList<String>();
        listAfterTag = new ArrayList<Word>();

        for(String str: list)
            origWordList.add(str.toLowerCase());
    }

    public List<String> getOrigWordList(){
        return origWordList;
    }

    public void addWord(Word word){
        listAfterTag.add(word);
    }

    public List<Word> getListAfterTag() {
        return listAfterTag;
    }

    @Override
    public String toString(){
        return listAfterTag.toString();
    }
}
