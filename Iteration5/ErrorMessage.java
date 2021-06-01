/**
 * Title: ErrorMessage.java
 * Class of generating a message for error or wrong operation.
 * @author Shuhan Zhang
 * @version 5.0
 */
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

public class ErrorMessage extends JFrame implements ActionListener{

	JButton jbtOK;

	public ErrorMessage(){
		JPanel messagePanel=new JPanel(new FlowLayout());
		
		JLabel exContent=new JLabel("System Error!",JLabel.CENTER);
		messagePanel.add(exContent);
	
		JPanel buttonPanel=new JPanel(new FlowLayout());
		jbtOK=new JButton("OK");
		jbtOK.setSize(50,30);
		jbtOK.addActionListener(this);
		buttonPanel.add(jbtOK);
		messagePanel.add(buttonPanel);
		
		getContentPane().add(messagePanel);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(120,105);
		setLocation(350,350);
		setVisible(true);
	}
	public ErrorMessage(String text){
		JPanel messagePanel=new JPanel(new FlowLayout());
		
		JLabel exContent=new JLabel(text,JLabel.CENTER);
		messagePanel.add(exContent);
	
		JPanel buttonPanel=new JPanel(new FlowLayout());
		jbtOK=new JButton("OK");
		jbtOK.setSize(50,30);
		jbtOK.addActionListener(this);
		buttonPanel.add(jbtOK);
		messagePanel.add(buttonPanel);
		
		getContentPane().add(messagePanel);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(200,105);
		setLocation(350,350);
		setVisible(true);
	}
	public ErrorMessage(String text1, String text2){
		JPanel messagePanel=new JPanel(new FlowLayout());
		
		JLabel content1=new JLabel(text1,JLabel.CENTER);
		JLabel content2=new JLabel(text2,JLabel.CENTER);
		messagePanel.add(content1);
		messagePanel.add(content2);
	
		JPanel buttonPanel=new JPanel(new FlowLayout());
		jbtOK=new JButton("OK");
		jbtOK.setSize(50,30);
		jbtOK.addActionListener(this);
		buttonPanel.add(jbtOK);
		messagePanel.add(buttonPanel);
		
		getContentPane().add(messagePanel);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(250,130);
		setLocation(350,350);
		setVisible(true);
	}
	public void actionPerformed(ActionEvent e){
		if(e.getSource()==jbtOK){
			dispose();
		}
	}
}