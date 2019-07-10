import java.util.function.Function;
import java.util.function.UnaryOperator;

public class ComposeAndThen {
    public static void main(String[] args) {
        //v1
        Function<Integer, String> iToHex = i -> "'" + Integer.toHexString(i) + "'";
        Function<Integer, String> itoUpperHex = iToHex.andThen(String::toUpperCase);       //так удобнее
        Function<Integer, String> itoUpperHex1 =
                ((Function<String, String>) String::toUpperCase).compose(iToHex);          //чем так

        //v2
        UnaryOperator<String> quote = s -> "'" + s + "'";
        Function<Integer,String> iToHexx = quote.compose(Integer::toHexString);             //так удобнее
        Function<Integer,String> iToHexx1
                = ((Function<Integer,String>)Integer::toHexString).andThen(quote);         //чем так

        Integer i = 10;
        //String test = "TestString";

        System.out.println(itoUpperHex.apply(i));
        System.out.println(iToHexx.apply(i));
    }
}
