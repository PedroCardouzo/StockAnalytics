package DataReceiver.alpha_vantage_defintions;

import java.security.InvalidParameterException;

public class Intervals {

    public static final String ONE_MIN = "1min";
    public static final String FIVE_MIN = "5min";
    public static final String FIFTEEN_MIN = "15min";
    public static final String THIRTY_MIN = "30min";
    public static final String SIXTY_MIN = "60min";

    public static String transformInputIntoFormalParameter(String input){
        switch(input){
            case "1": return ONE_MIN;
            case "5": return FIVE_MIN;
            case "15": return FIFTEEN_MIN;
            case "30": return THIRTY_MIN;
            case "60": return SIXTY_MIN;
            default: throw new InvalidParameterException("Invalid Parameter: " + input);
        }
    }

}
