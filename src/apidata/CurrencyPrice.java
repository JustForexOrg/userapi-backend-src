package apidata;

import utils.JFCurrency;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class CurrencyPrice {

    // TODO: Turn into a list of hashMaps; one for each file?
    private static HashMap<String, Double> p = new HashMap<>();
    private static HashMap<String,HashMap<String, Double>> prices = new HashMap<>();

    public static double getPrice(JFCurrency targetCurrency, JFCurrency baseCurrency, LocalDateTime t) {
        String year = String.valueOf(t.getYear());
        // TODO: refactor datetime
        String time = DateWrapper.time(t);
        String filename = "_" + year + ".json";
        String fn;

        // Testing
//        System.out.println(targetCurrency + " -> " + baseCurrency);
//        System.out.println(t + " -> " + time);

        boolean isTargetUSD = false;
        String currency;

        if (targetCurrency == baseCurrency) {
            return 1;
        } else if (baseCurrency == JFCurrency.USD) {
            currency = targetCurrency.toString();
        } else if (targetCurrency == JFCurrency.USD) {
            currency = baseCurrency.toString();
            isTargetUSD = true;
        } else {
            fn = baseCurrency + filename;
            String baseFile = "stockData" + File.separator + fn;
            readFile(baseFile, fn);
            double base = searchFile(fn, time, false);
//            System.out.println(base);

            p.clear();

            fn = targetCurrency + filename;
            String targetFile = "stockData" + File.separator + fn;
            readFile(targetFile, fn);
            double target = searchFile(fn, time, false);
//            System.out.println(target);

            return target/base;
        }

        fn = currency + filename;
        filename = "stockData" + File.separator + fn;
        readFile(filename, fn);


        // Testing
        // System.out.println(filename);
        // TODO: merge searchFile and readFile
        // TODO: stop searching file after date (as it's in ascending order) (is there any point? do we want all in memory?)
//        readFile(filename);

        // Testing
//        for (HashMap.Entry entry:p.entrySet()) {
//            System.out.println(entry);
//        }

        return searchFile(fn, time, isTargetUSD);
//        return 1;
    }

    // searches the file for the given time
    private static Double searchFile(String fn, String time, boolean isTargetUSD) {
        final HashMap<String, Double> rates = prices.getOrDefault(fn, p);
        final Double firstValue = rates.entrySet().iterator().next().getValue();
        final Double value = rates.getOrDefault(time, firstValue);
        return (isTargetUSD) ? 1/value: value;
    }

    private static void readFile(String filename, String fn) {
        Properties properties = new Properties();
        InputStream input = null;

        try {
            input = Thread.currentThread().getContextClassLoader().getResourceAsStream(filename);
            properties.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (input != null) try { input.close(); } catch (IOException ignore) {}
        }

        properties.stringPropertyNames().stream()
                .filter(line -> line.startsWith("[1"))
                .map(CurrencyPrice::format)
                .collect(Collectors.toList());

        prices.put(fn, p);
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
