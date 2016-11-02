package Util;

import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
/**
 * Created by User on 29.09.2016.
 */

public class ShowText {
    public static void show(String str) {
        Stage st = new Stage();
        st.setAlwaysOnTop(true);
        TextArea textArea = new TextArea(str) ;
        textArea.setStyle("-fx-border-color: darkSlateGrey; -fx-border-witch: 3pt; -fx-font: 12pt Arial; -fx-text-fill: darkSlateBlue");
        st.setScene(new Scene(textArea));
        st.show();
    }
}

