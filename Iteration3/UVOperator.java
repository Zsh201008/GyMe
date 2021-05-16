import java.io.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.awt.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class UVOperator implements ActionListener{
	private JPanel uvPanel;
	private JTextField searchTag;
	private JButton search;
	private String thisID;
	public UVOperator(String userID) {
		thisID=userID;
		this.uvPanel=new JPanel(new BorderLayout());
		this.uvPanel.add(searchVideo(),BorderLayout.NORTH);
		this.uvPanel.add(displayVideo(""),BorderLayout.CENTER);
		this.uvPanel.setOpaque(false);
		this.uvPanel.setBorder(new EmptyBorder(10,20,10,20));
	}
	public JPanel searchVideo() {
		JPanel searchPanel=new JPanel();
		JLabel searchLabel=new JLabel("Search by Tag: ");
		searchTag=new JTextField(60);
		search=new JButton("search");
		search.addActionListener(this);
		searchPanel.add(searchLabel);
		searchPanel.add(searchTag);
		searchPanel.add(search);
		searchPanel.setOpaque(false);
		searchPanel.setBorder(new EmptyBorder(0,0,10,0));
		return searchPanel;
	}
	public JPanel displayVideo(String tag) {
		JPanel videoPanel=new JPanel(new GridLayout(0,3,15,15));
		try {
			FileReader fr=new FileReader("./files/Videos.csv");
			BufferedReader br=new BufferedReader(fr);
			String newLine=br.readLine();
			while(newLine!=null) {
				String[] attributes=newLine.split(",");
				String videoName=attributes[1];
				String videoType=attributes[2];
				String videoPath=attributes[3];
				String videoImage=attributes[4];
				if(tag.equals("")) {
					Video v=new Video(thisID,attributes[0],videoName,videoType,videoPath,videoImage,230,120,15,12);
					videoPanel.add(v.getPanel());
				}
				else {
					if(videoType.equals(tag)) {
						Video v=new Video(thisID,attributes[0],videoName,videoType,videoPath,videoImage,230,120,15,12);
						videoPanel.add(v.getPanel());
					}
				}
				newLine=br.readLine();
			}
			br.close();
			fr.close();
		}
		catch(Exception e) {
			ErrorMessage em=new ErrorMessage();
			e.printStackTrace();
		} 
		videoPanel.setPreferredSize(new Dimension(1000,530));
		videoPanel.setOpaque(false);
		return videoPanel;
	}
	public JPanel getUVPanel() {
		return this.uvPanel;
	}
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==this.search) {
			String tag=this.searchTag.getText();
			this.uvPanel.removeAll();
			this.uvPanel.add(searchVideo(),BorderLayout.NORTH);
			this.uvPanel.add(displayVideo(tag),BorderLayout.CENTER);
			this.uvPanel.setVisible(false);
			this.uvPanel.setVisible(true);
		}
	}
}
