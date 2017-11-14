package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * @author chamodshehanka on 11/11/2017
 * @project ThogaKadeFX
 **/

public class StartProject extends Application{

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent parent = FXMLLoader.load(getClass().getResource("/view/fxml/ItemForm.fxml"));
        primaryStage.setTitle("ThogaKade");
        primaryStage.setScene(new Scene(parent));
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/view/icon/icon.png")));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
