import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;

class Game implements ActionListener {
  JFrame frame;
  JLabel welcomeLabel, questionLabel, scoreLabel;
  JButtons answers[];
  JButton nextButton;
  Question questions[];

  Game() {
    importQuestions();

    frame = new JFrame("Group #4's Trivia Game");
    frame.setLayout(new FlowLayout());
    frame.setSize(240, 120);


  }

  void importQuestions() {
    
  }
}