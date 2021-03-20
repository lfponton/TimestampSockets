package client.model;

public class ModelFactory {

    private DataModel dataModel;
    private ClientFactory clientFactory;

    public ModelFactory(ClientFactory clientFactory)
    {
        this.clientFactory = clientFactory;
    }
    public DataModel getDataModel() {
        if(dataModel == null) {
            dataModel = new DataModelManager(clientFactory.getClient());
        }
        return dataModel;
    }
}
