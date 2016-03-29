package in.yutou.site.common.auth.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import in.yutou.site.common.auth.dao.UserDao;
import in.yutou.site.common.domain.User;

@Service("userService")
public class UserService {
  
  @Autowired
  private UserDao userDao;
  
  public User getUserById(String userId) {
    return userDao.getUserById(userId);
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
