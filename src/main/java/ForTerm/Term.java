package ForTerm;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by User on 01.10.2016.
 */
public class Term implements Comparable{
    private int count = 1;
    private List<Word> words;

    public Term(){
        words = new ArrayList<Word>();
    }

    public void incrementCount(){
        count++;
    }

    public boolean myEquals(Term term){
        List<Word> inputList = term.getWords();

        if(inputList.size() != words.size()) {
            return false;
        }


        int size = words.size();
        int sizeInp = inputList.size();

        for(int i = 0; i < size; i++)
                if(!words.get(i).myEquals(inputList.get(i)))
                    return false;

        return true;
    }

    public void setWords(List<Word> words) {
        this.words = words;
    }

    public void addWord(Word word) {
        this.words.add(word);
    }

    public int getCount() {
        return count;
    }

    public List<Word> getWords() {
        return words;
    }

    @Override
    public String toString(){
        return words.toString();
    }

    public int compareTo(Object obj){
            Term term = (Term) obj;
            return term.getCount() - count;
    }
}
