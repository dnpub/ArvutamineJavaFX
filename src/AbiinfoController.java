import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;


public class AbiinfoController {
    @FXML
    private Button abiinfoSulgeNupp;

    @FXML
    private void sulgeAbiinfo(){
        Stage stage = (Stage) abiinfoSulgeNupp.getScene().getWindow();
        stage.close();
    }
}
