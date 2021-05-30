package application;

import java.awt.event.*;
import java.io.*;
import java.awt.*;
import javax.swing.*;

public class Notification extends JFrame implements ActionListener{
	private JButton jbtOK;
	private boolean isNoti=false;

	public Notification(String[] pairList){
		JPanel messagePanel=new JPanel(new GridLayout(4,0,0,0));
		try {
			FileReader bookFr=new FileReader("files/Booking.csv");
			BufferedReader bookBr=new BufferedReader(bookFr);
			String bookLine=bookBr.readLine();
			String booking1="";
			String booking2="";
			while(bookLine!=null) {
				String[] bookAttr=bookLine.split(",");
				if(bookAttr[1].equals(pairList[0])) {
					if(bookAttr[5].equals("on")) {
						booking1=bookAttr[2];
						booking2=bookAttr[3]+": "+bookAttr[4];
						isNoti=true;
					}
				}
				bookLine=bookBr.readLine();
			}
			bookBr.close();
			bookFr.close();
			
			JLabel exContent=new JLabel("You have a live session",JLabel.CENTER);
			messagePanel.add(exContent);
			JLabel textLabel1=new JLabel(booking1,JLabel.CENTER);
			messagePanel.add(textLabel1);
			JLabel textLabel2=new JLabel(booking2,JLabel.CENTER);
			messagePanel.add(textLabel2);
			
			JPanel buttonPanel=new JPanel(new FlowLayout());
			jbtOK=new JButton("I GOT IT");
			jbtOK.setSize(50,30);
			jbtOK.addActionListener(this);
			buttonPanel.add(jbtOK);
			messagePanel.add(buttonPanel);
		}
		catch(Exception e) {
			ErrorMessage em=new ErrorMessage();
			e.printStackTrace();
		}
		if(isNoti==false)
			dispose();
		else {
			getContentPane().add(messagePanel);
			setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			setSize(230,210);
			setLocation(500,300);
			setVisible(true);
		}
	}
	public boolean checkNoti() {
		return this.isNoti;
	}
	public void actionPerformed(ActionEvent e){
		if(e.getSource()==jbtOK)
			dispose();
	}
}