import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

public class BarChart extends JPanel{
	private String type;
	private List<Part> partList =new ArrayList<>();
	public BarChart(String type) {
		this.type=type;
	}
	public void addPart(int amount,Object tag,Color color)
	{
		if(amount<=0)
			return;
		
		Part p=new Part();
		p.amount=amount;
		p.tag=tag;
		p.color=color;
		partList.add(p);
	}
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		double lambda;
		if(this.type.equals("user"))
			lambda=0.01;
		else if(this.type.equals("trainer"))
			lambda=0.03;
		else
			lambda=0.008;
		double height = this.getHeight()*lambda;
		int width = this.getWidth();
		int w80 = (int) (width * 0.8);
		int w20 = (int) (width * 0.18);
		int uw = w80 / 8;
		int gap = w20;

		Graphics2D g2d = (Graphics2D) g;
		int count=0;
		
		for(Part p:partList) {
			int rectH=(int)(height*p.amount);
			g2d.setColor(p.color);
			g.fillRect((this.getHeight()/2-90)+gap*count,this.getHeight()-33-rectH, uw, rectH);
			String a=p.amount+"";
			g2d.drawString(a,(this.getHeight()/2-90)+gap*count+11, this.getHeight()-37-rectH);
			String str=p.tag.toString();
			String[] fstr=str.split(" ");
			g2d.drawString(fstr[0],(this.getHeight()/2-90)+gap*count+2,this.getHeight()/2+107);
			count++;
		}
		
		g2d.setColor(Color.BLACK);
		g2d.drawLine(this.getHeight()/2-100, this.getWidth()/2+30, this.getHeight()/2+225, this.getWidth()/2+30);
	}
	public class Part{
		int amount;  
		Color color; 
		Object tag;  
	} 
}
