package org.woehlke.logfileloader.core.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.woehlke.logfileloader.core.entities.Request;

/**
 * Created with IntelliJ IDEA.
 * User: tw
 * Date: 03.09.13
 * Time: 15:50
 * To change this template use File | Settings | File Templates.
 */
public interface RequestRepository extends JpaRepository<Request,Long> {
    Request findByRequest(String request);
}
