package in.yutou.site.knowledge.dict.domain;

import in.yutou.site.common.auth.domain.User;

import java.util.Date;

/**
 * Created by xiuxiu on 13/05/2016.
 */
public class FindingHistory {

    private long historyId;

    private User user;

    private DictResult word;

    private Date searchedAt;

    public FindingHistory() {
    }

    public FindingHistory(long historyId, User user, DictResult word, Date searchedAt) {
        this.historyId = historyId;
        this.user = user;
        this.word = word;
        this.searchedAt = searchedAt;
    }

    public long getHistoryId() {
        return historyId;
    }

    public void setHistoryId(long historyId) {
        this.historyId = historyId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public DictResult getWord() {
        return word;
    }

    public void setWord(DictResult word) {
        this.word = word;
    }

    public Date getSearchedAt() {
        return searchedAt;
    }

    public void setSearchedAt(Date searchedAt) {
        this.searchedAt = searchedAt;
    }

    @Override
    public String toString() {
        return "FindingHistory{" +
                "user=" + user +
                ", word=" + word +
                '}';
    }
}
