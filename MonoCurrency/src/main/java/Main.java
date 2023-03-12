import com.google.gson.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException {
        String JsonString = getStringURL("https://api.monobank.ua/bank/currency", "UTF-8");
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        CurrencyExchange[] currencyExchangesArray = gson.fromJson(JsonString, CurrencyExchange[].class);

        Map<Integer,String> currencyMap = new HashMap<>();
        currencyMap.put(840,"USD");
        currencyMap.put(978,"EUR");
        currencyMap.put(980,"UAH");

        CurrencyExchangeValue(currencyExchangesArray,currencyMap);
    }
    public static String getStringURL(String spec,String code) throws IOException {
        URL url = new URL(spec);
        URLConnection con = (HttpURLConnection) url.openConnection();
        String res = "";
        try(BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(),code))){
            String temp = "";
            for (;(temp = br.readLine()) != null;){
                res += temp + System.lineSeparator();
            }
        }
        return res;
    }
    public static void CurrencyExchangeValue(CurrencyExchange[] currencyExchangesArray,Map<Integer,String> currencyMap){
        for (CurrencyExchange currencyExchange:currencyExchangesArray) {
            if (currencyExchange.getRateSell() != 0.0){
                System.out.println(currencyMap.get(currencyExchange.getCurrencyCodeA()) + "/" + currencyMap.get(currencyExchange.getCurrencyCodeB()) + "-");
                System.out.println("Currency buy" + currencyExchange.getRateBuy());
                System.out.println();
                System.out.println("Currency sell" + currencyExchange.getRateSell());
                System.out.println();

            }
        }
    }
    }