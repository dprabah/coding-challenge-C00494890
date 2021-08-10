package io.twodigits.urlshortener.service;

import io.twodigits.urlshortener.model.Stats;
import io.twodigits.urlshortener.model.dto.StatsDto;
import io.twodigits.urlshortener.repository.StatRepository;
import io.twodigits.urlshortener.repository.URLRepository;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class StatsServiceImpl implements StatsService{
    private final StatRepository statRepository;
    private final URLRepository urlRepository;

    @Autowired
    public StatsServiceImpl(@NonNull StatRepository statRepository, @NonNull URLRepository urlRepository){
        this.statRepository = statRepository;
        this.urlRepository = urlRepository;
    }

    @Override
    public void saveStatsForUrl(Stats stat) {
        statRepository.save(stat);
    }

    @Override
    public StatsDto retrieveStats() {
        Map<String, Integer> urlCounts = new HashMap<>();
        StatsDto usageStats = new StatsDto();

        statRepository.findAll().forEach(stat -> urlCounts.merge(stat.getUrl().getId(), 1, Integer::sum));
        sortHashMapByValue(urlCounts);
        urlCounts.forEach((key, val) -> {
            StatsDto.Details details = new StatsDto.Details();
            details.setUrl(urlRepository.findById(key).orElse(null));
            details.setCount(val);
            usageStats.setUrlCounts(details);
        });

        return usageStats;
    }

    private <K, V> Map<K, V> sortHashMapByValue(Map<K, V> toSort){
        return toSort.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue((Comparator<? super V>) Comparator.reverseOrder()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));
    }
}
