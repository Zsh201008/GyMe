import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;

public class Login extends JFrame implements ActionListener{
	public JButton loginButton;
	public JButton registerButton;
	public JTextField inputID;
	public JPasswordField inputPassword;
	public JComboBox<String> loginIdentity;
	public Image icon;
	
	public Login() throws ClassNotFoundException{
		Container content=getContentPane();
		content.setLayout(new FlowLayout());
		JPanel content0=new JPanel(new GridLayout(5,0,15,15));
		
		JPanel titlePanel=new JPanel();
		JLabel titleLabel=new JLabel("Welcome to GyMe",JLabel.CENTER);
		titleLabel.setFont(new Font("Arial",Font.BOLD,18));
		titlePanel.add(titleLabel);
		titlePanel.setOpaque(false);
		
		JPanel idPane=new JPanel();
		JLabel idLabel=new JLabel("Login ID: ",JLabel.CENTER);
		this.inputID=new JTextField(20);
		idPane.add(idLabel);
		idPane.add(this.inputID);
		idPane.setOpaque(false);
		
		JPanel passwordPane=new JPanel();
		JLabel passwordLabel=new JLabel("Password: ",JLabel.CENTER);
		this.inputPassword=new JPasswordField(20);
		passwordPane.add(passwordLabel);
		passwordPane.add(this.inputPassword);
		passwordPane.setOpaque(false);
		
		JPanel comboPane=new JPanel();
		JLabel comboLabel=new JLabel("Your identity: ",JLabel.CENTER);
		this.loginIdentity=new JComboBox<String>();
		this.loginIdentity.addItem("User");
		this.loginIdentity.addItem("Trainer");
		this.loginIdentity.addItem("Administrator");
		this.loginIdentity.setSelectedIndex(-1);
		this.loginIdentity.addActionListener(this);
		comboPane.add(comboLabel);
		comboPane.add(this.loginIdentity);
		comboPane.setOpaque(false);
		
		JPanel buttonPane=new JPanel();
		this.loginButton=new JButton("login");
		this.loginButton.addActionListener(this);
		this.registerButton=new JButton("register");
		this.registerButton.addActionListener(this);
		buttonPane.add(this.loginButton);
		buttonPane.add(this.registerButton);
		buttonPane.setOpaque(false);
		
		ImageIcon img = new ImageIcon("imgs/login.jpg");
		JLabel imgLabel = new JLabel(img);
		
		ImageIcon ig = new ImageIcon("imgs/icon.jpg");
		icon = ig.getImage();
		setIconImage(icon);
		
		JPanel myPanel=(JPanel)content;
		myPanel.setOpaque(false);
		this.getLayeredPane().setLayout(null);
		content0.add(titlePanel);
		content0.add(idPane);
		content0.add(passwordPane);
		content0.add(comboPane);
		content0.add(buttonPane);
		content0.setOpaque(false);
		myPanel.add(content0);
		this.getLayeredPane().add(imgLabel, new Integer(Integer.MIN_VALUE));
		imgLabel.setBounds(0,0,600,300);
		setTitle("GyMe-Your Online Gym");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(600,300);
		setLocation(300,300);
		setVisible(true);
	}
	public int checkIdentity(String id, String pw, String identity) throws Exception{
		int isValid=0;
		String filename="files/"+identity+".csv";
		FileReader fr=new FileReader(filename);
		BufferedReader br=new BufferedReader(fr);
		String newline=br.readLine();
		while(newline!=null) {
			String[] items=newline.split(",");
			if(items[0].equals(id)&&items[1].equals(pw)) {
				if(identity.equals("User"))
					isValid=1;
				else if(identity.equals("Trainer"))
					isValid=2;
				else
					isValid=3;
				break;
			}
			newline=br.readLine();
		}
		return isValid;
	}
	public void actionPerformed(ActionEvent e){
		String identitySelection;
		int isIdentity=0;
		ErrorMessage fail;
		Register r;
		String thisID;
		if(e.getSource()==this.loginIdentity) {
			identitySelection=this.loginIdentity.getSelectedItem().toString();
			this.loginIdentity.setSelectedItem(identitySelection);
		}
		else if(e.getSource()==this.loginButton) {
			try {
				thisID=this.inputID.getText();
				isIdentity=this.checkIdentity(thisID,
										  this.inputPassword.getText(),
										  this.loginIdentity.getSelectedItem().toString());
				if(isIdentity==1) {
					UserPage up=new UserPage(thisID);
				}
				else if(isIdentity==2)
					System.out.println("Trainer login");
				else if(isIdentity==3)
					System.out.println("Administrator login");
				else
					fail=new ErrorMessage(1);
			}
			catch(Exception ex) {
				ErrorMessage em=new ErrorMessage();
				ex.printStackTrace();
			}
		}
		else if(e.getSource()==this.registerButton)
			r=new Register();
	}
	public static void main(String[] args) throws Exception{
		Login l=new Login();
	}
}
