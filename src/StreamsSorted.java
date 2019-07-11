import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class StreamsSorted {

    public static final int MAX = 1000000;

    public static void main(String[] args) throws IOException {

        //SORT

        //Последовательная сортировка
        //заполнение случайными значениями
        List<String> values = getRandomList(MAX);

        //сортировка
        System.out.println("Последовательная сортировка");

        long t0 = getNanoTime();
        //long count = values.stream().sorted().count();          //Sequentional sort почему-то занимает меньше миллисек, скорее всего сортировка не производится
        List<String> sortedValues = values.stream().sorted().collect(Collectors.toList());
        //long count = values.stream().parallel().sorted().count();
        long t1 = getNanoTime();

        //System.out.println("Количество элементов values: " + count);
        displayExecTime(t0, t1, "Sequentional sort в List занимает: ");

        String currentDir = System.getProperty("user.dir");     //папка проекта
        //System.out.println("Current dir using System: " + currentDir);
        Files.write(Paths.get(currentDir + "\\output\\sortedValues.txt"), sortedValues);

        //Параллельная сортировка
        //заполнение случайными значениями
        List<String> values2 = getRandomList(MAX);

        //сортировка
        System.out.println("Параллельная сортировка");

        long t00 = getNanoTime();
        long count2 = values2.parallelStream().sorted().count();
        //long count2 = values2.stream().parallel().sorted().count();
        //long count2 = values2.stream().sorted().count();
        //long count2 = values2.parallelStream().sequentional().sorted().count();
        long t01 = getNanoTime();

        System.out.println("Количество элементов values2: " + count2);
        displayExecTime(t00, t01, "Parallel sort занимает: ");

        t00 = getNanoTime();
        List<String> sortedValues2 = values2.parallelStream().sorted().collect(Collectors.toList());
        t01 = getNanoTime();
        displayExecTime(t00, t01, "Parallel sort в List занимает: ");
    }

    private static List<String> getRandomList(int maxCap) {
        List<String> values = new ArrayList<>(maxCap);

        System.out.println("Заполнение списка");
        long m0 = getNanoTime();

        for (int i = 0; i < maxCap; i++) {
            UUID uuid = UUID.randomUUID();
            values.add(uuid.toString());
        }

        long m1 = getNanoTime();
        displayExecTime(m0, m1, "Заполнение списка занимает: ");

        return values;
    }

    private static long getNanoTime() {
        long curTime = System.nanoTime();
        System.out.println(curTime);
        return curTime;
    }

    private static void displayExecTime(long n1, long n2, String op) {
        long mills = TimeUnit.NANOSECONDS.toMillis(n2 - n1);
        System.out.println(op + mills + ", mills\n");
    }

    private static void outputToFile() {

    }
}
