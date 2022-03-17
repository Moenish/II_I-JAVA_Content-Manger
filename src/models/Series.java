package models;

public class Series extends Item implements java.io.Serializable {
    private String director;
    private int episodesTotal = 0;
    private int episodesWatched = 0;
    private int seasonsTotal = 0;
    private int seasonsWatched = 0;

    public Series() {
        super();
    }

    public Series(String title, String publisher, String wikiLink, String imgLink, String category, String subCategory, int ratingMax, int ratingCrt, int ratingPrs, int ratingNumber, Boolean done, String director, int episodesTotal, int episodesWatched, int seasonsTotal, int seasonsWatched) {
        super(title, publisher, wikiLink, imgLink, category, subCategory, ratingMax, ratingCrt, ratingPrs, ratingNumber, done);
        this.director = director;
        this.episodesTotal = episodesTotal;
        this.episodesWatched = episodesWatched;
        this.seasonsTotal = seasonsTotal;
        this.seasonsWatched = seasonsWatched;
        evaluate();
    }

    public Series(String title, String publisher, String wikiLink, String imgLink, String category, String subCategory, int ratingMax, int ratingCrt, int ratingPrs, int ratingNumber, String director, int episodesTotal, int episodesWatched, int seasonsTotal, int seasonsWatched) {
        super(title, publisher, wikiLink, imgLink, category, subCategory, ratingMax, ratingCrt, ratingPrs, ratingNumber);
        this.director = director;
        this.episodesTotal = episodesTotal;
        this.episodesWatched = episodesWatched;
        this.seasonsTotal = seasonsTotal;
        this.seasonsWatched = seasonsWatched;
        evaluate();
    }

    public Series(Item tmp, String director, int episodesTotal, int episodesWatched, int seasonsTotal, int seasonsWatched) {
        super(tmp);
        this.director = director;
        this.episodesTotal = episodesTotal;
        this.episodesWatched = episodesWatched;
        this.seasonsTotal = seasonsTotal;
        this.seasonsWatched = seasonsWatched;
        evaluate();
    }

    public Series(Series tmp) {
        super(tmp);
        this.director = tmp.getDirector();
        this.episodesTotal = tmp.getEpisodesTotal();
        this.episodesWatched = tmp.getEpisodesWatched();
        this.seasonsTotal = tmp.getSeasonsTotal();
        this.seasonsWatched = tmp.getSeasonsWatched();
        evaluate();
    }

    //Might delete later
    public Series(Item tmp) {
        super(tmp);
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public int getEpisodesTotal() {
        return episodesTotal;
    }

    public void setEpisodesTotal(int episodesTotal) {
        this.episodesTotal = episodesTotal;
        evaluate();
    }

    public int getEpisodesWatched() {
        return episodesWatched;
    }

    public void setEpisodesWatched(int episodesWatched) {
        this.episodesWatched = episodesWatched;
        evaluate();
    }

    public int getSeasonsTotal() {
        return seasonsTotal;
    }

    public void setSeasonsTotal(int seasonsTotal) {
        this.seasonsTotal = seasonsTotal;
        evaluate();
    }

    public int getSeasonsWatched() {
        return seasonsWatched;
    }

    public void setSeasonsWatched(int seasonsWatched) {
        this.seasonsWatched = seasonsWatched;
        evaluate();
    }

    private void evaluate() {
        setDone(episodesWatched >= episodesTotal && seasonsWatched >= seasonsTotal && episodesTotal > 0 && seasonsTotal > 0);
    }

    @Override
    public String toString() {
        return "models.Series{" +
                super.toString() +
                ", director='" + director + '\'' +
                ", episodesTotal=" + episodesTotal +
                ", episodesWatched=" + episodesWatched +
                ", seasonsTotal=" + seasonsTotal +
                ", seasonsWatched=" + seasonsWatched +
                '}';
    }
}
