package task6;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ClientRmiTask6 {
    public static void main(String[] args) {
// Set the security manager if it has not already been set
        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }
        try {
            // Get the registry on the specified host and port
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);

            // Look up the remote object in the registry and cast it to the correct type
            DataService dataService = (DataService) registry.lookup("DataService");

            // Send a request for filtered data to the server
            String criteria = "rooms 2"; // example criteria
            String filteredData = dataService.getFilteredData(criteria);

            System.out.println("Received data: " + filteredData);
        } catch (RemoteException | NotBoundException e) {
            e.printStackTrace();
        }
    }
}
