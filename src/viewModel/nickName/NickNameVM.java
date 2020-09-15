package viewModel.nickName;


import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.Model;

public class NickNameVM {

    private StringProperty nickName;
    private Model model;

    public NickNameVM(Model model){
        this.model=model;
        nickName= new SimpleStringProperty();
    }

    public StringProperty getNickName(){
        return nickName;
    }

    public void registerUser(){
        model.registerUser(nickName.get());
    }
}
