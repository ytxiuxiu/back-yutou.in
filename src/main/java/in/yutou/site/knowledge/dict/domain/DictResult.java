package in.yutou.site.knowledge.dict.domain;

import java.util.ArrayList;
import java.util.List;

public class DictResult {
  
  private String keyword;
  
  private String audioBritish;
  
  private String symbolBritish;
  
  private String audioAmerican;
  
  private String symbolAmerican;
  
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

  public String getAudioBritish() {
    return audioBritish;
  }

  public void setAudioBritish(String audioBritish) {
    this.audioBritish = audioBritish;
  }

  public String getSymbolBritish() {
    return symbolBritish;
  }

  public void setSymbolBritish(String symbolBritish) {
    this.symbolBritish = symbolBritish;
  }

  public String getAudioAmerican() {
    return audioAmerican;
  }

  public void setAudioAmerican(String audioAmerican) {
    this.audioAmerican = audioAmerican;
  }

  public String getSymbolAmerican() {
    return symbolAmerican;
  }

  public void setSymbolAmerican(String symbolAmerican) {
    this.symbolAmerican = symbolAmerican;
  }

  public List<ExplanationGroup> getExplanationGroups() {
    return explanationGroups;
  }

  public void setExplanationGroups(List<ExplanationGroup> explanationGroups) {
    this.explanationGroups = explanationGroups;
  }

  @Override
  public String toString() {
    return "DictResult [keyword=" + keyword + ", audioBritish=" + audioBritish + ", symbolBritish=" + symbolBritish
        + ", audioAmerican=" + audioAmerican + ", symbolAmerican=" + symbolAmerican + ", explanationGroups="
        + explanationGroups + "]";
  }

}
