package domain;

import javax.sound.midi.*;

public class MusicTest1 {

	public void play() throws MidiUnavailableException {
	 Sequencer sequencer = MidiSystem.getSequencer(); 
	 System.out.println("We got a sequencer"); 
	 }

	public static void main(String[] args) {
		MusicTest1 mt = new MusicTest1();
		try {
			mt.play();
		} catch (MidiUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
