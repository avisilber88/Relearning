/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package relearning;

import java.util.List;
import javax.sound.midi.MidiDevice;
import javax.sound.midi.MidiMessage;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Receiver;
import javax.sound.midi.Transmitter;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.ShortMessage;

/**
 *
 * @author DJ 4 Fathers
 */
public class MidiHandler {

    public MidiDevice device;
    public MidiInputReceiver midMan;

    public MidiHandler() {

        MidiDevice.Info[] infos = MidiSystem.getMidiDeviceInfo();
        System.out.print(infos.length);
//        for (int i = 0; i < infos.length; i++) {
        for (int i = 0; i < 2; i++) {
            try {
                device = MidiSystem.getMidiDevice(infos[i]);
                //does the device have any transmitters?
                //if it does, add it to the device list
                System.out.println(infos[i]);

                //get all transmitters
                List<Transmitter> transmitters = device.getTransmitters();
                //and for each transmitter
//transmitters.get(1).setReceiver(new MidiInputReceiver (device.getDeviceInfo().toString()));
                for (int j = 0; j < transmitters.size(); j++) {
                    //create a new receiver
                    midMan = new MidiInputReceiver(device.getDeviceInfo().toString());
                    transmitters.get(j).setReceiver(
                            //using my own MidiInputReceiver
                            midMan);

                }

                Transmitter trans = device.getTransmitter();
                midMan = new MidiInputReceiver(device.getDeviceInfo().toString());
                trans.setReceiver(midMan);
                //open each device
                device.open();
                //if code gets this far without throwing an exception
                //print a success message
                System.out.println(device.getDeviceInfo() + " Was Opened");

            } catch (MidiUnavailableException e) {
            }
        }

    }
//tried to write my own class. I thought the send method handles an MidiEvents sent to it

    public class MidiInputReceiver implements Receiver {

        public String name;
        public LessonB placeHolderLesson;

        public MidiInputReceiver(String name) {
            this.name = name;
        }

        public void setOurLesson(LessonB something) {
            placeHolderLesson = something;
        }

        public void send(MidiMessage msg, long timeStamp) {
//      if (msg.getMessage().length>1) { 
//        System.out.println(this.name);
//        msg.getMessage(
            ShortMessage myMsg = new ShortMessage();
//            myMsg.setMessage (0, 60, 93)
            try {
                myMsg.setMessage(ShortMessage.NOTE_ON, 0, 60, 93);
//            System.out.println(myMsg.getData1());
            } catch (InvalidMidiDataException ex) {
                Logger.getLogger(MidiHandler.class.getName()).log(Level.SEVERE, null, ex);
            }

//            System.out.println(msg.getMessage
//            System.out.println(myMsg);
            myMsg = (ShortMessage) msg;
            if (myMsg.getData1() != 0) {
                int note = placeHolderLesson.arrangeNote((byte)myMsg.getData1());
                if (note == (placeHolderLesson.notea)) {
//        System.out.println("you pressed up");
                    if (myMsg.getData2() != 0) {
                        placeHolderLesson.keyOnePressed = true;
                    } else {
                        placeHolderLesson.keyOnePressed = false;
                    }

//        chord.put(e.getKeyChar(), true);
                } else if (note == (placeHolderLesson.noteb)) {
//        System.out.println("you pressed down");
                    if (myMsg.getData2() != 0) {
                        placeHolderLesson.keyTwoPressed = true;
                    } else {
                        placeHolderLesson.keyTwoPressed = false;
                    }

                } else if (note == (placeHolderLesson.notec)) {
//        System.out.println("you pressed left");
                    if (myMsg.getData2() != 0) {
                        placeHolderLesson.keyThreePressed = true;
                    } else {
                        placeHolderLesson.keyThreePressed = false;
                    }

                } else if (note == (placeHolderLesson.noted)) {
//        System.out.println("you pressed right");
                    if (myMsg.getData2() != 0) {
                        placeHolderLesson.keyFourPressed = true;
                    } else {
                        placeHolderLesson.keyFourPressed = false;
                    }

                } else {
                }
                placeHolderLesson.checkList();
                System.out.println((myMsg.getData2()));
            }

//              }}
//      else {};
        }
//    public void close() {}
//
//        @Override
//        public void send(MidiMessage message, long timeStamp) {
//            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//        }
//    }

        @Override
        public void close() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    }
}
