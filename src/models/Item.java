package models;

public class Item implements java.io.Serializable {
    private String title;
    private String publisher;
    private String wikiLink;
    private String imgLink;
    private String category;
    private String subCategory;
    private Boolean done = false;

    private Ratings ratings;

    public Item() {
        ratings = new Ratings();
    }

    public Item(String title, String publisher, String wikiLink, String imgLink, String category, String subCategory, int ratingMax, int ratingCrt, int ratingPrs, int ratingNumber, Boolean done) {
        this.title = title;
        this.publisher = publisher;
        this.wikiLink = wikiLink;
        this.imgLink = imgLink;
        this.category = category;
        this.subCategory = subCategory;
        this.done = done;

        this.ratings = new Ratings(ratingMax, ratingCrt, ratingPrs, ratingNumber);
    }

    public Item(String title, String publisher, String wikiLink, String imgLink, String category, String subCategory, int ratingMax, int ratingCrt, int ratingPrs, int ratingNumber) {
        this.title = title;
        this.publisher = publisher;
        this.wikiLink = wikiLink;
        this.imgLink = imgLink;
        this.category = category;
        this.subCategory = subCategory;

        this.ratings = new Ratings(ratingMax, ratingCrt, ratingPrs, ratingNumber);
    }

    public Item(Item tmp) {
        this.title = tmp.getTitle();
        this.publisher = tmp.getPublisher();
        this.wikiLink = tmp.getWikiLink();
        this.imgLink = tmp.getImgLink();
        this.category = tmp.getCategory();
        this.subCategory = tmp.getSubCategory();
        this.done = tmp.getDone();

        this.ratings = new Ratings(tmp.getRatings());
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getWikiLink() {
        return wikiLink;
    }

    public void setWikiLink(String wikiLink) {
        this.wikiLink = wikiLink;
    }

    public String getImgLink() {
        return imgLink;
    }

    public void setImgLink(String imgLink) {
        this.imgLink = imgLink;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(String subCategory) {
        this.subCategory = subCategory;
    }

    public Ratings getRatings() {
        return ratings;
    }

    public void setRatings(Ratings ratings) {
        this.ratings = ratings;
    }

    public Boolean getDone() {
        return done;
    }

    public void setDone(Boolean done) {
        this.done = done;
    }

    @Override
    public String toString() {
        return "models.Item{" +
                "title='" + title + '\'' +
                ", publisher='" + publisher + '\'' +
                ", wikiLink='" + wikiLink + '\'' +
                ", imgLink='" + imgLink + '\'' +
                ", category='" + category + '\'' +
                ", subCategory='" + subCategory + '\'' +
                ", done=" + done +
                ", ratings=" + ratings +
                '}';
    }
}
