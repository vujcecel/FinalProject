class Question {
  private String prompt;
  private String choices[];
  private int correctIdx;
  private int points;
/**
* Returns the prompt of the {@link Question}
*@return {@link prompt}
*/
  String getPrompt() {
    return prompt;
  }
/**
* Returns the choices of the {@link Question}
*@return {@link Choices}
*/
  String[] getChoices() {
    return choices;
  }
/**
* Returns the CorrectIdn of the {@link Question}
*@return {@link correctIdx}
*/
  int getCorrectIdx() {
    return correctIdx;
  }
/**
* Returns the points of the {@link Question}
*@return {@link points}
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