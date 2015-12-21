package siva.borie.backend.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.http.HttpServlet;

/**
 * Created by Eungjun on 2015-03-09.
 */
public class DatabaseConnection extends HttpServlet
{
    private static final String DB_URL = "jdbc:mysql://localhost:3306/borie_backend";
    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DB_POOL_DRIVER = "org.apache.commons.dbcp.PoolingDriver";
    private static final String ID = "root";
    private static final String PASS = "siva0204";


    public static Connection getConnection()
    {
        Connection conn = null;

        try
        {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, ID, PASS);

        } catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        } catch (SQLException e)
        {
            e.printStackTrace();
        }


        return conn;

    }

}
