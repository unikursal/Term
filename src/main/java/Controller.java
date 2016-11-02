import ForTerm.Sentence;
import ForTerm.Term;
import ForTerm.TermBuff;
import Util.MainСut;
import Util.MorphologAnalyzer;
import Util.ReadFile;
import Util.Rul;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by User on 30.09.2016.
 */
public class Controller {
    @FXML
    private TextField textField;

    @FXML
    private Button button;

    @FXML
    private TextArea textArea;

    @FXML TextField fieldForTime;

    public void initialize(){
        textArea.setWrapText(true);
    }

    public void clickBut(){
        long timeBegin = System.currentTimeMillis();

        textArea.setText("");

        List<String> list = null;
        if(textField.getText().length() == 0) {
            ReadFile readFile = new ReadFile();

            list = MainСut.toCutCharPunctuat(readFile.getListWithText());
            if(list == null)
                return;
        }else{
            list = new ArrayList<String>();
            list.add(textField.getText());
        }


        list = MainСut.toCutCharPunctuat(list);

        MorphologAnalyzer morphologAnalyzer = new MorphologAnalyzer(MainСut.cutIntoWords(list));
        morphologAnalyzer.tagging();

        long timeEnd = System.currentTimeMillis();

        /*List<Sentence> listSent = morphologAnalyzer.getSentenceList();

        for(Sentence sent: listSent) {
            for (Word word : sent.getListAfterTag())
                textArea.appendText(word.toString());
            textArea.appendText("\n");
        }*/



        TermBuff termBuff = morphologAnalyzer.getTermBuff();

        List<Term> terms = termBuff.getTermList();

        Collections.sort(terms);

        for(Term term: terms) {
            textArea.appendText(term.getWords().toString());
            textArea.appendText("                               " + term.getCount() + "\n");

        }
        timeEnd -= timeBegin;

        fieldForTime.setText(Long.toString(timeEnd));

    }
}
