package application;
import java.io.*;
public class NotiToFile {
	private int MAX_NOTI=1000;
	private String wString;
	private String fileName;
	public NotiToFile(String t, String w) {
		this.wString=w;
		if(t.equals("user"))
			fileName="files/UserNotification.csv";
		else if(t.equals("trainer"))
			fileName="files/TrainerNotification.csv";
		else if(t.equals("history"))
			fileName="files/UserHistory.csv";
	}
	public boolean notiToFile() {
		try {
			String[] tempNoti=new String[MAX_NOTI];
			tempNoti[0]=this.wString;
			int notiCount=1;
			FileReader tnFr=new FileReader(fileName);
			BufferedReader tnBr=new BufferedReader(tnFr);
			String tnLine=tnBr.readLine();
			while(tnLine!=null) {
				tempNoti[notiCount]=tnLine;
				notiCount++;
				tnLine=tnBr.readLine();
			}
			tnBr.close();
			tnFr.close();
			
			FileWriter tnFw=new FileWriter(fileName);
			BufferedWriter tnBw=new BufferedWriter(tnFw);
			for(int k=0;k<notiCount;k++)
				tnBw.write(tempNoti[k]+"\n");
			tnBw.close();
			tnFw.close();
			return true;
		}
		catch(Exception e) {
			ErrorMessage em=new ErrorMessage();
			e.printStackTrace();
			return false;
		}
	}
}
