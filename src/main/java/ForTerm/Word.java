package ForTerm;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 28.10.2016.
 */
public class Word {
    private List<TermTag> termTags;

    public Word(){
        termTags = new ArrayList<TermTag>();
    }

    public void addTermTag(TermTag inpTag){
        for(TermTag termTag: termTags)
            if(termTag.myEquals(inpTag)) {
                return;
            }

        termTags.add(inpTag);
    }

    public void setTermTags(List<TermTag> list){
        this.termTags = list;
    }

    public List<TermTag> getTermTags() {
        return termTags;
    }

    public boolean myEquals(Word inpWord){
        List<TermTag> inpTags = inpWord.getTermTags();

        for(TermTag termTag: termTags)
            for(TermTag inpTag: inpTags)
                if(termTag.myEquals(inpTag))
                    return true;

        return false;
    }

    @Override
    public String toString(){
        return termTags.toString();
    }
}
