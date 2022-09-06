package Client;

import java.io.*;
import java.net.Socket;
import java.util.Arrays;
import java.util.Map;
import java.util.Scanner;

public class Client implements AutoCloseable {
    final int port = 8888;

    private final Scanner reader;
    private final PrintWriter writer;
    private int uniqueId;

    public Client() throws Exception {

        Socket socket = new Socket("localhost", port);
        reader = new Scanner(socket.getInputStream());


        writer = new PrintWriter(socket.getOutputStream(), true);

        System.out.println(reader.nextLine());

    }

    public String get(int i) {
        writer.println("list " + i);
        return reader.nextLine();
    }

    public int currentClient(){
        //return uniqueId;
        writer.println("currentclient");

        String line = reader.nextLine();

        try {
            //System.out.println("You are player " + Integer.parseInt(line));
            return Integer.parseInt(line);
        }
        catch (Exception e){
            return 0;
        }
    }

    public int[] getUniqueIds() {
        // Sending command
        writer.println("players");

        // Reading the number of accounts
        String line = reader.nextLine();

        try {
            int numberOfPlayers = Integer.parseInt(line);
            System.out.println("There are currently " + numberOfPlayers + " players in the game");
            // Reading the account numbers
            int[] accounts = new int[numberOfPlayers];
            for (int i = 0; i < numberOfPlayers; i++) {
                line = reader.nextLine();
                accounts[i] = Integer.parseInt(line);
            }
            //System.out.println(Arrays.toString(accounts));
            return accounts;
        }
        catch (Exception e){
            return null;
        }
    }

    public int getPlayerWithball(){
        writer.println("playerwithball");
        String line = reader.nextLine();
        try{
            return Integer.parseInt(line);
        }
        catch (Exception e){
            return 0;
        }
        //return Integer.parseInt(line);
    }

    public void transfer(int toPlayer) {
        writer.println("transfer " + toPlayer);
    }

    public void clearList(){
        writer.println("clearlist1");
    }

    public String getListElement(){
        writer.println("getlistelement1");
        return reader.nextLine();
    }

    public int getListSize(){
        writer.println("getlistsize1");
        String line = reader.nextLine();
        return Integer.parseInt(line);
    }

    public void clearList2(){
        writer.println("clearlist2");
    }


    @Override
    public void close() throws Exception {
        reader.close();
        writer.close();
    }
}