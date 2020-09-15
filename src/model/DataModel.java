package model;


import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

public class DataModel implements ObservableModel {

    private ArrayList<String> listOfUsers;
    private ArrayList<Message> chat;

    private PropertyChangeSupport support;

    public DataModel(){
        listOfUsers = new ArrayList<>();
        chat = new ArrayList<>();

        support = new PropertyChangeSupport(this);
    }

    @Override
    public void registerUser(String nickName) {
        listOfUsers.add(nickName);
    }

    @Override
    public void addMessageToChat(String message, String nickName) {
        Message message1 = new Message(nickName, message);
        chat.add(message1);

        support.firePropertyChange("addMessage", null, message1 );
    }

    @Override
    public void addListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    @Override
    public void removeListener(PropertyChangeListener listener) {
        support.removePropertyChangeListener(listener);
    }
}
