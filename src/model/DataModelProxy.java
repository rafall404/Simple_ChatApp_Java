package model;

import networking.Client;

import java.beans.PropertyChangeListener;
import java.io.IOException;

public class DataModelProxy implements ObservableModel {

    private Client client;


    public DataModelProxy(Client client){
        this.client=client;
    }

    public void addListener(PropertyChangeListener listener){
        client.addListener(listener);
    }

    public void removeListener(PropertyChangeListener listener){
        client.removeListener(listener);
    }

    @Override
    public void registerUser(String nickName) {
        try{
            client.registerUser(nickName);

        }catch(IOException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public void addMessageToChat(String message, String nickName) {
        try{
            client.addMessageToChat(message);
        }catch(IOException e){
            throw new RuntimeException(e);
        }
    }

}
