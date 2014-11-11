
import java.awt.BorderLayout;
import java.awt.Panel;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;
import java.awt.event.MouseMotionAdapter;
import java.awt.Color;
public class CopyOfClient {

	public static int randInt(int min, int max) {

	    // NOTE: Usually this should be a field rather than a method
	    // variable so that it is not re-seeded every call.
	    Random rand = new Random();

	    // nextInt is normally exclusive of the top value,
	    // so add 1 to make it inclusive
	    int randomNum = rand.nextInt((max - min) + 1) + min;

	    return randomNum;
	}
	
   public static void setPositionForMouseEventWithLabel(MouseEvent e,JLabel L) {

	   int x,y,label_x,label_y;
		label_x = (int) L.getLocation().getX();
		label_y = (int) L.getLocation().getY();
		x = e.getX();
		y = e.getY();
		System.out.println(label_x+" "+label_y+" "+x+" "+y);
		label_x = randInt(100,400);
		label_y = randInt(0,300);
		L.setLocation(label_x, label_y);
		

   }

	
	  public static void main(String[] args) {
	      
	      JFrame f = new JFrame("");
	      f.setSize(521, 387);
	      f.setLocation(300,200);
	      
	      JPanel panel = new JPanel();
	      panel.setBackground(Color.WHITE);
	      GroupLayout groupLayout = new GroupLayout(f.getContentPane());
	      groupLayout.setHorizontalGroup(
	      	groupLayout.createParallelGroup(Alignment.LEADING)
	      		.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
	      			.addContainerGap()
	      			.addComponent(panel, GroupLayout.DEFAULT_SIZE, 485, Short.MAX_VALUE)
	      			.addContainerGap())
	      );
	      groupLayout.setVerticalGroup(
	      	groupLayout.createParallelGroup(Alignment.LEADING)
	      		.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
	      			.addContainerGap()
	      			.addComponent(panel, GroupLayout.DEFAULT_SIZE, 326, Short.MAX_VALUE)
	      			.addContainerGap())
	      );
	      
	      JLabel FliegeImg = new JLabel("");
	      FliegeImg.addMouseMotionListener(new MouseMotionAdapter() {
	      	@Override
	      	public void mouseDragged(MouseEvent e) {
	      		setPositionForMouseEventWithLabel(e,FliegeImg);
	      	}
	      });
	      FliegeImg.addMouseListener(new MouseAdapter() {
	      	@Override
	      	public void mouseClicked(MouseEvent e) {
	      		setPositionForMouseEventWithLabel(e,FliegeImg);
	      	}
	      });
	      FliegeImg.setIcon(new ImageIcon(CopyOfClient.class.getResource("/resources/fliege.jpg")));
	      panel.add(FliegeImg);
	      f.getContentPane().setLayout(groupLayout);
	      f.setVisible(true);
	      
	    }
}
