package io.twodigits.urlshortener.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Transactional
public class Stats {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;
    private String userAgent;
    private LocalDateTime time;
    @OneToOne(cascade = {CascadeType.ALL})
    @OnDelete(action = OnDeleteAction.CASCADE)
    private URL url;

    public URL getUrl() {
        return url;
    }

    public void setUrl(URL url) {
        this.url = url;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
