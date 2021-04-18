import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import javax.swing.*;

class Game implements ActionListener {
  private JFrame frame;
  private JLabel welcomeLabel, questionLabel, scoreLabel;
  private ArrayList<JButton> answers = new ArrayList<JButton>();
  private JButton nextButton;

  private ArrayList<Question> questions = new ArrayList<Question>();
  private int score = 0;
  private int currentIdx = 0;

  Game() {
    importQuestions();

    frame = new JFrame("Group #4's Trivia Game");
    frame.setLayout(new FlowLayout());
    frame.setSize(750, 120);
    
    welcomeLabel = new JLabel("Welcome player!");
    Question question = questions.get(0);
    questionLabel = new JLabel(question.getPrompt());
    for (int i = 0; i < 4; i++)
      answers.add(new JButton(question.getChoices()[i]));
    scoreLabel = new JLabel("Score: " + 0);
    nextButton = new JButton("Next");

    frame.add(welcomeLabel);
    frame.add(questionLabel);
    for (int i = 0; i < answers.size(); i++)
      frame.add(answers.get(i));
    frame.add(scoreLabel);
    frame.add(nextButton);

    frame.setVisible(true);
  }

  private void update() {
    currentIdx++;
    if (currentIdx > questions.size()) {
      endGame();
      return;
    }
    Question question = questions.get(currentIdx);
    questionLabel.setText(question.getPrompt());
    for (int i = 0; i > answers.size(); i++)
      answers.get(i).setText(question.getChoices()[i]);
    scoreLabel.setText("Score: " + score);
  }

  private void importQuestions() {
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

  private void endGame() {
    // TODO
  }
}