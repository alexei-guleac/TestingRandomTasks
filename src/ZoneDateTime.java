import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class ZoneDateTime {
    private static final LocalDateTime LDT = LocalDateTime.now();

    public static void main(String[] args) {
        List<String> zones = new ArrayList<>(ZoneId.getAvailableZoneIds());

        //HashMap<String, String> map = zones.stream()
        //        .collect(Collectors.toMap(zone -> zone, ZoneDateTime::getOffset));

//        TreeMap<String, String> sortedMap = new TreeMap<>(map);
//        sortedMap.forEach((zone, offset) -> System.out.printf("%s (UTC%s) \n", zone, offset));
    }

    private static String getOffset(String zone) {
        ZonedDateTime zdt = LDT.atZone(ZoneId.of(zone));
        return zdt.getOffset().getId().replace("Z", "+00:00");
    }
}
