# Simple Text-to-Speech

### by Pappim Pipatkasrira

This program read your text to sentence then speak.
Voicebank for this program can be created.

Words are from the audio files in the voicebank.

#### For the program's proposal is [here](https://github.com/printto/Simple-Text-to-Speech/blob/master/proposal.pdf).

### Library used
- AudioInputStream  
  Use for opening audio file stream.
- Clip  
  Use for audio file.
  
### Design Pattern used
- Composite - Java developers need the Composite pattern because we often must manipulate composites exactly the same way we manipulate primitive objects. [Read more about Composite design pattern](http://www.javaworld.com/article/2074564/learn-java/a-look-at-the-composite-design-pattern.html)
- Observer - Observer pattern is used when there is one-to-many relationship between objects such as if one object is modified, its depenedent objects are to be notified automatically. Observer pattern falls under behavioral pattern category. [Read more about Observer design pattern](https://www.tutorialspoint.com/design_pattern/observer_pattern.htm)
- Iterator - Iterator pattern is very commonly used design pattern in Java and .Net programming environment. This pattern is used to get a way to access the elements of a collection object in sequential manner without any need to know its underlying representation. [Read more about Iterator design pattern](https://www.tutorialspoint.com/design_pattern/iterator_pattern.htm)

### Installation
1. [Download this zip file](https://github.com/printto/Simple-Text-to-Speech/blob/master/runnable%20program.zip)
2. Extract and run Simple Text-to-Speech.jar
3. The voicebank that comes with the program is the lite voicebank that support only 1 speaking mode.  For full program demostration, [download full voicebank sample (149 MB)](https://drive.google.com/open?id=0B-zXMFcTjHNXcEJEUjlXbXRwckk) and load it to the program.

## Code explanation
### Using AudioInputStream with Clip
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

### Java API pages
- [AudioInputStream](https://docs.oracle.com/javase/7/docs/api/javax/sound/sampled/AudioInputStream.html)
- [Clip](https://docs.oracle.com/javase/7/docs/api/javax/sound/sampled/Clip.html)
