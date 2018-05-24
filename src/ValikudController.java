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
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.FontSmoothingType;
import javafx.stage.Stage;

import javax.swing.text.Style;
import java.awt.*;
import java.io.IOException;
import java.util.*;
import java.util.List;


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
    private ToggleButton plussNupp;

    @FXML
    private ToggleButton jagamisNupp;

    @FXML
    private ToggleButton korrutusNupp;

    @FXML
    private ToggleButton miinusNupp;

    @FXML
    private Button alustaLahendamistNupp;

    @FXML
    private RadioButton ajaKauduPiiramiseRaadionupp;


    @FXML
    private RadioButton ylesanneteHulgaKauduPiiramiseRaadionupp;


    public Set<String> tehted = new HashSet<String>();
    private List<ToggleButton> nupud = new ArrayList<ToggleButton>();

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
        ylesandedController.setKasAjaPeale(kasAjapeale());

        if(!ajapiiranguSisestuslahter.getText().isEmpty()){
            ylesandedController.setKontrollAeg(Integer.parseInt(ajapiiranguSisestuslahter.getText()));
        ylesandedController.uuendaAega(kasAjapeale(), Integer.parseInt(ajapiiranguSisestuslahter.getText())*60);}
        else if(ajapiiranguSisestuslahter.getText().isEmpty()){

            ylesandedController.uuendaAega(kasAjapeale(), 10000000); //tegelikult peaks endtime siin olema nö lõpmatus - exception vaja tekitada vajadusel
        }
        ylesandedController.setYlesandeidKokkuTekst(ylesannetePiirarvuSisestuslahter.getText());

        if(kasAjapeale()){
        Harjutuskord h1 = new Harjutuskord(true,Integer.parseInt(ajapiiranguSisestuslahter.getText()),tehted,Integer.parseInt(raskusastmeSisestuslahter.getText()));
            ylesandedController.setHarjutuskord(h1);
        ylesandedController.setYlesandeTekst(h1);}
        if(!kasAjapeale()){
            Harjutuskord h1 = new Harjutuskord(false,Integer.parseInt(ylesannetePiirarvuSisestuslahter.getText()),tehted,Integer.parseInt(raskusastmeSisestuslahter.getText()));
            ylesandedController.setHarjutuskord(h1);
            ylesandedController.setYlesandeTekst(h1);}


        abiinfoNuppValikudLehel.getScene().setRoot(root);


    }

    @FXML
    private void vajutaTehe(Event evt) {
        ToggleButton nupp = (ToggleButton)evt.getSource();

        raskusastmeSisestuslahter.setDisable(false);
        String tehe = nupp.getText();
        if(tehted.contains(nupp.getText())){
            tehted.remove(nupp.getText());
            if(nupp.isSelected());{
                nupp.setSelected(false);
            nupp.setStyle("-fx-background-color: rgba(230, 230, 230, 1);");}
        }
        else{
            if(nupp.isSelected()){nupp.setStyle("-fx-background-color: rgba(0, 204, 0, 1);");}
        tehted.add(tehe);}
        System.out.println(nupp.getText());

    }

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
                       // raskusastmeSisestuslahter.setDisable(true);
                    }
                    if (vastus[0].contains("hulk")) {
                        ajapiiranguSisestuslahter.clear();
                       // raskusastmeSisestuslahter.setDisable(true);
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

        return false;
    }


    private boolean raskus(){

        boolean raskusb = false;

        try{int r = Integer.parseInt(raskusastmeSisestuslahter.getText());
            if(r>10){raskusb = true;}}
      finally {
            return raskusb;
        }


    }


    private boolean yl(){
        boolean yb= false;

        try{int ry = Integer.parseInt(ylesannetePiirarvuSisestuslahter.getText());
            if(ry>=1){yb = true; }}

       finally {
           return yb;
        }
    }

    private  boolean aeg(){
        boolean ab= false;

        try{int ry = Integer.parseInt(ajapiiranguSisestuslahter.getText());
            if(ry>=1){ab = true;} }

        finally {return ab;}
    }

    @FXML
    public void lubaAlustada() {

        boolean onProbleemNimega = lahendajaNimeSisestuslahter.getText().isEmpty();
        ylesanneteHulgaKauduPiiramiseRaadionupp.setDisable(onProbleemNimega);
        ajaKauduPiiramiseRaadionupp.setDisable(onProbleemNimega);

        boolean onProbleemTehetega = lahendajaNimeSisestuslahter.getText().isEmpty() || (ylesannetePiirarvuSisestuslahter.getText().isEmpty() && ajapiiranguSisestuslahter.getText().isEmpty()||
                (ylesanneteHulgaKauduPiiramiseRaadionupp.isSelected()&& !yl()) ||(ajaKauduPiiramiseRaadionupp.isSelected() && !aeg()));
        for (int i = 0; i < nupud.size(); i++) {
            nupud.get(i).setDisable(onProbleemTehetega);
        }
        raskusastmeSisestuslahter.setDisable(onProbleemTehetega);
        if(onProbleemTehetega){alustaLahendamistNupp.setDisable(true);}

        if(raskusastmeSisestuslahter.getText().isEmpty() || !raskus()){ alustaLahendamistNupp.setDisable(true); }


        if(raskus()&& (yl() || aeg())){

            alustaLahendamistNupp.setDisable(false);
        }

    }

    public void initialize() {
        alustaLahendamistNupp.setDisable(true);
        alustaLahendamistNupp.setStyle("-fx-background-color: rgba(0, 204, 0, 1);");
        nupud.add(jagamisNupp);
        nupud.add(plussNupp);
        nupud.add(korrutusNupp);
        nupud.add(miinusNupp);
        for (int i = 0; i < nupud.size(); i++) {
            nupud.get(i).setStyle("-fx-background-color: rgba(230, 230, 230, 1);");
            nupud.get(i).setDisable(true);
        }
        ylesannetePiirarvuSisestuslahter.setDisable(true);
        ajapiiranguSisestuslahter.setDisable(true);
        raskusastmeSisestuslahter.setDisable(true);
        ajaKauduPiiramiseRaadionupp.setDisable(true);
        ylesanneteHulgaKauduPiiramiseRaadionupp.setDisable(true);
    }
}
