package in.yutou.site.knowledge.controller;

import java.io.IOException;
import java.sql.DataTruncation;
import java.util.Date;
import java.util.Map;


import in.yutou.idgenerator.IdGenerator;
import in.yutou.site.knowledge.dict.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import in.yutou.site.common.domain.Response;
import in.yutou.site.knowledge.service.DictService;

@Controller
@RequestMapping("knowledge/dict")
public class DictionaryController {
  
  @Autowired
  private DictService dictService;

  @RequestMapping("{keyword}")
  public @ResponseBody Map<String, Object> lookUp(@PathVariable("keyword") String keyword) throws IOException {
    Response response = new Response("result");

    DictResult _result = dictService.findWord(keyword);
    if (_result.getFetchedAt() == null) {

      IcibaFinder finder = new IcibaFinder();
      DictResult result = finder.find(keyword);

      result.setFetchedAt(new Date());
      if (_result == null) {
        result.setWordId(IdGenerator.generateId());
        dictService.addNewWord(result);
      } else {
        result.setWordId(_result.getWordId());
        dictService.updateWord(result);
      }

      for (ExplanationGroup explanationGroup : result.getExplanationGroups()) {
        System.out.println(explanationGroup);
        explanationGroup.setGroupId(IdGenerator.generateId());
        explanationGroup.setWord(result);
        dictService.addExplanationGroup(explanationGroup);

        for (Explanation explanation : explanationGroup.getExplanations()) {
          explanation.setExplanationId(IdGenerator.generateId());
          explanation.setExplanationGroup(explanationGroup);
          dictService.addExplanation(explanation);

          for (Example example : explanation.getExamples()) {
            example.setExampleId(IdGenerator.generateId());
            example.setExplanation(explanation);
            dictService.addExample(example);
          }
        }
      }

      _result = dictService.findWord(keyword);
    }

    _result.setFrequencies(dictService.getFrequenciesByWord(keyword));

    response.setObject(_result);
    return response.getResponse();
  }


  
}
