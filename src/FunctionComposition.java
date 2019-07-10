import java.util.function.Function;

public class FunctionComposition {
    public static void main(String[] args) {
        Function<String, String> trim = String::trim;

        String str = trim
                .andThen(String::toLowerCase)
                .andThen(StringBuilder::new)
                .andThen(StringBuilder::reverse)
                .andThen(StringBuilder::toString)
                .apply(" ABCDEFG ");
        System.out.println(str);

        String s = "ABCDEFG";

        StringBuilder sb = new StringBuilder(s.toLowerCase());
        String s0 = sb.reverse().toString();
        System.out.println(s0);

        String s1 = new StringBuilder(s.toLowerCase()).reverse().toString();
        System.out.println(s1);

        Converter<String, Integer> converter = (from) -> Integer.valueOf(from);
        Integer converted = converter.convert("123");
        System.out.println(converted);    // 123

        Converter<String, Integer> converter2 = Integer::valueOf;
        Integer converted2 = converter.convert("123");
        System.out.println(converted);   // 123

        Something something = new Something();

        Converter<String, String> converter3 = something::startsWith;
        String converted3 = converter3.convert("Java");
        System.out.println(converted3);    // "J"
    }

}
