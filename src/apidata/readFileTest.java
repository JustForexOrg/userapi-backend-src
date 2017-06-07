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
                    .map(String::toUpperCase)
                    .collect(Collectors.toList());

        } catch (IOException e) {
            e.printStackTrace();
        }

        list.forEach(System.out::println);
    }

    private static String removeComma(String s) {
        return (s.endsWith(",")) ? s.substring(0, s.length() - 1): s;
    }
}
