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
import javax.swing.JTextField;
import javax.swing.JSeparator;

import model.Dictionary;
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
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

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
	private JPanel panResultContainer;
	private JPanel panChoice;
	private JPanel panChoiceContainer;
	private JPanel panTypeSearch;
	private JPanel rdbTypeSearch;
	private JPanel panchoiceRight;
	private JPanel panRestrictionType;
	private JPanel panRestrictionTypeContainer;
	private JPanel panStopWord;
	private JPanel panStopWordContainer;
	private JPanel panChoiceLeft;

	private JScrollPane scrollPanResult;
	private JScrollPane scrollPanelistDoc;

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
	private Component hsChoiceLeftSetting;
	private Component hsChoiceBeetweenSetting;
	private Component vsBetweenChoiceAndResult;
	private Component hsBetweenRestrictionAndStopWordSetting;

	private JSeparator firstSeparator;
	private JSeparator secondSeparator;

	private JRadioButton rdbtnYes;
	private JRadioButton rdbtnNo;
	private JRadioButton rdbtnSimple;
	private JRadioButton rdbtnAdvance;
	private JRadioButton rdbtnUnion;
	private JRadioButton rdbtnIntersection;

	private final ButtonGroup bgTypeRestriction = new ButtonGroup();
	private final ButtonGroup bgTypeSearch = new ButtonGroup();
	private final ButtonGroup bgStopWord = new ButtonGroup();

	private JButton btnSearch;
	private JTextField textFieldQuery;
	private JEditorPane editorResult;
	private JList<String> listResultDoc;

	/**
	 * Create the application.
	 */
	public MainView(long executionTime)
	{
		initialize();
		editorResult.setText("<br/><center><b><font color='#000080'>Chargement effectué en </font>" + "<font color='blue'>" + (executionTime / 1000f) + "</font>  " + "<font color='#000080'> secondes</font><br />" + "<font color='#000080'>Le dictionnaire est composé de </font><font color='blue'>" + Dictionary.getElements().size() + " </font><font color='#000080'>mots.</font></b></center>");

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
		panTypeSearch.setBorder(new TitledBorder(new LineBorder(new Color(65, 105, 225), 1, true), "Type de recherche", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 128)));
		panTypeSearch.setPreferredSize(new Dimension(155, 10));
		panChoiceContainer.add(panTypeSearch, BorderLayout.WEST);
		panTypeSearch.setLayout(new BorderLayout(0, 0));

		rdbTypeSearch = new JPanel();
		rdbTypeSearch.setPreferredSize(new Dimension(10, 30));
		panTypeSearch.add(rdbTypeSearch, BorderLayout.SOUTH);
		rdbTypeSearch.setLayout(new BorderLayout(0, 0));

		rdbtnSimple = new JRadioButton("Simple");
		rdbtnSimple.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				panchoiceRight.setVisible(false);
				rdbtnUnion.setSelected(true);
				rdbtnYes.setSelected(true);
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
				panchoiceRight.setVisible(true);
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
		panRestrictionType.setPreferredSize(new Dimension(220, 10));
		panchoiceRight.add(panRestrictionType, BorderLayout.WEST);
		panRestrictionType.setLayout(new BorderLayout(0, 0));
		panchoiceRight.setVisible(false);

		hsChoiceBeetweenSetting = Box.createHorizontalStrut(20);
		hsChoiceBeetweenSetting.setPreferredSize(new Dimension(40, 0));
		panRestrictionType.add(hsChoiceBeetweenSetting, BorderLayout.WEST);

		panRestrictionTypeContainer = new JPanel();
		panRestrictionTypeContainer.setBorder(new TitledBorder(new LineBorder(new Color(65, 105, 225), 1, true), "Type de restriction", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 128)));
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

		panStopWord = new JPanel();
		panchoiceRight.add(panStopWord, BorderLayout.CENTER);
		panStopWord.setLayout(new BorderLayout(0, 0));

		hsBetweenRestrictionAndStopWordSetting = Box.createHorizontalStrut(20);
		hsBetweenRestrictionAndStopWordSetting.setPreferredSize(new Dimension(40, 0));
		panStopWord.add(hsBetweenRestrictionAndStopWordSetting, BorderLayout.WEST);

		panChoiceLeft = new JPanel();
		panStopWord.add(panChoiceLeft, BorderLayout.CENTER);
		panChoiceLeft.setLayout(new BorderLayout(0, 0));

		panStopWordContainer = new JPanel();
		panStopWordContainer.setPreferredSize(new Dimension(115, 10));
		panChoiceLeft.add(panStopWordContainer, BorderLayout.WEST);
		panStopWordContainer.setBorder(new TitledBorder(new LineBorder(new Color(65, 105, 225), 1, true), "StopWords", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 128)));
		panStopWordContainer.setLayout(new BorderLayout(0, 0));

		rdbtnYes = new JRadioButton("Oui");
		rdbtnYes.setSelected(true);
		bgStopWord.add(rdbtnYes);
		rdbtnYes.setForeground(new Color(0, 0, 139));
		rdbtnYes.setFont(new Font("Dialog", Font.ITALIC, 12));
		panStopWordContainer.add(rdbtnYes, BorderLayout.WEST);

		rdbtnNo = new JRadioButton("Non");
		bgStopWord.add(rdbtnNo);
		rdbtnNo.setFont(new Font("Dialog", Font.ITALIC, 12));
		rdbtnNo.setForeground(new Color(0, 0, 139));
		panStopWordContainer.add(rdbtnNo, BorderLayout.EAST);

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

		editorResult = new JEditorPane("text/html", "");
		editorResult.setEditable(false);
		scrollPanResult.setViewportView(editorResult);

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
				if (rdbtnAdvance.isSelected() && rdbtnNo.isSelected())
				{
					editorResult.setText(parser.loadApDocument(listResultDoc.getSelectedValue(), textFieldQuery.getText(), false));
				} else
				{
					editorResult.setText(parser.loadApDocument(listResultDoc.getSelectedValue(), textFieldQuery.getText(), true));
				}
			}
		});

		listResultDoc.setBackground(new Color(169, 169, 169));
		scrollPanelistDoc.setViewportView(listResultDoc);
		frmEzSearch.setVisible(true);
	}

	private void startSearch()
	{
		editorResult.setText("");

		if (textFieldQuery.getText().isEmpty())
		{
			JOptionPane.showMessageDialog(frmEzSearch, "Veuillez entrer une requête", null, JOptionPane.ERROR_MESSAGE);
		} else
		{
			Search search = new Search(textFieldQuery.getText());

			if (rdbtnAdvance.isSelected() && rdbtnNo.isSelected())
			{
				search.setUseStopWords(false);
			} else
			{
				search.setUseStopWords(true);
			}

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
