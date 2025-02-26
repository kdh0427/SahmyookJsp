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

public class DBCPInit2 extends HttpServlet {
	@Override
    public void init() throws ServletException {
        loadJDBCDriver();
        initConnectionPool();
    }

    private void loadJDBCDriver() {
    	String driverClass = getInitParameter("jdbcdriver");
        try {
            // Class.forName("com.mysql.cj.jdbc.Driver");
            Class.forName(driverClass);
        } catch (ClassNotFoundException ex) {
            throw new RuntimeException("Fail to load JDBC Driver", ex);
        }
    }

    private void initConnectionPool() {
        try {
        	/*
            String jdbcURL = "jdbc:mysql://localhost:3306/guestbook?" +
            		"useUnicode=true&characterEncoding=utf8";
            String username = "jspexam";
            String pw = "1234";
            */
            
            String jdbcUrl = getInitParameter("jdbcUrl");
            String username = getInitParameter("dbUser");
            String pw = getInitParameter("dbPass");

            ConnectionFactory connFactory = 
                    new DriverManagerConnectionFactory(jdbcUrl, username, pw);

            PoolableConnectionFactory poolableConnFactory = 
                    new PoolableConnectionFactory(connFactory, null);
            poolableConnFactory.setValidationQuery("SELECT 1");

            GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
            poolConfig.setTimeBetweenEvictionRunsMillis(1000L * 60L * 5L);
            poolConfig.setTestWhileIdle(true);
            poolConfig.setMinIdle(4);
            poolConfig.setMaxTotal(50);

            GenericObjectPool<PoolableConnection> connectionPool = 
                    new GenericObjectPool<>(poolableConnFactory, poolConfig);
            poolableConnFactory.setPool(connectionPool);

            Class.forName("org.apache.commons.dbcp2.PoolingDriver");
            
            // 14장
            /*
            PoolingDriver driver = (PoolingDriver) 
                    DriverManager.getDriver("jdbc:apache:commons:dbcp:");
            driver.registerPool("chap14", connectionPool);
            */
            
            // 15장
            PoolingDriver driver = (PoolingDriver)
                    DriverManager.getDriver("jdbc:apache:commons:dbcp:");
            String poolName = getInitParameter("poolName");
            // driver.registerPool("guestbook", connectionPool);
            driver.registerPool(poolName, connectionPool);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
