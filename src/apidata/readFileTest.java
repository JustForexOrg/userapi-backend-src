package apidata;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class readFileTest {

    private static List<Pair<String, Double>> p = new ArrayList<>();

    public static void main(String[] args) {
        String fileName = "src/stockData/test.json";
        fileName = System.getProperty("user.dir") + File.separator + fileName;

        readFile(fileName);

        for(Pair<String,Double> pair:p) {
            System.out.println(pair.getElement0());
            System.out.println(pair.getElement1());
        }


    }

    private static void readFile(String filename) {
        try (Stream<String> stream = Files.lines(Paths.get(filename))) {
            stream
                    .filter(line -> line.startsWith("[2"))
                    .map(readFileTest::removeComma)
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
