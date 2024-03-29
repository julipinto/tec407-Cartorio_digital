package model;

import facade.FacadeFront;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;
import javafx.application.Platform;

/**
 *Instrumento que vê quando a informação desse usuário chegou do cartório 
 * e muda para sua tela home
 * @author Juliana
 */
public class ObserverData implements Observer {
        private final FacadeFront facadef;

    public ObserverData() throws IOException, ClassNotFoundException {
        facadef = FacadeFront.getInstance();
    }
        
        @Override
        public void update(Observable o, Object o1) {
            boolean check = (boolean) o1;
            if(check){
                Platform.runLater(facadef::loadHomeScreen);
                
            }else{
                Platform.runLater(facadef::loadLoginScreen);                
            }
        }
    
    }