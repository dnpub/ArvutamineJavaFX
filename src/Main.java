

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.awt.*;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        FXMLLoader loader = new FXMLLoader(getClass().getResource("Valikud.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root, 600, 400);
     //   ValikudController valikudController = loader.getController();
   //     valikudController.valikud.layoutXProperty().bind(scene.widthProperty().subtract(valikudController.valikud.prefWidth(-1)).divide(2));


        primaryStage.setTitle("Arvutamise harjutamine ver.2.0");


        //TextField lahendajaNimeSisestuslahter = new TextField();
        primaryStage.setScene(scene);
        primaryStage.setMinWidth(600);
        primaryStage.setMinHeight(400);
        primaryStage.show();

    }


    public static void main(String[] args) {

        launch(args);
    }
}
