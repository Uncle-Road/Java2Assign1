package assignment.assignment1;

import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.*;
import java.io.IOException;
import java.nio.file.Files;
import java.util.stream.Collectors;

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
        int Runtime;
        //        Genre - Genre of the movie
        String Genre;
        //        IMDB_Rating - Rating of the movie at IMDB site
        double IMDB_Rating;
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
        double Gross;

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

        public int getRuntime() {
            return Runtime;
        }

        public String getGenre() {
            return Genre;
        }

        public double getIMDB_Rating() {
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

        public double getGross() {
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
            this.Poster_Link = Poster_Link.strip();
            this.Series_Title = Series_Title.replace("\"","").strip();
            this.Released_Year = Integer.parseInt(Released_Year);
            this.Certificate = Certificate.strip();
            this.Runtime = Integer.parseInt(Runtime.replace("min","").strip());
            this.Genre = Genre;
            this.IMDB_Rating = Double.parseDouble(IMDB_Rating);
            this.Overview = Overview.charAt(0)=='\"' ? Overview.substring(1,Overview.length()-1) :Overview.strip();
            this.Meta_score = Integer.parseInt(Meta_score);
            this.Director = Director;
            this.Star1 = Star1.strip();
            this.Star2 = Star2.strip();
            this.Star3 = Star3.strip();
            this.Star4 = Star4.strip();
            this.No_of_Votes = Integer.parseInt(No_of_Votes);
            this.Gross = Double.parseDouble(Gross.replace(",", "").replace("\"", "").strip());
        }

        public Movie(String[] arr) {
//            System.out.println(Arrays.toString(arr));
            this.Poster_Link = arr[0].strip();
            this.Series_Title = arr[1].replace("\"","").strip();
            this.Released_Year = Integer.parseInt(arr[2].strip());
            this.Certificate = arr[3].strip();
            this.Runtime = Integer.parseInt(arr[4].replace("min","").strip());
            this.Genre = arr[5].strip();
            this.IMDB_Rating = Double.parseDouble(arr[6].strip());
            this.Overview = arr[7].charAt(0)=='\"' ? arr[7].substring(1,arr[7].length()-1) :arr[7].strip();
            this.Meta_score = Integer.parseInt(arr[8].replace("", "1").strip());
            this.Director = arr[9].strip();
            this.Star1 = arr[10].strip();
            this.Star2 = arr[11].strip();
            this.Star3 = arr[12].strip();
            this.Star4 = arr[13].strip();
            this.No_of_Votes = Integer.parseInt(arr[14].strip());
            this.Gross =  Double.parseDouble(arr[15].replace(",", "").replace("\"", "").strip());
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

        if(Objects.equals(by, "runtime")){
            res = movieList.stream().sorted((m1,m2)-> {
                if (m1.getRuntime() == m2.getRuntime()) {
                    return m1.getSeries_Title().compareTo(m2.getSeries_Title());
                }else {
                    return m2.getRuntime()-m1.getRuntime();
                }
            }).map(Movie::getSeries_Title).limit(top_k).collect(Collectors.toList());

        } else if (Objects.equals(by, "overview")) {
            res = movieList.stream().sorted((m1,m2)->{
                if (m1.getOverview().length()== m2.getOverview().length()){
                    return m1.getSeries_Title().compareTo(m2.getSeries_Title());
                }else {
                    return m2.getOverview().length()-m1.getOverview().length();
                }
            }).map(Movie::getSeries_Title).limit(top_k).collect(Collectors.toList());;
        }
        return res;
    }

    //step 5 Top stars
    public List<String> getTopStars(int top_k, String by) {
        List<String> res = new ArrayList<>();
        Map<String, Double> rating_avg = new HashMap<>();
        Map<String, Double> rating_sum = new HashMap<>();
        Map<String,Integer>occur_num = new HashMap<>();
        Map<String, Double> gross_avg = new HashMap<>();
        Map<String, Double> gross_sum = new HashMap<>();
        if (Objects.equals(by, "rating")){
            movieList.stream().forEach(a->{
                String s1 = a.getStar1();
                String s2 = a.getStar2();
                String s3 = a.getStar3();
                String s4 = a.getStar4();
                String[] arr = new String[]{s1,s2,s3,s4};
                Arrays.sort(arr);
                for (String i :arr) {
                    if (rating_sum.containsKey(i)) {
                        rating_sum.put(i, rating_sum.get(i) + a.getIMDB_Rating());
                    } else {
                        rating_sum.putIfAbsent(i, a.getIMDB_Rating());
                    }
                    if (occur_num.containsKey(i)){
                        occur_num.put(i,occur_num.get(i)+1);
                    }else {
                        occur_num.putIfAbsent(i,1);
                    }
                }
            });
            Set<String> nameList = occur_num.keySet();
            for(String i:nameList){
                rating_avg.put(i,rating_sum.get(i)/occur_num.get(i));
            }
            rating_avg = rating_avg.entrySet().stream()
                    .sorted(Map.Entry.comparingByKey())
                    .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
            res = rating_avg.keySet().stream().limit(top_k).toList();
        }else if (Objects.equals(by, "gross")){
            movieList.stream().forEach(a->{
                if (a.getGross()!=1) {
                    String s1 = a.getStar1();
                    String s2 = a.getStar2();
                    String s3 = a.getStar3();
                    String s4 = a.getStar4();
                    String[] arr = new String[]{s1, s2, s3, s4};
                    Arrays.sort(arr);
                    for (String i : arr) {
                        if (gross_sum.containsKey(i)) {
                            gross_sum.put(i, gross_sum.get(i) + a.getGross());
                        } else {
                            gross_sum.putIfAbsent(i, a.getGross());
                        }
                        if (occur_num.containsKey(i)) {
                            occur_num.put(i, occur_num.get(i) + 1);
                        } else {
                            occur_num.putIfAbsent(i, 1);
                        }
                    }
                }
            });
            Set<String> nameList = occur_num.keySet();
            for(String i:nameList){
                gross_avg.put(i,gross_sum.get(i)/occur_num.get(i));
            }
            gross_avg = gross_avg.entrySet().stream()
                    .sorted(Map.Entry.comparingByKey())
                    .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
            res = gross_avg.keySet().stream().limit(top_k).toList();
        }
        return res;
    }

    //step 6 Search movies
    public List<String> searchMovies(String genre, float min_rating, int max_runtime) {
        List<String> res = new ArrayList<>();
        movieList.stream().filter(s->s.getRuntime()<=max_runtime).filter(s->s.getIMDB_Rating()>=min_rating).forEach(
                a->{
                    String[] arr = a.getGenre().split(",");
                    for(String i :arr){
                        String tmp = i.replace("\"", "").strip();
                        if (tmp.equals(genre)){
                            res.add(a.getSeries_Title());
                        }
                    }
                }
        );
//        res.stream().forEach(s -> System.out.println(s.toString()));
        List<String>ans = res.stream().sorted(Comparator.naturalOrder()).toList();
        ans.stream().forEach(s -> System.out.println(s.toString()));
        return ans;
    }

    public static void main(String[] args) throws URISyntaxException {
        System.out.println("===================");
        MovieAnalyzer m = new MovieAnalyzer("D:\\code\\java\\22F-CS209A-Labs\\src\\main\\resources\\imdb_top_500.csv");
        System.out.println("-------------------");
        System.out.println(m.movieList.size());
//        m.movieList.forEach(a->{a.getAllInformation();});
    }
}