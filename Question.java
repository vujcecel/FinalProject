/**
* Quiestion class represents a question and its related attributes
* @author Evan Vujcec
* @version 20-04-2021
*/

class Question {
  private String prompt;
  private String choices[];
  private int correctIdx;
  private int points;

  /**
  * Returns the prompt of the {@link Question}
  * @return the question prompt
  */
  String getPrompt() {
    return prompt;
  }

  /**
  * Returns the choices of the {@link Question}
  * @return an array of possible answers to the prompt
  */
  String[] getChoices() {
    return choices;
  }

  /**
  * Returns the CorrectIdn of the {@link Question}
  * @return the index of the correct answer in the choices array
  */
  int getCorrectIdx() {
    return correctIdx;
  }

  /**
  * Returns the points of the {@link Question}
  * @return the amount of points the question is worth
  */
  int getPoints() {
    return points;
  }

  Question(String prompt, String choices[], int correctIdx, int points) {
    this.prompt = prompt;
    this.choices = choices;
    this.correctIdx = correctIdx;
    this.points = points;
  }
}