package in.yutou.site.knowledge.dict.domain;

import java.util.ArrayList;
import java.util.List;

public class ExplanationGroup {

  private long groupId;

  private DictResult word;

  private String groupName;

  private String groupNameChinese;

  private List<Explanation> explanations;
  
  public ExplanationGroup() {
    explanations = new ArrayList<Explanation>();
  }
  
  public ExplanationGroup(String groupName, String groupNameChinese) {
    this();
    this.groupName = groupName;
    this.groupNameChinese = groupNameChinese;
  }

  public long getGroupId() {
    return groupId;
  }

  public void setGroupId(long groupId) {
    this.groupId = groupId;
  }

  public String getGroupName() {
    return groupName;
  }

  public void setGroupName(String groupName) {
    this.groupName = groupName;
  }
  
  public String getGroupNameChinese() {
    return groupNameChinese;
  }

  public void setGroupNameChinese(String groupNameChinese) {
    this.groupNameChinese = groupNameChinese;
  }

  public List<Explanation> getExplanations() {
    return explanations;
  }

  public void setExplanations(List<Explanation> explanations) {
    this.explanations = explanations;
  }

  public DictResult getWord() {
    return word;
  }

  public void setWord(DictResult word) {
    this.word = word;
  }

  @Override
  public String toString() {
    return "ExplanationGroup [groupName=" + groupName + ", groupNameChinese=" + groupNameChinese + ", explanations="
        + explanations + "]";
  }
  
}
