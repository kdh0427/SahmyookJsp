package jdbc;

import java.sql.DriverManager;

import org.apache.commons.dbcp2.ConnectionFactory;
import org.apache.commons.dbcp2.DriverManagerConnectionFactory;
import org.apache.commons.dbcp2.PoolableConnection;
import org.apache.commons.dbcp2.PoolableConnectionFactory;
import org.apache.commons.dbcp2.PoolingDriver;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;

public class DBCPInit extends HttpServlet {
	@Override
    public void init() throws ServletException {
        loadJDBCDriver();
        initConnectionPool();
    }

    private void loadJDBCDriver() {
        try {
            Class.forName("oracle.jdbc.OracleDriver");
        } catch (ClassNotFoundException ex) {
            throw new RuntimeException("Fail to load JDBC Driver", ex);
        }
    }

    private void initConnectionPool() {
        try {
            String jdbcURL = "jdbc:oracle:thin:@localhost:1521:orcl";
            String username = "c##jspexam";
            String pw = "qwer1234";

            ConnectionFactory connFactory = 
                    new DriverManagerConnectionFactory(jdbcURL, username, pw);

            PoolableConnectionFactory poolableConnFactory = 
                    new PoolableConnectionFactory(connFactory, null);
            poolableConnFactory.setValidationQuery("SELECT 1 FROM DUAL");

            GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
            poolConfig.setTimeBetweenEvictionRunsMillis(1000L * 60L * 5L);
            poolConfig.setTestWhileIdle(true);
            poolConfig.setMinIdle(4);
            poolConfig.setMaxTotal(50);

            GenericObjectPool<PoolableConnection> connectionPool = 
                    new GenericObjectPool<>(poolableConnFactory, poolConfig);
            poolableConnFactory.setPool(connectionPool);

            Class.forName("org.apache.commons.dbcp2.PoolingDriver");
            PoolingDriver driver = (PoolingDriver) 
                    DriverManager.getDriver("jdbc:apache:commons:dbcp:");
            driver.registerPool("chap14", connectionPool);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
