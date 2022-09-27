package practice.lab4;

import java.io.IOException;

import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.*;


public class demo {
    public static class City {
        private String name;
        private String state;
        private int population;

        public City(String name, String state, int population) {
            this.name = name;
            this.state = state;
            this.population = population;
        }

        public String getName() {
            return name;
        }

        public String getState() {
            return state;
        }

        public int getPopulation() {
            return population;
        }

    }

    public static Stream<City> readCities(String filename) throws IOException {
        return Files.lines(Paths.get(filename))
                .map(l -> l.split(", "))
                .map(a -> new City(a[0], a[1], Integer.parseInt(a[2])));
    }

    public static void main(String[] args) throws IOException, URISyntaxException {

        Stream<City> cities = readCities("src\\main\\java\\practice\\lab4\\cities.txt");
        // Q1: count how many cities there are for each state
        // TODO: Map<String, Long> cityCountPerState = ...
        Map<String, Long> cityCountPerState = new HashMap<>();
        cities.forEach(s -> {
            if (cityCountPerState.containsKey(s.getState())) {
                cityCountPerState.compute(s.getState(), (key, value) -> value + 1L);
            } else {
                cityCountPerState.put(s.getState(), 1L);
            }
        });
        System.out.println(cityCountPerState);
        System.out.println();

        cities = readCities("src\\main\\java\\practice\\lab4\\cities.txt");
        // Q2: count the total population for each state
        // TODO: Map<String, Integer> statePopulation = ...
        Map<String, Integer> statePopulation = new HashMap<>();
        cities.forEach(s -> {
            if (statePopulation.containsKey(s.getState())) {
                statePopulation.compute(s.getState(), (key, value) -> value + s.getPopulation());
            } else {
                statePopulation.put(s.getState(), s.getPopulation());
            }
        });
        System.out.println(statePopulation);
        System.out.println();

        cities = readCities("src\\main\\java\\practice\\lab4\\cities.txt");
        // Q3: for each state, get the set of cities with >500,000 population
        // TODO: Map<String, Set<City>> largeCitiesByState = ...
        Map<String, Set<City>> largeCitiesByState = new HashMap<>();
        cities.forEach(s->largeCitiesByState.putIfAbsent(s.getState(),new HashSet<>()));
        cities = readCities("src\\main\\java\\practice\\lab4\\cities.txt");
        cities.filter(city -> city.getPopulation() > 500000)
                .forEach(s ->
                        largeCitiesByState.get(s.getState()).add(s)
                );
        largeCitiesByState.forEach(
                (key,value) -> {
                    System.out.print(key +" [");
                    Iterator<City> i = value.iterator();
                    while (i.hasNext()){
                        City c = i.next();
                        System.out.print("City{name=\'"+c.getName()
                                +"\', state=\'" +c.getState()
                                +"\', population=" +c.getPopulation()
                                +"} "
                        );
                    }
                    System.out.println("]");
                }
        );
        System.out.println();
    }
}