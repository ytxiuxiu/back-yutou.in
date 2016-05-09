package in.yutou.site.common.auth.domain;

public class Action {
  
  private String actionId;
  
  private String name;
  
  private ActionCategory category;

  public String getActionId() {
    return actionId;
  }

  public void setActionId(String actionId) {
    this.actionId = actionId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public ActionCategory getCategory() {
    return category;
  }

  public void setCategory(ActionCategory category) {
    this.category = category;
  }

  @Override
  public String toString() {
    return "Action [actionId=" + actionId + ", name=" + name + ", category=" + category + "]";
  }

}
