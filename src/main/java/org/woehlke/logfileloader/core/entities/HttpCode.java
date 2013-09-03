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
@Table(name="HTTPCODE")
public class HttpCode {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, unique = true)
    private String code;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof HttpCode)) return false;

        HttpCode httpCode = (HttpCode) o;

        if (!code.equals(httpCode.code)) return false;
        if (!id.equals(httpCode.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + code.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "HttpCode{" +
                "id=" + id +
                ", code='" + code + '\'' +
                '}';
    }
}
