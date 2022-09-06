package Server;

import java.util.List;

public class Account {
    private final int uniqueId;
    private boolean Withball;

    public Account(int uniqueId, boolean Withball) {

        this.uniqueId = uniqueId;
        this.Withball = Withball;
    }

    public int getUniqueId(){
        return uniqueId;
    }

    public void setWithBallToTrue(){
        Withball = true;
    }

    public void setBallToFalse(){
        Withball = false;
    }

    public boolean getWithball(){
        return Withball;
    }


}
