import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
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


        //сумма чисел в списке
        numbers = Arrays.asList(1, 2, 3, 5);

        Optional<Integer> sum = numbers.stream()
                .reduce((left, right) -> left + right);

        sum.ifPresent(System.out::println); //output 11

        //сложные опреации над элементами
        numbers = Arrays.asList(1, 2, 3);

        // 1*10 + 2*10 + 3*10
        Integer sum1 = numbers.stream()
                .reduce(10, (identity, val) -> identity * val, (left, right) -> left + right);      //Stream преобразовывается из 1, 2, 3 в 10, 20, 30 а послее просто суммируется

        System.out.println(sum1); //output 60


        //найти наименьшее зн-ие
        numbers = Arrays.asList(1, 2, 3, 5, 7);

        Integer min = numbers.stream()
                .reduce(Integer.MAX_VALUE, Integer::min);

        System.out.println(min); //output

        //найти самую длинную строку
        List<String> someStrings = Arrays.asList("aaa", "bbb", "ccc", "ddd", "ffff");

        String s = someStrings.stream()
                .reduce("", (left, right) -> left.length() > right.length() ? left : right);

        System.out.println(s); //output ffff





        //Complex search
        //https://vertex-academy.com/tutorials/ru/java-8-stream-reduce/
        List<Connection> network = Arrays.asList(new Connection("A", "B"),
                new Connection("A", "C"),
                new Connection("A", "D"),
                new Connection("B", "C")
        );

        List<String> identity = new ArrayList<>();

        BiFunction<List<String>, Connection, List<String>> accumulator = (strings, connection) -> {
            strings.add(connection.getTo());
            return strings;
        };      //добавляем наши входящие узлы в список (identity) и возвращаем его

        BinaryOperator<List<String>> combiner = (strings, strings2) -> {
            strings.addAll(strings2);
            return strings;
        };

        List<String> list = network.stream()
                .filter(p -> "A".equals(p.getFrom()))
                .reduce(identity, accumulator, combiner);

        System.out.println(list); //output [B, C, D]
    }
}
