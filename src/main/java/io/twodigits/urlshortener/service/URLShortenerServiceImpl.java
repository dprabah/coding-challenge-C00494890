package io.twodigits.urlshortener.service;

import io.twodigits.urlshortener.model.URL;
import io.twodigits.urlshortener.repository.StatRepository;
import io.twodigits.urlshortener.repository.URLRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class URLShortenerServiceImpl implements URLShortenerService {
    private final URLRepository repository;
    private final StatRepository statRepository;

    @Autowired
    public URLShortenerServiceImpl(URLRepository repository, StatRepository statRepository){
        this.repository = repository;
        this.statRepository = statRepository;
    }

    @Override
    public Iterable<URL> listURLs(String user) {
        return repository.findByUser(user);
    }

    @Override
    public URL addURL(String user, String url) {
        final URL urlToStore = new URL();
        urlToStore.setUser(user);
        urlToStore.setId(getUniqueShortenedURL());
        urlToStore.setUrl(appendProtocol(url));
        repository.save(urlToStore);
        return urlToStore;
    }

    @Override
    public Optional<URL> getURL(String user, String id) {
        return repository.findByIdAndUser(id, user);
    }

    @Override
    public Optional<URL> getURL(String id) {
        Optional<URL> url = repository.findById(id);
        return url;
    }

    @Override
    public void deleteURL(String user, String id) {
        Optional<URL> url = repository.findByIdAndUser(id, user);
        System.out.println(url);
        if(url.isPresent()) {
            repository.delete(url.get());
        }
    }

    private String getUniqueShortenedURL(){
        String id;
        do{
            id = RandomStringUtils.random(12, true, true);
        }while(repository.findById(id).isPresent());
        return id;
    }

    private String appendProtocol(final String url){
        if (url.startsWith("http")){
            return url;
        }else{
            return "https://" + url;
        }
    }
}
