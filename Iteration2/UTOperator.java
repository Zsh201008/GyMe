import java.io.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.awt.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class UTOperator implements ActionListener {
	private JPanel utPanel;
	private int NUM=5;
	private int MAX_TRAINER=5;
	private String userID;
	private String trainerID="";
	private Color lightc=new Color(230,246,250);
	private Color titlec=new Color(215,240,247);
	private JButton send;
	private JTextField chatbox;
	private String[] pairList;
	private JButton addTrainer;
	private JButton book;
	public UTOperator(String ID) {
		this.userID=ID;
		this.pairList=new String[MAX_TRAINER];
		this.utPanel=new JPanel(new BorderLayout(10,10));
		try {			
			this.utPanel.add(createMyTPane(),BorderLayout.CENTER);
			this.utPanel.setOpaque(false);
			this.utPanel.setBorder(new EmptyBorder(15,15,15,15));
		}
		catch(Exception e) {
			ErrorMessage em=new ErrorMessage();
			e.printStackTrace();
		}
	}
	public JPanel createMyTPane() {
		JPanel myTPanel=new JPanel(new BorderLayout());
		try {
			JPanel myTTitlePanel=new JPanel();
			JLabel myTTitleLabel=new JLabel("My Trainer");
			myTTitleLabel.setFont(new Font("Arial",Font.BOLD,16));
			myTTitlePanel.add(myTTitleLabel);
			myTTitlePanel.setBackground(titlec);
			myTPanel.add(myTTitlePanel,BorderLayout.NORTH);
			
			JPanel myTDetailPanel=new JPanel(new BorderLayout());
			JPanel myTDetailPanel1=new JPanel(new BorderLayout(10,10));
			JPanel myTDetailPanel12=new JPanel(new GridLayout(4,0));
			String trainerImage="";
			String trainerName="";
			String trainerType="";
			FileReader fr2=new FileReader("files/UserTrainer.csv");
			BufferedReader br2=new BufferedReader(fr2);
			String newLine2=br2.readLine();
			int trainerNum=0;
			while(newLine2!=null&&trainerNum<MAX_TRAINER) {
				String[] attributes=newLine2.split(",");
				if(attributes[1].equals(this.userID)) {
					this.trainerID=attributes[2];  //ALTER: Trainer List
					this.pairList[trainerNum]=attributes[0];
					trainerNum++;
				}
				newLine2=br2.readLine();
			}
			br2.close();
			fr2.close();
			if(this.trainerID.equals("")) {
				JLabel noTrainerLabel=new JLabel("No trainer found",JLabel.CENTER);
				myTPanel.add(noTrainerLabel,BorderLayout.CENTER);
			}
			else {
				JPanel myTSubPanel=new JPanel(new GridLayout(2,0,15,15));
				JPanel myTSubPanel1=new JPanel(new GridLayout(0,2,15,15));
				JPanel myTSubPanel2=new JPanel(new GridLayout(0,2,15,15));
				FileReader fr3=new FileReader("files/Trainer.csv");
				BufferedReader br3=new BufferedReader(fr3);
				String newLine3=br3.readLine();
				while(newLine3!=null) {
					String[] attributes2=newLine3.split(",");
					if(attributes2[0].equals(this.trainerID)) {
						trainerName=attributes2[1];
						trainerType=attributes2[3];
						trainerImage=attributes2[4];
						break;
					}
					newLine3=br3.readLine();
				}
				br3.close();
				fr3.close();
				BufferedImage tImage=ImageIO.read(new File("imgs/UT/"+trainerImage));
				ImageIcon utIcon=new ImageIcon();
				utIcon.setImage(tImage.getScaledInstance(190,190,Image.SCALE_DEFAULT));
				JLabel udLabel=new JLabel(utIcon);
				myTDetailPanel1.add(udLabel,BorderLayout.CENTER);
				
				JLabel trainerNameLabel=new JLabel(trainerName,JLabel.CENTER);
				trainerNameLabel.setFont(new Font("Arial",Font.BOLD,20));
				JLabel trainerIDLabel=new JLabel("ID: "+this.trainerID,JLabel.CENTER);
				trainerIDLabel.setFont(new Font("Arial",Font.BOLD,13));
				JLabel trainerTypeLabel=new JLabel("Focus: "+trainerType,JLabel.CENTER);
				trainerTypeLabel.setFont(new Font("Arial",Font.BOLD,13));
				myTDetailPanel12.add(trainerNameLabel);
				myTDetailPanel12.add(trainerIDLabel);
				myTDetailPanel12.add(trainerTypeLabel);
				
				JPanel buttonPane=new JPanel();
				this.addTrainer=new JButton("Select/Add Trainer");
				this.addTrainer.addActionListener(this);
				this.addTrainer.setBackground(titlec);
				buttonPane.add(this.addTrainer);
				buttonPane.setOpaque(false);
				
				myTDetailPanel12.add(buttonPane);
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
				myTChatBoxPanel.setPreferredSize(new Dimension(490,40));
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
					if(chatAttr[0].equals(this.pairList[0])) {
						if(chatAttr[1].equals("user"))
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
					if(bookAttr[1].equals(this.pairList[0])) {
						booking[bookCount]=bookAttr[2]+", Platform: "+bookAttr[3]+", Room: "+bookAttr[4];
						LiveSession ls=new LiveSession(booking[bookCount],bookAttr[0]);
						myTBookBookPanel.add(ls.getPanel());
						bookCount++;
					}
					bookLine=bookBr.readLine();
				}
				bookBr.close();
				bookFr.close();
				
				JPanel bookButtonPane=new JPanel();
				this.book=new JButton("Book Live Session");
				this.book.addActionListener(this);
				this.book.setBackground(titlec);
				bookButtonPane.add(this.book);
				bookButtonPane.setOpaque(false);
				bookButtonPane.setBorder(new EmptyBorder(0,0,5,0));
				myTBookBookPanel.setOpaque(false);
				myTBookBookPanel.setBorder(new EmptyBorder(10,10,10,10));
				
				myTBookPanel.add(myTBookBookPanel,BorderLayout.CENTER);
				myTBookPanel.add(bookButtonPane,BorderLayout.SOUTH);
				myTBookPanel.setBackground(lightc);
				myTSubPanel2.add(myTBookPanel);
				
				
				JPanel myTRecoPanel=new JPanel(new BorderLayout());
				JPanel myTRecoTitlePanel=new JPanel();
				JLabel myTRecoTitleLabel=new JLabel("Recommendation",JLabel.CENTER);
				myTRecoTitleLabel.setFont(new Font("Arial",Font.BOLD,16));
				myTRecoTitlePanel.add(myTRecoTitleLabel);
				myTRecoTitlePanel.setBackground(titlec);
				myTRecoPanel.add(myTRecoTitlePanel,BorderLayout.NORTH);
				
				JPanel recoContentPanel=new JPanel();
				FileReader recoFr=new FileReader("files/Recommend.csv");
				BufferedReader recoBr=new BufferedReader(recoFr);
				String recoLine=recoBr.readLine();
				boolean isRecoFound=false;
				String[] recoList=new String[2];
				for(int i1=0;i1<2;i1++){
					recoList[i1]="";
				}
				int j1=0;
				while(recoLine!=null) {
					String[] recoAttr=recoLine.split(",");
					if(recoAttr[0].equals(this.pairList[0])) {
						isRecoFound=true;
						for(;j1+1<recoAttr.length&&j1<2;j1++)
							recoList[j1]=recoAttr[j1+1];
						break;
					}
					recoLine=recoBr.readLine();
				}
				recoBr.close();
				recoFr.close();

				if(isRecoFound) {
					recoContentPanel.setLayout(new GridLayout(0,2));
					FileReader cvFr=new FileReader("files/Videos.csv");
					BufferedReader cvBr=new BufferedReader(cvFr);
					String vLine=cvBr.readLine();
					while(vLine!=null) {
						String[] vAttr=vLine.split(",");
						for(int i2=0;i2<j1;i2++) {
							if(recoList[i2].equals(vAttr[0])) {
								Video v=new Video(vAttr[1],vAttr[2],vAttr[3],vAttr[4],170,95,12,10);
								recoContentPanel.add(v.getPanel());
							}
						}
						vLine=cvBr.readLine();
					}
					cvBr.close();
					cvFr.close();
					recoContentPanel.setBorder(new EmptyBorder(0,5,15,5));
					recoContentPanel.setOpaque(false);
					myTRecoPanel.add(recoContentPanel,BorderLayout.CENTER);
				}
				else {
					recoContentPanel.setLayout(new GridLayout(5,0,5,5));
					JLabel collContentLabel=new JLabel("No recommendation found",JLabel.CENTER);
					recoContentPanel.add(collContentLabel);
					recoContentPanel.setOpaque(false);
					myTRecoPanel.add(recoContentPanel);
				}
				myTRecoPanel.setBackground(lightc);
				myTSubPanel2.add(myTRecoPanel);
				
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
	public JPanel getUTPanel() {
		return this.utPanel;
	}
	public void sendMessage(String msg) {
		try {
			RandomAccessFile raf=new RandomAccessFile("files/Chatting.csv", "rw");
			raf.seek(raf.length());
			raf.writeBytes(this.pairList[0]+",user,"+msg+"\n");
			raf.close();
		}
		catch(Exception e) {
			ErrorMessage em=new ErrorMessage();
			e.printStackTrace();
		}
	}
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==this.addTrainer) {
			AddTrainer at=new AddTrainer(this.userID);
		}
		else if(e.getSource()==this.send) {
			String message=this.chatbox.getText();
			sendMessage(message);
			this.utPanel.removeAll();
			this.utPanel.add(createMyTPane(),BorderLayout.CENTER);
			this.utPanel.setVisible(false);
			this.utPanel.setVisible(true);
		}
		else if(e.getSource()==this.book) {
			BookLive bl=new BookLive(this.pairList[0]);
		}
	}
}
