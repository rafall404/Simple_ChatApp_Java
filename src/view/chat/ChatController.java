package view.chat;

import javafx.application.Platform;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import model.Message;
import view.ViewHandler;
import viewModel.chat.ChatVM;

public class ChatController {

    private ChatVM chatVM;
    private ViewHandler handler;

    @FXML
    private ListView<Message> chatList;

    @FXML
    private TextField messageField;

    public void init(ChatVM viewModel, ViewHandler handler) {
        chatVM = viewModel;
        this.handler = handler;

        chatVM.getMessage().bind(messageField.textProperty());

        chatVM.getChatList().addListener(new ListChangeListener<Message>() {
            @Override
            public void onChanged(Change<? extends Message> c) {
                Platform.runLater(() -> {
                    while (c.next()) {
                        chatList.getItems().addAll(c.getAddedSubList());
                        chatList.getItems().removeAll(c.getRemoved());
                    }
                });
            }
        });
    }

    public void onSendButton() {
        chatVM.sendMessage();
        messageField.setText("");
    }

    public void enterButtonPressed(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER) {
            onSendButton();
        }
    }
}
