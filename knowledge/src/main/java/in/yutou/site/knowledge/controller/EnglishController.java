package in.yutou.site.knowledge.controller;

import in.yutou.site.knowledge.service.DictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
@RequestMapping("knowledge/english")
public class EnglishController {
  
  @Autowired
  private DictService dictService;

  @RequestMapping("")
  public @ResponseBody Map<String, Object> index() {

    return null;

  }
  
}
