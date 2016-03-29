package in.yutou.site.common.auth.dao;

import in.yutou.site.common.domain.User;

public interface UserDao {
  
  public User getUserById(String userId);
  
  
  public void insert(User user);


  public void login(User user);

}
