package in.yutou.site.knowledge.domain;

import java.util.ArrayList;
import java.util.List;

public class Node {
  
  private String nodeId;
  
  private Edition currentEdition;
  
  private String nodeType;
  
  private boolean hasChild;
  
  private List<Edition> editions;
  
  public Node() {
    editions = new ArrayList<Edition>();
  }

  public String getNodeId() {
    return nodeId;
  }

  public void setNodeId(String nodeId) {
    this.nodeId = nodeId;
  }

  public Edition getCurrentEdition() {
    return currentEdition;
  }

  public void setCurrentEdition(Edition currentEdition) {
    this.currentEdition = currentEdition;
  }

  public List<Edition> getEditions() {
    return editions;
  }

  public void setEditions(List<Edition> editions) {
    this.editions = editions;
  }

  public String getNodeType() {
    return nodeType;
  }

  public void setNodeType(String nodeType) {
    this.nodeType = nodeType;
  }

  public boolean isHasChild() {
    return hasChild;
  }

  public void setHasChild(boolean hasChild) {
    this.hasChild = hasChild;
  }

  @Override
  public String toString() {
    return "Node [nodeId=" + nodeId + ", currentEdition=" + currentEdition + ", nodeType=" + nodeType + ", hasChild="
        + hasChild + ", editions=" + editions + "]";
  }
}
