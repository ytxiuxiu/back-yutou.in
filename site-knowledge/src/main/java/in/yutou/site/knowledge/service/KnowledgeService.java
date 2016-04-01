package in.yutou.site.knowledge.service;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.yutou.site.common.exception.BusinessException;
import in.yutou.site.knowledge.dao.NodeDao;
import in.yutou.site.knowledge.domain.Edition;
import in.yutou.site.knowledge.domain.Node;

@Service
public class KnowledgeService {

  @Autowired
  private NodeDao nodeDao;
  
  public Node getNodeById(String nodeId) {
    return nodeDao.getNodeById(nodeId);
  }
  
  public Edition getEditionById(String editionId) {
    return nodeDao.getEditionById(editionId);
  }
  
  public Edition getCurrentEditionByNodeId(String nodeId) {
    return nodeDao.getCurrentEditionByNodeId(nodeId);
  }
  
  public Edition selectNodeTreeByPath(String path) {
    Map<String, String> params = new HashMap<String, String>();
    params.put("path", path);
    
    List<Edition> nodes = nodeDao.selectLatestEditionsOfChildrenNodesByPath(params);
    
    for (int i = 0; i < nodes.size() - 1; i++) {
      Edition node = nodes.get(i);
      String nodePath = node.getPath();
      String nodeParentPath = nodePath.substring(0, nodePath.lastIndexOf("/"));
      
      for (int j = i; j < nodes.size(); j++) {
        Edition parentNode = nodes.get(j);
        String parentNodePath = parentNode.getPath();
        if (parentNodePath.equals(nodeParentPath) || parentNodePath.equals(nodeParentPath + "/")) {
          parentNode.getChildren().add(node);
          break;
        }
      }
    }
    
    Iterator<Edition> it = nodes.iterator();
    while (it.hasNext()) {
      Edition node = it.next();
      if (node.getPath().equals(path)) {
        return node;
      }
    }
    
    throw new BusinessException("No nodes match such path");
  }
  
  public void addNode(Node node) {
    nodeDao.insertNode(node);
  }
  
  public void addEdition(Edition node) {
    nodeDao.insertNewEdition(node);
  }
  
  public void switchCurrentEdition(String nodeId, String editionId) {
    Map<String, String> params = new HashMap<String, String>();
    params.put("nodeId", nodeId);
    params.put("editionId", editionId);
    
    nodeDao.switchCurrentEdition(params);
  }
  
}
