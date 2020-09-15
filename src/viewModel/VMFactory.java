package viewModel;

import model.ObservableModel;
import viewModel.chat.ChatVM;
import viewModel.nickName.NickNameVM;

public class VMFactory {
    private NickNameVM nickNameVM;
    private ChatVM chatVM;

    public VMFactory(ObservableModel model){
        nickNameVM = new NickNameVM(model);
        chatVM = new ChatVM(model);

    }

    public NickNameVM getNickNameVM(){
        return nickNameVM;
    }

    public ChatVM getChatVM(){
        return chatVM;
    }


}
