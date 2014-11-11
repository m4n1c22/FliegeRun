
import java.awt.BorderLayout;
import java.awt.Panel;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.GroupLayout;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;
import java.awt.event.MouseMotionAdapter;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowStateListener;
import java.awt.event.WindowEvent;

import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTable;
public class Client {

	private static Fliege F;
	private static JFrame f;
	private static JTextArea textArea;
	private static JLabel FliegeImg;
	private static JPanel panel;
	private static JPanel panel_1;
	private static GroupLayout groupLayout;
	private static JLabel playerNameLabel;
	private static JButton connectButton;
	private static JTextField playerNameTextField;
	//private static int minX,maxX,minY,maxY;
	
	
/*	public static int randInt(int min, int max) {

	    // NOTE: Usually this should be a field rather than a method
	    // variable so that it is not re-seeded every call.
	    Random rand = new Random();

	    // nextInt is normally exclusive of the top value,
	    // so add 1 to make it inclusive
	    int randomNum = rand.nextInt((max - min) + 1) + min;

	    return randomNum;
	}
*/

	//Call this method when you get the callback from server for new position of Fliege...
    public static void setPositionOfFliege() {
    	
    	int fliege_x,fliege_y;
    	fliege_x = F.getPositionFlyX();
    	fliege_y = F.getPositionFlyY();
    	FliegeImg.setLocation(fliege_x,fliege_y);
    }
	
	//Called from the mouseClickedEvent and mouseDraggedEvent Methods....
	public static void callFliegeHunted() {
		
		//server call for fliegeHunted()
		System.out.println("FliegeHunted.....");
	}
	
/*    public static void setPositionForMouseEventWithLabel(MouseEvent e,JLabel L) {

		
		//Call the serverMethod for fliegeHunted
		
		L.setLocation(label_x, label_y);
    }
*/
	    
    //Call this method on response to the login success....
    public static void initialiseUIForPlayer() {
    	
	      //f = new JFrame("");
    	  
    	//Hiding the button, Textfield, label in login page
    	playerNameLabel.setVisible(false);
    	playerNameTextField.setVisible(false);
    	connectButton.setVisible(false);
    	
    	
    	
	      F = new Fliege();
	      
/*	      f.setSize(521, 387);
	      f.setLocation(300,200);
*/	      
	      
	      //panel = new JPanel();
	      panel.setBackground(Color.WHITE);
	      
	      panel_1 = new JPanel();
	      panel_1.setBackground(Color.WHITE);
	      //groupLayout = new GroupLayout(f.getContentPane());
	      groupLayout.setHorizontalGroup(
	      	groupLayout.createParallelGroup(Alignment.TRAILING)
	      		.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
	      			.addContainerGap()
	      			.addComponent(panel, GroupLayout.PREFERRED_SIZE, 1108, GroupLayout.PREFERRED_SIZE)
	      			.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	      			.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 228, GroupLayout.PREFERRED_SIZE)
	      			.addContainerGap())
	      );
	      groupLayout.setVerticalGroup(
	      	groupLayout.createParallelGroup(Alignment.TRAILING)
	      		.addGroup(groupLayout.createSequentialGroup()
	      			.addContainerGap()
	      			.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
	      				.addComponent(panel_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 683, Short.MAX_VALUE)
	      				.addComponent(panel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 683, Short.MAX_VALUE))
	      			.addContainerGap())
	      );
	      
	      textArea = new JTextArea();
	      textArea.setEditable(false);
	      textArea.setLineWrap(true);
	      textArea.setRows(37);
	      panel_1.add(textArea);
	      
	      FliegeImg = new JLabel("");
	      FliegeImg.addMouseMotionListener(new MouseMotionAdapter() {
	      	@Override
	      	public void mouseDragged(MouseEvent e) {
	      		callFliegeHunted();
	      	}
	      });
	      FliegeImg.addMouseListener(new MouseAdapter() {
	      	@Override
	      	public void mouseClicked(MouseEvent e) {
	      		callFliegeHunted();
	      	}
	      });
	      FliegeImg.setIcon(new ImageIcon(Client.class.getResource("/resources/fliege.jpg")));
	      panel.add(FliegeImg);
	      f.getContentPane().setLayout(groupLayout);
	      f.setExtendedState(JFrame.MAXIMIZED_BOTH);
	      f.setVisible(true);
    	
    }
	
    public static void setLoginUI() {
    	
        f = new JFrame("A JFrame");
        f.setSize(607, 447);
        f.setLocation(300,200);
        
        int frameCenterX,frameCenterY;
        
        frameCenterX = (int) f.getSize().getWidth()/2;
        frameCenterY = (int) f.getSize().getHeight()/2;
        
        panel = new JPanel();
        groupLayout = new GroupLayout(f.getContentPane());
        groupLayout.setHorizontalGroup(
        	groupLayout.createParallelGroup(Alignment.LEADING)
        		.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
        			.addContainerGap(577, Short.MAX_VALUE)
        			.addComponent(panel, GroupLayout.PREFERRED_SIZE, 309, GroupLayout.PREFERRED_SIZE)
        			.addGap(468))
        );
        groupLayout.setVerticalGroup(
        	groupLayout.createParallelGroup(Alignment.LEADING)
        		.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
        			.addContainerGap(348, Short.MAX_VALUE)
        			.addComponent(panel, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
        			.addGap(282))
        );
        
        playerNameLabel = new JLabel("Player Name");
        playerNameLabel.setHorizontalAlignment(SwingConstants.LEFT);
        
        playerNameLabel.setLocation(frameCenterX-500,frameCenterY-5);
        panel.add(playerNameLabel);
        
        playerNameTextField = new JTextField();
        playerNameTextField.setHorizontalAlignment(SwingConstants.LEFT);
        playerNameTextField.setColumns(10);
        playerNameTextField.setLocation(frameCenterX+(int) (playerNameLabel.getSize().getWidth()),frameCenterY-5);
        panel.add(playerNameTextField);
        
        connectButton = new JButton("Connect");
        connectButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		String name = playerNameTextField.getText();
        		System.out.println("Hello "+name);
        		initialiseUIForPlayer();
        	}
        });
        panel.add(connectButton);
        
        f.getContentPane().setLayout(groupLayout);
        f.setExtendedState(JFrame.MAXIMIZED_BOTH);
        f.setVisible(true);


    }
    
	public static void main(String[] args) {
	
		setLoginUI();
		//initialiseUIForPlayer();
		
	}
}
