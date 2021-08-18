/* This frame displays text fields for user to enter data that they want to 
delete. Buttons to cancel and execute deleting.  */
package money;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

/**
 *
 * @author DuyenDang
 */
public class DeletePage extends JFrame implements ActionListener
{
    //JLabels
    private JLabel headerLabel;
    
    //JPanels
    private JPanel topPanel;
    private JPanel centerPanel;
    private JPanel bottomPanel;
    
    //JTextFields
    private JTextField dateField;
    private JTextField spendField;
    private JTextField amountField;
    
    //JRadioButton
    private JRadioButton dateButton;
    private JRadioButton spendButton;
    private JRadioButton amountButton;
    private ButtonGroup conditionGroup;
    
    //JButton
    private JButton deleteButton;
    private JButton cancelButton;
    
    public DeletePage()
    {
        //Constructing frame
        super("DELETE");
        this.setBounds(950, 50, 400, 210);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLayout(new BorderLayout());
        
        //Constructing labels
        headerLabel = new JLabel("Delete data from the list, where:");
        headerLabel.setForeground(Color.WHITE);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 20));
        
        //Constructing button
        deleteButton = new JButton("DELETE");
        deleteButton.addActionListener(this);
        cancelButton = new JButton("CANCEL");
        cancelButton.addActionListener(this);
        
        //Constructing radio button
        dateButton = new JRadioButton("Date (mm/dd/yyyy) = ");
        spendButton = new JRadioButton("    Spend = ");
        amountButton = new JRadioButton("   Amount = ");
        
        //Constructing button group
        conditionGroup = new ButtonGroup();
        conditionGroup.add(dateButton);
        conditionGroup.add(spendButton);
        conditionGroup.add(amountButton);
        
        //Constructing text fields
        dateField = new JTextField(17);
        spendField = new JTextField(20);
        amountField = new JTextField(20);
        
        //Constructing panels
        topPanel = new JPanel();
        topPanel.add(headerLabel);
        topPanel.setBackground(Color.BLACK);
        centerPanel = new JPanel();
        centerPanel.add(dateButton);
        centerPanel.add(dateField);
        centerPanel.add(spendButton);
        centerPanel.add(spendField);
        centerPanel.add(amountButton);
        centerPanel.add(amountField);
        centerPanel.setBackground(Color.WHITE);
        bottomPanel = new JPanel();
        bottomPanel.add(deleteButton);
        bottomPanel.add(cancelButton);
        bottomPanel.setBackground(Color.BLACK);
        
        //Adding components into frame
        this.add(topPanel, BorderLayout.NORTH);
        this.add(centerPanel, BorderLayout.CENTER);
        this.add(bottomPanel, BorderLayout.SOUTH);
        
        //Making frame visible
        this.setVisible(true);
        
    }
    
    @Override
    public void actionPerformed(ActionEvent e)
    {
        String command = e.getActionCommand();
        if(command.equals("DELETE"))
        {
            try
            {
                if(dateButton.isSelected())
                {
                    String dateVar = dateField.getText();
                    Connection myDbCon = null;
                    JavaDatabase objDb = new JavaDatabase("DuyenDang");

                    myDbCon = objDb.getDbCon();
                    String dbQuery1 = "DELETE FROM Money WHERE Day = '" + dateVar + "'";

                    try
                    {
                        PreparedStatement ps = myDbCon.prepareStatement(dbQuery1);
                        ps.executeUpdate();
                        String noti = "Data deleted successfully";
                        Notification notiPage = new Notification(noti);
                    }

                    catch (SQLException se)
                    {
                        System.out.println("Error deleting data");
                        se.printStackTrace(System.err);
                    }
                }
                if(spendButton.isSelected())
                {
                    String spendVar = spendField.getText();
                    Connection myDbCon = null;
                    JavaDatabase objDb = new JavaDatabase("DuyenDang");

                    myDbCon = objDb.getDbCon();
                    String dbQuery1 = "DELETE FROM Money WHERE Spend = '" + spendVar + "'";

                    try
                    {
                        PreparedStatement ps = myDbCon.prepareStatement(dbQuery1);
                        ps.executeUpdate();
                        String noti = "Data deleted successfully";
                        Notification notiPage = new Notification(noti);
                    }

                    catch (SQLException se)
                    {
                        System.out.println("Error deleting data");
                        se.printStackTrace(System.err);
                    }
                }
                if(amountButton.isSelected())
                {
                    int amountVar = Integer.parseInt(amountField.getText());
                    Connection myDbCon = null;
                    JavaDatabase objDb = new JavaDatabase("DuyenDang");
                    myDbCon = objDb.getDbCon();
                    String dbQuery1 = "DELETE FROM Money WHERE Amount = " + amountVar + " ";

                    try
                    {
                        PreparedStatement ps = myDbCon.prepareStatement(dbQuery1);
                        ps.executeUpdate();
                        String noti = "Data deleted successfully!";
                        Notification notiPage = new Notification(noti);
                    }

                    catch (SQLException se)
                    {
                        System.out.println("Error deleting data");
                        se.printStackTrace(System.err);
                    }
                }
            }
            
            catch (NumberFormatException nfe)
            {
                WarningPage warningPage = new WarningPage();
            }
        }
            
        if(command.equals("CANCEL"))
        {
            this.dispose();
        }
    
    }
    
//    public static void main(String[] args)
//    {
//          DeletePage deletePage = new DeletePage();
//    }
}
