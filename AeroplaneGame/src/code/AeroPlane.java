package code;

import java.applet.Applet;
import java.awt.event.*;
import java.awt.*;
public class AeroPlane
{
	private int x,y,w,h,dx,dy,speed=10;
	private Image plane;
	Applet a;

	public AeroPlane(int x,int y,int dx,int dy,int wd,int ht,Image m)
	{
		plane=m;
		this.x=x;
		this.y=y;
		this.dx=dx;
		this.dy=dy;
		w=wd;
		//addKeyListener(this);
		h=ht;
	}
	public void update(Applet a)
	{
		this.a=a;
		if((x+w)>=a.getWidth())
				x=0;
			x+=dx;
		if((y+h)>=a.getHeight())
				y=a.getHeight()-h;
			y+=dy;
	}
	public void paint(Graphics g)
	{
		g.drawImage(plane,x,y,w,h,a);
	}
	
	public void keyPressed(KeyEvent e)
	{
		int c=e.getKeyCode();
		if(c==KeyEvent.VK_UP)
		{
			y-=speed;
		}
		if(c==KeyEvent.VK_DOWN)
		{
			y+=speed;
		}
		if(c==KeyEvent.VK_RIGHT)
		{
			x+=speed;
		}
		if(c==KeyEvent.VK_LEFT)
		{
			x-=speed;
		}

	}
	public int getX()
	{
		return x;
	}
	public int getY()
	{
		return y;
	}
	public int getDx()
	{
		return dx;
	}
	public int getDy()
	{
		return dy;
	}
	public int getWidth()
	{
		return w;
	}
	public int getHeight()
	{
		return h;
	}
	

	public void setX(int x)
	{
		this.x=x;
	}
	public void setY(int x)
	{
		y=x;
	}
	public void setDx(int x)
	{
		dx=x;
	}
	public void setDy(int x)
	{
		dy=x;
	}
	
}

