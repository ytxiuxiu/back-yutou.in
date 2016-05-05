package in.yutou.site.knowledge.controller;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import in.yutou.site.common.auth.domain.User;
import in.yutou.site.common.auth.powercontrol.PowerControl;
import in.yutou.site.common.auth.service.UserService;
import in.yutou.site.common.domain.Response;
import in.yutou.site.common.util.UUID;
import in.yutou.site.knowledge.domain.Edition;
import in.yutou.site.knowledge.domain.Node;
import in.yutou.site.knowledge.service.KnowledgeService;

@Controller
@RequestMapping("knowledge")
public class KnowledgeController {
  
  @Autowired
  private KnowledgeService knowledgeService;
  
  @Autowired
  private UserService userService;
  
  
  /**
   * Update node
   * This method will add a new edition to specific node
   * @param idToken
   * @param edition new edition
   * @param node  contains nodeId
   * @return
   * @throws GeneralSecurityException
   * @throws IOException
   */
  @PowerControl({"knowledge.map.edit"})
  @RequestMapping(value="map/edition/add", method=RequestMethod.POST)
  public @ResponseBody Map<String, Object> addEdition(String loginToken, Edition edition, Node node) throws GeneralSecurityException, IOException {
    Response response = new Response("node");
    
    User user = userService.getUserByLoginToken(loginToken);
    edition.setUser(user);
    
    // generate a save id for each saving
    String saveId = UUID.getUUID();
    edition.setSaveId(saveId);
    
    // add node if not exists
    Node _node = knowledgeService.getNodeById(node.getNodeId());
    if (_node == null) {
      knowledgeService.addNode(node);
      _node = knowledgeService.getNodeById(node.getNodeId());
    }
    edition.setNode(_node);
    
    /* add edition */
    // update all children's path if the path has been changed
    Edition _edition = knowledgeService.getEditionById(_node.getCurrentEdition().getEditionId());
    if (_edition != null && !edition.getPath().equals(_edition.getPath())) {  // path changed
      
      knowledgeService.moveChildrenOfMovedNode(user.getUserId(), _edition.getPath(), edition.getPath(), saveId);
      
      // switch current edition
      List<Edition> children = knowledgeService.findAllNodesWhoesCurrentEditionNeedToBeSwitched(saveId);
      for (Edition child : children) {
        knowledgeService.switchCurrentEdition(child.getNode().getNodeId(), child.getEditionId());
      }
    }
    
    knowledgeService.addEdition(edition);
    
    // switch to this edition
    knowledgeService.switchCurrentEdition(node.getNodeId(), edition.getEditionId());
    
    
    response.setObject(edition);
    
    return response.getResponse();
  }

  @PowerControl({"knowledge.map.view"})
  @RequestMapping(value="map/{nodeId}", method=RequestMethod.GET)
  public @ResponseBody Map<String, Object> getMap(@PathVariable("nodeId") String nodeId) {
    Response response = new Response("map");
    String path = nodeId.equals("root") ? "/" : knowledgeService.getCurrentEditionByNodeId(nodeId).getPath();
    
    response.setObject(knowledgeService.selectNodeTreeByPath(path));
    
    return response.getResponse();
  }
  
  @PowerControl({"knowledge.map.view"})
  @RequestMapping(value="node/{nodeId}", method=RequestMethod.GET)
  public @ResponseBody Map<String, Object> getNode(String loginToken, @PathVariable("nodeId") String nodeId, HttpServletRequest request) throws GeneralSecurityException, IOException {
    Response response = new Response("node");
    
    Node node = knowledgeService.getNodeById(nodeId);
    
    // add view
    User user = null;
    try {
      user = userService.getUserByLoginToken(loginToken);
    } catch (Exception e) {
    }
    String userId = user == null ? null : user.getUserId();
    
    String ip = request.getHeader("X-Real-IP");
    ip = ip != null ? ip : request.getRemoteAddr();
    try {
      knowledgeService.addNewNodeView(userId, nodeId, ip);
    } catch (DuplicateKeyException e) {
    }
    
    node.setViewsNumber(knowledgeService.getViewNumber(nodeId));
    response.setObject(node);
    
    return response.getResponse();
  }

  @RequestMapping(value="test-active", method=RequestMethod.GET)
  public @ResponseBody Map<String, Object> testActive() {
    Response response = new Response("status");
    response.setObject("ok");
    return response.getResponse();
  }
}
