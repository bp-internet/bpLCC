package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.EventListener;

import javax.swing.*;

public class GUI extends JFrame implements ActionListener,MouseListener {
	
	BPButton btn_licht;
	BPButton btn_licht_an;
	BPButton btn_licht_aus;
	BPButton btn_licht_min;
	BPButton btn_licht_max;
	
	BPButton btn_pc;
	BPButton btn_pc_pc_an;
	BPButton btn_pc_server_an;
	
	BPButton btn_relais;
	BPButton btn_relais_1_an;
	BPButton btn_relais_1_aus;
	BPButton btn_relais_2_an;
	BPButton btn_relais_2_aus;
	
	BPButton btn_exit;
	
	JPanel pnl_cards;
	JPanel pnl_licht;
	JPanel pnl_pc;
	JPanel pnl_relais;
	private GraphicsDevice device;
	
	final static String BUTTONPANELLICHT = "Licht";
	final static String BUTTONPANELPC = "PC";
	final static String BUTTONPANELRELAIS = "Relais";
	
	private static String PIURLLED = "http://192.168.1.123/433/light.php?";
	private static String PIURLRELAIS = "http://192.168.1.123/relais/index.php";
	private static String WOLURL = "http://192.168.1.123/wol/wol.php?device=";
	
	private BackgroundLight bgLight;
	BPTimer bptimer;

	public GUI() {
		super("bplcc");
		
		bgLight = new BackgroundLight();
		bptimer = new BPTimer(20);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setUndecorated(true);
 		setResizable(false);
		
		setLayout(new BorderLayout());
		
		addMouseListener(this);
		
		//tabs
		JPanel tabs = new JPanel(new FlowLayout());
		
		btn_licht = new BPButton("Licht");
		btn_licht.addActionListener(this);
		btn_pc = new BPButton("PC");
		btn_pc.addActionListener(this);
		btn_relais = new BPButton("Relais");
		btn_relais.addActionListener(this);
		btn_exit = new BPButton("Exit");
		btn_exit.addActionListener(this);
		
		tabs.add(btn_licht);
		tabs.add(btn_pc);
		tabs.add(btn_relais);
		tabs.add(btn_exit);
		
		getContentPane().add(BorderLayout.NORTH, tabs);
		
		//licht
		btn_licht_an = new BPButton("an", true);
		btn_licht_an.addActionListener(this);
		btn_licht_aus = new BPButton("aus", true);
		btn_licht_aus.addActionListener(this);
		btn_licht_min = new BPButton("min", true);
		btn_licht_min.addActionListener(this);
		btn_licht_max = new BPButton("max", true);
		btn_licht_max.addActionListener(this);
		
		pnl_licht = new JPanel();
		pnl_licht.add(btn_licht_min);
		pnl_licht.add(btn_licht_an);
		pnl_licht.add(btn_licht_max);
		pnl_licht.add(btn_licht_aus);
		
		//pc
		btn_pc_pc_an = new BPButton("Pc an", true);
		btn_pc_pc_an.addActionListener(this);
		btn_pc_server_an = new BPButton("Server an", true);
		btn_pc_server_an.addActionListener(this);
		
		pnl_pc = new JPanel();
		pnl_pc.add(btn_pc_pc_an);
		pnl_pc.add(btn_pc_server_an);
		
		//relais
		btn_relais_1_an = new BPButton("Relais an", true);
		btn_relais_1_an.addActionListener(this);
		btn_relais_1_aus = new BPButton("Relais aus", true);
		btn_relais_1_aus.addActionListener(this);
		btn_relais_2_an = new BPButton("PC an", true);
		btn_relais_2_an.addActionListener(this);
		btn_relais_2_aus = new BPButton("PC aus", true);
		btn_relais_2_aus.addActionListener(this);
		
		pnl_relais = new JPanel();
		pnl_relais.add(btn_relais_1_an);
		pnl_relais.add(btn_relais_1_aus);
		pnl_relais.add(btn_relais_2_an);
		pnl_relais.add(btn_relais_2_aus);
		
		pnl_cards = new JPanel(new CardLayout());
		pnl_cards.add(pnl_licht, BUTTONPANELLICHT);
		pnl_cards.add(pnl_pc, BUTTONPANELPC);
		pnl_cards.add(pnl_relais, BUTTONPANELRELAIS);
		
		getContentPane().add(BorderLayout.CENTER, pnl_cards);
		
		GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().setFullScreenWindow(this);
	}

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GUI gui = new GUI();
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if(!bgLight.getState())
		{
			bgLight.on();
			bptimer.start();
			
			return;
		}
		
		if(e.getSource() == btn_licht)
		{
			CardLayout cl = (CardLayout)pnl_cards.getLayout();
			cl.show(pnl_cards, BUTTONPANELLICHT);
		}
		if(e.getSource() == btn_pc)
		{
			CardLayout cl = (CardLayout)pnl_cards.getLayout();
			cl.show(pnl_cards, BUTTONPANELPC);
		}
		if(e.getSource() == btn_relais)
		{
			CardLayout cl = (CardLayout)pnl_cards.getLayout();
			cl.show(pnl_cards, BUTTONPANELRELAIS);
		}
		if(e.getSource() == btn_exit)
		{
			System.exit(0);
		}
		
		// Licht
		if(e.getSource() == btn_licht_aus) // aus 
		{
			new UrlContent(PIURLLED+"off=1");
		}
		if(e.getSource() == btn_licht_an)
		{
			new UrlContent(PIURLLED+"value=72");
		}
		if(e.getSource() == btn_licht_min) 
		{
			new UrlContent(PIURLLED+"value=1");
		}
		if(e.getSource() == btn_licht_max) 
		{
			new UrlContent(PIURLLED+"value=255");
		}
		
		// WOL
		if(e.getSource() == btn_pc_pc_an)
		{
			new UrlContent(WOLURL+"Pc");
		}
		if(e.getSource() == btn_pc_server_an)
		{
			new UrlContent(WOLURL+"Server");
		}
		
		// Relais
		if(e.getSource() == btn_relais_1_an)
		{
			new UrlContent(PIURLRELAIS+"?pin=1&mode=0");
		}
		if(e.getSource() == btn_relais_1_aus)
		{
			new UrlContent(PIURLRELAIS+"?pin=1&mode=1");
		}
		if(e.getSource() == btn_relais_2_an)
		{
			new UrlContent(PIURLRELAIS+"?pin=2&mode=0");
		}
		if(e.getSource() == btn_relais_2_aus)
		{
			new UrlContent(PIURLRELAIS+"?pin=2&mode=1");
		}
	}


	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
		bgLight.on();
		bptimer.start();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}


}