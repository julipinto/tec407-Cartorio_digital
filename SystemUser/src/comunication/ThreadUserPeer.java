package comunication;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import model.DataProcess;

public class ThreadUserPeer extends Thread{
    private final UserPeer userPeer;
    private InputStream input;
    private final byte[] emptyByteArrayReference;
    private byte[] reciveReference;   
    private final DataProcess dataProcess;
    
    public ThreadUserPeer(UserPeer userPeer) {
        this.userPeer = userPeer;
        emptyByteArrayReference = new byte[]{}; 
        dataProcess = new DataProcess();
    }
    
    @Override
    public void run(){
        userPeer.conect();
        input = userPeer.getInputStream();
        new ThreadAcceptNewConnections(userPeer.getPort()).start();
        while(!Thread.currentThread().isInterrupted()){
            reciveReference = toByteArray(input);
            if(reciveReference.length > 0){
                dataProcess.pullMessage(reciveReference);
            }
        }        
    }
    
    private byte[] toByteArray(InputStream input){
        DataInputStream dataInputStream = new DataInputStream(input);
        
        byte buffer[] = emptyByteArrayReference;
        try {
            if(dataInputStream.available() > 0){
                buffer = new byte[dataInputStream.available()];
                dataInputStream.readFully(buffer);
            }    
        } catch (IOException ex) {
            
        }
        return buffer;
    }        
    
}
