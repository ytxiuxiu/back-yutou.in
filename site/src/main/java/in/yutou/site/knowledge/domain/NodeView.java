package in.yutou.site.knowledge.domain;

import java.util.Date;

import in.yutou.site.common.auth.domain.User;

public class NodeView {
  
  private String viewId;
  
  private User viewUser;
  
  private Node viewNode;
  
  private String viewIp;
  
  private Date viewedAt;

  public String getViewId() {
    return viewId;
  }

  public void setViewId(String viewId) {
    this.viewId = viewId;
  }

  public User getViewUser() {
    return viewUser;
  }

  public void setViewUser(User viewUser) {
    this.viewUser = viewUser;
  }

  public Node getViewNode() {
    return viewNode;
  }

  public void setViewNode(Node viewNode) {
    this.viewNode = viewNode;
  }

  public String getViewIp() {
    return viewIp;
  }

  public void setViewIp(String viewIp) {
    this.viewIp = viewIp;
  }

  public Date getViewedAt() {
    return viewedAt;
  }

  public void setViewedAt(Date viewedAt) {
    this.viewedAt = viewedAt;
  }

  @Override
  public String toString() {
    return "NodeView [viewId=" + viewId + ", viewUser=" + viewUser + ", viewNode=" + viewNode + ", viewIp=" + viewIp
        + ", viewedAt=" + viewedAt + "]";
  }
  

}
