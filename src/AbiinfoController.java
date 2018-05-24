import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;

public class AbiinfoController implements Initializable {
    @FXML
    private Button abiinfoSulgeNupp;

    @FXML
    private Text abiinfoTekst;

    @FXML
    private TextFlow tekstiRaam;
    @FXML
    private AnchorPane ankruPaan;

    int riduFailis = 30;

    //meetodid

    private String loeAbiinfoFailist(){
        String abiInfo = null;
        try {
            File fail = new File ("abiinfo.txt");
            Scanner faililugeja = new Scanner(fail,"UTF-8");

            ArrayList<String> abiTekstiRidadeLoend = new ArrayList<>();

            while (faililugeja.hasNextLine()) {
                String rida = faililugeja.nextLine();
                abiTekstiRidadeLoend.add(rida);
                riduFailis ++;
            }

            abiInfo = listStringiks(abiTekstiRidadeLoend);
            //System.out.println(abiInfo);

        } catch (FileNotFoundException e) {
            System.out.println("Faili abiinfo.txt ei leitud programmiga samast kaustast.");
        }


        return abiInfo;
    }

    @FXML
    private void sulgeAbiinfo(){
        Stage stage = (Stage) abiinfoSulgeNupp.getScene().getWindow();

        stage.close();}

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        abiinfoTekst.setText(loeAbiinfoFailist());//loeb teksti failist muutujasse enne, kui aken nähtavale tuuakse
        tekstiRaam.setPrefHeight(riduFailis*10.0); //suurendab/vähendab tekstiraami vastavalt abiinfo failis olevate ridade arvule
        ankruPaan.setPrefHeight((riduFailis*10.0)+20.0);////suurendab/vähendab ankrupaani vastavalt abiinfo failis olevate ridade arvule ja jätab alla ka väikse varu tühja ruumi


    }

    //meetod selleks, et failist loetud read muuta selliseks sõneks, millel ei ole ebavajalikke vahepealseid eraldusmärke ning väljatrükkimisel näeks tekst välja samasugune nagu lähtefailis
    public static String listStringiks(List<String> list) {
        String tulemus = " ";
        for (int i = 0; i < list.size(); i++) {
            tulemus += "\n" + list.get(i);
        }
        return tulemus;
    }
}
