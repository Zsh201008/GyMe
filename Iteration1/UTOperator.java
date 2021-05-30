import java.io.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.awt.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class UTOperator implements ActionListener {
	private JPanel utPanel;
	private int NUM=7;
	private String userID;
	private String trainerID="";
	private Color lightc=new Color(230,246,250);
	private Color titlec=new Color(215,240,247);
	private JButton send;
	private JTextField chatbox;
	public UTOperator(String ID) {
		this.userID=ID;
		this.utPanel=new JPanel(new GridLayout(0,2,20,20));
		try {
			JPanel selectTPanel=new JPanel(new BorderLayout(10,10));
			JPanel selectTTitlePanel=new JPanel();
			JLabel selectTTitleLabel=new JLabel("Select Trainer");
			selectTTitleLabel.setFont(new Font("Arial",Font.BOLD,16));
			selectTTitlePanel.add(selectTTitleLabel);
			selectTTitlePanel.setBackground(titlec);
			selectTPanel.add(selectTTitlePanel,BorderLayout.NORTH);
			
			JPanel selectTTPanel=new JPanel(new GridLayout(0,4,15,15));
			FileReader fr=new FileReader("files/Trainer.csv");
			BufferedReader br=new BufferedReader(fr);
			String newLine=br.readLine();
			int i=0;
			while(newLine!=null&&i<NUM) {
				String[] attributes=newLine.split(",");
				Trainer t=new Trainer(ID,attributes[0],attributes[1],attributes[3],"imgs/UT/"+attributes[4]);
				selectTTPanel.add(t.getPanel());
				newLine=br.readLine();
				i++;
			}
			br.close();
			fr.close();
			selectTTPanel.setBackground(lightc);
			selectTPanel.add(selectTTPanel);
			selectTPanel.setBackground(lightc);
			this.utPanel.add(selectTPanel);
			
			JPanel myTPanel=new JPanel(new BorderLayout(0,0));
			JPanel myTTitlePanel=new JPanel();
			JLabel myTTitleLabel=new JLabel("My Trainer");
			myTTitleLabel.setFont(new Font("Arial",Font.BOLD,16));
			myTTitlePanel.add(myTTitleLabel);
			myTTitlePanel.setBackground(titlec);
			myTPanel.add(myTTitlePanel,BorderLayout.NORTH);
			
			JPanel myTDetailPanel=new JPanel(new BorderLayout());
			JPanel myTDetailPanel1=new JPanel(new BorderLayout(10,10));
			JPanel myTDetailPanel12=new JPanel(new GridLayout(3,0,5,5));
			String trainerImage="";
			String trainerName="";
			String trainerType="";
			FileReader fr2=new FileReader("files/User_Trainer.csv");
			BufferedReader br2=new BufferedReader(fr2);
			String booking="";
			String chatting="";
			String newLine2=br2.readLine();
			while(newLine2!=null) {
				String[] attributes=newLine2.split(",");
				if(attributes[0].equals(this.userID)) {
					this.trainerID=attributes[1];
					booking=attributes[2];
					chatting=attributes[3];
					break;
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
				utIcon.setImage(tImage.getScaledInstance(80,80,Image.SCALE_DEFAULT));
				JLabel udLabel=new JLabel(utIcon);
				myTDetailPanel1.add(udLabel,BorderLayout.CENTER);
				
				JLabel trainerNameLabel=new JLabel(trainerName,JLabel.CENTER);
				trainerNameLabel.setFont(new Font("Arial",Font.BOLD,20));
				JLabel trainerIDLabel=new JLabel("ID: "+this.trainerID,JLabel.CENTER);
				JLabel trainerTypeLabel=new JLabel("Focus: "+trainerType,JLabel.CENTER);
				myTDetailPanel12.add(trainerNameLabel);
				myTDetailPanel12.add(trainerIDLabel);
				myTDetailPanel12.add(trainerTypeLabel);
				myTDetailPanel12.setBackground(lightc);
				myTDetailPanel12.setBorder(new EmptyBorder(10,10,10,10));
				myTDetailPanel1.add(myTDetailPanel12,BorderLayout.SOUTH);
				myTDetailPanel1.setBackground(lightc);
				myTDetailPanel.add(myTDetailPanel1,BorderLayout.NORTH);
				
				JPanel myTUserPanel=new JPanel(new GridLayout(2,0,10,10));
				JPanel myTBookPanel=new JPanel(new GridLayout(3,0,10,10));
				JPanel myTBookTitlePanel=new JPanel();
				JLabel myTBookTitleLabel=new JLabel("Live Session",JLabel.CENTER);
				myTBookTitleLabel.setFont(new Font("Arial",Font.BOLD,16));
				myTBookTitlePanel.add(myTBookTitleLabel);
				myTBookTitlePanel.setBackground(titlec);
				myTBookPanel.add(myTBookTitlePanel);
				
				JLabel myTBookBookLabel=new JLabel(booking,JLabel.CENTER);
				myTBookPanel.add(myTBookBookLabel);
				myTBookPanel.setBackground(lightc);
				
				JPanel myTChatPanel=new JPanel(new GridLayout(3,0,10,10));
				JPanel myTChatTitlePanel=new JPanel();
				JLabel myTChatTitleLabel=new JLabel("Chatting",JLabel.CENTER);
				myTChatTitleLabel.setFont(new Font("Arial",Font.BOLD,16));
				myTChatTitlePanel.add(myTChatTitleLabel);
				myTChatTitlePanel.setBackground(titlec);
				myTChatPanel.add(myTChatTitlePanel);
				JPanel myTChatBoxPanel=new JPanel();
				JLabel chatlabel=new JLabel("Enter: ",JLabel.CENTER);
				this.chatbox=new JTextField(20);
				this.send=new JButton("send");
				this.send.addActionListener(this);
				myTChatBoxPanel.add(chatlabel);
				myTChatBoxPanel.add(this.chatbox);
				myTChatBoxPanel.add(this.send);
				myTChatBoxPanel.setBackground(lightc);
				myTChatPanel.add(myTChatBoxPanel);
				
				JLabel myTChatChatPanel1=new JLabel(trainerName+": "+chatting,JLabel.CENTER);
				myTChatPanel.add(myTChatChatPanel1);
				myTChatPanel.setBackground(lightc);
				
				myTUserPanel.add(myTBookPanel);
				myTUserPanel.add(myTChatPanel);
				myTUserPanel.setBackground(lightc);
				myTDetailPanel.setBackground(lightc);
				myTDetailPanel.add(myTUserPanel,BorderLayout.CENTER);
				myTDetailPanel.setBorder(new EmptyBorder(10,10,10,10));
				myTPanel.add(myTDetailPanel);
			}
			myTPanel.setBackground(lightc);
			this.utPanel.add(myTPanel);
			this.utPanel.setOpaque(false);
			this.utPanel.setBorder(new EmptyBorder(30,30,30,30));
		}
		catch(Exception e) {
			ErrorMessage em=new ErrorMessage();
			e.printStackTrace();
		}
	}
	public JPanel getUTPanel() {
		return this.utPanel;
	}
	public void actionPerformed(ActionEvent e) {
		System.out.println("Submit");
	}
}
