package view;

import javax.swing.JFrame;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import java.awt.Dimension;
import java.awt.Component;

import javax.swing.Box;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JSeparator;

import model.ApDocument;
import model.ParserAP;
import model.Search;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JList;
import javax.swing.border.LineBorder;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ScrollPaneConstants;

public class MainView
{

	private JFrame frmEzSearch;
	private JPanel panAction;
	private JPanel panBtn;
	private JPanel mainPanQuery;
	private JPanel panelQueryLabel;
	private JPanel panResult;
	private JPanel panSeparator;
	private JPanel panSeparatorContainer;
	private JPanel panTextAreaResult;
	private JScrollPane scrollPanResult;
	private JScrollPane scrollPaneListDoc;

	private Component vsActionLowSetting;
	private Component hsBtnSearchRightSetting;
	private Component hsBtnSearchLowSetting;
	private Component hsBtnSearchLeftSetting;
	private Component hsActionLeftSetting;
	private Component hsResultLeftSetting;
	private Component hsResultRightSetting;
	private Component vsResultTopSetting;
	private Component vsSeparatorTopSetting;
	private Component vsSeparatorLowSetting;
	private Component hsSeparatorLeftSetting;
	private Component hsSeparatorRightSetting;
	private Component horizontalStrut;

	private JSeparator firstSeparator;
	private JSeparator secondSeparator;
	private JSeparator thirdSeparator;

	private JLabel labelQuery;
	private JButton btnSearch;
	private JTextField textFieldQuery;
	private JTextArea textAreaResult;
	private JPanel panResultContainer;
	private JList<String> listResultDoc;

	/**
	 * Create the application.
	 */
	public MainView()
	{
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize()
	{
		frmEzSearch = new JFrame();
		frmEzSearch.setTitle("EZ-Google");
		frmEzSearch.setBounds(100, 100, 600, 420);
		frmEzSearch.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmEzSearch.getContentPane().setLayout(new BorderLayout(0, 0));

		panAction = new JPanel();
		panAction.setPreferredSize(new Dimension(10, 50));
		frmEzSearch.getContentPane().add(panAction, BorderLayout.SOUTH);
		panAction.setLayout(new BorderLayout(0, 0));

		vsActionLowSetting = Box.createVerticalStrut(20);
		vsActionLowSetting.setPreferredSize(new Dimension(0, 10));
		panAction.add(vsActionLowSetting, BorderLayout.SOUTH);

		panBtn = new JPanel();
		panBtn.setPreferredSize(new Dimension(130, 10));
		panAction.add(panBtn, BorderLayout.EAST);
		panBtn.setLayout(new BorderLayout(0, 0));

		btnSearch = new JButton("Rechercher");
		btnSearch.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				startSearch();
			}
		});
		panBtn.add(btnSearch, BorderLayout.CENTER);

		hsBtnSearchRightSetting = Box.createHorizontalStrut(20);
		hsBtnSearchRightSetting.setPreferredSize(new Dimension(10, 0));
		panBtn.add(hsBtnSearchRightSetting, BorderLayout.EAST);

		hsBtnSearchLowSetting = Box.createVerticalStrut(20);
		hsBtnSearchLowSetting.setPreferredSize(new Dimension(0, 1));
		panBtn.add(hsBtnSearchLowSetting, BorderLayout.SOUTH);

		mainPanQuery = new JPanel();
		panAction.add(mainPanQuery, BorderLayout.CENTER);
		mainPanQuery.setLayout(new BorderLayout(0, 0));

		textFieldQuery = new JTextField();
		mainPanQuery.add(textFieldQuery, BorderLayout.CENTER);
		textFieldQuery.setColumns(10);

		textFieldQuery.addKeyListener(new KeyAdapter()
		{
			public void keyPressed(KeyEvent e)
			{
				if (e.getKeyCode() == KeyEvent.VK_ENTER)
				{
					btnSearch.doClick();
				}
			}
		});

		hsBtnSearchLeftSetting = Box.createHorizontalStrut(20);
		hsBtnSearchLeftSetting.setPreferredSize(new Dimension(10, 0));
		mainPanQuery.add(hsBtnSearchLeftSetting, BorderLayout.EAST);

		panelQueryLabel = new JPanel();
		panelQueryLabel.setPreferredSize(new Dimension(10, 17));
		panAction.add(panelQueryLabel, BorderLayout.NORTH);
		panelQueryLabel.setLayout(new BorderLayout(0, 0));

		labelQuery = new JLabel("   Requête : ");
		labelQuery.setFont(new Font("Dialog", Font.ITALIC, 12));
		labelQuery.setForeground(new Color(0, 0, 128));
		panelQueryLabel.add(labelQuery, BorderLayout.NORTH);

		hsActionLeftSetting = Box.createHorizontalStrut(20);
		hsActionLeftSetting.setPreferredSize(new Dimension(10, 0));
		panAction.add(hsActionLeftSetting, BorderLayout.WEST);

		panResult = new JPanel();
		frmEzSearch.getContentPane().add(panResult, BorderLayout.CENTER);
		panResult.setLayout(new BorderLayout(0, 0));

		hsResultLeftSetting = Box.createHorizontalStrut(20);
		hsResultLeftSetting.setPreferredSize(new Dimension(10, 0));
		panResult.add(hsResultLeftSetting, BorderLayout.WEST);

		hsResultRightSetting = Box.createHorizontalStrut(20);
		hsResultRightSetting.setPreferredSize(new Dimension(10, 0));
		panResult.add(hsResultRightSetting, BorderLayout.EAST);

		vsResultTopSetting = Box.createVerticalStrut(20);
		vsResultTopSetting.setPreferredSize(new Dimension(0, 10));
		vsResultTopSetting.setMinimumSize(new Dimension(0, 10));
		panResult.add(vsResultTopSetting, BorderLayout.NORTH);

		panSeparator = new JPanel();
		panResult.add(panSeparator, BorderLayout.SOUTH);
		panSeparator.setLayout(new BorderLayout(0, 0));

		vsSeparatorTopSetting = Box.createVerticalStrut(20);
		vsSeparatorTopSetting.setPreferredSize(new Dimension(0, 10));
		panSeparator.add(vsSeparatorTopSetting, BorderLayout.NORTH);

		vsSeparatorLowSetting = Box.createVerticalStrut(20);
		vsSeparatorLowSetting.setPreferredSize(new Dimension(0, 5));
		panSeparator.add(vsSeparatorLowSetting, BorderLayout.SOUTH);

		hsSeparatorLeftSetting = Box.createHorizontalStrut(20);
		hsSeparatorLeftSetting.setPreferredSize(new Dimension(10, 0));
		panSeparator.add(hsSeparatorLeftSetting, BorderLayout.WEST);

		hsSeparatorRightSetting = Box.createHorizontalStrut(20);
		hsSeparatorRightSetting.setPreferredSize(new Dimension(10, 0));
		panSeparator.add(hsSeparatorRightSetting, BorderLayout.EAST);

		panSeparatorContainer = new JPanel();
		panSeparator.add(panSeparatorContainer, BorderLayout.CENTER);
		panSeparatorContainer.setLayout(new BorderLayout(0, 0));

		firstSeparator = new JSeparator();
		panSeparatorContainer.add(firstSeparator, BorderLayout.NORTH);

		secondSeparator = new JSeparator();
		panSeparatorContainer.add(secondSeparator, BorderLayout.CENTER);

		thirdSeparator = new JSeparator();
		panSeparatorContainer.add(thirdSeparator, BorderLayout.SOUTH);

		panResultContainer = new JPanel();
		panResult.add(panResultContainer, BorderLayout.CENTER);
		panResultContainer.setLayout(new BorderLayout(0, 0));

		listResultDoc = new JList<String>();
		listResultDoc.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				JOptionPane.showMessageDialog(frmEzSearch, listResultDoc.getSelectedValue(), null, JOptionPane.ERROR_MESSAGE);
				ParserAP parser = new ParserAP();
				ApDocument apDocument = parser.loadApDocument(listResultDoc.getSelectedValue());
				System.out.println(apDocument);
				textAreaResult.setText(apDocument.toString());
			}
		});
		listResultDoc.setBackground(new Color(169, 169, 169));
		listResultDoc.setBorder(new LineBorder(new Color(128, 128, 128)));
		listResultDoc.setPreferredSize(new Dimension(120, 0));
		//panResultContainer.add(listResultDoc, BorderLayout.WEST);

		panTextAreaResult = new JPanel();
		panResultContainer.add(panTextAreaResult, BorderLayout.CENTER);
		panTextAreaResult.setLayout(new BorderLayout(0, 0));

		horizontalStrut = Box.createHorizontalStrut(20);
		horizontalStrut.setPreferredSize(new Dimension(10, 0));
		panTextAreaResult.add(horizontalStrut, BorderLayout.WEST);

		scrollPanResult = new JScrollPane();
		panTextAreaResult.add(scrollPanResult, BorderLayout.CENTER);

		textAreaResult = new JTextArea();
		textAreaResult.setEditable(false);
		scrollPanResult.setViewportView(textAreaResult);
		
		scrollPaneListDoc = new JScrollPane(listResultDoc);
		scrollPaneListDoc.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPaneListDoc.setPreferredSize(new Dimension(130, 3));
		//scrollPaneListDoc.setViewportView(listResultDoc);
		panResultContainer.add(scrollPaneListDoc, BorderLayout.WEST);

		frmEzSearch.setVisible(true);
	}

	private void startSearch()
	{
		textAreaResult.setText("");

		if (textFieldQuery.getText().isEmpty())
		{
			JOptionPane.showMessageDialog(frmEzSearch, "Veuillez entrer une requête", null, JOptionPane.ERROR_MESSAGE);
		} else
		{
			Search search = new Search(textFieldQuery.getText());
			search.execute();
			textAreaResult.setText(search.getResult().toString());
			DefaultListModel<String> result = search.getListResultOrdered();
			listResultDoc.setModel(result);
		}
	}
}
