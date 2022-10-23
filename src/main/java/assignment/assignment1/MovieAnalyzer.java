package assignment.assignment1;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.*;

/**
 * @author zmt 11912725
 */

public class MovieAnalyzer {
    /*
    he constructor of MovieAnalyzer takes the path of the dataset file
    and reads the data.
    The dataset is in csv format and has the following columns

     */
    public static class Movie{
//      Poster_Link - address
        String Poster_Link;
//      Series_Title - Name of the movie
        String Series_Title;
//      Released_Year - Year at which that movie released
        String Released_Year;
//      Certificate - Certificate earned by that movie
        String Certificate;
//        Runtime - Total runtime of the movie
        String Runtime;
//        Genre - Genre of the movie
        String Genre;
//        IMDB_Rating - Rating of the movie at IMDB site
        String IMDB_Rating;
//        Overview - mini story/ summary
        String Overview;
//        Meta_score - Score earned by the movie
        String Meta_score;
//        Director - Name of the Director
        String Director;
//        Star1,Star2,Star3,Star4 - Name of the Stars
        String Star1;
        String Star2;
        String Star3;
        String Star4;
//        No_of_votes - Total number of votes
        String No_of_votes;

        public String getPoster_Link() {
            return Poster_Link;
        }

        public void setPoster_Link(String poster_Link) {
            Poster_Link = poster_Link;
        }

        public String getSeries_Title() {
            return Series_Title;
        }

        public void setSeries_Title(String series_Title) {
            Series_Title = series_Title;
        }

        public String getReleased_Year() {
            return Released_Year;
        }

        public void setReleased_Year(String released_Year) {
            Released_Year = released_Year;
        }

        public String getCertificate() {
            return Certificate;
        }

        public void setCertificate(String certificate) {
            Certificate = certificate;
        }

        public String getRuntime() {
            return Runtime;
        }

        public void setRuntime(String runtime) {
            Runtime = runtime;
        }

        public String getGenre() {
            return Genre;
        }

        public void setGenre(String genre) {
            Genre = genre;
        }

        public String getIMDB_Rating() {
            return IMDB_Rating;
        }

        public void setIMDB_Rating(String IMDB_Rating) {
            this.IMDB_Rating = IMDB_Rating;
        }

        public String getOverview() {
            return Overview;
        }

        public void setOverview(String overview) {
            Overview = overview;
        }

        public String getMeta_score() {
            return Meta_score;
        }

        public void setMeta_score(String meta_score) {
            Meta_score = meta_score;
        }

        public String getDirector() {
            return Director;
        }

        public void setDirector(String director) {
            Director = director;
        }

        public String getStar1() {
            return Star1;
        }

        public void setStar1(String star1) {
            Star1 = star1;
        }

        public String getStar2() {
            return Star2;
        }

        public void setStar2(String star2) {
            Star2 = star2;
        }

        public String getStar3() {
            return Star3;
        }

        public void setStar3(String star3) {
            Star3 = star3;
        }

        public String getStar4() {
            return Star4;
        }

        public void setStar4(String star4) {
            Star4 = star4;
        }

        public String getNo_of_votes() {
            return No_of_votes;
        }

        public void setNo_of_votes(String no_of_votes) {
            No_of_votes = no_of_votes;
        }

        public String getGross() {
            return Gross;
        }

        public void setGross(String gross) {
            Gross = gross;
        }

        //        Gross - Money earned by that movie
        String Gross;


    }

    //step 0 reading the dataset
    public MovieAnalyzer(String dataset_set) throws URISyntaxException {
        URI uri = MovieAnalyzer.class.getClassLoader().getResource(dataset_set).toURI();
        String filePath = Paths.get(uri).toString();
        try (FileInputStream fis = new FileInputStream(filePath);
             InputStreamReader isr = new InputStreamReader(fis, "gb2312");
             BufferedReader bReader = new BufferedReader(isr);){

            char[] cbuf = new char[16];
            int file_len = bReader.read(cbuf);

            System.out.println(file_len);
            System.out.println(cbuf);

        } catch (FileNotFoundException e) {
            System.out.println("The pathname does not exist.");
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            System.out.println("The Character Encoding is not supported.");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Failed or interrupted when doing the I/O operations");
            e.printStackTrace();
        }
    }

    //step 1 Movie count by year
    public Map<Integer,Integer> getMovieCountByYear(String year){
        //returns <year, count> map
        Map<Integer,Integer> res = new HashMap<>();

        return res;
    }

    //step 2 Movie count by genre
    public Map<Integer,Integer> getMovieCountByGenre(){
        //returns a <genre, count> map
        Map<Integer,Integer> res = new HashMap<>();

        return res;
    }

    //step 3 Movie count by co-stars
    public Map<List<String>, Integer> getCoStarCount(){
        //returns a <genre, count> map
        Map<List<String>,Integer> res = new HashMap<>();

        return res;
    }

    //step 4 Top movies
    public List<String> getTopMovies(int top_k, String by){
        List<String> res = new ArrayList<>();

        return res;
    }

    //step 5 Top stars
    public List<String> getTopStars(int top_k, String by){
        List<String> res = new ArrayList<>();
        return res;
    }

    //step 6 Search movies
    public List<String> searchMovies(String genre, float min_rating, int max_runtime){
        List<String> res = new ArrayList<>();
        return res;
    }

    public static void main(String[] args) throws URISyntaxException {
        System.out.println("===================");
        MovieAnalyzer m = new MovieAnalyzer("D:\\code\\java\\22F-CS209A-Labs\\src\\main\\resources\\imdb_top_500.csv");
        System.out.println("+++++++++++++++++++");
        System.out.println(m.getTopStars(20, "gross"));
    }
}