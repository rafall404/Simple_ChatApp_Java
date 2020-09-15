package view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import view.chat.ChatController;
import view.nickName.NickNameController;
import viewModel.VMFactory;

import java.io.IOException;

public class ViewHandler {
    private Stage stage;
    private VMFactory vm;


    public ViewHandler(Stage stage, VMFactory vm) {
        this.stage=stage;
        this.vm=vm;

    }

    public void openView(String viewToOpen) {
        Scene scene  = null;
        FXMLLoader loader = new FXMLLoader();
        Parent root = null;
        try {
            if (viewToOpen.equals("nickName")) {
                loader.setLocation(getClass().getResource("nickName/nickName.fxml"));
                root = loader.load();
                loader.<NickNameController>getController().init(vm.getNickNameVM(), this);

                stage.setTitle("Chat Application");
            } else if (viewToOpen.equals("chat")) {
                loader.setLocation(getClass().getResource("chat/chat.fxml"));
                root = loader.load();
                loader.<ChatController>getController().init(vm.getChatVM(), this);

                stage.setTitle("Chat Application");
            }
        }catch(IOException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Error!");
            alert.setContentText("Error message: " + e);
            alert.showAndWait();
        }
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void closeView(Stage stage){
        stage.close();
    }
}
