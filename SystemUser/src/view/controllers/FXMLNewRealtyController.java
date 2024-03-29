package view.controllers;

import com.jfoenix.controls.JFXTextField;
import facade.FacadeBack;
import facade.FacadeComunication;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;
import org.json.JSONObject;
import util.Cript;

public class FXMLNewRealtyController implements Initializable {

    @FXML   private JFXTextField tfAddress;
    @FXML   private Label charterPath;
    
    private FileChooser fileChooser;
    private File selectedFile;
    private FacadeComunication facadec;
    private FacadeBack facadeb;
    private final Cript cript = new Cript();
    
    @FXML   private Label warn;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        charterPath.setVisible(false);
        warn.setVisible(false);
        
        try {
            facadec = FacadeComunication.getInstance();
            facadeb = FacadeBack.getInstance();
        } catch (IOException | ClassNotFoundException ex) {
            System.err.println(ex);
        }
    }    

    @FXML
    private void search(ActionEvent event) {
        fileChooser = new FileChooser();
        selectedFile = fileChooser.showOpenDialog(null);

        if (selectedFile != null) {
            charterPath.setVisible(true);
            charterPath.setText(selectedFile.getName());
        }
      
    }

    @FXML
    private void registerNewRealty(ActionEvent event) {
        if(selectedFile.isFile() && !tfAddress.getText().equals("")){
            JSONObject message = new JSONObject();
            message.accumulate("request", "signNewRealty");
            message.accumulate("host", facadec.getUserHost());
            message.accumulate("port", facadec.getUserPeerPort());
            
            JSONObject realtyInfos = new JSONObject();
            realtyInfos.accumulate("address", tfAddress.getText());
            realtyInfos.accumulate("charter", pathToByteString(selectedFile));
            realtyInfos.accumulate("ownerId", facadeb.getUser().getCpf());
            
            message.accumulate("realtyInfos", realtyInfos);
            facadec.sendMessageToCourthouse(message.toString());  
            
            tfAddress.setText("");
            charterPath.setText("");
            warn.setVisible(false);
        }else{
            warn.setVisible(true);
        }
    }
    
    private String pathToByteString(File file){
        try {
            return cript.BASE64encode(Files.readAllBytes(file.toPath()));
        } catch (IOException ex) {
            System.err.println(ex);
        }
        return null;
    }
    
}
