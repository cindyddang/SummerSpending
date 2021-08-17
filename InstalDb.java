/* Duyen Dang
* July 30th, 2019
* This class is for creating a new data and new table. */
package money;

import java.sql.Connection;

/**
 *
 * @author DuyenDang
 */
public class InstalDb
{
    public static void main(String[] args)
    {
        String dbName = "DuyenDang";
        JavaDatabase objDb = new JavaDatabase();
        objDb.createDb(dbName);
        
        //creating a new table
        String newTable = "CREATE TABLE Money (Day varchar(15), Spend varchar(100), Amount int)";
        objDb.createTable(newTable, dbName);
        
    }
}
