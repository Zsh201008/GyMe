import java.awt.event.*;
import java.io.*;
import java.awt.*;
import javax.swing.*;

public class Confirm extends JFrame implements ActionListener{
	String trainerName;
	String trainerID;
	String userID;
	JButton jbtOK;

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

	public void actionPerformed(ActionEvent e){
		if(e.getSource()==jbtOK){
			try {
				File file=new File("files/User_Trainer.csv");
				RandomAccessFile raf=new RandomAccessFile(file, "rw");
				raf.seek(raf.length());
				raf.writeBytes(userID+","+trainerID+"\n");
				raf.close();
				Success s=new Success("Your new trainer is: "+this.trainerName);
			}
			catch(Exception ex) {
				ErrorMessage em=new ErrorMessage();
				ex.printStackTrace();
			}
			dispose();
		}
	}
}