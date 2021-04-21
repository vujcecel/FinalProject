/*
Final Project
Group #4: Zack Preszler, Evan Vujcec, Jake Hartman, Zac Howe
Date: 4-20-2021
*/
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
* Main class handles execution of SwingUI created by {@link Game}
* @author Evan Vujcec
* @version 16-04-2021
*/
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