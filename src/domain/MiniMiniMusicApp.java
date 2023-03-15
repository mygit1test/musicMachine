package domain;

import javax.sound.midi.*;

public class MiniMiniMusicApp {

	private static final int NOTE_ON = 0;
	private static final int NOTE_OFF = 0;

	public MiniMiniMusicApp() {
	}

	public void play(){

			Sequencer player = null;
			try {
				player = MidiSystem.getSequencer();
			} catch (MidiUnavailableException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				player.open();
			} catch (MidiUnavailableException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Sequence seq = null;
			try {
				seq = new Sequence(Sequence.PPQ, 4);
			} catch (InvalidMidiDataException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Track track = seq.createTrack();
			
			ShortMessage msg1 = new ShortMessage();
			try {
				msg1.setMessage(NOTE_ON, 1, 44, 100);
			} catch (InvalidMidiDataException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			MidiEvent noteOn = new MidiEvent(msg1, 1);
			track.add(noteOn);
			
			ShortMessage msg2 = new ShortMessage();
			try {
				msg2.setMessage(NOTE_OFF, 1, 44, 100);
			} catch (InvalidMidiDataException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			MidiEvent noteOff = new MidiEvent(msg2, 16);
			track.add(noteOff);
			
			try {
				player.setSequence(seq);
			} catch (InvalidMidiDataException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			player.start();
	}




}
