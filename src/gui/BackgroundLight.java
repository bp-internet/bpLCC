package gui;

import java.io.IOException;

public class BackgroundLight {
	
	private Runtime run;
	private final String ONCMD  = "/home/pi/background/on.sh";
	private final String OFFCMD = "/home/pi/background/off.sh";
	private static boolean state;
	
	public BackgroundLight()
	{
		 run = Runtime.getRuntime();
		 state = false;
	}
	
	public void on()
	{
		execCMD(true);
	}
	
	public void off()
	{
		execCMD(false);
	}
	
	public boolean getState()
	{
		return state;
	}
	
	private void execCMD(boolean on)
	{
		String cmd = on? ONCMD : OFFCMD;
		state = on;
		
		try {
			Process proc = run.exec(new String[]{"/bin/sh", "-c", cmd});
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
