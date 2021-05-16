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
	private String thisID;
	private JButton bodyIQ;
	private Color color0=new Color(230,246,250);
	public UAOperator(String userID) {
		this.thisID=userID;
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
			
			FileReader hisFr=new FileReader("files/UserHistory.csv");
			BufferedReader hisBr=new BufferedReader(hisFr);
			String hisLine=hisBr.readLine();
			String[] hisInfo=new String[3];
			for(int i=0;i<3;i++)
				hisInfo[i]="";
			int j=0;
			boolean isHisFound=false;
			while(hisLine!=null&&j<3) {
				String[] hisAttr=hisLine.split(",");
				if(hisAttr[0].equals(this.thisID)) {
					hisInfo[j]=hisAttr[1]+", "+hisAttr[2];
					j++;
					isHisFound=true;
				}
				hisLine=hisBr.readLine();
			}
			hisBr.close();
			hisFr.close();
			
			if(isHisFound) {
				JPanel hisContentPanel1=new JPanel();
				hisContentPanel1.setBackground(color0);
				JLabel hisContentLabel1=new JLabel(hisInfo[0],JLabel.LEFT);
				hisContentPanel1.add(hisContentLabel1);
				hisPanel.add(hisContentPanel1);
				
				JPanel hisContentPanel2=new JPanel();
				hisContentPanel2.setBackground(color0);
				JLabel hisContentLabel2=new JLabel(hisInfo[1],JLabel.LEFT);
				hisContentPanel2.add(hisContentLabel2);
				hisPanel.add(hisContentPanel2);
				
				JPanel hisContentPanel3=new JPanel();
				hisContentPanel3.setBackground(color0);
				JLabel hisContentLabel3=new JLabel(hisInfo[2],JLabel.LEFT);
				hisContentPanel3.add(hisContentLabel3);
				hisPanel.add(hisContentPanel3);
			}
			else {
				JPanel hisNotFoundPanel=new JPanel();
				hisNotFoundPanel.setBackground(color0);
				JLabel hisNotFoundLabel=new JLabel("No workout history found",JLabel.LEFT);
				hisNotFoundPanel.add(hisNotFoundLabel);
				hisPanel.add(hisNotFoundPanel);
			}
			
			hisPanel.setBackground(color0);
			
			JPanel collPanel=new JPanel();
			JPanel collTitlePanel=new JPanel();
			collTitlePanel.setBackground(new Color(215,240,247));
			JLabel collTitleLabel=new JLabel("My Collection",JLabel.LEFT);
			collTitleLabel.setFont(new Font("Arial",Font.BOLD,16));
			collTitlePanel.add(collTitleLabel);
			
			JPanel collContentPanel=new JPanel();
			collContentPanel.setBackground(new Color(230,246,250));
			
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
				collPanel.setLayout(new BorderLayout());
				collContentPanel.setLayout(new GridLayout(0,2));
				FileReader cvFr=new FileReader("files/Videos.csv");
				BufferedReader cvBr=new BufferedReader(cvFr);
				String vLine=cvBr.readLine();
				while(vLine!=null) {
					String[] vAttr=vLine.split(",");
					for(int i2=0;i2<j1;i2++) {
						if(collList[i2].equals(vAttr[0])) {
							Video v=new Video(vAttr[1],vAttr[2],vAttr[3],vAttr[4],170,95,12,10);
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
				collPanel.setLayout(new GridLayout(5,0,5,5));
				JLabel collContentLabel=new JLabel("No collection found",JLabel.CENTER);
				collContentPanel.add(collContentLabel);
				collPanel.add(collTitlePanel);
				collPanel.add(collContentPanel);
			}
			
			collPanel.setBackground(new Color(230,246,250));
			infoPanel.add(collPanel);
			infoPanel.setBorder(new EmptyBorder(30,30,30,30));
			
			JPanel bodyPanel=new JPanel(new BorderLayout());
			JPanel bodyTitlePanel=new JPanel();
			bodyTitlePanel.setBackground(new Color(215,240,247));
			JLabel bodyTitleLabel=new JLabel("My Body Insight",JLabel.LEFT);
			bodyTitleLabel.setFont(new Font("Arial",Font.BOLD,16));
			bodyTitlePanel.add(bodyTitleLabel);
			bodyPanel.add(bodyTitlePanel,BorderLayout.NORTH);
			
			JPanel bodySubPanel=new JPanel(new GridLayout(0,2,10,10));
			
			FileReader bodyFr=new FileReader("files/BodyInfo.csv");
			BufferedReader bodyBr=new BufferedReader(bodyFr);
			String bodyLine=bodyBr.readLine();
			String[] bodyList=new String[4];
			boolean bodyFound=false;
			while(bodyLine!=null) {
				String[] bodyAttr=bodyLine.split(",");
				if(bodyAttr[0].equals(this.thisID)) {
					bodyFound=true;
					bodyList[0]=bodyAttr[1];
					bodyList[1]=bodyAttr[2];
					bodyList[2]=bodyAttr[3];
					bodyList[3]=bodyAttr[4];
					break;
				}
				bodyLine=bodyBr.readLine();
			}
			bodyBr.close();
			bodyFr.close();
			JPanel bodySPanel=new JPanel(new GridLayout(4,0,5,5));
			
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
			}
			else {
				bodySPanel.setLayout(new GridLayout(3,0,5,5));
				bodyContentLabel1=new JLabel("No body info found",JLabel.LEFT);
				bodySPanel.add(bodyContentLabel1);
			}
			bodySPanel.setBackground(color0);
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
			bodyIQPane.setBackground(color0);
			bodyIPanel.add(bodyIQPane,BorderLayout.SOUTH);
			bodyIPanel.setBackground(color0);
			bodyIPanel.setBorder(new EmptyBorder(10,0,10,0));
			
			bodySubPanel.add(bodyIPanel);
			bodySubPanel.add(bodySPanel);
			bodySubPanel.setBackground(color0);
			bodyPanel.add(bodySubPanel,BorderLayout.CENTER);
			
			JPanel notiPanel=new JPanel(new GridLayout(5,0,5,5));
			JPanel notiTitlePanel=new JPanel();
			notiTitlePanel.setBackground(new Color(215,240,247));
			JLabel notiTitleLabel=new JLabel("Notifications",JLabel.LEFT);
			notiTitleLabel.setFont(new Font("Arial",Font.BOLD,16));
			notiTitlePanel.add(notiTitleLabel);
			notiPanel.add(notiTitlePanel);
			
			JPanel notiContentPanel1=new JPanel(new GridLayout(1,0));
			notiContentPanel1.setBackground(color0);
			JLabel notiContentLabel1=new JLabel("[Gym Assistent] Live session with Ariana Grande at 08:30~09:30, 2021-04-18", JLabel.LEFT);
			notiContentPanel1.add(notiContentLabel1);
			notiContentPanel1.setBorder(new EmptyBorder(0,20,0,20));
			notiPanel.add(notiContentPanel1);
			
			JPanel notiContentPanel2=new JPanel(new GridLayout(1,0));
			notiContentPanel2.setBackground(color0);
			JLabel notiContentLabel2=new JLabel("[Rank Upgrade] Congratulations! You are a SILVER user now!",JLabel.LEFT);
			notiContentPanel2.add(notiContentLabel2);
			notiContentPanel2.setBorder(new EmptyBorder(0,20,0,20));
			notiPanel.add(notiContentPanel2);
			
			notiPanel.setBackground(color0);
			
			JPanel hisSPanel=new JPanel(new GridLayout(0,2,20,20));
			hisSPanel.setOpaque(false);
			hisSPanel.add(hisPanel);
			hisSPanel.add(notiPanel);
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
		if(e.getSource()==this.bodyIQ) {
			BodyInfo bi=new BodyInfo(this.thisID);
		}
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
