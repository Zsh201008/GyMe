import java.io.*;

import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.awt.*;
import java.util.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class ASController{
	private JPanel asPanel;
	private ArrayList<String> userIDs;
	private Color lightc=new Color(230,246,250);
	private Color titlec=new Color(207,236,245);
	public ASController() {
		this.asPanel=new JPanel(new GridLayout(0,3,15,15));
		int userCount=0;
		int trainerCount=0;
		int videoCount=0;
		int liveCount=0;
		int userCType=0;
		int userPType=0;
		int userCopper=0;
		int userSilver=0;
		int userGolden=0;
		try {		
			JPanel countPanel=new JPanel(new BorderLayout());
			JPanel countTitlePanel=new JPanel();
			JLabel countTitleLabel=new JLabel("Sum Information",JLabel.CENTER);
			countTitleLabel.setFont(new Font("Arial",Font.BOLD,16));
			countTitlePanel.add(countTitleLabel);
			countTitlePanel.setBackground(titlec);
			countPanel.add(countTitlePanel,BorderLayout.NORTH);
			
			JPanel countCoPanel=new JPanel(new GridLayout(0,2,5,5));
			JPanel countContentPanel=new JPanel(new GridLayout(6,0));
			
			BufferedImage bimage=ImageIO.read(new File("./imgs/sum.png")); 
			ImageIcon uaAvatar=new ImageIcon();
			uaAvatar.setImage(bimage.getScaledInstance(150,180,Image.SCALE_DEFAULT));
			JLabel avatarLabel=new JLabel(uaAvatar);
			countCoPanel.add(avatarLabel);
			
			FileReader uFr=new FileReader("files/User.csv");
			BufferedReader uBr=new BufferedReader(uFr);
			String uLine=uBr.readLine();
			while(uLine!=null) {
				String[] uattr=uLine.split(",");
				if(uattr[6].equals("Copper"))
					userCopper++;
				else if(uattr[6].equals("Silver"))
					userSilver++;
				else if(uattr[6].equals("Golden"))
					userGolden++;
				
				if(uattr[7].equals("C"))
					userCType++;
				else if(uattr[7].equals("P"))
					userPType++;
				userCount++;
				uLine=uBr.readLine();
			}
			uBr.close();
			uFr.close();
			
			FileReader tFr=new FileReader("files/Trainer.csv");
			BufferedReader tBr=new BufferedReader(tFr);
			String tLine=tBr.readLine();
			while(tLine!=null) {
				trainerCount++;
				tLine=tBr.readLine();
			}
			tBr.close();
			tFr.close();
			
			FileReader vFr=new FileReader("files/Videos.csv");
			BufferedReader vBr=new BufferedReader(vFr);
			String vLine=vBr.readLine();
			while(vLine!=null) {
				videoCount++;
				vLine=vBr.readLine();
			}
			vBr.close();
			vFr.close();
			
			FileReader lFr=new FileReader("files/Booking.csv");
			BufferedReader lBr=new BufferedReader(lFr);
			String lLine=lBr.readLine();
			while(lLine!=null) {
				liveCount++;
				lLine=lBr.readLine();
			}
			lBr.close();
			lFr.close();
			
			JLabel emptyLabel1=new JLabel();
			JLabel emptyLabel2=new JLabel();
			JLabel userCountLabel=new JLabel("Total user: "+userCount,JLabel.CENTER);
			userCountLabel.setFont(new Font("Arial",Font.BOLD,13));
			JLabel trainerCountLabel=new JLabel("Total trainer: "+trainerCount,JLabel.CENTER);
			trainerCountLabel.setFont(new Font("Arial",Font.BOLD,13));
			JLabel videoCountLabel=new JLabel("Total video: "+videoCount,JLabel.CENTER);
			videoCountLabel.setFont(new Font("Arial",Font.BOLD,13));
			JLabel liveCountLabel=new JLabel("Total live session: "+liveCount,JLabel.CENTER);
			liveCountLabel.setFont(new Font("Arial",Font.BOLD,13));
			countContentPanel.add(emptyLabel1);
			countContentPanel.add(userCountLabel);
			countContentPanel.add(trainerCountLabel);
			countContentPanel.add(videoCountLabel);
			countContentPanel.add(liveCountLabel);
			countContentPanel.add(emptyLabel2);
			countContentPanel.setBackground(lightc);
			countCoPanel.add(countContentPanel);
			countCoPanel.setBackground(lightc);
			countCoPanel.setBorder(new EmptyBorder(0,20,10,20));
			countPanel.add(countCoPanel,BorderLayout.CENTER);
			countPanel.setBackground(lightc);
			
			JPanel userTypePanel=new JPanel(new BorderLayout());
			JPanel userTypeTitlePanel=new JPanel();
			JLabel userTypeTitleLabel=new JLabel("User Type",JLabel.CENTER);
			userTypeTitleLabel.setFont(new Font("Arial",Font.BOLD,16));
			userTypeTitlePanel.add(userTypeTitleLabel);
			userTypeTitlePanel.setBackground(titlec);
			userTypePanel.add(userTypeTitlePanel,BorderLayout.NORTH);
			
			PieChart pc1=new PieChart("type");
			pc1.addPart(userCType, "Common", new Color(253,253,210));
			pc1.addPart(userPType, "Privilege", new Color(253,178,157));
			pc1.setBackground(lightc);
			userTypePanel.add(pc1,BorderLayout.CENTER);
			
			JPanel userRankPanel=new JPanel(new BorderLayout());
			JPanel userRankTitlePanel=new JPanel();
			JLabel userRankTitleLabel=new JLabel("User Rank",JLabel.CENTER);
			userRankTitleLabel.setFont(new Font("Arial",Font.BOLD,16));
			userRankTitlePanel.add(userRankTitleLabel);
			userRankTitlePanel.setBackground(titlec);
			userRankPanel.add(userRankTitlePanel,BorderLayout.NORTH);
			
			PieChart pc2=new PieChart("rank");
			pc2.addPart(userCopper, "Copper", new Color(240,222,222));
			pc2.addPart(userSilver, "Silver", new Color(250,250,250));
			pc2.addPart(userGolden, "Golden", new Color(254,246,208));
			pc2.setBackground(lightc);
			userRankPanel.add(pc2,BorderLayout.CENTER);
			
			JPanel userTimePanel=new JPanel(new BorderLayout());
			JPanel userTimeTitlePanel=new JPanel();
			JLabel userTimeTitleLabel=new JLabel("Top 5 Workout Most Users",JLabel.CENTER);
			userTimeTitleLabel.setFont(new Font("Arial",Font.BOLD,16));
			userTimeTitlePanel.add(userTimeTitleLabel);
			userTimeTitlePanel.setBackground(titlec);
			userTimePanel.add(userTimeTitlePanel,BorderLayout.NORTH);
			userTimePanel.add(selectFive("user","files/User.csv",10,2),BorderLayout.CENTER);
			
			JPanel trainerTimePanel=new JPanel(new BorderLayout());
			JPanel trainerTimeTitlePanel=new JPanel();
			JLabel trainerTimeTitleLabel=new JLabel("Top 5 Work Longest Trainers",JLabel.CENTER);
			trainerTimeTitleLabel.setFont(new Font("Arial",Font.BOLD,16));
			trainerTimeTitlePanel.add(trainerTimeTitleLabel);
			trainerTimeTitlePanel.setBackground(titlec);
			trainerTimePanel.add(trainerTimeTitlePanel,BorderLayout.NORTH);
			trainerTimePanel.add(selectFive("trainer","files/Trainer.csv",10,1),BorderLayout.CENTER);
			
			JPanel videoHotPanel=new JPanel(new BorderLayout());
			JPanel videoHotTitlePanel=new JPanel();
			JLabel videoHotTitleLabel=new JLabel("Top 5 Hottest Videos",JLabel.CENTER);
			videoHotTitleLabel.setFont(new Font("Arial",Font.BOLD,16));
			videoHotTitlePanel.add(videoHotTitleLabel);
			videoHotTitlePanel.setBackground(titlec);
			videoHotPanel.add(videoHotTitlePanel,BorderLayout.NORTH);
			videoHotPanel.add(selectFive("video","files/Videos.csv",6,2),BorderLayout.CENTER);
			
			this.asPanel.add(countPanel);
			this.asPanel.add(userTypePanel);
			this.asPanel.add(userRankPanel);
			this.asPanel.add(userTimePanel);
			this.asPanel.add(trainerTimePanel);
			this.asPanel.add(videoHotPanel);
			this.asPanel.setOpaque(false);
			this.asPanel.setBorder(new EmptyBorder(15,15,15,15));
		}
		catch(Exception e) {
			ErrorMessage em=new ErrorMessage();
			e.printStackTrace();
		}
	}
	public JPanel selectFive(String type,String filename,int index,int index2) {
		BarChart bc=new BarChart(type);
		try {
			int[] userTime=new int[100];
			String[] tempUser=new String[100];
			int count=0;
			FileReader fr=new FileReader(filename);
			BufferedReader br=new BufferedReader(fr);
			String line=br.readLine();
			while(line!=null&&count<100) {
				tempUser[count]=line;
				String[] attr=line.split(",");
				userTime[count]=Integer.parseInt(attr[index]);
				count++;
				line=br.readLine();
			}
			br.close();
			fr.close();
			int[] resultTime=sortFive(userTime,count);
			
			for(int i=0;i<5;i++) {
				String[] tempAttr=tempUser[resultTime[i]].split(",");
				int amount=Integer.parseInt(tempAttr[index]);
				String tag=tempAttr[index2];
				int rgb=new Random().nextInt(0xFFFFFF);
				Color color=new Color(rgb);
				bc.addPart(amount,tag,color);
			}
		}
		catch(Exception e) {
			ErrorMessage em=new ErrorMessage();
			e.printStackTrace();
		}
		
		bc.setBackground(lightc);
		return bc;
	}
	public int[] sortFive(int[] list,int length) {
		int[] indexList=new int[5];
		for(int k=0;k<5;k++)
			indexList[k]=-1;
		int max;
		for(int i=0;i<5;i++) {
			max=0;
			for(int j=0;j<length;j++) {
				boolean isExist=false;
				if(list[j]>max) {
					for(int m=0;m<5;m++) {
						if(indexList[m]==j) {
							isExist=true;
							break;
						}
					}
					if(isExist)
						continue;
					max=list[j];
					indexList[i]=j;
				}
			}
		}
		return indexList;
	}
	public JPanel getASPanel() {
		return this.asPanel;
	}
}
