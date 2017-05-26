package printtospeech;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.NoSuchElementException;

/**
 * This class read txt files and return array of elements in the line.
 * @author Pappim Pipatkasrira
 * @version 1.0
 * @since May 26, 2017
 */
public class LoadVoiceConfig {

	//Delimiter that seperates elements in CSV file.
	String delimiter = "=";
	//InputStream.
	InputStream inStream;
	//BufferedReader that reads line.
	BufferedReader buffer;
	//Line that recieved from hasNext or next.
	String line;
	//Check if called hasNext or not.
	boolean calledHasNext = false;

	/**
	 * Create CSVReader from InputStream.
	 * @param inStream as InputStream.
	 */
	public LoadVoiceConfig ( InputStream inStream ){
		this.inStream = inStream;
		Reader reader = new InputStreamReader(inStream);
		buffer = new BufferedReader(reader);
	}

	/**
	 * Create LoadVoiceConfig from String of URL or file location.
	 * @param inString of file location.
	 */
	public LoadVoiceConfig ( String inString){
		try {
			File file = new File(inString);
			inStream = new FileInputStream(file);
			Reader reader = new InputStreamReader(inStream);
			buffer = new BufferedReader(reader);
		} catch (FileNotFoundException e) {
			System.err.println("File" + inString + "not found");
		}
	}


	/**
	 * Close LoadVoiceConfig.
	 * Close InputStream and BufferReader in CSVReader.
	 */
	public void close(){
		try {
			inStream.close();
			buffer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Set delimiter that is used to separate the config txt file.
	 * @param Character that use to separate the file.
	 */
	public void setDelimiter(char c){
		delimiter = Character.toString(c);
	}

	/**
	 * Get the character that is set to be the delimiter.
	 * @return Delimiter that is set in CSVReader.
	 */
	public char getDelimiter(){
		return delimiter.charAt(0);
	}

	/**
	 * Check if the file ended or not.
	 * @return True if it's not the end of the line.
	 */
	public boolean hasNext(){
		//If hasNext hasn't been called before or called next already.
		if(!calledHasNext){
			try {
				calledHasNext = true;
				line = buffer.readLine();
				if(line != null){
					//Skip waste or comment lines
					line = line.trim();
					while(line.isEmpty() || line.charAt(0) == '#'){
						line = buffer.readLine();
					}
				}
				return (line != null);
			} catch (IOException e) {
				return false;
			}
		}
		//If hasNext has been called before.
		else{
			return (line != null);
		}
	}

	/**
	 * Get elements of the line as an array.
	 * @return Array of elements in the line as a String array.
	 */
	public String[] next(){
		//If hasNext was called before.
		if(calledHasNext == true){
			if(line != null){
				calledHasNext = false;
				String[] lineArray = line.split(delimiter);
				for(int i = 0 ; i < lineArray.length ; i++){
					lineArray[i] = lineArray[i].trim();
				}
				return lineArray;
			}
			else{
				throw new NoSuchElementException();
			}
		}
		//If hasNext wasn't used.
		else{
			try {
				line = buffer.readLine();
				line = line.trim();
				if(line.isEmpty() || line.charAt(0) == '#'){
					line = buffer.readLine();
				}
				String[] lineArray = line.split(delimiter);
				for(int i = 0 ; i < lineArray.length ; i++){
					lineArray[i] = lineArray[i].trim();
				}
				return lineArray;
			} catch (IOException e) {
				throw new RuntimeException();
			}
		}

	}

	/**
	 * Blank remove method.
	 */
	public void remove(){

	}
}
