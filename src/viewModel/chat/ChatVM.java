package viewModel.chat;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Message;
import model.ObservableModel;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class ChatVM implements PropertyChangeListener {

    private StringProperty message;
    private ObservableList<Message> chatList;

    private ObservableModel model;


    public ChatVM(ObservableModel model){
        this.model=model;

        message = new SimpleStringProperty();
        chatList = FXCollections.observableArrayList();

        model.addListener(this);
    }

    public StringProperty getMessage(){
        return message;
    }

    public ObservableList<Message> getChatList(){
        return chatList;
    }

    public void sendMessage(){
        model.addMessageToChat(message.get(), "");
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        chatList.add((Message) evt.getNewValue());
    }
}
