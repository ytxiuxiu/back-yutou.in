package in.yutou.site.knowledge.dict.domain;

public class WordFrequency {

  private long frequencyId;
  
  private String wordId;
  
  private int rank;
  
  private int total;
  
  private String partOfSpeech;

  public long getFrequencyId() {
    return frequencyId;
  }

  public void setFrequencyId(long frequencyId) {
    this.frequencyId = frequencyId;
  }

  public String getWordId() {
    return wordId;
  }

  public void setWordId(String wordId) {
    this.wordId = wordId;
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
    return "WordFrequency [wordId=" + wordId + ", rank=" + rank + ", total=" + total
        + ", partOfSpeech=" + partOfSpeech + "]";
  }

}
