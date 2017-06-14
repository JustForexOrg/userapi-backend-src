package apidata;

import utils.JFCurrency;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CurrencyPrice {

    // TODO: Turn into a list of hashMaps; one for each file?
    private static HashMap<String, Double> p = new HashMap<>();
//    private static Set<HashMap<String, Double>> prices = new HashSet<>();

    public static double getPrice(JFCurrency targetCurrency, JFCurrency baseCurrency, LocalDateTime t) {
        String year = String.valueOf(t.getYear());
        // TODO: refactor datetime
        String time = DateWrapper.time(t);
        String filename = "_" + year + ".json";

        // Testing
//        System.out.println(targetCurrency + " -> " + baseCurrency);
//        System.out.println(t + " -> " + time);

        boolean isTargetUSD = false;
        String currency = "";

        if (targetCurrency == baseCurrency) {
            return 1;
        } else if (baseCurrency == JFCurrency.USD) {
            currency = targetCurrency.toString();
        } else if (targetCurrency == JFCurrency.USD) {
            currency = baseCurrency.toString();
            isTargetUSD = true;
        } else {
            String baseFile = System.getProperty("user.dir") + File.separator +
                    "src" + File.separator +
                    "stockData" + File.separator +
                    baseCurrency + filename;
            readFile(baseFile);
            double base = searchFile(baseFile, false);
//            System.out.println(base);

            p.clear();

            String targetFile = System.getProperty("user.dir") + File.separator +
                    "src" + File.separator +
                    "stockData" + File.separator +
                    targetCurrency + filename;
            readFile(targetFile);
            double target = searchFile(targetFile, false);
//            System.out.println(target);

            return target/base;
        }

//        filename = System.getProperty("user.dir") + File.separator +
//                "src" + File.separator +
//                "stockData" + File.separator +
//                currency + filename;

        Properties properties = new Properties();
        InputStream input = null;

        try {
            input = Thread.currentThread().getContextClassLoader().getResourceAsStream("EUR_2012.json");
            properties.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (input != null) try { input.close(); } catch (IOException ignore) {}
        }

        System.out.println(input);

        System.out.println(Arrays.toString(properties.stringPropertyNames().toArray()));

//        try {
//
//            // read until a single byte is available
//            while(input.available()>0) {
//
//                // read the byte and convert the integer to character
//                char c = (char)input.read();
//
//                // print the characters
//                System.out.println("Char: "+c);
//            }
//        } catch(Exception e) {
//            // if any I/O error occurs
//            e.printStackTrace();
//        } finally {
////            // releases any system resources associated with the stream
////            if(inStream!=null)
////                inStream.close();
//            if(input!=null)
//                try {
//                    input.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//        }

        // Testing
        // System.out.println(filename);
        // TODO: merge searchFile and readFile
        // TODO: stop searching file after date (as it's in ascending order) (is there any point? do we want all in memory?)
//        readFile(filename);

        // Testing
//        for (HashMap.Entry entry:p.entrySet()) {
//            System.out.println(entry);
//        }

//        return searchFile(time, isTargetUSD);
        return 1;
    }

    // searches the file for the given time
    private static Double searchFile(String time, boolean isTargetUSD) {
        final Double firstValue = p.entrySet().iterator().next().getValue();
        final Double value = p.getOrDefault(time, firstValue);
        return (isTargetUSD) ? 1/value: value;
    }

    private static void readFile(String filename) {
        try (Stream<String> stream = Files.lines(Paths.get(filename))) {
            stream
                    .filter(line -> line.startsWith("[1"))
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
