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




    private final Integer endtime =15; // siia tuleb panna määratud aeg

    private Integer starttime =0;
    private Timeline timeline;
    private IntegerProperty timeseconds= new SimpleIntegerProperty(starttime);
    private IntegerProperty timeseconds2= new SimpleIntegerProperty(endtime);


    public void uuendaAega(){
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

         timeline.playFromStart();
     }

     /*public void uuendaAllesJaanudAega(){
         allesjaanudAegTekst.textProperty().bind(timeseconds2.asString());
         timeseconds2.set(endtime);
         timeline2 = new Timeline();
         timeline.getKeyFrames().add(
                 new KeyFrame(Duration.seconds(endtime+1),
                         new KeyValue(timeseconds2, 0)));
         timeline.playFromStart();

     }*/
     public void setYlesandeidKokkuTekst(String ylpiirarv){
         if(!ylpiirarv.isEmpty()){
     ylesandeidKokkuTekst.setText(ylpiirarv);}
     else{ ylesandeidKokkuTekst.setText("Pole määratud");}

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
        System.out.println("2");
    }


}
