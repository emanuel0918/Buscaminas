import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.Icon;

public class Bomb implements Icon {
	private int width;
	private int height;
	
	public Bomb(int width, int height){
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
		g.fillOval((int)(x+width/5),(int)(y+height/5),(int)(width*3/5),(int)(height*3/5));
		g.fillRect((int)(x+width*1/30),(int)(y+height*7/15),(int)(width*14/15),(int)(height*7/100));
		g.fillOval(x,(int)(y+height*7/15),(int)(width/15),(int)(height/15));
		g.fillOval((int)(x+width*14/15),(int)(y+height*7/15),(int)(width/15),(int)(height/15));
		g.fillRect((int)(x+width*7/15),(int)(y+height/30),(int)(width*7/100),(int)(height*14/15));
		g.fillOval((int)(x+width*7/15),y,(int)(width/15),(int)(height/15));
		g.fillOval((int)(x+width*7/15),(int)(y+height*14/15),(int)(width/15),(int)(height/15));
		int n_r1=4;
		int r1_x[]=new int[4];
		int r1_y[]=new int[4];
		r1_x[0]=(int)(x+width*53/300);r1_y[0]=(int)(y+height*67/300);
		r1_x[1]=(int)(x+width*67/300);r1_y[1]=(int)(y+height*53/300);
		r1_x[2]=(int)(x+width*247/300);r1_y[2]=(int)(y+height*233/300);
		r1_x[3]=(int)(x+width*233/300);r1_y[3]=(int)(y+height*247/300);
		g.fillPolygon(r1_x,r1_y,n_r1);
		g.fillOval((int)(x+width/6),(int)(y+height/6),(int)(width/15),(int)(height/15));
		g.fillOval((int)(x+width*23/30),(int)(y+height*23/30),(int)(width/15),(int)(height/15));
		int n_r2=4;
		int r2_x[]=new int[4];
		int r2_y[]=new int[4];
		r2_x[0]=(int)(x+width*53/300);r2_y[0]=(int)(y+height*233/300);
		r2_x[1]=(int)(x+width*67/300);r2_y[1]=(int)(y+height*247/300);
		r2_x[2]=(int)(x+width*247/300);r2_y[2]=(int)(y+height*67/300);
		r2_x[3]=(int)(x+width*233/300);r2_y[3]=(int)(y+height*53/300);
		g.fillPolygon(r2_x,r2_y,n_r2);
		g.fillOval((int)(x+width/6),(int)(y+height*23/30),(int)(width/15),(int)(height/15));
		g.fillOval((int)(x+width*23/30),(int)(y+height/6),(int)(width/15),(int)(height/15));
		g.setColor(Color.WHITE);
		g.fillOval((int)(x+width*3/10),(int)(y+height*3/10),(int)(width*7/30),(int)(height*7/30));
	}
}