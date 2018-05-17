import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class ValikudController {
    public ValikudController(){}

    @FXML
    private Button abiinfoNuppValikudLehel;

    @FXML
    private Button valjuNuppValikudLehel;

    @FXML
    private TextField lahendajaNimeSisestuslahter;

    @FXML
    private TextField ylesannetePiirarvuSisestuslahter;

    @FXML
    private TextField ajapiiranguSisestuslahter;

    @FXML
    private TextField raskusastmeSisestuslahter;

    @FXML
    private Button plussNupp;

    @FXML
    private Button jagamisNupp;

    @FXML
    private Button korrutusNupp;

    @FXML
    private Button miinusNupp;

    @FXML
    private Button alustaLahendamistNupp;

    @FXML
    private RadioButton ajaKauduPiiramiseRaadionuppylesanneteHulgaKauduPiiramiseRaadionupp;


    @FXML
    private RadioButton ylesanneteHulgaKauduPiiramiseRaadionupp;






    @FXML
    private void avaAbiinfo() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Abiinfo.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(root, 600, 400));
        stage.show();


    }

    @FXML
    private void välju() {
        Stage stage = (Stage) valjuNuppValikudLehel.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void AlustaLahendamist() throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("Ylesanded.fxml"));
        Parent root = loader.load();
        YlesandedController ylesandedController= loader.getController();
        ylesandedController.setLahendajaNimiTekst(lahendajaNimeSisestuslahter.getText());
        ylesandedController.setTeheteValikTekst(tehted);
        ylesandedController.setRaskusasteTekst(raskusastmeSisestuslahter.getText());
       

        abiinfoNuppValikudLehel.getScene().setRoot(root);


    }



    public Set<String> tehted = new HashSet<String>();

    @FXML
    private void vajutaPlussNupp() {
        plussNupp.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

            }
        });
        tehted.add("+");
        System.out.println("+");
    }

    @FXML
    private void vajutaMiinusNupp() {
        miinusNupp.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

            }
        });
        tehted.add("-");
        System.out.println("-");
    }

    @FXML
    private void vajutaJagamisNupp() {
        jagamisNupp.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

            }
        });
        tehted.add("/");
        System.out.println("/");
    }

    @FXML
    private void vajutaKorrutusNupp() {
        korrutusNupp.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

            }
        });
        tehted.add("*");
        System.out.println("*");
    }


    public boolean kasAjapeale() {
        final ToggleGroup group = new ToggleGroup();
        final String[] vastus = new String[1]; // selekteeritud nupp
        group.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {

            @Override
            public void changed(ObservableValue<? extends Toggle> ov, Toggle esialgne, Toggle uus) {
                // kui
                if (group.getSelectedToggle() != null) {
                    RadioButton nupp = (RadioButton) group.getSelectedToggle();
                    vastus[0] = nupp.getText();
                    ajapiiranguSisestuslahter.setDisable(!vastus[0].contains("aeg"));
                    ylesannetePiirarvuSisestuslahter.setDisable(!vastus[0].contains("hulk"));

                    System.out.println("Vajutatud: " + nupp.getText());

                }
            }

        });
        ajaKauduPiiramiseRaadionuppylesanneteHulgaKauduPiiramiseRaadionupp.setToggleGroup(group);
        ylesanneteHulgaKauduPiiramiseRaadionupp.setToggleGroup(group);

        if (vastus[vastus.length - 1].contains("hulk")) {
            return false;
        } else if (vastus[vastus.length - 1].contains("aeg")) {
            return true;
        }

        return false; // eelnevalt vaja ära määrata et üks peab vähemalt selekteeritud olema
    }

    @FXML
    private void initialize(){

    }

    public void Valikud(){
        String nimi = lahendajaNimeSisestuslahter.getText();
        Integer ylhulk = Integer.parseInt(ylesannetePiirarvuSisestuslahter.getText());

    }





}
