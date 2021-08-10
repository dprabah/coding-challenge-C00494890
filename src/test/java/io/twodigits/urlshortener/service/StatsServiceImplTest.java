package io.twodigits.urlshortener.service;

import io.twodigits.urlshortener.model.Stats;
import io.twodigits.urlshortener.model.URL;
import io.twodigits.urlshortener.model.dto.StatsDto;
import io.twodigits.urlshortener.repository.StatRepository;
import io.twodigits.urlshortener.repository.URLRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@RunWith(MockitoJUnitRunner.class)
public class StatsServiceImplTest {
    @Mock
    StatRepository statRepository;
    @Mock
    URLRepository urlRepository;
    StatsService subject;
    @Before
    public void setUp() {
        subject = new StatsServiceImpl(statRepository, urlRepository);
    }

    @Test
    public void testRetrieveStats_correctValues_successful(){
        // Arrange
        when(statRepository.findAll()).thenReturn(getTestDataForStats());
        when(urlRepository.findById("shortened_url")).thenReturn(getTestURL());
        final StatsDto.Details details = new StatsDto.Details();
        details.setUrl(getTestURL().get());
        details.setCount(2);
        details.setUser(null);

        // Act
        final StatsDto response = subject.retrieveStats();

        // Assert
        assertEquals(details, response.getUrlCounts().get(0));
    }

    @Test
    public void testSaveStatsForUrl(){
        // Arrange
        final Stats stat = getTestDataForStats().iterator().next();

        // Act
        subject.saveStatsForUrl(stat);

        // Assert
        verify(statRepository).save(stat);
    }

    private Iterable<Stats> getTestDataForStats(){
        final URL url = getTestURL().get();
        final Stats stat1 = new Stats();
        stat1.setUrl(url);
        stat1.setUserAgent("user_agent");
        stat1.setTime(LocalDateTime.now());

        final Stats stat2 = new Stats();
        stat2.setUrl(url);
        stat2.setUserAgent("user_agent");
        stat2.setTime(LocalDateTime.now());

        final List<Stats> stats = new ArrayList<>();
        stats.add(stat1);
        stats.add(stat2);

        return stats;
    }

    private Optional<URL> getTestURL(){
            return Optional.of(new URL("shortened_url", "actual_url", "user"));
    }
}