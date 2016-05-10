package in.yutou.site.knowledge.service;

import java.util.List;

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
  
}
