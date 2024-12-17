package programma.ui;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import programma.DB.CursusRepo;
import programma.DB.ModuleRepo;
import programma.DB.WebcastRepo;
import programma.domain.Cursus;
import programma.domain.Module;
import programma.logic.Validatie;

import java.net.URL;
import java.text.ParseException;
import java.util.ResourceBundle;

public class UpdateModuleController implements Initializable {
    NavbarController navbarController = new NavbarController();
    ModuleRepo moduleRepo = new ModuleRepo();


    static int indexModule = 0;


    @FXML
    private Button btnTerug;

    @FXML
    private TextField txtTitel;

    @FXML
    private TextField txtEmail;

    @FXML
    private Button btnAdd;

    @FXML
    private TextField txtVersie;

    @FXML
    private Label txtSucces;

    @FXML
    private TextField txtNaam;

    @FXML
    private TextField txtBeschrijving;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        txtTitel.setText(moduleRepo.getModule(indexModule).getTitel());
        txtEmail.setText(moduleRepo.getModule(indexModule).getContactEmail());
        txtVersie.setText(moduleRepo.getModule(indexModule).getVersie());
        txtBeschrijving.setText(moduleRepo.getModule(indexModule).getBeschrijving());
        txtNaam.setText(moduleRepo.getModule(indexModule).getContactNaam());




    }
    @FXML
    void handleAddButton(ActionEvent event) throws ParseException {
        if(!Validatie.validatieEmail(txtEmail.getText())){
            txtSucces.setText("Email is onjuist");
            txtSucces.setTextFill(Color.RED);
            return;
        }
        if (!txtEmail.getText().isEmpty() && !txtNaam.getText().isEmpty()
                && !txtBeschrijving.getText().isEmpty() && !txtTitel.getText().isEmpty() && !txtVersie.getText().isEmpty()) {
            try {
                moduleRepo.update(indexModule,new Module(txtEmail.getText(), txtNaam.getText(),txtBeschrijving.getText(), txtVersie.getText(), txtTitel.getText()));
                txtSucces.setText("Module succesvol\n geupdate");
                txtSucces.setTextFill(Color.GREEN);
            }catch (Exception e){
                txtSucces.setText("Module\n update mislukt");
                txtSucces.setTextFill(Color.RED);
                return;
            }
        }else {
            txtSucces.setText("Module update \nmislukt");
            txtSucces.setTextFill(Color.RED);
        }
    }

    @FXML
    void handleTerug() throws Exception{
        NavbarController.terug = 4;
        Parent root = FXMLLoader.load(getClass().getResource("fxml/addCursus.fxml"));
        Stage window = (Stage)btnTerug.getScene().getWindow();
        window.setScene(new Scene(root, 1080,600));
        window.setResizable(false);

    }

}
