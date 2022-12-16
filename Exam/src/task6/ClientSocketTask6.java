package task6;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class ClientSocketTask6 {
    public static void main(String[] args) {
        String hostname = "localhost";
        int port = Integer.parseInt("8080");
        try (Socket socket = new Socket(hostname, port)) {
            InputStream input = socket.getInputStream();
            OutputStream output = socket.getOutputStream();
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter the criteria for selecting data (e.g. number of rooms, floor, area): ");
            String criteria = scanner.nextLine();
            System.out.println("Sending criteria: " + criteria);
            output.write((criteria + "\n").getBytes(StandardCharsets.UTF_8));
            output.flush();
            int data;
            while ((data = input.read()) != -1) {
                System.out.print(data);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
