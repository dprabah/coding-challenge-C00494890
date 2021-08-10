package io.twodigits.urlshortener.service;

import io.twodigits.urlshortener.model.URL;
import io.twodigits.urlshortener.repository.StatRepository;
import io.twodigits.urlshortener.repository.URLRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@RunWith(MockitoJUnitRunner.class)
public class URLShortenerServiceImplTest {
    @Mock
    URLRepository urlRepository;
    @Mock
    StatRepository statRepository;
    URLShortenerService subject;

    @Before
    public void setUp() {
        subject = new URLShortenerServiceImpl(urlRepository, statRepository);
    }

    @Test
    public void testAddURL(){
        // Arrange
        final URL url = getTestURL().get();

        // Act
        subject.addURL(url.getUser(), url.getURL());

        // Assert
        verify(urlRepository).save(any(URL.class));
    }

    private Optional<URL> getTestURL(){
        return Optional.of(new URL("shortened_url", "actual_url", "user"));
    }
}