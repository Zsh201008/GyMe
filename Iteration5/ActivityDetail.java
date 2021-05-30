package application;

import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.awt.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class ActivityDetail extends JFrame implements ActionListener{
	public ActivityDetail(String actID,String img) {
		JPanel adPanel=new JPanel(new BorderLayout());
		JPanel messagePanel=new JPanel();
		try {
			FileReader fr=new FileReader("files/Activity.csv");
			BufferedReader br=new BufferedReader(fr);
			String line=br.readLine();
			while(line!=null) {
				String[] attr=line.split(",");
				if(attr[0].equals(actID)) {
					JLabel messageLabel=new JLabel(attr[2]);
					messagePanel.add(messageLabel);
					break;
				}
				line=br.readLine();
			}
			br.close();
			fr.close();
			BufferedImage uvImage=ImageIO.read(new File(img));
			ImageIcon uvIcon=new ImageIcon();
			uvIcon.setImage(uvImage.getScaledInstance(300,120,Image.SCALE_DEFAULT));
			JPanel imgPanel=new JPanel();
			JLabel imgLabel=new JLabel(uvIcon);
			imgPanel.add(imgLabel);
			imgPanel.setBorder(new EmptyBorder(10,10,10,10));
			
			adPanel.add(imgPanel, BorderLayout.CENTER);
			adPanel.add(messagePanel,BorderLayout.SOUTH);
			
			getContentPane().add(adPanel);
			setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			setSize(450,200);
			setLocation(350,350);
			setVisible(true);
		}
		catch(Exception e) {
			ErrorMessage em=new ErrorMessage();
			e.printStackTrace();
		}
	}
	public void actionPerformed(ActionEvent e) {
		
	}
}
