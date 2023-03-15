package domain;

import javax.sound.midi.*;

public class MyBeautifulMusicTest {

	public void play() throws MidiUnavailableException {
	 Sequencer sequencer = MidiSystem.getSequencer(); 
	 System.out.println("We got a sequencer"); 
	 }

	public static void main(String[] args) {
		MyBeautifulMusicTest mt = new MyBeautifulMusicTest();
		try {
			mt.play();
		} catch (MidiUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
