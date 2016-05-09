package in.yutou.site.knowledge.dict.domain;

import java.util.ArrayList;
import java.util.List;

public class ExplanationGroup {

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

  @Override
  public String toString() {
    return "ExplanationGroup [groupName=" + groupName + ", groupNameChinese=" + groupNameChinese + ", explanations="
        + explanations + "]";
  }
  
}
