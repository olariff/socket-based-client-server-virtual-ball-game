package Server;

import java.util.*;

public class Ball {

    public final Map<Integer, Account> accounts = new TreeMap<>();



    public void createAccount(int uniqueId, boolean Withball ){
        Account account = new Account(uniqueId, Withball);
        accounts.put(uniqueId,account);

    }

    public int getPlayerWithBall(){
        int playerWithBall = 0;
        for (Map.Entry<Integer, Account> entry : accounts.entrySet()){
            if (entry.getValue().getWithball() == true){
                playerWithBall = entry.getValue().getUniqueId();
            }


        }
        return playerWithBall;
    }

    public int addPlayer(){
        int newUniqueId = 1;

        synchronized (accounts) {
            for (Map.Entry<Integer, Account> entry : accounts.entrySet()) {
                newUniqueId = Math.max(newUniqueId, entry.getValue().getUniqueId()) + 1;
            }

            System.out.println(newUniqueId);
            createAccount(newUniqueId, false);
        }
        return newUniqueId;
    }

    public int transfer(int from, int to){
        synchronized (accounts) {

            if (from != to) {
                accounts.get(to).setWithBallToTrue();
                accounts.get(from).setBallToFalse();
            }

            System.out.println("Player " + from + " passed the ball to player " + to);

        }
        return to;
    }


    public int giveBall(){
        int uniqueId = 0;
        List<Boolean> temp = new ArrayList<>();

        synchronized (accounts){


            for (Map.Entry<Integer, Account> entry : accounts.entrySet()) {

                temp.add(entry.getValue().getWithball());

            }

            Random rand = new Random();
            if (temp.size() != 0) {
                uniqueId = playerList().get(rand.nextInt(playerList().size()));
            }

                if (temp.contains(true)) {

                    for (Map.Entry<Integer, Account> entry : accounts.entrySet()) {

                        if (entry.getValue().getWithball() == true) {
                            System.out.println("Player " + entry.getValue().getUniqueId() + " is currently with the ball");
                            break;
                        }
                    }

                }
                else {
                    createAccount(uniqueId, true);
                }

        }
        return uniqueId;
    }


    public int removePlayer(int uniqueId){
        synchronized (accounts){
            accounts.remove(uniqueId);
            System.out.println("Player " + uniqueId + " has left the game");

        }
        return uniqueId;
    }

    public List<Integer> playerList(){
        List<Integer> getList = new ArrayList<>();

        for(Account account : accounts.values()){
            getList.add(account.getUniqueId());

        }
        return getList;
    }




}
