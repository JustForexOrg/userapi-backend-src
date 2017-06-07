package apidata;

import utils.JFCurrency;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class readFileTest {

    private static List<Pair<String, Double>> p = new ArrayList<>();

    public static void main(String[] args) {
        String fileName = "src/stockData/test.json";
        fileName = System.getProperty("user.dir") + File.separator + fileName;

        LocalDateTime date = LocalDateTime.parse("2014-01-02T01:02:00");

        CurrencyPrice.getPrice(JFCurrency.EUR, JFCurrency.USD, date);



//                test that currency price works


    }


}
