package in.yutou.site.knowledge.domain;

import java.util.ArrayList;
import java.util.List;

public class Node {
  
  private String nodeId;
  
  private Edition currentEdition;
  
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

  @Override
  public String toString() {
    return "Node [nodeId=" + nodeId + ", currentEdition=" + currentEdition + ", editions=" + editions + "]";
  }
}
