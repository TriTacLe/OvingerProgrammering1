package com.example;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Person> people = getPeople();

        /*
        List<Person> females = new ArrayList<>();
        // Imperative approach Nei:

        for (Person person : people) {
            if (person.getGender().equals(Gender.FEMALE)) {
                females.add(person);
            }
        }
        females.forEach(System.out::println);
        */

        // Declarative approach Ja:
        //Filter
        List<Person> females = people.stream()
                .filter(person -> person.getGender().equals(Gender.FEMALE))
                .collect(Collectors.toList());
//        females.forEach(System.out::println);

        //Sort
        List<Person> sorted = people.stream()
                .sorted(Comparator.comparing(Person::getGender))
                .collect(Collectors.toList());
        sorted.forEach(System.out::println);

        //All match
        boolean allMatch = people.stream()
                .allMatch(person -> person.getAge()>5);
//        System.out.println(allMatch);

        boolean anyMatch = people.stream()
                .anyMatch(person -> person.getAge()>5);
//        System.out.println(anyMatch);

        boolean noneMatch = people.stream()
                .noneMatch(person -> person.getName().equals("Antonio"));
//        System.out.println(noneMatch);

        //Max
        people.stream()
                .max(Comparator.comparing(Person::getAge));
//                .ifPresent(System.out::println);
        //Min
        people.stream()
                .min(Comparator.comparing(Person::getAge));
//                .ifPresent(System.out::println);

        //Group
        Map<Gender, List<Person>> groupByGender = people.stream()
                .collect(Collectors.groupingBy(Person::getGender));

        /*
        groupByGender.forEach((gender, people1) -> {
            System.out.println(gender);
            people1.forEach(System.out::println);
        });
         */
        // Chaining
        Optional<String> oldesFemaleAge = people.stream()
                .filter(person -> person.getGender().equals(Gender.FEMALE))
                .max(Comparator.comparing(Person::getAge))
                .map(Person::getName);
        oldesFemaleAge.ifPresent(System.out::println);
    }

    private static List<Person> getPeople() {
        return List.of(
                new Person("Antonio", 20, Gender.MALE),
                new Person("Alina Smith", 33, Gender.FEMALE),
                new Person("Helen White", 57, Gender.FEMALE),
                new Person("Alex Boz", 14, Gender.MALE),
                new Person("Jamie Goa", 99, Gender.MALE),
                new Person("Anna Cook", 7, Gender.FEMALE),
                new Person("Zelda Brown", 120, Gender.FEMALE)
        );
    }
}