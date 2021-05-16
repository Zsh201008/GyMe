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
			this.trainerPanel=new JPanel(new BorderLayout());
			BufferedImage utImage=ImageIO.read(new File(image));
			ImageIcon utIcon=new ImageIcon();
			utIcon.setImage(utImage.getScaledInstance(80,80,Image.SCALE_DEFAULT));
			JLabel udLabel=new JLabel(utIcon);
			this.trainerPanel.add(udLabel);
			
			JPanel trainerContentPanel=new JPanel(new GridLayout(3,0,0,0));
			JLabel trainerNameLabel=new JLabel(name,JLabel.CENTER);
			trainerNameLabel.setFont(new Font("Arial",Font.BOLD,15));
			JLabel trainerTypeLabel=new JLabel(type,JLabel.CENTER);
			trainerContentPanel.add(trainerNameLabel);
			trainerContentPanel.add(trainerTypeLabel);
			
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
}
