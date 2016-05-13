package in.yutou.site.knowledge.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import in.yutou.site.knowledge.dict.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.yutou.site.knowledge.dao.DictDao;

@Service
public class DictService {
  
  @Autowired
  private DictDao dao;

  public List<WordFrequency> getFrequenciesByWord(String word) {
    return dao.getFrequenciesByWord(word);
  }

  public List<FindingHistory> listAllFindingHistoriesByUserId(String userId) {
    return dao.listAllFindingHistoriesByUserId(userId);
  }

  public List<FindingHistory> getSearchingHistoryOfAWordOfAUser(String userId, long wordId) {
    Map<String, Object> params = new HashMap<String, Object>();
    params.put("userId", userId);
    params.put("wordId", wordId);
    return dao.getSearchingHistoryOfAWordOfAUser(params);
  }

  public void addNewWord(DictResult word) {
    dao.addNewWord(word);
  }

  public DictResult findWord(String word) {
    return dao.findWord(word);
  }

  public void updateWord(DictResult word) {
    dao.updateWord(word);
  }

  public void addExplanationGroup(ExplanationGroup explanationGroup) {
    dao.addExplanationGroup(explanationGroup);
  }

  public void addExplanation(Explanation explanation) {
    dao.addExplanation(explanation);
  }

  public void addExample(Example example) {
    dao.addExample(example);
  }

  public void addAFindingHistory(FindingHistory history) {
    dao.addAFindingHistory(history);
  }
  
}
