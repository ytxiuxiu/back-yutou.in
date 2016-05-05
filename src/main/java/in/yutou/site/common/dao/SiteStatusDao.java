package in.yutou.site.common.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository
public interface SiteStatusDao {
  
  public List<Map<String, String>> getAllProperties();

}
