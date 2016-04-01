package in.yutou.site.common.auth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.yutou.site.common.auth.dao.GroupDao;
import in.yutou.site.common.auth.domain.Group;
import in.yutou.site.common.auth.domain.User;

@Service
public class GroupService {
  
  @Autowired
  private GroupDao groupDao;
  
  public Group getGroupById(String groupId) {
    return groupDao.getGroupById(groupId);
  }
  
  public Group getGroupByName(String groupName) {
    return groupDao.getGroupByName(groupName);
  }
}
