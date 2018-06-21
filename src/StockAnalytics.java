import DataAnalysis.*;
import Parser.Parser;
import java.util.Scanner;

/*************************************************************************************************************
 * Allowed commands:                                                                                         *
 * pull <stock_key> <type> [<sampling_size>] as <name>                                                       *
 * pull <stock_key> intraday <minutes> [<sampling_size>] as <name>                                           *
 *     where <minutes> can be: 1, 5, 15, 30 or 60 only                                                       *
 * new graph <graph_name>                                                                                    *
 * <graph_name> <- <analysis_id> <field>: <args>                                                             *
 *     where                                                                                                 *
 *         <field> is the field (close, open, high, low ..) which we will get the data from                  *
 * <graph_name> <- obv: <args>                                                                               *
 *     obv selects fields by default                                                                         *
 *         <args> is a sequence os names of stocks that have been pulled (you may use a group here instead)  *
 * group <args> as <group_name>                                                                              *
 *     where                                                                                                 *
 *         <args> is a sequence os names of stocks that have been pulled (you may use a group here instead)  *
 * push <stock_key> to <group_name>                                                                          *
 * remove <stock_key> from <group_name>                                                                      *
 * show <graph_name>                                                                                         *
 *************************************************************************************************************/

public class StockAnalytics {

    public static void main(String[] args) throws java.lang.Exception {

        DataAnalysis analyser = new DataAnalyzerLocal(); // new DataAnalyzerRFC("localhost", 1333);

        Parser parser = new Parser(analyser);
        boolean loop = true;
        Scanner input = new Scanner(System.in);


        // parser.parse("pull amd monthly as amd_mon");
        // parser.parse("pull msft monthly as msft_mon");
        // parser.parse("new graph g");
        // parser.parse("g <- sma close: msft_mon | amd_mon");
        // parser.parse("show g");
        while(loop){
            System.out.print(":: ");
            loop = parser.parse(input.nextLine());
        }

    }
}

