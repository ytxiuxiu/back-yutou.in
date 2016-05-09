package in.yutou.site.common.auth.dao;

import org.springframework.stereotype.Repository;

import in.yutou.site.common.auth.domain.Group;

@Repository
public interface GroupDao {
  
  public Group getGroupById(String userId);
  
  public Group getGroupByName(String userName);

}
