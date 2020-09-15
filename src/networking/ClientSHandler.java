package networking;

import model.Message;
import model.ObservableModel;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientSHandler implements Runnable, PropertyChangeListener {

    private ObservableModel model;

    private Socket socket;
    private ObjectOutputStream outToClient;
    private ObjectInputStream inFromClient;


    public ClientSHandler(Socket socket, ObservableModel model) throws IOException {
        this.model = model;
        this.socket = socket;
        inFromClient = new ObjectInputStream(socket.getInputStream());
        outToClient = new ObjectOutputStream(socket.getOutputStream());

        model.addListener(this);
    }

    @Override
    public void run() {
        try{

            String userName = (String)inFromClient.readObject();
            model.registerUser(userName);
            System.out.println(userName);
            while(true){
                Message message = (Message)inFromClient.readObject();
                model.addMessageToChat(message.getMessage(),message.getUser());
                System.out.println(message);
            }
        }catch(IOException | ClassNotFoundException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        try {
            outToClient.writeObject(evt);
        }catch(IOException e){
            throw new RuntimeException(e);
        }
    }
}
