class Question {
  private String prompt;
  private String choices[];
  private int correctIdx;
  private int points;

  String getPrompt() {
    return prompt;
  }

  String getChoices() {
    return choices;
  }

  int getCorrectIdx() {
    return correctIdx;
  }

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