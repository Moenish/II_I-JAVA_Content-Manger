package models;

public class Book extends Item implements java.io.Serializable {
    private String author;
    private int pagesTotal = 0;
    private int pagesRead = 0;
    private int chaptersTotal = 0;
    private int chaptersRead = 0;

    public Book() {
        super();
    }

    public Book(String title, String publisher, String wikiLink, String imgLink, String category, String subCategory, int ratingMax, int ratingCrt, int ratingPrs, int ratingNumber, Boolean done, String author, int pagesTotal, int pagesRead, int chaptersTotal, int chaptersRead) {
        super(title, publisher, wikiLink, imgLink, category, subCategory, ratingMax, ratingCrt, ratingPrs, ratingNumber, done);
        this.author = author;
        this.pagesTotal = pagesTotal;
        this.pagesRead = pagesRead;
        this.chaptersTotal = chaptersTotal;
        this.chaptersRead = chaptersRead;
        evaluate();
    }

    public Book(String title, String publisher, String wikiLink, String imgLink, String category, String subCategory, int ratingMax, int ratingCrt, int ratingPrs, int ratingNumber, String author, int pagesTotal, int pagesRead, int chaptersTotal, int chaptersRead) {
        super(title, publisher, wikiLink, imgLink, category, subCategory, ratingMax, ratingCrt, ratingPrs, ratingNumber);
        this.author = author;
        this.pagesTotal = pagesTotal;
        this.pagesRead = pagesRead;
        this.chaptersTotal = chaptersTotal;
        this.chaptersRead = chaptersRead;
        evaluate();
    }

    public Book(Item tmp, String author, int pagesTotal, int pagesRead, int chaptersTotal, int chaptersRead) {
        super(tmp);
        this.author = author;
        this.pagesTotal = pagesTotal;
        this.pagesRead = pagesRead;
        this.chaptersTotal = chaptersTotal;
        this.chaptersRead = chaptersRead;
        evaluate();
    }

    public Book(Book tmp) {
        super(tmp);
        this.author = tmp.getAuthor();
        this.pagesTotal = tmp.getPagesTotal();
        this.pagesRead = tmp.getPagesRead();
        this.chaptersTotal = tmp.getChaptersTotal();
        this.chaptersRead = tmp.getChaptersRead();
        evaluate();
    }

    //Might delete later
    public Book(Item tmp) {
        super(tmp);
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getPagesTotal() {
        return pagesTotal;
    }

    public void setPagesTotal(int pagesTotal) {
        this.pagesTotal = pagesTotal;
        evaluate();
    }

    public int getPagesRead() {
        return pagesRead;
    }

    public void setPagesRead(int pagesRead) {
        this.pagesRead = pagesRead;
        evaluate();
    }

    public int getChaptersTotal() {
        return chaptersTotal;
    }

    public void setChaptersTotal(int chaptersTotal) {
        this.chaptersTotal = chaptersTotal;
        evaluate();
    }

    public int getChaptersRead() {
        return chaptersRead;
    }

    public void setChaptersRead(int chaptersRead) {
        this.chaptersRead = chaptersRead;
        evaluate();
    }

    private void evaluate() {
        setDone(chaptersRead >= chaptersTotal && pagesRead >= pagesTotal && pagesTotal > 0 && chaptersTotal > 0);
    }

    @Override
    public String toString() {
        return "models.Book{" +
                super.toString() +
                ", author='" + author + '\'' +
                ", pagesTotal=" + pagesTotal +
                ", pagesRead=" + pagesRead +
                ", chaptersTotal=" + chaptersTotal +
                ", chaptersRead=" + chaptersRead +
                '}';
    }
}
