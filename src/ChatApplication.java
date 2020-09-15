import javafx.application.Application;
import javafx.stage.Stage;
import model.DataModelProxy;
import model.ObservableModel;
import networking.Client;
import view.ViewHandler;
import viewModel.VMFactory;

public class ChatApplication extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Client client = new Client("localhost", 3216);
        new Thread(client).start();

        ObservableModel proxy = new DataModelProxy(client);
        VMFactory vm = new VMFactory(proxy);
        ViewHandler viewHandler = new ViewHandler(primaryStage, vm);
        viewHandler.openView("nickName");


    }

}
