import java.io.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.*;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

//Display User Account Information
public class UAOperator implements ActionListener{
	private JPanel uaPanel;
	private String uName;
	private String uSex;
	private String uType;
	private int uRank;
	private int uClassNum;
	public UAOperator(String userID) {
		this.uaPanel=new JPanel(new BorderLayout());
		try {
			JPanel uaName=new JPanel(new GridLayout(0,5,0,0));
			BufferedImage bimage=ImageIO.read(new File("./imgs/UA/avatar.jpg"));
			ImageIcon uaAvatar=new ImageIcon();
			uaAvatar.setImage(bimage.getScaledInstance(140,140,Image.SCALE_DEFAULT));
			JLabel avatarLabel=new JLabel(uaAvatar);
			uaName.add(avatarLabel);
			
			JPanel namePanel=new JPanel(new GridLayout(3,0,0,0));
			FileReader fr=new FileReader("files/User.csv");
			BufferedReader br=new BufferedReader(fr);
			String newLine=br.readLine();
			while(newLine!=null) {
				String[] attributes=newLine.split(",");
				if(attributes[0].equals(userID)) {
					this.uName=attributes[2];
					this.uSex=attributes[3];
					this.uRank=Integer.parseInt(attributes[6]);
					this.uType=attributes[7];
					this.uClassNum=Integer.parseInt(attributes[9]);
					break;
				}
				newLine=br.readLine();
			}
			JPanel namePanel1=new JPanel(new BorderLayout());
			JLabel nameLabel=new JLabel(this.uName);
			nameLabel.setFont(new Font("Arial",Font.BOLD,28));
			nameLabel.setOpaque(false);
			namePanel1.add(nameLabel,BorderLayout.CENTER);
			BufferedImage buaSex;
			if(this.uSex.equals("M"))
				buaSex=ImageIO.read(new File("./imgs/UA/m.png"));
			else
				buaSex=ImageIO.read(new File("./imgs/UA/f.png"));
			ImageIcon sexIcon=new ImageIcon();
			sexIcon.setImage(buaSex.getScaledInstance(25,25,Image.SCALE_DEFAULT));
			JLabel sexLabel=new JLabel(sexIcon);
			sexLabel.setOpaque(false);
			namePanel1.add(sexLabel,BorderLayout.EAST);
			namePanel1.setOpaque(false);
			namePanel.add(namePanel1);
			
			JLabel accNoLabel=new JLabel("   Account: "+userID,JLabel.LEFT);
			accNoLabel.setFont(new Font("Arial",Font.BOLD,14));
			namePanel.add(accNoLabel);
			
			JPanel namePanel3=new JPanel(new FlowLayout(0,10,10));
			JPanel typePanel=new JPanel(new GridLayout(1,0,0,0));
			String typeDetail="";
			if(this.uType.equals("C")) {
				typePanel.setBackground(new Color(253,253,210));
				typeDetail="Common";
			}
			else {
				typePanel.setBackground(new Color(253,178,157));
				typeDetail="Privilage";
			}
			JLabel typeLabel=new JLabel("Type: "+typeDetail);
			typeLabel.setFont(new Font("Arial",Font.BOLD,14));
			typePanel.add(typeLabel);
			JPanel rankPanel=new JPanel(new GridLayout(1,0,0,0));
			String rankDetail="";
			if(this.uRank==1) {
				rankPanel.setBackground(new Color(240,222,222));
				rankDetail="Copper";
			}
			else if(this.uRank==2) {
				rankPanel.setBackground(new Color(250,250,250));
				rankDetail="Silver";
			}
			else {
				rankPanel.setBackground(new Color(254,246,208));
				rankDetail="Golden";
			}
			JLabel rankLabel=new JLabel("Rank: "+rankDetail);
			rankLabel.setFont(new Font("Arial",Font.BOLD,14));
			rankPanel.add(rankLabel);
			namePanel3.add(typePanel);
			namePanel3.add(rankPanel);
			namePanel3.setOpaque(false);
			namePanel.add(namePanel3);
			
			namePanel.setOpaque(false);
			uaName.add(namePanel);
			uaName.setOpaque(false);
			this.uaPanel.add(uaName,BorderLayout.NORTH);    //
			
			JPanel infoPanel=new JPanel(new GridLayout(0,2,20,20));
			infoPanel.setOpaque(false);
			
			JPanel hisPanel=new JPanel(new GridLayout(5,0,5,5));
			JPanel hisTitlePanel=new JPanel();
			hisTitlePanel.setBackground(new Color(215,240,247));
			JLabel hisTitleLabel=new JLabel("Workout History",JLabel.LEFT);
			hisTitleLabel.setFont(new Font("Arial",Font.BOLD,16));
			hisTitlePanel.add(hisTitleLabel);
			hisPanel.add(hisTitlePanel);
			
			JPanel hisContentPanel1=new JPanel();
			hisContentPanel1.setBackground(new Color(230,246,250));
			JLabel hisContentLabel1=new JLabel("Pamela Arm Strength Improvement, 2021-04-15 16:30",JLabel.LEFT);
			hisContentPanel1.add(hisContentLabel1);
			hisPanel.add(hisContentPanel1);
			
			JPanel hisContentPanel2=new JPanel();
			hisContentPanel2.setBackground(new Color(230,246,250));
			JLabel hisContentLabel2=new JLabel("HIIT Fantastic Dance Training, 2021-04-14 17:23",JLabel.LEFT);
			hisContentPanel2.add(hisContentLabel2);
			hisPanel.add(hisContentPanel2);
			
			JPanel hisContentPanel3=new JPanel();
			hisContentPanel3.setBackground(new Color(230,246,250));
			JLabel hisContentLabel3=new JLabel("Workout Stretch Relaxation Course, 2021-04-12 18:07",JLabel.LEFT);
			hisContentPanel3.add(hisContentLabel3);
			hisPanel.add(hisContentPanel3);
			
			hisPanel.setBackground(new Color(230,246,250));
			
			JPanel collPanel=new JPanel(new GridLayout(5,0,5,5));
			JPanel collTitlePanel=new JPanel();
			collTitlePanel.setBackground(new Color(215,240,247));
			JLabel collTitleLabel=new JLabel("My Collection",JLabel.LEFT);
			collTitleLabel.setFont(new Font("Arial",Font.BOLD,16));
			collTitlePanel.add(collTitleLabel);
			collPanel.add(collTitlePanel);
			
			JPanel collContentPanel=new JPanel();
			collContentPanel.setBackground(new Color(230,246,250));
			JLabel collContentLabel=new JLabel("No collection found");
			collContentPanel.add(collContentLabel);
			collPanel.add(collContentPanel);
			collPanel.setBackground(new Color(230,246,250));
			infoPanel.add(collPanel);
			infoPanel.setBorder(new EmptyBorder(30,30,30,30));
			
			JPanel bodyPanel=new JPanel(new GridLayout(5,0,5,5));
			JPanel bodyTitlePanel=new JPanel();
			bodyTitlePanel.setBackground(new Color(215,240,247));
			JLabel bodyTitleLabel=new JLabel("My Body Insight",JLabel.LEFT);
			bodyTitleLabel.setFont(new Font("Arial",Font.BOLD,16));
			bodyTitlePanel.add(bodyTitleLabel);
			bodyPanel.add(bodyTitlePanel);
			
			JPanel bodyContentPanel1=new JPanel();
			bodyContentPanel1.setBackground(new Color(230,246,250));
			JLabel bodyContentLabel1=new JLabel("Height: 180 cm");
			bodyContentPanel1.add(bodyContentLabel1);
			bodyPanel.add(bodyContentPanel1);
			bodyPanel.setBackground(new Color(230,246,250));
			
			JPanel bodyContentPanel2=new JPanel();
			bodyContentPanel2.setBackground(new Color(230,246,250));
			JLabel bodyContentLabel2=new JLabel("Weight: 65 kg");
			bodyContentPanel2.add(bodyContentLabel2);
			bodyPanel.add(bodyContentPanel2);
			bodyPanel.setBackground(new Color(230,246,250));
			
			JPanel bodyContentPanel3=new JPanel();
			bodyContentPanel3.setBackground(new Color(230,246,250));
			JLabel bodyContentLabel3=new JLabel("BMI: 20.1 (Normal)");
			bodyContentPanel3.add(bodyContentLabel3);
			bodyPanel.add(bodyContentPanel3);
			bodyPanel.setBackground(new Color(230,246,250));
			
			JPanel hisSPanel=new JPanel(new GridLayout(1,0,0,0));
			hisSPanel.setOpaque(false);
			hisSPanel.add(hisPanel);
			hisSPanel.setBorder(new EmptyBorder(20,30,30,30));
			
			infoPanel.add(bodyPanel);
			infoPanel.add(collPanel);
			infoPanel.setBorder(new EmptyBorder(30,30,0,30));
			
			this.uaPanel.add(infoPanel,BorderLayout.CENTER);    //
			this.uaPanel.add(hisSPanel,BorderLayout.SOUTH);
			this.uaPanel.setBorder(new EmptyBorder(30,30,30,30));
			
			this.uaPanel.setOpaque(false);
		}
		catch(Exception e) {
			ErrorMessage er=new ErrorMessage();
			e.printStackTrace();
		}
	}
	public JPanel getUAPanel() {
		return this.uaPanel;
	}
	public void actionPerformed(ActionEvent e) {
		
	}
//	public static void main(String[] args) {
//		UAOperator ua=new UAOperator("u20108");
//		JFrame testFrame=new JFrame();
//		testFrame.add(ua.getUAPanel());
//		testFrame.setTitle("GyMe-Your Virtual Gym");
//		testFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		testFrame.setSize(1200,600);
//		testFrame.setLocation(250,100);
//		testFrame.setVisible(true);
//	}
}
