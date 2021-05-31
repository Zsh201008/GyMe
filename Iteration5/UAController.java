import java.io.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.*;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

//Display User Account Information
public class UAController implements ActionListener{
	private JPanel uaPanel;
	private String uName;
	private String uSex;
	private String uType;
	private String uRank;
	private int uClassNum;
	private int uDuration;
	private String uAvatar;
	private String thisID;
	private JButton bodyIQ;
	private JButton badgebt;
	private JButton upbt;
	private Color lightc=new Color(230,246,250);
	private Color titlec=new Color(207,236,245);
	public UAController(String userID) {
		this.thisID=userID;
		this.uaPanel=new JPanel();
		this.uaPanel.add(createPanel());
		this.uaPanel.setOpaque(false);
	}
	public JPanel createPanel() {
		JPanel resultPanel=new JPanel(new BorderLayout());
		try {
			JPanel namePanel=new JPanel(new GridLayout(4,0,0,0));
			FileReader fr=new FileReader("files/User.csv");
			BufferedReader br=new BufferedReader(fr);
			String newLine=br.readLine();
			while(newLine!=null) {
				String[] attributes=newLine.split(",");
				if(attributes[0].equals(this.thisID)) {
					this.uName=attributes[2];
					this.uSex=attributes[3];
					this.uRank=attributes[6];
					this.uType=attributes[7];
					this.uClassNum=Integer.parseInt(attributes[9]);
					this.uDuration=Integer.parseInt(attributes[10]);
					this.uAvatar=attributes[11];
					break;
				}
				newLine=br.readLine();
			}
			checkUpgrade();
			checkBadge();
			JPanel uaName=new JPanel(new GridLayout(0,4));
			BufferedImage bimage=ImageIO.read(new File("./imgs/User/"+this.uAvatar));
			ImageIcon uaAvatar=new ImageIcon();
			uaAvatar.setImage(bimage.getScaledInstance(150,150,Image.SCALE_DEFAULT));
			JLabel avatarLabel=new JLabel(uaAvatar);
			uaName.add(avatarLabel);
			
			JPanel namePanel1=new JPanel(new BorderLayout());
			JLabel nameLabel=new JLabel(this.uName);
			nameLabel.setFont(new Font("Arial",Font.BOLD,28));
			nameLabel.setOpaque(false);
			namePanel1.add(nameLabel,BorderLayout.WEST);
			BufferedImage buaSex;
			if(this.uSex.equals("M"))
				buaSex=ImageIO.read(new File("./imgs/UA/m.png"));
			else
				buaSex=ImageIO.read(new File("./imgs/UA/f.png"));
			ImageIcon sexIcon=new ImageIcon();
			sexIcon.setImage(buaSex.getScaledInstance(25,25,Image.SCALE_DEFAULT));
			JLabel sexLabel=new JLabel(sexIcon);
			sexLabel.setOpaque(false);
			namePanel1.add(sexLabel,BorderLayout.CENTER);
			namePanel1.setOpaque(false);
			namePanel.add(namePanel1);
			
			JLabel accNoLabel=new JLabel("   Account: "+this.thisID,JLabel.LEFT);
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
				typeDetail="Privilege";
			}
			JLabel typeLabel=new JLabel("Type: "+typeDetail);
			typeLabel.setFont(new Font("Arial",Font.BOLD,14));
			typePanel.add(typeLabel);
			JPanel rankPanel=new JPanel(new GridLayout(1,0,0,0));
			if(this.uRank.equals("Copper"))
				rankPanel.setBackground(new Color(240,222,222));
			else if(this.uRank.equals("Silver"))
				rankPanel.setBackground(new Color(250,250,250));
			else
				rankPanel.setBackground(new Color(254,246,208));
			JLabel rankLabel=new JLabel("Rank: "+this.uRank);
			rankLabel.setFont(new Font("Arial",Font.BOLD,14));
			rankPanel.add(rankLabel);
			namePanel3.add(typePanel);
			namePanel3.add(rankPanel);
			namePanel3.setOpaque(false);
			namePanel.add(namePanel3);
			
			JPanel badgePanel=new JPanel();
			badgebt=new JButton("View My Badges");
			badgebt.setBackground(titlec);
			badgebt.addActionListener(this);
			upbt=new JButton("Upgrade Account");
			upbt.setBackground(titlec);
			upbt.addActionListener(this);
			badgePanel.add(badgebt);
			badgePanel.add(upbt);
			badgePanel.setOpaque(false);
			namePanel.add(badgePanel);
			
			namePanel.setOpaque(false);
			uaName.add(namePanel);
			uaName.setOpaque(false);
			resultPanel.add(uaName,BorderLayout.NORTH);  
			
			JPanel infoPanel=new JPanel(new GridLayout(0,2,20,20));
			infoPanel.setOpaque(false);
			
			JPanel hisPanel=new JPanel(new BorderLayout());
			JPanel hisTitlePanel=new JPanel();
			hisTitlePanel.setBackground(titlec);
			JLabel hisTitleLabel=new JLabel("Workout History",JLabel.LEFT);
			hisTitleLabel.setFont(new Font("Arial",Font.BOLD,16));
			hisTitlePanel.add(hisTitleLabel);
			hisPanel.add(hisTitlePanel,BorderLayout.NORTH);
			
			FileReader hisFr=new FileReader("files/UserHistory.csv");
			BufferedReader hisBr=new BufferedReader(hisFr);
			String hisLine=hisBr.readLine();
			String[] hisInfo=new String[4];
			for(int i=0;i<4;i++)
				hisInfo[i]="";
			int j=0;
			boolean isHisFound=false;
			while(hisLine!=null&&j<4) {
				String[] hisAttr=hisLine.split(",");
				if(hisAttr[0].equals(this.thisID)) {
					hisInfo[j]=hisAttr[2]+", "+hisAttr[3];
					j++;
					isHisFound=true;
				}
				hisLine=hisBr.readLine();
			}
			hisBr.close();
			hisFr.close();
			
			JPanel hisContentPanel=new JPanel(new GridLayout(4,0,5,5));
			if(isHisFound) {
				JPanel hisContentPanel1=new JPanel();
				hisContentPanel1.setBackground(lightc);
				JLabel hisContentLabel1=new JLabel(hisInfo[0],JLabel.LEFT);
				hisContentPanel1.add(hisContentLabel1);
				hisContentPanel.add(hisContentPanel1);
				
				JPanel hisContentPanel2=new JPanel();
				hisContentPanel2.setBackground(lightc);
				JLabel hisContentLabel2=new JLabel(hisInfo[1],JLabel.LEFT);
				hisContentPanel2.add(hisContentLabel2);
				hisContentPanel.add(hisContentPanel2);
				
				JPanel hisContentPanel3=new JPanel();
				hisContentPanel3.setBackground(lightc);
				JLabel hisContentLabel3=new JLabel(hisInfo[2],JLabel.LEFT);
				hisContentPanel3.add(hisContentLabel3);
				hisContentPanel.add(hisContentPanel3);
				
				JPanel hisContentPanel4=new JPanel();
				hisContentPanel4.setBackground(lightc);
				JLabel hisContentLabel4=new JLabel(hisInfo[3],JLabel.LEFT);
				hisContentPanel4.add(hisContentLabel4);
				hisContentPanel.add(hisContentPanel4);
			}
			else {
				JPanel hisNotFoundPanel=new JPanel();
				hisNotFoundPanel.setBackground(lightc);
				JLabel hisNotFoundLabel=new JLabel("No workout history found",JLabel.LEFT);
				hisNotFoundPanel.add(hisNotFoundLabel);
				hisContentPanel.add(hisNotFoundPanel);
			}
			hisContentPanel.setBackground(lightc);
			hisContentPanel.setBorder(new EmptyBorder(5,0,5,0));
			hisPanel.add(hisContentPanel,BorderLayout.CENTER);
			hisPanel.setBackground(lightc);
			
			JPanel collPanel=new JPanel(new BorderLayout());
			JPanel collTitlePanel=new JPanel();
			collTitlePanel.setBackground(titlec);
			JLabel collTitleLabel=new JLabel("My Collection",JLabel.LEFT);
			collTitleLabel.setFont(new Font("Arial",Font.BOLD,16));
			collTitlePanel.add(collTitleLabel);
			
			JPanel collContentPanel=new JPanel();
			collContentPanel.setBackground(lightc);
			
			FileReader collFr=new FileReader("files/UserCollection.csv");
			BufferedReader collBr=new BufferedReader(collFr);
			String collLine=collBr.readLine();
			boolean isCollFound=false;
			String[] collList=new String[2];
			for(int i1=0;i1<2;i1++){
				collList[i1]="";
			}
			int j1=0;
			while(collLine!=null) {
				String[] collAttr=collLine.split(",");
				if(collAttr[0].equals(this.thisID)) {
					isCollFound=true;
					for(;j1+1<collAttr.length&&j1<2;j1++)
						collList[j1]=collAttr[j1+1];
					break;
				}
				collLine=collBr.readLine();
			}
			collBr.close();
			collFr.close();

			if(isCollFound) {
				collContentPanel.setLayout(new GridLayout(0,2));
				FileReader cvFr=new FileReader("files/Videos.csv");
				BufferedReader cvBr=new BufferedReader(cvFr);
				String vLine=cvBr.readLine();
				while(vLine!=null) {
					String[] vAttr=vLine.split(",");
					for(int i2=0;i2<j1;i2++) {
						if(collList[i2].equals(vAttr[0])) {
							Video v=new Video(this.thisID,vAttr[0],vAttr[1],vAttr[2],vAttr[3],vAttr[4],170,95);
							collContentPanel.add(v.getPanel());
						}
					}
					vLine=cvBr.readLine();
				}
				cvBr.close();
				cvFr.close();
				collPanel.add(collTitlePanel,BorderLayout.NORTH);
				collContentPanel.setBorder(new EmptyBorder(5,5,5,5));
				collPanel.add(collContentPanel,BorderLayout.CENTER);
			}
			else {
				collContentPanel.setLayout(new GridLayout(4,0,5,5));
				JLabel collContentLabel=new JLabel("No collection found",JLabel.CENTER);
				collContentPanel.add(collContentLabel);
				collPanel.add(collTitlePanel,BorderLayout.NORTH);
				collPanel.add(collContentPanel,BorderLayout.CENTER);
			}
			
			collPanel.setBackground(lightc);
			infoPanel.add(collPanel);
			infoPanel.setBorder(new EmptyBorder(30,30,30,30));
			
			JPanel bodyPanel=new JPanel(new BorderLayout());
			JPanel bodyTitlePanel=new JPanel();
			bodyTitlePanel.setBackground(titlec);
			JLabel bodyTitleLabel=new JLabel("My Body Insight",JLabel.LEFT);
			bodyTitleLabel.setFont(new Font("Arial",Font.BOLD,16));
			bodyTitlePanel.add(bodyTitleLabel);
			bodyPanel.add(bodyTitlePanel,BorderLayout.NORTH);
			
			JPanel bodySubPanel=new JPanel(new GridLayout(0,2,10,10));
			
			FileReader bodyFr=new FileReader("files/BodyInfo.csv");
			BufferedReader bodyBr=new BufferedReader(bodyFr);
			String bodyLine=bodyBr.readLine();
			String[] bodyList=new String[5];
			boolean bodyFound=false;
			while(bodyLine!=null) {
				String[] bodyAttr=bodyLine.split(",");
				if(bodyAttr[0].equals(this.thisID)) {
					bodyFound=true;
					bodyList[0]=bodyAttr[1];
					bodyList[1]=bodyAttr[2];
					bodyList[2]=bodyAttr[3];
					bodyList[3]=bodyAttr[4];
					bodyList[4]=bodyAttr[7];
					break;
				}
				bodyLine=bodyBr.readLine();
			}
			bodyBr.close();
			bodyFr.close();
			JPanel bodySPanel=new JPanel(new GridLayout(5,0,5,5));
			
			JLabel bodyContentLabel1;
			if(bodyFound) {
				bodyContentLabel1=new JLabel("Height: "+bodyList[0]+"cm",JLabel.LEFT);
				bodySPanel.add(bodyContentLabel1);
				JLabel bodyContentLabel2=new JLabel("Weight: "+bodyList[1]+"kg",JLabel.LEFT);
				bodySPanel.add(bodyContentLabel2);
				JLabel bodyContentLabel3=new JLabel("BMI: "+bodyList[2],JLabel.LEFT);
				bodySPanel.add(bodyContentLabel3);
				JLabel bodyContentLabel4=new JLabel("BF Rate: "+bodyList[3]+"%",JLabel.LEFT);
				bodySPanel.add(bodyContentLabel4);
				JLabel bodyContentLabel5=new JLabel("Physical Ability: "+bodyList[4], JLabel.LEFT);
				bodySPanel.add(bodyContentLabel5);
			}
			else {
				bodySPanel.setLayout(new GridLayout(3,0,5,5));
				bodyContentLabel1=new JLabel("No body info found",JLabel.LEFT);
				bodySPanel.add(bodyContentLabel1);
			}
			bodySPanel.setBackground(lightc);
			bodySPanel.setBorder(new EmptyBorder(0,20,0,20));
			
			JPanel bodyIPanel=new JPanel(new BorderLayout());
			BufferedImage bodyIstImg=ImageIO.read(new File("./imgs/UA/bodyinsight.png"));
			ImageIcon bodyIstIcon=new ImageIcon();
			bodyIstIcon.setImage(bodyIstImg.getScaledInstance(170,95,Image.SCALE_DEFAULT));
			JLabel bodyIstLabel=new JLabel(bodyIstIcon);
			bodyIPanel.add(bodyIstLabel,BorderLayout.CENTER);
			bodyIQ=new JButton("Create/Update Body Info");
			JPanel bodyIQPane=new JPanel();
			bodyIQ.addActionListener(this);
			bodyIQ.setBackground(new Color(215,240,247));
			bodyIQPane.add(bodyIQ);
			bodyIQPane.setBackground(lightc);
			bodyIPanel.add(bodyIQPane,BorderLayout.SOUTH);
			bodyIPanel.setBackground(lightc);
			bodyIPanel.setBorder(new EmptyBorder(10,0,10,0));
			
			bodySubPanel.add(bodyIPanel);
			bodySubPanel.add(bodySPanel);
			bodySubPanel.setBackground(lightc);
			bodyPanel.add(bodySubPanel,BorderLayout.CENTER);
			
			JPanel notiPanel=new JPanel(new BorderLayout());
			JPanel notiTitlePanel=new JPanel();
			notiTitlePanel.setBackground(titlec);
			JLabel notiTitleLabel=new JLabel("Notifications",JLabel.LEFT);
			notiTitleLabel.setFont(new Font("Arial",Font.BOLD,16));
			notiTitlePanel.add(notiTitleLabel);
			notiPanel.add(notiTitlePanel,BorderLayout.NORTH);
			
			JPanel notiContentPanel=new JPanel(new GridLayout(4,0,5,5));
			FileReader notiFr=new FileReader("files/UserNotification.csv");
			BufferedReader notiBr=new BufferedReader(notiFr);
			String notiLine=notiBr.readLine();
			String[] notiInfo=new String[4];
			for(int i=0;i<4;i++)
				notiInfo[i]="";
			int k=0;
			boolean isNotiFound=false;
			while(notiLine!=null&&k<4) {
				String[] notiAttr=notiLine.split(",");
				if(notiAttr[1].equals(this.thisID)) {
					String type=notiAttr[3];
					if(type.equals("type")) {
						notiInfo[k]="[Gym Assistant] Wow! You've been upgraded to "+notiAttr[4]+" user!";
					}
					else if(type.equals("upgrade")) {
						notiInfo[k]="[Gym Assistant] Congratulations! You've been upgraded to "+notiAttr[4]+"!";
					}
					else if(type.equals("badge")) {
						notiInfo[k]="[Gym Assistant] Surprise! You've got a new badge: "+notiAttr[4]+"!";
					}
					else {
						FileReader bookFr=new FileReader("files/Booking.csv");
						BufferedReader bookBr=new BufferedReader(bookFr);
						String bookLine=bookBr.readLine();
						String bookTime="";
						String bookPlatform="";
						while(bookLine!=null) {
							String[] bookAttr=bookLine.split(",");
							if(bookAttr[0].equals(notiAttr[4])) {
								bookTime=bookAttr[2];
								bookPlatform=bookAttr[3];
								break;
							}
							bookLine=bookBr.readLine();
						}
						bookBr.close();
						bookFr.close();
						if(type.equals("live")) {
							notiInfo[k]="[Live Session] You have a live session on "+bookTime+" at "+bookPlatform+".";
						}
						else if(type.equals("confirm")) {
							notiInfo[k]="[Live Session] Live session on "+bookTime+" at "+bookPlatform+" has been confirmed.";
						}
					}
					k++;
					isNotiFound=true;
				}
				notiLine=notiBr.readLine();
			}
			notiBr.close();
			notiFr.close();
			
			if(isNotiFound) {
				JLabel notiContentLabel1=new JLabel(notiInfo[0],JLabel.LEFT);
				notiContentPanel.add(notiContentLabel1);

				JLabel notiContentLabel2=new JLabel(notiInfo[1],JLabel.LEFT);
				notiContentPanel.add(notiContentLabel2);
				
				JLabel notiContentLabel3=new JLabel(notiInfo[2],JLabel.LEFT);
				notiContentPanel.add(notiContentLabel3);
				
				JLabel notiContentLabel4=new JLabel(notiInfo[3],JLabel.LEFT);
				notiContentPanel.add(notiContentLabel4);
			}
			else {
				JPanel notiNotFoundPanel=new JPanel();
				notiNotFoundPanel.setBackground(lightc);
				JLabel notiNotFoundLabel=new JLabel("No notification found",JLabel.LEFT);
				notiNotFoundPanel.add(notiNotFoundLabel);
				notiContentPanel.add(notiNotFoundPanel);
			}
			notiContentPanel.setBorder(new EmptyBorder(5,15,10,10));
			notiContentPanel.setBackground(lightc);
			notiPanel.add(notiContentPanel,BorderLayout.CENTER);
			notiPanel.setBackground(lightc);
			
			JPanel hisSPanel=new JPanel(new GridLayout(0,2,20,20));
			hisSPanel.setOpaque(false);
			hisSPanel.add(hisPanel);
			hisSPanel.add(notiPanel);
			hisSPanel.setBorder(new EmptyBorder(20,30,30,30));
			
			infoPanel.add(bodyPanel);
			infoPanel.add(collPanel);
			infoPanel.setBorder(new EmptyBorder(30,30,0,30));
			
			resultPanel.add(infoPanel,BorderLayout.CENTER);    //
			resultPanel.add(hisSPanel,BorderLayout.SOUTH);
			resultPanel.setBorder(new EmptyBorder(20,20,20,20));
			
			resultPanel.setOpaque(false);
		}
		catch(Exception e) {
			ErrorMessage er=new ErrorMessage();
			e.printStackTrace();
		}
		return resultPanel;
	}
	public void checkUpgrade() {
		if(uClassNum>=10 || uDuration>=1200) {
			if(!uRank.equals("Golden")) {
				this.uRank="Golden";
				upgradeRank("Golden");
				notifyRank("Golden");
			}
		}
		else if(uClassNum>=5 || uDuration>=600) {
			if(!uRank.equals("Silver")) {
				this.uRank="Silver";
				upgradeRank("Silver");
				notifyRank("Silver");
			}
		}
	}
	public void upgradeRank(String newrank) {
		try {
			FileReader fr=new FileReader("files/User.csv");
			BufferedReader br=new BufferedReader(fr);
			String[] tempUser=new String[1000];
			int count=0;
			String line=br.readLine();
			while(line!=null) {
				String[] attr=line.split(",");
				tempUser[count]="";
				if(attr[0].equals(this.thisID)) {
					attr[6]=newrank;
				}
				for(int j=0;j<11;j++)
					tempUser[count]=tempUser[count]+attr[j]+",";
				tempUser[count]=tempUser[count]+attr[11];
				count++;
				line=br.readLine();
			}
			br.close();
			fr.close();
			
			FileWriter fw=new FileWriter("files/User.csv");
			BufferedWriter bw=new BufferedWriter(fw);
			for(int k=0;k<count;k++)
				bw.write(tempUser[k]+"\n");
			bw.close();
			fw.close();
		}
		catch(Exception e) {
			ErrorMessage em=new ErrorMessage();
			e.printStackTrace();
		}
	}
	public void notifyRank(String newrank) {
		try {
			int nid=(int)(Math.random()*10000)+10000;
			String notiID="n"+nid;
			String wStr=notiID+","+this.thisID+",null,upgrade,"+newrank.toUpperCase();
			NotiToFile ntf=new NotiToFile("user",wStr);
			boolean isOK=ntf.notiToFile();
			if(!isOK) {
				ErrorMessage em=new ErrorMessage();
			}
		}
		catch(Exception e) {
			ErrorMessage em=new ErrorMessage();
			e.printStackTrace();
		}
	}
	public void notifyBadge(int b) {
		try {
			String gName="";
			b=60001+b;
			String gid="g"+b;
			FileReader fr=new FileReader("files/Badge.csv");
			BufferedReader br=new BufferedReader(fr);
			String line=br.readLine();
			while(line!=null) {
				String[] attr=line.split(",");
				if(attr[0].equals(gid)) {
					gName=attr[2];
					break;
				}
				line=br.readLine();
			}
			br.close();
			fr.close();
			
			RandomAccessFile raf=new RandomAccessFile("files/UserBadges.csv", "rw");
			raf.seek(raf.length());
			raf.writeBytes(this.thisID+","+gid+"\n");
			raf.close();
			
			int nid=(int)(Math.random()*10000)+10000;
			String notiID="n"+nid;
			String wStr=notiID+","+this.thisID+",null,badge,"+gName;
			NotiToFile ntf=new NotiToFile("user",wStr);
			boolean isOK=ntf.notiToFile();
			if(!isOK) {
				ErrorMessage em=new ErrorMessage();
			}
		}
		catch(Exception e) {
			ErrorMessage em=new ErrorMessage();
			e.printStackTrace();
		}
	}
	public void checkBadge() {
		try {
			int[] badges=new int[5];
			for(int i=0;i<5;i++)
				badges[i]=0;
			FileReader fr=new FileReader("files/UserHistory.csv");
			BufferedReader br=new BufferedReader(fr);
			String line=br.readLine();
			while(line!=null) {
				String[] attr=line.split(",");
				if(attr[0].equals(this.thisID)) {
					if(attr[1].equals("v20801")||attr[1].equals("v20802"))
						badges[0]++;
					else if(attr[1].equals("v20803")||attr[1].equals("v20804"))
						badges[1]++;
					else if(attr[1].equals("v20805")||attr[1].equals("v20806"))
						badges[2]++;
					else if(attr[1].equals("v20807")||attr[1].equals("v20809"))
						badges[3]++;
					else if(attr[1].equals("v20808"))
						badges[4]++;
				}
				line=br.readLine();
			}
			br.close();
			fr.close();
			for(int k=0;k<5;k++) {
				if(badges[k]>=3) {
					notifyBadge(k);
				}
			}
		}
		catch(Exception e) {
			ErrorMessage em=new ErrorMessage();
			e.printStackTrace();
		}
	}
	public JPanel getUAPanel() {
		return this.uaPanel;
	}
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==this.bodyIQ) {
			BodyInfo bi=new BodyInfo(this.thisID);
		}
		if(e.getSource()==this.badgebt) {
			UserBadge ub=new UserBadge(this.thisID);
		}
		if(e.getSource()==this.upbt) {
			Confirm cf=new Confirm(this.thisID,this.uType);
		}
	}
	public class Confirm extends JFrame implements ActionListener{
		private String userID;
		private JButton jbtOK;
		private boolean isUpgrade=false;
		public Confirm(String userID, String uType) {
			this.userID=userID;
			String text1="";
			String text2="";
			String buttonText="";
			if(uType.equals("P")) {
				text1="You are already";
				text2="Privilage user!";
				buttonText="OK";
				this.isUpgrade=false;
			}	
			else {
				text1="Are you sure to";
				text2="upgrade your account?";
				buttonText="Yes";
				this.isUpgrade=true;
			}
			JPanel messagePanel=new JPanel(new GridLayout(3,0,0,0));
			
			JLabel exContent=new JLabel(text1,JLabel.CENTER);
			messagePanel.add(exContent);
			JLabel textLabel=new JLabel(text2,JLabel.CENTER);
			messagePanel.add(textLabel);
			
			JPanel buttonPanel=new JPanel(new FlowLayout());
			jbtOK=new JButton(buttonText);
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
		public void upgradeAccount() {
			try {
				FileReader fr=new FileReader("files/User.csv");
				BufferedReader br=new BufferedReader(fr);
				String[] tempUser=new String[1000];
				int count=0;
				String line=br.readLine();
				while(line!=null) {
					String[] attr=line.split(",");
					tempUser[count]="";
					if(attr[0].equals(this.userID)) {
						attr[7]="P";
					}
					for(int j=0;j<11;j++)
						tempUser[count]=tempUser[count]+attr[j]+",";
					tempUser[count]=tempUser[count]+attr[11];
					count++;
					line=br.readLine();
				}
				br.close();
				fr.close();
				
				FileWriter fw=new FileWriter("files/User.csv");
				BufferedWriter bw=new BufferedWriter(fw);
				for(int k=0;k<count;k++)
					bw.write(tempUser[k]+"\n");
				bw.close();
				fw.close();
				
				int nid=(int)(Math.random()*10000)+10000;
				String notiID="n"+nid;
				String wString=notiID+","+this.userID+",null,type,privilege";
				NotiToFile ntf=new NotiToFile("user",wString);
				boolean isOK=ntf.notiToFile();
				if(isOK) {
					Success s=new Success("Upgraded to Privilege User");
				}
				
			}
			catch(Exception e) {
				ErrorMessage em=new ErrorMessage();
				e.printStackTrace();
			}
		}
		public void actionPerformed(ActionEvent e){
			if(e.getSource()==jbtOK){
				if(isUpgrade) {
					upgradeAccount();
				}
				uaPanel.removeAll();
				uaPanel.add(createPanel());
				uaPanel.setVisible(false);
				uaPanel.setVisible(true);
				dispose();
			}
		}
	}
	public class BodyInfo extends JFrame implements ActionListener{
		private JButton submit;
		private JTextField height;
		private JTextField weight;
		private JTextField bfrate;
		private JTextField focus;
		private JTextField requirement;
		private JCheckBox phy1;
		private JCheckBox phy2;
		private JCheckBox phy3;
		private int phyCount=0;
		private String phyLevel="";
		private String phyAbility="";
		private String thisID;
		public BodyInfo(String userID) {
			this.thisID=userID;
			JPanel insertPane=new JPanel(new GridLayout(7,0,5,5));

			JPanel heightPane=new JPanel();
			JLabel heightLabel=new JLabel("Your height (cm): ",JLabel.CENTER);
			this.height=new JTextField(7);
			heightPane.add(heightLabel);
			heightPane.add(this.height);
			insertPane.add(heightPane);
			
			JPanel weightPane=new JPanel();
			JLabel weightLabel=new JLabel("Your weight (kg): ",JLabel.CENTER);
			this.weight=new JTextField(7);
			weightPane.add(weightLabel);
			weightPane.add(this.weight);
			insertPane.add(weightPane);
			
			JPanel bfPane=new JPanel();
			JLabel bfLabel=new JLabel("Your Body-Fat Rate (%): ",JLabel.CENTER);
			this.bfrate=new JTextField(7);
			bfPane.add(bfLabel);
			bfPane.add(this.bfrate);
			insertPane.add(bfPane);
			
			JPanel focusPane=new JPanel();
			JLabel focusLabel=new JLabel("Your focus fields: ",JLabel.CENTER);
			this.focus=new JTextField(30);
			focusPane.add(focusLabel);
			focusPane.add(this.focus);
			insertPane.add(focusPane);
			
			JPanel reqPane=new JPanel();
			JLabel reqLabel=new JLabel("Your requirement: ",JLabel.CENTER);
			this.requirement=new JTextField(30);
		    reqPane.add(reqLabel);
			reqPane.add(this.requirement);
			insertPane.add(reqPane);
			
			JPanel phyPane=new JPanel(new GridLayout(2,0));
			JLabel phyLabel=new JLabel("Pick following exercises you can do in one go: ",JLabel.CENTER);
			JPanel phyContentPane=new JPanel();
			phy1=new JCheckBox("30 Squat");
			phy2=new JCheckBox("2000m Run");
			phy3=new JCheckBox("20 Push-up");
			phy1.addActionListener(this);
			phy2.addActionListener(this);
			phy3.addActionListener(this);
			phyContentPane.add(phy1);
			phyContentPane.add(phy2);
			phyContentPane.add(phy3);
			phyPane.add(phyLabel);
			phyPane.add(phyContentPane);
			phyPane.setBorder(new EmptyBorder(0,0,15,0));
			insertPane.add(phyPane);

			JPanel buttonPane=new JPanel();
			this.submit=new JButton("Submit Your Info");
			this.submit.addActionListener(this);
			buttonPane.add(this.submit);
			buttonPane.setOpaque(false);
			insertPane.add(buttonPane);
			insertPane.setBorder(new EmptyBorder(20,0,0,0));
			
			Image icon;
			ImageIcon ig = new ImageIcon("imgs/icon.jpg");
			icon = ig.getImage();
			setIconImage(icon);
			
			Container content=this.getContentPane();
			JPanel myPanel=(JPanel)content;
			myPanel.setOpaque(false);
			myPanel.add(insertPane);
			setTitle("Create/Update Your Body Info");
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			setSize(500,550);
			setLocation(300,200);
			setVisible(true);
		}
		public boolean writeBodyInfo() {
			String sHeight=this.height.getText();
			String sWeight=this.weight.getText();
			String sBFRate=this.bfrate.getText();
			String focus=this.focus.getText();
			String requirement=this.requirement.getText();
			try {
				FileReader fr1=new FileReader("files/BodyInfo.csv");
				BufferedReader br1=new BufferedReader(fr1);
				String bodyLine=br1.readLine();
				String[] bodyAttr=new String[9];
				boolean isFound=false;
				while(bodyLine!=null) {
					bodyAttr=bodyLine.split(",");
					if(bodyAttr[0].equals(this.thisID)) {
						isFound=true;
						break;
					}
					bodyLine=br1.readLine();
				}
				if(isFound==false) {
					bodyAttr[0]=this.thisID;
					for(int m=1;m<9;m++)
						bodyAttr[m]="--";
				}
				int height=-1;
				int weight=-1;
				if(!sHeight.equals("")) {
					height=Integer.parseInt(sHeight);
					bodyAttr[1]=sHeight;
				}
				if(!sWeight.equals("")) {
					weight=Integer.parseInt(sWeight);
					bodyAttr[2]=sWeight;
				}
				if(!sBFRate.equals(""))
					bodyAttr[4]=sBFRate;
				if(!focus.equals(""))
					bodyAttr[5]=focus;
				if(!requirement.equals(""))
					bodyAttr[6]=requirement;
				
				if(height==-1) {
					if(!bodyAttr[1].equals("--")) {
						height=Integer.parseInt(bodyAttr[1]);
					}
				}
				if(weight==-1) {
					if(!bodyAttr[2].equals("--")) {
						weight=Integer.parseInt(bodyAttr[2]);
					}
				}
				if(height!=-1&&weight!=-1) {
					float bmi=(float)(weight/(height/100.0)/(height/100.0));
					bodyAttr[3]=bmi+"";
				}
				checkPhyAbility();
				boolean isChange=true;
				if(this.phyLevel.equals("low")) {
					if(!bodyAttr[7].equals("low")) {
						isChange=false;
					}
				}
				if(isChange) {
					bodyAttr[7]=this.phyLevel;
					bodyAttr[8]=this.phyAbility;
				}
				String wString="";
				for(int n=0;n<8;n++)
					wString=wString+bodyAttr[n]+",";
				wString=wString+bodyAttr[8];
				
				String[] temp=new String[1000];
				temp[0]=wString;
				int count=1;
				FileReader fr=new FileReader("files/BodyInfo.csv");
				BufferedReader br=new BufferedReader(fr);
				String line=br.readLine();
				while(line!=null) {
					String[] attr=line.split(",");
					if(!attr[0].equals(this.thisID)) {
						temp[count]=line;
						count++;
					}
					line=br.readLine();
				}
				br.close();
				fr.close();
				FileWriter fw=new FileWriter("files/BodyInfo.csv");
				BufferedWriter bw=new BufferedWriter(fw);
				for(int j=0;j<count;j++)
					bw.write(temp[j]+"\n");
				bw.close();
				fw.close();
				return true;
			}
			catch(Exception e) {
				ErrorMessage em=new ErrorMessage();
				e.printStackTrace();
				return false;
			}
		}
		public void checkPhyAbility() {
			if(phy1.isSelected()==true) {
				phyCount++;
				phyAbility+=phy1.getText()+". ";
			}	
			if(phy2.isSelected()==true) {
				phyCount++;
				phyAbility+=phy2.getText()+". ";
			}	
			if(phy3.isSelected()==true) {
				phyCount++;
				phyAbility+=phy3.getText()+". ";
			}

			if(phyCount==0) {
				phyLevel="low";	
				phyAbility="nothing";
			}
			else if(phyCount==1)
				phyLevel="medium";
			else if(phyCount==2)
				phyLevel="high";
			else if(phyCount==3)
				phyLevel="super";
		}
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==phy1) {
				boolean is=phy1.isSelected();
				phy1.setSelected(is);
			}
			else if(e.getSource()==phy2) {
				boolean is=phy2.isSelected();
				phy2.setSelected(is);
			}
			else if(e.getSource()==phy3) {
				boolean is=phy3.isSelected();
				phy3.setSelected(is);
			}
			if(e.getSource()==this.submit) {
				boolean isDone=writeBodyInfo();
				Success s=null;
				if(isDone) {
					s=new Success("New body info created");
					uaPanel.removeAll();
					uaPanel.add(createPanel());
					uaPanel.setVisible(false);
					uaPanel.setVisible(true);
				}
			}
		}
	}
}
