package in.yutou.site.common.auth.controller;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import in.yutou.site.common.auth.AuthAES;
import in.yutou.site.common.auth.GoogleAuth;
import in.yutou.site.common.auth.domain.User;
import in.yutou.site.common.auth.service.GroupService;
import in.yutou.site.common.auth.service.UserService;
import in.yutou.site.common.domain.Response;

@Controller
@RequestMapping("auth")
public class AuthController {
  
  @Autowired
  private UserService userService;
  
  @Autowired
  private GroupService groupService;
  
  @Autowired
  private GoogleAuth googleAuth;
  
  @Autowired
  private AuthAES aes;
  
  /**
   * Process user login. This method will verify user login information via Google ID Token and 
   * store user info to database and http session.
   * POST /auth
   * 
   * @param idToken POST Parameter: Google ID Token
   * @param session Http Session
   * @return
   * @throws GeneralSecurityException
   * @throws IOException
   */
  @RequestMapping(value="login", method=RequestMethod.POST)
  public @ResponseBody Map<String, Object> login(String idToken) throws GeneralSecurityException, IOException {
    Response response = new Response("user");
    
    // Verify Google ID Token
    User user = googleAuth.getUser(idToken);
      
    // Check and get/save user info in database
    User _user = userService.getUserById(user.getUserId());
    
    if (_user == null) {
      // if not register
      userService.register(user);
      _user = userService.getUserById(user.getUserId());
    } else {
      userService.login(_user);
    }
    
    // Generate login token
    response.getObjects().put("loginToken", aes.encryptAuthInfo(_user));
    response.setObject(_user);
    
    return response.getResponse();
    
  }
  
  @RequestMapping(value="", method=RequestMethod.POST)
  public @ResponseBody Map<String, Object> auth(String loginToken) throws GeneralSecurityException, IOException {
    Response response = new Response("user");
    
    User user = userService.getUserByLoginToken(loginToken);
    userService.login(user);
    response.setObject(user);
    
    return response.getResponse();
  }
  
  @RequestMapping("no-login-user")
  public @ResponseBody Map<String, Object> getNoLoginUser() {
    Response response = new Response("user");
    
    User user = new User();
    user.setGroup(groupService.getGroupByName("no-login"));
    response.setObject(user);
    
    return response.getResponse();
  }
  
  
}
