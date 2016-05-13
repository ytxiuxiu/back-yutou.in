package in.yutou.site.knowledge.dao;

import java.util.List;
import java.util.Map;

import in.yutou.site.knowledge.dict.domain.*;
import org.springframework.stereotype.Repository;

@Repository
public interface DictDao {
  
  public List<WordFrequency> getFrequenciesByWord(String word);

  public List<FindingHistory> listAllFindingHistoriesByUserId(String userId);

  public List<FindingHistory> getSearchingHistoryOfAWordOfAUser(Map<String, Object> params);

  public void addNewWord(DictResult word);

  public DictResult findWord(String word);

  public void updateWord(DictResult word);

  public void addExplanationGroup(ExplanationGroup explanationGroup);

  public void addExplanation(Explanation explanation);

  public void addExample(Example example);

  public void addAFindingHistory(FindingHistory history);

}
