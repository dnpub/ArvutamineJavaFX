/*TODO: akna mõõtmete muutmine - seo sisuga
/TODO: vigade händlimine (täisarvud) ja esitus valikud aknas
TODO: kujundus
TODO: abiinfo import failist
TODO: harjutuskäigu statistika salvestamine faili - vastav nupp või hüpikaken Ylesanded lehele.
TODO: kujundus
TODO: koodi optimeerimine
*/
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;


import javax.swing.text.TabExpander;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.Set;

import static javafx.scene.text.TextAlignment.CENTER;


public class YlesandedController {
public YlesandedController(){}
    @FXML
    private Button abiinfoNuppYlesandedLehel;

    @FXML
    private Button katkestaNuppYlesandedLehel;

    @FXML
    private TextField vastuseSisestuslahter;

    @FXML
    private Button kinnitaVastusNupp;

    @FXML
    private Text lahendajaNimiTekst;

    @FXML
    private Text teheteValikTekst;

    @FXML
    private Text raskusasteTekst;

    @FXML
    private Text kulunudAegTekst;

    @FXML
    private Text allesjaanudAegTekst;

   @FXML
    private Text ylesandeidKokkuTekst;

   @FXML
   private Text ylesandeidTehtudTekst;

   @FXML
   private Text oigeidVastuseidTekst;

   @FXML
   private Text ylesandeTekst;
   @FXML
   private Text vastuseHindamiseTekst;

   private Integer Kontrollvastus;
   private Integer õigeidVastuseid=0;
   private Integer ylesandeidVastatud=0;
   private boolean kasAjaPeale;
   private Integer kontrollAeg;




    public void setKontrollAeg(int aegMinutites){
       kontrollAeg = aegMinutites*60;
   }

   public void setKasAjaPeale(boolean t){
       kasAjaPeale = t;
   }



    private Harjutuskord harjutuskord;

   // public final Integer endtime =15; // siia tuleb panna määratud aeg
    private Integer starttime =0;
    private Timeline timeline;
    private IntegerProperty timeseconds;//= new SimpleIntegerProperty(starttime);
    private IntegerProperty timeseconds2;//= new SimpleIntegerProperty(endtime);




    public void uuendaAega(boolean kasajapeale, int endtime){

        timeseconds= new SimpleIntegerProperty(starttime);
        timeseconds2= new SimpleIntegerProperty(endtime);
        if(kasajapeale){
        kulunudAegTekst.textProperty().bind(timeseconds.asString());
        allesjaanudAegTekst.textProperty().bind(timeseconds2.asString());

        timeseconds.set(starttime);

        timeline = new Timeline();

         timeline.getKeyFrames().add(
                 new KeyFrame(Duration.seconds(endtime+1),
                         new KeyValue(timeseconds, endtime)));

        timeline.getKeyFrames().add(
                new KeyFrame(Duration.seconds(endtime+1),
                        new KeyValue(timeseconds2, 0)));

        timeline.setOnFinished(event -> {vastuseHindamiseTekst.setText("Tubli! Ajalimiit on täis!\n Tulemused salvestati!" );
            kinnitaVastusNupp.setDisable(true);
            vastuseSisestuslahter.setDisable(true);
            ylesandeTekst.setText("");
            katkestaNuppYlesandedLehel.setText("Sulge");
            harjutuskord.setLahendamiseAeg(timeseconds.getValue().intValue());//lahendamisaja kättesaadavaks tegemine välja trükkivale komponendile
            harjutuskord.setLahendatudYlesandeid(ylesandeidVastatud);//lahendatud ülesannete koguse kättesaadavaks tegemine välja trükkivale komponendile
            harjutuskord.setOigeidVastuseid(õigeidVastuseid);//õiged vastused väljatrükkijale kättesaadavaks
            try {
                harjutuskord.kirjutaHarjutuskorraTulemusedFaili();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }) ;

         timeline.playFromStart();

         }
         else{kulunudAegTekst.textProperty().bind(timeseconds.asString());

            allesjaanudAegTekst.setText("-");
            timeseconds.set(starttime);

            timeline = new Timeline();

            timeline.getKeyFrames().add(
                    new KeyFrame(Duration.seconds(endtime+1),
                            new KeyValue(timeseconds, endtime)));
            timeline.playFromStart();}
     }


   public void setHarjutuskord(Harjutuskord h){
        harjutuskord = h;

   }
@FXML
   public void setYlesandeTekst(Harjutuskord h){
      // ylesandeTekst.setTextOrigin(VPos.TOP);
      // Scene scene = ylesandeTekst.getScene();

        Ülesanne yl1 = h.genereeriÜlesanne();
        if(kasAjaPeale){
        if(Integer.parseInt(kulunudAegTekst.getText())<kontrollAeg){

            ylesandeTekst.setText(yl1.toString());

        Kontrollvastus =Integer.parseInt(yl1.getVastus());}
       /* else if(Integer.parseInt(kulunudAegTekst.getText())>=kontrollAeg){
           // System.out.println(kulunudAegTekst.getText());
            //System.out.println(kontrollAeg);
            kinnitaVastusNupp.setDisable(true);
            vastuseSisestuslahter.setDisable(true);
           // vastuseHindamiseTekst.setText("Tubli! Ajalimiit on täis!"); //
            }*/
        }
        if(!kasAjaPeale){
            if(Integer.parseInt(ylesandeidTehtudTekst.getText()) < Integer.parseInt(ylesandeidKokkuTekst.getText())){
                ylesandeTekst.setText(yl1.toString());
                Kontrollvastus =Integer.parseInt(yl1.getVastus());
            }
            else{kinnitaVastusNupp.setDisable(true);
            vastuseSisestuslahter.setDisable(true);

            ylesandeTekst.setText("");

            timeline.stop();
                katkestaNuppYlesandedLehel.setText("Sulge");
            vastuseHindamiseTekst.setText("Tubli! Kõik ülesanded on lahendatud!\n Tulemused salvestati!");
                harjutuskord.setLahendamiseAeg(timeseconds.getValue().intValue());//lahendamisaja kättesaadavaks tegemine välja trükkivale komponendile
                harjutuskord.setLahendatudYlesandeid(ylesandeidVastatud);//lahendatud ülesannete koguse kättesaadavaks tegemine välja trükkivale komponendile
                harjutuskord.setOigeidVastuseid(õigeidVastuseid);//õiged vastused väljatrükkijale kättesaadavaks
                try {
                    harjutuskord.kirjutaHarjutuskorraTulemusedFaili();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }}
        }



   }

   @FXML
   public void setOigeidVastuseidTekst(){
        oigeidVastuseidTekst.setText(õigeidVastuseid.toString());

   }

   @FXML
   public void setYlesandeidTehtudTekst(){
        ylesandeidTehtudTekst.setText(ylesandeidVastatud.toString());
   }


     public boolean vastusVastabFormaadile(){
        boolean vastab = false;
        try{
            int v = Integer.parseInt(vastuseSisestuslahter.getText());


        vastab = true;}

        finally { return vastab;
        }
     }

    @FXML
    private void sisestaVastus(){

        if(vastuseSisestuslahter.getText().isEmpty()){kinnitaVastusNupp.setDisable(true);}
        if(!vastuseSisestuslahter.getText().isEmpty()){
            if(vastusVastabFormaadile()){
                System.out.println("Vastab");
                kinnitaVastusNupp.setDisable(false);}
                else{ kinnitaVastusNupp.setDisable(true);}




            vastuseSisestuslahter.setOnKeyPressed(new EventHandler<KeyEvent>()
            {
                @Override
                public void handle(KeyEvent w)
                {
                    if (w.getCode().equals(KeyCode.ENTER))
                    {
                        String vastus = vastuseSisestuslahter.getText();

                        Integer vastusInt =Integer.parseInt(vastus);

                        vastuseSisestuslahter.clear();
                        kinnitaVastusNupp.setDisable(true);
                        if(vastusInt.equals(Kontrollvastus)){
                            vastuseHindamiseTekst.setText("Tubli! Õige vastus!");
                            õigeidVastuseid++;
                            setOigeidVastuseidTekst();
                            ylesandeidVastatud++;
                            setYlesandeidTehtudTekst();;
                        }
                        else{vastuseHindamiseTekst.setText("Vale! Õige vastus oli: " + Kontrollvastus);
                            ylesandeidVastatud++;
                            setYlesandeidTehtudTekst();;}
                        setYlesandeTekst(harjutuskord);
                    }
                }
            });
            kinnitaVastusNupp.setOnMouseClicked(new EventHandler<MouseEvent>() {
                public void handle(MouseEvent e) {
                    String vastus = vastuseSisestuslahter.getText();

                    Integer vastusInt =Integer.parseInt(vastus);

                    vastuseSisestuslahter.clear();
                    kinnitaVastusNupp.setDisable(true);
                    if(vastusInt.equals(Kontrollvastus)){
                        vastuseHindamiseTekst.setText("Tubli! Õige vastus!");
                        õigeidVastuseid++;
                        setOigeidVastuseidTekst();
                        ylesandeidVastatud++;
                        setYlesandeidTehtudTekst();
                    }
                    else{vastuseHindamiseTekst.setText("Vale vastus! Õige oli: " + Kontrollvastus);
                        ylesandeidVastatud++;
                        setYlesandeidTehtudTekst();;}
                    setYlesandeTekst(harjutuskord);

                }


            });}

    }

     public void setYlesandeidKokkuTekst(String ylpiirarv){
         if(!ylpiirarv.isEmpty()){
     ylesandeidKokkuTekst.setText(ylpiirarv);}
     else{ ylesandeidKokkuTekst.setText("-");}

     }
    public  void setRaskusasteTekst(String raskusaste){
        raskusasteTekst.setText(raskusaste);

    }

    public void setLahendajaNimiTekst(String nimi){
        lahendajaNimiTekst.setText(nimi);
    }

    public void setTeheteValikTekst(Set<String> tehted){

        StringBuilder stringBuilder =new StringBuilder();
        Iterator<String> iterator = tehted.iterator();
        while(iterator.hasNext()){
            stringBuilder.append(iterator.next());
            stringBuilder.append(" ");
        }

        teheteValikTekst.setText(stringBuilder.toString());
    }

    @FXML
    private void avaAbiinfoYl() throws IOException{
        FXMLLoader loader = FXMLLoader.load(getClass().getResource("Abiinfo.fxml"));
        Parent root = loader.load();


        Stage stage = new Stage();
        stage.setScene(new Scene(root, 600,400));
        stage.show();
    }

    @FXML
    private void katkesta()throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Valikud.fxml"));
        Parent root = loader.load();
        lahendajaNimiTekst.getScene().setRoot(root);

    }

    @FXML
    private void initialize(){


        kinnitaVastusNupp.setDisable(true);
        kinnitaVastusNupp.setStyle("-fx-background-color: rgba(0, 204, 0, 1);");
        System.out.println("2");


    }


}
