public class Record implements java.io.Serializable {
    String user;
    long time;

    Record(String user, long time) {
        this.user = user;
        this.time = time;
    }

    public String getUser() {
        return user;
    }

    public long getTime() {
        return time;
    }

    public String toString() {
        return "Username: " + user + " Time: " + time;
    }
}
