// Set up question class with prompt choices correctIdx and point system
class Question {
  private String prompt;
  private String choices[];
  private int correctIdx;
  private int points;
//set Accessor method for prompt, choices, correctIdx and points
  String getPrompt() {
    return prompt;
  }

  String[] getChoices() {
    return choices;
  }

  int getCorrectIdx() {
    return correctIdx;
  }

  int getPoints() {
    return points;
  }
//Set up contructor
  Question(String prompt, String choices[], int correctIdx, int points) {
    this.prompt = prompt;
    this.choices = choices;
    this.correctIdx = correctIdx;
    this.points = points;
  }
}