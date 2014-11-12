import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

//Interface for adding the listeners
interface ClientViewListener {

    public void connectButtonActionWithName(String name);

    public void callFliegeHunted();

    public void callLogoutAction();
}


public class ClientView {

    static ArrayList<ClientViewListener> listeners = new ArrayList<ClientViewListener>();
    //Call this method when you get the callback from server for new position of Fliege...
    static int i = 0, j = 0;
    //private static Fliege F;
    private static JFrame f;
    private static JTextArea textArea;
    private static JLabel FliegeImg;
    private static JPanel panel;
    private static JPanel panel_1;
    private static GroupLayout groupLayout;
    private static JLabel playerNameLabel;
    private static JButton connectButton;


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
private static JTextField playerNameTextField;
//    public static void setLocationForFliege(int x, int y) {
//
//        FliegeImg.setBounds(new Rectangle(new Point(200, 300), FliegeImg.getPreferredSize()));
//        System.out.println("setLocationForFliege is called " + ++i);
//    }

    public static void setPositionOfFliege(int x, int y) {

//        int fliege_x, fliege_y;
        //fliege_x = newFliege.getPositionFlyX();
        //fliege_y = newFliege.getPositionFlyY();


        System.out.println("client setPositionFielge");
        System.out.println(x);
        System.out.println(y);

        FliegeImg.setVisible(false);
//        setLocationForFliege(x, y)
        FliegeImg.setLocation(x,y);

        FliegeImg.setText("Hello");

        System.out.println(FliegeImg.getX());
        System.out.println("setPositionOfFliege is called " + ++j);
        FliegeImg.setVisible(true);

        f.revalidate();

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


        // F = new Fliege();

/*	      f.setSize(521, 387);
	      f.setLocation(300,200);
*/

        f.getContentPane().remove(panel);

        panel = new JPanel();
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
        textArea.setWrapStyleWord(true);
        textArea.setSize(200, 600);
        panel_1.add(textArea);


        FliegeImg = new JLabel("");
        FliegeImg.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                //triggerMouseEventsToClientController();
            }
        });
        FliegeImg.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                triggerMouseEventsToClientController();
            }
        });
        FliegeImg.setIcon(new ImageIcon(ClientView.class.getResource("/resources/fliege.jpg")));
        System.out.println("initialising here");
        panel.add(FliegeImg);
        f.getContentPane().add(panel);
        f.getContentPane().setLayout(groupLayout);
        f.setExtendedState(JFrame.MAXIMIZED_BOTH);
        f.setVisible(true);
        System.out.println(panel.getSize());

        //WindowEvent Trigger for logging out.....

        f.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {

                triggerWindowCloseEventsToClientController();

                f.setVisible(false);
                f.dispose();
            }
        });

        f.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        panel.setLayout(null);
//        FliegeImg.setLocation(100, 300);
        //FliegeImg.setLayout(null);
    }

    /**
     * @wbp.parser.entryPoint
     */
    public static void setLoginUI() {

        f = new JFrame("A JFrame");
        f.setSize(607, 447);
//        f.setLocation(300, 200);

        int frameCenterX, frameCenterY;

        frameCenterX = (int) f.getSize().getWidth() / 2;
        frameCenterY = (int) f.getSize().getHeight() / 2;

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

        playerNameLabel.setLocation(frameCenterX - 500, frameCenterY - 5);
        panel.add(playerNameLabel);

        playerNameTextField = new JTextField();
        playerNameTextField.setHorizontalAlignment(SwingConstants.LEFT);
        playerNameTextField.setColumns(10);
        playerNameTextField.setLocation(frameCenterX + (int) (playerNameLabel.getSize().getWidth()), frameCenterY - 5);
        panel.add(playerNameTextField);

        connectButton = new JButton("Connect");
        connectButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String name = playerNameTextField.getText();
                System.out.println("Hello " + name);
                //
                if (name.length() != 0) {
                    triggerButtonActionToClientControllerWithString(name);
                }

            }
        });
        panel.add(connectButton);

        f.getContentPane().setLayout(groupLayout);
        f.setExtendedState(JFrame.MAXIMIZED_BOTH);
        f.setVisible(true);


    }

    public static void triggerButtonActionToClientControllerWithString(String name) {

        for (ClientViewListener cl : listeners)
            cl.connectButtonActionWithName(name);
    }

    public static void triggerMouseEventsToClientController() {

        for (ClientViewListener cl : listeners)
            cl.callFliegeHunted();
    }

    public static void triggerWindowCloseEventsToClientController() {

        for (ClientViewListener cl : listeners)
            cl.callLogoutAction();
    }

    public void showPlayerListInUI(String playerList) {

        textArea.setText(playerList);
    }

    public void addListener(ClientViewListener toAdd) {

        listeners.add(toAdd);
    }
    
    
/*	public static void main(String[] args) {
	
		//initialiseUIForPlayer();
		
	}
*/
}

