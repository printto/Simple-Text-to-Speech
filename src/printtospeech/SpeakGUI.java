package printtospeech;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.border.TitledBorder;
import javax.swing.text.NumberFormatter;

/**
 * GUI version of Printto Speech.
 * @author Pappim Pipatkasrira
 * @version 1.0
 * @since Mar 18, 2017
 */

public class SpeakGUI extends JFrame implements Observer{

	Speech speech;
	//Name of the voicebank
	String name = "Name wasn't set in char.txt";
	//Voicebank's greeting sentence
	String greet = "Greeting wasn't set in char.txt";
	//Sentence of the spoken words
	String spokenFormat = "I've just spoken %s";
	//Picture of the voicebank
	JLabel picture = new JLabel("Picture wasn't set in char.txt");
	//Speaking sentence TextField
	JTextField sentenceIn = new JTextField();
	//Speaking buttons
	JButton button1 = new JButton("Default Speaking");
	JButton button2 = new JButton("none");
	JButton button3 = new JButton("none");
	JButton button4 = new JButton("none");
	JButton button5 = new JButton("none");
	//Speed of the speech
	JFormattedTextField speed;
	//Panel for display interactions
	JLabel greetPanel;
	//Voicebank location
	String voiceLocation = "./voice/";
	//For browse file
	JTextField location = new JTextField("./voice/");
	JButton browseButton = new JButton("Browse...");

	/**
	 * Create JFrame and initialize
	 */
	public SpeakGUI(Speech speech){
		this.speech = speech;
		initTextFormat();
		initProfile();
		initButtons();
		initComponents();
		initActions();
	}

	/**
	 * Initialize TextField Format for speaking speed
	 */
	public void initTextFormat(){
		NumberFormat numberFormat = NumberFormat.getIntegerInstance();
		NumberFormatter numberFormatter = new NumberFormatter(numberFormat);
		numberFormatter.setValueClass(Integer.class);
		numberFormatter.setAllowsInvalid(true);
		numberFormatter.setMaximum(999);
		speed = new JFormattedTextField(numberFormatter);
		speed.setText("200");
		speed.setColumns(5);
	}

	/**
	 * Initialize voicebank's profile from char.txt
	 */
	private void initProfile() {
		//reset
		speech.setVoiceLocation(voiceLocation);
		//initialize
		LoadVoiceConfig readChar = new LoadVoiceConfig(voiceLocation + "/char.txt");
		readChar.setDelimiter('=');
		List<String[]> charactor = new ArrayList<String[]>();
		while(readChar.hasNext()){
			charactor.add(readChar.next());
		}
		for(String[] info : charactor){
			switch (info[0]){
			case "name":
				this.name = info[1];
				break;
			case "greeting":
				this.greet = info[1];
				break;
			case "picture":
				this.picture = new JLabel(new ImageIcon(voiceLocation + info[1]));
				break;
			case "spoken":
				this.spokenFormat = info[1];
				break;
			default:
			}
		}
	}

	/**
	 * Initialize voicebank's mode from config.txt
	 */
	private void initButtons() {
		//reset
		button1.setText("Default Speaking");
		button2.setText("none");
		button2.setEnabled(true);
		button3.setText("none");
		button4.setText("none");
		button5.setText("none");
		//initialize
		try{
			LoadVoiceConfig readChar = new LoadVoiceConfig(voiceLocation + "config.txt");
			readChar.setDelimiter('=');
			List<String[]> charactor = new ArrayList<String[]>();
			while(readChar.hasNext()){
				charactor.add(readChar.next());
			}
			for(String[] info : charactor){
				switch (info[0]){
				case "button1":
					//If default button text is "none" will not be initialized.
					if(!info[1].split(",")[0].equals("none")){
						button1.setText(info[1].split(",")[0]);
					}
					break;
				case "button2":
					button2.setText(info[1].split(",")[0]);
					break;
				case "button3":
					button3.setText(info[1].split(",")[0]);
					break;
				case "button4":
					button4.setText(info[1].split(",")[0]);
					break;
				case "button5":
					button5.setText(info[1].split(",")[0]);
					break;
				default:
				}
			}
		}catch(RuntimeException e){
			button2.setText("config.txt not found.");
			button2.setEnabled(false);
		}

	}

	/**
	 * Initialize components.
	 */
	private void initComponents() {
		this.setTitle(name);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().removeAll();
		this.setLayout(new BorderLayout());
		this.setMinimumSize(new Dimension(750, 400));
		//South part
		JPanel south = new JPanel();
		south.add(new JLabel("Set words duration: "));
		south.add(speed);
		south.add(location);
		location.setEnabled(false);
		south.add(browseButton);
		south.setBorder(new TitledBorder("Settings Panel"));
		this.add( south , BorderLayout.SOUTH);
		//North part
		JPanel north = new JPanel();
		north.setBorder(new TitledBorder("Greeting Panel"));
		greetPanel = new JLabel(greet);
		north.add( greetPanel );
		this.add( north , BorderLayout.NORTH);
		//West part
		picture.setSize(1, 1);
		picture.setBorder(new TitledBorder("Avatar"));
		this.add(picture , BorderLayout.WEST);
		//Center part
		sentenceIn.setBorder(new TitledBorder("Sentence to speak"));
		this.add(sentenceIn , BorderLayout.CENTER);
		//East part
		JPanel east = new JPanel();
		east.setLayout(new BoxLayout(east, BoxLayout.Y_AXIS));
		east.setBorder(new TitledBorder("Speak!"));
		east.add(button1);
		if(!button2.getText().equals("none"))
			east.add(button2);
		if(!button3.getText().equals("none"))
			east.add(button3);
		if(!button4.getText().equals("none"))
			east.add(button4);
		if(!button5.getText().equals("none"))
			east.add(button5);
		this.add(east , BorderLayout.EAST);
		this.pack();
	}

	/**
	 * Add ActionListener to buttons
	 */
	private void initActions(){
		button1.addActionListener(new button1Pressed());
		button2.addActionListener(new button2Pressed());
		button3.addActionListener(new button3Pressed());
		button4.addActionListener(new button4Pressed());
		button5.addActionListener(new button5Pressed());
		browseButton.addActionListener(new BrowsingAction());
	}

	private void setOnOff(boolean onOff){
		if(onOff){
			button1.setEnabled(true);
			button2.setEnabled(true);
			button3.setEnabled(true);
			button4.setEnabled(true);
			button5.setEnabled(true);
		}
		else{
			button1.setEnabled(false);
			button2.setEnabled(false);
			button3.setEnabled(false);
			button4.setEnabled(false);
			button5.setEnabled(false);
		}
	}

	/**
	 * ActionListener for browsing for voicebanks
	 * @author Pappim Pipatkasrira
	 * @version 1.0
	 * @since Apr 20, 2017
	 */
	public class BrowsingAction implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			fileChooser.setAcceptAllFileFilterUsed(false);
			int checkValid = fileChooser.showOpenDialog(null);
			if (checkValid == JFileChooser.APPROVE_OPTION) {
				name = "Name wasn't set in char.txt";
				greet = "Greeting wasn't set in char.txt";
				spokenFormat = "I've just spoken %s";
				picture = new JLabel("Picture wasn't set in char.txt");
				location.setText(fileChooser.getSelectedFile().toString() + "/");
				voiceLocation = location.getText();
				speech.setVoiceLocation(voiceLocation);
				initProfile();
				initButtons();
				speech.initModes();
				initComponents();
			}
		}
	}

	/**
	 * Make this JFrame visible.
	 */
	public void run(){
		this.setVisible(true);
	}

	/**
	 * Update when finish playing sound.
	 */
	@Override
	public void update(Observable o, Object arg) {
		setOnOff(true);
		greetPanel.setText(String.format(spokenFormat, sentenceIn.getText()));
	}

	/**
	 * ActionListener classes for speaking.
	 */
	public class button1Pressed implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			if(!sentenceIn.getText().equals("")){
				setOnOff(false);
				Thread speakingThread = new Thread(new Runnable(){
					@Override
					public void run() {
						speech.speakSentence(sentenceIn.getText() , Integer.parseInt( speed.getText() ) , 1 );
					}});
				speakingThread.start();
				greetPanel.setText("Speaking " + sentenceIn.getText());
			}
		}
	}
	public class button2Pressed implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			if(!sentenceIn.getText().equals("")){
				setOnOff(false);
				Thread speakingThread = new Thread(new Runnable(){
					@Override
					public void run() {
						speech.speakSentence(sentenceIn.getText() , Integer.parseInt( speed.getText() ) , 2 );
					}});
				speakingThread.start();
				greetPanel.setText("Speaking " + sentenceIn.getText());
			}
		}
	}
	public class button3Pressed implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			if(!sentenceIn.getText().equals("")){
				setOnOff(false);
				Thread speakingThread = new Thread(new Runnable(){
					@Override
					public void run() {
						speech.speakSentence(sentenceIn.getText() , Integer.parseInt( speed.getText() ) , 3 );
					}});
				speakingThread.start();
				greetPanel.setText("Speaking " + sentenceIn.getText());
			}
		}
	}
	public class button4Pressed implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			if(!sentenceIn.getText().equals("")){
				setOnOff(false);
				Thread speakingThread = new Thread(new Runnable(){
					@Override
					public void run() {
						speech.speakSentence(sentenceIn.getText() , Integer.parseInt( speed.getText() ) , 4 );
					}});
				speakingThread.start();
				greetPanel.setText("Speaking " + sentenceIn.getText());
			}
		}
	}
	public class button5Pressed implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			if(!sentenceIn.getText().equals("")){
				setOnOff(false);
				Thread speakingThread = new Thread(new Runnable(){
					@Override
					public void run() {
						speech.speakSentence(sentenceIn.getText() , Integer.parseInt( speed.getText() ) , 5 );
					}});
				speakingThread.start();
				greetPanel.setText("Speaking " + sentenceIn.getText());
			}
		}
	}
}
