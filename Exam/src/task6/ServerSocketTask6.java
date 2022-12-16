package task6;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import task6.House;

public class ServerSocketTask6 {
    private static House[] houses = House.createHouses();

    public static void main(String[] args) {
        int port = Integer.parseInt("8080");
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            while (true) {
                Socket socket = serverSocket.accept();
                new Thread(() -> {
                    try {
                        System.out.println("Client connected");
                        InputStream input = socket.getInputStream();
                        OutputStream output = socket.getOutputStream();
                        Scanner scanner = new Scanner(input, StandardCharsets.UTF_8);
                        String criteria = scanner.nextLine();
                        System.out.println("Received criteria: " + criteria);
                        String filteredData = applyCriteria(criteria);
                        output.write(filteredData.getBytes());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String applyCriteria(String criteria) {
        System.out.println(criteria);
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