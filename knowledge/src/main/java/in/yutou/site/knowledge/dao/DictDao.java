package in.yutou.site.knowledge.dao;

import java.util.List;

import in.yutou.site.knowledge.dict.domain.*;
import org.springframework.stereotype.Repository;

@Repository
public interface DictDao {
  
  public List<WordFrequency> getFrequenciesByWord(String word);

  public void addNewWord(DictResult word);

  public DictResult findWord(String word);

  public void updateWord(DictResult word);

  public void addExplanationGroup(ExplanationGroup explanationGroup);

  public void addExplanation(Explanation explanation);

  public void addExample(Example example);

}
