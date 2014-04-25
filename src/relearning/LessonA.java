/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package relearning;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;

import java.awt.event.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import javax.swing.*;

/**
 *
 * @author Avi
 */
public class LessonA extends JFrame implements KeyListener {
boolean upPressed;
boolean downPressed;
boolean leftPressed;
boolean rightPressed;
JTextField blob;
JTextField playThis;
Map chordSet;
List<Chord> chordList;
int up = 38;
     int down = 40;
    int left = 37;
    int right = 39;
    public LessonA() {

    }

    public static void main(String[] args) {
        //System.out.println("it runs boss");
//        javax.swing.SwingUtilities.invokeLater (new Runnable() {   public void run() {
//        System.out.println ("it runs boss");
////       LessonA boom = new LessonA(); 
////               boom.letsMake();
//    letsMake();
//        }
    
//});
            
//letsMake();
   LessonA top = new LessonA();
    top.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    top.letsMake();
    top.startGame();
    }
    
    public void startGame(){
        chordList = new ArrayList<Chord>(0);
        System.out.println("we made it");
        Chord upDown = new Chord();
        upDown.addChord("Up Down", up, down);
        Chord leftRight = new Chord ();
        leftRight.addChord("Left Right", left, right);
        int minimum = 0;
        Random rn = new Random();
        chordList.add(chordList.size(), upDown);
        chordList.add(chordList.size(), leftRight);
        int maximum = chordList.size();
        int randomNum = rn.nextInt((maximum - minimum) + 1) + minimum;
        playThis.setText(chordList.get(randomNum).myName);
        
//        chordSet.put(upDown, true);
//        chordSet.put(leftRight, true);
    }
    
    public void letsMake(){
       playThis=new JTextField(20);
         blob = new JTextField(20);
         getContentPane().add(playThis, BorderLayout.PAGE_START);
       getContentPane().add(blob, BorderLayout.PAGE_END);
       setVisible(true);
    blob.addKeyListener(this);
//    blob.setText("hi");
    playThis.setEditable(false);
    blob.setEditable(false);
       pack();
    }
    
    @Override

    public void keyTyped(KeyEvent e) {
        
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyPressed(KeyEvent e) {
//     chord.put(e.getKeyCode(), true);
        if (e.getKeyCode()==up){
        System.out.println("you pressed up");
        upPressed = true;
//        chord.put(e.getKeyChar(), true);
        }
        else if (e.getKeyCode()==down){
        System.out.println("you pressed down");
        downPressed = true;
        }
        else if (e.getKeyCode()==left){
        System.out.println("you pressed left");
        leftPressed = true;
        }
        else if (e.getKeyCode()==right){
        System.out.println("you pressed right");
        rightPressed = true;
        }
        else         
            System.out.println(e.getKeyCode());

//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyReleased(KeyEvent e) {
//        chord.remove(e.getKeyChar());
       if (upPressed==true && downPressed==true){
           System.out.println("you pressed the chord");
           blob.setText("up down chord played");
       }
       else if (leftPressed==true && rightPressed==true){
           blob.setText("left right chord played");
       }
       else{}
       
        if (e.getKeyCode()==up){
            upPressed=false;
        }
        else if (e.getKeyCode()==right){
            rightPressed=false;
        }
        else if (e.getKeyCode()==down){
            downPressed=false;
        }
        else if (e.getKeyCode()==left){
            leftPressed=false;
        }
        else System.out.println("you released another key");
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
