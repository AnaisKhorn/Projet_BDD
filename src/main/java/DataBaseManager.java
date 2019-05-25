import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.schemabuilder.Create;
import com.datastax.driver.core.utils.UUIDs;
import org.cassandraunit.dataset.xml.Keyspace;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DataBaseManager {

    private Session session;
    private String keySpace;

    public DataBaseManager(String address) {
        ConnectorBase.connect(address, null);
        session = ConnectorBase.getSession();
    }


    public void setKeySpace(String keySpace) {
        this.keySpace = keySpace;
    }

    /***
     *  create a keyspace if not existing
     * @param keyspaceName name of the keyspace
     * @param replicationStrategy
     * @param replicationFactor
     * These  two parameters determine the number of replicas and how the replicas will be distributed across the ring, respectively.
     * With replication Cassandra ensures reliability and fault tolerance by storing copies of data in multiple nodes.
     */
    public ResultSet createKeyspace(String keyspaceName, String replicationStrategy, int replicationFactor) {
        keySpace = keyspaceName;
        StringBuilder sb =
                new StringBuilder("CREATE KEYSPACE IF NOT EXISTS ")
                        .append(keyspaceName).append(" WITH replication = {")
                        .append("'class':'").append(replicationStrategy)
                        .append("','replication_factor':").append(replicationFactor)
                        .append("};");
        String query = sb.toString();
        return executeQuery(query);

    }

    /***
     *  Creation of table from keyspace
     * @param tableName String KeySpace.TableName
     * @param primaryKey String Title+Type
     * @param args String[] Title+Type
     * @return ResultSet request of result
     */
    public ResultSet createTable(String tableName, String primaryKey, String... args) {
        StringBuilder sb = new StringBuilder("CREATE TABLE IF NOT EXISTS ")
                .append(keySpace + "." + tableName).append("(")
                .append(primaryKey + " PRIMARY KEY, ");
        for (String s : args) {
            sb.append(s + ", ");
        }
        sb = sb.replace(sb.lastIndexOf(","), sb.length(), ");");
        String query = sb.toString();
        return executeQuery(query);
    }


    public ResultSet alterTable(String tableName, String columnName, String columnType) {
        StringBuilder sb = new StringBuilder("ALTER TABLE ")
                .append(keySpace + "." + tableName).append(" ADD ")
                .append(columnName).append(" ")
                .append(columnType).append(";");

        String query = sb.toString();
        return executeQuery(query);
    }

    public ResultSet insertData(String tableName, List<String> into, List<String> values) {
        StringBuilder sb = new StringBuilder("INSERT INTO ")
                .append(keySpace + "." + tableName)
                .append("(");
        for (String s : into) {
            sb.append(s + ",");
        }
        sb = sb.replace(sb.lastIndexOf(","), sb.length(), ") VALUES (");
        for (String s : values) {
            sb.append(s + ",");
        }
        sb = sb.replace(sb.lastIndexOf(","), sb.length(), ");");
        String query = sb.toString();
        return executeQuery(query);
    }


    public ResultSet executeQuery(String query) {
        try {
            return session.execute(query);
        } catch (Exception e) {
            ConnectorBase.close();
            return null;
        }


    }

    public static void main(String[] args) {
        DataBaseManager dbm = new DataBaseManager("127.0.0.1");
        ResultSet result;
        dbm.setKeySpace("Test_Librairy");
        //region Testing creation of keyspace
       /* ResultSet result = dbm.createKeyspace("Test_Librairy", "SimpleStrategy", 1);
        result = dbm.executeQuery("SELECT * FROM system_schema.keyspaces;");
        List<String> matchedKeyspaces = result.all()
                .stream()
                .filter(r -> r.getString(0).equals("Test_Librairy".toLowerCase()))
                .map(r -> r.getString(0))
                .collect(Collectors.toList());
        System.out.println("size : " + matchedKeyspaces.size());
        System.out.println("name :" + matchedKeyspaces.get(0));*/
        //endregion

        //region Create Table
     /*   dbm.createTable("books", "id uuid", "title text", "auteur text", "pages double");
        result = dbm.executeQuery(
                "SELECT * FROM " + "Test_Librairy" + ".books;");

        List<String> columnNames =
                result.getColumnDefinitions().asList().stream()
                        .map(cl -> cl.getName())
                        .collect(Collectors.toList());

        System.out.println("number of column : " + columnNames.size());
        for (String s : columnNames) {
            System.out.println(s);
        }*/
        //endregion


        //region AlterTable
        /*dbm.alterTable("books","publisher","text");
        result = dbm.executeQuery(
                "SELECT * FROM " + "Test_Librairy" + ".books;");

        List<String> columnNames =
                result.getColumnDefinitions().asList().stream()
                        .map(cl -> cl.getName())
                        .collect(Collectors.toList());

        System.out.println("number of column : " + columnNames.size());
        for (String s : columnNames) {
            System.out.println(s);
        }*/
        //endregion

        /*List<String> clefs =new ArrayList<>();
        clefs.add("id");
        clefs.add("title");
        clefs.add("auteur");
        clefs.add("pages");

        List<String> values =new ArrayList<>();
        values.add(UUIDs.timeBased().toString());
        values.add("'java book'");
        values.add("'Michel'");
        values.add("1000");
        dbm.insertData("books",clefs,values);*/

        result = dbm.executeQuery(
                "SELECT * FROM " + "Test_Librairy" + ".books;");

        List<String> columnId = new ArrayList<String>();
        result.forEach(row -> {
            columnId.add(row.getUUID("id") + "/" +
                    row.getString("title") + "/" +
                    row.getString("auteur") + "/" +
                    row.getDouble("pages") + "/");
        });

        System.out.println("number of column : " + columnId.size());
        for (String s : columnId) {
            System.out.println(s);
        }


        ConnectorBase.close();

    }

}
