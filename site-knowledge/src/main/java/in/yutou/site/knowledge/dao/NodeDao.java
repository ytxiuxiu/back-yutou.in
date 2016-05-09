package in.yutou.site.knowledge.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import in.yutou.site.knowledge.domain.Edition;
import in.yutou.site.knowledge.domain.Node;

@Repository
public interface NodeDao {

  public Node getNodeById(String nodeId);
  
  public Edition getEditionById(String editionId);
  
  public Edition getCurrentEditionByNodeId(String nodeId);
  
  /**
   * Select the latest editions of children nodes of a certain path
   * @param params a map contains:
   *  path: select all children of this path (including this path)
   * @return
   */
  public List<Edition> selectLatestEditionsOfChildrenNodesByPath(Map<String, String> params);
  
  public void insertNode(Node node);
  
  public void insertNewEdition(Edition node);
  
  /**
   * Switch current edition of a node
   * @param params a map contains:
   *  nodeId: switch whose edition
   *  editionId: switch to which edition
   */
  public void switchCurrentEdition(Map<String, String> params);
}
