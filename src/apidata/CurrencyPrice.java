package apidata;

import utils.JFCurrency;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CurrencyPrice {

    private static List<Pair<String, Double>> p = new ArrayList<>();

    float getPrice(JFCurrency targetCurrency, JFCurrency baseCurrency, LocalDateTime t) {
        String year = String.valueOf(t.getYear());
        DateTime time  = new DateTime(t);
        String filename = "_"+year+".json";

        if(baseCurrency == JFCurrency.USD) {

        } else {
            filename = System.getProperty("user.dir") +
                       File.separator +
                       "src/stockData/" +
                       targetCurrency.toString() +
                       filename;

            readFile(filename);

            for(Pair<String,Double> pair:p) {
                System.out.println(pair.getElement0());
                System.out.println(pair.getElement1());
            }
            // open file as array of arrays
            // search outer array for inner head of arrays & return the tail
        }
        return 0;
    }

    static void readFile(String filename) {
        try (Stream<String> stream = Files.lines(Paths.get(filename))) {
            stream
                    .filter(line -> line.startsWith("[2"))
                    .map(CurrencyPrice::removeComma)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String removeComma(String s) {
        if (s.endsWith(",")) {
            s = s.substring(0, s.length() - 1);
        }
        String fst = s.substring(1, s.indexOf(","));
        Double snd = Double.parseDouble(s.substring(s.indexOf(",")+1, s.length()-1));
        p.add(new Pair<>(fst, snd));
        return s;
    }

}
