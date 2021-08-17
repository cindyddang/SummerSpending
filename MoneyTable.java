/*Duyen Dang
* August 7th, 2019
* This page displays data which is already inserted.
*/
package money;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.sql.Connection;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

/**
 *
 * @author DuyenDang
 */
public class MoneyTable extends JFrame
{
    //labels
    private JLabel moneyLabel;
    
    //panels
    private JPanel northPanel;
    
    //table
    private final String[] TABLE_HEADER = {"Day", "Spend for", "Amount($)"};
    private Object[][] data;
    private JTable moneyTable;
    private JScrollPane moneyPane;
    private JTableHeader moneyHeader;
    private TableColumn moneyColumn;
    
    public MoneyTable()
    {
        //Constructing frame
        super("Table");
        this.setBounds(50, 50, 900, 600);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLayout(new BorderLayout());
        
        //Constructing label
        moneyLabel = new JLabel("Money spent for summer", SwingConstants.CENTER);
        moneyLabel.setForeground(Color.WHITE);
        moneyLabel.setFont(new Font("Arial", Font.BOLD, 21));
        
        //Connecting to database
        String[] columnName = {"Day", "Spend", "Amount"};
        Connection myDbConn = null;
        JavaDatabase objDb = new JavaDatabase("DuyenDang");
        myDbConn = objDb.getDbCon();
        
        ArrayList<ArrayList<String>> myData = objDb.getData(("Money"),columnName);
        
        for(int k=0; k<myData.size(); k++)
        {
            myData.get(k);
        }
        
        Object[][] dataArray = objDb.to2dArray(myData);
        
        //Constructing table
        this.data = dataArray;
        moneyTable = new JTable(this.data, TABLE_HEADER);
        
        //Formatting table
        moneyTable.setGridColor(Color.BLACK);
        
        //Formatting header
        moneyHeader = moneyTable.getTableHeader();
        moneyHeader.setBackground(Color.WHITE);
        moneyHeader.setFont(new Font("Arial", Font.BOLD, 16));
        
        //Formatting table
        moneyColumn = moneyTable.getColumnModel().getColumn(0);
        moneyColumn.setPreferredWidth(200);
        moneyColumn = moneyTable.getColumnModel().getColumn(1);
        moneyColumn.setPreferredWidth(500);
        moneyColumn = moneyTable.getColumnModel().getColumn(2);
        moneyColumn.setPreferredWidth(200);
        
        //Construcing pane
        moneyPane = new JScrollPane();
        moneyPane.getViewport().add(moneyTable);
        moneyTable.setVisible(true);
        
        //Constructing panels
        northPanel = new JPanel();
        northPanel.setBackground(Color.BLACK);
        northPanel.add(moneyLabel);
        
        //Adding components into frame
        this.add(northPanel, BorderLayout.NORTH);
        this.add(moneyPane, BorderLayout.CENTER);
        
        //Make frame visible
        this.setVisible(true);
    }
        
    
    public static void main(String args[])
    { 
        MoneyTable moneyTable = new MoneyTable();
    }
}
