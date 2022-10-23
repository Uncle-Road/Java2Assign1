package assignment.assignment1;

import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.*;
import java.io.IOException;
import java.nio.file.Files;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author zmt 11912725
 */

public class MovieAnalyzer {

    public static class Movie {
        //      Poster_Link - address
        String Poster_Link;
        //      Series_Title - Name of the movie
        String Series_Title;
        //      Released_Year - Year at which that movie released
        int Released_Year;
        //      Certificate - Certificate earned by that movie
        String Certificate;
        //        Runtime - Total runtime of the movie
        String Runtime;
        //        Genre - Genre of the movie
        String Genre;
        //        IMDB_Rating - Rating of the movie at IMDB site
        float IMDB_Rating;
        //        Overview - mini story/ summary
        String Overview;
        //        Meta_score - Score earned by the movie
        int Meta_score;
        //        Director - Name of the Director
        String Director;
        //        Star1,Star2,Star3,Star4 - Name of the Stars
        String Star1;
        String Star2;
        String Star3;
        String Star4;
        int No_of_Votes;
        long Gross;

        public Movie() {

        }

        public String getPoster_Link() {
            return Poster_Link;
        }

        public String getSeries_Title() {
            return Series_Title;
        }

        public int getReleased_Year() {
            return Released_Year;
        }

        public String getCertificate() {
            return Certificate;
        }

        public String getRuntime() {
            return Runtime;
        }

        public String getGenre() {
            return Genre;
        }

        public float getIMDB_Rating() {
            return IMDB_Rating;
        }

        public String getOverview() {
            return Overview;
        }

        public int getMeta_score() {
            return Meta_score;
        }

        public String getDirector() {
            return Director;
        }

        public String getStar1() {
            return Star1;
        }

        public String getStar2() {
            return Star2;
        }

        public String getStar3() {
            return Star3;
        }

        public String getStar4() {
            return Star4;
        }

        public int getNo_of_Votes() {
            return No_of_Votes;
        }

        public long getGross() {
            return Gross;
        }

        public void getAllInformation() {
            //Poster_Link,Series_Title,Released_Year,Certificate,Runtime,Genre,IMDB_Rating,Overview,Meta_score,Director,Star1,Star2,Star3,Star4,No_of_Votes,Gross
            System.out.println(getPoster_Link() + "*"
                    + getSeries_Title() + "*"
                    + getReleased_Year() + "*"
                    + getCertificate() + "*"
                    + getRuntime() + "*"
                    + getGenre() + "*"
                    + getIMDB_Rating() + "*"
                    + getOverview() + "*"
                    + getMeta_score() + "*"
                    + getDirector() + "*"
                    + getStar1() + "*" + getStar2() + "*" + getStar3() + "*" + getStar4() + "*"
                    + getNo_of_Votes() + "*" + getGross() + "*"
            );
        }

        public Movie(String Poster_Link, String Series_Title, String Released_Year, String Certificate, String Runtime, String Genre, String IMDB_Rating, String Overview, String Meta_score, String Director, String Star1, String Star2, String Star3, String Star4, String No_of_Votes, String Gross) {
            this.Poster_Link = Poster_Link;
            this.Series_Title = Series_Title;
            this.Released_Year = Integer.parseInt(Released_Year);
            this.Certificate = Certificate;
            this.Runtime = Runtime;
            this.Genre = Genre;
            this.IMDB_Rating = Float.parseFloat(IMDB_Rating);
            this.Overview = Overview;
            this.Meta_score = Integer.parseInt(Meta_score);
            this.Director = Director;
            this.Star1 = Star1;
            this.Star2 = Star2;
            this.Star3 = Star3;
            this.Star4 = Star4;
            this.No_of_Votes = Integer.parseInt(No_of_Votes);
            this.Gross = Long.parseLong(Gross);
        }

        public Movie(String[] arr) {
//            System.out.println(Arrays.toString(arr));
            this.Poster_Link = arr[0].strip();
            this.Series_Title = arr[1].strip();
            this.Released_Year = Integer.parseInt(arr[2].strip());
            this.Certificate = arr[3].strip();
            this.Runtime = arr[4].strip();
            this.Genre = arr[5].strip();
            this.IMDB_Rating = Float.parseFloat(arr[6].strip());
            this.Overview = arr[7].strip();
            this.Meta_score = Integer.parseInt(arr[8].replace("", "1").strip());
            this.Director = arr[9].strip();
            this.Star1 = arr[10].strip();
            this.Star2 = arr[11].strip();
            this.Star3 = arr[12].strip();
            this.Star4 = arr[13].strip();
            this.No_of_Votes = Integer.parseInt(arr[14].strip());
            this.Gross = Integer.parseInt(arr[15].replace(",", "").replace("\"", "").strip());
        }
    }

    List<Movie> movieList = new ArrayList<>();

    //step 0 reading the dataset
    public MovieAnalyzer(String dataset_path) {
        try {
            Files.lines(Paths.get(dataset_path)).skip(1).forEach(a -> {
                if (a.endsWith(",")) {
                    a += "\"1\"";
                }
                String[] arr = a.strip().split(",(?=([^\\\"]*\\\"[^\\\"]*\\\")*[^\\\"]*$)", -1);
                Movie m = new Movie(arr);
                movieList.add(m);
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //step 1 Movie count by year
    public Map<Integer, Integer> getMovieCountByYear() {
        //returns <year, count> map
        //sort

        Map<Integer, Integer> res = movieList.stream().collect(Collectors.groupingBy(Movie::getReleased_Year, Collectors.summingInt(m -> 1)));

        Map<Integer, Integer> ans = new TreeMap<>(
                new Comparator<Integer>() {
                    @Override
                    public int compare(Integer a1, Integer a2) {
                        return a2.compareTo(a1);
                    }
                }
        );
        Set<Integer> keySets = res.keySet();
        Iterator<Integer> iter = keySets.iterator();
        while (iter.hasNext()) {
            Integer key = iter.next();
            ans.put(key, res.get(key));
        }
        return ans;
    }

    //step 2 Movie count by genre
    public Map<String, Integer> getMovieCountByGenre() {
        Map<String, Integer> res = new HashMap<>();

        movieList.stream().forEach(a -> {
            String[] arr = a.getGenre().split(",");
            for (String i : arr) {
                String tmp = i.replace("\"", "").strip();
                if (res.containsKey(tmp)) {
                    res.put(tmp, res.get(tmp) + 1);
                } else {
                    res.putIfAbsent(tmp, 1);
                }

            }
        });

        Map<String, Integer> ans = res.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

        return ans;
    }

    //step 3 Movie count by co-stars
    public Map<List<String>, Integer> getCoStarCount() {
        Map<List<String>, Integer> res = new HashMap<>();
        movieList.forEach(a->{
            String s1 = a.getStar1();
            String s2 = a.getStar2();
            String s3 = a.getStar3();
            String s4 = a.getStar4();
            String[] arr = new String[]{s1,s2,s3,s4};
            Arrays.sort(arr);
            for(int i =0;i<3;i++){
                for(int j =i+1;j<4;j++){
                    List<String> tmp = new ArrayList<>();
                    tmp.add(arr[i]);
                    tmp.add(arr[j]);
                    if(res.containsKey(tmp)){
                        res.put(tmp, res.get(tmp) + 1);
                    }else {
                        res.putIfAbsent(tmp, 1);
                    }
                }
            }
        });
        return res;
    }

    //step 4 Top movies
    public List<String> getTopMovies(int top_k, String by) {
        List<String> res = new ArrayList<>();

        return res;
    }

    //step 5 Top stars
    public List<String> getTopStars(int top_k, String by) {
        List<String> res = new ArrayList<>();

        return res;
    }

    //step 6 Search movies
    public List<String> searchMovies(String genre, float min_rating, int max_runtime) {
        List<String> res = new ArrayList<>();
        return res;
    }

    public static void main(String[] args) throws URISyntaxException {
        System.out.println("===================");
        MovieAnalyzer m = new MovieAnalyzer("D:\\code\\java\\22F-CS209A-Labs\\src\\main\\resources\\imdb_top_500.csv");
        System.out.println("-------------------");
        System.out.println(m.movieList.size());
//        m.movieList.forEach(a->{a.getAllInformation();});
    }
}