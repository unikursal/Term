import Util.ShowText;
import javafx.application.Application;

import javafx.fxml.FXMLLoader;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


/**
 * Created by User on 29.09.2016.
 */
public class Main extends Application{
    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage prStage){
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("main.fxml"));
        if(loader.getLocation() == null){
            ShowText.show("Location is null");
            return;
        }
        try {
            Parent parent = loader.load();
            prStage.setScene(new Scene(parent));
            prStage.show();
        }catch (IOException e){
            ShowText.show(e.getMessage());
            return;
        }

    }

}
