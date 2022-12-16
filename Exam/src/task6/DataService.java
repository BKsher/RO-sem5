package task6;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface DataService extends Remote {
    String getFilteredData(String criteria) throws RemoteException;
}
