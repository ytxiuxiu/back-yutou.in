package in.yutou.site.common.auth.controller;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;

import in.yutou.site.common.auth.GoogleAuth;
import in.yutou.site.common.auth.service.UserService;
import in.yutou.site.common.domain.Response;
import in.yutou.site.common.domain.User;
import in.yutou.site.exception.BusinessException;

@Controller
@RequestMapping("auth")
public class AuthController {
  
  @Autowired
  private UserService userService;
  
  @Autowired
  private GoogleAuth googleAuth;
  
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
  @RequestMapping(value="", method=RequestMethod.POST)
  public @ResponseBody Map<String, Object> auth(String idToken) throws GeneralSecurityException, IOException {
    Response response = new Response("user");
    
    // Verify Google ID Token
    GoogleIdToken googleIdToken = googleAuth.verifyGooleTokenId(idToken);
    if (googleIdToken != null) {
      User user = googleAuth.getUserInfoFromGoogle(googleIdToken);
      
      // Check and get/save user info in database
      User _user = userService.getUserById(user.getUserId());
      
      if (_user == null) {
        // if not register
        userService.register(user);
        _user = userService.getUserById(user.getUserId());
      } else {
        userService.login(_user);
      }

      response.setObject(_user);
      
    } else {
      throw new BusinessException("Invalid ID Token");
    }
    
    return response.getResponse();
    
  }
  
  
}
