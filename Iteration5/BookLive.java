/**
 * Title: BookLive.java
 * Controller class of booking live session.
 * @author Shuhan Zhang
 * @version 5.0
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.io.*;

public class BookLive extends JFrame implements ActionListener{
	private JComboBox<String> year;
	private JComboBox<String> month;
	private JComboBox<String> day;
	private JComboBox<String> fromHour;
	private JComboBox<String> fromMin;
	private JComboBox<String> toHour;
	private JComboBox<String> toMin;
	private JComboBox<String> platform;
	private JTextField room;
	private JButton submit;
	private String pairID;
	private String userID;
	private int MAX_NOTI=1000;
	public BookLive(String userID,String pairID) {
		this.userID=userID;
		this.pairID=pairID;
		JPanel insertPane=new JPanel(new GridLayout(6,0,10,10));
		
		JPanel titlePane=new JPanel();
		JLabel titleLabel1=new JLabel("Submit this form to book a live session!",JLabel.CENTER);
		titlePane.add(titleLabel1);
		titlePane.setOpaque(false);
		titlePane.setBorder(new EmptyBorder(20,0,0,0));
		insertPane.add(titlePane);
		
		JPanel timePanel1=new JPanel();
		JLabel yearLabel=new JLabel("Year: ",JLabel.CENTER);
		JLabel monthLabel=new JLabel("Month: ",JLabel.CENTER);
		JLabel dayLabel=new JLabel("Day: ",JLabel.CENTER);
		this.year=new JComboBox<String>();
		this.year.addItem(""+2021);
		this.year.addActionListener(this);
		this.month=new JComboBox<String>();
		for(int i=1;i<=12;i++) {
			if(i<10)
				this.month.addItem("0"+i);
			else
				this.month.addItem(""+i);
		}
		this.month.addActionListener(this);
		this.day=new JComboBox<String>();
		for(int i=1;i<=30;i++) {
			if(i<10)
				this.day.addItem("0"+i);
			else
				this.day.addItem(""+i);
		}
		this.day.addActionListener(this);
		timePanel1.add(yearLabel);
		timePanel1.add(this.year);
		timePanel1.add(monthLabel);
		timePanel1.add(this.month);
		timePanel1.add(dayLabel);
		timePanel1.add(this.day);
		insertPane.add(timePanel1);
		
		JPanel timePanel2=new JPanel();
		JLabel fromLabel=new JLabel("From: ",JLabel.CENTER);
		this.fromHour=new JComboBox<String>();
		for(int i=8;i<=23;i++) {
			if(i<10)
				this.fromHour.addItem("0"+i);
			else
				this.fromHour.addItem(""+i);
		}	
		this.fromHour.addActionListener(this);
		this.fromMin=new JComboBox<String>();
		for(int i=0;i<60;i++) {
			if(i<10)
				this.fromMin.addItem("0"+i);
			else
				this.fromMin.addItem(""+i);
		}
		this.fromMin.addActionListener(this);
		JLabel toLabel=new JLabel("To: ",JLabel.CENTER);
		this.toHour=new JComboBox<String>();
		for(int i=8;i<=23;i++) {
			if(i<10)
				this.toHour.addItem("0"+i);
			else
				this.toHour.addItem(""+i);
		}	
		this.toHour.addActionListener(this);
		this.toMin=new JComboBox<String>();
		for(int i=0;i<60;i++) {
			if(i<10)
				this.toMin.addItem("0"+i);
			else
				this.toMin.addItem(""+i);
		}
		this.toMin.addActionListener(this);
		timePanel2.add(fromLabel);
		timePanel2.add(this.fromHour);
		timePanel2.add(this.fromMin);
		timePanel2.add(toLabel);
		timePanel2.add(this.toHour);
		timePanel2.add(this.toMin);
		insertPane.add(timePanel2);
		
		JPanel platformPanel=new JPanel();
		JLabel platformLabel=new JLabel("Platform: ",JLabel.CENTER);
		this.platform=new JComboBox<String>();
		this.platform.addItem("Zoom");
		this.platform.addItem("TencentMeeting");
		this.platform.addItem("CCTalk");
		this.platform.addActionListener(this);
		platformPanel.add(platformLabel);
		platformPanel.add(this.platform);
		insertPane.add(platformPanel);
		
		JPanel roomPanel=new JPanel();
		JLabel roomLabel=new JLabel("Room: ",JLabel.CENTER);
		this.room=new JTextField(20);
		roomPanel.add(roomLabel);
		roomPanel.add(this.room);
		insertPane.add(roomPanel);
		
		JPanel buttonPane=new JPanel();
		this.submit=new JButton("Confirm and Submit");
		this.submit.addActionListener(this);
		buttonPane.add(this.submit);
		insertPane.add(buttonPane);
		//insertPane.setBorder(new EmptyBorder(20,0,0,0));
		
		Image icon;
		ImageIcon ig = new ImageIcon("imgs/icon.jpg");
		icon = ig.getImage();
		setIconImage(icon);
		
		Container content=this.getContentPane();
		JPanel myPanel=(JPanel)content;
		myPanel.setOpaque(false);
		myPanel.add(insertPane);
		setTitle("Book Live Session");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(500,400);
		setLocation(300,200);
		setVisible(true);
	}
	
	public boolean writeToFile() {
		boolean isOK=false;
		try {
			String wYear=this.year.getSelectedItem().toString();
			String wMonth=this.month.getSelectedItem().toString();
			String wDay=this.day.getSelectedItem().toString();
			String wFromHour=this.fromHour.getSelectedItem().toString();
			String wFromMin=this.fromMin.getSelectedItem().toString();
			String wToHour=this.toHour.getSelectedItem().toString();
			String wToMin=this.toMin.getSelectedItem().toString();
			String wPlatform=this.platform.getSelectedItem().toString();
			String wRoom=this.room.getText();
			int bid=(int)(Math.random()*10000)+12000;
			String bookID="b"+bid;
			String state="unconfirm";
			String wString=bookID+","+this.pairID+","+wYear+"-"+wMonth+"-"+wDay+" "+wFromHour+":"+wFromMin+"~"+wToHour+":"+wToMin+","
						+wPlatform+","+wRoom+","+state+"\n";
			RandomAccessFile raf=new RandomAccessFile("files/Booking.csv", "rw");
			raf.seek(raf.length());
			raf.writeBytes(wString);
			raf.close();
			
			FileReader fr=new FileReader("files/User.csv");
			BufferedReader br=new BufferedReader(fr);
			String[] tempUser=new String[1000];
			int count=0;
			String line=br.readLine();
			while(line!=null) {
				String[] attr=line.split(",");
				tempUser[count]="";
				if(attr[0].equals(this.userID)) {
					int classNum=Integer.parseInt(attr[9]);
					classNum+=1;
					attr[9]=classNum+"";
				}
				for(int j=0;j<11;j++)
					tempUser[count]=tempUser[count]+attr[j]+",";
				tempUser[count]=tempUser[count]+attr[11];
				count++;
				line=br.readLine();
			}
			br.close();
			fr.close();
			
			FileWriter fw=new FileWriter("files/User.csv");
			BufferedWriter bw=new BufferedWriter(fw);
			for(int k=0;k<count;k++)
				bw.write(tempUser[k]+"\n");
			bw.close();
			fw.close();
			
			int nid=(int)(Math.random()*10000)+10000;
			String notiID="n"+nid;
			String wStr=notiID+","+this.pairID+",live,"+bookID;
			NotiToFile ntf=new NotiToFile("trainer",wStr);
			isOK=ntf.notiToFile();
		}
		catch(Exception e) {
			ErrorMessage em=new ErrorMessage();
			e.printStackTrace();
		}
		return isOK;
	}
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==this.submit) {
			Success s;
			ErrorMessage em;
			boolean isOK=writeToFile();
			if(isOK)
				s=new Success("Please wait for your trainer to confirm");
		}
		else if(e.getSource()==this.year) {
			int iYear=this.year.getSelectedIndex();
			this.year.setSelectedIndex(iYear);
		}
		else if(e.getSource()==this.month) {
			int iMonth=this.month.getSelectedIndex();
			this.month.setSelectedIndex(iMonth);
		}
		else if(e.getSource()==this.day) {
			int iDay=this.day.getSelectedIndex();
			this.day.setSelectedIndex(iDay);
		}
		else if(e.getSource()==this.fromHour) {
			int iFromHour=this.fromHour.getSelectedIndex();
			this.fromHour.setSelectedIndex(iFromHour);
		}
		else if(e.getSource()==this.fromMin) {
			int iFromMin=this.fromMin.getSelectedIndex();
			this.fromMin.setSelectedIndex(iFromMin);
		}
		else if(e.getSource()==this.toHour) {
			int iToHour=this.toHour.getSelectedIndex();
			this.toHour.setSelectedIndex(iToHour);
		}
		else if(e.getSource()==this.toMin) {
			int iToMin=this.toMin.getSelectedIndex();
			this.toMin.setSelectedIndex(iToMin);
		}
		else if(e.getSource()==this.platform) {
			int iPlatform=this.platform.getSelectedIndex();
			this.platform.setSelectedIndex(iPlatform);
		}
	}
}
