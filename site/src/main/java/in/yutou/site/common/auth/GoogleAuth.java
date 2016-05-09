package in.yutou.site.common.auth;

import java.io.IOException;
import java.nio.file.attribute.UserPrincipalLookupService;
import java.security.GeneralSecurityException;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;

import in.yutou.site.common.auth.domain.User;
import in.yutou.site.common.exception.BusinessException;

@Component
public class GoogleAuth {
  
  @Value("${google.api.clientId}")
  private String clientId;
  
  public User getUser(String idToken) throws GeneralSecurityException, IOException {
    GoogleIdToken googleIdToken = verifyGooleTokenId(idToken);
    if (googleIdToken != null) {
      User user = getUserInfoFromGoogle(googleIdToken);
      
      return user;
      
    } else {
      throw new BusinessException("Invalid ID Token");
    }
  }
  
  public String getUserId(String idToken) throws GeneralSecurityException, IOException {
    return getUser(idToken).getUserId();
  }
  
  /**
   * Check if a id token is valid
   * @param idToken The id token string from Google Login API
   * @return If it is null, this token id is invalid. Or return a item which contain the user's info
   * @throws GeneralSecurityException
   * @throws IOException
   */
  public GoogleIdToken verifyGooleTokenId(String idToken) throws GeneralSecurityException, IOException {
    HttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
    JsonFactory jsonFactory = JacksonFactory.getDefaultInstance();
    GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(httpTransport, jsonFactory)
        .setAudience(Arrays.asList(clientId))
        .build();
    
    return verifier.verify(idToken);
  }
  
  /**
   * Get user's info from a token id
   * @param googleIdToken
   * @return
   */
  public User getUserInfoFromGoogle(GoogleIdToken googleIdToken) {
    Payload payload = googleIdToken.getPayload();

    // Get user identifier
    String userId = payload.getSubject();

    // Get profile information from payload
    String email = payload.getEmail();
    boolean emailVerified = Boolean.valueOf(payload.getEmailVerified());
//    String name = (String) payload.get("name");
    String pictureUrl = (String) payload.get("picture");
//    String locale = (String) payload.get("locale");
    String familyName = (String) payload.get("family_name");
    String givenName = (String) payload.get("given_name");
    
    return new User(userId, email, familyName, givenName, pictureUrl);
  }

}
