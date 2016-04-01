package in.yutou.site.common.auth.domain;

import java.util.Date;

public class User {
  
  private String userId;
  
  private Group group;
  
  private String email;
  
  private String familyName;
  
  private String givenName;
  
  private String pictureUrl;
  
  private Date registeredAt;
  
  private Date lastLogedInAt;
  
  public User() {
    
  }

  public User(String userId, String email, String familyName, String givenName, String pictureUrl) {
    super();
    this.userId = userId;
    this.email = email;
    this.familyName = familyName;
    this.givenName = givenName;
    this.pictureUrl = pictureUrl;
    this.registeredAt = new Date();
  }

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public Group getGroup() {
    return group;
  }

  public void setGroup(Group group) {
    this.group = group;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getFamilyName() {
    return familyName;
  }

  public void setFamilyName(String familyName) {
    this.familyName = familyName;
  }

  public String getGivenName() {
    return givenName;
  }

  public void setGivenName(String givenName) {
    this.givenName = givenName;
  }

  public String getPictureUrl() {
    return pictureUrl;
  }

  public void setPictureUrl(String pictureUrl) {
    this.pictureUrl = pictureUrl;
  }

  public Date getRegisteredAt() {
    return registeredAt;
  }

  public void setRegisteredAt(Date registeredAt) {
    this.registeredAt = registeredAt;
  }

  public Date getLastLogedInAt() {
    return lastLogedInAt;
  }

  public void setLastLogedInAt(Date lastLogedInAt) {
    this.lastLogedInAt = lastLogedInAt;
  }

  @Override
  public String toString() {
    return "User [userId=" + userId + ", group=" + group + ", email=" + email + ", familyName=" + familyName
        + ", givenName=" + givenName + ", pictureUrl=" + pictureUrl + ", registeredAt=" + registeredAt
        + ", lastLogedInAt=" + lastLogedInAt + "]";
  }

}
