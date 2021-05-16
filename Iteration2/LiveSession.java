import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class LiveSession implements ActionListener{
	private JPanel bookPanel;
	private JButton cancel;
	private String bookID;
	private int MAX_BOOKING=1000;
	
	public LiveSession(String booking, String id) {
		this.bookID=id;
		this.bookPanel=new JPanel(new BorderLayout());
		JLabel bookLabel=new JLabel(booking,JLabel.LEFT);
		this.cancel=new JButton("cancel");
		this.cancel.setBackground(new Color(215,240,247));
		this.cancel.addActionListener(this);
		this.bookPanel.add(bookLabel,BorderLayout.CENTER);
		this.bookPanel.add(cancel,BorderLayout.EAST);
		this.bookPanel.setOpaque(false);
	}
	public JPanel getPanel() {
		return this.bookPanel;
	}
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==this.cancel) {
			Confirm cf=new Confirm(this.bookID,"cancelbooking");
		}
	}
}
