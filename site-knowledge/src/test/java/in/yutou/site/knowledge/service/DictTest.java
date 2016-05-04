package in.yutou.site.knowledge.service;

import org.junit.BeforeClass;
import org.junit.Test;

import in.yutou.site.knowledge.dict.domain.WordFrequency;

public class DictTest {
  
  private static DictService service;
  
  @BeforeClass
  public void init() {
    service = new DictService();
  }
  
  @Test
  public void testGetFrequencyByWord() {
    WordFrequency frequency = service.getFrequencyByWord("a");
    System.out.println(frequency);
  }

}
