/* Duyen Dang
* August 13th, 2019
* This frame displays textfields for user to enter data 
which they want to change.*/
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
public class UpdatePage extends JFrame implements ActionListener
{
    //JLabels
    private JLabel headerLabel;
    private JLabel dashLabel;
    
    //JPanels
    private JPanel topPanel;
    private JPanel centerPanel;
    private JPanel bottomPanel;
    
    //JTextFields
    private JTextField dateField;
    private JTextField spendField;
    private JTextField amountField;
    private JTextField dateConditionField;
    private JTextField spendConditionField;
    private JTextField amountConditionField;
    
    //JCheckBox
    private JRadioButton dateButton;
    private JRadioButton spendButton;
    private JRadioButton amountButton;
    private ButtonGroup setGroup;
    private JRadioButton dateConButton;
    private JRadioButton spendConButton;
    private JRadioButton amountConButton;
    private ButtonGroup conditionGroup;
    
    //JButton
    private JButton updateButton;
    private JButton cancelButton;
    
    public UpdatePage()
    {
        //Constructing frame
        super("UPDATE");
        this.setBounds(950, 50, 400, 320);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLayout(new BorderLayout());
        
        //Constructing labels
        headerLabel = new JLabel("Update data from the list:");
        headerLabel.setForeground(Color.WHITE);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 20));
        dashLabel = new JLabel("_______________________________________________");
        
        //Constructing button
        updateButton = new JButton("UPDATE");
        updateButton.addActionListener(this);
        cancelButton = new JButton("CANCEL");
        cancelButton.addActionListener(this);
        
        
        //Constructing text fields
        dateField = new JTextField(15);
        spendField = new JTextField(15);
        amountField = new JTextField(15);
        dateConditionField = new JTextField(15);
        spendConditionField = new JTextField(15);
        amountConditionField = new JTextField(15);
        
        //Constructing radio button
        dateButton = new JRadioButton("   Set date =  ");
        spendButton = new JRadioButton("  Set spend =  ");
        amountButton = new JRadioButton(" Set amount =  ");
        dateConButton  = new JRadioButton(" Where date  = ");
        spendConButton  = new JRadioButton(" Where spend  = ");
        amountConButton  = new JRadioButton(" Where amount  = ");
        
        //Constructing button group
        setGroup = new ButtonGroup();
        setGroup.add(dateButton);
        setGroup.add(spendButton);
        setGroup.add(amountButton);
        conditionGroup = new ButtonGroup();
        conditionGroup.add(dateConButton);
        conditionGroup.add(spendConButton);
        conditionGroup.add(amountConButton);
        
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
        centerPanel.add(dashLabel);
        centerPanel.add(dateConButton);
        centerPanel.add(dateConditionField);
        centerPanel.add(spendConButton);
        centerPanel.add(spendConditionField);
        centerPanel.add(amountConButton);
        centerPanel.add(amountConditionField);
        bottomPanel = new JPanel();
        bottomPanel.add(updateButton);
        bottomPanel.add(cancelButton);
        
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
         
         if(command.equals("CANCEL"))
         {
             this.dispose();
         }
         if(command.equals("UPDATE"))
         {
            Connection myDbCon = null;
            JavaDatabase objDb = new JavaDatabase("DuyenDang");
            myDbCon = objDb.getDbCon();
            String dbQuery = null;
            
            try
            {
             if(dateButton.isSelected())
             {
                 String dateVar = dateField.getText();
                 
                 dbQuery = "UPDATE Money SET Day = '" + dateVar + "'";
             }
             if(spendButton.isSelected())
             {
                 String spendVar = spendField.getText();
                 
                 dbQuery = "UPDATE Money SET Spend = '" + spendVar + "'";
             }
             if(amountButton.isSelected())
             {
                 int amountVar = Integer.parseInt(amountField.getText());
                 
                 dbQuery = "UPDATE Money SET Amount = " + amountVar + " ";
             }
             if(dateConButton.isSelected())
             {
                 String dateVar = dateConditionField.getText();
                 
                 dbQuery = dbQuery + "WHERE Day = '" + dateVar + "'";
             }
             if(spendConButton.isSelected())
             {
                 String spendVar = spendConditionField.getText();
                 
                 dbQuery = dbQuery + "WHERE Spend = '" + spendVar + "'";
             }
             if(amountConButton.isSelected())
             {
                 int amountVar = Integer.parseInt(amountConditionField.getText());
                 
                 dbQuery = dbQuery + "WHERE Amount = " + amountVar + " ";
             }
            }
            
            catch(NumberFormatException nfe)
            {
                WarningPage warningPage = new WarningPage();
            }
             
             try
             {
                 PreparedStatement ps = myDbCon.prepareStatement(dbQuery);
                 ps.executeUpdate();
                 String noti = "Data updated successfully";
                 Notification notiPage = new Notification(noti);
             }
             
             catch(SQLException se)
             {
                 System.out.println("Error updating data");
                 System.out.println(dbQuery);
                 se.printStackTrace(System.err);
             }
        }
    }
    
//    public static void main(String[] args)
//    {
//        UpdatePage updatePage = new UpdatePage();
//    }
}
