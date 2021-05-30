package application;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.awt.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class Video implements ActionListener{
	private JPanel videoPanel;
	private String theVideoPath="";
	private JButton videoImgButton;
	private JButton addColl;
	private String videoID="";
	private String userID="";
	private String trainerID;
	private int optype;
	private String[] pairList;
	private int videoLength;
	public Video(String videoID,String videoName, String videoType, String videoPath, String videoImg, int width, int height,int namefont, int typefont) {
		try {
			this.videoID=videoID;
			this.theVideoPath=getVideoPath()+videoPath;
			this.videoPanel=new JPanel(new BorderLayout(7,7));
			BufferedImage uvImage=ImageIO.read(new File(videoImg));
			ImageIcon uvIcon=new ImageIcon();
			uvIcon.setImage(uvImage.getScaledInstance(width,height,Image.SCALE_DEFAULT));
			videoImgButton=new JButton(uvIcon);
			videoImgButton.setContentAreaFilled(false);
			videoImgButton.setBorderPainted(false);
			videoImgButton.addActionListener(this);
			videoPanel.add(videoImgButton,BorderLayout.CENTER);

			JPanel videoContentPanel=new JPanel(new GridLayout(2,0,3,3));
			JLabel videoNameLabel=new JLabel(videoName,JLabel.CENTER);
			videoNameLabel.setFont(new Font("Arial",Font.BOLD,namefont));
			JLabel videoTypeLabel=new JLabel("Type: "+videoType+"   ",JLabel.CENTER);
			videoTypeLabel.setFont(new Font("Arial",Font.BOLD,typefont));
			videoContentPanel.add(videoNameLabel);
			videoContentPanel.add(videoTypeLabel);
			videoContentPanel.setOpaque(false);

			videoPanel.add(videoContentPanel,BorderLayout.SOUTH);
			videoPanel.setOpaque(false);
		}
		catch(Exception e) {
			ErrorMessage em=new ErrorMessage();
			e.printStackTrace();
		}
	}
	public Video(String userID,String videoID,String videoName, String videoType, String videoPath, String videoImg, int width, int height) {
		try {
			this.userID=userID;
			this.videoID=videoID;
			this.theVideoPath=getVideoPath()+videoPath;
			this.videoPanel=new JPanel(new BorderLayout(7,7));
			BufferedImage uvImage=ImageIO.read(new File(videoImg));
			ImageIcon uvIcon=new ImageIcon();
			uvIcon.setImage(uvImage.getScaledInstance(width,height,Image.SCALE_DEFAULT));
			videoImgButton=new JButton(uvIcon);
			videoImgButton.setContentAreaFilled(false);
			videoImgButton.setBorderPainted(false);
			videoImgButton.addActionListener(this);
			videoPanel.add(videoImgButton,BorderLayout.CENTER);

			JPanel videoContentPanel=new JPanel(new GridLayout(2,0,3,3));
			JLabel videoNameLabel=new JLabel(videoName,JLabel.CENTER);
			videoNameLabel.setFont(new Font("Arial",Font.BOLD,12));
			JLabel videoTypeLabel=new JLabel("Type: "+videoType+"   ",JLabel.CENTER);
			videoTypeLabel.setFont(new Font("Arial",Font.BOLD,10));
			videoContentPanel.add(videoNameLabel);
			videoContentPanel.add(videoTypeLabel);
			videoContentPanel.setOpaque(false);

			videoPanel.add(videoContentPanel,BorderLayout.SOUTH);
			videoPanel.setOpaque(false);
		}
		catch(Exception e) {
			ErrorMessage em=new ErrorMessage();
			e.printStackTrace();
		}
	}
	public Video(String userID,String videoID,String videoName, String videoType, String videoPath, String videoImg, int width, int height,String time, String period) {
		try {
			this.userID=userID;
			this.videoID=videoID;
			this.theVideoPath=getVideoPath()+videoPath;
			this.videoPanel=new JPanel(new BorderLayout(7,7));
			BufferedImage uvImage=ImageIO.read(new File(videoImg));
			ImageIcon uvIcon=new ImageIcon();
			uvIcon.setImage(uvImage.getScaledInstance(width,height,Image.SCALE_DEFAULT));
			videoImgButton=new JButton(uvIcon);
			videoImgButton.setContentAreaFilled(false);
			videoImgButton.setBorderPainted(false);
			videoImgButton.addActionListener(this);
			videoPanel.add(videoImgButton,BorderLayout.CENTER);

			JPanel videoContentPanel=new JPanel(new GridLayout(3,0,3,3));
			JLabel videoNameLabel=new JLabel(videoName,JLabel.CENTER);
			videoNameLabel.setFont(new Font("Arial",Font.BOLD,13));
			JLabel videoTypeLabel=new JLabel("Type: "+videoType+"   ",JLabel.CENTER);
			JLabel recLabel=new JLabel(time+" times per "+period,JLabel.CENTER);
			
			videoContentPanel.add(videoNameLabel);
			videoContentPanel.add(videoTypeLabel);
			videoContentPanel.add(recLabel);
			videoContentPanel.setOpaque(false);

			videoPanel.add(videoContentPanel,BorderLayout.SOUTH);
			videoPanel.setOpaque(false);
		}
		catch(Exception e) {
			ErrorMessage em=new ErrorMessage();
			e.printStackTrace();
		}
	}
	public Video(String uID, String vID, String videoName, String videoType, String videoPath, String videoImg, int width, int height,int namefont, int typefont) {
		try {
			this.optype=1;
			videoID=vID;
			userID=uID;
			this.theVideoPath=getVideoPath()+videoPath;
			this.videoPanel=new JPanel(new BorderLayout());
			BufferedImage uvImage=ImageIO.read(new File(videoImg));
			ImageIcon uvIcon=new ImageIcon();
			uvIcon.setImage(uvImage.getScaledInstance(width,height,Image.SCALE_DEFAULT));
			videoImgButton=new JButton(uvIcon);
			videoImgButton.setContentAreaFilled(false);
			videoImgButton.setBorderPainted(false);
			videoImgButton.addActionListener(this);
			videoPanel.add(videoImgButton,BorderLayout.CENTER);
			
			BufferedImage addImg=ImageIO.read(new File("./imgs/UV/add.png"));
			ImageIcon addIcon=new ImageIcon();
			addIcon.setImage(addImg.getScaledInstance(15,15,Image.SCALE_DEFAULT));
			this.addColl=new JButton(addIcon);
			this.addColl.setContentAreaFilled(false);
			this.addColl.setBorderPainted(false);
			this.addColl.setPreferredSize(new Dimension(15,15));
			this.addColl.addActionListener(this);
			
			JPanel videoCollPanel=new JPanel();
			JPanel videoContentPanel=new JPanel(new GridLayout(2,0));
			JLabel videoNameLabel=new JLabel(videoName,JLabel.CENTER);
			videoNameLabel.setFont(new Font("Arial",Font.BOLD,namefont));
			JLabel videoTypeLabel=new JLabel("Type: "+videoType+"   ",JLabel.CENTER);
			videoTypeLabel.setFont(new Font("Arial",Font.BOLD,typefont));
			videoContentPanel.add(videoNameLabel);
			
			videoCollPanel.add(videoTypeLabel);
			videoCollPanel.add(this.addColl);
			videoCollPanel.setOpaque(false);
			videoCollPanel.setPreferredSize(new Dimension(230,20));
			videoContentPanel.add(videoCollPanel);
			videoContentPanel.setOpaque(false);

			videoPanel.add(videoContentPanel,BorderLayout.SOUTH);
			videoPanel.setOpaque(false);
		}
		catch(Exception e) {
			ErrorMessage em=new ErrorMessage();
			e.printStackTrace();
		}
	}
	public Video(String tID, String vID, String videoName, String videoType, String videoPath, String videoImg, int width, int height,int namefont, int typefont, String[] pair) {
		try {
			this.optype=2;
			videoID=vID;
			trainerID=tID;
			this.pairList=pair;
			this.theVideoPath=getVideoPath()+videoPath;
			this.videoPanel=new JPanel(new BorderLayout());
			BufferedImage uvImage=ImageIO.read(new File(videoImg));
			ImageIcon uvIcon=new ImageIcon();
			uvIcon.setImage(uvImage.getScaledInstance(width,height,Image.SCALE_DEFAULT));
			videoImgButton=new JButton(uvIcon);
			videoImgButton.setContentAreaFilled(false);
			videoImgButton.setBorderPainted(false);
			videoImgButton.addActionListener(this);
			videoPanel.add(videoImgButton,BorderLayout.CENTER);
			
			BufferedImage addImg=ImageIO.read(new File("./imgs/UV/rec.png"));
			ImageIcon addIcon=new ImageIcon();
			addIcon.setImage(addImg.getScaledInstance(15,15,Image.SCALE_DEFAULT));
			this.addColl=new JButton(addIcon);
			this.addColl.setContentAreaFilled(false);
			this.addColl.setBorderPainted(false);
			this.addColl.setPreferredSize(new Dimension(15,15));
			this.addColl.addActionListener(this);
			
			JPanel videoCollPanel=new JPanel();
			JPanel videoContentPanel=new JPanel(new GridLayout(2,0));
			JLabel videoNameLabel=new JLabel(videoName,JLabel.CENTER);
			videoNameLabel.setFont(new Font("Arial",Font.BOLD,namefont));
			JLabel videoTypeLabel=new JLabel("Type: "+videoType+"   ",JLabel.CENTER);
			videoTypeLabel.setFont(new Font("Arial",Font.BOLD,typefont));
			videoContentPanel.add(videoNameLabel);
			
			videoCollPanel.add(videoTypeLabel);
			videoCollPanel.add(this.addColl);
			videoCollPanel.setOpaque(false);
			videoCollPanel.setPreferredSize(new Dimension(230,20));
			videoContentPanel.add(videoCollPanel);
			videoContentPanel.setOpaque(false);

			videoPanel.add(videoContentPanel,BorderLayout.SOUTH);
			videoPanel.setOpaque(false);
		}
		catch(Exception e) {
			ErrorMessage em=new ErrorMessage();
			e.printStackTrace();
		}
	}
	public JPanel getPanel() {
		return this.videoPanel;
	}
	public void writeToColl() {
		try {
			String[] tempInfo=new String[1000];
			int count=0;
			FileReader fr=new FileReader("files/UserCollection.csv");
			BufferedReader br=new BufferedReader(fr);
			String line=br.readLine();
			while(line!=null) {
				String[] attr=line.split(",");
				if(attr[0].equals(userID)) {
					line=attr[0]+","+videoID;
					for(int i=1;i<attr.length;i++)
						line=line+","+attr[i];
				}
				tempInfo[count]=line;
				count++;
				line=br.readLine();
			}
			br.close();
			fr.close();
			
			FileWriter fw=new FileWriter("files/UserCollection.csv");
			BufferedWriter bw=new BufferedWriter(fw);
			for(int j=0;j<count;j++)
				bw.write(tempInfo[j]+"\n");
			bw.close();
			fw.close();
			Success s=new Success("Added to collection");
		}
		catch(Exception e) {
			ErrorMessage em=new ErrorMessage();
			e.printStackTrace();
		}
	}
	public String getVideoPath() {
		String videoPath=System.getProperty("user.dir")+"\\videos\\";
		return videoPath;
	}
	public void recordVideoLength() {
		try {
			int vLength=0;
			int vTime=0;
			String vName="";
			String[] vtemp=new String[1000];
			int vcount=0;
			FileReader vfr=new FileReader("files/Videos.csv");
			BufferedReader vbr=new BufferedReader(vfr);
			String vLine=vbr.readLine();
			while(vLine!=null) {
				vtemp[vcount]="";
				String[] vattr=vLine.split(",");
				if(vattr[0].equals(this.videoID)) {
					vLength=Integer.parseInt(vattr[5]);
					vName=vattr[1];
					vTime=Integer.parseInt(vattr[6]);
					vTime+=1;
					vattr[6]=vTime+"";
				}
				for(int n=0;n<6;n++)
					vtemp[vcount]=vtemp[vcount]+vattr[n]+",";
				vtemp[vcount]=vtemp[vcount]+vattr[6];
				vcount++;
				vLine=vbr.readLine();
			}
			vbr.close();
			vfr.close();
			
			FileWriter vfw=new FileWriter("files/Videos.csv");
			BufferedWriter vbw=new BufferedWriter(vfw);
			for(int k=0;k<vcount;k++)
				vbw.write(vtemp[k]+"\n");
			vbw.close();
			vfw.close();
			
			FileReader fr=new FileReader("files/User.csv");
			BufferedReader br=new BufferedReader(fr);
			String[] tempUser=new String[1000];
			int count=0;
			String line=br.readLine();
			while(line!=null) {
				String[] attr=line.split(",");
				tempUser[count]="";
				if(attr[0].equals(this.userID)) {
					int minute=Integer.parseInt(attr[10]);
					minute+=vLength;
					attr[10]=minute+"";
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
			
			SimpleDateFormat df=new SimpleDateFormat("yyyy/MM/dd HH:mm");
			String time=df.format(new Date());
			String wString=this.userID+","+this.videoID+","+vName+","+time+","+vLength;
			NotiToFile ntf=new NotiToFile("history",wString);
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
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==this.videoImgButton) {
			try {
				ProcessBuilder pb=new ProcessBuilder("C:\\Program Files\\Windows Media Player\\wmplayer.exe",this.theVideoPath);
				pb.start();
				if(!this.userID.equals(""))
					recordVideoLength();
			}
			catch(Exception ex) {
				ErrorMessage em=new ErrorMessage();
				ex.printStackTrace();
			}
		}
		else if(e.getSource()==this.addColl) {
			if(this.optype==1)
				writeToColl();
			else if(this.optype==2) {
				Recommend rec=new Recommend(this.videoID,this.pairList);
			}	
		}
	}
}
