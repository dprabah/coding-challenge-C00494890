package io.twodigits.urlshortener.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.transaction.Transactional;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Transactional
public class URL {
    /**
     * The unique ID of an URL
     */
    @Id
    private String id;

    /**
     * The URL for which a short URL is provided
     */
    private String url;

    /**
     * The ID of a user to which this URL belongs
     */
    private String user;

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getURL() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUser() {
        return this.user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
