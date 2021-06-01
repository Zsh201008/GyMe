/**
 * Title: AdminPage.java
 * Boundary class of displaying administrator page.
 * @author Shuhan Zhang
 * @version 5.0
 */
import javax.swing.*;
import java.awt.*;

public class AdminPage extends JFrame{
	private ASController as;
	private Color menuColor=new Color(172,224,238);
	public AdminPage(String adminID){
		Container content=this.getContentPane();
		this.as=new ASController();
		
		ImageIcon img = new ImageIcon("imgs/background.jpg");
		JLabel imgLabel = new JLabel(img);
		JPanel myPanel=(JPanel)content;
		myPanel.setLayout(new BorderLayout());
		myPanel.setOpaque(false);
		this.getLayeredPane().setLayout(null);
		
		myPanel.add(this.as.getASPanel(),BorderLayout.CENTER);
		this.getLayeredPane().add(imgLabel, new Integer(Integer.MIN_VALUE));
		imgLabel.setBounds(0, 0, 1200, 600);
		
		ImageIcon ig = new ImageIcon("imgs/icon.jpg");
		Image icon = ig.getImage();
		setIconImage(icon);
		
		setTitle("GyMe-Your Virtual Gym, Admin: "+adminID);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1200,640);
		setLocation(250,100);
		setVisible(true);
	}
//	public static void main(String[] args) {
//		AdminPage up=new AdminPage("m60001");
//	}
}
