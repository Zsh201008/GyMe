import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

public class Success extends JFrame implements ActionListener{

	JButton jbtOK;

	public Success(String text){
		JPanel messagePanel=new JPanel(new GridLayout(3,0,0,0));
		
		JLabel exContent=new JLabel("Operation Done! ",JLabel.CENTER);
		messagePanel.add(exContent);
		JLabel textLabel=new JLabel(text,JLabel.CENTER);
		messagePanel.add(textLabel);
		
		JPanel buttonPanel=new JPanel(new FlowLayout());
		jbtOK=new JButton("OK");
		jbtOK.setSize(50,30);
		jbtOK.addActionListener(this);
		buttonPanel.add(jbtOK);
		messagePanel.add(buttonPanel);
		
		getContentPane().add(messagePanel);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(230,150);
		setLocation(350,350);
		setVisible(true);
	}

	public void actionPerformed(ActionEvent e){
		if(e.getSource()==jbtOK){
			dispose();
		}
	}
}