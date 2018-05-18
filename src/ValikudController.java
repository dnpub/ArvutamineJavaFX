import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.Event;
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
import java.util.*;


public class ValikudController {
    public ValikudController() {
    }

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
    private RadioButton ajaKauduPiiramiseRaadionupp;


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
        YlesandedController ylesandedController = loader.getController();
        ylesandedController.setLahendajaNimiTekst(lahendajaNimeSisestuslahter.getText());
        ylesandedController.setTeheteValikTekst(tehted);
        ylesandedController.setRaskusasteTekst(raskusastmeSisestuslahter.getText());
        ylesandedController.uuendaAega();
        ylesandedController.setYlesandeidKokkuTekst(ylesannetePiirarvuSisestuslahter.getText());


        abiinfoNuppValikudLehel.getScene().setRoot(root);


    }


    public Set<String> tehted = new HashSet<String>();

    /*@FXML
    private void vajutaPlussNupp() {
        raskusastmeSisestuslahter.setDisable(false);
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
        raskusastmeSisestuslahter.setDisable(false);
        miinusNupp.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

            }
        });
        tehted.add("-");
        System.out.println("-");
    }*/

    @FXML
    private void vajutaTehe(Event evt) {
        Button nupp = (Button)evt.getSource();

        raskusastmeSisestuslahter.setDisable(false);
        String tehe = nupp.getText();
        tehted.add(tehe);
        System.out.println(nupp.getText());

    }

 /*   @FXML
    private void vajutaJagamisNupp() {
        raskusastmeSisestuslahter.setDisable(false);
        jagamisNupp.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

            }
        });
        tehted.add("/");
        System.out.println("/");
    }*/

   /* @FXML
    private void vajutaKorrutusNupp() {
        raskusastmeSisestuslahter.setDisable(false);
        korrutusNupp.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

            }
        });
        tehted.add("*");
        System.out.println("*");
    }*/

    @FXML
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
                    if (vastus[0].contains("aeg")) {
                        ylesannetePiirarvuSisestuslahter.clear();
                    }
                    if (vastus[0].contains("hulk")) {
                        ajapiiranguSisestuslahter.clear();
                    }

                    ajapiiranguSisestuslahter.setDisable(!vastus[0].contains("aeg"));
                    ylesannetePiirarvuSisestuslahter.setDisable(!vastus[0].contains("hulk"));

                    System.out.println("Vajutatud: " + nupp.getText());

                }
            }

        });
        ajaKauduPiiramiseRaadionupp.setToggleGroup(group);
        ylesanneteHulgaKauduPiiramiseRaadionupp.setToggleGroup(group);

        if (vastus[vastus.length - 1].contains("hulk")) {
            return false;
        } else if (vastus[vastus.length - 1].contains("aeg")) {
            return true;
        }

        return false; // eelnevalt vaja ära määrata et üks peab vähemalt selekteeritud olema
    }

    @FXML
    public void lubaAlustada() {


        String nimi = lahendajaNimeSisestuslahter.getText();
        String raskusaste = raskusastmeSisestuslahter.getText();
        String ylarv = ylesannetePiirarvuSisestuslahter.getText();
        String aeg = ajapiiranguSisestuslahter.getText();

        boolean onProbleemNimega = nimi.isEmpty();
        ylesanneteHulgaKauduPiiramiseRaadionupp.setDisable(onProbleemNimega);
        ajaKauduPiiramiseRaadionupp.setDisable(onProbleemNimega);

        boolean onProbleemTehetega = nimi.isEmpty() || (ylarv.isEmpty() && aeg.isEmpty());
        for (int i = 0; i < nupud.size(); i++) {
            nupud.get(i).setDisable(onProbleemTehetega);
        }

        if(!raskusaste.isEmpty()){alustaLahendamistNupp.setDisable(false);}

    }


    private List<Button> nupud = new ArrayList<Button>();


    public void initialize() {
        alustaLahendamistNupp.setDisable(true);

        nupud.add(jagamisNupp);
        nupud.add(plussNupp);
        nupud.add(korrutusNupp);
        nupud.add(miinusNupp);
        for (int i = 0; i < nupud.size(); i++) {
            nupud.get(i).setDisable(true);
        }
        ylesannetePiirarvuSisestuslahter.setDisable(true);
        ajapiiranguSisestuslahter.setDisable(true);
        raskusastmeSisestuslahter.setDisable(true);
        ajaKauduPiiramiseRaadionupp.setDisable(true);
        ylesanneteHulgaKauduPiiramiseRaadionupp.setDisable(true);


    }


}
