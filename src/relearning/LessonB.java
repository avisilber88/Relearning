/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package relearning;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.*;

import java.awt.image.ImageObserver;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import javax.sound.midi.MidiDevice;
import javax.sound.midi.MidiEvent;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import javax.swing.*;
//import org.jfugue.*;

/**
 *
 * @author Avi
 */
public class LessonB extends JFrame implements KeyListener {

    boolean keyOnePressed;
    boolean keyTwoPressed;
    boolean keyThreePressed;
    boolean keyFourPressed;
    int randomNum;
    List notesIn;
    JTextField blob;
    JTextField playThis;
    JTextField scoreSheet;
    JPanel staffPanel;
    Map chordSet;
    List<Chord> chordList;
    int up = 38;
    int down = 40;
    int left = 37;
    int right = 39;
    int aNote = 9;
    int bNote = 11;
    int cNote = 12;
    int dNote = 2;
    int eNote = 4;
    int fNote = 5;
    int gNote = 7;
    int aFlatNote = 8;
    int bFlatNote = 10;
    int dFlatNote = 1;
    int eFlatNote = 3;
    int gFlatNote = 6;
    int cSharpNote = dFlatNote;
    int dSharpNote = eFlatNote;
    int fSharpNote = gFlatNote;
    int gSharpNote = aFlatNote;
    int aSharpNote = bFlatNote;

    int notea;
    int noteb;
    int notec;
    int noted;
    int gameScore;

    //This begins the section that I am testing around with and am not confident in
    Graphics pic;
//   static MidiParser bam;
//    ParserListener bamB;
//    static Sequence theSequence;
//    static DeviceThatWillTransmitMidi keyBoard;
//This ends the section that I am testing around with and am not confident in

    public LessonB() {

    }

    public static void main(String[] args) throws MidiUnavailableException, InterruptedException {
//DeviceThatWillTransmitMidi keyBoard2 = new DeviceThatWillTransmitMidi();
        LessonB top = new LessonB();

//Sequencer sequencer = bamha.getDeviceInfo();
 try {
     MidiDevice.Info[] infos = MidiSystem.getMidiDeviceInfo();
                MidiDevice device = MidiSystem.getMidiDevice(infos[1]);
                 } catch (MidiUnavailableException e) {
            }
 
 
 
//        keyBoard=keyBoard2;
//        keyBoard.addParserListener(top);
////        keyBoard2.listenForMillis(10000);
//        keyBoard.startListening();
//        Sequence music = keyBoard.getSequenceFromListening();
//        theSequence = music;

        top.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        top.letsMake();
        top.startGame();
    }

    public void startGame() {
        MidiHandler chocolate = new MidiHandler();
chocolate.midMan.setOurLesson(this);
MidiDevice bamha = chocolate.device;
System.out.println(chocolate.device.getDeviceInfo());
//        bam = new MidiParser();
//                bam = new MidiParser();
//bam.parse(theSequence);
//        bam.addParserListener(bamB);

        gameScore = 0;
        chordList = new ArrayList<Chord>(0);

        makeActualChords();
        int testVal = 60;
        System.out.println(testVal + " is really " + arrangeNote((byte) testVal) + " or " + noteNameComplex(testVal));
        pickAChord();

//        chordSet.put(upDown, true);
//        chordSet.put(leftRight, true);
    }

    public void makeChords() {
        Chord upDown = new Chord();
        upDown.addChord("Up Down", up, down);
        Chord leftRight = new Chord();
        leftRight.addChord("Left Right", left, right);
        Chord leftRightUp = new Chord();
        leftRightUp.addChord("Left Right Up", left, right, up);

        chordList.add(chordList.size(), upDown);
        chordList.add(chordList.size(), leftRight);
        chordList.add(chordList.size(), leftRightUp);

    }

    public void makeActualChords() {
        makeMajorChords();
        makeMinorChords();
    }

    public void makeMajorChords() {
        Chord aMaj = new Chord();
        Chord aSharpMaj = new Chord();
        Chord bFlatMaj = new Chord();
        Chord bMaj = new Chord();
        Chord cMaj = new Chord();
        Chord cSharpMaj = new Chord();
        Chord dFlatMaj = new Chord();
        Chord dMaj = new Chord();
        Chord dSharpMaj = new Chord();
        Chord eFlatMaj = new Chord();
        Chord eMaj = new Chord();
        Chord fMaj = new Chord();
        Chord fSharpMaj = new Chord();
        Chord gFlatMaj = new Chord();
        Chord gMaj = new Chord();
        Chord gSharpMaj = new Chord();
        Chord aFlatMaj = new Chord();
        aMaj.addChord("A", aNote, cSharpNote, eNote);
        aSharpMaj.addChord("A#", bFlatNote, dNote, fNote);
        bFlatMaj.addChord("Bb", bFlatNote, dNote, fNote);
        bMaj.addChord("B", bNote, dSharpNote, fSharpNote);
        cMaj.addChord("C", cNote, eNote, gNote);
        cSharpMaj.addChord("C#", cSharpNote, fNote, gSharpNote);
        dFlatMaj.addChord("Db", cSharpNote, fNote, gSharpNote);
        dMaj.addChord("D", dNote, fSharpNote, aNote);
        dSharpMaj.addChord("D#", dSharpNote, gNote, aSharpNote);
        eFlatMaj.addChord("Eb", dSharpNote, gNote, aSharpNote);
        eMaj.addChord("E", eNote, gSharpNote, bNote);
        fMaj.addChord("F", fNote, aNote, cNote);
        fSharpMaj.addChord("F#", fSharpNote, aSharpNote, cSharpNote);
        gFlatMaj.addChord("Gb", fSharpNote, aSharpNote, cSharpNote);
        gMaj.addChord("G", gNote, bNote, dNote);
        gSharpMaj.addChord("G#", gSharpNote, cNote, dSharpNote);
        aFlatMaj.addChord("Ab", gSharpNote, cNote, dSharpNote);
chordList.add (chordList.size(), aMaj);
chordList.add (chordList.size(), aSharpMaj);
chordList.add (chordList.size(), bFlatMaj);
chordList.add (chordList.size(), bMaj);
chordList.add (chordList.size(), cMaj);
chordList.add (chordList.size(), cSharpMaj);
chordList.add (chordList.size(), dFlatMaj);
chordList.add (chordList.size(), dMaj);
chordList.add (chordList.size(), dSharpMaj);
chordList.add (chordList.size(), eFlatMaj);
chordList.add (chordList.size(), eMaj);
chordList.add (chordList.size(), fMaj);
chordList.add (chordList.size(), fSharpMaj);
chordList.add (chordList.size(), gFlatMaj);
chordList.add (chordList.size(), gMaj);
chordList.add (chordList.size(), gSharpMaj);
chordList.add (chordList.size(), aFlatMaj);

    }

    public void makeMinorChords() {
        Chord aMin = new Chord();
        Chord aSharpMin = new Chord();
        Chord bFlatMin = new Chord();
        Chord bMin = new Chord();
        Chord cMin = new Chord();
        Chord cSharpMin = new Chord();
        Chord dFlatMin = new Chord();
        Chord dMin = new Chord();
        Chord dSharpMin = new Chord();
        Chord eFlatMin = new Chord();
        Chord eMin = new Chord();
        Chord fMin = new Chord();
        Chord fSharpMin = new Chord();
        Chord gFlatMin = new Chord();
        Chord gMin = new Chord();
        Chord gSharpMin = new Chord();
        Chord aFlatMin = new Chord();
        aMin.addChord("Am", aNote, cNote, eNote);
        aSharpMin.addChord("A#m", bFlatNote, dFlatNote, fNote);
        bFlatMin.addChord("Bbm", bFlatNote, dFlatNote, fNote);
        bMin.addChord("Bm", bNote, dNote, fSharpNote);
        cMin.addChord("Cm", cNote, eFlatNote, gNote);
        cSharpMin.addChord("C#m", cSharpNote, eNote, gSharpNote);
        dFlatMin.addChord("Dbm", cSharpNote, eNote, gSharpNote);
        dMin.addChord("Dm", dNote, fNote, aNote);
        dSharpMin.addChord("D#m", dSharpNote, gFlatNote, aSharpNote);
        eFlatMin.addChord("Ebm", dSharpNote, gFlatNote, aSharpNote);
        eMin.addChord("Em", eNote, gNote, bNote);
        fMin.addChord("Fm", fNote, aFlatNote, cNote);
        fSharpMin.addChord("F#m", fSharpNote, aNote, cSharpNote);
        gFlatMin.addChord("Gbm", fSharpNote, aNote, cSharpNote);
        gMin.addChord("Gm", gNote, bFlatNote, dNote);
        gSharpMin.addChord("G#m", gSharpNote, bNote, dSharpNote);
        aFlatMin.addChord("Abm", gSharpNote, bNote, dSharpNote);
chordList.add (chordList.size(), aMin);
chordList.add (chordList.size(), aSharpMin);
chordList.add (chordList.size(), bFlatMin);
chordList.add (chordList.size(), bMin);
chordList.add (chordList.size(), cMin);
chordList.add (chordList.size(), cSharpMin);
chordList.add (chordList.size(), dFlatMin);
chordList.add (chordList.size(), dMin);
chordList.add (chordList.size(), dSharpMin);
chordList.add (chordList.size(), eFlatMin);
chordList.add (chordList.size(), eMin);
chordList.add (chordList.size(), fMin);
chordList.add (chordList.size(), fSharpMin);
chordList.add (chordList.size(), gFlatMin);
chordList.add (chordList.size(), gMin);
chordList.add (chordList.size(), gSharpMin);
chordList.add (chordList.size(), aFlatMin);

    }

    public void pickAChord() {
        int maximum = chordList.size();
        int minimum = 0;
        Random rn = new Random();
        randomNum = rn.nextInt((maximum - minimum)) + minimum;
        playThis.setText(chordList.get(randomNum).myName);
        notesIn = chordList.get(randomNum).notes;
        if (notesIn.size() > 0) {
            notea = (int) notesIn.get(0);
            if (notesIn.size() > 1) {
                noteb = (int) notesIn.get(1);
                if (notesIn.size() > 2) {
                    notec = (int) notesIn.get(2);
                    if (notesIn.size() > 3) {
                        noted = (int) notesIn.get(3);

                    }
                }
            }
        }
        keyOnePressed = false;
        keyTwoPressed = false;
        keyThreePressed = false;
        keyFourPressed = false;
    }

    public int arrangeNote(byte a) {
        byte b = a;
//        int timesThrough = 0;
        for (int i = a; i > 0; i = i - 12) {
            b = (byte) i;
//            timesThrough++;
        }
        //9 if equation then a
        //10 if equation then a#
        //11 if equation then b
        //12 if equation then c
        //1 equation then c#
        //2 equation then d
        //3 equation then d#
        //4 equation then e
        //5 equation then f
        //6 equation then f#
        //7 equation then g
        //8 equation then g#
        return b;
    }

    public int getTimesThrough(byte a) {
        byte b = 0;

        for (int i = a; i > 0; i = i - 12) {
            b++;

        }
        //9 if equation then a
        //10 if equation then a#
        //11 if equation then b
        //12 if equation then c
        //1 equation then c#
        //2 equation then d
        //3 equation then d#
        //4 equation then e
        //5 equation then f
        //6 equation then f#
        //7 equation then g
        //8 equation then g#
        return b;
    }

    public String noteName(int a) {

        if (arrangeNote((byte) a) == 1) {
            return "C# or Db";
        } else if (arrangeNote((byte) a) == 2) {
            return "D";
        } else if (arrangeNote((byte) a) == 3) {
            return "D# or Eb";
        } else if (arrangeNote((byte) a) == 4) {
            return "E";
        } else if (arrangeNote((byte) a) == 5) {
            return "F";
        } else if (arrangeNote((byte) a) == 6) {
            return "F# or Gb";
        } else if (arrangeNote((byte) a) == 7) {
            return "G";
        } else if (arrangeNote((byte) a) == 8) {
            return "G# or Ab";
        } else if (arrangeNote((byte) a) == 9) {
            return "A";
        } else if (arrangeNote((byte) a) == 10) {
            return "A# or Bb";
        } else if (arrangeNote((byte) a) == 11) {
            return "B";
        } else if (arrangeNote((byte) a) == 12) {
            return "C";
        } else {
            return "none found";

        }
    }

    public String noteNameComplex(int a) {

        if (arrangeNote((byte) a) == 1) {
            return "C# or Db" + getTimesThrough((byte) a);
        } else if (arrangeNote((byte) a) == 2) {
            return "D" + getTimesThrough((byte) a);
        } else if (arrangeNote((byte) a) == 3) {
            return "D# or Eb" + getTimesThrough((byte) a);
        } else if (arrangeNote((byte) a) == 4) {
            return "E" + getTimesThrough((byte) a);
        } else if (arrangeNote((byte) a) == 5) {
            return "F" + getTimesThrough((byte) a);
        } else if (arrangeNote((byte) a) == 6) {
            return "F# or Gb" + getTimesThrough((byte) a);
        } else if (arrangeNote((byte) a) == 7) {
            return "G" + getTimesThrough((byte) a);
        } else if (arrangeNote((byte) a) == 8) {
            return "G# or Ab" + getTimesThrough((byte) a);
        } else if (arrangeNote((byte) a) == 9) {
            return "A" + getTimesThrough((byte) a);
        } else if (arrangeNote((byte) a) == 10) {
            return "A# or Bb" + getTimesThrough((byte) a);
        } else if (arrangeNote((byte) a) == 11) {
            return "B" + getTimesThrough((byte) a);
        } else if (arrangeNote((byte) a) == 12) {
            return "C" + getTimesThrough((byte) a);
        } else {
            return "none found";

        }
    }

    public void checkList() { // did u play right chord?
        int score = 0;
        if (keyOnePressed == true) {
            score++;
        }
        if (keyTwoPressed == true) {
            score++;
        }
        if (keyThreePressed == true) {
            score++;
        }
        if (keyFourPressed == true) {
            score++;
        }
        if (score == notesIn.size()) {
//            System.out.println("You Win!!");

            gameScore = gameScore + 1;
            scoreSheet.setText("Your score is " + gameScore);
            pickAChord();
        }

        blob.setText(numToString(score));
    }

    public String numToString(int a) {
        String b = new Integer(a).toString();
        return b;
    }

    public void letsMake() { //make the setup and everything
        this.setBounds(0, 0, 700, 400);
        this.setSize(700, 400);
        playThis = new JTextField(20);
        blob = new JTextField(20);
        scoreSheet = new JTextField(20);
        staffPanel = new JPanel();
//       staffPanel.setBounds (200, 50, 0, 50);
        getContentPane().add(playThis, BorderLayout.PAGE_START);
        getContentPane().add(scoreSheet, BorderLayout.PAGE_END);
        getContentPane().add(staffPanel, BorderLayout.CENTER);
        getContentPane().add(blob);
        setVisible(true);
        blob.addKeyListener(this);
//    blob.setText("hi");
        playThis.setEditable(false);
        blob.setEditable(false);
        blob.grabFocus();

        staffPanel.setSize(555, 555);
//        getContentPane().
//        staffPanel.setBackground(Color.red);
        staffPanel.setForeground(Color.BLUE);
//        staffPanel.
//pic = new Graphics();   

        Image thisThing = new ImageIcon("images.jpg").getImage();
        prepareImage(thisThing, staffPanel);
//        staffPanel.
//        staffPanel.prepareImage(100, 100, "images.jpg", new ImageObserver() {

//            @Override
//            public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
//                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//            }
//        });
//        pic.drawLine(50, 60, 40, 100);
//        staffPanel.paint(pic);
//        staffPanel.getGraphics().create(50, 50, 50, 50);
//                staffPanel.paintImmediately(0, 100, 0, 50);
//        staffPanel.add
//        pack();
//         Graphics2D g2d = (Graphics2D)g;
//         g2d.drawImage(playerImage, WIDTH/2,HEIGHT/2 , this);  
        staffPanel.repaint();

//        paintImage();
    }

    @Override

    public void keyTyped(KeyEvent e) {

//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyPressed(KeyEvent e) {
//     chord.put(e.getKeyCode(), true);
        if (e.getKeyCode() == notea) {
//        System.out.println("you pressed up");
            keyOnePressed = true;
//        chord.put(e.getKeyChar(), true);
        } else if (e.getKeyCode() == noteb) {
//        System.out.println("you pressed down");
            keyTwoPressed = true;
        } else if (e.getKeyCode() == notec) {
//        System.out.println("you pressed left");
            keyThreePressed = true;
        } else if (e.getKeyCode() == noted) {
//        System.out.println("you pressed right");
            keyFourPressed = true;
        } else {
        }
//            System.out.println(e.getKeyCode());

//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyReleased(KeyEvent e) {
//        chord.remove(e.getKeyChar());
        
       
        if (keyOnePressed == true && keyTwoPressed == true) {
            //System.out.println("you pressed the chord");
            //blob.setText("up down chord played");
        } else if (keyThreePressed == true && keyFourPressed == true) {
            //blob.setText("left right chord played");
        } else {
        }
        checkList();
        if (e.getKeyCode() == notea) {
            keyOnePressed = false;

        } else if (e.getKeyCode() == noteb) {
            keyTwoPressed = false;
        } else if (e.getKeyCode() == notec) {
            keyThreePressed = false;
        } else if (e.getKeyCode() == noted) {
            keyFourPressed = false;
        } else {
        }//System.out.println("you released another key");
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

//    @Override
//    public void voiceEvent(Voice voice) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//
//    @Override
//    public void tempoEvent(Tempo tempo) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//
//    @Override
//    public void instrumentEvent(Instrument instrument) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//
//    @Override
//    public void layerEvent(Layer layer) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//
//    @Override
//    public void measureEvent(Measure measure) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//
//    @Override
//    public void timeEvent(Time time) {
////        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//
//    @Override
//    public void keySignatureEvent(KeySignature keySig) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//
//    @Override
//    public void controllerEvent(Controller controller) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//
//    @Override
//    public void channelPressureEvent(ChannelPressure channelPressure) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//
//    @Override
//    public void polyphonicPressureEvent(PolyphonicPressure polyphonicPressure) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//
//    @Override
//    public void pitchBendEvent(PitchBend pitchBend) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//
//    @Override
//    public void noteEvent(Note e) {
//             System.out.println("We Did IT!!!");
//       if (e.getValue() == notea) {
////        System.out.println("you pressed up");
//            keyOnePressed = true;
////        chord.put(e.getKeyChar(), true);
//        } else if (arrangeNote(e.getValue()) == noteb) {
////        System.out.println("you pressed down");
//            keyTwoPressed = true;
//        } else if (arrangeNote(e.getValue()) == notec) {
////        System.out.println("you pressed left");
//            keyThreePressed = true;
//        } else if (arrangeNote(e.getValue()) == noted) {
////        System.out.println("you pressed right");
//            keyFourPressed = true;
//        } else {
//        }
//       
////        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//
//    @Override
//    public void sequentialNoteEvent(Note e) {
//         if (e.getValue() == notea) {
////        System.out.println("you pressed up");
//            keyOnePressed = true;
////        chord.put(e.getKeyChar(), true);
//        } else if (arrangeNote(e.getValue()) == noteb) {
////        System.out.println("you pressed down");
//            keyTwoPressed = true;
//        } else if (arrangeNote(e.getValue()) == notec) {
////        System.out.println("you pressed left");
//            keyThreePressed = true;
//        } else if (arrangeNote(e.getValue()) == noted) {
////        System.out.println("you pressed right");
//            keyFourPressed = true;
//        } else {
//        }
//        //supposedly will return _ for others
////        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//
//    @Override
//    public void parallelNoteEvent(Note e) {
//         if (e.getValue() == notea) {
////        System.out.println("you pressed up");
//            keyOnePressed = true;
////        chord.put(e.getKeyChar(), true);
//        } else if (arrangeNote(e.getValue()) == noteb) {
////        System.out.println("you pressed down");
//            keyTwoPressed = true;
//        } else if (arrangeNote(e.getValue()) == notec) {
////        System.out.println("you pressed left");
//            keyThreePressed = true;
//        } else if (arrangeNote(e.getValue()) == noted) {
////        System.out.println("you pressed right");
//            keyFourPressed = true;
//        } else {
//        }
//        e.getMusicString(); //supposedly will return + for each same note
////        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }

}
