package comunication;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class UserPeer {
    private Socket socket;
    private final int port;

    public UserPeer(int port){
        this.port = port;
    }
 
    void conect(){
        try {
            System.out.println("Esperando algum peer se conectar");
            socket = new ServerSocket(port).accept();
            System.out.println("peer conectado");
        } catch (IOException ex) {
            System.out.println("ex conect");
        }
    }
    
    public Socket getSocket(){
        return socket;
    }
    
    public int getPort(){
        return port;
    }
    
    public void closeSocket(Socket socket) throws IOException{
        socket.close();
    }         
      
}