package networking;

import model.Message;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client implements Runnable {

    private Socket socket;
    private ObjectOutputStream outToServer;
    private ObjectInputStream inFromServer;

    private PropertyChangeSupport support;
    private String nickName;

    public Client(String address, int port) throws IOException {

        socket = new Socket(address,port);
        outToServer = new ObjectOutputStream(socket.getOutputStream());
        inFromServer = new ObjectInputStream(socket.getInputStream());

        support = new PropertyChangeSupport(this);
    }

    public void registerUser(String user) throws IOException {
        outToServer.writeObject(user);
        nickName=user;
    }

    public void addMessageToChat(String message) throws IOException {
        outToServer.writeObject(new Message(nickName,message));
    }

    public void addListener(PropertyChangeListener listener){
        support.addPropertyChangeListener(listener);
    }

    public void removeListener(PropertyChangeListener listener){
        support.removePropertyChangeListener(listener);
    }


    @Override
    public void run() {
        try{
            while(true){
                PropertyChangeEvent event = (PropertyChangeEvent)inFromServer.readObject();
                support.firePropertyChange(event);
            }

        }catch(IOException | ClassNotFoundException e){
            throw new RuntimeException(e);
        }

    }
}
