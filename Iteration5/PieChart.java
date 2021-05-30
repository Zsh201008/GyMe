import java.awt.Color;
import java.awt.geom.Arc2D;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.geom.Arc2D;
import java.util.ArrayList;
import java.util.List;
 
import javax.swing.JPanel;

public class PieChart extends JPanel{
	private List<Part> partList =new ArrayList<>();
	private String type;
	public PieChart(String type) {
		this.type=type;
	}
	public void addPart(double amount,Object tag,Color color){
		if(amount<=0)
			return;
		
		Part p=new Part();
		p.amount=amount;
		p.tag=tag;
		p.color=color;
		partList.add(p);
	}
	
	private void calculate(){
		if(partList.size()==0)
			return;

		double totalAmount=0;
		for(Part p : partList)
			totalAmount=totalAmount+p.amount;
			
		int totalDegrees=0;
		for(int i=0;i<partList.size();i++){
			Part p=partList.get(i);
			p.degree=(int)(360*p.amount/totalAmount);
			if(p.degree<3)
				p.degree=3;
			if(i==partList.size()-1)
				p.degree=360-totalDegrees;
			totalDegrees+=p.degree;
		}
			
		int width=this.getWidth();
		int height=this.getHeight();
		int w=width;
		int h=width;
		if(h>height){
			h=height;
			w=height;
		}
		Rectangle rect = new Rectangle((width-w)/2,(height-h)/2,w,h);
		rect.grow(-4, -4);
		
		int start=90;
		for(Part p : partList){
			p.shape=new Arc2D.Double(rect, start, p.degree, Arc2D.PIE);
			start=start+p.degree;
		}
	}
 
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		int width = getWidth();
		int height = getHeight();
		Graphics2D g2d = (Graphics2D) g;
		
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		this.calculate();
		for(Part p:partList)
		{
			if(p.shape != null) 
			{
				g2d.setPaint(p.color);
				g2d.fill(p.shape);
			}
		}
		g2d.setColor(Color.BLACK);
		if(this.type.equals("type")) {
			g2d.drawString("Common",this.getHeight()/2,this.getWidth()/2);
			g2d.drawString("Privilege",this.getHeight()/2+100,this.getWidth()/2-120);
		}
		else {
			g2d.drawString("Copper",this.getHeight()/2,this.getWidth()/2);
			g2d.drawString("Silver",this.getHeight()/2+100,this.getWidth()/2-80);
			g2d.drawString("Golden",this.getHeight()/2+50,this.getWidth()/2-150);
		}
	}
	public class Part{
		double amount;  
		int degree;  
		Color color; 
		Object tag;  
		Arc2D shape; 
	} 
}
