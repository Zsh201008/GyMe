import java.io.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.*;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

//Display User Account Information
public class TAController{
	private JPanel taPanel;
	private String tName;
	private String tSex;
	private String tFocus;
	private int tClassNum;
	private String tAvatar;
	private String thisID;
	private String[] pairList;
	private Color lightc=new Color(230,246,250);
	private Color titlec=new Color(207,236,245);
	public TAController(String trainerID,String[] pair) {
		this.thisID=trainerID;
		this.pairList=pair;
		this.taPanel=new JPanel();
		this.taPanel.add(createPanel());
		this.taPanel.setOpaque(false);
	}
	public JPanel createPanel() {
		JPanel resultPanel=new JPanel(new BorderLayout());
		try {
			JPanel namePanel=new JPanel(new GridLayout(3,0,0,0));
			FileReader fr=new FileReader("files/Trainer.csv");
			BufferedReader br=new BufferedReader(fr);
			String newLine=br.readLine();
			while(newLine!=null) {
				String[] attributes=newLine.split(",");
				if(attributes[0].equals(thisID)) {
					this.tName=attributes[1];
					this.tSex=attributes[2];
					this.tFocus=attributes[3];
					this.tClassNum=Integer.parseInt(attributes[9]);
					this.tAvatar=attributes[4];
					break;
				}
				newLine=br.readLine();
			}
			JPanel uaName=new JPanel(new GridLayout(0,5,0,0));
			BufferedImage bimage=ImageIO.read(new File("./imgs/Trainer/"+this.tAvatar));
			ImageIcon uaAvatar=new ImageIcon();
			uaAvatar.setImage(bimage.getScaledInstance(140,140,Image.SCALE_DEFAULT));
			JLabel avatarLabel=new JLabel(uaAvatar);
			uaName.add(avatarLabel);
			
			JPanel namePanel1=new JPanel(new BorderLayout());
			JLabel nameLabel=new JLabel(this.tName);
			nameLabel.setFont(new Font("Arial",Font.BOLD,28));
			nameLabel.setOpaque(false);
			namePanel1.add(nameLabel,BorderLayout.CENTER);
			BufferedImage buaSex;
			if(this.tSex.equals("M"))
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
			
			JLabel accNoLabel=new JLabel("   Account: "+thisID,JLabel.LEFT);
			accNoLabel.setFont(new Font("Arial",Font.BOLD,14));
			namePanel.add(accNoLabel);
			
			JPanel namePanel3=new JPanel(new FlowLayout(0,10,10));
			JPanel typePanel=new JPanel(new GridLayout(1,0,0,0));
			String typeDetail=this.tFocus;
			JLabel typeLabel=new JLabel("Focus: "+typeDetail);
			typeLabel.setFont(new Font("Arial",Font.BOLD,14));
			typePanel.add(typeLabel);
			typePanel.setBackground(new Color(210,255,143));
			JPanel classPanel=new JPanel(new GridLayout(1,0,0,0));
			String classDetail=this.tClassNum+"";
			JLabel classLabel=new JLabel("Courses: "+classDetail);
			classLabel.setFont(new Font("Arial",Font.BOLD,14));
			classPanel.add(classLabel);
			classPanel.setBackground(new Color(230,193,255));
			namePanel3.add(typePanel);
			namePanel3.add(classPanel);
			namePanel3.setOpaque(false);
			namePanel.add(namePanel3);
			
			namePanel.setOpaque(false);
			uaName.add(namePanel);
			uaName.setOpaque(false);
			resultPanel.add(uaName,BorderLayout.NORTH);    //
			
			JPanel infoPanel=new JPanel(new GridLayout(0,2,20,20));
			infoPanel.setOpaque(false);
			
			JPanel schePanel=new JPanel(new GridLayout(5,0,5,5));
			JPanel scheTitlePanel=new JPanel();
			scheTitlePanel.setBackground(titlec);
			JLabel scheTitleLabel=new JLabel("My Schedule",JLabel.LEFT);
			scheTitleLabel.setFont(new Font("Arial",Font.BOLD,16));
			scheTitlePanel.add(scheTitleLabel);
			schePanel.add(scheTitlePanel);
			
			FileReader scheFr=new FileReader("files/Booking.csv");
			BufferedReader scheBr=new BufferedReader(scheFr);
			String scheLine=scheBr.readLine();
			int j=0;
			boolean isScheFound=false;
			while(scheLine!=null&&j<3) {
				String[] scheAttr=scheLine.split(",");
				for(int k=0;k<this.pairList.length;k++) {
					if(scheAttr[1].equals(this.pairList[k])) {
						if(scheAttr[5].equals("on")) {
							String scheContent="";
							scheContent="Live Session: "+scheAttr[2]+", Platform: "+scheAttr[3]+", Room: "+scheAttr[4];
							JPanel scheContentPanel=new JPanel(new GridLayout(1,0));
							scheContentPanel.setBackground(lightc);
							JLabel scheContentLabel=new JLabel(scheContent,JLabel.LEFT);
							scheContentPanel.add(scheContentLabel);
							scheContentPanel.setBorder(new EmptyBorder(0,10,0,10));
							schePanel.add(scheContentPanel);
							j++;
							isScheFound=true;
						}
					}
				}
				scheLine=scheBr.readLine();
			}
			scheBr.close();
			scheFr.close();
			
			if(!isScheFound) {
				JPanel noSchePanel=new JPanel();
				JLabel noScheLabel=new JLabel("No schedule found", JLabel.CENTER);
				noSchePanel.add(noScheLabel);
				noSchePanel.setBackground(lightc);
				schePanel.add(noSchePanel);
			}
			schePanel.setBackground(lightc);
			
			JPanel notiPanel=new JPanel(new GridLayout(5,0,5,5));
			JPanel notiTitlePanel=new JPanel();
			notiTitlePanel.setBackground(titlec);
			JLabel notiTitleLabel=new JLabel("Notifications",JLabel.LEFT);
			notiTitleLabel.setFont(new Font("Arial",Font.BOLD,16));
			notiTitlePanel.add(notiTitleLabel);
			notiPanel.add(notiTitlePanel);
			
			FileReader notiFr=new FileReader("files/TrainerNotification.csv");
			BufferedReader notiBr=new BufferedReader(notiFr);
			String notiLine=notiBr.readLine();
			int notiCount=0;
			boolean isNotiFound=false;
			while(notiLine!=null&&notiCount<5) {
				String[] notiAttr=notiLine.split(",");
				for(int i=0;i<this.pairList.length;i++) {
					if(this.pairList[i].equals(notiAttr[1])) {
						JPanel notiContentPanel=new JPanel(new GridLayout(1,0));
						notiContentPanel.setBackground(lightc);
						if(notiAttr[2].equals("live")) {
							LiveSession ls=new LiveSession(readUser(notiAttr[1]),notiAttr[1],readLive(notiAttr[3]),notiAttr[3]);
							notiContentPanel.add(ls.getPanel());
						}
						else if(notiAttr[2].equals("add")) {
							String content=readUser(notiAttr[1])+" selects you as his/her trainer.";
							JLabel notiContentLabel=new JLabel(content,JLabel.LEFT);
							notiContentPanel.add(notiContentLabel);
						}
						else if(notiAttr[2].equals("delete")) {
							String content2="You have been removed by "+readUser(notiAttr[2])+".";
							JLabel notiContentLabel2=new JLabel(content2,JLabel.LEFT);
							notiContentPanel.add(notiContentLabel2);
						}
						else if(notiAttr[2].equals("cancel")) {
							FileReader canFr=new FileReader("files/Booking.csv");
							BufferedReader canBr=new BufferedReader(canFr);
							String canLine=canBr.readLine();
							while(canLine!=null) {
								String[] canAttr=canLine.split(",");
								if(canAttr[0].equals(notiAttr[3])) {
									String cnt="Live session on "+canAttr[2]+" at "+canAttr[3]+" has been canceled.";
									JLabel notiContentL=new JLabel(cnt,JLabel.LEFT);
									notiContentPanel.add(notiContentL);
									break;
								}
								canLine=canBr.readLine();
							}
							canBr.close();
							canFr.close();
						}
						notiContentPanel.setBorder(new EmptyBorder(0,10,0,10));
						notiPanel.add(notiContentPanel);
						notiCount++;
						isNotiFound=true;
					}
				}
				notiLine=notiBr.readLine();
			}
			notiBr.close();
			notiFr.close();			
			if(!isNotiFound) {
				JPanel noNotiPanel=new JPanel();
				JLabel noNotiLabel=new JLabel("No notification found", JLabel.CENTER);
				noNotiPanel.add(noNotiLabel);
				noNotiPanel.setBackground(lightc);
				notiPanel.add(noNotiPanel);
			}
			notiPanel.setBackground(lightc);
			
			JPanel myCoursePanel=new JPanel(new BorderLayout());
			JPanel myCourseTitlePanel=new JPanel();
			myCourseTitlePanel.setBackground(titlec);
			JLabel myCourseTitleLabel=new JLabel("My Courses",JLabel.CENTER);
			myCourseTitleLabel.setFont(new Font("Arial",Font.BOLD,16));
			myCourseTitlePanel.add(myCourseTitleLabel);
			
			JPanel myCourseContentPanel=new JPanel();
			myCourseContentPanel.setBackground(lightc);
			
			FileReader courseFr=new FileReader("files/TrainerCourse.csv");
			BufferedReader courseBr=new BufferedReader(courseFr);
			String courseLine=courseBr.readLine();
			boolean isCourseFound=false;
			String[] courseList=new String[4];
			for(int i1=0;i1<4;i1++){
				courseList[i1]="";
			}
			int j2=0;
			while(courseLine!=null) {
				String[] courseAttr=courseLine.split(",");
				if(courseAttr[0].equals(this.thisID)) {
					isCourseFound=true;
					for(;j2+1<courseAttr.length&&j2<4;j2++)
						courseList[j2]=courseAttr[j2+1];
					break;
				}
				courseLine=courseBr.readLine();
			}
			courseBr.close();
			courseFr.close();

			if(isCourseFound) {
				myCoursePanel.setLayout(new BorderLayout());
				myCourseContentPanel.setLayout(new GridLayout(0,4,10,10));
				FileReader crFr=new FileReader("files/Videos.csv");
				BufferedReader crBr=new BufferedReader(crFr);
				String vLine=crBr.readLine();
				while(vLine!=null) {
					String[] vAttr=vLine.split(",");
					for(int i2=0;i2<j2;i2++) {
						if(courseList[i2].equals(vAttr[0])) {
							Video v=new Video(vAttr[0],vAttr[1],vAttr[2],vAttr[3],vAttr[4],170,95,12,10);
							myCourseContentPanel.add(v.getPanel());
						}
					}
					vLine=crBr.readLine();
				}
				crBr.close();
				crFr.close();
				myCoursePanel.add(myCourseTitlePanel,BorderLayout.NORTH);
				myCourseContentPanel.setBorder(new EmptyBorder(5,5,10,5));
				myCoursePanel.add(myCourseContentPanel,BorderLayout.CENTER);
			}
			else {
				myCourseContentPanel.setLayout(new GridLayout(4,0,5,5));
				JLabel noContentLabel=new JLabel("No recorded courses found",JLabel.CENTER);
				myCourseContentPanel.add(noContentLabel);
				myCourseContentPanel.setBorder(new EmptyBorder(10,5,10,5));
				myCoursePanel.add(myCourseTitlePanel,BorderLayout.NORTH);
				myCoursePanel.add(myCourseContentPanel,BorderLayout.CENTER);
			}
			myCoursePanel.setOpaque(false);
			myCoursePanel.setBorder(new EmptyBorder(20,30,30,30));
			
			infoPanel.add(schePanel);
			infoPanel.add(notiPanel);
			infoPanel.setBorder(new EmptyBorder(30,30,0,30));
			resultPanel.add(infoPanel,BorderLayout.CENTER);
			resultPanel.add(myCoursePanel,BorderLayout.SOUTH);
		}
		catch(Exception e) {
			ErrorMessage em=new ErrorMessage();
			e.printStackTrace();
		}
		resultPanel.setOpaque(false);
		resultPanel.setBorder(new EmptyBorder(30,30,30,30));
		return resultPanel;
	}
	public String readLive(String bookID) {
		String liveContent="";
		try {
			FileReader fr=new FileReader("files/Booking.csv");
			BufferedReader br=new BufferedReader(fr);
			String line=br.readLine();
			while(line!=null) {
				String[] attr=line.split(",");
				if(attr[0].equals(bookID)) {
					liveContent="New Live Session: "+attr[2]+", Platform: "+attr[3];
					break;
				}
				line=br.readLine();
			}
			br.close();
			fr.close();
		}
		catch(Exception e) {
			ErrorMessage em=new ErrorMessage();
			e.printStackTrace();
		}
		return liveContent;
	}
	public String readUser(String pairID) {
		String liveContent="";
		try {
			FileReader fr=new FileReader("files/UserTrainer.csv");
			BufferedReader br=new BufferedReader(fr);
			String line=br.readLine();
			while(line!=null) {
				String[] attr=line.split(",");
				if(attr[0].equals(pairID)) {
					liveContent=attr[1];
					break;
				}
				line=br.readLine();
			}
			br.close();
			fr.close();
		}
		catch(Exception e) {
			ErrorMessage em=new ErrorMessage();
			e.printStackTrace();
		}
		return liveContent;
	}
	public JPanel getTAPanel() {
		return this.taPanel;
	}

	public class LiveSession implements ActionListener{
		private JPanel bookPanel;
		private JButton button;
		private String pairID;
		private String bookID;
		private String userID;
		private int MAX_B=1000;
		private int MAX_NOTI=1000;
		
		public LiveSession(String userID,String pairID,String booking, String id) {
			this.userID=userID;
			this.pairID=pairID;
			this.bookID=id;
			this.bookPanel=new JPanel(new BorderLayout());
			JLabel bookLabel=new JLabel(booking,JLabel.LEFT);
			this.button=new JButton("confirm");
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
			try {
				String[] tempInfo=new String[MAX_B];
				int count=0;
				FileReader fr=new FileReader("files/Booking.csv");
				BufferedReader br=new BufferedReader(fr);
				String line=br.readLine();
				while(line!=null&&count<MAX_B) {
					String[] attr=line.split(",");
					if(attr[0].equals(this.bookID)) {
						line=attr[0]+","+attr[1]+","+attr[2]+","+attr[3]+","+attr[4]+",on";
					}
					tempInfo[count]=line;
					count++;
					line=br.readLine();
				}
				br.close();
				fr.close();
				
				FileWriter fw=new FileWriter("files/Booking.csv");
				BufferedWriter bw=new BufferedWriter(fw);
				for(int i=0;i<count;i++)
					bw.write(tempInfo[i]+"\n");
				bw.close();
				fw.close();
				
				String[] temp=new String[MAX_B];
				int c=0;
				FileReader notiFr=new FileReader("files/TrainerNotification.csv");
				BufferedReader notiBr=new BufferedReader(notiFr);
				String l=notiBr.readLine();
				while(l!=null&&c<MAX_B) {
					String[] a=l.split(",");
					if(!a[3].equals(this.bookID)) {
						temp[c]=l;
						c++;
					}
					l=notiBr.readLine();
				}
				notiBr.close();
				notiFr.close();
				
				FileWriter notiFw=new FileWriter("files/TrainerNotification.csv");
				BufferedWriter notiBw=new BufferedWriter(notiFw);
				for(int j=0;j<c;j++)
					notiBw.write(temp[j]+"\n");
				notiBw.close();
				notiFw.close();
				
				int nid=(int)(Math.random()*10000)+10000;
				String notiID="n"+nid;
				String wString=notiID+","+userID+","+pairID+",confirm,"+bookID;
				NotiToFile ntf=new NotiToFile("user",wString);
				boolean isOK=ntf.notiToFile();
				if(isOK) {
					Success s=new Success("Booking confirmed");
				}
			}
			catch(Exception e) {
				ErrorMessage em=new ErrorMessage();
				e.printStackTrace();
			}
		}
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==this.button) {
				confirmBooking();
				taPanel.removeAll();
				taPanel.add(createPanel());
				taPanel.setVisible(false);
				taPanel.setVisible(true);
			}
		}
	}
}
