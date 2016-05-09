package in.yutou.site.common.auth.dao;

import org.springframework.stereotype.Repository;

import in.yutou.site.common.auth.domain.User;

@Repository
public interface UserDao {
  
  public User getUserById(String userId);
  
  
  public void insert(User user);


  public void login(User user);

}
