import java.util.ArrayList;

public class LeaderBoard implements java.io.Serializable {
    private ArrayList<Record> leaderBoard = new ArrayList<>();
    int size;

    LeaderBoard(int size) {
        this.size = size;
    }

    public void printLeaderboard() {
        for (int i = 0; i < leaderBoard.size(); i++) {
            System.out.println(leaderBoard.get(i).toString());
        }
    }

    public void addPlace(String user, long time) {
        boolean added = false;
        for (int i = 0; i < leaderBoard.size(); i++) {
            if (leaderBoard.get(i).getTime() > time) {
                leaderBoard.add(i, new Record(user, time));
                added = true;
                break;
            }
        }
        if (!added) {
            leaderBoard.add(new Record(user, time));
        }
    }
}
