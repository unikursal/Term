package ForTerm;

import Util.ShowText;

/**
 * Created by User on 11.10.2016.
 */
public class TermTag {
    private String lema;
    private String posTag;
    private int count = 0;

    public TermTag(String lema,  String posTag){
        this.lema = lema;
        this.posTag = posTag;
    }

    public String getLema() {
        return lema;
    }

    public String getPosTag() {
        return posTag;
    }

    public boolean myEquals(TermTag inpTag){
        if(inpTag.getLema() == null || posTag == null) {
            ShowText.show("posTag in TermTag == null");
            return false;
        }

        String inpPart = inpTag.getPosTag().split(":")[0];
        String part = posTag.split(":")[0];

        if(part.equals(inpPart) && lema.equals(inpTag.getLema()))
            return true;

        return false;
    }

    @Override
    public String toString(){
        return lema;
    }
}
