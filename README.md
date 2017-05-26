# Simple Text-to-Speech

### by Pappim Pipatkasrira

This program read your text to sentence then speak.
Voicebank for this program can be created.

Words are from the audio files in the voicebank.

### Used library
- AudioInputStream  
  Use for opening audio file stream.
- Clip  
  Use for audio file.

### Installation
1. [Download this zip file](https://github.com/printto/Simple-Text-to-Speech/blob/master/runnable%20program.zip)
2. Extract and run Simple Text-to-Speech.jar

### Code explanation
#### For declaring Clip to use as an Audio object
1. Create File object (from the existing audio file).
2. Get AudioInputStream from the File.
3. Get Clip from the audio system.
4. use the clip to open and play the audio from AudioInputStream
##### As the example code below
```
File audioFile = new File ( a.wav );
AudioInputStream audioIn = AudioSystem.getAudioInputStream(file);
Clip clip = AudioSystem.getClip();
clip.open(audioIn);
```
#### Now you can use clip to control the audio
```
clip.start();
clip.stop();
```
#### When finish using AudioInputStream, close it
An InputStream ties up a tiny kernel resource, a low level file handle. In addition, the file will be locked to some extent (from delete, renaming), as long as you have it open for read.
```
audioIn.close
```
