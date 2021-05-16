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
	public ErrorMessage(int i){
		JPanel messagePanel=new JPanel(new FlowLayout());
		
		JLabel exContent=new JLabel("Wrong ID or password!",JLabel.CENTER);
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

	public void actionPerformed(ActionEvent e){
		if(e.getSource()==jbtOK){
			dispose();
		}
	}
}