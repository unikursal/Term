package Util;

import ForTerm.Sentence;
import ForTerm.Term;
import ForTerm.Word;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 01.10.2016.
 */
public class MyComparator {

    public List<Term> comparable(Sentence sentence1, Sentence sent2){
        List<Term> terms = new ArrayList<Term>();

        List<Word> list1 = sentence1.getListAfterTag();
        List<Word> list2 = sent2.getListAfterTag();

        int size1 = list1.size();
        int size2 = list2.size();

        boolean masFlag[][] = new boolean[size1][size2];

        for(int i = 0; i < size1; i++)
            for(int j = 0; j < size2; j++)
                masFlag[i][j] = false;

        for(int i = 0; i < size1; i++)
            for(int j = 0; j < size2; j++){
                if(list1.get(i).myEquals(list2.get(j)))
                    masFlag[i][j] = true;
            }



        matcher(masFlag, list1, list2, terms);

        return terms;
    }

    //Перевіряється чи є словосполучення які співпадають в 2 вхідних реченнях
    private void matcher(boolean flags[][], List<Word> list1, List<Word> list2, List<Term> terms){
       /* for(int i =0; i< list1.size(); i++) {
            for (int j = 0; j < list2.size(); j++)
                System.out.print(flags[i][j] + "  ");

            System.out.println();
        }*/


        int size1 = list1.size();
        int size2 = list2.size();

        for(int i = 0; i < size1; i++) {
            Term term = null;

            for (int j = 0; j < size2; j++)
                if (flags[i][j] == true) {

                    term = new Term();

                    int m, n;

                    //Перевіряємо у прямому порядкові

                    boolean end = false;

                    for (m = i, n = j; m < size1 && n < size2; m++, n++)
                        if (flags[m][n] == true ) {
                            term.addWord(list1.get(m));
                            end = true;
                        }else{
                            if(end)
                                break;
                        }

                    if(!term.getWords().isEmpty())
                        terms.add(term);

                    //Перевіряємо у зворотньому порядкові

                    end = false;

                    List<Word> reverseList = new ArrayList<Word>();

                    for (m = i, n = j; m > -1 && n > -1; m--, n--)
                        if (flags[m][n] == true) {
                            reverseList.add(list1.get(m));
                            end = true;
                        }else{
                            if(end)
                                break;
                        }


                    /*
                    Слова добавились у зворотньому порядкові порівняно з тим як вони зустрічаються у тексті, перезаписуємо їх у прямий порядок
                     */

                    if(!reverseList.isEmpty()) {
                        Term t = new Term();

                        int size = reverseList.size();

                        for(int k = size - 1; k > 0; k--)
                            t.addWord(reverseList.get(k));

                        if(!t.getWords().isEmpty())
                            terms.add(t);
                    }
                }
        }
    }
}
