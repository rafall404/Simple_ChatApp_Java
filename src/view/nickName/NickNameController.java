package view.nickName;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import view.ViewHandler;
import viewModel.nickName.NickNameVM;


public class NickNameController {

    private NickNameVM viewModel;
    private ViewHandler handler;

    @FXML
    private TextField nickNameField;

    public void init(NickNameVM viewModel, ViewHandler handler){
        this.viewModel=viewModel;
        this.handler=handler;

        viewModel.getNickName().bind(nickNameField.textProperty());
    }

    public void onJoinChatButton() {
        viewModel.registerUser();
        handler.openView("chat");
    }

    public void buttonPressed(KeyEvent keyEvent) {
        if(keyEvent.getCode()== KeyCode.ENTER){
            onJoinChatButton();
        }
    }
}
