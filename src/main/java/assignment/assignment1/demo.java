package assignment.assignment1;

import tutorial.lab5.BufferReader;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.*;
import java.io.*;

/**
 * @author zmt 11912725
 */

public class demo {

    //step 0 reading the dataset
    public demo(String dataset_set) throws URISyntaxException {
    /*
    he constructor of MovieAnalyzer takes the path of the dataset file
    and reads the data.
    The dataset is in csv format and has the following columns
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
        URI uri = BufferReader.class.getClassLoader().getResource(dataset_set).toURI();
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
}