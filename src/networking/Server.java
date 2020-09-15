package networking;

import model.ObservableModel;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements Runnable {
    private ServerSocket serverSocket;
    private ObservableModel model;


    public Server(int port, ObservableModel model) throws IOException {
        this.model=model;
        this.serverSocket = new ServerSocket(port);
    }

    @Override
    public void run() {
        try{
            while(true){
                System.out.println("Where is client? ...");

                Socket socket1 = serverSocket.accept();

                ClientSHandler s = new ClientSHandler(socket1, model );
                System.out.println("Oh.. here You are <Client connected>");

                new Thread(s).start();
            }

        }catch(IOException e){
            throw new RuntimeException(e);
        }
    }
}
