package assignment.assignment1.src;

import java.util.*;
import java.io.*;

/**
 * @author zmt 11912725
 */

public class MovieAnalyzer {

    //step 0 reading the dataset
    /*
    Series_Title - Name of the movie
    Released_Year - Year at which that movie released
    Certificate - Certificate earned by that movie
    Runtime - Total runtime of the movie
    Genre - Genre of the movie
    IMDB_Rating - Rating of the movie at IMDB site
    Overview - mini story/ summary
    Meta_score - Score earned by the movie
    Director - Name of the Director
    Star1,Star2,Star3,Star4 - Name of the Stars
    Noofvotes - Total number of votes
    Gross - Money earned by that movie
     */
    public MovieAnalyzer(String dataset_set) {

    }

    //step 1 Movie count by year
    public Map<Integer,Integer> getMovieCountByYear(){
        return new HashMap<>();
    }

    //step 2 Movie count by genre
    public Map<Integer,Integer> getMovieCountByGenre(){
        return new HashMap<>();
    }

    //step 3 Movie count by co-stars
    public Map<List<String>, Integer> getCoStarCount(){
        return new HashMap<>();
    }

    //step 4 Top movies
    public List<String> getTopMovies(int top_k, String by){
        return new ArrayList<>();
    }

    //step 5 Top stars
    public List<String> getTopStars(int top_k, String by){
        return new ArrayList<>();
    }

    //step 6 Search movies
    public List<String> searchMovies(String genre, float min_rating, int max_runtime){
        return new ArrayList<>();
    }
}