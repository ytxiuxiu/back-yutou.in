package in.yutou.site.knowledge.dict.domain;

public class WordFrequency {
  
  private String wordId;
  
  private String word;
  
  private int rank;
  
  private int total;
  
  private String partOfSpeech;

  public String getWordId() {
    return wordId;
  }

  public void setWordId(String wordId) {
    this.wordId = wordId;
  }

  public String getWord() {
    return word;
  }

  public void setWord(String word) {
    this.word = word;
  }

  public int getRank() {
    return rank;
  }

  public void setRank(int rank) {
    this.rank = rank;
  }

  public String getPartOfSpeech() {
    return partOfSpeech;
  }

  public void setPartOfSpeech(String partOfSpeech) {
    this.partOfSpeech = partOfSpeech;
  }

  public int getTotal() {
    return total;
  }

  public void setTotal(int total) {
    this.total = total;
  }

  @Override
  public String toString() {
    return "WordFrequency [wordId=" + wordId + ", word=" + word + ", rank=" + rank + ", total=" + total
        + ", partOfSpeech=" + partOfSpeech + "]";
  }

}
