package practice.lab4;

import java.io.IOException;

import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.*;


public class Practice4 {
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
        Map<String, Long> cityCountPerState = cities.collect(Collectors.groupingBy(
                City::getState, Collectors.counting()
        ));
        System.out.println(cityCountPerState);
        System.out.println();

        cities = readCities("src\\main\\java\\practice\\lab4\\cities.txt");
        // Q2: count the total population for each state
        Map<String, Integer> statePopulation = cities.collect(Collectors.groupingBy(
                City::getState, Collectors.summingInt(City::getPopulation)
        ));
        System.out.println(statePopulation);
        System.out.println();

        cities = readCities("src\\main\\java\\practice\\lab4\\cities.txt");
        // Q3: for each state, get the set of cities with >500,000 population
        Map<String, Set<City>> largeCitiesByState = cities.collect(Collectors.groupingBy(
                City::getState, Collectors.filtering(s -> s.getPopulation() > 500000, Collectors.toSet())
        ));

        largeCitiesByState.forEach(
                (key, value) -> {
                    System.out.print(key + " [");
                    Iterator<City> i = value.iterator();
                    while (i.hasNext()) {
                        City c = i.next();
                        System.out.print("City{name=\'" + c.getName()
                                + "\', state=\'" + c.getState()
                                + "\', population=" + c.getPopulation()
                                + "} "
                        );
                    }
                    System.out.println("]");
                }
        );
        System.out.println();

    }
}

