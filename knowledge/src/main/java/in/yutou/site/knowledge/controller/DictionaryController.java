package in.yutou.site.knowledge.controller;

import in.yutou.idgenerator.IdGenerator;
import in.yutou.site.common.auth.domain.User;
import in.yutou.site.common.auth.service.UserService;
import in.yutou.site.common.domain.Response;
import in.yutou.site.common.exception.BusinessException;
import in.yutou.site.knowledge.dict.domain.*;
import in.yutou.site.knowledge.service.DictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.Date;
import java.util.Map;

@Controller
@RequestMapping("knowledge/dict")
public class DictionaryController {
  
  @Autowired
  private DictService dictService;

  @Autowired
  private UserService userService;

  @RequestMapping("{keyword}")
  public @ResponseBody Map<String, Object> lookUp(String loginToken, @PathVariable("keyword") String keyword) throws IOException {
    Response response = new Response("result");

    DictResult _result = dictService.findWord(keyword);
    if (_result == null || _result.getFetchedAt() == null) {

      getAndSaveAWord(keyword, _result);

      _result = dictService.findWord(keyword);
    }

    // save finding history if logged in
    try {
      User user = userService.getUserByLoginToken(loginToken);
      dictService.addAFindingHistory(new FindingHistory(IdGenerator.generateId(), user, _result, new Date()));
    } catch (BusinessException e) {
    }

    _result.setFrequencies(dictService.getFrequenciesByWord(keyword));

    response.setObject(_result);
    return response.getResponse();
  }

  @RequestMapping("/finding-history")
  public @ResponseBody Map<String, Object> getFindingHistory(String loginToken, long wordId) {
    Response response = new Response("history");

    User user = userService.getUserByLoginToken(loginToken);
    response.setObject(dictService.getSearchingHistoryOfAWordOfAUser(user.getUserId(), wordId));

    return response.getResponse();
  }

  private void getAndSaveAWord(@PathVariable("keyword") String keyword, DictResult _result) throws IOException {
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
  }


}
