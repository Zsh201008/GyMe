import java.io.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.awt.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class Video implements ActionListener{
	private JPanel videoPanel;
	private String theVideoPath;
	private JButton videoImgButton;
	public Video(String videoName, String videoType, String videoPath, String videoImg, int width, int height,int namefont, int typefont) {
		try {
			this.theVideoPath="D:\\Eclipse_workspace\\GyMe\\videos\\"+videoPath;
			this.videoPanel=new JPanel(new BorderLayout(7,7));
			BufferedImage uvImage=ImageIO.read(new File(videoImg));
			ImageIcon uvIcon=new ImageIcon();
			uvIcon.setImage(uvImage.getScaledInstance(width,height,Image.SCALE_DEFAULT));
			videoImgButton=new JButton(uvIcon);
			videoImgButton.setContentAreaFilled(false);
			videoImgButton.setBorderPainted(false);
			videoImgButton.addActionListener(this);
			videoPanel.add(videoImgButton,BorderLayout.CENTER);
			
			JPanel videoContentPanel=new JPanel(new GridLayout(2,0,3,3));
			JLabel videoNameLabel=new JLabel(videoName,JLabel.CENTER);
			videoNameLabel.setFont(new Font("Arial",Font.BOLD,namefont));
			JLabel videoTypeLabel=new JLabel("Type: "+videoType,JLabel.CENTER);
			videoTypeLabel.setFont(new Font("Arial",Font.BOLD,typefont));
			videoContentPanel.add(videoNameLabel);
			videoContentPanel.add(videoTypeLabel);
			videoContentPanel.setOpaque(false);
			videoPanel.add(videoContentPanel,BorderLayout.SOUTH);
			videoPanel.setOpaque(false);
		}
		catch(Exception e) {
			ErrorMessage em=new ErrorMessage();
			e.printStackTrace();
		}
	}
	public JPanel getPanel() {
		return this.videoPanel;
	}
	public void actionPerformed(ActionEvent e) {
		try {
			ProcessBuilder pb=new ProcessBuilder("C:\\Program Files\\Windows Media Player\\wmplayer.exe",this.theVideoPath);
			pb.start();
		}
		catch(Exception ex) {
			ErrorMessage em=new ErrorMessage();
			ex.printStackTrace();
		}
	}
}
