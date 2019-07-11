import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class Streams {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("John", "Daenerys", "Tyrion", "", null, "Arya");

        names.stream()
                .filter(Objects::nonNull)
                .filter(name -> !name.isEmpty() && name.contains("a"))
                .forEach(System.out::println);

        System.out.println();

        List<String> stringCollection = new ArrayList<>();
        stringCollection.add("ddd2");
        stringCollection.add("aaa2");
        stringCollection.add("bbb1");
        stringCollection.add("aaa1");
        stringCollection.add("bbb3");
        stringCollection.add("ccc");
        stringCollection.add("bbb2");
        stringCollection.add("ddd1");

        stringCollection
                .stream()
                .filter((s) -> s.startsWith("a"))
                .forEach(System.out::println);

        System.out.println();

        stringCollection
                .stream()
                .map(String::toUpperCase)
                .sorted((a, b) -> b.compareTo(a))
                .forEach(System.out::println);

        System.out.println();

        boolean anyStartsWithA =
                stringCollection
                        .stream()
                        .anyMatch((s) -> s.startsWith("a"));

        System.out.println(anyStartsWithA);      // true

        boolean allStartsWithA =
                stringCollection
                        .stream()
                        .allMatch((s) -> s.startsWith("a"));

        System.out.println(allStartsWithA);      // false

        boolean noneStartsWithZ =
                stringCollection
                        .stream()
                        .noneMatch((s) -> s.startsWith("z"));

        System.out.println(noneStartsWithZ);     // true

        System.out.println();

        long startsWithB =
                stringCollection
                        .stream()
                        .filter((s) -> s.startsWith("b"))
                        .count();

        System.out.println(startsWithB);
        System.out.println("\n");


        //parallel sort
        long m0 = System.nanoTime();
        System.out.println(m0);
        int max = 1000000;
        List<String> values = new ArrayList<>(max);

        for (int i = 0; i < max; i++) {
            UUID uuid = UUID.randomUUID();
            values.add(uuid.toString());
        }
        long m1 = System.nanoTime();
        System.out.println(m1);
        long mills = TimeUnit.NANOSECONDS.toMillis(m1 - m0);
        System.out.println(String.format("заполнение занимает: %d ms\n", mills));

        //последовательная сортировка
        long t0 = System.nanoTime();
        System.out.println(t0);

        long count = values.stream().sorted().count();
        System.out.println(count);

        long t1 = System.nanoTime();
        System.out.println(t1);
        System.out.println(t1 - t0);

        long millis = TimeUnit.NANOSECONDS.toMillis(t1 - t0);
        System.out.println(String.format("sequential sort took: %d ms", millis));

        //параллельная сортировка
        long t01 = System.nanoTime();
        System.out.println(t01);

        long count2 = values.parallelStream().sorted().count();
        System.out.println(count2);

        long t11 = System.nanoTime();
        System.out.println(t11);
        System.out.println(t11 - t01);

        long millis2 = TimeUnit.NANOSECONDS.toMillis(t11 - t01);
        System.out.println(String.format("parallel sort took: %d ms\n", millis2));






        //Collector
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        List<Integer> evenNumbers = numbers.stream()
                .filter(i -> i % 2 == 0)
                .collect(Collectors.toList());

        System.out.println(evenNumbers + "\n"); //output [2, 4, 6, 8, 10]


        List<String> otherNames = Arrays.asList("John", "Arya", "Sansa", "John");
        System.out.println(otherNames);
        Set<String> upperCaseNames = otherNames.stream()
                .map(String::toUpperCase)
                .collect(Collectors.toSet());

        System.out.println(upperCaseNames); //output [ARYA, JOHN, SANSA]

        //есть ли в стриме четное число
        List<Integer> numbers1 = Arrays.asList(1, 2, 3, 4, 5);

        boolean match = numbers1.stream()
                .anyMatch(number -> number % 2 == 0); // есть ли в Stream-e четное число

        System.out.println(match); //output true
    }
}
