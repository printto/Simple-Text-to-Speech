package printtospeech;

import java.util.Scanner;

/**
 * Console version of Printto Speech.
 * @author Pappim Pipatkasrira
 * @version 1.0
 * @since Mar 18, 2017
 */

public class Console {
	
	//Declare Scanner.
	public static Scanner input = new Scanner(System.in);

	//Run on console.
	public static void play(){
		while(true){
			System.out.print("\nPlease enter sentence: ");
			String sentence = input.nextLine();
			Speech speech = new Speech();
			speech.speakSentence(sentence , 200 , 1);
		}
	}

}
