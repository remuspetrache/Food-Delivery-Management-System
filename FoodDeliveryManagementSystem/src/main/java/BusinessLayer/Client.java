package BusinessLayer;

public class Client extends User implements Comparable<Client> {
    private int clientID;
    private String clientName;

    public Client(String username, String password, Role role, int clientID, String clientName) {
        super(username, password, role);
        this.clientID = clientID;
        this.clientName = clientName;
    }

    public int getClientID() {
        return clientID;
    }

    public void setClientID(int clientID) {
        this.clientID = clientID;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    @Override
    public String toString() {
        return "Client name: " + clientName;
    }

    @Override
    public int compareTo(Client o) {
        return Integer.compare(this.clientID, o.clientID);
    }
}
