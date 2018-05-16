import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.xml.soap.Text;
import java.io.IOException;


public class YlesandedController {

    @FXML
    private Button abiinfoNuppYlesandedLehel;

    @FXML
    private Button katkestaNuppYlesandedLehel;

    @FXML
    private TextField vastuseSisestuslahter;

    @FXML
    private Button kinnitaVastusNupp;

    @FXML
    private void avaAbiinfoYl() throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("Abiinfo.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(root, 600,400));
        stage.show();
    }

    @FXML
    private void katkesta(){
        Stage stage = (Stage) katkestaNuppYlesandedLehel.getScene().getWindow();
        stage.close();
    }

}
