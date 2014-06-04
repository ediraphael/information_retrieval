package view;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
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

	private JFrame frame;

	private JPanel mainPan;
	private JPanel panLabel;
	private JPanel panProgressBar;
	private JPanel panContainerLabelLoad;
	private JPanel panTitle;
	private JPanel panImg;
	private JPanel panLabelLoad;

	private JLabel labelLoad;
	private JLabel labelFileInProgress;
	private JLabel labelImgLoading;
	private JLabel labelImgMen;

	private Component hsMainSettingLeft;
	private Component hsMainSettingRight;
	private Component vsMainSettingLow;
	private Component vsPanLoadLowSetting;
	private Component horizontalStrut;

	private JProgressBar progressBarLoad;
	private ParserAP parser = new ParserAP();

	private ImageIcon imgMen;
	private ImageIcon imgLoading;
	private JPanel panel;
	private Component horizontalStrut_1;
	public static long executionTime;
	
	public class LoadThread extends Thread
	{
		public void run()
		{
			long start = System.currentTimeMillis();
			parser.loadAllApDocumentByFolder(progressBarLoad, labelFileInProgress);
			long end = System.currentTimeMillis();
			
			LoadView.executionTime = end - start;
			loadingTerminated();
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
					
				} catch (Exception e)
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
		frame.setBounds(100, 100, 600, 420);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		mainPan = new JPanel();
		frame.getContentPane().add(mainPan, BorderLayout.CENTER);
		mainPan.setLayout(new BorderLayout(0, 0));

		panLabel = new JPanel();
		panLabel.setPreferredSize(new Dimension(10, 350));
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

		panImg = new JPanel();
		panTitle.add(panImg, BorderLayout.CENTER);
		panImg.setLayout(new BorderLayout(0, 0));

		imgLoading = new ImageIcon("./bin/document/image/loading.gif");
		labelImgLoading = new JLabel("");
		labelImgLoading.setIcon(imgLoading);
		panImg.add(labelImgLoading, BorderLayout.CENTER);

		horizontalStrut = Box.createHorizontalStrut(20);
		horizontalStrut.setPreferredSize(new Dimension(200, 0));
		panImg.add(horizontalStrut, BorderLayout.WEST);

		imgMen = new ImageIcon("./bin/document/image/men.gif");

		panel = new JPanel();
		panImg.add(panel, BorderLayout.SOUTH);
		panel.setLayout(new BorderLayout(0, 0));
		labelImgMen = new JLabel("");
		panel.add(labelImgMen, BorderLayout.CENTER);
		labelImgMen.setIcon(imgMen);

		horizontalStrut_1 = Box.createHorizontalStrut(20);
		horizontalStrut_1.setPreferredSize(new Dimension(180, 0));
		panel.add(horizontalStrut_1, BorderLayout.WEST);

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

		Thread threadLoadView = new LoadThread();
		frame.setVisible(true);
		threadLoadView.start();
	}

	public void loadingTerminated()
	{
		new MainView(executionTime);
		frame.dispose();
	}

}
