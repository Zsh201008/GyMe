/**
 * Title: AddTrainer.java
 * Controller class of user adding new trainer.
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

public class AddTrainer extends JFrame{
	private int NUM=7;
	public AddTrainer(String ID) {
		try {
			Container contentPane=this.getContentPane();
			contentPane.setLayout(new BorderLayout());
			JPanel selectTPanel=new JPanel(new BorderLayout(10,10));
			JPanel selectTTitlePanel=new JPanel();
			JLabel selectTTitleLabel=new JLabel("Click 'select' button to pick him/her! ");
			selectTTitleLabel.setFont(new Font("Arial",Font.BOLD,14));
			selectTTitlePanel.add(selectTTitleLabel);
			selectTTitlePanel.setBorder(new EmptyBorder(10,0,0,0));
			contentPane.add(selectTTitlePanel,BorderLayout.NORTH);
			
			JPanel selectTTPanel=new JPanel(new GridLayout(0,4,10,10));
			FileReader fr=new FileReader("files/Trainer.csv");
			BufferedReader br=new BufferedReader(fr);
			String newLine=br.readLine();
			int i=0;
			while(newLine!=null&&i<NUM) {
				String[] attributes=newLine.split(",");
				Trainer t=new Trainer(ID,attributes[0],attributes[1],attributes[3],"imgs/Trainer/"+attributes[4]);
				selectTTPanel.add(t.getPanel());
				newLine=br.readLine();
				i++;
			}
			br.close();
			fr.close();
			selectTTPanel.setBorder(new EmptyBorder(0,10,10,10));
			contentPane.add(selectTTPanel,BorderLayout.CENTER);
			
			Image icon;
			ImageIcon ig = new ImageIcon("imgs/icon.jpg");
			icon = ig.getImage();
			setIconImage(icon);
			
			setTitle("Select Trainer");
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			setSize(500,500);
			setLocation(250,100);
			setVisible(true);
		}
		catch(Exception e) {
			ErrorMessage em=new ErrorMessage();
			e.printStackTrace();
		}
	}
}
