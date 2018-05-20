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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;


import javax.swing.text.TabExpander;
import java.io.IOException;
import java.util.Iterator;
import java.util.Set;


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

         timeline.playFromStart();}
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
        Ülesanne yl1 = h.genereeriÜlesanne();
        ylesandeTekst.setText(yl1.toString());
        Kontrollvastus =Integer.parseInt(yl1.getVastus());

   }

   @FXML
   public void setOigeidVastuseidTekst(){
        oigeidVastuseidTekst.setText(õigeidVastuseid.toString());

   }

   @FXML
   public void setYlesandeidTehtudTekst(){
        ylesandeidTehtudTekst.setText(ylesandeidVastatud.toString());
   }


    @FXML
    private void sisestaVastus(){

        if(!vastuseSisestuslahter.getText().isEmpty()){
            kinnitaVastusNupp.setDisable(false);
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

                    vastuseSisestuslahter.clear(); vastuseSisestuslahter.clear();
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
        Parent root = FXMLLoader.load(getClass().getResource("Abiinfo.fxml"));
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
        System.out.println("2");


    }


}
