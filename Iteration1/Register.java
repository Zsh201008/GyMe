import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.io.*;

public class Register extends JFrame implements ActionListener{
	public JComboBox<String> identity;
	public JTextField name;
	public JTextField password;
	public JTextField dob;
	public JTextField telephone;
	public JComboBox<String> sex;
	public JTextField address;
	public JButton submit;
	public JButton back;
	public Image icon;
	public Register() {
		JPanel insertPane=new JPanel(new GridLayout(11,0,5,5));
		
		JPanel titlePane=new JPanel();
		JLabel titleLabel=new JLabel("Let's get started!",JLabel.CENTER);
		titlePane.add(titleLabel);
		titlePane.setOpaque(false);
		insertPane.add(titlePane);
		
		JPanel idPane=new JPanel();
		JLabel idLabel=new JLabel("You register as a ",JLabel.CENTER);
		this.identity=new JComboBox<String>();
		this.identity.addItem("User");
		this.identity.addItem("Trainer");
		this.identity.addActionListener(this);
		idPane.add(idLabel);
		idPane.add(this.identity);
		idPane.setOpaque(false);
		insertPane.add(idPane);
		
		JPanel namePane=new JPanel();
		JLabel nameLabel=new JLabel("Your name: ",JLabel.CENTER);
		this.name=new JTextField(30);
		namePane.add(nameLabel);
		namePane.add(this.name);
		namePane.setOpaque(false);
		insertPane.add(namePane);
		
		JPanel passwordPane=new JPanel();
		JLabel passwordLabel=new JLabel("Your password: ",JLabel.CENTER);
		this.password=new JTextField(30);
		passwordPane.add(passwordLabel);
		passwordPane.add(this.password);
		passwordPane.setOpaque(false);
		insertPane.add(passwordPane);
		
		JPanel sexPane=new JPanel();
		JLabel sexLabel=new JLabel("Your sex: ",JLabel.CENTER);
		this.sex=new JComboBox<String>();
		this.sex.addItem("M");
		this.sex.addItem("F");
		this.sex.addActionListener(this);
	    sexPane.add(sexLabel);
		sexPane.add(this.sex);
		sexPane.setOpaque(false);
		insertPane.add(sexPane);
		
		JPanel dobPane=new JPanel();
		JLabel dobLabel=new JLabel("Your date of birth: ",JLabel.CENTER);
		this.dob=new JTextField(30);
		dobPane.add(dobLabel);
		dobPane.add(this.dob);
		dobPane.setOpaque(false);
		insertPane.add(dobPane);
		
		JPanel telePane=new JPanel();
		JLabel teleLabel=new JLabel("Your telephone num: ",JLabel.CENTER);
		this.telephone=new JTextField(30);
		telePane.add(teleLabel);
		telePane.add(this.telephone);
		telePane.setOpaque(false);
		insertPane.add(telePane);
		
		JPanel addressPane=new JPanel();
		JLabel supIDLabel=new JLabel("Your address: ",JLabel.CENTER);
		this.address=new JTextField(30);
		addressPane.add(supIDLabel);
		addressPane.add(this.address);
		addressPane.setOpaque(false);
		insertPane.add(addressPane);
		
		JPanel buttonPane=new JPanel();
		this.submit=new JButton("Submit and Register");
		this.back=new JButton("Back to Login");
		this.back.addActionListener(this);
		this.submit.addActionListener(this);
		buttonPane.add(this.submit);
		buttonPane.add(this.back);
		buttonPane.setOpaque(false);
		insertPane.add(buttonPane);
		insertPane.setBorder(new EmptyBorder(20,0,0,0));
		insertPane.setOpaque(false);
		
//		ImageIcon img = new ImageIcon("imgs/register.png");
//		JLabel imgLabel = new JLabel(img);
		
		ImageIcon ig = new ImageIcon("imgs/icon.jpg");
		icon = ig.getImage();
		setIconImage(icon);
		
		Container content=this.getContentPane();
		JPanel myPanel=(JPanel)content;
		myPanel.setOpaque(false);
//		this.getLayeredPane().setLayout(null);
		myPanel.add(insertPane);
//		this.getLayeredPane().add(imgLabel, new Integer(Integer.MIN_VALUE));
//		imgLabel.setBounds(0, 0, 500, 550);
		setTitle("Register to GyMe");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(500,550);
		setLocation(300,200);
		setVisible(true);
	}
	
	// Need to check validity
	public int ValidityCheck(String cname, String cpassword, String cdob, String ctelephone, String caddress){
		// Create numeric variables for representing the date.
		int yearNum = 0;
		int monthNum = 0;
		int dateNum = 0;
		
		// 1. check if name is validate:
		String[] nameParts = cname.split(" ");
		if(nameParts.length == 2) {
			for(int i = 0; i < nameParts.length; i++) {
				if(nameParts[i].length() > 15) {
					return 1;
				}
				for(int j = 0; j < nameParts[i].length(); j++) {
					if(!Character.isUpperCase(nameParts[i].charAt(0))) {
						return 1;
					}else if(!Character.isLetterOrDigit(nameParts[i].charAt(j))) {
						return 1;
					}
				}
			}
		}else {
			return 1;
		}
		
		// 2. check if password is validate:
		if(cpassword.length() <= 6 && cpassword.length() > 0) {
			for(int i = 0; i <  cpassword.length(); i++) {
				if(!Character.isLetterOrDigit(cpassword.charAt(i))) {
					return 2;
				}
			}
		}else {
			return 2;
		}
		
		// 3. check dob
		String[] dobParts = cdob.split("/");
		// First judge if this is digital
		if(dobParts.length != 3) {
			return 3;
		}
		for(int i = 0; i < dobParts[0].length(); i++) {
			if(!Character.isDigit(dobParts[0].charAt(i))) {
				return 3;
			}
		}
		yearNum = Integer.parseInt(dobParts[0]);
		for(int i = 0; i < dobParts[1].length(); i++) {
			if(!Character.isDigit(dobParts[0].charAt(i))) {
				return 3;
			}
		}
		monthNum = Integer.parseInt(dobParts[1]);
		for(int i = 0; i < dobParts[2].length(); i++) {
			if(!Character.isDigit(dobParts[0].charAt(i))) {
				return 3;
			}
		}
		dateNum = Integer.parseInt(dobParts[2]);
		
		if(yearNum < 1900 || yearNum > 2021) {
			return 3;
		}
		if(monthNum <= 0 || monthNum > 12) {
			return 3;
		}
		else if(monthNum == 2) {
			if(((yearNum%4 == 0 && yearNum%100 != 0) || yearNum%400 == 0)) {
				if(dateNum  <= 0 || dateNum > 29) {
					return 3;
				}
			}
			else {
				if(dateNum <= 0 || dateNum > 28) {
					return 3;
				}
			}
		}
		else if(monthNum == 4 || monthNum == 6 || monthNum == 9 || monthNum == 11) {
			if(dateNum <= 0 || dateNum > 30) {
				return 3;
			}
				
		}
		else{
			if(dateNum <= 0 || dateNum >31) {
				return 3;
			}
		}
		
		
		// 4. check telephone no.
		if(ctelephone.length() != 11) {
			return 4;
		}
		
		// 5. check addr
		if( caddress.length() == 0 || caddress.length() >= 25) {
			return 5;
		}
		
		return 0;
	}
	
	public String writeToFile() throws Exception {
		String wacc="";
		int accNo=(int)(Math.random()*1000+20210);
		String wname=this.name.getText();
		String wpassword=this.password.getText();
		String wsex=this.sex.getSelectedItem().toString();
		String wdob=this.dob.getText();
		String wtelephone=this.telephone.getText();
		String rank="primary";
		String type="C";
		String waddress=this.address.getText();
		int wclassnum=0;
		String wid=this.identity.getSelectedItem().toString();
		File file=null;
		if(wid.equals("User")) {
			wacc="u"+accNo;
			file=new File("files/User.csv");
		}
		else {
			wacc="t"+accNo;
			file=new File("files/Trainer.csv");
		}
		
		if(ValidityCheck(wname, wpassword, wdob, wtelephone, waddress) != 0) {
			wacc = "-1";
		}
		else {
			String wString=wacc+","+wpassword+","+wname+","+wsex+","+wdob+","+wtelephone+","+rank+","+type+","+waddress+","+wclassnum+"\n";
			RandomAccessFile raf=new RandomAccessFile(file, "rw");
			raf.seek(raf.length());
			raf.writeBytes(wString);
			raf.close();
		}

		return wacc;
	}
	public void actionPerformed(ActionEvent e) {
		Success s;
		ErrorMessage em;
		if(e.getSource()==this.submit) {
			try {
				String acc=writeToFile();
				if(acc != "-1")
					s = new Success("Your account is: "+acc);
				else
					em=new ErrorMessage();
			}
			catch(Exception ex) {
				em=new ErrorMessage();
				ex.printStackTrace();
			}
		}
		else if(e.getSource()==this.back) {
			dispose();
		}
		else if(e.getSource()==this.identity) {
			String id=this.identity.getSelectedItem().toString();
			this.identity.setSelectedItem(id);
		}
		else if(e.getSource()==this.sex) {
			String string=this.sex.getSelectedItem().toString();
			this.sex.setSelectedItem(string);
		}
	}
}
