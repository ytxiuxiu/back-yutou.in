package in.yutou.site.knowledge.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import in.yutou.site.knowledge.dict.domain.WordFrequency;

@Repository
public interface DictDao {
  
  public List<WordFrequency> getFrequenciesByWord(String word);

}
