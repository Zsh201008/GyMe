package application;
import java.io.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.awt.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
public class UserBadge extends JFrame{
	private int NUM=6;
	public UserBadge(String userID) {
		try {
			Container contentPane=this.getContentPane();
			contentPane.setLayout(new BorderLayout());
			JPanel selectTPanel=new JPanel(new BorderLayout());
			JPanel selectTTitlePanel=new JPanel();
			JLabel selectTTitleLabel=new JLabel("My Badges Collection",JLabel.CENTER);
			selectTTitleLabel.setFont(new Font("Arial",Font.BOLD,15));
			selectTTitlePanel.add(selectTTitleLabel);
			selectTTitlePanel.setBorder(new EmptyBorder(10,0,0,0));
			contentPane.add(selectTTitlePanel,BorderLayout.NORTH);
			
			String[] badgeList=new String[NUM];
			int count=0;
			JPanel selectTTPanel=new JPanel(new GridLayout(0,3,10,10));
			FileReader fr=new FileReader("files/UserBadges.csv");
			BufferedReader br=new BufferedReader(fr);
			String newLine=br.readLine();
			while(newLine!=null&&count<NUM) {
				String[] attributes=newLine.split(",");
				if(attributes[0].equals(userID)) {
					badgeList[count]=attributes[1];
					count++;
				}
				newLine=br.readLine();
			}
			br.close();
			fr.close();
			
			FileReader bfr=new FileReader("files/Badge.csv");
			BufferedReader bbr=new BufferedReader(bfr);
			String bLine=bbr.readLine();
			while(bLine!=null) {
				String[] battr=bLine.split(",");
				for(int j=0;j<count;j++) {
					if(battr[0].equals(badgeList[j])) {
						String filename="imgs/UA/Badges/"+battr[1];
						JLabel description=new JLabel(battr[2],JLabel.CENTER);
						JPanel badgePanel=new JPanel(new BorderLayout());
						BufferedImage utImage=ImageIO.read(new File(filename));
						ImageIcon utIcon=new ImageIcon();
						utIcon.setImage(utImage.getScaledInstance(80,80,Image.SCALE_DEFAULT));
						JLabel udLabel=new JLabel(utIcon);
						badgePanel.add(udLabel,BorderLayout.CENTER);
						badgePanel.add(description,BorderLayout.SOUTH);
						badgePanel.setBorder(new EmptyBorder(0,0,20,0));
						selectTTPanel.add(badgePanel);
					}
				}
				bLine=bbr.readLine();
			}
			selectTTPanel.setBorder(new EmptyBorder(0,10,10,10));
			contentPane.add(selectTTPanel,BorderLayout.CENTER);
			
			Image icon;
			ImageIcon ig = new ImageIcon("imgs/icon.jpg");
			icon = ig.getImage();
			setIconImage(icon);
			
			setTitle("Badges Collection");
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			if(count>3)
				setSize(500,400);
			else
				setSize(500,230);
			setLocation(250,100);
			setVisible(true);
		}
		catch(Exception e) {
			ErrorMessage em=new ErrorMessage();
			e.printStackTrace();
		}
	}
}
