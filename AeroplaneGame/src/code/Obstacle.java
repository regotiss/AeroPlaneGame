package code;

import java.applet.Applet;
import java.awt.*;
import java.util.Random;
public class Obstacle  
{
	private int x,y,dx,w,h;
	private Image im;
	Random r;
	Applet a;
	public Obstacle(int x,int y,int dx,int dy,Image i)
	{
		this.x=x;
		this.y=y;
		this.dx=dx;
		im=i;
		w=80;
		h=120;
	}
	public boolean update(Applet a,AeroPlane ap)
	{
		this.a=a;
		
		if(x<=-w)
		{
			r=new Random();
			//y=a.getHeight()-h-r.nextInt(400);
			y=r.nextInt(400)+20;
			x=a.getWidth();
		}
		else
			x-=dx;
		boolean b=checkCollision(ap);
		return b;
		/*if((y+h)>=a.getHeight())
				y=a.getHeight()-h;
			y+=dy;*/
		
	}
	public void paint(Graphics g)
	{
		g.setColor(Color.black);
		g.drawRect(x-1,y-1,w+1,h+1);
		g.drawImage(im,x,y,w,h,a);
	}
	public boolean checkCollision(AeroPlane ap)
	{
		
		if((ap.getX()+ap.getWidth())>=x&&ap.getX()<x&&((ap.getY()+100)>=y)&&((ap.getY()+ap.getHeight()-100)<=(y+h)))
		return true;
		else
		return false;
		
		
	}
}

