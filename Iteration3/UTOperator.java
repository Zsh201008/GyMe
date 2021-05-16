import java.io.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.awt.*;
import java.util.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class UTOperator implements ActionListener {
	private JPanel utPanel;
	private int NUM=5;
	private int MAX_TRAINER=5;
	private String userID;
	private ArrayList<String> trainerIDs;
	private Color lightc=new Color(230,246,250);
	private Color titlec=new Color(215,240,247);
	private JButton send;
	private JTextField chatbox;
	private String[] pairList;
	private JButton addTrainer;
	private JButton book;
	private int trainerNum;
	private int currentTrainer;
	private JButton viewTrainer;
	private JButton deleteTrainer;
	public UTOperator(String ID,String[] pair) {
		this.userID=ID;
		this.pairList=pair;
		this.trainerIDs=new ArrayList<String>(MAX_TRAINER);
		this.trainerNum=0;
		this.currentTrainer=0;
		this.utPanel=new JPanel(new BorderLayout(10,10));
		try {			
			FileReader fr2=new FileReader("files/UserTrainer.csv");
			BufferedReader br2=new BufferedReader(fr2);
			String newLine2=br2.readLine();
			while(newLine2!=null&&trainerNum<MAX_TRAINER) {
				String[] attributes=newLine2.split(",");
				if(attributes[1].equals(this.userID)) {
					this.trainerIDs.add(attributes[2]);
					trainerNum++;
				}
				newLine2=br2.readLine();
			}
			br2.close();
			fr2.close();
			
			this.utPanel.add(createMyTPane(trainerIDs.get(currentTrainer)),BorderLayout.CENTER);
			this.utPanel.setOpaque(false);
			this.utPanel.setBorder(new EmptyBorder(15,15,15,15));
		}
		catch(Exception e) {
			ErrorMessage em=new ErrorMessage();
			e.printStackTrace();
		}
	}
	public void increTrainer() {
		if(currentTrainer==trainerNum-1)
			currentTrainer=0;
		else
			currentTrainer++;
	}
	public void decreTrainer() {
		this.trainerNum--;
		this.trainerIDs.remove(currentTrainer);
	}
	public JPanel createMyTPane(String tID) {
		JPanel myTPanel=new JPanel(new BorderLayout());
		try {
			JPanel myTTitlePanel=new JPanel();
			JLabel myTTitleLabel=new JLabel("My Trainer");
			myTTitleLabel.setFont(new Font("Arial",Font.BOLD,16));
			myTTitlePanel.add(myTTitleLabel);
			myTTitlePanel.setBackground(titlec);
			myTPanel.add(myTTitlePanel,BorderLayout.NORTH);
			
			JPanel myTDetailPanel=new JPanel(new BorderLayout());
			JPanel myTDetailPanel1=new JPanel(new BorderLayout(5,5));
			JPanel myTDetailPanel12=new JPanel(new GridLayout(5,0));
			
			JPanel buttonPane=new JPanel();
			this.addTrainer=new JButton("Select/Add New Trainer");
			this.addTrainer.addActionListener(this);
			this.addTrainer.setBackground(titlec);
			buttonPane.add(this.addTrainer);
			buttonPane.setOpaque(false);
			
			if(this.trainerNum==0) {
				JPanel noTrainerPanel=new JPanel();
				JLabel noTrainerLabel=new JLabel("No trainer found",JLabel.CENTER);
				noTrainerPanel.add(noTrainerLabel);
				noTrainerPanel.setOpaque(false);
				noTrainerPanel.setBorder(new EmptyBorder(10,10,10,10));
				noTrainerPanel.setPreferredSize(new Dimension(800,45));
				myTPanel.add(noTrainerPanel,BorderLayout.CENTER);
				myTPanel.add(buttonPane,BorderLayout.SOUTH);
			}
			else {
				String tName="";
				String tType="";
				String tImg="";
				JPanel myTSubPanel=new JPanel(new GridLayout(2,0,15,15));
				JPanel myTSubPanel1=new JPanel(new GridLayout(0,2,15,15));
				JPanel myTSubPanel2=new JPanel(new GridLayout(0,2,15,15));
				FileReader fr3=new FileReader("files/Trainer.csv");
				BufferedReader br3=new BufferedReader(fr3);
				String newLine3=br3.readLine();
				while(newLine3!=null) {
					String[] attributes2=newLine3.split(",");
					if(attributes2[0].equals(tID)) {
						tName=attributes2[1];
						tType=attributes2[3];
						tImg=attributes2[4];
						break;
					}
					newLine3=br3.readLine();
				}
				br3.close();
				fr3.close();
				BufferedImage tImage=ImageIO.read(new File("imgs/Trainer/"+tImg));
				ImageIcon utIcon=new ImageIcon();
				utIcon.setImage(tImage.getScaledInstance(190,190,Image.SCALE_DEFAULT));
				JLabel udLabel=new JLabel(utIcon);
				myTDetailPanel1.add(udLabel,BorderLayout.CENTER);
				myTDetailPanel1.add(this.addTrainer,BorderLayout.SOUTH);
				
				JPanel viewButtonPane=new JPanel();
				this.viewTrainer=new JButton(" view next trainer ");
				this.viewTrainer.addActionListener(this);
				this.viewTrainer.setBackground(titlec);
				viewButtonPane.add(this.viewTrainer);
				viewButtonPane.setOpaque(false);
				
				JPanel deletePane=new JPanel();
				this.deleteTrainer=new JButton("delete this trainer");
				this.deleteTrainer.addActionListener(this);
				this.deleteTrainer.setBackground(titlec);
				deletePane.add(this.deleteTrainer);
				deletePane.setOpaque(false);
				
				JLabel trainerNameLabel=new JLabel(tName,JLabel.CENTER);
				trainerNameLabel.setFont(new Font("Arial",Font.BOLD,20));
				JLabel trainerIDLabel=new JLabel("ID: "+tID,JLabel.CENTER);
				trainerIDLabel.setFont(new Font("Arial",Font.BOLD,13));
				JLabel trainerTypeLabel=new JLabel("Focus: "+tType,JLabel.CENTER);
				trainerTypeLabel.setFont(new Font("Arial",Font.BOLD,13));
				myTDetailPanel12.add(trainerNameLabel);
				myTDetailPanel12.add(trainerIDLabel);
				myTDetailPanel12.add(trainerTypeLabel);
				
				myTDetailPanel12.add(viewButtonPane);
				myTDetailPanel12.add(deletePane);
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
					if(chatAttr[0].equals(this.pairList[currentTrainer])) {
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
					if(bookAttr[1].equals(this.pairList[currentTrainer])) {
						if(bookAttr[5].equals("on")) {
							booking[bookCount]=bookAttr[2]+", Platform: "+bookAttr[3]+", Room: "+bookAttr[4];
							LiveSession ls=new LiveSession(booking[bookCount],bookAttr[0],"cancel");
							myTBookBookPanel.add(ls.getPanel());
							bookCount++;
						}
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
				while(recoLine!=null&&j1<2) {
					String[] recoAttr=recoLine.split(",");
					if(recoAttr[0].equals(this.pairList[currentTrainer])) {
						isRecoFound=true;
						recoList[j1]=recoAttr[1];
						j1++;
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
								Video v=new Video(vAttr[1],vAttr[2],vAttr[3],vAttr[4],180,100,13,12);
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
			raf.writeBytes(this.pairList[currentTrainer]+",user,"+msg+"\n");
			raf.close();
		}
		catch(Exception e) {
			ErrorMessage em=new ErrorMessage();
			e.printStackTrace();
		}
	}
	public boolean checkCanAdd() {
		boolean canAdd=false;
		try {
			FileReader fr=new FileReader("files/User.csv");
			BufferedReader br=new BufferedReader(fr);
			String userLine=br.readLine();
			while(userLine!=null) {
				String[] userAttr=userLine.split(",");
				if(userAttr[0].equals(this.userID)) {
					if(userAttr[7].equals("P")) {
						canAdd=true;
						break;
					}
				}
				userLine=br.readLine();
			}
			br.close();
			fr.close();
		}
		catch(Exception ex) {
			ErrorMessage em=new ErrorMessage();
			ex.printStackTrace();
		}
		return canAdd;
	}
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==this.addTrainer) {
			if(checkCanAdd()) {
				AddTrainer at=new AddTrainer(this.userID);
			}
			else {
				ErrorMessage em=new ErrorMessage("You are 'Common-type' user,","you have no access to add trainer!");
			}
		}
		else if(e.getSource()==this.send) {
			String message=this.chatbox.getText();
			sendMessage(message);
			this.utPanel.removeAll();
			this.utPanel.add(createMyTPane(trainerIDs.get(currentTrainer)),BorderLayout.CENTER);
			this.utPanel.setVisible(false);
			this.utPanel.setVisible(true);
		}
		else if(e.getSource()==this.book) {
			BookLive bl=new BookLive(this.pairList[currentTrainer]);
		}
		else if(e.getSource()==this.viewTrainer) {
			increTrainer();
			this.utPanel.removeAll();
			this.utPanel.add(createMyTPane(trainerIDs.get(currentTrainer)),BorderLayout.CENTER);
			this.utPanel.setVisible(false);
			this.utPanel.setVisible(true);
		}
		else if(e.getSource()==this.deleteTrainer) {
			Confirm c=new Confirm(this.userID,this.pairList[currentTrainer]);
		}
	}
	public class Confirm extends JFrame implements ActionListener{
		private JButton jbtOK;
		private String pairID;
		private int MAX_UT=1000;
		public Confirm(String userID, String pID) {
			this.pairID=pID;
			JPanel messagePanel=new JPanel(new GridLayout(3,0,0,0));
			
			JLabel exContent=new JLabel("Are you sure to",JLabel.CENTER);
			messagePanel.add(exContent);
			JLabel textLabel=new JLabel("delete this trainer?",JLabel.CENTER);
			messagePanel.add(textLabel);
			
			JPanel buttonPanel=new JPanel(new FlowLayout());
			jbtOK=new JButton("YES");
			jbtOK.setSize(50,30);
			jbtOK.addActionListener(this);
			buttonPanel.add(jbtOK);
			messagePanel.add(buttonPanel);
			getContentPane().add(messagePanel);
			setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			setSize(230,150);
			setLocation(350,350);
			setVisible(true);
		}
		public void deleteTrainer() {
			try {
				String[] utContent=new String[MAX_UT];
				int utCount=0;
				FileReader fr=new FileReader("files/UserTrainer.csv");
				BufferedReader br=new BufferedReader(fr);
				String utLine=br.readLine();
				while(utLine!=null) {
					String[] utAttr=utLine.split(",");
					if(!utAttr[0].equals(this.pairID)) {
						utContent[utCount]=utLine;
						utCount++;
					}
					utLine=br.readLine();
				}
				br.close();
				fr.close();
				
				FileWriter fw=new FileWriter("files/UserTrainer.csv");
				BufferedWriter bw=new BufferedWriter(fw);
				for(int i=0;i<utCount;i++)
					bw.write(utContent[i]+"\n");
				bw.close();
				fw.close();
				Success s=new Success("Trainer Deleted");
			}
			catch(Exception e) {
				ErrorMessage em=new ErrorMessage();
				e.printStackTrace();
			}
		}
		public void actionPerformed(ActionEvent e){
			if(e.getSource()==this.jbtOK) {
				deleteTrainer();
				decreTrainer();
				utPanel.removeAll();
				utPanel.add(createMyTPane(trainerIDs.get(currentTrainer)),BorderLayout.CENTER);
				utPanel.setVisible(false);
				utPanel.setVisible(true);
				dispose();
			}
		}
	}
	public class LiveSession implements ActionListener{
		private JPanel bookPanel;
		private JButton button;
		private String bookID;
		private String opType;
		
		public LiveSession(String booking, String id, String type) {
			this.opType=type;
			this.bookID=id;
			this.bookPanel=new JPanel(new BorderLayout());
			JLabel bookLabel=new JLabel(booking,JLabel.LEFT);
			if(type.equals("cancel")) {
				this.button=new JButton("cancel");
			}
			else if(type.equals("confirm")) {
				this.button=new JButton("confirm");
			}
			this.button.setBackground(new Color(215,240,247));
			this.button.addActionListener(this);
			this.bookPanel.add(bookLabel,BorderLayout.CENTER);
			this.bookPanel.add(button,BorderLayout.EAST);
			this.bookPanel.setOpaque(false);
		}
		public JPanel getPanel() {
			return this.bookPanel;
		}
		public void confirmBooking() {
			System.out.println("booking confirmed");
		}
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==this.button&&this.opType.equals("cancel")) {
				ConfirmCancel cc=new ConfirmCancel(this.bookID);
			}
			else if(e.getSource()==this.button&&this.opType.equals("confirm")) {
				confirmBooking();
			}
		}
		public class ConfirmCancel extends JFrame implements ActionListener{
			private String bookID;
			private JButton jbtOK;
			private int MAX_BOOKING=1000;
			public ConfirmCancel(String bookID) {
				this.bookID=bookID;
				JPanel messagePanel=new JPanel(new GridLayout(3,0,0,0));
				
				JLabel exContent=new JLabel("Are you sure to",JLabel.CENTER);
				messagePanel.add(exContent);
				JLabel textLabel=new JLabel("cancel this live session?",JLabel.CENTER);
				messagePanel.add(textLabel);
				
				JPanel buttonPanel=new JPanel(new FlowLayout());
				jbtOK=new JButton("YES");
				jbtOK.setSize(50,30);
				jbtOK.addActionListener(this);
				buttonPanel.add(jbtOK);
				messagePanel.add(buttonPanel);
				getContentPane().add(messagePanel);
				setDefaultCloseOperation(DISPOSE_ON_CLOSE);
				setSize(230,150);
				setLocation(350,350);
				setVisible(true);
			}
			public void cancelBooking() {
				try {
					FileReader fr=new FileReader("files/Booking.csv");
					BufferedReader br=new BufferedReader(fr);
					String[] bookContent=new String[MAX_BOOKING];
					int bookCount=0;
					String bookLine=br.readLine();
					while(bookLine!=null&&bookCount<MAX_BOOKING) {
						String[] bookAttr=bookLine.split(",");
						if(!this.bookID.equals(bookAttr[0])) {
							bookContent[bookCount]=bookLine;
							bookCount++;
						}
						bookLine=br.readLine();
					}
					br.close();
					fr.close();
					
					FileWriter fw=new FileWriter("files/Booking.csv");
					BufferedWriter bw=new BufferedWriter(fw);
					for(int i=0;i<bookCount;i++)
						bw.write(bookContent[i]+"\n");
					bw.close();
					fw.close();
					Success s=new Success("Live Session Canceled");
				}
				catch(Exception e) {
					ErrorMessage em=new ErrorMessage();
					e.printStackTrace();
				}
			}
			public void actionPerformed(ActionEvent e){
				if(e.getSource()==this.jbtOK) {
					cancelBooking();
					utPanel.removeAll();
					utPanel.add(createMyTPane(trainerIDs.get(currentTrainer)),BorderLayout.CENTER);
					utPanel.setVisible(false);
					utPanel.setVisible(true);
					dispose();
				}
			}
		}
	}
}
