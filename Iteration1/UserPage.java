import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.*;

import java.awt.*;
import java.awt.event.*;

public class UserPage extends JFrame implements ActionListener{
	JMenuBar menuBar;
	JButton u_account;
	JButton u_discover;
	JButton u_trainer;
	JButton u_video;
	JPanel mainMenu;
	UAOperator ua;
	UVOperator uv;
	UDOperator ud;
	UTOperator ut;
	Color menuColor=new Color(172,224,238);
	Image icon;
	public UserPage(String userID){
		menuBar=new JMenuBar();
		menuBar.setLayout(new GridLayout(4,0,20,20));
		u_account=new JButton("     Account    ");
		u_discover=new JButton("    Discover    ");
		u_trainer=new JButton("     Trainer    ");
		u_video=new JButton("    Workout    ");
		u_account.setBackground(menuColor);
		u_account.setBorderPainted(false);
		u_discover.setBackground(menuColor);
		u_discover.setBorderPainted(false);
		u_trainer.setBackground(menuColor);
		u_trainer.setBorderPainted(false);
		u_video.setBackground(menuColor);
		u_video.setBorderPainted(false);
		u_account.addActionListener(this);
		u_trainer.addActionListener(this);
		u_video.addActionListener(this);
		u_discover.addActionListener(this);
		
		menuBar.add(u_video);
		menuBar.add(u_trainer);
		menuBar.add(u_discover);
		menuBar.add(u_account);
		menuBar.setBackground(menuColor);
		
		Container content=this.getContentPane();
		
		this.ua=new UAOperator(userID);
		this.uv=new UVOperator(userID);
		this.ud=new UDOperator(userID);
		this.ut=new UTOperator(userID);
		
		this.mainMenu=new JPanel();
		this.mainMenu.setOpaque(false);
		this.mainMenu.add(this.ua.getUAPanel());
		
		ImageIcon img = new ImageIcon("imgs/background.jpg");
		JLabel imgLabel = new JLabel(img);
		JPanel myPanel=(JPanel)content;
		myPanel.setLayout(new BorderLayout());
		myPanel.setOpaque(false);
		this.getLayeredPane().setLayout(null);
		myPanel.add(this.menuBar,BorderLayout.WEST);
		myPanel.add(this.mainMenu,BorderLayout.CENTER);
		this.getLayeredPane().add(imgLabel, new Integer(Integer.MIN_VALUE));
		imgLabel.setBounds(0, 0, 1200, 600);
		
		ImageIcon ig = new ImageIcon("imgs/icon.jpg");
		icon = ig.getImage();
		setIconImage(icon);
		
		setTitle("GyMe-Your Virtual Gym");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1200,640);
		setLocation(250,100);
		setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==this.u_account) {
			this.mainMenu.removeAll();
			this.mainMenu.add(this.ua.getUAPanel());
			setVisible(false);
			setVisible(true);
		}
		else if(e.getSource()==this.u_trainer) {
			this.mainMenu.removeAll();
			this.mainMenu.add(this.ut.getUTPanel());
			setVisible(false);
			setVisible(true);
		}
		else if(e.getSource()==this.u_video) {
			this.mainMenu.removeAll();
			this.mainMenu.add(this.uv.getUVPanel());
			setVisible(false);
			setVisible(true);
		}
		else if(e.getSource()==this.u_discover) {
			this.mainMenu.removeAll();
			this.mainMenu.add(this.ud.getUDPanel());
			setVisible(false);
			setVisible(true);
		}
	}
//	public static void main(String[] args) {
//		UserPage up=new UserPage("u20108");
//	}
}
