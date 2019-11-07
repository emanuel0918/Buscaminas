import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.Icon;

public class Happy implements Icon {
	private int width;
	private int height;
	
	public Happy(int width, int height){
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
		g.drawLine((int)(x+width*3/8),(int)(y+height/5),(int)(x+width*3/8),(int)(y+height*9/20));
		g.drawLine((int)(x+width*5/8),(int)(y+height/5),(int)(x+width*5/8),(int)(y+height*9/20));
		g.drawArc((int)(x+width/4),(int)(y+height/4),(int)(width/2),(int)(height/2),225,90);
	}
}