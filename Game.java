import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import javax.swing.*;

// Game class handles UI and internal game logic
class Game implements ActionListener {
  private JFrame frame;
  private JLabel welcomeLabel, questionLabel, scoreLabel;
  private ArrayList<JButton> answers = new ArrayList<JButton>();
  private JButton nextButton;
  private JPanel PanelAnswer, PanelQuestion, PanelScore;

  private ArrayList<Question> questions = new ArrayList<Question>();
  private int score = 0;
  private int currentIdx = 0;
  // Import questions code
  Game() {
    importQuestions();

    // Create header and determine frame size and layout
    frame = new JFrame("Group #4's Trivia Game");
    frame.setLayout(new BorderLayout());
    frame.setSize(800, 150);

    //Set up panels for UI design
    PanelQuestion = new JPanel();
    PanelAnswer = new JPanel();
    PanelScore = new JPanel();

    // Set up code to welcome player and prompt questions as well as set score to 0
    welcomeLabel = new JLabel("Welcome player!");
    Question question = questions.get(0);
    questionLabel = new JLabel(question.getPrompt());
    for (int i = 0; i < 4; i++)
      answers.add(new JButton(question.getChoices()[i]));
    scoreLabel = new JLabel("Score: " + 0);
    nextButton = new JButton("Next");

    // Create functionality to answer buttons
    answers.get(0).setActionCommand("A");
    answers.get(1).setActionCommand("B");
    answers.get(2).setActionCommand("C");
    answers.get(3).setActionCommand("D");
    
    // Add action listener to each button    
    answers.get(0).addActionListener(this);
    answers.get(1).addActionListener(this);
    answers.get(2).addActionListener(this);
    answers.get(3).addActionListener(this);
    nextButton.addActionListener(this);

    // Add labels for welcome, questions score and next button
    PanelQuestion.add(welcomeLabel);
    PanelQuestion.add(questionLabel);
    for (int i = 0; i < answers.size(); i++)
      PanelAnswer.add(answers.get(i));
    PanelScore.add(scoreLabel);
    PanelScore.add(nextButton);
    frame.add(PanelQuestion, BorderLayout.PAGE_START);
    frame.add(PanelAnswer, BorderLayout.CENTER);
    frame.add(PanelScore, BorderLayout.PAGE_END);
    
    frame.setVisible(true);
  }

  /* Go to next question, reset UI state, update score for the {@link Game}
  */
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

  // Set up file reader to get texts from trivia.txt file
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

  /** Saves score and displays end screen to the {@link Game}
  */
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
    //frame.remove(welcomeLabel);
    //frame.remove(questionLabel);
    //for (int i = 0; i < answers.size(); i++)
      //frame.remove(answers.get(i));
    frame.remove(PanelQuestion);
    frame.remove(PanelAnswer);
    nextButton.setText("End");
    scoreLabel.setText("Final Score: " + score);
    frame.setSize(150, 100);
    frame.repaint();
  }

  // Method for handling button presses
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

  /* Grade answer and change button color to the {@link Game}
  */
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