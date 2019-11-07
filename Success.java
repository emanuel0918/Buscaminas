import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.Icon;

public class Success implements Icon {
	private int width;
	private int height;
	
	public Success(int width, int height){
		this.width=width;
		this.height=height;
	}
	
	public void setIconWidth(int width){
		this.width=width;
	}
	
	public void setIconHeight(int height){
		this.height=height;
	}
	
    
    public int getIconWidth() {
		return width;
	}
	
	public int getIconHeight() {
		return height;
	}
	
	public void paintIcon(Component c, Graphics g, int x, int y){
		g.setColor(Color.BLACK);
		g.drawOval(x,y,(int)(width),(int)(height));
		g.setColor(Color.YELLOW);
		g.fillOval((int)(x+width/40),(int)(y+height/40),(int)(width*19/20),(int)(height*19/20));
		g.setColor(Color.BLACK);
		g.drawLine((int)(x+width/20),(int)(y+height*3/10),(int)(x+width*37/40),(int)(y+height*3/10));
		g.fillOval((int)(x+width*7/40),(int)(y+height*7/40),(int)(width*3/10),(int)(height*11/40));
		g.fillOval((int)(x+width*21/40),(int)(y+height*7/40),(int)(width*3/10),(int)(height*11/40));
		g.drawArc((int)(x+width/4),(int)(y+height/4),(int)(width/2),(int)(height/2),225,90);
		g.setColor(Color.YELLOW);
		g.fillRect((int)(x+width*11/40),(int)(y+height*3/40),(int)(width*9/40),(int)(height*9/40));
		g.fillRect((int)(x+width*7/40),(int)(y+height*7/40),(int)(width/10),(int)(height/8));
		g.fillRect((int)(x+width/2),(int)(y+height*3/40),(int)(width*9/40),(int)(height*9/40));
		g.fillRect((int)(x+width*29/40),(int)(y+height*7/40),(int)(width/10),(int)(height/8));
		g.setColor(Color.BLACK);
		g.drawLine((int)(x+width/20),(int)(y+height*3/10),(int)(x+width*303/320),(int)(y+height*3/10));
	}
}