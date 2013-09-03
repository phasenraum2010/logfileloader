package org.woehlke.logfileloader.core.entities;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: tw
 * Date: 03.09.13
 * Time: 15:33
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name="REQUEST")
public class Request {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, unique = true)
    private String request;

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Request)) return false;

        Request request1 = (Request) o;

        if (!request.equals(request1.request)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return request.hashCode();
    }

    @Override
    public String toString() {
        return "Request{" +
                "id=" + id +
                ", request='" + request + '\'' +
                '}';
    }
}
