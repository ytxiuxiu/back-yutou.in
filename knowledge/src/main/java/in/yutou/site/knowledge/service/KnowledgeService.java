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
    List<Edition> nodes = selectNodesByPath(path);
    
    for (int i = 0; i < nodes.size() - 1; i++) {
      Edition node = nodes.get(i);
      String nodePath = node.getPath();
      String nodeParentPath = nodePath.substring(0, nodePath.lastIndexOf("/"));
      
      boolean isSplited = false;
      if (node.getNode().getNodeType().equals("spliter") && node.getPath().length() != path.length() + 32 + 1) {
        isSplited = true;
      }
      
      for (int j = i; j < nodes.size(); j++) {
        Edition parentNode = nodes.get(j);
        String parentNodePath = parentNode.getPath();
        if (parentNodePath.equals(nodeParentPath) || parentNodePath.equals(nodeParentPath + "/")) {
          parentNode.getNode().setHasChild(true);
          if (!isSplited) {
            parentNode.getChildren().add(node);
          }
          
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
  
  public List<Edition> selectNodesByPath(String path) {
    Map<String, String> params = new HashMap<String, String>();
    params.put("path", path);
    
    return nodeDao.selectLatestEditionsOfChildrenNodesByPath(params);
  }
  
  public int getViewNumber(String nodeId) {
    return nodeDao.getViewNumber(nodeId);
  }
  
  
  public void addNode(Node node) {
    nodeDao.insertNode(node);
  }
  
  public void addEdition(Edition node) {
    nodeDao.insertNewEdition(node);
  }
  
  public void addNewNodeView(String userId, String nodeId, String ip) {
    Map<String, String> params = new HashMap<String, String>();
    params.put("userId", userId);
    params.put("nodeId", nodeId);
    params.put("ip", ip);
    
    nodeDao.addNewNodeView(params);
  }
  
  public void moveChildrenOfMovedNode(String userId, String oldParentPath, String newParentPath, String saveId) {
    Map<String, String> params = new HashMap<String, String>();
    params.put("userId", userId);
    params.put("oldParentPath", oldParentPath);
    params.put("newParentPath", newParentPath);
    params.put("saveId", saveId);
    
    nodeDao.moveChildrenOfMovedNode(params);
  }
  
  public List<Edition> findAllNodesWhoesCurrentEditionNeedToBeSwitched(String saveId) {
    return nodeDao.findAllNodesWhoesCurrentEditionNeedToBeSwitched(saveId);
  }
  
  public void switchCurrentEdition(String nodeId, String editionId) {
    Map<String, String> params = new HashMap<String, String>();
    params.put("nodeId", nodeId);
    params.put("editionId", editionId);
    
    nodeDao.switchCurrentEdition(params);
  }
  
}
