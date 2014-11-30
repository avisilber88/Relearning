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
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.*;

import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.sound.midi.MidiDevice;
import javax.sound.midi.MidiEvent;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import javax.swing.*;

/**
 *
 * @author Avi
 */
public class LessonB extends JPanel implements MouseListener, ItemListener, ImageObserver, KeyListener, ActionListener {

    JButton okay;
    int bassNumberExpected;
    double bestDifficulty;
    int currentPlayedCorrect;
    int hi;
    int test2;
    int test3;
    double allTimeBestDifficulty;
    JScrollPane frequencyScroller;
    JTextArea scoreFrequencyText;
    boolean chordMode;
    boolean keyOnePressed;
    boolean keyTwoPressed;
    boolean keyThreePressed;
    boolean keyFourPressed;
    boolean bassPressed;
    boolean secondBassPressed;
    int randomNum;
    List notesIn;
    JTextArea blob;
    JTextArea playThis;
    JTextArea scoreSheetTextArea;
    JPanel staffPanel;
    Map chordSet;
    List<Chord> chordList;
    BufferedImage img;
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
    int realThisNoteA, realThisNoteB, realThisNoteC, realThisNoteD;
    int realLastNoteA, realLastNoteB, realLastNoteC, realLastNoteD;
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
    double maxDifficulty;
    JTextArea timePassedTextArea;
    JTextArea titleLineTextArea;
    JTextArea wrongNotesCountTextArea;
    JTextArea difficultyChoice;
    int wrongNotesCountThisChord;
    Chord currentChord;
    //This begins the section that I am testing around with and am not confident in
    Graphics pic;
    Container window;
    Container bob;
    JMenuBar menuBar;
    JMenu menu, submenu, bassMenu, inversionMenu;
    
    JMenuItem menuIteem;
    JRadioButton noBass, oneBass, twoBass;
    JRadioButton inversionModeOff, inversionModeOn;
    JCheckBoxMenuItem justNotes, major, minor, sus, sus2, majorSeventh, minorSeventh, dominantSeventh;
    Formatter highScoreFormatter;
    File highScoreFile;
    Scanner highScoreScanner;
//   static MidiParser bam;
//    ParserListener bamB;
//    static Sequence theSequence;
//    static DeviceThatWillTransmitMidi keyBoard;
//This ends the section that I am testing around with and am not confident in

    public LessonB() {
        difficultyAddative = .05;
        difficulty = 2;
        currentChordName = "";
        wrongNotesCount = 0;
        wrongNotesCountThisChord = 0;
        realLastNoteA=0;
        realLastNoteB=0;
        realLastNoteC=0;
        realLastNoteD=0;
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

        letsMakeNew();
        startGame();
    }

    public static void main(String[] args) {//throws MidiUnavailableException, InterruptedException {
        //try {
        new LessonB();
        //}catch(MidiUnavailableException e){
        //   System.out.println();
        //}
    }

    public boolean inversionCheck(int n){
        int i = 3;
       if (realLastNoteA==0){
           return true;
       }
       else if (Math.abs(realLastNoteA-n)<=i){
            return true;
        }
        else if (Math.abs(realLastNoteB-n)<=i){
            return true;
        }
        else if (Math.abs(realLastNoteC-n)<=i){
            return true;
        }
        else if (Math.abs(realLastNoteD-n)<=i){
            return true;
        }
        else
        return false;
    }
    
    public void startGame() {
//        try
        try {
            MidiHandler chocolate = new MidiHandler();
            chocolate.midMan.setOurLesson(this);
            MidiDevice bamha = chocolate.device;
        } catch (Exception e) {

        }
//        System.out.println(chocolate.device.getDeviceInfo());
//        }
//        catch ( e){

//        }
//        bam = new MidiParser();
//                bam = new MidiParser();
//bam.parse(theSequence);
//        bam.addParserListener(bamB);
//scroller.setB
        gameScore = 0;
        bestDifficulty = 1;

        startGame2();
//        chordSet.put(upDown, true);
//        chordSet.put(leftRight, true);
    }

    public void startGame2() {
        chordList = new ArrayList<Chord>(0);
        if (noBass.isSelected()) {
            bassNumberExpected = 0;
        }
        if (oneBass.isSelected()) {
            bassNumberExpected = 1;
        }
        if (twoBass.isSelected()) {
            bassNumberExpected = 2;
        }
        makeActualChords();
        int testVal = 10;
//        System.out.println(testVal + " is really " + arrangeNote((byte) testVal) + " or " + noteNameComplex(testVal));
        pickAChord();
    }

    public void makeNotes() {
        Chord aNotes = new Chord();
        Chord bNotes = new Chord();
        Chord cNotes = new Chord();
        Chord dNotes = new Chord();
        Chord eNotes = new Chord();
        Chord fNotes = new Chord();
        Chord gNotes = new Chord();
        Chord aFlatNotes = new Chord();
        Chord aSharpNotes = new Chord();
        Chord bFlatNotes = new Chord();
        Chord cSharpNotes = new Chord();
        Chord dFlatNotes = new Chord();
        Chord dSharpNotes = new Chord();
        Chord eFlatNotes = new Chord();
        Chord fSharpNotes = new Chord();
        Chord gFlatNotes = new Chord();
        Chord gSharpNotes = new Chord();
        aNotes.addChord("A Note", aNote);
        bNotes.addChord("B Note", bNote);
        cNotes.addChord("C Note", cNote);
        dNotes.addChord("D Note", dNote);
        eNotes.addChord("E Note", eNote);
        fNotes.addChord("F Note", fNote);
        gNotes.addChord("G Note", gNote);
        aSharpNotes.addChord("A# Note", aSharpNote);
        aFlatNotes.addChord("Ab Note", aFlatNote);
        bFlatNotes.addChord("Bb Note", bFlatNote);
        cSharpNotes.addChord("C# Note", cSharpNote);
        dFlatNotes.addChord("Db Note", dFlatNote);
        dSharpNotes.addChord("D# Note", dSharpNote);
        eFlatNotes.addChord("Eb Note", eFlatNote);
        fSharpNotes.addChord("F# Note", fSharpNote);
        gFlatNotes.addChord("Gb Note", gFlatNote);
        gSharpNotes.addChord("G# Note", gSharpNote);

        chordList.add(chordList.size(), aNotes);
        chordList.add(chordList.size(), aSharpNotes);
        chordList.add(chordList.size(), bFlatNotes);
        chordList.add(chordList.size(), bNotes);
        chordList.add(chordList.size(), cNotes);
        chordList.add(chordList.size(), cSharpNotes);
        chordList.add(chordList.size(), dFlatNotes);
        chordList.add(chordList.size(), dNotes);
        chordList.add(chordList.size(), dSharpNotes);
        chordList.add(chordList.size(), eFlatNotes);
        chordList.add(chordList.size(), eNotes);
        chordList.add(chordList.size(), fNotes);
        chordList.add(chordList.size(), fSharpNotes);
        chordList.add(chordList.size(), gFlatNotes);
        chordList.add(chordList.size(), gNotes);
        chordList.add(chordList.size(), gSharpNotes);
        chordList.add(chordList.size(), aFlatNotes);

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
        if (justNotes.isSelected()) {
            makeNotes();
        }
        if (major.isSelected()) {
            makeMajorChords();
        }
        if (minor.isSelected()) {
            makeMinorChords();
        }
        if (sus.isSelected()) {
            makeSuspendedFourChords();
        }
        if (sus2.isSelected()) {
            makeSuspendedTwoChords();
        }
        if (minorSeventh.isSelected()) {
            makeMinorSeventhChords();
        }
        if (majorSeventh.isSelected()) {
            makeMajorSeventhChords();
        }
        if (dominantSeventh.isSelected()) {
            makeDominantSeventhChords();
        }

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

    public void makeDominantSeventhChords() {
        Chord aDom = new Chord();
        Chord aSharpDomSeventh = new Chord();
        Chord bFlatDomSeventh = new Chord();
        Chord bDomSeventh = new Chord();
        Chord cDomSeventh = new Chord();
        Chord cSharpDomSeventh = new Chord();
        Chord dFlatDomSeventh = new Chord();
        Chord dDomSeventh = new Chord();
        Chord dSharpDomSeventh = new Chord();
        Chord eFlatDomSeventh = new Chord();
        Chord eDomSeventh = new Chord();
        Chord fDomSeventh = new Chord();
        Chord fSharpDomSeventh = new Chord();
        Chord gFlatDomSeventh = new Chord();
        Chord gDomSeventh = new Chord();
        Chord gSharpDomSeventh = new Chord();
        Chord aFlatDomSeventh = new Chord();
        int baseNote;
        baseNote = aNote;
        aDom.addChord("A7", addHalfSteps(baseNote, 0), addHalfSteps(baseNote, 4), addHalfSteps(baseNote, 7), addHalfSteps(baseNote, 10));
        baseNote = bFlatNote;
        aSharpDomSeventh.addChord("A#7", addHalfSteps(baseNote, 0), addHalfSteps(baseNote, 4), addHalfSteps(baseNote, 7), addHalfSteps(baseNote, 10));
        baseNote = bFlatNote;
        bFlatDomSeventh.addChord("Bb7", addHalfSteps(baseNote, 0), addHalfSteps(baseNote, 4), addHalfSteps(baseNote, 7), addHalfSteps(baseNote, 10));
        baseNote = bNote;
        bDomSeventh.addChord("B7", addHalfSteps(baseNote, 0), addHalfSteps(baseNote, 4), addHalfSteps(baseNote, 7), addHalfSteps(baseNote, 10));
        baseNote = cNote;
        cDomSeventh.addChord("C7", addHalfSteps(baseNote, 0), addHalfSteps(baseNote, 4), addHalfSteps(baseNote, 7), addHalfSteps(baseNote, 10));
        baseNote = cSharpNote;
        cSharpDomSeventh.addChord("C#7", addHalfSteps(baseNote, 0), addHalfSteps(baseNote, 4), addHalfSteps(baseNote, 7), addHalfSteps(baseNote, 10));
        baseNote = cSharpNote;
        dFlatDomSeventh.addChord("Db7", addHalfSteps(baseNote, 0), addHalfSteps(baseNote, 4), addHalfSteps(baseNote, 7), addHalfSteps(baseNote, 10));
        baseNote = dNote;
        dDomSeventh.addChord("D7", addHalfSteps(baseNote, 0), addHalfSteps(baseNote, 4), addHalfSteps(baseNote, 7), addHalfSteps(baseNote, 10));
        baseNote = dSharpNote;
        dSharpDomSeventh.addChord("D#7", addHalfSteps(baseNote, 0), addHalfSteps(baseNote, 4), addHalfSteps(baseNote, 7), addHalfSteps(baseNote, 10));
        baseNote = dSharpNote;
        eFlatDomSeventh.addChord("Eb7", addHalfSteps(baseNote, 0), addHalfSteps(baseNote, 4), addHalfSteps(baseNote, 7), addHalfSteps(baseNote, 10));
        baseNote = eNote;
        eDomSeventh.addChord("E7", addHalfSteps(baseNote, 0), addHalfSteps(baseNote, 4), addHalfSteps(baseNote, 7), addHalfSteps(baseNote, 10));
        baseNote = fNote;
        fDomSeventh.addChord("F7", addHalfSteps(baseNote, 0), addHalfSteps(baseNote, 4), addHalfSteps(baseNote, 7), addHalfSteps(baseNote, 10));
        baseNote = fSharpNote;
        fSharpDomSeventh.addChord("F#7", addHalfSteps(baseNote, 0), addHalfSteps(baseNote, 4), addHalfSteps(baseNote, 7), addHalfSteps(baseNote, 10));
        baseNote = fSharpNote;
        gFlatDomSeventh.addChord("Gb7", addHalfSteps(baseNote, 0), addHalfSteps(baseNote, 4), addHalfSteps(baseNote, 7), addHalfSteps(baseNote, 10));
        baseNote = gNote;
        gDomSeventh.addChord("G7", addHalfSteps(baseNote, 0), addHalfSteps(baseNote, 4), addHalfSteps(baseNote, 7), addHalfSteps(baseNote, 10));
        baseNote = gSharpNote;
        gSharpDomSeventh.addChord("G#7", addHalfSteps(baseNote, 0), addHalfSteps(baseNote, 4), addHalfSteps(baseNote, 7), addHalfSteps(baseNote, 10));
        baseNote = gSharpNote;
        aFlatDomSeventh.addChord("Ab7", addHalfSteps(baseNote, 0), addHalfSteps(baseNote, 4), addHalfSteps(baseNote, 7), addHalfSteps(baseNote, 10));
        chordList.add(chordList.size(), aDom);
        chordList.add(chordList.size(), aSharpDomSeventh);
        chordList.add(chordList.size(), bFlatDomSeventh);
        chordList.add(chordList.size(), bDomSeventh);
        chordList.add(chordList.size(), cDomSeventh);
        chordList.add(chordList.size(), cSharpDomSeventh);
        chordList.add(chordList.size(), dFlatDomSeventh);
        chordList.add(chordList.size(), dDomSeventh);
        chordList.add(chordList.size(), dSharpDomSeventh);
        chordList.add(chordList.size(), eFlatDomSeventh);
        chordList.add(chordList.size(), eDomSeventh);
        chordList.add(chordList.size(), fDomSeventh);
        chordList.add(chordList.size(), fSharpDomSeventh);
        chordList.add(chordList.size(), gFlatDomSeventh);
        chordList.add(chordList.size(), gDomSeventh);
        chordList.add(chordList.size(), gSharpDomSeventh);
        chordList.add(chordList.size(), aFlatDomSeventh);
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
        int baseNote;
        baseNote = aNote;
        aMin.addChord("Am7", addHalfSteps(baseNote, 0), addHalfSteps(baseNote, 3), addHalfSteps(baseNote, 7), addHalfSteps(baseNote, 10));
        baseNote = bFlatNote;
        aSharpMinSeventh.addChord("A#m7", addHalfSteps(baseNote, 0), addHalfSteps(baseNote, 3), addHalfSteps(baseNote, 7), addHalfSteps(baseNote, 10));
        baseNote = bFlatNote;
        bFlatMinSeventh.addChord("Bbm7", addHalfSteps(baseNote, 0), addHalfSteps(baseNote, 3), addHalfSteps(baseNote, 7), addHalfSteps(baseNote, 10));
        baseNote = bNote;
        bMinSeventh.addChord("Bm7", addHalfSteps(baseNote, 0), addHalfSteps(baseNote, 3), addHalfSteps(baseNote, 7), addHalfSteps(baseNote, 10));
        baseNote = cNote;
        cMinSeventh.addChord("Cm7", addHalfSteps(baseNote, 0), addHalfSteps(baseNote, 3), addHalfSteps(baseNote, 7), addHalfSteps(baseNote, 10));
        baseNote = cSharpNote;
        cSharpMinSeventh.addChord("C#m7", addHalfSteps(baseNote, 0), addHalfSteps(baseNote, 3), addHalfSteps(baseNote, 7), addHalfSteps(baseNote, 10));
        baseNote = cSharpNote;
        dFlatMinSeventh.addChord("Dbm7", addHalfSteps(baseNote, 0), addHalfSteps(baseNote, 3), addHalfSteps(baseNote, 7), addHalfSteps(baseNote, 10));
        baseNote = dNote;
        dMinSeventh.addChord("Dm7", addHalfSteps(baseNote, 0), addHalfSteps(baseNote, 3), addHalfSteps(baseNote, 7), addHalfSteps(baseNote, 10));
        baseNote = dSharpNote;
        dSharpMinSeventh.addChord("D#m7", addHalfSteps(baseNote, 0), addHalfSteps(baseNote, 3), addHalfSteps(baseNote, 7), addHalfSteps(baseNote, 10));
        baseNote = dSharpNote;
        eFlatMinSeventh.addChord("Ebm7", addHalfSteps(baseNote, 0), addHalfSteps(baseNote, 3), addHalfSteps(baseNote, 7), addHalfSteps(baseNote, 10));
        baseNote = eNote;
        eMinSeventh.addChord("Em7", addHalfSteps(baseNote, 0), addHalfSteps(baseNote, 3), addHalfSteps(baseNote, 7), addHalfSteps(baseNote, 10));
        baseNote = fNote;
        fMinSeventh.addChord("Fm7", addHalfSteps(baseNote, 0), addHalfSteps(baseNote, 3), addHalfSteps(baseNote, 7), addHalfSteps(baseNote, 10));
        baseNote = fSharpNote;
        fSharpMinSeventh.addChord("F#m7", addHalfSteps(baseNote, 0), addHalfSteps(baseNote, 3), addHalfSteps(baseNote, 7), addHalfSteps(baseNote, 10));
        baseNote = fSharpNote;
        gFlatMinSeventh.addChord("Gbm7", addHalfSteps(baseNote, 0), addHalfSteps(baseNote, 3), addHalfSteps(baseNote, 7), addHalfSteps(baseNote, 10));
        baseNote = gNote;
        gMinSeventh.addChord("Gm7", addHalfSteps(baseNote, 0), addHalfSteps(baseNote, 3), addHalfSteps(baseNote, 7), addHalfSteps(baseNote, 10));
        baseNote = gSharpNote;
        gSharpMinSeventh.addChord("G#m7", addHalfSteps(baseNote, 0), addHalfSteps(baseNote, 3), addHalfSteps(baseNote, 7), addHalfSteps(baseNote, 10));
        baseNote = gSharpNote;
        aFlatMinSeventh.addChord("Abm7", addHalfSteps(baseNote, 0), addHalfSteps(baseNote, 3), addHalfSteps(baseNote, 7), addHalfSteps(baseNote, 10));
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

    public void makeMajorSeventhChords() {
        Chord aMaj = new Chord();
        Chord aSharpMajSeventh = new Chord();
        Chord bFlatMajSeventh = new Chord();
        Chord bMajSeventh = new Chord();
        Chord cMajSeventh = new Chord();
        Chord cSharpMajSeventh = new Chord();
        Chord dFlatMajSeventh = new Chord();
        Chord dMajSeventh = new Chord();
        Chord dSharpMajSeventh = new Chord();
        Chord eFlatMajSeventh = new Chord();
        Chord eMajSeventh = new Chord();
        Chord fMajSeventh = new Chord();
        Chord fSharpMajSeventh = new Chord();
        Chord gFlatMajSeventh = new Chord();
        Chord gMajSeventh = new Chord();
        Chord gSharpMajSeventh = new Chord();
        Chord aFlatMajSeventh = new Chord();
        int baseNote;
        baseNote = aNote;
        aMaj.addChord("Amaj7", addHalfSteps(baseNote, 0), addHalfSteps(baseNote, 4), addHalfSteps(baseNote, 7), addHalfSteps(baseNote, 11));
        baseNote = bFlatNote;
        aSharpMajSeventh.addChord("A#maj7", addHalfSteps(baseNote, 0), addHalfSteps(baseNote, 4), addHalfSteps(baseNote, 7), addHalfSteps(baseNote, 11));
        baseNote = bFlatNote;
        bFlatMajSeventh.addChord("Bbmaj7", addHalfSteps(baseNote, 0), addHalfSteps(baseNote, 4), addHalfSteps(baseNote, 7), addHalfSteps(baseNote, 11));
        baseNote = bNote;
        bMajSeventh.addChord("Bmaj7", addHalfSteps(baseNote, 0), addHalfSteps(baseNote, 4), addHalfSteps(baseNote, 7), addHalfSteps(baseNote, 11));
        baseNote = cNote;
        cMajSeventh.addChord("Cmaj7", addHalfSteps(baseNote, 0), addHalfSteps(baseNote, 4), addHalfSteps(baseNote, 7), addHalfSteps(baseNote, 11));
        baseNote = cSharpNote;
        cSharpMajSeventh.addChord("C#maj7", addHalfSteps(baseNote, 0), addHalfSteps(baseNote, 4), addHalfSteps(baseNote, 7), addHalfSteps(baseNote, 11));
        baseNote = cSharpNote;
        dFlatMajSeventh.addChord("Dbmaj7", addHalfSteps(baseNote, 0), addHalfSteps(baseNote, 4), addHalfSteps(baseNote, 7), addHalfSteps(baseNote, 11));
        baseNote = dNote;
        dMajSeventh.addChord("Dmaj7", addHalfSteps(baseNote, 0), addHalfSteps(baseNote, 4), addHalfSteps(baseNote, 7), addHalfSteps(baseNote, 11));
        baseNote = dSharpNote;
        dSharpMajSeventh.addChord("D#maj7", addHalfSteps(baseNote, 0), addHalfSteps(baseNote, 4), addHalfSteps(baseNote, 7), addHalfSteps(baseNote, 11));
        baseNote = dSharpNote;
        eFlatMajSeventh.addChord("Ebmaj7", addHalfSteps(baseNote, 0), addHalfSteps(baseNote, 4), addHalfSteps(baseNote, 7), addHalfSteps(baseNote, 11));
        baseNote = eNote;
        eMajSeventh.addChord("Emaj7", addHalfSteps(baseNote, 0), addHalfSteps(baseNote, 4), addHalfSteps(baseNote, 7), addHalfSteps(baseNote, 11));
        baseNote = fNote;
        fMajSeventh.addChord("Fmaj7", addHalfSteps(baseNote, 0), addHalfSteps(baseNote, 4), addHalfSteps(baseNote, 7), addHalfSteps(baseNote, 11));
        baseNote = fSharpNote;
        fSharpMajSeventh.addChord("F#maj7", addHalfSteps(baseNote, 0), addHalfSteps(baseNote, 4), addHalfSteps(baseNote, 7), addHalfSteps(baseNote, 11));
        baseNote = fSharpNote;
        gFlatMajSeventh.addChord("Gbmaj7", addHalfSteps(baseNote, 0), addHalfSteps(baseNote, 4), addHalfSteps(baseNote, 7), addHalfSteps(baseNote, 11));
        baseNote = gNote;
        gMajSeventh.addChord("Gmaj7", addHalfSteps(baseNote, 0), addHalfSteps(baseNote, 4), addHalfSteps(baseNote, 7), addHalfSteps(baseNote, 11));
        baseNote = gSharpNote;
        gSharpMajSeventh.addChord("G#maj7", addHalfSteps(baseNote, 0), addHalfSteps(baseNote, 4), addHalfSteps(baseNote, 7), addHalfSteps(baseNote, 11));
        baseNote = gSharpNote;
        aFlatMajSeventh.addChord("Abmaj7", addHalfSteps(baseNote, 0), addHalfSteps(baseNote, 4), addHalfSteps(baseNote, 7), addHalfSteps(baseNote, 11));
        chordList.add(chordList.size(), aMaj);
        chordList.add(chordList.size(), aSharpMajSeventh);
        chordList.add(chordList.size(), bFlatMajSeventh);
        chordList.add(chordList.size(), bMajSeventh);
        chordList.add(chordList.size(), cMajSeventh);
        chordList.add(chordList.size(), cSharpMajSeventh);
        chordList.add(chordList.size(), dFlatMajSeventh);
        chordList.add(chordList.size(), dMajSeventh);
        chordList.add(chordList.size(), dSharpMajSeventh);
        chordList.add(chordList.size(), eFlatMajSeventh);
        chordList.add(chordList.size(), eMajSeventh);
        chordList.add(chordList.size(), fMajSeventh);
        chordList.add(chordList.size(), fSharpMajSeventh);
        chordList.add(chordList.size(), gFlatMajSeventh);
        chordList.add(chordList.size(), gMajSeventh);
        chordList.add(chordList.size(), gSharpMajSeventh);
        chordList.add(chordList.size(), aFlatMajSeventh);
    }

    public void makeMinorNinthChords() {

    }

    public void makeMajorNinthChords() {

    }

    public void makeSuspendedFourChords() {
        Chord aSusFour = new Chord();
        Chord aSharpSusFour = new Chord();
        Chord bFlatSusFour = new Chord();
        Chord bSusFour = new Chord();
        Chord cSusFour = new Chord();
        Chord cSharpSusFour = new Chord();
        Chord dFlatSusFour = new Chord();
        Chord dSusFour = new Chord();
        Chord dSharpSusFour = new Chord();
        Chord eFlatSusFour = new Chord();
        Chord eSusFour = new Chord();
        Chord fSusFour = new Chord();
        Chord fSharpSusFour = new Chord();
        Chord gFlatSusFour = new Chord();
        Chord gSusFour = new Chord();
        Chord gSharpSusFour = new Chord();
        Chord aFlatSusFour = new Chord();
        int baseNote;
        baseNote = aNote;
        aSusFour.addChord("Asus", addHalfSteps(baseNote, 0), addHalfSteps(baseNote, 5), addHalfSteps(baseNote, 7));
        baseNote = bFlatNote;
        aSharpSusFour.addChord("A#sus", addHalfSteps(baseNote, 0), addHalfSteps(baseNote, 5), addHalfSteps(baseNote, 7));
        baseNote = bFlatNote;
        bFlatSusFour.addChord("Bbsus", addHalfSteps(baseNote, 0), addHalfSteps(baseNote, 5), addHalfSteps(baseNote, 7));
        baseNote = bNote;
        bSusFour.addChord("Bsus", addHalfSteps(baseNote, 0), addHalfSteps(baseNote, 5), addHalfSteps(baseNote, 7));
        baseNote = cNote;
        cSusFour.addChord("Csus", addHalfSteps(baseNote, 0), addHalfSteps(baseNote, 5), addHalfSteps(baseNote, 7));
        baseNote = cSharpNote;
        cSharpSusFour.addChord("C#sus", addHalfSteps(baseNote, 0), addHalfSteps(baseNote, 5), addHalfSteps(baseNote, 7));
        baseNote = cSharpNote;
        dFlatSusFour.addChord("Dbsus", addHalfSteps(baseNote, 0), addHalfSteps(baseNote, 5), addHalfSteps(baseNote, 7));
        baseNote = dNote;
        dSusFour.addChord("Dsus", addHalfSteps(baseNote, 0), addHalfSteps(baseNote, 5), addHalfSteps(baseNote, 7));
        baseNote = dSharpNote;
        dSharpSusFour.addChord("D#sus", addHalfSteps(baseNote, 0), addHalfSteps(baseNote, 5), addHalfSteps(baseNote, 7));
        baseNote = dSharpNote;
        eFlatSusFour.addChord("Ebsus", addHalfSteps(baseNote, 0), addHalfSteps(baseNote, 5), addHalfSteps(baseNote, 7));
        baseNote = eNote;
        eSusFour.addChord("Esus", addHalfSteps(baseNote, 0), addHalfSteps(baseNote, 5), addHalfSteps(baseNote, 7));
        baseNote = fNote;
        fSusFour.addChord("Fsus", addHalfSteps(baseNote, 0), addHalfSteps(baseNote, 5), addHalfSteps(baseNote, 7));
        baseNote = fSharpNote;
        fSharpSusFour.addChord("F#sus", addHalfSteps(baseNote, 0), addHalfSteps(baseNote, 5), addHalfSteps(baseNote, 7));
        baseNote = fSharpNote;
        gFlatSusFour.addChord("Gbsus", addHalfSteps(baseNote, 0), addHalfSteps(baseNote, 5), addHalfSteps(baseNote, 7));
        baseNote = gNote;
        gSusFour.addChord("Gsus", addHalfSteps(baseNote, 0), addHalfSteps(baseNote, 5), addHalfSteps(baseNote, 7));
        baseNote = gSharpNote;
        gSharpSusFour.addChord("G#sus", addHalfSteps(baseNote, 0), addHalfSteps(baseNote, 5), addHalfSteps(baseNote, 7));
        baseNote = gSharpNote;
        aFlatSusFour.addChord("Absus", addHalfSteps(baseNote, 0), addHalfSteps(baseNote, 5), addHalfSteps(baseNote, 7));
        chordList.add(chordList.size(), aSusFour);
        chordList.add(chordList.size(), aSharpSusFour);
        chordList.add(chordList.size(), bFlatSusFour);
        chordList.add(chordList.size(), bSusFour);
        chordList.add(chordList.size(), cSusFour);
        chordList.add(chordList.size(), cSharpSusFour);
        chordList.add(chordList.size(), dFlatSusFour);
        chordList.add(chordList.size(), dSusFour);
        chordList.add(chordList.size(), dSharpSusFour);
        chordList.add(chordList.size(), eFlatSusFour);
        chordList.add(chordList.size(), eSusFour);
        chordList.add(chordList.size(), fSusFour);
        chordList.add(chordList.size(), fSharpSusFour);
        chordList.add(chordList.size(), gFlatSusFour);
        chordList.add(chordList.size(), gSusFour);
        chordList.add(chordList.size(), gSharpSusFour);
        chordList.add(chordList.size(), aFlatSusFour);
    }

    public void makeSuspendedTwoChords() {
        Chord aSusTwo = new Chord();
        Chord aSharpSusTwo = new Chord();
        Chord bFlatSusTwo = new Chord();
        Chord bSusTwo = new Chord();
        Chord cSusTwo = new Chord();
        Chord cSharpSusTwo = new Chord();
        Chord dFlatSusTwo = new Chord();
        Chord dSusTwo = new Chord();
        Chord dSharpSusTwo = new Chord();
        Chord eFlatSusTwo = new Chord();
        Chord eSusTwo = new Chord();
        Chord fSusTwo = new Chord();
        Chord fSharpSusTwo = new Chord();
        Chord gFlatSusTwo = new Chord();
        Chord gSusTwo = new Chord();
        Chord gSharpSusTwo = new Chord();
        Chord aFlatSusTwo = new Chord();
        int baseNote;
        baseNote = aNote;
        aSusTwo.addChord("Asus2", addHalfSteps(baseNote, 0), addHalfSteps(baseNote, 2), addHalfSteps(baseNote, 7));
        baseNote = bFlatNote;
        aSharpSusTwo.addChord("A#sus2", addHalfSteps(baseNote, 0), addHalfSteps(baseNote, 2), addHalfSteps(baseNote, 7));
        baseNote = bFlatNote;
        bFlatSusTwo.addChord("Bbsus2", addHalfSteps(baseNote, 0), addHalfSteps(baseNote, 2), addHalfSteps(baseNote, 7));
        baseNote = bNote;
        bSusTwo.addChord("Bsus2", addHalfSteps(baseNote, 0), addHalfSteps(baseNote, 2), addHalfSteps(baseNote, 7));
        baseNote = cNote;
        cSusTwo.addChord("Csus2", addHalfSteps(baseNote, 0), addHalfSteps(baseNote, 2), addHalfSteps(baseNote, 7));
        baseNote = cSharpNote;
        cSharpSusTwo.addChord("C#sus2", addHalfSteps(baseNote, 0), addHalfSteps(baseNote, 2), addHalfSteps(baseNote, 7));
        baseNote = cSharpNote;
        dFlatSusTwo.addChord("Dbsus2", addHalfSteps(baseNote, 0), addHalfSteps(baseNote, 2), addHalfSteps(baseNote, 7));
        baseNote = dNote;
        dSusTwo.addChord("Dsus2", addHalfSteps(baseNote, 0), addHalfSteps(baseNote, 2), addHalfSteps(baseNote, 7));
        baseNote = dSharpNote;
        dSharpSusTwo.addChord("D#sus2", addHalfSteps(baseNote, 0), addHalfSteps(baseNote, 2), addHalfSteps(baseNote, 7));
        baseNote = dSharpNote;
        eFlatSusTwo.addChord("Ebsus2", addHalfSteps(baseNote, 0), addHalfSteps(baseNote, 2), addHalfSteps(baseNote, 7));
        baseNote = eNote;
        eSusTwo.addChord("Esus2", addHalfSteps(baseNote, 0), addHalfSteps(baseNote, 2), addHalfSteps(baseNote, 7));
        baseNote = fNote;
        fSusTwo.addChord("Fsus2", addHalfSteps(baseNote, 0), addHalfSteps(baseNote, 2), addHalfSteps(baseNote, 7));
        baseNote = fSharpNote;
        fSharpSusTwo.addChord("F#sus2", addHalfSteps(baseNote, 0), addHalfSteps(baseNote, 2), addHalfSteps(baseNote, 7));
        baseNote = fSharpNote;
        gFlatSusTwo.addChord("Gbsus2", addHalfSteps(baseNote, 0), addHalfSteps(baseNote, 2), addHalfSteps(baseNote, 7));
        baseNote = gNote;
        gSusTwo.addChord("Gsus2", addHalfSteps(baseNote, 0), addHalfSteps(baseNote, 2), addHalfSteps(baseNote, 7));
        baseNote = gSharpNote;
        gSharpSusTwo.addChord("G#sus2", addHalfSteps(baseNote, 0), addHalfSteps(baseNote, 2), addHalfSteps(baseNote, 7));
        baseNote = gSharpNote;
        aFlatSusTwo.addChord("Absus2", addHalfSteps(baseNote, 0), addHalfSteps(baseNote, 2), addHalfSteps(baseNote, 7));
        chordList.add(chordList.size(), aSusTwo);
        chordList.add(chordList.size(), aSharpSusTwo);
        chordList.add(chordList.size(), bFlatSusTwo);
        chordList.add(chordList.size(), bSusTwo);
        chordList.add(chordList.size(), cSusTwo);
        chordList.add(chordList.size(), cSharpSusTwo);
        chordList.add(chordList.size(), dFlatSusTwo);
        chordList.add(chordList.size(), dSusTwo);
        chordList.add(chordList.size(), dSharpSusTwo);
        chordList.add(chordList.size(), eFlatSusTwo);
        chordList.add(chordList.size(), eSusTwo);
        chordList.add(chordList.size(), fSusTwo);
        chordList.add(chordList.size(), fSharpSusTwo);
        chordList.add(chordList.size(), gFlatSusTwo);
        chordList.add(chordList.size(), gSusTwo);
        chordList.add(chordList.size(), gSharpSusTwo);
        chordList.add(chordList.size(), aFlatSusTwo);
    }

    public void pickAChord() {
        int maximum = chordList.size();
        int minimum = 0;
        Random rn = new Random();
        randomNum = rn.nextInt((maximum - minimum)) + minimum;
        if ((this.wrongNotesCountThisChord == 0) && ((this.getBounds().getMaxX() - chordTime * difficulty) > 0)) {
            difficulty = difficulty + difficultyAddative;
        } else {
            if (difficulty > 1) {
                difficulty = difficulty - difficultyAddative;
            }
        }
        wrongNotesCountThisChord = 0;

        currentChordName = chordList.get(randomNum).myName;
        currentChord = chordList.get(randomNum);
//        playThis.setText(currentChordName);
        chordTime = 0;
if (checkInversionMode()){
    setupInversionNotes();
}
    notesIn = chordList.get(randomNum).notes;
        System.out.println(notesIn.size());
        if (notesIn.size() > 0) {
            notea = (Integer) notesIn.get(0);
            if (notesIn.size() > 1) {
                noteb = (Integer) notesIn.get(1);
                if (notesIn.size() > 2) {
                    notec = (Integer) notesIn.get(2);
                    if (notesIn.size() > 3) {
                        noted = (Integer) notesIn.get(3);

                    }
                }
            }
        }
        keyOnePressed = false;
        keyTwoPressed = false;
        keyThreePressed = false;
        keyFourPressed = false;
        bassPressed = false;
        secondBassPressed = false;
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
        currentPlayedCorrect = 0;
        if (keyOnePressed == true) {
            currentPlayedCorrect++;
        }
        if (keyTwoPressed == true) {
            currentPlayedCorrect++;
        }
        if (keyThreePressed == true) {
            currentPlayedCorrect++;
        }
        if (keyFourPressed == true) {
            currentPlayedCorrect++;
        }
        if (bassPressed == true) {
            currentPlayedCorrect++;
        }
        if (secondBassPressed == true) {
            currentPlayedCorrect++;
        }
        if (currentPlayedCorrect == (bassNumberExpected + notesIn.size())) {
//            System.out.println("You Win!!");

            gameScore = gameScore + 1;
//            scoreSheetTextArea.setText("Your score is " + gameScore);
            repaint();
            currentChord.addMistakes(wrongNotesCountThisChord);
            currentChord.raiseChorcCount();
            pickAChord();
        }
        repaint();
//        blob.setText(numToString(score));
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
//        top.add(new ImagePanel());
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

    public void letsMakeNew() {
        time = 0;
        timeFast = 10;
        chordTime = 0;
//        timer = new Timer(100, this);
//        timer.start();
        chordTimer = new Timer(10, this);
        chordTimer.start();

        // this.paint(shape);
//		frame = new JFrame();
        top.setSize(600, 600);
        Rectangle shape = new Rectangle(5, 5, 5, 5);
        top.setContentPane(this);
        window = top.getContentPane();
        bob = top.getContentPane();
        this.difficultyChoice = new JTextArea(10, 150);
        this.difficultyChoice.setText("Set Diff(delete and type)");

        bob.add(difficultyChoice);
        difficultyChoice.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e) {

                if (((difficultyChoice.getText().compareTo("Set Diff(delete and type)") == 0))) {
                    difficultyChoice.setText("");
                }

//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            public void mousePressed(MouseEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

//            @Override
            public void mouseReleased(MouseEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

//            @Override
            public void mouseEntered(MouseEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

//            @Override
            public void mouseExited(MouseEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
//bob.addMouseListener(this);
        okay = new JButton();
        okay.setText("OK");
        bob.add(okay);
        okay.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    double a = Double.parseDouble(difficultyChoice.getText());
                    if (a < allTimeBestDifficulty) {
                        try {
                            difficulty = Double.parseDouble(difficultyChoice.getText());
                        } catch (Exception b) {
                            System.out.println("we caught");
                        }

                    }
                } catch (NumberFormatException a) {
                    System.out.println("we caught");

                }
            }
        });

        scoreFrequencyText = new JTextArea(50, 150);

        frequencyScroller = new JScrollPane(scoreFrequencyText);

        bob.add(frequencyScroller);
        menuBar = new JMenuBar();

        menu = new JMenu("chord options");

        menu.setMnemonic(KeyEvent.VK_A);

        menuBar.add(menu);

//        menu
//        top.add(menuBar
        justNotes = new JCheckBoxMenuItem("Just Learn Notes");

        major = new JCheckBoxMenuItem("major");

        justNotes.setSelected(
                true);
        justNotes.addItemListener(
                this);
        menu.add(justNotes);

        menu.add(major);

        major.addItemListener(
                this);
        minor = new JCheckBoxMenuItem("minor");

        menu.add(minor);

        minor.addItemListener(
                this);
        sus2 = new JCheckBoxMenuItem("sus2");

        menu.add(sus2);

        sus2.addItemListener(
                this);
        sus = new JCheckBoxMenuItem("sus");

        menu.add(sus);

        sus.addItemListener(
                this);
        minorSeventh = new JCheckBoxMenuItem("minorSeventh");

        menu.add(minorSeventh);

        minorSeventh.addItemListener(
                this);
        majorSeventh = new JCheckBoxMenuItem("majorSeventh");

        menu.add(majorSeventh);

        majorSeventh.addItemListener(
                this);
        dominantSeventh = new JCheckBoxMenuItem("dominantSeventh");

        menu.add(dominantSeventh);

        dominantSeventh.addItemListener(
                this);


        menu.setMnemonic(KeyEvent.VK_B);

            bassMenu = new JMenu ("Left Hand Bass Count");
            
        menu.setMnemonic (KeyEvent.VK_B);
        
        menuBar.add(bassMenu);

        noBass = new JRadioButton("No Bass");
        oneBass = new JRadioButton("One Bass");
        twoBass = new JRadioButton("Two Bass");
        inversionMenu = new JMenu ("Inversion Options");
           inversionModeOn = new JRadioButton("Only Score Best Inversions");
           inversionModeOff = new JRadioButton("Count Any Invesion");
           ButtonGroup inversionGroup = new ButtonGroup();
           inversionGroup.add(inversionModeOn);
           inversionGroup.add(inversionModeOff);
           inversionMenu.add(inversionModeOn);
           inversionMenu.add(inversionModeOff);
           menuBar.add(inversionMenu);

        ButtonGroup group = new ButtonGroup();

        group.add(noBass);

        group.add(oneBass);

        group.add(twoBass);

        bassMenu.add(noBass);

        bassMenu.add(oneBass);

        bassMenu.add(twoBass);


        noBass.addItemListener(
                this);
        oneBass.addItemListener(
                this);
        twoBass.addItemListener(
                this);
        
        
        inversionModeOn.addItemListener(this);
        inversionModeOff.addItemListener(this);
        
        inversionModeOn.setSelected(true);
        noBass.addItemListener(this);
        oneBass.addItemListener(this);
        twoBass.addItemListener(this);

        top.setJMenuBar(menuBar);

        top.setVisible(
                true);
    }


    public boolean checkInversionMode(){
       if (inversionModeOn.isSelected()){
              return true;
       }       
       else
           return false;
    }
    
    public void setupInversionNotes(){
        realLastNoteA=realThisNoteA;
        realLastNoteB=realThisNoteB;
        realLastNoteC=realThisNoteC;
        realLastNoteD=realThisNoteD;
        
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
        g.setFont(new Font("TimesRoman", Font.PLAIN, 12));
        Graphics2D graphics = (Graphics2D) (g);
//Font thisFont = new Font("TimesRoman");
        int difficultyCount = (int) (difficulty * 1000);
        double difficultyRounded = difficultyCount / 1000.0;
        if (difficultyRounded > bestDifficulty) {
            bestDifficulty = difficultyRounded;

            highScoreFile = new File("highScore.txt");
            if (!highScoreFile.exists()) {
                try {
                    highScoreFormatter = new Formatter("highScore.txt");
                    System.out.println("made a file");
                } catch (Exception e) {
                    System.out.println("Formatter's busted homie");
                }
                highScoreFormatter.format("%s", bestDifficulty);
                highScoreFormatter.close();
            } else {
            }
            try {
                highScoreScanner = new Scanner((highScoreFile));
                System.out.println(highScoreFile.getPath());
            } catch (Exception e) {
                System.out.println("scanner's busted homie");
            }
            Double value = bestDifficulty;
            while (highScoreScanner.hasNext()) {
                String a = highScoreScanner.next();
                value = Double.parseDouble(a);

//                System.out.println("test1 " + value);
            };
//            System.out.println("test2 " + value);
            if (value < bestDifficulty) {
                try {
                    highScoreFormatter = new Formatter("highScore.txt");
                    System.out.println("made a file");
                } catch (Exception e) {
                    System.out.println("Formatter's busted homie");
                }
                highScoreFormatter.format("%s", bestDifficulty);
                highScoreFormatter.close();
                allTimeBestDifficulty = bestDifficulty;

            } else {
                allTimeBestDifficulty = value;
            }

        }

        graphics.drawString(
                "Current Top Difficulty: " + bestDifficulty, 88, 20);

        graphics.drawString(
                "All-Time Top Difficulty: " + allTimeBestDifficulty, this.getWidth() - 150, 20);

        graphics.drawString(
                "Difficulty: " + difficultyRounded, 0, 20);
        g.setFont(new Font("TimesRoman", Font.BOLD, 14));
        graphics.drawString("/", 80, 20);
        g.setFont(new Font("TimesRoman", Font.PLAIN, 12));
        graphics.drawString(
                "Time:" + (int) howManySeconds(), this.getWidth() / 2, this.getHeight() - 20);
        graphics.drawString(
                ("Current Correct Notes: " + numToString(currentPlayedCorrect)), this.getWidth() / 4, this.getHeight() - 20);
        graphics.drawString(
                ("Current Chord: " + currentChordName), (int) this.getBounds()
                .getMinX() + 20, this.getHeight() / 5);
        graphics.drawString(
                ("Wrong Notes = " + wrongNotesCount), (int) (this.getWidth()
                * .75), this.getHeight() - 20);
        graphics.drawString(
                ("Your score is " + gameScore), 20, this.getHeight() - 20);
//graphics.

//		super.paintComponent(g);
//graphics.
//        System.out.println(chordTime);
//        try {
//            
//            img = ImageIO.read(new File ("staff.jpeg"));
//            graphics.drawImage(img, 5, 70, null);
//        } catch (IOException ex) {
////            Logger.getLogger(LessonB.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        System.out.println(img.);
        try {
            URL url;
            if (currentChordName != null) {
//                System.out.println(currentChordName + ".jpeg");

                url = getClass().getResource(currentChordName + ".jpeg");
            } else {
                url = getClass().getResource("blank keyboard.jpeg");
            }
            img = ImageIO.read(url);
            BufferedImage blankImage = ImageIO.read(getClass().getResource("Blank Keyboard.jpeg"));
            BufferedImage staff = ImageIO.read(getClass().getResource("staff.jpeg"));
            int imgHeight = (int) ((this.getWidth() - 20) * ((double) 163.0 / 440));
            int imgWidth = this.getWidth() - 20;
            int staffWidth = ((this.getWidth() - 180));
            int staffHeight = (int) (staffWidth * ((double) 162.0 / 310));
            frequencyScroller.setBounds(10, (this.getHeight() / 5) + 10, 150, this.getHeight() - ((imgHeight + 60) + (this.getHeight() / 5)));
//           graphics.drawImage(staff, 170,((this.getHeight() / 5) + 10), staffWidth, staffHeight, this);
            difficultyChoice.setBounds(this.getWidth() - 350, 5, 130, 20);
            difficultyChoice.setBackground(Color.gray);

            okay.setBounds(this.getWidth() - 220, 5, 60, 20);

            int eLine = this.getHeight() - (imgHeight + 50);
            int fLine = (this.getHeight() / 5);
            int gLine = fLine + (int) ((eLine - fLine) * .75);
            int bLine = fLine + (int) ((eLine - fLine) * .5);
            int dLine = fLine + (int) ((eLine - fLine) * .25);

            graphics.drawLine(170, eLine, this.getWidth(), eLine);
            graphics.drawLine(170, gLine, this.getWidth(), gLine);
            graphics.drawLine(170, bLine, this.getWidth(), bLine);
            graphics.drawLine(170, dLine, this.getWidth(), dLine);
            graphics.drawLine(170, fLine, this.getWidth(), fLine);
            if (wrongNotesCountThisChord > 0) //                img
            {

                graphics.drawImage(img, 10, this.getHeight() - (imgHeight + 40), this.getWidth() - 20, imgHeight, this);
//            this.getWidth() - img.getWidth()) / 2?
            } else {
                graphics.drawImage(blankImage, 10, this.getHeight() - (imgHeight + 40), this.getWidth() - 20, imgHeight, this);

            }
        } //   img = ImageIO.read(new File("/relearning/staff.jpeg"));
        //            JLabel picLabel = new JLabel(new ImageIcon(img));
        //            window.add(picLabel);
        catch (IOException ex) {
            Logger.getLogger(LessonB.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

        g.setFont(
                new Font("TimesRoman", Font.BOLD, 16));
        graphics.drawString(currentChordName,
                (int) (this.getBounds()
                .getMaxX() - chordTime * difficulty), this.getHeight() / 5 - 20);

        // Draw Text
//		g.drawString("This is my custom Panel!", 10, 20);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        timeFast++;
        time = (int) timeFast / 10;
        chordTime++;
        if (canCountSecond()) {
//            timePassedTextArea.setText("Time:" + (int) howManySeconds());
//            titleLineTextArea.setText("brought to you by Silber Solutions");
//            System.out.println(time / 10);
//			paintComponents(this.getGraphics());

            if (time >= 0) {
                List<Chord> finalList = new ArrayList(chordList.size());
                List<Chord> chordListCopy = new ArrayList(chordList.size());

                Chord a = new Chord();
                int k = 0;
                for (Chord d : chordList) {
                    k++;
                    a = d;
//                    System.out.print(d.myName);
                    chordListCopy.add(d);
//                    System.out.print(chordListCopy.get(k - 1).myName);

                }
//                System.out.println(chordList.size() + "  " + chordListCopy.size());
                while (!chordListCopy.isEmpty()) { //perform operation until all elements are moved to new List

//                    System.out.println("yikes");
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
//                    System.out.println(i);
//                    System.out.println(topNow.myName);
                    if (j > 0) {
//                        System.out.println(chordListCopy.get(j - 1).myName);
                        chordListCopy.remove(j - 1);
                    } else {
                        chordListCopy.remove(j);
                    }
//                chordList.remove(topNow);

                }

                String sendOut = "";
                for (Chord d : finalList) {

                    if (d.chordCalledCount > 0) {
                        sendOut = sendOut + "\r\n " + d.myName + " acc: " + (int) (100 * d.calculateMistakesFrequency()) + "%";

                        scoreFrequencyText.setText(sendOut);
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

    public int addHalfSteps(int incomingNumber, int addThisMany) {
        int c = incomingNumber + addThisMany;
//int e = incomingNumber + addThisMany;
        while (c > 12) {
            c = c - 12;

//            System.out.println(d);
        }
        return c;
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
//      
//      System.out.println("You pressed something");
        this.startGame2();

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

    @Override
    public void mouseClicked(MouseEvent e) {

//        if (((difficultyChoice.getText().compareTo("Set Diff(delete and type)") == 0))) {
//            difficultyChoice.setText("");
//        }
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mousePressed(MouseEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseReleased(MouseEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(MouseEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(MouseEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
