/* Duyen Dang
* July 28, 2019
* This frame displays text fields for user to insert data, buttons to cancel
and execute inserting. */
package money;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author DuyenDang
 */
public class InsertPage extends JFrame implements ActionListener
{
    //JLabel
    private JLabel headerLabel;
    private JLabel dateLabel;
    private JLabel spendLabel;
    private JLabel amountLabel;
    
    //JTextFields
    private JTextField dateField;
    private JTextField spendField;
    private JTextField amountField;
    
    //JPanel
    private JPanel topPanel;
    private JPanel centerPanel;
    private JPanel bottomPanel;
    
    //JButtons
    private JButton addButton;
    private JButton cancelButton;
    
    public InsertPage()
    {
        //Constructing frame
        super("INSERT");
        this.setBounds(950, 50, 500, 210);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLayout(new BorderLayout());
        
        //Constructing labels
        headerLabel = new JLabel("Insert new data into storage");
        headerLabel.setFont(new Font("Arial", Font.BOLD, 20));
        headerLabel.setForeground(Color.WHITE);
        dateLabel = new JLabel("Date: ");
        spendLabel = new JLabel("Spend for: ");
        amountLabel = new JLabel("Amount($): ");
        
        //Constructing text fields
        dateField = new JTextField(35);
        spendField = new JTextField(32);
        amountField = new JTextField(32);
        
        //Constructing buttons
        addButton = new JButton("INSERT");
        addButton.addActionListener(this);
        cancelButton = new JButton("CANCEL");
        cancelButton.addActionListener(this);
        
        //Constructing panel
        topPanel = new JPanel();
        topPanel.add(headerLabel);
        topPanel.setBackground(Color.BLACK);
        centerPanel = new JPanel();
        centerPanel.add(dateLabel);
        centerPanel.add(dateField);
        centerPanel.add(spendLabel);
        centerPanel.add(spendField);
        centerPanel.add(amountLabel);
        centerPanel.add(amountField);
        centerPanel.setBackground(Color.WHITE);
        bottomPanel = new JPanel();
        bottomPanel.add(addButton);
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
        if(command.equals("INSERT"))
        {
            //get info from user
            try
            {
                String dateVar = dateField.getText();
                String spendVar = spendField.getText();
                int amountVar = Integer.parseInt(amountField.getText());

                Connection myDbCon = null;
                JavaDatabase objDb = new JavaDatabase("DuyenDang");
                myDbCon = objDb.getDbCon();
                String dbQuery1 = "INSERT INTO Money VALUES (?,?,?)";

                try
                {
                    PreparedStatement ps = myDbCon.prepareStatement(dbQuery1);
                    ps.setString(1, dateVar);
                    ps.setString(2, spendVar);
                    ps.setInt(3, amountVar);
                    ps.executeUpdate();
                    String noti = "Data inserted successfully";
                    Notification notiPage = new Notification(noti);
                }

                catch (SQLException se)
                {
                    System.out.println("Error inserting data");
                    se.printStackTrace(System.err);
                }
            }
            
            catch(NumberFormatException nfe)
            {
                WarningPage warningPage = new WarningPage();
            }
            
        }
        if(command.equals("CANCEL"))
        {
            this.dispose();
        }
    }
//    
//    public static void main(String[] args)
//    {
//        InsertPage insertPage = new InsertPage();
//    }
}