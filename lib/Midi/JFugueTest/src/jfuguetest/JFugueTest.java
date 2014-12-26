/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jfuguetest;

/**
 *
 * @author Avi
 */
import org.jfugue.*; 
 
public class JFugueTest  
{ 
 public static void main(String[] args) {
     
 Player player = new Player(); 
 Pattern pattern = new Pattern("C "); 
 System.out.println(pattern.getMusicString());
 
 player.play(pattern); 
 System.exit(0); // If using Java 1.4 or lower 

         }
}