package in.yutou.site.common.auth.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.yutou.site.common.auth.AuthAES;
import in.yutou.site.common.auth.dao.UserDao;
import in.yutou.site.common.auth.domain.User;
import in.yutou.site.common.exception.BusinessException;

@Service
public class UserService {
  
  @Autowired
  private UserDao userDao;
  
  @Autowired
  private AuthAES aes;
  
  public User getUserById(String userId) {
    return userDao.getUserById(userId);
  }
  
  public User getUserByLoginToken(String loginToken) {
    User _user = null;
    try {
      _user = aes.decryptAuthInfo(loginToken);
    } catch (Exception e) {
      throw new BusinessException("invalid login token");
    }
    
    User user = getUserById(_user.getUserId());
    if (_user.getEmail().equals(user.getEmail())) {
      return user;
    } else {
      throw new BusinessException("invalid login token");
    }
  }
  
  public void register(User user) {
    user.setLastLogedInAt(new Date());
    userDao.insert(user);
  }

  public void login(User user) {
    user.setLastLogedInAt(new Date());
    userDao.login(user);
  }
  
}
