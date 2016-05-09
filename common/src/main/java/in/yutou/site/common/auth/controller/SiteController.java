package in.yutou.site.common.auth.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import in.yutou.site.common.domain.Response;
import in.yutou.site.common.service.SiteStatusService;

@Controller
@RequestMapping("site")
public class SiteController {
  
  @Autowired
  SiteStatusService siteStatusService;
  
  @RequestMapping("status")
  public @ResponseBody Map<String, Object> siteStatus() {
    Response response = new Response("status");
    
    Map<String, String> status = new HashMap<String, String>();
    List<Map<String, String>> properties = siteStatusService.getAllProperties();
    for (Map<String, String> property : properties) {
      status.put(property.get("name"), property.get("value"));
    }
    response.setObject(status);
    
    return response.getResponse();
  }
  
  
}
