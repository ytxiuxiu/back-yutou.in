package in.yutou.site.common.auth.domain;

import java.util.List;

public class ActionCategory {
  
  private String categoryId;
  
  private String name;
  
  private List<Action> actions;

  public String getCategoryId() {
    return categoryId;
  }

  public void setCategoryId(String categoryId) {
    this.categoryId = categoryId;
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
    return "ActionCategory [categoryId=" + categoryId + ", name=" + name + ", actions=" + actions + "]";
  }

}
