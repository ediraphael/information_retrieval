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
import java.awt.Font;
import java.awt.Color;

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
    private Component vsPanLoadLowSetting;
    private JLabel labelLoad;
    private JLabel labelFileInProgress;
    private JPanel panContainerLabelLoad;
    private JPanel panTitle;
	
    
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
		
		vsPanLoadLowSetting = Box.createVerticalStrut(20);
		vsPanLoadLowSetting.setPreferredSize(new Dimension(0, 3));
		panLabelLoad.add(vsPanLoadLowSetting, BorderLayout.SOUTH);
		
		labelFileInProgress = new JLabel("");
		labelFileInProgress.setForeground(new Color(0, 0, 128));
		labelFileInProgress.setFont(new Font("Dialog", Font.PLAIN, 12));
		panLabelLoad.add(labelFileInProgress, BorderLayout.CENTER);
		
		panContainerLabelLoad = new JPanel();
		panContainerLabelLoad.setPreferredSize(new Dimension(260, 10));
		panLabelLoad.add(panContainerLabelLoad, BorderLayout.WEST);
		panContainerLabelLoad.setLayout(new BorderLayout(0, 0));
		
		labelLoad = new JLabel("Chargement : ");
		panContainerLabelLoad.add(labelLoad, BorderLayout.EAST);
		labelLoad.setForeground(new Color(0, 0, 128));
		labelLoad.setFont(new Font("Dialog", Font.PLAIN, 12));
		
		panTitle = new JPanel();
		panLabel.add(panTitle, BorderLayout.CENTER);
		panTitle.setLayout(new BorderLayout(0, 0));
		
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
		progressBarLoad.setStringPainted(true);
		progressBarLoad.setToolTipText("");
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
