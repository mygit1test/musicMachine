import java.util.Scanner;

import javax.sound.midi.*;

/*
we'll learn first:
- how to build a swing GUI
- connect to another machine via networking 
- I/O operations to send something to the other machine
- Java sound API


*** EXCEPTIONS *********
1) MidiSystem.getSequencer() throws an Exception that we have to handle. We didn't write
the class MidiSystem.
- such an exception might be due to the fact that the sequencer is already being used

2) What should you write in the catch block? 
- if the server is down we can try to use another server
- if a file is not there, we might ask the user for another path (??)

3) Unchecked exceptions are runtime exceptions and its subclasses
- they are not checked by the compiler
- they are basically flaws in our code that should be corrected instead
treated with a try/catch. Example: an array out of bounds.

4) Declaring a superclass Exception allows us to throw any of its subclasses
- however throwing and catching Exception prevents us from knowing exactly
what we are treating, what the problem was.
- multiple catch blocks should be used for different Exception subclasses,
starting from more specific to less specific.

5) We can throw Exceptions further up if out method is not the right place
to treat it - we just declare it Throws on the signature
- whoever then calls our method will have to hadle that exception


*/  

public class MiniMusicCmdLine {
    
    public void play(int instrument, int note){

        try {
            Sequencer player = MidiSystem.getSequencer();
            player.open();

            /*
             * A Track holds all the MidiEvent objects. 
             * The Sequence organizes them according to when each event is supposed to happen, 
             * and then the Sequencer plays them back in that order.
             */

            Sequence seq = new Sequence(Sequence.PPQ, 4);
            Track track = seq.createTrack();

            //MidiEvent event = null;
            ShortMessage first = new ShortMessage();
            first.setMessage(192, 1, instrument, 0);
            MidiEvent changeInstrument = new MidiEvent(first, 1); 
            track.add(changeInstrument);
            

            // put some MidiEvents into the track
            // pay attention to setMessage & MidiEvent arguments
            ShortMessage a = new ShortMessage();

            /*
             * command: type of message - 144 means noteOn
             * channel: piano, drum
             * data1: the note: middle-c, d-sharp - number between 0 and 127
             * data2: velocity in which the note is played
             */ 
            a.setMessage(144, 1, note, 100);
            MidiEvent noteOn = new MidiEvent(a, 1);
            track.add(noteOn);

            // the ShortMessage says what to do and the MidiEvent when to do it
            ShortMessage b = new ShortMessage();
            b.setMessage(128, 1, note, 100);
            /*
             * the midi event is a combination of the message plus 
             * the moment in time when this msg should fire (beat or tick 16)
             */ 
            MidiEvent noteOff = new MidiEvent(b, 16);
            track.add(noteOff);
            
            player.setSequence(seq);

            player.start();

        } catch (MidiUnavailableException e) {
            // Exception extends Throwable and inherits the following 2 key methods:

            e.printStackTrace();
            e.getMessage();
        } catch (InvalidMidiDataException e) {
            // Exception extends Throwable and inherits the following 2 key methods:

            e.printStackTrace();
            e.getMessage();
        } finally {
            System.out.println("This always runs");
        }
    }
    
    
    public static void main(String[] args) throws Exception {
       MiniMusicCmdLine mini = new MiniMusicCmdLine();
       
       String[] myArgs = args;
       
       if (args.length < 2) {
        System.out.println("Don’t forget the instrument and note args");
        } else {
        int instrument = Integer.parseInt(args[0]);
        int note = Integer.parseInt(args[1]);
        mini.play(instrument, note);
        }

    }
}
