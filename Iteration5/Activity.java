/**
 * Title: Activity.java
 * Entity class of gym's online activities for promotion.
 * @author Shuhan Zhang
 * @version 5.0
 */
import java.io.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.awt.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class Activity implements ActionListener{
	private JPanel actPanel;
	private JButton actImgButton;
	private String thisID;
	private String thisImg;
	public Activity(String actID, String actImg, int width, int height) {
		try {
			this.thisID=actID;
			this.thisImg=actImg;
			this.actPanel=new JPanel();
			BufferedImage uvImage=ImageIO.read(new File(actImg));
			ImageIcon uvIcon=new ImageIcon();
			uvIcon.setImage(uvImage.getScaledInstance(width,height,Image.SCALE_DEFAULT));
			actImgButton=new JButton(uvIcon);
			actImgButton.setContentAreaFilled(false);
			actImgButton.setBorderPainted(false);
			actImgButton.addActionListener(this);
			actPanel.add(actImgButton,BorderLayout.CENTER);
			actPanel.setOpaque(false);
		}
		catch(Exception e) {
			ErrorMessage em=new ErrorMessage();
			e.printStackTrace();
		}
	}
	public JPanel getPanel() {
		return this.actPanel;
	}
	public void actionPerformed(ActionEvent e) {
		ActivityDetail ad=new ActivityDetail(thisID,thisImg);
	}
}
