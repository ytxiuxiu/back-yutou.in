package in.yutou.site.common.auth;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import in.yutou.site.common.auth.domain.User;

public class AuthAES {
  
  private String key;
  
  private String initVector;
  
  private String prefix;
  
  public AuthAES() {}
  
  public AuthAES(String key, String initVector, String prefix) {
    this.key = key;
    this.initVector = initVector;
    this.prefix = prefix;
  }
  
  
  public String encryptAuthInfo(User user) throws InvalidKeyException, UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
    return encrypt(encrypt(encrypt("user.id=" + user.getUserId() + ":user.email=" + user.getEmail())));
  }
  
  public User decryptAuthInfo(String code) throws InvalidKeyException, UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException {
    String text = decrypt(decrypt(decrypt(code)));
    String[] infoArray = text.split(":");
    String userId = "";
    String email = "";
    for (int i = 0; i < infoArray.length; i++) {
      String[] infoItem = infoArray[i].split("=");
      if (i == 0) {
        userId = infoItem[1];
      } else if (i == 1) {
        email = infoItem[1];
      }
    }
    
    User user = new User();
    user.setUserId(userId);
    user.setEmail(email);
    return user;
  }
  
  private String encrypt(String value) throws UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
    value = prefix + value;
    IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
    SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");

    Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
    cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);

    byte[] encrypted = cipher.doFinal(value.getBytes());

    return Base64.getEncoder().encodeToString(encrypted);
  }

  private String decrypt(String encrypted) throws UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException, InvalidAlgorithmParameterException {
    IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
    SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");

    Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
    cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);

    byte[] original = cipher.doFinal(Base64.getDecoder().decode(encrypted));

    String result = new String(original);
    return result.substring(prefix.length(), result.length());
  }

  public String getKey() {
    return key;
  }

  public void setKey(String key) {
    this.key = key;
  }

  public String getInitVector() {
    return initVector;
  }

  public void setInitVector(String initVector) {
    this.initVector = initVector;
  }

  public String getPrefix() {
    return prefix;
  }

  public void setPrefix(String prefix) {
    this.prefix = prefix;
  }

}
