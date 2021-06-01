/**
 * Title: UDController.java
 * Controller class of user viewing activities.
 * @author Shuhan Zhang
 * @version 5.0
 */
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.*;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;

public class UDController {
	private JPanel udPanel;
	private int NUM=4;
	public UDController(String ID) {
		this.udPanel=new JPanel(new BorderLayout(20,20));
		try {
			JPanel discoverPanel=new JPanel(new GridLayout(0,2,20,20));
			FileReader actFr=new FileReader("files/Activity.csv");
			BufferedReader actBr=new BufferedReader(actFr);
			String actLine=actBr.readLine();
			int i=0;
			while(actLine!=null&&i<NUM) {
				String[] actAttr=actLine.split(",");
				String Imgname="./imgs/UD/"+actAttr[1];
				Activity a=new Activity(actAttr[0],Imgname,450,200);
				discoverPanel.add(a.getPanel());
				i++;
				actLine=actBr.readLine();
			}
			actBr.close();
			actFr.close();
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
