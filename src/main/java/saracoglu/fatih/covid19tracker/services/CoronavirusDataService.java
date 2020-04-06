package saracoglu.fatih.covid19tracker.services;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import saracoglu.fatih.covid19tracker.model.CountryStatistics;

import javax.annotation.PostConstruct;
import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
public class CoronavirusDataService {

    private final static String DATA_SOURCE = "https://api.covid19api.com/summary";

    private List<CountryStatistics> statistics = new ArrayList<>();
    private CountryStatistics statisticsTurkey;
    private CountryStatistics statisticsGlobal = new CountryStatistics();

    public List<CountryStatistics> getStatistics() {
        return statistics;
    }

    public CountryStatistics getStatisticsTurkey() {
        return statisticsTurkey;
    }

    public CountryStatistics getStatisticsGlobal() {
        return statisticsGlobal;
    }

    public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
        InputStream is = new URL(url).openStream();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            JSONObject json = new JSONObject(jsonText);
            return json;
        } finally {
            is.close();
        }
    }

    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    public String formatDate(String date) {
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.ENGLISH);
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy", Locale.ENGLISH);
        LocalDate localDate = LocalDate.parse(date, inputFormatter);
        return outputFormatter.format(localDate);
    }

    @PostConstruct
    public void fetchData() throws IOException, InterruptedException {
        JSONObject jsonObject = readJsonFromUrl(DATA_SOURCE);
        JSONArray jsonArray = jsonObject.getJSONArray("Countries");
        List<CountryStatistics> newStatistics = new ArrayList<>();
        for (int i = 0 ; i < jsonArray.length(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);
            CountryStatistics countryStat = new CountryStatistics();
            String country = obj.getString("Country");
            countryStat.setCountry(country);
            countryStat.setConfirmed(obj.getInt("TotalConfirmed"));
            countryStat.setDeaths(obj.getInt("TotalDeaths"));
            countryStat.setRecovered(obj.getInt("TotalRecovered"));
            countryStat.setLastUpdate(formatDate(obj.getString("Date")));
            if (country.toLowerCase().equals("turkey")) {
                statisticsTurkey = countryStat;
            }
            newStatistics.add(countryStat);
        }
        statistics = newStatistics;

        JSONObject obj = jsonObject.getJSONObject("Global");
        statisticsGlobal.setCountry("global");
        statisticsGlobal.setConfirmed(obj.getInt("TotalConfirmed"));
        statisticsGlobal.setDeaths(obj.getInt("TotalDeaths"));
        statisticsGlobal.setRecovered(obj.getInt("TotalRecovered"));
    }
}
