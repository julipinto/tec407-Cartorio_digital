package view.controllers;

import facade.FacadeBack;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import model.ObserverData;

public class FXMLLoadingController implements Initializable{
    private FacadeBack facadeb;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            facadeb = FacadeBack.getInstance();
        } catch (IOException | ClassNotFoundException ex) {
            System.err.println(ex);
        }
        
        ObserverData observer;
        
        try {
            observer = new ObserverData();
            facadeb.addObservable(observer);
        } catch (IOException | ClassNotFoundException ex) {
            System.err.println(ex);
        }
        
    }    
    
}  
    
