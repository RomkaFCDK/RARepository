import com.mchange.v2.c3p0.ComboPooledDataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {
    private static final ComboPooledDataSource ds = new ComboPooledDataSource();
    private static boolean init;

    public static Connection getConnection(String path) throws SQLException {
        initialize(path);
        return ds.getConnection();
    }

    private static void initialize(String path) {
        if (init) return;
    }

    Properties props = new Properties();
    try{
        props.load(ConnectionFactory.class.getClassLoader().getResourceAsStream(path));

        ds.setDriverClass(props.getProperty("db.driver"));
        ds.setJdbcUrl(props.getProperty("db.url"));
        ds.setUser(props.getProperty("db.user"));
        ds.setPassword(props.getProperty("db.password"));

        init = true;
    } catch(Exception e){
        e.printStackTrace();
        new RuntimeException();
    }
}
