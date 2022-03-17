package models;

public class Movie extends Item implements java.io.Serializable {
    private String director;
    private int minutesTotal = 0;
    private int minutesWatched = 0;

    public Movie() {
        super();
    }

    public Movie(String title, String publisher, String wikiLink, String imgLink, String category, String subCategory, int ratingMax, int ratingCrt, int ratingPrs, int ratingNumber, Boolean done, String director, int minutesTotal, int minutesWatched) {
        super(title, publisher, wikiLink, imgLink, category, subCategory, ratingMax, ratingCrt, ratingPrs, ratingNumber, done);
        this.director = director;
        this.minutesTotal = minutesTotal;
        this.minutesWatched = minutesWatched;
        evaluate();
    }

    public Movie(String title, String publisher, String wikiLink, String imgLink, String category, String subCategory, int ratingMax, int ratingCrt, int ratingPrs, int ratingNumber, String director, int minutesTotal, int minutesWatched) {
        super(title, publisher, wikiLink, imgLink, category, subCategory, ratingMax, ratingCrt, ratingPrs, ratingNumber);
        this.director = director;
        this.minutesTotal = minutesTotal;
        this.minutesWatched = minutesWatched;
        evaluate();
    }

    public Movie(Item tmp, String director, int minutesTotal, int minutesWatched) {
        super(tmp);
        this.director = director;
        this.minutesTotal = minutesTotal;
        this.minutesWatched = minutesWatched;
        evaluate();
    }

    public Movie(Movie tmp) {
        super(tmp);
        this.director = tmp.getDirector();
        this.minutesTotal = tmp.getMinutesTotal();
        this.minutesWatched = tmp.getMinutesWatched();
        evaluate();
    }

    //Might delete later
    public Movie(Item tmp) {
        super(tmp);
        evaluate();
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public int getMinutesTotal() {
        return minutesTotal;
    }

    public void setMinutesTotal(int minutesTotal) {
        this.minutesTotal = minutesTotal;
        evaluate();
    }

    public int getMinutesWatched() {
        return minutesWatched;
    }

    public void setMinutesWatched(int minutesWatched) {
        this.minutesWatched = minutesWatched;
        evaluate();
    }

    private void evaluate() {
        setDone(minutesWatched >= minutesTotal && minutesTotal > 0);
    }

    @Override
    public String toString() {
       return "models.Movie{" +
                super.toString() +
                ", director='" + director + '\'' +
                ", minutesTotal=" + minutesTotal +
                ", minutesWatched=" + minutesWatched +
                '}';
    }
}
