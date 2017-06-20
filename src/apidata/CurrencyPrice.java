package apidata;

import utils.JFCurrency;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class CurrencyPrice {

    private static String[] fileList = {"CHF_2012.json", "CHF_2013.json", "CHF_2014.json", "CHF_2015.json", "CHF_2016.json",
            "EUR_2012.json", "EUR_2013.json", "EUR_2014.json", "EUR_2015.json", "EUR_2016.json",
            "GBP_2012.json", "GBP_2013.json", "GBP_2014.json", "GBP_2015.json", "GBP_2016.json",
            "JPY_2012.json", "JPY_2013.json", "JPY_2014.json", "JPY_2015.json", "JPY_2016.json"};

    private static HashMap<String,HashMap<String, Double>> prices = readAllFiles();

    public static HashMap<String, HashMap<String, Double>> readAllFiles() {
        HashMap<String, HashMap<String, Double>> p = new HashMap<>();
        for(int i = 0; i < fileList.length; i++) {
            String temp = fileList[i];
            fileList[i] = "stockData" + File.separator + temp;
            readFile(fileList[i], temp, p);
        }
        return p;
    }

    public static double getPrice(JFCurrency targetCurrency, JFCurrency baseCurrency, LocalDateTime t) {

//        System.out.println(Arrays.toString(fileList));
//        readAllFiles();
//        System.out.println(Arrays.toString(fileList));

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
            readFile(baseFile, fn, prices);
            double base = searchFile(fn, time, false);

            fn = targetCurrency + filename;
            String targetFile = "stockData" + File.separator + fn;
            readFile(targetFile, fn, prices);
            double target = searchFile(fn, time, false);

            return target/base;
        }

        fn = currency + filename;
        filename = "stockData" + File.separator + fn;
        readFile(filename, fn, prices);

        return searchFile(fn, time, isTargetUSD);
    }

    // searches the file for the given time
    private static Double searchFile(String fn, String time, boolean isTargetUSD) {
        final HashMap<String, Double> rates;
        if (prices.containsKey(fn)) {
            rates = prices.get(fn);
        } else {
            System.out.println("---ERROR");
            readFile("stockData" + File.separator + fn, fn, prices);
            rates = prices.get(fn);
        }
        final Double firstValue = rates.entrySet().iterator().next().getValue();
        final Double value = rates.getOrDefault(time, firstValue);
        return (isTargetUSD) ? 1/value: value;
    }

    // reads the file, parses it and populates a hashmap of string->doubles which is added to the global hashmap
    private static void readFile(String filename, String fn, HashMap<String, HashMap<String, Double>> p) {if (filename.startsWith("stockData" + File.separator + "stockData")) {
            filename = fn;
        }

        if(!p.containsKey(fn)) {
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

            p.put(fn, makePairs(file));
        }
    }

    private static HashMap<String, Double> makePairs(List<String> file) {
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
}
