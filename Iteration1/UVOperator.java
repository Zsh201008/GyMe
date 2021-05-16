import java.io.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.awt.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class UVOperator{
	private JPanel uvPanel;
	private int NUM=9;
	public UVOperator(String userID) {
		this.uvPanel=new JPanel(new BorderLayout(20,20));
		try {
			JPanel videoImagePanel=new JPanel(new GridLayout(0,3,10,10));
			FileReader fr=new FileReader("./files/Videos.csv");
			BufferedReader br=new BufferedReader(fr);
			String newLine=br.readLine();
			while(newLine!=null) {
				String[] attributes=newLine.split(",");
				String videoName=attributes[0];
				String videoType=attributes[1];
				String videoPath=attributes[2];
				String videoImage=attributes[3];
				Video v=new Video(videoName,videoType,videoPath,videoImage);
				videoImagePanel.add(v.getPanel());
				newLine=br.readLine();
			}
			videoImagePanel.setOpaque(false);
			this.uvPanel.add(videoImagePanel);
			this.uvPanel.setOpaque(false);
			this.uvPanel.setBorder(new EmptyBorder(10,30,10,30));
		}
		catch(Exception e) {
			ErrorMessage em=new ErrorMessage();
			e.printStackTrace();
		}
	}
	public JPanel getUVPanel() {
		return this.uvPanel;
	}
}
