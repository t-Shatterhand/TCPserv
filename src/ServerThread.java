import java.io.*;
import java.net.Socket;
import java.time.LocalDateTime;

public class ServerThread extends Thread{
    private final Socket socket;
    private final int identifier;

    public int getIdentifier() {
        return identifier;
    }

    public ServerThread(Socket socket, int id){
        this.socket = socket;
        this.identifier = id;
    }

    @Override
    public void run() {
        try {
            InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();

            BufferedReader bufferedInputStream = new BufferedReader(new InputStreamReader(inputStream));
            PrintWriter writer = new PrintWriter(outputStream, true);

            String inputText, outputText = "";
            boolean exitFlag = false;
            do {
                inputText = bufferedInputStream.readLine();
                switch (inputText){
                    case "WEATHER":
                        outputText = "WEATHER YET NOT AVAILABLE";
                        break;
                    case "DATE":
                    case "TIME":
                        outputText = "DATE/TIME: " + LocalDateTime.now().toString();
                        break;
                    case "CLOSE CONNECTION":
                        exitFlag = true;
                        outputText = "BYE!";
                        break;
                    default:
                        outputText = "INVALID COMMAND!";
                        break;
                }
                writer.println("SERVER: " + outputText);
            } while (!exitFlag);
            writer.println("CONNECTION CLOSED!");

        } catch (Exception e){
            System.out.println("Thread exception on id: " + identifier + ";\nMessage: " + e.getMessage());
            e.printStackTrace();
        }

    }
}
