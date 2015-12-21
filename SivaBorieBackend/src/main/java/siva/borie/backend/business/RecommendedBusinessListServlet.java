package siva.borie.backend.business;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import siva.borie.backend.database.DatabaseConnection;
import siva.borie.backend.database.DatabaseUtils;

/**
 * Created by Eungjun on 2015-03-09.
 */
public class RecommendedBusinessListServlet extends HttpServlet
{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
            IOException
    {
        JSONArray bizArray = readDB();

        System.out.println("doGet");
        System.out.println(bizArray.toString());

        resp.setContentType("text/json; charset=UTF-8");
        OutputStreamWriter writer = new OutputStreamWriter(resp.getOutputStream(), "utf-8");
        writer.write(bizArray.toString());
        writer.close();
    }

    private JSONArray readDB()
    {
        JSONArray bizArray = new JSONArray();
        Connection conn = DatabaseConnection.getConnection();

        try
        {
            Statement stmt = conn.createStatement();
            String query = "select * from recommend_businesses;";
            ResultSet rs = stmt.executeQuery(query);

            while(rs.next())
            {
                JSONObject bizObject = new JSONObject();

                try
                {
                    bizObject.put(BusinessUtils.BIZ_NAME, rs.getString(DatabaseUtils.BIZ_NAME));
                    bizObject.put(BusinessUtils.BIZ_ADDRESS, rs.getString(DatabaseUtils.BIZ_ADDRESS));
                    bizObject.put(BusinessUtils.OWNER_EMAIL, rs.getString(DatabaseUtils.OWNER_EMAIL));
                }
                catch (JSONException e)
                {
                    e.printStackTrace();
                }

                bizArray.put(bizObject);

            }

        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        finally
        {
            if( null != conn)
            {
                try
                {
                    conn.close();
                } catch (SQLException e)
                {
                    e.printStackTrace();
                }
            }

        }

        return bizArray;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
    }
}
