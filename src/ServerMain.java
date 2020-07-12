import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerMain {

    private int id = 1;

    public ServerMain(){
    }

    public void startWorking(int port){

        try(ServerSocket serverSocket = new ServerSocket(port)){
            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("New client connected. Client.Client ID is: " + id);
                id++;
                new ServerThread(socket, id).start();
            }
        } catch(Exception e) {
            System.out.println("Server exception: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void startWorking(){
        startWorking(6667);
    }
}
