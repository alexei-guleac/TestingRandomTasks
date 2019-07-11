import java.util.*;
import java.util.stream.Collectors;

public class CollectorGroup {
    public static void main(String[] args) {
        List<Human> humans = Arrays.asList(
                new Human("Ned", "Stark", 1),
                new Human("Robb", "Stark", 2),
                new Human("Arya", "Stark", 1),
                new Human("Aegon", "Targaryen", 6),
                new Human("Daenerys", "Targaryen", 4),
                new Human("Jaime", "Lannister", 1),
                new Human("Tyrion", "Lannister", 3));

        HashMap<String, List<Human>> map = new HashMap<>();

        for (Human human : humans) {
            String surname = human.getSurname();
            if (!map.containsKey(surname)) { // если фамилии еще нет - создаем новый список
                List<Human> humanList = new ArrayList<>();
                humanList.add(human);
                map.put(surname, humanList);
            } else {                        // если фамилия есть - добавляем представителя семьи =)
                List<Human> humanList = map.get(surname);
                humanList.add(human);
            }
        }
        System.out.println(map);



        HashMap<String, List<Human>> mapSurnames = humans.stream().collect(Collectors.groupingBy(Human::getSurname));
        System.out.println(mapSurnames);

        HashMap<String, Long> mapCount = humans.stream()
                .collect(Collectors.groupingBy(Human::getSurname, Collectors.counting()));

        System.out.println(mapCount);       //output {Lannister=2, Targaryen=2, Stark=3}

        HashMap<String, Integer> mapFriendsSum = humans.stream()
                .collect(Collectors.groupingBy(Human::getSurname, Collectors.summingInt(Human::getFriendsAmount)));

        System.out.println(mapFriendsSum); //output {Lannister=4, Targaryen=10, Stark=4}

        HashMap<String, Set<String>> mapSet = humans.stream()
                .collect(Collectors.groupingBy(Human::getSurname,                   //группируем по фамилии
                        Collectors.mapping(Human::getName, Collectors.toSet())));   // собираем имена в Set

        System.out.println(mapSet);
    }
}
