//By Sujata Regoti
//Date: 20 March 2015

package code;

import java.applet.*;
import java.awt.event.*;
import java.awt.*;
import java.util.Random;
/*<applet code=AirPlaneGame width=500 height=500></applet>*/
public class AeroPlaneGame extends Applet implements Runnable,KeyListener 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Image plane;
	Image back,crash;
	double backX=0;
	double backDx=.9;
	int appW,appH;
	Random r;
	private Image i,obst;
	private Graphics doubleG;
	Thread t;
	long score;
	int level=1;
	Obstacle ob[];
	AeroPlane ap;
	boolean gameOver,flg=true;


	public void init()
	{
	
		back=getImage(getDocumentBase(),"images/airplane/back.jpg");
		plane=getImage(getDocumentBase(),"images/airplane/plane.png");
		crash=getImage(getDocumentBase(),"images/airplane/crash.png");
		
		addKeyListener(this);

		GraphicsDevice gd=GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		int width = gd.getDisplayMode().getWidth();
		int height = gd.getDisplayMode().getHeight();
		this.setSize(width, height);
	}
	public void start()
	{
		score=0;
		appW=getWidth();
		appH=getHeight();
		r=new Random();
	
		showStatus("Score above 500 to Win");
		ap=new AeroPlane(10,appH/2-100,1,0,260,190,plane);
		ob=new Obstacle[7];
		for(int i=0;i<ob.length;i++)
		{

			obst=getImage(getDocumentBase(),"images/airplane/ob"+(r.nextInt(5)+1)+".jpg");
			//int tmp=r.nextInt(appH)-120;
			//if(tmp<=0)

			ob[i]=new Obstacle(appW+i*(10-level)*100,r.nextInt(500)+20,level+15,0,obst);
		}
		t=new Thread(this);
		t.start();
	}
	public void run()
	{
		while(true)
		{
			if(!gameOver)
			{
				ap.update(this);
				
				for(int i=0;i<ob.length;i++)
				{
					
					gameOver=ob[i].update(this,ap);
					if(gameOver)
						break;
				}
				if(backX>appW*-1)
				{
					backX-=backDx;
				}else
				{
					backX=0;
				}
				
				score++;
				if(score>=500)
				{
					gameOver=true;
				}
			}
				try
				{
					Thread.sleep(100);
				}
				catch (Exception e)
				{
				}
			flg=flg?false:true;
			repaint();
		}
	}
	public void paint(Graphics g)
	{
		//g.drawImage(back,0,0,appW,appH,this);
		g.drawImage(back,(int)backX,0,appW*2,appH,this);
		String s=Long.toString(score);
		g.setFont(new Font("Serif",Font.BOLD,32));
		g.setColor(Color.black);
		g.drawString(s,getWidth()-150-2,50-2);
		g.setColor(new Color(198,226,255));
		g.drawString(s,getWidth()-150,50);
		ap.paint(g);
		for(int i=0;i<ob.length;i++)
		ob[i].paint(g);
		if(gameOver&&score<500)
		{
			if(flg)
			g.setColor(Color.white);
			else
			g.setColor(Color.red);
			g.setFont(new Font("Arial",Font.BOLD,35));	
			g.drawString("Game Over",getWidth()/2-100,getHeight()/2);
			g.drawImage(crash,ap.getX()+ap.getWidth()-50,ap.getY()+(ap.getY()/4),50,50,this);
			
				
		}
		else if(gameOver){
			if(flg)
			g.setColor(Color.white);
			else
			g.setColor(Color.BLUE);
			g.setFont(new Font("Arial",Font.BOLD,35));	
			g.drawString("Greate!! You Won.. Congratulation",getWidth()/2-100,getHeight()/2);
			
		}

	}
	public void update(Graphics g)
	{
		if(i==null)
		{
			i=createImage(this.getSize().width,this.getSize().height);
			doubleG=i.getGraphics();
		}

		doubleG.setColor(getBackground());
		doubleG.fillRect(0,0,this.getSize().width,this.getSize().height);
		doubleG.setColor(getForeground());
		paint(doubleG);
		g.drawImage(i,0,0,this);
	}
	public void keyTyped(KeyEvent e)
	{	
	}
	public void keyPressed(KeyEvent e)
	{
		ap.keyPressed(e);
	}
	public void keyReleased(KeyEvent e)
	{
		
	}

	
}
