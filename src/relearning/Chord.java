/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package relearning;

//import java.awt.List;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

//import java.util.Map;

/**
 *
 * @author Avi
 */
public class Chord {
    private int aHere;
    private int bHere;
    private int cHere;
    private int dHere;
    public List notes;
    public String myName;
    public double mistakesNumber;
    public double mistakesFrequency;
    public double chordCalledCount;
    
    public Chord(){
this.mistakesNumber=0;
 this.mistakesFrequency=0;
 this.chordCalledCount=0;
}
    
    public void raiseChorcCount(){
        this.chordCalledCount++;
    }
    public double calculateMistakesFrequency(){
        this.mistakesFrequency = this.chordCalledCount/(this.chordCalledCount+this.mistakesNumber);
                return this.mistakesFrequency;
    }
    public void addMistakes(int newMistakes){
       this.mistakesNumber=this.mistakesNumber + newMistakes;
        
    }

               public void addChord (String name, int a) {
        myName = name;
        notes = new ArrayList(1);
//        System.out.println(notes.isEmpty());
        aHere=a;
        notes.add(0, a);
        
    }
         
    public void addChord (String name, int a, int b){
//        notes = new List();
        myName = name;
        notes = new ArrayList(2);
//        System.out.println(notes.isEmpty());
        aHere=a;
        bHere=b;
        notes.add(0, a);
        notes.add(1, b);
//        System.out.println(notes.get(0));
    }

   public void addChord (String name, int a, int b, int c){
//       makeIt();
         notes = new ArrayList(3);
       myName = name;
       notes.add(a);
        notes.add(b);
        notes.add(c);
   }
   public void addChord (String name, int a, int b, int c, int d){
//       makeIt();
       notes = new ArrayList(4);
       myName = name;
         notes.add(a);
        notes.add(b);
        notes.add(c);
        notes.add(d);
   }
}
