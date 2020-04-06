package saracoglu.fatih.covid19tracker.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import saracoglu.fatih.covid19tracker.model.CountryStatistics;
import saracoglu.fatih.covid19tracker.services.CoronavirusDataService;

import java.util.List;

@Controller
public class HomeController {

    private final CoronavirusDataService coronavirusDataService;

    public HomeController(CoronavirusDataService coronavirusDataService) {
        this.coronavirusDataService = coronavirusDataService;
    }

    @GetMapping("/")
    public String index(Model model) {
        List<CountryStatistics> countryStatisticsList = coronavirusDataService.getStatistics();
        model.addAttribute("global", coronavirusDataService.getStatisticsGlobal());
        model.addAttribute("turkeyStat", coronavirusDataService.getStatisticsTurkey());
        return "index";
    }

    @GetMapping("/worldwide")
    public String global(Model model) {
        model.addAttribute("countryStatistics", coronavirusDataService.getStatistics());
        return "worldwide";
    }
}
