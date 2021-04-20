import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
//Run Game
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