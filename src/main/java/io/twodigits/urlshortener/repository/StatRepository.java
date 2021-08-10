package io.twodigits.urlshortener.repository;

import io.twodigits.urlshortener.model.Stats;
import io.twodigits.urlshortener.model.URL;
import org.springframework.data.repository.CrudRepository;

public interface StatRepository extends CrudRepository<Stats, String> {
    void deleteByUrl(URL url);
}
