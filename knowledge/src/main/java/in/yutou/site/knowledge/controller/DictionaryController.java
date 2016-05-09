package in.yutou.site.knowledge.controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.impl.client.HttpClients;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import in.yutou.site.common.domain.Response;
import in.yutou.site.common.exception.BusinessException;
import in.yutou.site.knowledge.dict.domain.DictResult;
import in.yutou.site.knowledge.dict.domain.Example;
import in.yutou.site.knowledge.dict.domain.Explanation;
import in.yutou.site.knowledge.dict.domain.ExplanationGroup;
import in.yutou.site.knowledge.service.DictService;
import in.yutou.utils.webget.WebGet;

@Controller
@RequestMapping("knowledge/dict")
public class DictionaryController {
  
  @Autowired
  private DictService dictService;

  @RequestMapping("{keyword}")
  public @ResponseBody Map<String, Object> lookUp(@PathVariable("keyword") String keyword) throws ClientProtocolException, IOException {
    Response response = new Response("result");
    WebGet webGet = new WebGet(HttpClients.createDefault());
    
    // get html doc from iciba.com
    Document doc = webGet.getDocument("http://www.iciba.com/" + URLEncoder.encode(keyword, "UTF-8"));
    // parse html doc
    DictResult result = new DictResult();

    getBasicInfo(doc, result);
    
    if (!checkIfHasCollins(doc)) {
      response.setObject(result);
      return response.getResponse();
    }
    
    ExplanationGroup currentGroup = null;
    currentGroup = parseGroups(doc, result, currentGroup);
    
    response.setObject(result);
    return response.getResponse();
  }

  private ExplanationGroup parseGroups(Document doc, DictResult result, ExplanationGroup currentGroup) {
    for (Element section : doc.select(".collins-section").get(0).children()) {
      currentGroup = parseOneGroup(result, currentGroup, section);
    }
    return currentGroup;
  }

  private ExplanationGroup parseOneGroup(DictResult result, ExplanationGroup currentGroup, Element section) {
    String type = section.className();
    if (type.contains("section-h")) { // explanation group
      currentGroup = createGroup(result, section);
    } else if (type.contains("section-prep")) { // explanation
      if (result.getExplanationGroups().size() == 0) { // create the default group if no group
        currentGroup = createDefaultGroup(result);
      }
      parseExplanation(currentGroup, section);
      parseExamples(currentGroup, section);
    }
    return currentGroup;
  }

  private ExplanationGroup createDefaultGroup(DictResult result) {
    ExplanationGroup currentGroup;
    result.getExplanationGroups().add(new ExplanationGroup("default", null));
    currentGroup = result.getExplanationGroups().get(0);
    return currentGroup;
  }

  private ExplanationGroup createGroup(DictResult result, Element section) {
    ExplanationGroup currentGroup;
    Element groupNameElement = section.select("span.family-english").get(1);
    String groupNameChinese = groupNameElement.nextSibling().outerHtml().trim();
    result.getExplanationGroups().add(new ExplanationGroup(groupNameElement.text(), groupNameChinese));
    currentGroup = result.getExplanationGroups().get(result.getExplanationGroups().size() - 1);
    return currentGroup;
  }

  private void parseExamples(ExplanationGroup currentGroup, Element section) {
    Elements exampleElements = section.select("div.prep-order > div.text-sentence > div.sentence-item");
    for (Element example : exampleElements) {
      parseOneExample(currentGroup, example);
    }
  }

  private void parseOneExample(ExplanationGroup currentGroup, Element example) {
    Elements exampleContentElements = example.select("p");
    Elements audioElements = exampleContentElements.get(0).select("i");
    String audio = audioElements.size() >= 1 ? audioElements.get(0).attr("onmouseover") : null;
    currentGroup.getExplanations()
      .get(currentGroup.getExplanations().size() - 1)
      .getExamples().add(new Example(exampleContentElements.get(0).text(), 
          exampleContentElements.get(1).text(), 
          audio.replace("displayAudio('", "").replace("')", "")));
  }

  private void parseExplanation(ExplanationGroup currentGroup, Element section) {
    Elements explanationElements = section.select("div.prep-order > p.size-chinese > span");
    if (explanationElements.size() == 3) {
      Explanation explanation = new Explanation(explanationElements.get(0).text(), 
          explanationElements.get(2).text(), 
          explanationElements.get(1).text());
      currentGroup.getExplanations().add(explanation);
    }
  }

  private void getBasicInfo(Document doc, DictResult result) {
    String keyword = doc.select("h1.keyword").get(0).text();
    result.setKeyword(keyword);
    if (doc.select("div.base-speak > i").size() == 2) {
      result.setSymbolBritish(doc.select("div.base-speak > span").get(0).text().replace("英", "").trim());
      result.setAudioBritish(doc.select("div.base-speak > i").get(0).attr("onmouseover").
          replace("displayAudio('", "").replace("')", ""));
      result.setSymbolAmerican(doc.select("div.base-speak > span").get(1).text().replace("美", "").trim());
      result.setAudioAmerican(doc.select("div.base-speak > i").get(1).attr("onmouseover").
          replace("displayAudio('", "").replace("')", ""));
    }
    
    result.setFrequencies(dictService.getFrequenciesByWord(keyword));
  }

  private boolean checkIfHasCollins(Document doc) {
    boolean hasCollins = false;
    Elements dictElements = doc.select("div.info-article ul.article-list li.current");
    for (Element dictElement : dictElements) {
      if (dictElement.text().equals("柯林斯高阶英汉双解学习词典")) {
        hasCollins = true;
      }
    }
    return hasCollins;
  }
  
}
