package task6;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface DataService2 extends Remote {
    String applyCriteria(String criteria) throws RemoteException;
}
