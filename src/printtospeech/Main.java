package printtospeech;

/**
 * Run Text-to-Speech in both GUI mode and console mode.
 * @author Pappim Pipatkasrira
 * @version 1.0
 * @since May 25, 2017
 */
public class Main {

	public static void main(String[] args){
		Speech speech = new Speech();
		SpeakGUI ui = new SpeakGUI(speech);
		speech.addObserver(ui);
		ui.run();
		Console.play();
	}
	
}
