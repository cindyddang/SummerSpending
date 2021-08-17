/* Duyen Dang
* August 12, 2019
* This frame displays notification when system inserted, deleted, 
updated data successfully.*/
package money;

import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

/**
 *
 * @author DuyenDang
 */
public class Notification extends JFrame
{
    private JLabel notiLabel;
    
    
    public Notification(String notification)
    {
        super("NOTIFICATION");
        this.setBounds(500, 400, 400, 100);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        
        //Constructing label
        notiLabel = new JLabel(notification, SwingConstants.CENTER);
        notiLabel.setFont(new Font("Airal", Font.BOLD, 20));
        
        //Adding opponents into frame
        this.add(notiLabel);
        
        //Make this frame visible
        this.setVisible(true);
    }
    
//    public static void main(String[] args)
//    {
//        Notification notiPage = new Notification("Noti goes here");
//    }
}
