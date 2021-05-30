import java.awt.event.*;
import java.io.*;
import java.awt.*;
import javax.swing.*;
import java.util.*;
public class Recommend extends JFrame implements ActionListener{
	private String videoID;
	private JButton jbtOK;
	private JTextField time;
	private JComboBox<String> users;
	private JComboBox<String> period;
	private String[] pairList;

	public Recommend(String vID, String[] pair){
		this.videoID=vID;
		this.pairList=pair;
		this.users=new JComboBox<String>();
		String[] userList=new String[5];
		int userCount=0;
		JPanel messagePanel=new JPanel(new GridLayout(4,0,0,0));
		try {
			FileReader fr=new FileReader("files/UserTrainer.csv");
			BufferedReader br=new BufferedReader(fr);
			String line=br.readLine();
			while(line!=null) {
				String[] attr=line.split(",");
				for(int i=0;i<pair.length;i++) {
					if(pair[i].equals(attr[0])) {
						userList[userCount]=attr[1];
						userCount++;
					}	
				}
				line=br.readLine();
			}
			br.close();
			fr.close();
			
			FileReader ufr=new FileReader("files/User.csv");
			BufferedReader ubr=new BufferedReader(ufr);
			String uline=ubr.readLine();
			while(uline!=null) {
				String[] uattr=uline.split(",");
				for(int k=0;k<userCount;k++) {
					if(userList[k].equals(uattr[0]))
						users.addItem(uattr[2]);
				}
				uline=ubr.readLine();
			}
			ubr.close();
			ufr.close();
			JLabel exContent=new JLabel("Select user to recommend: ",JLabel.CENTER);
			messagePanel.add(exContent);
			
			JPanel boxPanel=new JPanel();
			this.users.addActionListener(this);
			boxPanel.add(users);
			messagePanel.add(boxPanel);
			
			JPanel planPanel=new JPanel(new FlowLayout());
			this.time=new JTextField(3);
			this.period=new JComboBox<String>();
			this.period.addItem("day");
			this.period.addItem("week");
			this.period.addItem("month");
			this.period.addActionListener(this);
			JLabel textLabel=new JLabel(" times per ",JLabel.CENTER);
			planPanel.add(this.time);
			planPanel.add(textLabel);
			planPanel.add(this.period);
			messagePanel.add(planPanel);
			
			JPanel buttonPanel=new JPanel(new FlowLayout());
			jbtOK=new JButton("OK");
			jbtOK.setSize(50,30);
			jbtOK.addActionListener(this);
			buttonPanel.add(jbtOK);
			messagePanel.add(buttonPanel);
		}
		catch(Exception e) {
			ErrorMessage em=new ErrorMessage();
			e.printStackTrace();
		}
		
		getContentPane().add(messagePanel);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(230,210);
		setLocation(500,300);
		setVisible(true);
	}
	public void recommendToUser() {
		try {
			String sTime=this.time.getText();
			if(sTime.equals(""))
				sTime="1";
			String sPeriod=this.period.getSelectedItem().toString();
			int uIndex=this.users.getSelectedIndex();
			String recLine=this.pairList[uIndex]+","+this.videoID+","+sTime+","+sPeriod;
			
			String[] temp=new String[1000];
			int count=1;
			temp[0]=recLine;
			FileReader fr=new FileReader("files/Recommend.csv");
			BufferedReader br=new BufferedReader(fr);
			String line=br.readLine();
			while(line!=null&&count<1000) {
				temp[count]=line;
				count++;
				line=br.readLine();
			}
			br.close();
			fr.close();
			
			FileWriter fw=new FileWriter("files/Recommend.csv");
			BufferedWriter bw=new BufferedWriter(fw);
			for(int j=0;j<count;j++)
				bw.write(temp[j]+"\n");
			bw.close();
			fw.close();
			Success s=new Success("Successfully recommended");
		}
		catch(Exception e) {
			ErrorMessage em=new ErrorMessage();
			e.printStackTrace();
		}
	}
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==this.jbtOK) {
			recommendToUser();
			dispose();
		}
		else if(e.getSource()==this.users) {
			String id=users.getSelectedItem().toString();
			users.setSelectedItem(id);
		}
		else if(e.getSource()==this.period) {
			String id=period.getSelectedItem().toString();
			period.setSelectedItem(id);
		}
	}
}
