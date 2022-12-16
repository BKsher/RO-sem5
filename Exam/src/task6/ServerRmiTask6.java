package task6;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ServerRmiTask6 extends UnicastRemoteObject implements DataService2 {
    private static House[] houses = House.createHouses();

    public ServerRmiTask6() throws RemoteException {
        super();
    }

    @Override
    public String applyCriteria(String criteria) {
        // Parse the criteria string to determine the type of request
        String[] parts = criteria.split(" ");
        if (parts[0].equals("rooms")) {
            // Handle request for a list of apartments with a given number of rooms
            int numRooms = Integer.parseInt(parts[1]);
            return filterByNumRooms(numRooms);
        } else if (parts[0].equals("rooms_and_floor")) {
            // Handle request for a list of apartments with a given number of rooms and located on a floor within a given interval
            int numRooms = Integer.parseInt(parts[1]);
            int floorMin = Integer.parseInt(parts[2]);
            int floorMax = Integer.parseInt(parts[3]);
            return filterByNumRoomsAndFloor(numRooms, floorMin, floorMax);
        } else if (parts[0].equals("area")) {
            // Handle request for a list of apartments with an area exceeding a given value
            double area = Double.parseDouble(parts[1]);
            return filterByArea(area);
        }
        return "";
    }

    private static String filterByNumRooms(int numRooms) {
        // Apply the filter for number of rooms and return the filtered data
        StringBuilder sb = new StringBuilder();
        for (House house : houses) {
            if (house.getNumRooms() == numRooms) {
                sb.append(house.toString()).append("\n");
            }
        }
        return sb.toString();
    }

    private static String filterByNumRoomsAndFloor(int numRooms, int floorMin, int floorMax) {
        // Apply the filter for number of rooms and floor and return the filtered data
        StringBuilder sb = new StringBuilder();
        for (House house : houses) {
            if (house.getNumRooms() == numRooms && house.getFloor() >= floorMin && house.getFloor() <= floorMax) {
                sb.append(house.toString()).append("\n");
            }
        }
        return sb.toString();
    }

    private static String filterByArea(double area) {
        StringBuilder sb = new StringBuilder();
        for (House house : houses) {
            if (house.getArea() >= area) {
                sb.append(house.toString()).append("\n");
            }
        }
        return sb.toString();
    }
}

