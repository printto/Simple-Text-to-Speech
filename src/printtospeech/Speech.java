package printtospeech;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.concurrent.TimeUnit;

import javax.sound.sampled.*;

/**
 * Synthesize speech from text.
 * @author Pappim Pipatkasrira
 * @version 1.0
 * @since Mar 18, 2017
 */

public class Speech extends Observable{

	public String[] words;
	AudioInputStream audioIn;
	String[] button1 = {"","normal","normal","normal"};
	String[] button2;
	String[] button3;
	String[] button4;
	String[] button5;
	String voiceLocation = "voice/";

	public void setVoiceLocation (String location){
		voiceLocation = location;
	}

	/**
	 * Initialize speaking modes.
	 */
	public void initModes(){
		CSVReader readChar = new CSVReader(voiceLocation + "config.txt");
		readChar.setDelimiter('=');
		List<String[]> charactor = new ArrayList<String[]>();
		while(readChar.hasNext()){
			charactor.add(readChar.next());
		}
		for(String[] info : charactor){
			switch (info[0]){
			case "button1":
				button1 = info[1].split(",");
				break;
			case "button2":
				button2 = info[1].split(",");
				break;
			case "button3":
				button3 = info[1].split(",");
				break;
			case "button4":
				button4 = info[1].split(",");
				break;
			case "button5":
				button5 = info[1].split(",");
				break;
			default:

			}
		}
	}

	/**
	 * Speak from the sentence.
	 * @param sentence , speed , button number
	 */
	public String speakSentence(String sentence , int speed , int mode){
		String[] selected = button1;
		switch (mode){			
		case 2:
			selected = button2;
			break;
		case 3:
			selected = button3;
			break;
		case 4:
			selected = button4;
			break;
		case 5:
			selected = button5;
			break;
		default:
			selected = button1;
		}
		words = splitWords(sentence);
		System.out.print("Playing: "+sentence+"\n");
		for(int i = 0 ; i < words.length ; i++){
			if(i == 0)
				playSound(voiceLocation + selected[1] + "/" + words[i] + ".wav" , speed);
			else if(i == words.length - 1)
				playSound(voiceLocation + selected[3] + "/" + words[i] + ".wav" , speed);
			else
				playSound(voiceLocation + selected[2] + "/" + words[i] + ".wav" , speed);
		}
		setChanged();
		notifyObservers("done");
		return sentence;
	}

	/**
	 * Split sentence to romaji array of words.
	 * @param sentence
	 * @return words array
	 */
	public String[] splitWords(String sentence){
		String[] primaryWords = sentence.split(" ");
		List<String> secondaryWords = new ArrayList<String>();
		Hiragana hiragana = new Hiragana();
		//Check all the words. If it's Hiragana, convert it to romaji
		for(int i = 0 ; i < primaryWords.length ; i++){
			//If word are Hiragana, it'll stuck as many syllibles.
			if(hiragana.isHiragana(primaryWords[i].charAt(0))){
				boolean specialWords = primaryWords[i].equals("は") || primaryWords[i].equals("へ") || primaryWords[i].equals("を");
				boolean notStartStop = i < primaryWords.length - 1 && i != 0;
				//Special words will have only 1 syllable in the middle of the sentence
				if(specialWords && notStartStop && primaryWords[i].length() == 1){
					switch(primaryWords[i]){
					case "は":
						secondaryWords.add("wa");
						break;
					case "へ":
						secondaryWords.add("e");
						break;
					case "を":
						secondaryWords.add("o");
						break;
					}
				}
				else{
					for(String wordRomaji : hiragana.toRomajiArray(primaryWords[i])){
						secondaryWords.add(wordRomaji);
					}
				}
			}
			else{
				secondaryWords.add(primaryWords[i]);
			}
		}
		words = new String[secondaryWords.size()];
		words = secondaryWords.toArray(words);
		return words;
	}

	/**
	 * Play 1 sound at a time.
	 * @param soundFileTitle is the word that matches the wav file , speed
	 */
	private void playSound(String soundFileTitle , int speed) {
		try{
			File file = new File(soundFileTitle.toLowerCase());
			audioIn = AudioSystem.getAudioInputStream(file.toURI().toURL());  
			Clip clip = AudioSystem.getClip();
			clip.open(audioIn);
			clip.start();
			try {
				TimeUnit.MILLISECONDS.sleep(speed);
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
			clip.stop();
		} catch (UnsupportedAudioFileException e) {
			System.err.println("Unsupported Audio File");
		} catch (IOException e) {
			System.err.println("File "+ soundFileTitle + " not found.");
		} catch (LineUnavailableException e) {
			System.err.println("Line Unavailable.");
		}
	}
}
