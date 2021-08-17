/* Duyen Dang
* August 19, 2019
* This frame displays warning for Number Format Exception.*/
package money;

import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

/**
 *
 * @author DuyenDang
 */
public class WarningPage extends JFrame
{
    //JLabels
    private JLabel warningLabel;
    
    public WarningPage()
    {
        //Constructing frame
        super("WARNING");
        this.setBounds(500, 400, 400, 100);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        
        //Constructing label
        warningLabel = new JLabel("Please put number only in Amount field(s)", SwingConstants.CENTER);
        warningLabel.setFont(new Font("Airal", Font.PLAIN, 15));
        
        //Adding components into frame
        this.add(warningLabel);
        
        //Make frame visible
        this.setVisible(true);
    }
    
//    public static void main(String[] arg)
//    {
//        WarningPage warningPage = new WarningPage();
//        
//    }
}
