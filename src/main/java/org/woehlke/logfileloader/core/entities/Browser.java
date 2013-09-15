package org.woehlke.logfileloader.core.entities;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: tw
 * Date: 03.09.13
 * Time: 15:34
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "BROWSER")
public class Browser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, length=512, unique = true)
    private String browser;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBrowser() {
        return browser;
    }

    public void setBrowser(String browser) {
        this.browser = browser;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Browser)) return false;

        Browser browser1 = (Browser) o;

        if (!browser.equals(browser1.browser)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return browser.hashCode();
    }

    @Override
    public String toString() {
        return "Browser{" +
                "id=" + id +
                ", browser='" + browser + '\'' +
                '}';
    }
}
