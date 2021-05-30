import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.*;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class UDOperator {
	private JPanel udPanel;
	private int NUM=4;
	public UDOperator(String ID) {
		this.udPanel=new JPanel(new BorderLayout(20,20));
		try {
			JPanel discoverPanel=new JPanel(new GridLayout(0,2,20,20));
			for(int i=1;i<=NUM;i++) {
				String filename="./imgs/UD/"+i+".png";
				BufferedImage udImage=ImageIO.read(new File(filename));
				ImageIcon udIcon=new ImageIcon();
				udIcon.setImage(udImage.getScaledInstance(450,180,Image.SCALE_DEFAULT));
				JLabel udLabel=new JLabel(udIcon);
				discoverPanel.add(udLabel);
			}
			discoverPanel.setOpaque(false);
			JPanel titlePanel=new JPanel(new GridLayout(1,0,0,0));
			JLabel titleLabel=new JLabel("Pick and join interesting activities!",JLabel.CENTER);
			titleLabel.setFont(new Font("Arial",Font.BOLD,18));
			titlePanel.add(titleLabel);
			titlePanel.setOpaque(false);
			this.udPanel.add(titlePanel,BorderLayout.NORTH);
			this.udPanel.add(discoverPanel,BorderLayout.CENTER);
			this.udPanel.setOpaque(false);
			this.udPanel.setBorder(new EmptyBorder(30,30,30,30));
		}
		catch(Exception e) {
			ErrorMessage em=new ErrorMessage();
			e.printStackTrace();
		}
	}
	public JPanel getUDPanel() {
		return this.udPanel;
	}
}
