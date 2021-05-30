import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
public class BodyInfo extends JFrame implements ActionListener{
	private JButton submit;
	private JTextField height;
	private JTextField weight;
	private JTextField bfrate;
	private JTextField focus;
	private JTextField requirement;
	private String thisID;
	private int MAX=1000;
	public BodyInfo(String userID) {
		this.thisID=userID;
		JPanel insertPane=new JPanel(new GridLayout(6,0,5,5));

		JPanel heightPane=new JPanel();
		JLabel heightLabel=new JLabel("Your height (cm): ",JLabel.CENTER);
		this.height=new JTextField(7);
		heightPane.add(heightLabel);
		heightPane.add(this.height);
		insertPane.add(heightPane);
		
		JPanel weightPane=new JPanel();
		JLabel weightLabel=new JLabel("Your weight (kg): ",JLabel.CENTER);
		this.weight=new JTextField(7);
		weightPane.add(weightLabel);
		weightPane.add(this.weight);
		insertPane.add(weightPane);
		
		JPanel bfPane=new JPanel();
		JLabel bfLabel=new JLabel("Your Body-Fat Rate (%): ",JLabel.CENTER);
		this.bfrate=new JTextField(7);
		bfPane.add(bfLabel);
		bfPane.add(this.bfrate);
		insertPane.add(bfPane);
		
		JPanel focusPane=new JPanel();
		JLabel focusLabel=new JLabel("Your focus fields: ",JLabel.CENTER);
		this.focus=new JTextField(30);
		focusPane.add(focusLabel);
		focusPane.add(this.focus);
		insertPane.add(focusPane);
		
		JPanel reqPane=new JPanel();
		JLabel reqLabel=new JLabel("Your requirement: ",JLabel.CENTER);
		this.requirement=new JTextField(30);
	    reqPane.add(reqLabel);
		reqPane.add(this.requirement);
		insertPane.add(reqPane);

		JPanel buttonPane=new JPanel();
		this.submit=new JButton("Submit Your Info");
		this.submit.addActionListener(this);
		buttonPane.add(this.submit);
		buttonPane.setOpaque(false);
		insertPane.add(buttonPane);
		insertPane.setBorder(new EmptyBorder(20,0,0,0));
		
		Image icon;
		ImageIcon ig = new ImageIcon("imgs/icon.jpg");
		icon = ig.getImage();
		setIconImage(icon);
		
		Container content=this.getContentPane();
		JPanel myPanel=(JPanel)content;
		myPanel.setOpaque(false);
		myPanel.add(insertPane);
		setTitle("Create/Update Your Body Info");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(500,450);
		setLocation(300,200);
		setVisible(true);
	}
	public boolean writeBodyInfo(float height, float weight, float bfrate, String focus, String requirement) {
		try {
			float bmi=weight/(height/100)/(height/100);
			SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm");
			String time=df.format(new Date());
			String wString=this.thisID+","+height+","+weight+","+bmi+","+bfrate+","+focus+","+requirement+","+time+"\n";
			
			String[] tempInfo=new String[MAX];
			tempInfo[0]=wString;
			int count=1;
			FileReader fr=new FileReader("files/BodyInfo.csv");
			BufferedReader br=new BufferedReader(fr);
			String biLine=br.readLine();
			while(biLine!=null) {
				tempInfo[count]=biLine;
				count++;
				biLine=br.readLine();
			}
			br.close();
			fr.close();
			
			FileWriter fw=new FileWriter("files/BodyInfo.csv");
			BufferedWriter bw=new BufferedWriter(fw);
			for(int i=0;i<count;i++) {
				bw.write(tempInfo[i]+"\n");
			}
			bw.close();
			fw.close();
			return true;
		}
		catch(Exception e) {
			ErrorMessage em=new ErrorMessage();
			e.printStackTrace();
			return false;
		}
	}
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==this.submit) {
			float height=Float.parseFloat(this.height.getText());
			float weight=Float.parseFloat(this.weight.getText());
			float bfrate=Float.parseFloat(this.bfrate.getText());
			String focus=this.focus.getText();
			String requirement=this.requirement.getText();
			boolean isDone=writeBodyInfo(height,weight,bfrate,focus,requirement);
			Success s=null;
			if(isDone)
				s=new Success("New body info created");
		}
	}
}
