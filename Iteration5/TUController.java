package application;

import java.io.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.awt.*;
import java.util.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class TUController implements ActionListener {
	private JPanel tuPanel;
	private int NUM=5;
	private int MAX_USER=5;
	private String trainerID;
	private ArrayList<String> userIDs;
	private Color lightc=new Color(230,246,250);
	private Color titlec=new Color(207,236,245);
	private JButton send;
	private JTextField chatbox;
	private String[] pairList;
	private int userNum;
	private int currentUser;
	private JButton viewUser;
	public TUController(String ID,String[] pair) {
		this.trainerID=ID;
		this.pairList=pair;
		this.userIDs=new ArrayList<String>(MAX_USER);
		this.userNum=0;
		this.currentUser=0;
		this.tuPanel=new JPanel(new BorderLayout(10,10));
		try {			
			FileReader fr2=new FileReader("files/UserTrainer.csv");
			BufferedReader br2=new BufferedReader(fr2);
			String newLine2=br2.readLine();
			while(newLine2!=null&&userNum<MAX_USER) {
				String[] attributes=newLine2.split(",");
				if(attributes[2].equals(this.trainerID)) {
					this.userIDs.add(attributes[1]);
					userNum++;
				}
				newLine2=br2.readLine();
			}
			br2.close();
			fr2.close();
			
			this.tuPanel.add(createMyTPane(userIDs.get(currentUser)),BorderLayout.CENTER);
			this.tuPanel.setOpaque(false);
			this.tuPanel.setBorder(new EmptyBorder(15,15,15,15));
		}
		catch(Exception e) {
			ErrorMessage em=new ErrorMessage();
			e.printStackTrace();
		}
	}
	public void increUser() {
		if(currentUser==userNum-1)
			currentUser=0;
		else
			currentUser++;
	}
	public JPanel createMyTPane(String uID) {
		JPanel myTPanel=new JPanel(new BorderLayout());
		try {
			JPanel myTTitlePanel=new JPanel();
			JLabel myTTitleLabel=new JLabel("My User");
			myTTitleLabel.setFont(new Font("Arial",Font.BOLD,16));
			myTTitlePanel.add(myTTitleLabel);
			myTTitlePanel.setBackground(titlec);
			myTPanel.add(myTTitlePanel,BorderLayout.NORTH);
			
			JPanel myTDetailPanel=new JPanel(new BorderLayout());
			JPanel myTDetailPanel1=new JPanel(new BorderLayout(5,5));
			JPanel myTDetailPanel12=new JPanel(new GridLayout(4,0));
			
			if(this.userNum==0) {
				JPanel noTrainerPanel=new JPanel();
				JLabel noTrainerLabel=new JLabel("No user found",JLabel.CENTER);
				noTrainerPanel.add(noTrainerLabel);
				noTrainerPanel.setOpaque(false);
				noTrainerPanel.setBorder(new EmptyBorder(10,10,10,10));
				noTrainerPanel.setPreferredSize(new Dimension(800,45));
				myTPanel.add(noTrainerPanel,BorderLayout.CENTER);
			}
			else {
				String uName="";
				String uType="";
				String uImg="";
				JPanel myTSubPanel=new JPanel(new GridLayout(2,0,15,15));
				JPanel myTSubPanel1=new JPanel(new GridLayout(0,2,15,15));
				JPanel myTSubPanel2=new JPanel(new GridLayout(0,2,15,15));
				FileReader fr3=new FileReader("files/User.csv");
				BufferedReader br3=new BufferedReader(fr3);
				String newLine3=br3.readLine();
				while(newLine3!=null) {
					String[] attributes2=newLine3.split(",");
					if(attributes2[0].equals(uID)) {
						uName=attributes2[2];
						uType=attributes2[3]+" | Rank: "+attributes2[6];
						uImg=attributes2[11];
						break;
					}
					newLine3=br3.readLine();
				}
				br3.close();
				fr3.close();
				BufferedImage tImage=ImageIO.read(new File("imgs/User/"+uImg));
				ImageIcon utIcon=new ImageIcon();
				utIcon.setImage(tImage.getScaledInstance(190,190,Image.SCALE_DEFAULT));
				JLabel udLabel=new JLabel(utIcon);
				myTDetailPanel1.add(udLabel,BorderLayout.CENTER);
				
				JPanel viewButtonPane=new JPanel();
				this.viewUser=new JButton("view next user");
				this.viewUser.addActionListener(this);
				this.viewUser.setBackground(titlec);
				viewButtonPane.add(this.viewUser);
				viewButtonPane.setOpaque(false);

				JLabel trainerNameLabel=new JLabel(uName,JLabel.CENTER);
				trainerNameLabel.setFont(new Font("Arial",Font.BOLD,20));
				JLabel trainerIDLabel=new JLabel("ID: "+uID,JLabel.CENTER);
				trainerIDLabel.setFont(new Font("Arial",Font.BOLD,13));
				JLabel trainerTypeLabel=new JLabel(uType,JLabel.CENTER);
				trainerTypeLabel.setFont(new Font("Arial",Font.BOLD,13));
				myTDetailPanel12.add(trainerNameLabel);
				myTDetailPanel12.add(trainerIDLabel);
				myTDetailPanel12.add(trainerTypeLabel);
				
				myTDetailPanel12.add(viewButtonPane);
				myTDetailPanel12.setBackground(lightc);
				myTDetailPanel12.setBorder(new EmptyBorder(10,10,10,30));
				myTDetailPanel1.add(myTDetailPanel12,BorderLayout.EAST);
				myTDetailPanel1.setBackground(lightc);
				myTDetailPanel.add(myTDetailPanel1);
				myTDetailPanel.setBackground(lightc);
				myTDetailPanel.setBorder(new EmptyBorder(10,10,10,10));
				myTSubPanel1.add(myTDetailPanel);
								
				JPanel myTChatPanel=new JPanel(new BorderLayout(10,10));
				JPanel myTChatBoxPanel=new JPanel();
				JLabel chatlabel=new JLabel("Enter: ",JLabel.CENTER);
				this.chatbox=new JTextField(35);
				this.send=new JButton("send");
				this.send.setBackground(titlec);
				this.send.addActionListener(this);
				myTChatBoxPanel.add(chatlabel);
				myTChatBoxPanel.add(this.chatbox);
				myTChatBoxPanel.add(this.send);
				myTChatBoxPanel.setBackground(lightc);
				myTChatBoxPanel.setBorder(new EmptyBorder(10,5,0,5));
				myTChatBoxPanel.setPreferredSize(new Dimension(490,45));
				myTChatPanel.add(myTChatBoxPanel,BorderLayout.NORTH);
				
				JPanel chatContentPane=new JPanel(new GridLayout(8,0,5,5));
				FileReader chatFr=new FileReader("files/Chatting.csv");
				BufferedReader chatBr=new BufferedReader(chatFr);
				String chatLine=chatBr.readLine();
				String[] chatting=new String[5];
				for(int i=0;i<5;i++)
					chatting[i]="";
				int chatCount=0;
				while(chatLine!=null&&chatCount<5) {
					String[] chatAttr=chatLine.split(",");
					if(chatAttr[0].equals(this.pairList[currentUser])) {
						if(chatAttr[1].equals("trainer"))
							chatting[chatCount]="  "+"YOU: "+chatAttr[2];
						else
							chatting[chatCount]="  "+chatAttr[1]+": "+chatAttr[2];
						JLabel myTChatLabel=new JLabel(chatting[chatCount],JLabel.LEFT);
						chatContentPane.add(myTChatLabel);
						chatCount++;
					}
					chatLine=chatBr.readLine();
				}
				chatBr.close();
				chatFr.close();
				chatContentPane.setBackground(new Color(249,251,253));
				chatContentPane.setBorder(new EmptyBorder(3,0,3,0));
				myTChatPanel.add(chatContentPane,BorderLayout.CENTER);
				myTChatPanel.setBorder(new EmptyBorder(0,10,10,10));
				myTChatPanel.setBackground(lightc);
				myTSubPanel1.add(myTChatPanel);
				
				JPanel myTBookPanel=new JPanel(new BorderLayout());
				JPanel myTBookTitlePanel=new JPanel();
				JLabel myTBookTitleLabel=new JLabel("Live Session",JLabel.CENTER);
				myTBookTitleLabel.setFont(new Font("Arial",Font.BOLD,16));
				myTBookTitlePanel.add(myTBookTitleLabel);
				myTBookTitlePanel.setBackground(titlec);
				myTBookPanel.add(myTBookTitlePanel,BorderLayout.NORTH);
				
				JPanel myTBookBookPanel=new JPanel(new GridLayout(5,0,5,5));
				FileReader bookFr=new FileReader("files/Booking.csv");
				BufferedReader bookBr=new BufferedReader(bookFr);
				String bookLine=bookBr.readLine();
				String[] booking=new String[4];
				for(int i=0;i<4;i++)
					booking[i]="";
				int bookCount=0;
				while(bookLine!=null&&bookCount<4) {
					String[] bookAttr=bookLine.split(",");
					if(bookAttr[1].equals(this.pairList[currentUser])) {
						if(bookAttr[5].equals("on")) {
							booking[bookCount]=bookAttr[2]+", Platform: "+bookAttr[3]+", Room: "+bookAttr[4];
							LiveSession ls=new LiveSession(booking[bookCount],bookAttr[0]);
							myTBookBookPanel.add(ls.getPanel());
							bookCount++;
						}
					}
					bookLine=bookBr.readLine();
				}
				bookBr.close();
				bookFr.close();

				myTBookBookPanel.setOpaque(false);
				myTBookBookPanel.setBorder(new EmptyBorder(10,10,10,10));
				myTBookPanel.add(myTBookBookPanel,BorderLayout.CENTER);
				myTBookPanel.setBackground(lightc);
				myTSubPanel2.add(myTBookPanel);
				
				JPanel myUInfoPanel=new JPanel(new BorderLayout());
				JPanel myUInfoTitlePanel=new JPanel();
				JLabel myUInfoTitleLabel=new JLabel("User Body Info",JLabel.CENTER);
				myUInfoTitleLabel.setFont(new Font("Arial",Font.BOLD,16));
				myUInfoTitlePanel.add(myUInfoTitleLabel);
				myUInfoTitlePanel.setBackground(titlec);
				myUInfoPanel.add(myUInfoTitlePanel,BorderLayout.NORTH);
				
				JPanel infoContentPanel=new JPanel();
				FileReader infoFr=new FileReader("files/BodyInfo.csv");
				BufferedReader infoBr=new BufferedReader(infoFr);
				String infoLine=infoBr.readLine();
				boolean isInfoFound=false;
				String[] infoList=new String[8];
				while(infoLine!=null) {
					String[] infoAttr=infoLine.split(",");
					if(infoAttr[0].equals(userIDs.get(currentUser))) {
						isInfoFound=true;
						for(int j1=0;j1<8;j1++)
							infoList[j1]=infoAttr[j1+1];
						break;
					}
					infoLine=infoBr.readLine();
				}
				infoBr.close();
				infoFr.close();
				
				JPanel infoSPanel=new JPanel(new GridLayout(0,2));
				if(isInfoFound) {
					JLabel infok1=new JLabel("Height: "+infoList[0]+"cm",JLabel.LEFT);
					infoSPanel.add(infok1);
					JLabel infok2=new JLabel("Weight: "+infoList[1]+"kg",JLabel.LEFT);
					infoSPanel.add(infok2);
					JLabel infok3=new JLabel("BMI: "+infoList[2],JLabel.LEFT);
					infoSPanel.add(infok3);
					JLabel infok4=new JLabel("BF Rate: "+infoList[3]+"%",JLabel.LEFT);
					infoSPanel.add(infok4);
					JLabel infok5=new JLabel("Focus: "+infoList[4],JLabel.LEFT);
					infoSPanel.add(infok5);
					JLabel infok6=new JLabel("Req: "+infoList[5],JLabel.LEFT);
					infoSPanel.add(infok6);
					JLabel infok7=new JLabel("Level: "+infoList[6],JLabel.LEFT);
					infoSPanel.add(infok7);
					JLabel infok8=new JLabel("Ability: "+infoList[7],JLabel.LEFT);
					infoSPanel.add(infok8);
					infoSPanel.setOpaque(false);
					infoSPanel.setBorder(new EmptyBorder(20,20,20,20));
					infoSPanel.setOpaque(false);
					myUInfoPanel.add(infoSPanel,BorderLayout.CENTER);
				}
				else {
					JLabel collContentLabel=new JLabel("No user info found",JLabel.CENTER);
					infoContentPanel.add(collContentLabel);
					infoContentPanel.setOpaque(false);
					myUInfoPanel.add(infoContentPanel);
				}
				myUInfoPanel.setBackground(lightc);
				myTSubPanel2.add(myUInfoPanel);
				
				myTSubPanel1.setBackground(lightc);
				myTSubPanel2.setOpaque(false);
				myTSubPanel.add(myTSubPanel1);
				myTSubPanel.add(myTSubPanel2);
				myTSubPanel.setOpaque(false);
				myTPanel.add(myTSubPanel);
				myTPanel.setOpaque(false);
			}
			myTPanel.setBackground(lightc);
		}
		catch(Exception e) {
			ErrorMessage em=new ErrorMessage();
			e.printStackTrace();
		}
		return myTPanel;
	}
	public JPanel getTUPanel() {
		return this.tuPanel;
	}
	public void sendMessage(String msg) {
		try {
			RandomAccessFile raf=new RandomAccessFile("files/Chatting.csv", "rw");
			raf.seek(raf.length());
			raf.writeBytes(this.pairList[currentUser]+",user,"+msg+"\n");
			raf.close();
		}
		catch(Exception e) {
			ErrorMessage em=new ErrorMessage();
			e.printStackTrace();
		}
	}
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==this.send) {
			String message=this.chatbox.getText();
			sendMessage(message);
			this.tuPanel.removeAll();
			this.tuPanel.add(createMyTPane(userIDs.get(currentUser)),BorderLayout.CENTER);
			this.tuPanel.setVisible(false);
			this.tuPanel.setVisible(true);
		}
		else if(e.getSource()==this.viewUser) {
			increUser();
			this.tuPanel.removeAll();
			this.tuPanel.add(createMyTPane(userIDs.get(currentUser)),BorderLayout.CENTER);
			this.tuPanel.setVisible(false);
			this.tuPanel.setVisible(true);
		}
	}
	public class LiveSession{
		private JPanel bookPanel;
		private String bookID;
		
		public LiveSession(String booking, String id) {
			this.bookID=id;
			this.bookPanel=new JPanel(new BorderLayout());
			JLabel bookLabel=new JLabel(booking,JLabel.LEFT);
			this.bookPanel.add(bookLabel,BorderLayout.CENTER);
			this.bookPanel.setOpaque(false);
		}
		public JPanel getPanel() {
			return this.bookPanel;
		}
	}
}
