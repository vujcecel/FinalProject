import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import javax.swing.*;

class Game {
  JFrame frame;
  JLabel welcomeLabel, questionLabel, scoreLabel;
  JButton answers[] = new JButton[4];
  JButton nextButton;
  ArrayList<Question> questions = new ArrayList<Question>();

  Game() {
    importQuestions();

    frame = new JFrame("Group #4's Trivia Game");
    frame.setLayout(new FlowLayout());
    frame.setSize(240, 120);


  }

  void importQuestions() {
    try {
      FileReader fileReader = new FileReader("trivia.txt");
      BufferedReader bufferedReader = new BufferedReader(fileReader);
      String question;
      String choices[] = new String[4];
      int correctIdx;
      int points;
      while (bufferedReader.ready()) {
        question = bufferedReader.readLine();
        for (int i = 0; bufferedReader.ready() && i < choices.length; i++) {
          choices[i] = bufferedReader.readLine();
        }
        correctIdx = Integer.parseInt(bufferedReader.readLine());
        points = Integer.parseInt(bufferedReader.readLine());
        questions.add(new Question(question, choices, correctIdx, points));
      }
      bufferedReader.close();
    }
    catch(IOException e) {
      System.out.println(e.toString());
    }
  }
}