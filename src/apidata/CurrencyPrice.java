package apidata;

import utils.JFCurrency;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// TODO: Switch to using hashmap instead of pair?

public class CurrencyPrice {

    // TODO: Turn into a list of hashMaps; one for each file?
    private static HashMap<String, Double> p = new HashMap<>();

    static double getPrice(JFCurrency targetCurrency, JFCurrency baseCurrency, LocalDateTime t) {
        String year = String.valueOf(t.getYear());
        // TODO: refactor
        String time = DateTime.time(t);
        String filename = "_" + year + ".json";

        System.out.println(targetCurrency + " -> " + baseCurrency);
        System.out.println(t + " -> " + time);

        //TODO: Wrap around if due to different conditions based on currencies
        if (targetCurrency == baseCurrency) {
            return 1;
        } else if (baseCurrency == JFCurrency.USD) {
            filename = System.getProperty("user.dir") +
                    File.separator +
                    "src/stockData/" +
                    targetCurrency.toString() +
                    filename;
        } else if (targetCurrency == JFCurrency.USD) {
            filename = System.getProperty("user.dir") +
                    File.separator +
                    "src/stockData/" +
                    targetCurrency.toString() +
                    filename;
        }

        System.out.println(filename);
        // TODO: merge searchFile and readFile
        // TODO: stop searching file after date (as it's in ascending order) (is there any point? do we want all in memory?)
        readFile(filename);
        Double value = searchFile(time);

//        for (HashMap.Entry entry:p.entrySet()) {
//            System.out.println(entry);
//        }

        System.out.println(value);

        return 0;
    }

    // searches the file for the given time
    private static Double searchFile(String time, Boolean isTargetUSD) {
        final Double firstValue = p.entrySet().iterator().next().getValue();
        final Double value = p.getOrDefault(time, firstValue);
        return (isTargetUSD) ? 1/value: value;
    }

    private static void readFile(String filename) {
        try (Stream<String> stream = Files.lines(Paths.get(filename))) {
            stream
                    .filter(line -> line.startsWith("[2"))
                    .map(CurrencyPrice::format)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // removes trailing comma if present
    // splits string into a pair of string to double which is added to the hashMap
    private static String format(String s) {
        if (s.endsWith(",")) {
            s = s.substring(0, s.length() - 1);
        }
        String fst = s.substring(1, s.indexOf(","));
        Double snd = Double.parseDouble(s.substring(s.indexOf(",")+1, s.length()-1));
        p.put(fst, snd);
        return s;
    }

}
