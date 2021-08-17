/*Duyen Dang
* August 3rd, 2019
* This welcome page is the main page. 4 buttons displays for user to insert, 
delete, update data.*/
package money;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author DuyenDang
 */
public class WelcomePage extends JFrame implements ActionListener
{
    //declare constants
    private final java.net.URL SUMMER_URL = getClass().getResource("summer.gif");
    private final ImageIcon SUMMER_ICON = new ImageIcon(new ImageIcon(SUMMER_URL).getImage().getScaledInstance(600, 161, Image.SCALE_DEFAULT));
    
    //declaring variable
    private JLabel dashLabel;
    private JLabel imageLabel;
    private JLabel descriptionLabel;
    private JButton viewButton;
    private JButton insertButton;
    private JButton updateButton;
    private JButton deleteButton;
    
    //declaring panel
    private JPanel buttonPanel;
    private JPanel mainPanel;
    
    public WelcomePage()
    {
        //constructing frame
        this.setBounds(400, 300, 700, 300);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        
        //constructing labels
        descriptionLabel = new JLabel("How much did you spend for your summer?");
        descriptionLabel.setFont(new Font("Times New Roman", Font.PLAIN, 35));
        descriptionLabel.setForeground(new Color(209, 134, 98));
        dashLabel = new JLabel("__________________________________________________________");
        dashLabel.setForeground(new Color(219, 238, 240));
        imageLabel = new JLabel(SUMMER_ICON);
        
        //constructing buttons
        viewButton = new JButton("View");
        viewButton.addActionListener(this);
        insertButton = new JButton ("Insert");
        insertButton.addActionListener(this);
        updateButton = new JButton("Update");
        updateButton.addActionListener(this);
        deleteButton = new JButton("Delete");
        deleteButton.addActionListener(this);
        
        //constructing panel
        buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.add(insertButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(viewButton);
        mainPanel = new JPanel();
        mainPanel.setBackground(Color.WHITE);
        mainPanel.add(descriptionLabel);
        mainPanel.add(dashLabel);
        mainPanel.add(imageLabel);
        mainPanel.add(buttonPanel);
        
        //Adding components into frame
        this.add(mainPanel, BorderLayout.CENTER);
        
        //make frame visible
        this.setVisible(true);
    }
    
    @Override
    public void actionPerformed (ActionEvent e)
    {
        String command = e.getActionCommand();
        if (command.equals("View"))
        {
            MoneyTable moneyTabelFrame = new MoneyTable();
        }
        if (command.equals("Insert"))
        {
            InsertPage insertPage = new InsertPage();
        }
        if (command.equals("Delete"))
        {
            DeletePage deletePage = new DeletePage();
        }
        if (command.equals("Update"))
        {
            UpdatePage updatePage = new UpdatePage();
        }
    }
    
    public static void main(String[] args)
    {
        WelcomePage moneyFrame = new WelcomePage();
    }
    
}
