/*
Final Project
Group #_: Zack Preszler, Evan Vujec, Jake Hartman, Zac Howe
Date: (4/20/2021)
*/
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
// Runs the game
class Main {
  public static void main(String args[]) {
    SwingUtilities.invokeLater(
      new Runnable() {
        public void run() {
          new Game();
        }
      }
    );
  }
}