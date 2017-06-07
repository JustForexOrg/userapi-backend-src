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
    public static void main(String[] args) {
        String fileName = "src/stockData/test.json";
        fileName = System.getProperty("user.dir") + File.separator + fileName;
        List<String> list = new ArrayList<>();

        try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
            list = stream
                    .filter(line -> line.startsWith("[2"))
                    .map(readFileTest::removeComma)
                    .collect(Collectors.toList());

        } catch (IOException e) {
            e.printStackTrace();
        }

        list.forEach(System.out::println);

        String j = list.get(0);

        List<Pair<String, Double>> l = makePair(list);
    }

    private static List<Pair<String, Double>> makePair(List<String> str) {
        List<Pair<String, Double>> p = new ArrayList<>();
        for (String s: str) {
            String fst = s.substring(1, s.indexOf(","));
            Double snd = Double.parseDouble(s.substring(s.indexOf(",")+1, s.length()-1));
            System.out.println(fst);
            System.out.println(snd);
            p.add(new Pair<String, Double>(fst, snd));
        }
        return p;
    }

    private static String removeComma(String s) {
        return (s.endsWith(",")) ? s.substring(0, s.length() - 1): s;
    }
}
