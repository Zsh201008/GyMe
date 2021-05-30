package application;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.*;

import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.FileReader;

public class TrainerPage extends JFrame implements ActionListener{
	private JMenuBar menuBar;
	private JButton t_account;
	private JButton t_trainer;
	private JButton t_video;
	private JPanel mainMenu;
	private TAController ta;
	private TVController tv;
	private TUController tu;
	private Color menuColor=new Color(172,224,238);
	private Image icon;
	private String trainerID;
	private String[] pairList;
	private int MAX_USER=10;
	public TrainerPage(String trainerID){
		this.trainerID=trainerID;
		this.pairList=new String[MAX_USER];
		for(int i=0;i<MAX_USER;i++)
			this.pairList[i]="";
		
		menuBar=new JMenuBar();
		menuBar.setLayout(new GridLayout(3,0,20,20));
		t_account=new JButton("  Account  ");
		t_account.setFont(new Font("Arial",Font.BOLD,14));
		t_trainer=new JButton("    User    ");
		t_trainer.setFont(new Font("Arial",Font.BOLD,14));
		t_video=new JButton("  Workout  ");
		t_video.setFont(new Font("Arial",Font.BOLD,14));
		t_account.setBackground(menuColor);
		t_account.setBorderPainted(false);
		t_trainer.setBackground(menuColor);
		t_trainer.setBorderPainted(false);
		t_video.setBackground(menuColor);
		t_video.setBorderPainted(false);
		t_account.addActionListener(this);
		t_trainer.addActionListener(this);
		t_video.addActionListener(this);
		
		menuBar.add(t_video);
		menuBar.add(t_trainer);
		menuBar.add(t_account);
		menuBar.setBackground(menuColor);
		
		Container content=this.getContentPane();
		
		try {
			this.pairList=new String[MAX_USER];
			for(int i=0;i<MAX_USER;i++)
				this.pairList[i]="";
			FileReader fr2=new FileReader("files/UserTrainer.csv");
			BufferedReader br2=new BufferedReader(fr2);
			String newLine2=br2.readLine();
			int userNum=0;
			while(newLine2!=null&&userNum<MAX_USER) {
				String[] attributes=newLine2.split(",");
				if(attributes[2].equals(this.trainerID)) {
					this.pairList[userNum]=attributes[0];
					userNum++;
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
		
		this.ta=new TAController(trainerID,this.pairList);
		this.tv=new TVController(trainerID,this.pairList);
		this.tu=new TUController(trainerID,this.pairList);
		
		this.mainMenu=new JPanel();
		this.mainMenu.setOpaque(false);
		this.mainMenu.add(this.ta.getTAPanel());
		
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
		if(e.getSource()==this.t_account) {
			this.mainMenu.removeAll();
			this.mainMenu.add(this.ta.getTAPanel());
			setVisible(false);
			setVisible(true);
		}
		else if(e.getSource()==this.t_trainer) {
			this.mainMenu.removeAll();
			this.mainMenu.add(this.tu.getTUPanel());
			setVisible(false);
			setVisible(true);
		}
		else if(e.getSource()==this.t_video) {
			this.mainMenu.removeAll();
			this.mainMenu.add(this.tv.getTVPanel());
			setVisible(false);
			setVisible(true);
		}
	}
//	public static void main(String[] args) {
//		TrainerPage up=new TrainerPage("t85277");
//	}
}
