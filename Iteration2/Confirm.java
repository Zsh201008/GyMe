import java.awt.event.*;
import java.io.*;
import java.awt.*;
import javax.swing.*;

public class Confirm extends JFrame implements ActionListener{
	private String trainerName;
	private String trainerID;
	private String userID;
	private String operationType;
	private String bookID;
	private JButton jbtOK;
	private int MAX_BOOKING=1000;

	public Confirm(String user, String id, String name, String type){
		this.trainerName=name;
		this.trainerID=id;
		this.userID=user;
		this.operationType=type;
		
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
	public Confirm(String bookID, String type) {
		this.bookID=bookID;
		this.operationType=type;
		JPanel messagePanel=new JPanel(new GridLayout(3,0,0,0));
		
		JLabel exContent=new JLabel("Are you sure to",JLabel.CENTER);
		messagePanel.add(exContent);
		JLabel textLabel=new JLabel("cancel this live session?",JLabel.CENTER);
		messagePanel.add(textLabel);
		
		JPanel buttonPanel=new JPanel(new FlowLayout());
		jbtOK=new JButton("YES");
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
	public void addTrainer() {
		try {
			boolean canAdd=false;
			FileReader fr=new FileReader("files/User.csv");
			BufferedReader br=new BufferedReader(fr);
			String userLine=br.readLine();
			while(userLine!=null) {
				String[] userAttr=userLine.split(",");
				if(userAttr[0].equals(this.userID)) {
					if(userAttr[7].equals("P")) {
						canAdd=true;
						break;
					}
				}
				userLine=br.readLine();
			}
			br.close();
			fr.close();
			
			if(canAdd) {
				File file=new File("files/UserTrainer.csv");
				RandomAccessFile raf=new RandomAccessFile(file, "rw");
				raf.seek(raf.length());
				int pid=(int)(Math.random()*10000)+20000;
				String pairID="p"+pid;
				raf.writeBytes(pairID+","+userID+","+trainerID+"\n");
				raf.close();
				Success s=new Success("Your new trainer is: "+this.trainerName);
			}
			else {
				ErrorMessage em=new ErrorMessage("You are 'Common-type' user,","you have no access to add trainer!");
			}
		}
		catch(Exception ex) {
			ErrorMessage em=new ErrorMessage();
			ex.printStackTrace();
		}
	}
	public void cancelBooking() {
		try {
			FileReader fr=new FileReader("files/Booking.csv");
			BufferedReader br=new BufferedReader(fr);
			String[] bookContent=new String[MAX_BOOKING];
			int bookCount=0;
			String bookLine=br.readLine();
			while(bookLine!=null&&bookCount<MAX_BOOKING) {
				String[] bookAttr=bookLine.split(",");
				if(!this.bookID.equals(bookAttr[0])) {
					bookContent[bookCount]=bookLine;
					bookCount++;
				}
				bookLine=br.readLine();
			}
			br.close();
			fr.close();
			
			FileWriter fw=new FileWriter("files/Booking.csv");
			BufferedWriter bw=new BufferedWriter(fw);
			for(int i=0;i<bookCount;i++)
				bw.write(bookContent[i]+"\n");
			bw.close();
			fw.close();
			Success s=new Success("Live Session Canceled");
		}
		catch(Exception e) {
			ErrorMessage em=new ErrorMessage();
			e.printStackTrace();
		}
	}
	public void actionPerformed(ActionEvent e){
		if(e.getSource()==jbtOK&&this.operationType.equals("addtrainer")){
			addTrainer();
			dispose();
		}
		else if(e.getSource()==this.jbtOK&&this.operationType.equals("cancelbooking")) {
			cancelBooking();
			dispose();
		}
	}
}