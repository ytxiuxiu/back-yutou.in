package in.yutou.site.knowledge.controller;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.junit.BeforeClass;
import org.junit.Test;

import in.yutou.site.knowledge.dict.domain.DictResult;

public class DictTest {
  
  private static DictionaryController dictionaryController;
  
  @BeforeClass
  public static void init() {
    dictionaryController = new DictionaryController();
  }
  
  @Test
  public void testSymbol() throws ClientProtocolException, IOException {
    DictResult result = (DictResult) dictionaryController.lookUp("normalization").get("result");
    
    assertEquals("[ ˌnɔ:məlaɪ'zeɪʃn ]", result.getSymbolBritish());
  }

}
