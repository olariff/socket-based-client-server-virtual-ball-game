package Server;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;

public class ClientHandler implements Runnable{
    private final Socket socket;
    private Ball ball;
    private int uniqueId;
    private int j;
    private static final Map<Integer, PrintWriter> updateClient = new TreeMap<>();
    private static final List<String> list = new ArrayList<>();

    public ClientHandler(Socket socket, Ball ball) {
        this.socket = socket;
        this.ball = ball;
        uniqueId = 0;
        j = 0;

    }

    @Override
    public void run() {

        try {
                Scanner scanner = new Scanner(socket.getInputStream());
                PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);

            try {

                uniqueId = ball.addPlayer();
                System.out.println("Player " + uniqueId + " has joined the game");
                list.add("Player " + uniqueId + " has joined the game");

                System.out.println("\nThe palyers currently in the game are: ");
                for (int i = 0;i < ball.playerList().size();i++){
                    System.out.println("Player " + ball.playerList().get(i));
                }
                ball.giveBall();
                writer.println("Player " + ball.getPlayerWithBall() + " is currently with the ball");


                while (true) {

                    String line = scanner.nextLine();
                    String[] substrings = line.split(" ");
                    switch (substrings[0].toLowerCase()) {

                        case "players":
                            writer.println(ball.playerList().size());
                            for (int i = 0;i < ball.playerList().size();i++){
                                writer.println(ball.playerList().get(i));
                            }

                            break;

                        case "currentclient":
                            int currentClient = uniqueId;
                            writer.println(currentClient);
                            break;

                        case "list":
                            int in = Integer.parseInt(substrings[1]);
                            writer.println(list.get(in));
                            break;

                        case "playerwithball":
                            writer.println(ball.getPlayerWithBall());
                            break;

                        case "transfer":
                            int fromPlayer = ball.getPlayerWithBall();
                            int toPlayer = Integer.parseInt(substrings[1]);
                            ball.transfer(fromPlayer, toPlayer);
                            break;

                        case "clearlist1":
                            list.clear();
                            break;

                        case "getlistsize1":
                            writer.println(list.size());
                            break;

                        case "getlistelement1":
                            writer.println(list.get(0));
                            break;

                        default:
                            throw new Exception("Unknown command: " + substrings[0]);

                    }

                }


            } catch (Exception e) {
                writer.println("ERROR " + e.getMessage());
                socket.close();
            }
        } catch (Exception e) {
        } finally {

            int removePlayer = ball.removePlayer(uniqueId);

            if (ball.getPlayerWithBall() == removePlayer){
                ball.giveBall();
            }
            System.out.println("Player " + ball.giveBall() + " has automatically been passed the ball");
            System.out.println("Player " + uniqueId + " has left the game");
        }
    }

}
