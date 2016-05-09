package in.yutou.site.knowledge.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import in.yutou.site.common.auth.domain.User;

public class Edition {
  
  private String editionId;
  
  private Node node;
  
  private User user;
  
  private String saveId;
  
  private String name;
  
  private String path;
  
  private Node parent;
  
  private List<Edition> children;
  
  private String small;
  
  private String content;
  
  private String status;
  
  private int priority;
  
  private Date createdAt;
  
  private boolean deleted;
  
  public Edition() {
    children = new ArrayList<Edition>();
  }

  public Node getNode() {
    return node;
  }

  public void setNode(Node node) {
    this.node = node;
  }

  public List<Edition> getChildren() {
    return children;
  }

  public void setChildren(List<Edition> children) {
    this.children = children;
  }

  public String getEditionId() {
    return editionId;
  }

  public void setEditionId(String editionId) {
    this.editionId = editionId;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPath() {
    return path;
  }

  public void setPath(String path) {
    this.path = path;
  }

  public Node getParent() {
    return parent;
  }

  public void setParent(Node parent) {
    this.parent = parent;
  }

  public String getSmall() {
    return small;
  }

  public void setSmall(String small) {
    this.small = small;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public Date getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(Date createdAt) {
    this.createdAt = createdAt;
  }

  public boolean isDeleted() {
    return deleted;
  }

  public void setDeleted(boolean deleted) {
    this.deleted = deleted;
  }

  public String getSaveId() {
    return saveId;
  }

  public void setSaveId(String saveId) {
    this.saveId = saveId;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public int getPriority() {
    return priority;
  }

  public void setPriority(int priority) {
    this.priority = priority;
  }

  @Override
  public String toString() {
    return "Edition [editionId=" + editionId + ", node=" + node + ", user=" + user + ", saveId=" + saveId + ", name="
        + name + ", path=" + path + ", parent=" + parent + ", children=" + children + ", small=" + small + ", content="
        + content + ", status=" + status + ", priority=" + priority + ", createdAt=" + createdAt + ", deleted="
        + deleted + "]";
  }
  
}
