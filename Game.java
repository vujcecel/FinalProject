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

    answers.get(0).setActionCommand("A");
    answers.get(1).setActionCommand("B");
    answers.get(2).setActionCommand("C");
    answers.get(3).setActionCommand("D");
    
    
    answers.get(0).addActionListener(this);
    answers.get(1).addActionListener(this);
    answers.get(2).addActionListener(this);
    answers.get(3).addActionListener(this);
    nextButton.addActionListener(this);

    frame.add(welcomeLabel);
    frame.add(questionLabel);
    for (int i = 0; i < answers.size(); i++)
      frame.add(answers.get(i));
    frame.add(scoreLabel);
    frame.add(nextButton);

    frame.setVisible(true);
  }

  private void next() {
    currentIdx++;
    if (currentIdx == questions.size() - 1) {
      nextButton.setText("Score");
    }
    Question question = questions.get(currentIdx);
    questionLabel.setText(question.getPrompt());
    for (int i = 0; i < answers.size(); i++) {
      answers.get(i).setText(question.getChoices()[i]);
      answers.get(i).setForeground(new Color(0x000000));
    }
    scoreLabel.setText("Score: " + score);
  }

  private void importQuestions() {
    try {
      FileReader fileReader = new FileReader("trivia.txt");
      BufferedReader bufferedReader = new BufferedReader(fileReader);
      while (bufferedReader.ready()) {
        String choices[] = new String[4];
        String question = bufferedReader.readLine();
        for (int i = 0; i < choices.length; i++) {
          choices[i] = bufferedReader.readLine();
        }
        int correctIdx = Integer.parseInt(bufferedReader.readLine());
        int points = Integer.parseInt(bufferedReader.readLine());
        questions.add(new Question(question, choices, correctIdx, points));
      }
      bufferedReader.close();
    }
    catch(IOException e) {
      System.out.println(e.toString());
    }
  }

  private void scoreGame() {
    try {
      FileWriter fileWriter = new FileWriter("scores.txt", true);
      BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
      bufferedWriter.write(Integer.toString(score));
      bufferedWriter.newLine();
      bufferedWriter.flush();
      bufferedWriter.close();
    }
    catch(IOException e) {
      System.out.println(e.toString());
    }
    frame.remove(welcomeLabel);
    frame.remove(questionLabel);
    for (int i = 0; i < answers.size(); i++)
      frame.remove(answers.get(i));
    nextButton.setText("End");
    scoreLabel.setText("Final Score: " + score);
    frame.setSize(150, 100);
    frame.repaint();
  }

  public void actionPerformed(ActionEvent ae) {
    switch (ae.getActionCommand()) {
      case "Next":
        next();
        break;
      case "A":
        grade(0);
        break;
      case "B":
        grade(1);
        break;
      case "C":
        grade(2);
        break;
      case "D":
        grade(3);
        break;
      case "Score":
        scoreGame();
        break;
      case "End":
        System.exit(0);
        break;
      default:
        System.out.printf("Error: unhandled action command '%s'%n", ae.getActionCommand());
    }
  }

  private void grade(int choice) {
    if (questions.get(currentIdx).getCorrectIdx() == choice) {
      score += questions.get(currentIdx).getPoints();
      answers.get(choice).setForeground(new Color(0x00ff00));
    }
    else {
      answers.get(choice).setForeground(new Color(0xff0000));
    }
    scoreLabel.setText("Score: " + score);
  }
}