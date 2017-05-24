package printtospeech;

public class Main {

	public static void main(String[] args){
		Speech speech = new Speech();
		SpeakGUI ui = new SpeakGUI(speech);
		speech.addObserver(ui);
		ui.run();
		Console.play();
	}
	
}
