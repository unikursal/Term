package ForTerm;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 01.10.2016.
 */
public class TermBuff {
    private List<Term> termList;

    public TermBuff(){
        termList = new ArrayList<Term>();
    }

    public List<Term> getTermList() {
        return termList;
    }

    public void addTerm(List<Term> inpTerms) {
        if (termList.isEmpty()) {
            termList.addAll(inpTerms);
            return;
        }

        for (Term term : inpTerms) {

            boolean is = false;
            for(Term t: termList) {
                if (t.myEquals(term)) {
                    t.incrementCount();
                    is = true;
                }
            }

            if(is == false) {
                termList.add(term);
            }
        }
    }

    public void addTerm(Term term) {
        if (termList.isEmpty()) {
            termList.add(term);
            return;
        }

        boolean is = false;

        for(Term t: termList)
            if(t.myEquals(term)) {
                t.incrementCount();
                is = true;
            }

        if(is == false)
            termList.add(term);
        }

    }
