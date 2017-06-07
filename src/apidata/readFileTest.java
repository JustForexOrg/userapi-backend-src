package apidata;

import utils.JFCurrency;

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

        CurrencyPrice.getPrice(JFCurrency.EUR, JFCurrency.USD, /* date */);

        CurrencyPrice.

//                test that currency price works


    }


}
