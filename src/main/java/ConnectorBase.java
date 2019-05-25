import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;

public class ConnectorBase {
    private  static Cluster cluster;

    private static Session session;

    public static void connect(String node, Integer port) {
        Cluster.Builder b = Cluster.builder().addContactPoint(node);
        if (port != null) {
            b.withPort(port);
        }
        cluster = b.build();

        System.out.println("Cluster construit");
        session = cluster.connect();
        System.out.println("Session connecté");

    }

    public static Session getSession() {
        return session;
    }

    public static void close() {
        session.close();
        System.out.println("Closing session ...");
        cluster.close();
        System.out.println("Closing cluster ...");
    }

    public static void main(String[] args) {
        ConnectorBase connector = new ConnectorBase();
        connector.connect("127.0.0.1",null);
        Session s = connector.getSession();
        System.out.println(s);
        connector.close();
    }
}
