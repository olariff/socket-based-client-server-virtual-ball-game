package Client;
import Server.ServerProgram;

import java.util.*;
import java.lang.*;

import static Server.ServerProgram.*;

public class ClientProgram {

    public static void main(String[] args) {

        try {
            Scanner scanner = new Scanner(System.in);

            try (Client client = new Client()) {

                int counter = 0;
                String message = "";


                System.out.println("You are player " + client.currentClient());
                int [] playerList = client.getUniqueIds();

                for (int i = 0;i < playerList.length;i++){
                    System.out.println("player " + playerList[i]);
                }

                while (true) {

                    while (true) {
                        if (client.getListSize() == counter) {
                            if(client.getPlayerWithball() == client.currentClient()) {

                                System.out.println("Who would you like to pass the ball to: ");
                                int toId = Integer.parseInt(scanner.nextLine());
                                client.transfer(toId);

                                playerList = client.getUniqueIds();
                                String mychar = "";

                                for (int i = 0;i < playerList.length;i++){
                                    mychar = mychar + " " + playerList[i];
                                }
                                System.out.println("The current players in the game are players: " + mychar);
                                System.out.println("Waiting for player " + client.getPlayerWithball() + " to pass the ball");
//
                            }
                            continue;
                        }

                        message = client.get(client.getListSize() - 1);
                        break;
                    }

                    if(client.getPlayerWithball() == client.currentClient()) {
                        System.out.println("Who would you like to pass the ball to: ");

                        int toId = Integer.parseInt(scanner.nextLine());
                        client.transfer(toId);

                        playerList = client.getUniqueIds();
                        String mychar = "";

                        for (int i = 0;i < playerList.length;i++){
                            mychar = mychar + " " + playerList[i];
                        }
                        System.out.println("The current players in the game are players: " + mychar);
                        System.out.println("Waiting for player " + client.getPlayerWithball() + " to pass the ball");
                    }

                    if (counter >= client.currentClient()) {
                        System.out.println("\n" + message);

                        playerList = client.getUniqueIds();
                        String mychar = "";

                        for (int i = 0;i < playerList.length;i++){
                            mychar = mychar + " " + playerList[i];

                        }
                        System.out.println("The current players in the game are players: " + mychar);
                        System.out.println("Waiting for player " + client.getPlayerWithball() + " to pass the ball");
                    }
                    counter++;

                }
            }catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}


