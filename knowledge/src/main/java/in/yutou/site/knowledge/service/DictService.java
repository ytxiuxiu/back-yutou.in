package in.yutou.site.knowledge.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.yutou.site.knowledge.dao.DictDao;
import in.yutou.site.knowledge.dict.domain.WordFrequency;

@Service
public class DictService {
  
  @Autowired
  private DictDao dao;

  public List<WordFrequency> getFrequenciesByWord(String word) {
    return dao.getFrequenciesByWord(word);
  }
  
}
