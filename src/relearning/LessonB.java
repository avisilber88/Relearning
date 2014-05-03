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
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Rectangle;
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
public class LessonB extends JPanel implements KeyListener, ActionListener {

    boolean keyOnePressed;
    boolean keyTwoPressed;
    boolean keyThreePressed;
    boolean keyFourPressed;
    int randomNum;
    List notesIn;
    JTextArea blob;
    JTextArea playThis;
    JTextArea scoreSheetTextArea;
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
    String currentChordName;
    int notea;
    int noteb;
    int notec;
    int noted;
    int numNotesPressed;
    int wrongNotesCount;
    int gameScore;
    JFrame top;
    Timer timer;
    Timer chordTimer;
    int chordTime;
    int time;
    double timeFast;
    double difficulty;
    double difficultyAddative;
    JTextArea timePassedTextArea;
    JTextArea titleLineTextArea;
    JTextArea wrongNotesCountTextArea;
    int wrongNotesCountThisChord;
    Chord currentChord;
    //This begins the section that I am testing around with and am not confident in
    Graphics pic;
    Container window;
//   static MidiParser bam;
//    ParserListener bamB;
//    static Sequence theSequence;
//    static DeviceThatWillTransmitMidi keyBoard;
//This ends the section that I am testing around with and am not confident in

    public LessonB() {
        difficultyAddative = .02;
        difficulty = 1;
        currentChordName = "";
        wrongNotesCount = 0;
        wrongNotesCountThisChord = 0;
//DeviceThatWillTransmitMidi keyBoard2 = new DeviceThatWillTransmitMidi();
        top = new JFrame();
        top.add(this);
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

        letsMake();
        startGame();
    }

    public static void main(String[] args) {//throws MidiUnavailableException, InterruptedException {
        //try {
        new LessonB();
        //}catch(MidiUnavailableException e){
        //   System.out.println();
        //}
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
        chordList.add(chordList.size(), aMaj);
        chordList.add(chordList.size(), aSharpMaj);
        chordList.add(chordList.size(), bFlatMaj);
        chordList.add(chordList.size(), bMaj);
        chordList.add(chordList.size(), cMaj);
        chordList.add(chordList.size(), cSharpMaj);
        chordList.add(chordList.size(), dFlatMaj);
        chordList.add(chordList.size(), dMaj);
        chordList.add(chordList.size(), dSharpMaj);
        chordList.add(chordList.size(), eFlatMaj);
        chordList.add(chordList.size(), eMaj);
        chordList.add(chordList.size(), fMaj);
        chordList.add(chordList.size(), fSharpMaj);
        chordList.add(chordList.size(), gFlatMaj);
        chordList.add(chordList.size(), gMaj);
        chordList.add(chordList.size(), gSharpMaj);
        chordList.add(chordList.size(), aFlatMaj);

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
        chordList.add(chordList.size(), aMin);
        chordList.add(chordList.size(), aSharpMin);
        chordList.add(chordList.size(), bFlatMin);
        chordList.add(chordList.size(), bMin);
        chordList.add(chordList.size(), cMin);
        chordList.add(chordList.size(), cSharpMin);
        chordList.add(chordList.size(), dFlatMin);
        chordList.add(chordList.size(), dMin);
        chordList.add(chordList.size(), dSharpMin);
        chordList.add(chordList.size(), eFlatMin);
        chordList.add(chordList.size(), eMin);
        chordList.add(chordList.size(), fMin);
        chordList.add(chordList.size(), fSharpMin);
        chordList.add(chordList.size(), gFlatMin);
        chordList.add(chordList.size(), gMin);
        chordList.add(chordList.size(), gSharpMin);
        chordList.add(chordList.size(), aFlatMin);

    }

    public void makeMajorSeventhChords() {

    }

    public void makeMinorSeventhChords() {
        Chord aMin = new Chord();
        Chord aSharpMinSeventh = new Chord();
        Chord bFlatMinSeventh = new Chord();
        Chord bMinSeventh = new Chord();
        Chord cMinSeventh = new Chord();
        Chord cSharpMinSeventh = new Chord();
        Chord dFlatMinSeventh = new Chord();
        Chord dMinSeventh = new Chord();
        Chord dSharpMinSeventh = new Chord();
        Chord eFlatMinSeventh = new Chord();
        Chord eMinSeventh = new Chord();
        Chord fMinSeventh = new Chord();
        Chord fSharpMinSeventh = new Chord();
        Chord gFlatMinSeventh = new Chord();
        Chord gMinSeventh = new Chord();
        Chord gSharpMinSeventh = new Chord();
        Chord aFlatMinSeventh = new Chord();
        aMin.addChord("Am7", aNote, cNote, eNote, eNote + 3);
        aSharpMinSeventh.addChord("A#m7", bFlatNote, dFlatNote, fNote, fNote + 3);
        bFlatMinSeventh.addChord("Bbm7", bFlatNote, dFlatNote, fNote, fNote + 3);
        bMinSeventh.addChord("Bm7", bNote, dNote, fSharpNote, fSharpNote + 3);
        cMinSeventh.addChord("Cm7", cNote, eFlatNote, gNote, gNote + 3);
        cSharpMinSeventh.addChord("C#m7", cSharpNote, eNote, gSharpNote, gSharpNote + 3);
        dFlatMinSeventh.addChord("Dbm7", cSharpNote, eNote, gSharpNote, gSharpNote + 3);
        dMinSeventh.addChord("Dm7", dNote, fNote, aNote, aNote + 3);
        dSharpMinSeventh.addChord("D#m7", dSharpNote, gFlatNote, aSharpNote, aSharpNote + 3);
        eFlatMinSeventh.addChord("Ebm7", dSharpNote, gFlatNote, aSharpNote);
        eMinSeventh.addChord("Em7", eNote, gNote, bNote);
        fMinSeventh.addChord("Fm7", fNote, aFlatNote, cNote);
        fSharpMinSeventh.addChord("F#m7", fSharpNote, aNote, cSharpNote);
        gFlatMinSeventh.addChord("Gbm7", fSharpNote, aNote, cSharpNote);
        gMinSeventh.addChord("Gm7", gNote, bFlatNote, dNote);
        gSharpMinSeventh.addChord("G#m7", gSharpNote, bNote, dSharpNote);
        aFlatMinSeventh.addChord("Abm7", gSharpNote, bNote, dSharpNote);
        chordList.add(chordList.size(), aMin);
        chordList.add(chordList.size(), aSharpMinSeventh);
        chordList.add(chordList.size(), bFlatMinSeventh);
        chordList.add(chordList.size(), bMinSeventh);
        chordList.add(chordList.size(), cMinSeventh);
        chordList.add(chordList.size(), cSharpMinSeventh);
        chordList.add(chordList.size(), dFlatMinSeventh);
        chordList.add(chordList.size(), dMinSeventh);
        chordList.add(chordList.size(), dSharpMinSeventh);
        chordList.add(chordList.size(), eFlatMinSeventh);
        chordList.add(chordList.size(), eMinSeventh);
        chordList.add(chordList.size(), fMinSeventh);
        chordList.add(chordList.size(), fSharpMinSeventh);
        chordList.add(chordList.size(), gFlatMinSeventh);
        chordList.add(chordList.size(), gMinSeventh);
        chordList.add(chordList.size(), gSharpMinSeventh);
        chordList.add(chordList.size(), aFlatMinSeventh);

    }

    public void makeDominantSeventhChords() {

    }

    public void makeMinorNinthChords() {

    }

    public void makeMajorNinthChords() {

    }

    public void makeSuspendedTwoChords() {

    }

    public void makeSuspendedFourChords() {

    }

    public void pickAChord() {
        int maximum = chordList.size();
        int minimum = 0;
        Random rn = new Random();
        randomNum = rn.nextInt((maximum - minimum)) + minimum;
        currentChordName = chordList.get(randomNum).myName;
        currentChord = chordList.get(randomNum);
        playThis.setText(currentChordName);
        chordTime = 0;
        difficulty = difficulty + difficultyAddative;
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
            scoreSheetTextArea.setText("Your score is " + gameScore);
            currentChord.addMistakes(wrongNotesCountThisChord);
            currentChord.raiseChorcCount();
            wrongNotesCountThisChord = 0;
            pickAChord();
        }

        blob.setText(numToString(score));
    }

    public String numToString(int a) {
        String b = new Integer(a).toString();
        return b;
    }

    public void letsMake() { //make the setup and everything

        time = 0;
        timeFast = 10;
        chordTime = 0;
//        timer = new Timer(100, this);
//        timer.start();
        chordTimer = new Timer(10, this);
        chordTimer.start();
        // this.paint(shape);
//		frame = new JFrame();
        top.setSize(500, 500);
        Rectangle shape = new Rectangle(5, 5, 5, 5);
        top.setContentPane(this);
        window = top.getContentPane();

        timePassedTextArea = new JTextArea();
        timePassedTextArea.setRows(1);
        timePassedTextArea.setColumns(1);
        window.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        timePassedTextArea.setRows(1);
        timePassedTextArea.setColumns(1);
        playThis = new JTextArea();
        playThis.setRows(1);
        playThis.setColumns(1);
        blob = new JTextArea();
        blob.setRows(1);
        blob.setColumns(1);
        scoreSheetTextArea = new JTextArea();
        scoreSheetTextArea.setRows(1);
        scoreSheetTextArea.setColumns(1);
        wrongNotesCountTextArea = new JTextArea();
        wrongNotesCountTextArea.setRows(1);
        wrongNotesCountTextArea.setColumns(1);

        titleLineTextArea = new JTextArea();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weighty = 0.5;
        c.gridx = 1;
        c.gridy = 1;
//        c.insets = new Insets(0, 0, 100, 0); // (space out up, space out left,
        // space out down, space out
        // right)
        window.add(timePassedTextArea, c);
//		c.gridx = 2;
//		c.gridy = 0;
//		window.add(new MyPanel(), c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 1;
//        c.insets = new Insets(0, 0, 100, 0); // (space out up, space out left,
        // space out down, space out
        // right)
        window.add(scoreSheetTextArea, c);
        scoreSheetTextArea.setAlignmentX(LEFT_ALIGNMENT);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
//        c.weighty=0.5;
        c.gridx = 0;
        c.gridy = 0;

        window.add(playThis, c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 0;
        window.add(blob, c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 2;
        window.add(wrongNotesCountTextArea, c);
//       c.gridx = 0;
//       c.gridy = 10;
//        window.add(titleLineTextArea, c);
//        c.gridx = 1;
        // timePassedTextArea.setAlignmentY(250); f.add(new MyPanel());
//		this.pack();
        top.setVisible(true);
//        this.setBounds(0, 0, 700, 400);
//        this.setSize(700, 400);

//        staffPanel = new JPanel();
//       staffPanel.setBounds (200, 50, 0, 50);
//        top.getContentPane().add(playThis, BorderLayout.PAGE_START);
//        top.getContentPane().add(scoreSheetTextArea, BorderLayout.PAGE_END);
//        top.getContentPane().add(staffPanel, BorderLayout.CENTER);
//        top.getContentPane().add(blob);
//        setVisible(true);
//        blob.addKeyListener(this);
//    blob.setText("hi");
//        playThis.setEditable(false);
        blob.setEditable(false);
        blob.grabFocus();

//        staffPanel.setSize(555, 555);
//        getContentPane().
//        staffPanel.setBackground(Color.red);
//        staffPanel.setForeground(Color.BLUE);
//        staffPanel.
//pic = new Graphics();   
//        Image thisThing = new ImageIcon("images.jpg").getImage();
//        prepareImage(thisThing, staffPanel);
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
//        this.repaint();
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

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics = (Graphics2D) (g);
//		super.paintComponent(g);
//graphics.
//        System.out.println(chordTime);
        graphics.drawString(currentChordName, (int) (this.getBounds().getMaxX() - chordTime * difficulty), 70);
        // Draw Text
//		g.drawString("This is my custom Panel!", 10, 20);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        timeFast++;
        time = (int) timeFast / 10;
        chordTime++;
        if (canCountSecond()) {
            timePassedTextArea.setText("Time:" + (int) howManySeconds());
            titleLineTextArea.setText("brought to you by Silber Solutions");
//            System.out.println(time / 10);
//			paintComponents(this.getGraphics());

            if (time >= 120) {
                List<Chord> finalList = new ArrayList(chordList.size());
                List<Chord> chordListCopy = new ArrayList(chordList.size());

                Chord a = new Chord();
                int k = 0;
                for (Chord d : chordList) {
                    k++;
                    a = d;
                    System.out.print(d.myName);
                    chordListCopy.add(d);
                    System.out.print(chordListCopy.get(k - 1).myName);

                }
                System.out.println(chordList.size() + "  " + chordListCopy.size());
                while (!chordListCopy.isEmpty()) { //perform operation until all elements are moved to new List

                    System.out.println("yikes");
                    double rank = 0;
                    Chord topNow = new Chord();
                    int i = 0;
                    int j = 0;
                    for (Chord d : chordListCopy) {
                        i++;

                        if (d.calculateMistakesFrequency() >= rank) {
//                        if (d.calculateMistakesFrequency()!=null){
                            rank = d.calculateMistakesFrequency();
//                        }
                            topNow = d;
                            j = i;
                        }

                    }
//            if (topNow!=null){
                    finalList.add(topNow);
//            }
                    System.out.println(i);
                    System.out.println(topNow.myName);
                    if (j > 0) {
                        System.out.println(chordListCopy.get(j - 1).myName);
                        chordListCopy.remove(j - 1);
                    } else {
                        chordListCopy.remove(j);
                    }
//                chordList.remove(topNow);

                }
                for (Chord d : finalList) {
                    if (d.chordCalledCount > 0) {
                        System.out.println(d.myName + "accuracy: " + d.calculateMistakesFrequency());
                    }
                }
            }
        }
        repaint();
        // TODO Auto-generated method stub

    }

    public double howManySeconds() {
        double doubleNumber = (double) time / 10;
        return doubleNumber;

    }

    public boolean canCountSecond() {
        return (howManySeconds() == (double) (time / 10));
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
