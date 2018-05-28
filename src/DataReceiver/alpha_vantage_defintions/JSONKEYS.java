package DataReceiver.alpha_vantage_defintions;
import java.util.Set;
import java.util.ArrayList;
import java.util.List;

public class JSONKEYS {

    public static final String META_DATA = "Meta Data";
    public static final String OPEN = "1. open";
    public static final String HIGH = "2. high";
    public static final String LOW = "3. low";
    public static final String CLOSE = "4. close";
    public static final String VOLUME = "5. volume";
    public static final String ADJUSTED_CLOSE = "5. adjusted close";
    public static final String ADJUSTED_VOLUME = "6. volume";
    public static final String DIVIDEND_AMOUNT = "7. dividend amount";

    public static final String SYMBOL = "1. symbol";
    public static final String PRICE = "2. price";
    public static final String STOCK_VOLUME = "3. volume";
    public static final String TIMESTAMP = "4. timestamp";



    public static final String resolveKeyName(Set<String> keys) {
        List<String> list = new ArrayList<String>(keys);
        System.out.println(list);
        return list.get(1);
    }
}