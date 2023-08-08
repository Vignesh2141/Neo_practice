package org.neosoft;

import java.util.*;
import java.util.stream.*;

public class EvenNumbers {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        int sum= numbers.stream().filter(i->i%2==0).reduce((a, b)->a+b).get();
        System.out.println("sum "+sum);

        List<Integer> num=new ArrayList<>();
        num.addAll(numbers);

        int secondHighest = numbers.stream()
                .sorted((a, b) -> b.compareTo(a)) // Sort in descending order
                .skip(1) // Skip the first element (highest integer)
                .findFirst() // Get the second highest integer
                .orElseThrow(() -> new IllegalArgumentException("List is empty or has only one element."));

        System.out.println("Second highest integer: " + secondHighest);

        List<String> list1 = Arrays.asList("apple", "banana", "cherry");
        List<String> list2 = Arrays.asList("orange", "grape", "kiwi");

        // Concatenate lists using Stream API
        List<String> concatenatedList = Stream.concat(list1.stream(), list2.stream())
                .collect(Collectors.toList());

        // Print the concatenated list
        System.out.println("Concatenated List: " + concatenatedList);


    }
}
