package com.leo.comic.bean;

import java.util.List;
import java.util.Map;

public class ComicMeanBean {
    private String comicName;
    private String comicUrl;
    private String comicCover;
    private String comicAuthor;
    private String comicIntro;
    private String comicLastUpdate;
    private String comicLastUpdateUrl;
    private String comicLastUpdateChapter;
    private String comicLastUpdateChapterUrl;
    private Map<String, String> comicLists;

    public ComicMeanBean(String comicName, String comicUrl, String comicCover, String comicAuthor, String comicIntro, String comicLastUpdate, String comicLastUpdateUrl, String comicLastUpdateChapter, String comicLastUpdateChapterUrl, List<String> comicLists) {
        this.comicName = comicName;
        this.comicUrl = comicUrl;
        this.comicCover = comicCover;
        this.comicAuthor = comicAuthor;
        this.comicIntro = comicIntro;
        this.comicLastUpdate = comicLastUpdate;
    }
    public ComicMeanBean() {
    }

    @Override
    public String toString() {
        return "ComicMeanBean{" +
                "comicName='" + comicName + '\'' +
                ", comicUrl='" + comicUrl + '\'' +
                ", comicCover='" + comicCover + '\'' +
                ", comicAuthor='" + comicAuthor + '\'' +
                ", comicIntro='" + comicIntro + '\'' +
                ", comicLastUpdate='" + comicLastUpdate + '\'' +
                ", comicLastUpdateUrl='" + comicLastUpdateUrl + '\'' +
                ", comicLastUpdateChapter='" + comicLastUpdateChapter + '\'' +
                ", comicLastUpdateChapterUrl='" + comicLastUpdateChapterUrl + '\'' +
                ", comicLists=" + comicLists +
                '}';
    }

    public String getComicName() {
        return comicName;
    }

    public void setComicName(String comicName) {
        this.comicName = comicName;
    }

    public String getComicUrl() {
        return comicUrl;
    }

    public void setComicUrl(String comicUrl) {
        this.comicUrl = comicUrl;
    }

    public String getComicCover() {
        return comicCover;
    }

    public void setComicCover(String comicCover) {
        this.comicCover = comicCover;
    }

    public String getComicAuthor() {
        return comicAuthor;
    }

    public void setComicAuthor(String comicAuthor) {
        this.comicAuthor = comicAuthor;
    }

    public String getComicIntro() {
        return comicIntro;
    }

    public void setComicIntro(String comicIntro) {
        this.comicIntro = comicIntro;
    }

    public String getComicLastUpdate() {
        return comicLastUpdate;
    }

    public void setComicLastUpdate(String comicLastUpdate) {
        this.comicLastUpdate = comicLastUpdate;
    }

    public String getComicLastUpdateUrl() {
        return comicLastUpdateUrl;
    }

    public void setComicLastUpdateUrl(String comicLastUpdateUrl) {
        this.comicLastUpdateUrl = comicLastUpdateUrl;
    }

    public String getComicLastUpdateChapter() {
        return comicLastUpdateChapter;
    }

    public void setComicLastUpdateChapter(String comicLastUpdateChapter) {
        this.comicLastUpdateChapter = comicLastUpdateChapter;
    }

    public String getComicLastUpdateChapterUrl() {
        return comicLastUpdateChapterUrl;
    }

    public void setComicLastUpdateChapterUrl(String comicLastUpdateChapterUrl) {
        this.comicLastUpdateChapterUrl = comicLastUpdateChapterUrl;
    }

    public Map<String, String> getComicLists() {
        return comicLists;
    }

    public void setComicLists(Map<String, String> comicLists) {
        this.comicLists = comicLists;
    }
}
