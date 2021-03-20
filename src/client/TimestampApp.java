package client;

import client.model.ClientFactory;
import javafx.application.Application;
import javafx.stage.Stage;
import client.model.ModelFactory;
import client.view.ViewHandler;
import client.viewmodel.ViewModelFactory;

@SuppressWarnings("ALL")
public class TimestampApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        ClientFactory clientFactory = new ClientFactory();
        ModelFactory mf = new ModelFactory(clientFactory);
        ViewModelFactory viewModelFactory = new ViewModelFactory(mf);
        ViewHandler viewHandler = new ViewHandler(stage, viewModelFactory);
        viewHandler.start();

    }

}
