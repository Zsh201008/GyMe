/**
 * Title: Trainer.java
 * Entity class of trainer.
 * @author Shuhan Zhang
 * @version 5.0
 */
import java.io.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.*;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

public class Trainer implements ActionListener {
	private JPanel trainerPanel;
	private JButton select;
	private String trainerID;
	private String trainerName;
	private String userID;
	public Trainer(String user, String id, String name, String type, String image) {
		try {
			this.userID=user;
			this.trainerID=id;
			this.trainerName=name;
			this.trainerPanel=new JPanel(new BorderLayout(0,0));
			BufferedImage utImage=ImageIO.read(new File(image));
			ImageIcon utIcon=new ImageIcon();
			utIcon.setImage(utImage.getScaledInstance(80,80,Image.SCALE_DEFAULT));
			JLabel udLabel=new JLabel(utIcon);
			this.trainerPanel.add(udLabel,BorderLayout.CENTER);
			
			JPanel trainerContentPanel=new JPanel(new GridLayout(2,0));
			JPanel trainerNamePanel=new JPanel(new GridLayout(2,0,5,5));
			JLabel trainerNameLabel=new JLabel(name,JLabel.CENTER);
			trainerNameLabel.setFont(new Font("Arial",Font.BOLD,14));
			trainerNamePanel.add(trainerNameLabel);
			JLabel trainerTypeLabel=new JLabel(type,JLabel.CENTER);
			trainerNamePanel.add(trainerTypeLabel);
			trainerNamePanel.setOpaque(false);
			trainerContentPanel.add(trainerNamePanel);
			
			JPanel buttonPanel=new JPanel();
			this.select=new JButton("select");
			this.select.addActionListener(this);
			buttonPanel.add(this.select);
			buttonPanel.setOpaque(false);
			trainerContentPanel.add(buttonPanel);
			trainerContentPanel.setOpaque(false);
			this.trainerPanel.add(trainerContentPanel,BorderLayout.SOUTH);
			this.trainerPanel.setOpaque(false);
		}
		catch(Exception e) {
			ErrorMessage em=new ErrorMessage();
			e.printStackTrace();
		}
	}
	public JPanel getPanel() {
		return this.trainerPanel;
	}
	public void actionPerformed(ActionEvent e) {
		Confirm cf=new Confirm(this.userID,this.trainerID,this.trainerName);
	}
	public class Confirm extends JFrame implements ActionListener{
		private String trainerName;
		private String trainerID;
		private String userID;
		private JButton jbtOK;

		public Confirm(String user, String id, String name){
			this.trainerName=name;
			this.trainerID=id;
			this.userID=user;
			
			JPanel messagePanel=new JPanel(new GridLayout(4,0,0,0));
			
			JLabel exContent=new JLabel("Are you sure?",JLabel.CENTER);
			messagePanel.add(exContent);
			JLabel textLabel=new JLabel("You will add",JLabel.CENTER);
			messagePanel.add(textLabel);
			JLabel textLabel2=new JLabel(this.trainerName,JLabel.CENTER);
			messagePanel.add(textLabel2);
			
			JPanel buttonPanel=new JPanel(new FlowLayout());
			jbtOK=new JButton("YES");
			jbtOK.setSize(50,30);
			jbtOK.addActionListener(this);
			buttonPanel.add(jbtOK);
			messagePanel.add(buttonPanel);
			
			getContentPane().add(messagePanel);
			setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			setSize(230,180);
			setLocation(350,350);
			setVisible(true);
		}
		
		public void addTrainer() {
			try {
				File file=new File("files/UserTrainer.csv");
				RandomAccessFile raf=new RandomAccessFile(file, "rw");
				raf.seek(raf.length());
				int pid=(int)(Math.random()*10000)+20000;
				String pairID="p"+pid;
				raf.writeBytes(pairID+","+userID+","+trainerID+"\n");
				raf.close();
				
				int nid=(int)(Math.random()*10000)+10000;
				String notiID="n"+nid;
				String wString=notiID+","+pairID+",add,null";
				NotiToFile ntf=new NotiToFile("trainer",wString);
				boolean isOK=ntf.notiToFile();
				if(isOK) {
					Success s=new Success("Your new trainer is: "+this.trainerName);
				}
			}
			catch(Exception ex) {
				ErrorMessage em=new ErrorMessage();
				ex.printStackTrace();
			}
		}
		
		public void actionPerformed(ActionEvent e){
			if(e.getSource()==jbtOK){
				addTrainer();
				dispose();
			}
		}
	}
}
