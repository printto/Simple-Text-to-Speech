package printtospeech;

import java.util.Scanner;

public class ProgrammingTool {
	public static void main(String[] args){
		Scanner input = new Scanner(System.in);
		String words = input.next();
		String[] wordsarray = words.split("");
		for(String word: wordsarray){
			System.out.println("case \""+word+"\":");
		}
	}
}
