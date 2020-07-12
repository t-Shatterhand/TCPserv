import java.net.*;
import java.io.*;

public class Client {

    public static void main(String[] args) {
        if (args.length < 2) return;

        String host = args[0];
        int port = Integer.parseInt(args[1]);

        try (Socket socket = new Socket(host, port)) {
            OutputStream output = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(output, true);

            Console console = System.console();
            String text;

            do {
                text = console.readLine("ENTER COMMAND: ");

                writer.println(text);

                InputStream input = socket.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(input));

                String time = reader.readLine();

                System.out.println(time);

            } while (!text.equals("CLOSE CONNECTION"));

            socket.close();
        } catch (UnknownHostException e) {
            System.out.println("HOSTNAME INCORRECT: " + e.getMessage());
        } catch (IOException ex) {
            System.out.println("IO ERROR: " + ex.getMessage());
        }
    }
}