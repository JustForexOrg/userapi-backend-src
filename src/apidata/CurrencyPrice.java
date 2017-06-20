package apidata;

import utils.JFCurrency;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class CurrencyPrice {

    private static HashMap<String,HashMap<String, Double>> prices = new HashMap<>();
    private static HashMap<String, Double> file = new HashMap<>();

    public static double getPrice(JFCurrency targetCurrency, JFCurrency baseCurrency, LocalDateTime t) {
        String year = String.valueOf(t.getYear());
        String time = DateWrapper.time(t);
        String filename = "_" + year + ".json";
        String fn;

        // Testing
//        System.out.println(targetCurrency + " -> " + baseCurrency);
//        System.out.println(t + " -> " + time);

        boolean isTargetUSD = false;
        String currency;
//        if (!file.isEmpty()) file.clear();

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

            fn = targetCurrency + filename;
            String targetFile = "stockData" + File.separator + fn;
            readFile(targetFile, fn);
            double target = searchFile(fn, time, false);

            return target/base;
        }

        fn = currency + filename;
        filename = "stockData" + File.separator + fn;
        readFile(filename, fn);

        return searchFile(fn, time, isTargetUSD);
    }

    // searches the file for the given time
    private static Double searchFile(String fn, String time, boolean isTargetUSD) {
        final HashMap<String, Double> rates;
        if (prices.containsKey(fn)) {
            rates = prices.get(fn);
        } else {
            System.out.println("---ERROR");
            readFile("stockData" + File.separator + fn, fn);
            rates = prices.get(fn);
        }
        final Double firstValue = rates.entrySet().iterator().next().getValue();
        final Double value = rates.getOrDefault(time, firstValue);
        return (isTargetUSD) ? 1/value: value;
    }

    private static void readFile(String filename, String fn) {
//        prices.keySet().forEach(System.out::println);
//        System.out.println("\n");
        if (!file.isEmpty()) System.out.println(file.entrySet().iterator().next().getValue());
        long start = System.nanoTime();

        if(!prices.containsKey(fn)) {
//            System.out.println("not here");

            Properties properties = new Properties();
            InputStream input = null;

            try {
                input = Thread.currentThread().getContextClassLoader().getResourceAsStream(filename);
                properties.load(input);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (input != null) try {
                    input.close();
                } catch (IOException ignore) {
                }
            }

            List<String> file = properties.stringPropertyNames().stream()
                    .filter(line -> line.startsWith("[1"))
                    .collect(Collectors.toList());

            prices.put(fn, makeFile(file));
//                    .map(CurrencyPrice::format)
//            System.out.println();

//        } else {
//            System.out.println("already here");
//            prices.put(fn, CurrencyPrice.file);
        }
        long end = System.nanoTime();
        long time = end - start;
//        System.out.println("\n" + time/10000 + "\n");
    }

    private static HashMap<String, Double> makeFile(List<String> file) {
        HashMap<String, Double> map = new HashMap<>();
        for (String s: file) {
            if (s.endsWith(",")) {
                s = s.substring(0, s.length() - 1);
            }
            String fst = s.substring(1, s.indexOf(","));
            Double snd = Double.parseDouble(s.substring(s.indexOf(",")+1, s.length()-1));
            map.put(fst, snd);
        }
        return map;
    }

    // removes trailing comma if present
    // splits string into a pair of string -> double which is added to the hashMap
    private static String format(String s) {
        if (s.endsWith(",")) {
            s = s.substring(0, s.length() - 1);
        }
        String fst = s.substring(1, s.indexOf(","));
        Double snd = Double.parseDouble(s.substring(s.indexOf(",")+1, s.length()-1));
        file.put(fst, snd);
        return s;
    }

}
