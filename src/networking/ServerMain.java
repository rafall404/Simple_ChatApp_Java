package networking;

import model.DataModel;
import model.ObservableModel;
import java.io.IOException;

public class ServerMain {
    public static void main(String[] args) throws IOException {
        ObservableModel dataModel = new DataModel();
        Server server = new Server(3216, dataModel);

        server.run();
    }
}
