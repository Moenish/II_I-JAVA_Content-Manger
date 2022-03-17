package models;

public class Ratings implements java.io.Serializable {
    private int ratingMax = 0;
    private int ratingCrt = 0;
    private int ratingPrs = 0;
    private int ratingNbr = 0;

    public Ratings() {
        super();
    }

    public Ratings(int ratingMax, int ratingCrt, int ratingPrs, int ratingNbr) {
        this.ratingMax = ratingMax;
        this.ratingCrt = ratingCrt;
        this.ratingPrs = ratingPrs;
        this.ratingNbr = ratingNbr;
    }

    public Ratings(Ratings tmp) {
        this.ratingMax = tmp.getRatingMax();
        this.ratingCrt = tmp.getRatingCrt();
        this.ratingPrs = tmp.getRatingPrs();
        this.ratingNbr = tmp.getRatingNbr();
    }

    public int getRatingMax() {
        return ratingMax;
    }

    public void setRatingMax(int ratingMax) {
        this.ratingMax = ratingMax;
    }

    public int getRatingCrt() {
        return ratingCrt;
    }

    public void setRatingCrt(int ratingCrt) {
        this.ratingCrt = ratingCrt;
    }

    public int getRatingPrs() {
        return ratingPrs;
    }

    public void setRatingPrs(int ratingPrs) {
        this.ratingPrs = ratingPrs;
    }

    public int getRatingNbr() {
        return ratingNbr;
    }

    public void setRatingNbr(int ratingNbr) {
        this.ratingNbr = ratingNbr;
    }

    @Override
    public String toString() {
        return "models.Ratings{" +
                "ratingMax=" + ratingMax +
                ", ratingCrt=" + ratingCrt +
                ", ratingPrs=" + ratingPrs +
                ", ratingNbr=" + ratingNbr +
                '}';
    }
}
