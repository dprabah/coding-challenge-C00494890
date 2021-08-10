package io.twodigits.urlshortener.controller;

import io.twodigits.urlshortener.model.Stats;
import io.twodigits.urlshortener.model.URL;
import io.twodigits.urlshortener.model.dto.StatsDto;
import io.twodigits.urlshortener.service.StatsService;
import io.twodigits.urlshortener.service.URLShortenerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Optional;

@RestController
public class AppController {
    private final URLShortenerService urlShortenerService;
    private final StatsService statsService;

    @Autowired
    public AppController(URLShortenerService urlShortenerService, StatsService statsService){
        this.urlShortenerService = urlShortenerService;
        this.statsService = statsService;
    }

    @GetMapping("/s/{id}")
    public RedirectView retrieve(@PathVariable String id, HttpServletRequest request) {
        final RedirectView redirectView = new RedirectView();
        final Optional<URL> url = urlShortenerService.getURL(id);
        final Stats usageStatistics = new Stats();
        usageStatistics.setUserAgent(request.getHeader(HttpHeaders.USER_AGENT));
        usageStatistics.setTime(LocalDateTime.now());
        usageStatistics.setUrl(url.get());
        statsService.saveStatsForUrl(usageStatistics);
        redirectView.setUrl(url.get().getURL());
        return redirectView;
    }

    @PostMapping("/service/shorten")
    public String shorten(@RequestBody URL url) throws Exception {
        return urlShortenerService.addURL(url.getUser(), url.getURL()).getId();
    }

    @GetMapping("/service/stats")
    public StatsDto stats() {
        return statsService.retrieveStats();
    }

    @GetMapping("/service/delete/{user}/{id}")
    public RedirectView delete(@PathVariable String user, @PathVariable String id) {
        final RedirectView redirectView = new RedirectView();
        urlShortenerService.deleteURL(user, id);
        redirectView.setUrl("/statistics.html");
        return redirectView;
    }
}
