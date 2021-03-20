package client.model;

import client.network.Client;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Date;

public class DataModelManager implements DataModel, PropertyChangeSubject
{
    private PropertyChangeSupport propertyChangeSupport;
    private Client client;

    public DataModelManager(Client client) {
        propertyChangeSupport = new PropertyChangeSupport(this);
        this.client = client;
        client.startClient();
        client.addPropertyChangeListener("updated", this::updated);
    }

    private void updated(PropertyChangeEvent propertyChangeEvent)
    {
        propertyChangeSupport.firePropertyChange(propertyChangeEvent);
    }

    @Override
    public String getLastUpdateTimeStamp() {
        return client.getLastUpdateTimeStamp();
    }

    @Override
    public int getNumberOfUpdates() {
        return client.getNumberOfUpdates();
    }

    @Override
    public void setTimeStamp(Date timeStamp) {
        client.setTimeStamp(timeStamp);
    }

    @Override
    public void addPropertyChangeListener(String name, PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(name, listener);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(listener);
    }

    @Override
    public void removePropertyChangeListener(String name, PropertyChangeListener listener) {
        propertyChangeSupport.removePropertyChangeListener(name, listener);
    }

    @Override
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.removePropertyChangeListener(listener);
    }
}
