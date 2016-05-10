package in.yutou.site.knowledge.dict.domain;

public class Example {

  private long exampleId;

  private Explanation explanation;
  
  private String english;
  
  private String chinese;
  
  private String audio;
  
  public Example() {
  }

  public Example(String english, String chinese, String audio) {
    this.english = english;
    this.chinese = chinese;
    this.audio = audio;
  }

  public long getExampleId() {
    return exampleId;
  }

  public void setExampleId(long exampleId) {
    this.exampleId = exampleId;
  }

  public Explanation getExplanation() {
    return explanation;
  }

  public void setExplanation(Explanation explanation) {
    this.explanation = explanation;
  }

  public String getEnglish() {
    return english;
  }

  public void setEnglish(String english) {
    this.english = english;
  }

  public String getChinese() {
    return chinese;
  }

  public void setChinese(String chinese) {
    this.chinese = chinese;
  }

  public String getAudio() {
    return audio;
  }

  public void setAudio(String audio) {
    this.audio = audio;
  }

  @Override
  public String toString() {
    return "Example [english=" + english + ", chinese=" + chinese + ", audio=" + audio + "]";
  }

}
