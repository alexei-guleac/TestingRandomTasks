import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class ConcurrentModification {

    public static void main(String[] args) {
        collectionRemove();
    }

    static void collectionRemove() {
        List<Integer> listOne = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8);
        //removeOddWithForEach(listOne);        //UnsupportedOperationException
        //removeOddWithIterator(listOne);       //UnsupportedOperationException
        System.out.println("listOne");
        listOne.forEach((n) -> System.out.print(n + ", "));
        System.out.println();

        List<Integer> listTwo = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8));
        //removeOddWithForEach(listTwo);       //ConcurrentModificationException
        removeOddWithIterator(listTwo);
        System.out.println("listTwo");
        listTwo.forEach((n) -> System.out.print(n + ", "));
        System.out.println();
    }

    private static void removeOddWithForEach(List<Integer> list) {
        for (Integer num : list) {
            if ((num & 0x1) == 0x0) {   //нечётные числа
                list.remove(num);
            }
        }
    }

    private static void removeOddWithIterator(List<Integer> list) {
        Iterator<Integer> numIterator = list.iterator();
        while (numIterator.hasNext()) {
            Integer val = numIterator.next();
            if ((val & 0x1) == 0x1) {   //нечётные числа
                numIterator.remove();
            }
        }
    }
}
