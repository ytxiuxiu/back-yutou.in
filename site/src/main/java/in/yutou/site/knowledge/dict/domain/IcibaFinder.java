package in.yutou.site.knowledge.dict.domain;

import in.yutou.site.common.exception.BusinessException;
import in.yutou.utils.webget.WebGet;
import org.apache.http.client.HttpResponseException;
import org.apache.http.impl.client.HttpClients;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.IOException;
import java.net.URLEncoder;

/**
 * Created by xiuxiu on 9/05/2016.
 */
public class IcibaFinder {

    public DictResult find(@PathVariable("keyword") String keyword) throws IOException {

        // get html doc from iciba.com
        WebGet webGet = new WebGet(HttpClients.createDefault());
        Document doc = null;
        try {
            doc = webGet.getDocument("http://www.iciba.com/" + URLEncoder.encode(keyword, "UTF-8"));
        } catch (HttpResponseException e) {
            throw new BusinessException("Word not found");
        }

        // parse html doc
        DictResult result = new DictResult();
        parseBasicInfo(doc, result);
        parseGroups(doc, result);

        return result;
    }

    private void parseGroups(Document doc, DictResult result) {
        ExplanationGroup currentGroup = null;
        if (doc.select(".collins-section") != null && doc.select(".collins-section").size() != 0) {
            for (Element section : doc.select(".collins-section").get(0).children()) {
                currentGroup = parseOneGroup(result, currentGroup, section);
            }
        }
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
        result.getExplanationGroups().add(new ExplanationGroup("default", ""));
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

    private void parseBasicInfo(Document doc, DictResult result) {
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
    }

}
