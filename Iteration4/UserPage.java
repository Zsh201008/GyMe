import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.*;

import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.FileReader;

public class UserPage extends JFrame implements ActionListener{
	private JMenuBar menuBar;
	private JButton u_account;
	private JButton u_discover;
	private JButton u_trainer;
	private JButton u_video;
	private JPanel mainMenu;
	private UAController ua;
	private UVController uv;
	private UDController ud;
	private UTController ut;
	private Color menuColor=new Color(172,224,238);
	private Image icon;
	private String userID;
	private String[] pairList;
	private int MAX_TRAINER=5;
	public UserPage(String userID){
		this.userID=userID;
		menuBar=new JMenuBar();
		menuBar.setLayout(new GridLayout(4,0,20,20));
		u_account=new JButton("  Account  ");
		u_account.setFont(new Font("Arial",Font.BOLD,14));
		u_discover=new JButton("  Discover  ");
		u_discover.setFont(new Font("Arial",Font.BOLD,14));
		u_trainer=new JButton("  Trainer  ");
		u_trainer.setFont(new Font("Arial",Font.BOLD,14));
		u_video=new JButton("  Workout  ");
		u_video.setFont(new Font("Arial",Font.BOLD,14));
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
		
		try {
			this.pairList=new String[MAX_TRAINER];
			for(int i=0;i<MAX_TRAINER;i++)
				this.pairList[i]="";
			FileReader fr2=new FileReader("files/UserTrainer.csv");
			BufferedReader br2=new BufferedReader(fr2);
			String newLine2=br2.readLine();
			int trainerNum=0;
			while(newLine2!=null&&trainerNum<MAX_TRAINER) {
				String[] attributes=newLine2.split(",");
				if(attributes[1].equals(this.userID)) {
					this.pairList[trainerNum]=attributes[0];
					trainerNum++;
				}
				newLine2=br2.readLine();
			}
			br2.close();
			fr2.close();
		}
		catch(Exception e) {
			ErrorMessage em=new ErrorMessage();
			e.printStackTrace();
		}
		
		this.ua=new UAController(userID);
		this.uv=new UVController(userID);
		this.ud=new UDController(userID);
		this.ut=new UTController(userID,this.pairList);
		
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
		
		if(!this.pairList[0].equals("")) {
			Notification nf=new Notification(this.pairList);
		}
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==this.u_account) {
			this.mainMenu.removeAll();
			this.ua=new UAController(userID);
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
