package in.yutou.site.common.auth.domain;

import java.util.List;

public class Group {
  
  private String groupId;
  
  private String name;
  
  private List<Action> actions;

  public String getGroupId() {
    return groupId;
  }

  public void setGroupId(String groupId) {
    this.groupId = groupId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<Action> getActions() {
    return actions;
  }

  public void setActions(List<Action> actions) {
    this.actions = actions;
  }

  @Override
  public String toString() {
    return "Group [groupId=" + groupId + ", name=" + name + ", actions=" + actions + "]";
  }

}
