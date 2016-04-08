package in.yutou.site.knowledge.dict.domain;

import java.util.ArrayList;
import java.util.List;

public class DictResult {
  
  private String keyword;
  
  private String audio;
  
  private List<ExplanationGroup> explanationGroups;
  
  public DictResult() {
    explanationGroups = new ArrayList<ExplanationGroup>();
  }

  public String getKeyword() {
    return keyword;
  }

  public void setKeyword(String keyword) {
    this.keyword = keyword;
  }

  public String getAudio() {
    return audio;
  }

  public void setAudio(String audio) {
    this.audio = audio;
  }

  public List<ExplanationGroup> getExplanationGroups() {
    return explanationGroups;
  }

  public void setExplanationGroups(List<ExplanationGroup> explanationGroups) {
    this.explanationGroups = explanationGroups;
  }

  @Override
  public String toString() {
    return "DictResult [keyword=" + keyword + ", audio=" + audio + ", explanationGroups=" + explanationGroups + "]";
  }

}
