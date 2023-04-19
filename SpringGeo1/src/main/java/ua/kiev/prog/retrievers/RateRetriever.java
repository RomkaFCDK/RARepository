package ua.kiev.prog.retrievers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ua.kiev.prog.json.Rate;

@Component
public class RateRetriever {

    private static final String URL = "http://data.fixer.io/api/latest?access_key=30e7ff0de73cf99ac8842101abce80ca";
    private static final String URL2 = "https://api.apilayer.com/currency_data/live?source=UAH&currencies=EUR";
    private static final String URL3 = "https://api.apilayer.com/exchangerates_data/latest?symbols=UAH&base=EUR"

    String key = "6omDDPOZw4ZWzBuonjg75OAzaAn19Cbf"
    @Cacheable("rates") // Redis
    public Rate getRate() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("apikey", key);
        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Rate> response = restTemplate.exchange(
                URL3,
                HttpMethod.GET,
                entity,
                Rate.class
        );
        return response.getBody();
    }
    @Autowired
    private CacheManager cacheManager;

    @Scheduled(cron = "0 0/1 * * * ?")
    public void clearCacheSchedule() {
        for (String name : cacheManager.getCacheNames()) {
            cacheManager.getCache(name).clear();
        }
    }

}
