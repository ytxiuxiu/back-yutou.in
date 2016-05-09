package in.yutou.site.common.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.yutou.site.common.dao.SiteStatusDao;

@Service
public class SiteStatusService {
  
  @Autowired
  private SiteStatusDao siteStatusDao;
  
  public List<Map<String, String>> getAllProperties() {
    return siteStatusDao.getAllProperties();
  }
  
}
