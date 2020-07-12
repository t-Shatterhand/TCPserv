public class Main {

    public static void main(String[] args) {
        if (args.length < 1) return;
        ServerMain server = new ServerMain();
        server.startWorking(Integer.parseInt(args[0]));

    }
}
