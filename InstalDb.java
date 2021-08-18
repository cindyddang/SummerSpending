package money;

import java.sql.Connection;

public class InstalDb
{
    public static void main(String[] args)
    {
        String dbName = "name";
        JavaDatabase objDb = new JavaDatabase();
        objDb.createDb(dbName);
        
        //creating a new table
        String newTable = "CREATE TABLE Money (Day varchar(15), Spend varchar(100), Amount int)";
        objDb.createTable(newTable, dbName);
        
    }
}
