/* Duyen Dang
* July 30, 2019
* Class to deal with database*/
package money;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author DuyenDang
 */
public class JavaDatabase
{
    String dbName;
    Connection dbCon;
    ArrayList <ArrayList<String>> data;
    
    public JavaDatabase()
    {
        dbName = "";
        dbCon = null;
        data = null;
    }
    
    public JavaDatabase(String dbName)
    {
        setDbName(dbName);
        setDbCon();
        data = null;
    }
    
    //setters
    public void setDbName(String dbName)
    {
        this.dbName = dbName;
    }
    
    public void setDbCon()
    {
        String connectionURL = "jdbc:derby:" + this.dbName;
        //Find the driver and make connection
        try
        {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            this.dbCon = DriverManager.getConnection(connectionURL);
        }
        catch(ClassNotFoundException cnfe)
        {
            System.out.println("Class for name not found");
            cnfe.printStackTrace(System.err);
        }
        catch (SQLException se)
        {
            System.out.println("SQL connection error");
            se.printStackTrace(System.err);
        }
    }
    
    public void setData (ArrayList<ArrayList<String>> data)
    {
        this.data = data;
    }
    
    //getters
    public String getDbName()
    {
        return this.dbName;
    }
    
    public Connection getDbCon()
    {
        return this.dbCon;
    }
    
    public ArrayList<ArrayList<String>> getData(String tableName, String[] tableHeaders)
    {
        int columnCount = tableHeaders.length;
        Statement s = null;
        ResultSet rs = null;
        String dbQuery = "SELECT * FROM " + tableName;
        this.data = new ArrayList<>();
        
        //read the data
        try
        {
            //send the query and receive daata
            s = this.dbCon.createStatement();
            rs = s.executeQuery(dbQuery);
            
            //read the data using rs and store in ArrayList
            while (rs.next())
            {
                ArrayList<String> row = new ArrayList<>();
                for (int i = 0; i<columnCount; i++)
                {
                    row.add(rs.getString(tableHeaders[i]));
                }
                this.data.add(row);
            }
        }
        catch (SQLException se)
        {
            System.out.println("SQL Error: Not able to get data");
            se.printStackTrace(System.err);
        }
        return this.data;
    }
    
    public void createDb(String newDbName)
    {
        setDbName(newDbName);
        String connectionURL = "jdbc:derby:" + this.dbName + ";create=true";
        this.dbCon = null;
        //Find the driver and make connection
        try
        {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            this.dbCon = DriverManager.getConnection(connectionURL);
            System.out.println("Create a new Database");
        }
        catch (ClassNotFoundException cnfe)
        {
            System.out.println("Class for name not found");
            cnfe.printStackTrace(System.err);
        }
        catch (SQLException se)
        {
        System.out.println("SQL connection error");
        se.printStackTrace(System.err);
        }
    }
    
    public void createTable(String newTable, String dbName)
    {
        System.out.println(newTable);
        setDbName(dbName);
        setDbCon();
        Statement s = null;
        
        try
        {
            s = this.dbCon.createStatement();
            s.execute(newTable);
            System.out.println("New table created!");
        }
        catch (SQLException se)
        {
            System.out.println("SQL error creating table");
            se.printStackTrace(System.err);
        }
    }
    
    public Object[][] to2dArray(ArrayList<ArrayList<String>> data)
    {
        int columnCount = data.get(0).size();
        Object[][] dataList = new Object[data.size()][columnCount];
        
        for (int i=0; i<data.size(); i++)
        {
            ArrayList<String> row = data.get(i);
            for (int j=0; j<columnCount; j++)
            {
                dataList[i][j]= row.get(j);
            }
        }
        
        return dataList;
    }
}

