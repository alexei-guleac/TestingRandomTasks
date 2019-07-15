import java.io.*;
import java.util.Properties;
import java.util.Set;

public class PropertiesList {
    public static void main(String[] args) throws IOException {

        Properties defList = new Properties();
        defList.put("Флорида", "Тэлесси");
        defList.put("Bиcкoнcин", "Мэдисон");

        Properties capitals = new Properties(defList);
        capitals.put("Иллинойс", "Спрингфилд");
        capitals.put("Mиccypи", "Джефферсон-Сити");
        capitals.put("Вашингтон", "Олимпия");
        capitals.put("Калифорния", "Сакраменто");
        capitals.put("Индиaнa", "Индианаполис");

        //получить множество ключей
        Set<?> states = capitals.keySet();

        // вывести все штаты и их столицы
        for (Object name : states)
            System.out.println("Cтoлицa штата " + name + " - " + capitals.getProperty((String) name) + ".");
        System.out.println();

        //Теперь штат Флорида будет найден
        //в списке по умолчанию
        String str = capitals.getProperty("Флopидa");
        System.out.println("Cтoлицa Флориды - " + str + ".");

        Properties ht = new Properties();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String name, number;
        FileInputStream fin = null;
        boolean changed = false;

        //Попытаться открыть файл phonebook.dat
        try {
            fin = new FileInputStream("phonebook.dat");
        } catch (FileNotFoundException e) { //игнорировать отсутствующий файл
        }

        /* Если телефонная книга уже существует, загрузить
        существующие телефонные номера. */
        try {
            if (fin != null) {
                ht.load(fin);
                fin.close();
            }
        } catch (IOException е) {
            System.out.println("Oшибкa чтения файла.");
        }

        //разрешить пользователю вводить новые имена и
        //номера телефонов абонентов
        do {
            System.out.println("Bвeдитe имя ('выход' для завершения): ");
            name = br.readLine();

            if (name.equals("выxoд")) continue;

            System.out.println("Bвeдитe номер: ");
            number = br.readLine();
            ht.put(name, number);
            changed = true;
        } while (!name.equals("выход"));

        // сохранить телефонную книгу, если она изменилась
        if (changed) {
            FileOutputStream fout = new FileOutputStream("phonebook.dat");
            ht.store(fout, "Телефонная книга");
            fout.close();
        }

        //искать номер по имени абонента
        do {
            System.out.println("Bвeдитe имя для поиска ('выход' для завершения):");
            name = br.readLine();

            if (name.equals("выxoд")) continue;

            number = (String) ht.get(name);
            System.out.println(number);
        } while (!name.equals("выxoд"));
    }
}
