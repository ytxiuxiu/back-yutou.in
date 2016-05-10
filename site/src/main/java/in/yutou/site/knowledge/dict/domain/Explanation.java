package in.yutou.site.knowledge.dict.domain;

import java.util.ArrayList;
import java.util.List;

public class Explanation {

  private long explanationId;

  private ExplanationGroup explanationGroup;
  
  private String partOfSpeech;

  private String explanation;
  
  private String explanationChinese;
  
  private List<Example> examples;
  
  public Explanation() {
    examples = new ArrayList<Example>();
  }

  public Explanation(String partOfSpeech, String explanation, String explanationChinese) {
    this();
    this.partOfSpeech = partOfSpeech;
    this.explanation = explanation;
    this.explanationChinese = explanationChinese;
  }

  public long getExplanationId() {
    return explanationId;
  }

  public void setExplanationId(long explanationId) {
    this.explanationId = explanationId;
  }

  public ExplanationGroup getExplanationGroup() {
    return explanationGroup;
  }

  public void setExplanationGroup(ExplanationGroup explanationGroup) {
    this.explanationGroup = explanationGroup;
  }

  public String getPartOfSpeech() {
    return partOfSpeech;
  }

  public void setPartOfSpeech(String partOfSpeech) {
    this.partOfSpeech = partOfSpeech;
  }

  public String getExplanation() {
    return explanation;
  }

  public void setExplanation(String explanation) {
    this.explanation = explanation;
  }

  public String getExplanationChinese() {
    return explanationChinese;
  }

  public void setExplanationChinese(String explanationChinese) {
    this.explanationChinese = explanationChinese;
  }

  public List<Example> getExamples() {
    return examples;
  }

  public void setExamples(List<Example> examples) {
    this.examples = examples;
  }

  @Override
  public String toString() {
    return "Explanation [partOfSpeech=" + partOfSpeech + ", explaination=" + explanation + ", explainationChinese="
        + explanationChinese + ", examples=" + examples + "]";
  }
  
}
