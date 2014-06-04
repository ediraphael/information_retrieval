package view;

import javax.swing.JFrame;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import java.awt.Dimension;
import java.awt.Component;

import javax.swing.Box;
import javax.swing.DefaultListModel;
import javax.swing.JEditorPane;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JSeparator;

import model.ParserAP;
import model.Search;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JList;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;

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

	private Component vsActionLowSetting;
	private Component hsBtnSearchRightSetting;
	private Component hsBtnSearchLowSetting;
	private Component hsBtnSearchLeftSetting;
	private Component hsActionLeftSetting;
	private Component hsResultLeftSetting;
	private Component hsResultRightSetting;
	private Component vsResultTopSetting;
	private Component vsSeparatorTopSetting;
	private Component hsSeparatorLeftSetting;
	private Component hsSeparatorRightSetting;
	private Component horizontalStrut;

	private JSeparator firstSeparator;
	private JSeparator secondSeparator;
	private JButton btnSearch;
	private JTextField textFieldQuery;
	private JEditorPane textAreaResult;
	private JPanel panResultContainer;
	private JScrollPane scrollPanelistDoc;
	private JList<String> listResultDoc;
	private JPanel panChoice;
	private Component hsChoiceLeftSetting;
	private JPanel panChoiceContainer;
	private JPanel panTypeSearch;
	private JLabel labelTypeOfSearch;
	private JPanel rdbTypeSearch;
	private JRadioButton rdbtnSimple;
	private JRadioButton rdbtnAdvance;
	private JPanel panchoiceRight;
	private JPanel panRestrictionType;
	private Component hsChoiceBeetweenSetting;
	private JPanel panRestrictionTypeContainer;
	private JRadioButton rdbtnUnion;
	private JRadioButton rdbtnIntersection;
	private JLabel labelTypeDeRestriction;
	private final ButtonGroup bgTypeRestriction = new ButtonGroup();
	private final ButtonGroup bgTypeSearch = new ButtonGroup();
	private Component vsBetweenChoiceAndResult;

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

		vsBetweenChoiceAndResult = Box.createVerticalStrut(20);
		vsBetweenChoiceAndResult.setPreferredSize(new Dimension(0, 10));
		panSeparatorContainer.add(vsBetweenChoiceAndResult, BorderLayout.SOUTH);

		panChoice = new JPanel();
		panChoice.setPreferredSize(new Dimension(10, 50));
		panSeparator.add(panChoice, BorderLayout.SOUTH);
		panChoice.setLayout(new BorderLayout(0, 0));

		hsChoiceLeftSetting = Box.createHorizontalStrut(20);
		hsChoiceLeftSetting.setPreferredSize(new Dimension(10, 0));
		panChoice.add(hsChoiceLeftSetting, BorderLayout.WEST);

		panChoiceContainer = new JPanel();
		panChoice.add(panChoiceContainer, BorderLayout.CENTER);
		panChoiceContainer.setLayout(new BorderLayout(0, 0));

		panTypeSearch = new JPanel();
		panTypeSearch.setPreferredSize(new Dimension(145, 10));
		panChoiceContainer.add(panTypeSearch, BorderLayout.WEST);
		panTypeSearch.setLayout(new BorderLayout(0, 0));

		labelTypeOfSearch = new JLabel("Type de recherche :");
		labelTypeOfSearch.setFont(new Font("Dialog", Font.BOLD, 12));
		labelTypeOfSearch.setForeground(new Color(0, 0, 128));
		panTypeSearch.add(labelTypeOfSearch, BorderLayout.NORTH);

		rdbTypeSearch = new JPanel();
		rdbTypeSearch.setPreferredSize(new Dimension(10, 30));
		panTypeSearch.add(rdbTypeSearch, BorderLayout.SOUTH);
		rdbTypeSearch.setLayout(new BorderLayout(0, 0));

		rdbtnSimple = new JRadioButton("Simple");
		rdbtnSimple.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				panRestrictionType.setVisible(false);
				rdbtnUnion.setSelected(true);
			}
		});
		bgTypeSearch.add(rdbtnSimple);
		rdbtnSimple.setSelected(true);
		rdbtnSimple.setForeground(new Color(0, 0, 139));
		rdbtnSimple.setFont(new Font("Dialog", Font.ITALIC, 12));
		rdbTypeSearch.add(rdbtnSimple, BorderLayout.WEST);

		rdbtnAdvance = new JRadioButton("Avancé");
		rdbtnAdvance.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				panRestrictionType.setVisible(true);
			}
		});
		bgTypeSearch.add(rdbtnAdvance);
		rdbtnAdvance.setForeground(new Color(0, 0, 139));
		rdbtnAdvance.setFont(new Font("Dialog", Font.ITALIC, 12));
		rdbtnAdvance.setActionCommand("Avancé");
		rdbTypeSearch.add(rdbtnAdvance, BorderLayout.EAST);

		panchoiceRight = new JPanel();
		panChoiceContainer.add(panchoiceRight, BorderLayout.CENTER);
		panchoiceRight.setLayout(new BorderLayout(0, 0));

		panRestrictionType = new JPanel();
		panRestrictionType.setPreferredSize(new Dimension(210, 10));
		panchoiceRight.add(panRestrictionType, BorderLayout.WEST);
		panRestrictionType.setLayout(new BorderLayout(0, 0));
		panRestrictionType.setVisible(false);

		hsChoiceBeetweenSetting = Box.createHorizontalStrut(20);
		hsChoiceBeetweenSetting.setPreferredSize(new Dimension(40, 0));
		panRestrictionType.add(hsChoiceBeetweenSetting, BorderLayout.WEST);

		panRestrictionTypeContainer = new JPanel();
		panRestrictionType.add(panRestrictionTypeContainer, BorderLayout.CENTER);
		panRestrictionTypeContainer.setLayout(new BorderLayout(0, 0));

		rdbtnUnion = new JRadioButton("Union");
		bgTypeRestriction.add(rdbtnUnion);
		rdbtnUnion.setSelected(true);
		rdbtnUnion.setFont(new Font("Dialog", Font.ITALIC, 12));
		rdbtnUnion.setForeground(new Color(0, 0, 139));
		panRestrictionTypeContainer.add(rdbtnUnion, BorderLayout.WEST);

		rdbtnIntersection = new JRadioButton("Intersection");
		bgTypeRestriction.add(rdbtnIntersection);
		rdbtnIntersection.setFont(new Font("Dialog", Font.ITALIC, 12));
		rdbtnIntersection.setForeground(new Color(0, 0, 139));
		panRestrictionTypeContainer.add(rdbtnIntersection, BorderLayout.EAST);

		labelTypeDeRestriction = new JLabel("Type de restriction :");
		labelTypeDeRestriction.setForeground(new Color(0, 0, 128));
		panRestrictionTypeContainer.add(labelTypeDeRestriction, BorderLayout.NORTH);

		panResultContainer = new JPanel();
		panResult.add(panResultContainer, BorderLayout.CENTER);
		panResultContainer.setLayout(new BorderLayout(0, 0));

		panTextAreaResult = new JPanel();
		panResultContainer.add(panTextAreaResult, BorderLayout.CENTER);
		panTextAreaResult.setLayout(new BorderLayout(0, 0));

		horizontalStrut = Box.createHorizontalStrut(20);
		horizontalStrut.setPreferredSize(new Dimension(10, 0));
		panTextAreaResult.add(horizontalStrut, BorderLayout.WEST);

		scrollPanResult = new JScrollPane();
		panTextAreaResult.add(scrollPanResult, BorderLayout.CENTER);

		textAreaResult = new JEditorPane("text/html", "");
		textAreaResult.setEditable(false);
		scrollPanResult.setViewportView(textAreaResult);

		scrollPanelistDoc = new JScrollPane();
		scrollPanelistDoc.setPreferredSize(new Dimension(130, 0));
		panResultContainer.add(scrollPanelistDoc, BorderLayout.WEST);

		listResultDoc = new JList<String>();
		listResultDoc.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				ParserAP parser = new ParserAP();
				textAreaResult.setText(parser.loadApDocument(listResultDoc.getSelectedValue(), textFieldQuery.getText()));
			}
		});

		listResultDoc.setBackground(new Color(169, 169, 169));
		scrollPanelistDoc.setViewportView(listResultDoc);
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

			if (rdbtnAdvance.isSelected() && rdbtnIntersection.isSelected())
			{
				search.executeIntersection();
			} else
			{
				search.executeUnion();
			}
			DefaultListModel<String> result = search.getListResultOrdered();
			listResultDoc.setModel(result);
		}
	}
}
