package org.woehlke.logfileloader.core.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.woehlke.logfileloader.core.entities.Ip;

/**
 * Created with IntelliJ IDEA.
 * User: tw
 * Date: 03.09.13
 * Time: 15:51
 * To change this template use File | Settings | File Templates.
 */
public interface IpRepository extends JpaRepository<Ip,Long> {
    Ip findByIp(String ip);
}
