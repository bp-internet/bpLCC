package gui;

import java.util.Timer;
import java.util.TimerTask;

public class BPTimer {
	
	private Timer timer;
	private BackgroundLight bgLight;
	private int delay;
	
	public BPTimer(int sec)
	{
		bgLight = new BackgroundLight();
		
		timer = new Timer();
		delay = sec*1000;
	}
	
	public void start(){
			
		timer.schedule(new TimerTask() {
			  @Override
			  public void run() {
				  bgLight.off();
			  }
			}, delay);
	}
	
	public void cancel()
	{
		timer.cancel();
	}

}
