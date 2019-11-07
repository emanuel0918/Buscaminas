import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.Icon;

public class Flag implements Icon {
	private int width;
	private int height;
	
	public Flag(int width, int height){
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
		g.setColor(Color.RED);
		int n_flag=3;
		int flag_x[]=new int[3];
		int flag_y[]=new int[3];
		flag_x[0]=(int)(x+width*50/87);flag_y[0]=y;
		flag_x[1]=x;flag_y[1]=(int)(y+height/4);
		flag_x[2]=(int)(x+width*50/87);flag_y[2]=(int)(y+height/2);
		g.fillPolygon(flag_x,flag_y,n_flag);
		g.setColor(Color.BLACK);
		int n_stick=12;
		int stick_x[]=new int[12];
		int stick_y[]=new int[12];
		stick_x[0]=(int)(x+width*37/87);stick_y[0]=(int)(y+height*87/200);
		stick_x[1]=(int)(x+width*37/87);stick_y[1]=(int)(y+height*13/20);
		stick_x[2]=(int)(x+width*5/87);stick_y[2]=(int)(y+height*17/20);
		stick_x[3]=(int)(x+width*5/87);stick_y[3]=(int)(y+height*37/40);
		stick_x[4]=x;stick_y[4]=(int)(int)(y+height*37/40);
		stick_x[5]=x;stick_y[5]=(int)(y+height);
		stick_x[6]=(int)(x+width);stick_y[6]=(int)(y+height);
		stick_x[7]=(int)(x+width);stick_y[7]=(int)(y+height*37/40);
		stick_x[8]=(int)(x+width*82/87);stick_y[8]=(int)(y+height*37/40);
		stick_x[9]=(int)(x+width*82/87);stick_y[9]=(int)(y+height*17/20);
		stick_x[10]=(int)(x+width*50/87);stick_y[10]=(int)(y+height*13/20);
		stick_x[11]=(int)(x+width*50/87);stick_y[11]=(int)(y+height/2);
		g.fillPolygon(stick_x,stick_y,n_stick);
	}
}