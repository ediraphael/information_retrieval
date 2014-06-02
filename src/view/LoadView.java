package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.Box;
import javax.swing.JProgressBar;

import model.ParserAP;

import java.awt.Dimension;
import javax.swing.JLabel;

public class LoadView  
{

	private static JFrame frame;
	
	private JPanel mainPan;
	private JPanel panLabel;
	private JPanel panProgressBar;
	
	private Component hsMainSettingLeft;
	private Component hsMainSettingRight;
	private Component vsMainSettingLow;
	
    private JProgressBar progressBarLoad;
    private ParserAP parser = new ParserAP();
    private JPanel panLabelLoad;
    private Component verticalStrut;
    private JLabel labelLoading;
    private JLabel labelFileInProgress;
	
    
    public class LoadThread extends Thread
    {
    	public void run()
    	{
    		parser.loadAllApDocumentByFolder(progressBarLoad, labelFileInProgress);
    		//Thread.currentThread().interrupt();
    		while(progressBarLoad.getValue()!=100)
    		{
    			System.out.println("toto");
    		}
    	}    
    }
    
   

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() 
			{
				try 
				{
					new LoadView();
					frame.setVisible(true);
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public LoadView() 
	{
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() 
	{
		frame = new JFrame();
		frame.setTitle("EZ-Google");
		frame.setBounds(100, 100, 600, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		mainPan = new JPanel();
		frame.getContentPane().add(mainPan, BorderLayout.CENTER);
		mainPan.setLayout(new BorderLayout(0, 0));
		
		panLabel = new JPanel();
		panLabel.setPreferredSize(new Dimension(10, 300));
		mainPan.add(panLabel, BorderLayout.NORTH);
		panLabel.setLayout(new BorderLayout(0, 0));
		
		panLabelLoad = new JPanel();
		panLabelLoad.setPreferredSize(new Dimension(10, 30));
		panLabel.add(panLabelLoad, BorderLayout.SOUTH);
		panLabelLoad.setLayout(new BorderLayout(0, 0));
		
		verticalStrut = Box.createVerticalStrut(20);
		verticalStrut.setPreferredSize(new Dimension(0, 5));
		panLabelLoad.add(verticalStrut, BorderLayout.SOUTH);
		
		labelLoading = new JLabel("Chargement : ");
		panLabelLoad.add(labelLoading, BorderLayout.WEST);
		
		labelFileInProgress = new JLabel("hum");
		panLabelLoad.add(labelFileInProgress, BorderLayout.CENTER);
		
		hsMainSettingLeft = Box.createHorizontalStrut(20);
		mainPan.add(hsMainSettingLeft, BorderLayout.WEST);
		
		hsMainSettingRight = Box.createHorizontalStrut(20);
		mainPan.add(hsMainSettingRight, BorderLayout.EAST);
		
		vsMainSettingLow = Box.createVerticalStrut(20);
		mainPan.add(vsMainSettingLow, BorderLayout.SOUTH);
		
		panProgressBar = new JPanel();
		mainPan.add(panProgressBar, BorderLayout.CENTER);
		panProgressBar.setLayout(new BorderLayout(0, 0));
		
		progressBarLoad = new JProgressBar();
		panProgressBar.add(progressBarLoad, BorderLayout.NORTH);
		
		LoadThread thread = new LoadThread();
		thread.start();
		
	}
	
	public static void loadlingTerminated()
	{
		//JOptionPane.showMessageDialog(frame,"Chargement terminé",null, JOptionPane.INFORMATION_MESSAGE);
		new MainView();
		frame.dispose();
	}
	
}
