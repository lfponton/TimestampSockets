package client.network;

import client.model.PropertyChangeSubject;
import shared.Request;

import java.beans.PropertyChangeListener;
import java.util.Date;

public interface Client extends PropertyChangeSubject
{
  String getLastUpdateTimeStamp();
  int getNumberOfUpdates();
  void setTimeStamp(Date timeStamp);
  void startClient();
  void sendRequest(Request request);
}
