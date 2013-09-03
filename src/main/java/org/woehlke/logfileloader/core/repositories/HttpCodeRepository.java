package org.woehlke.logfileloader.core.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.woehlke.logfileloader.core.entities.HttpCode;

/**
 * Created with IntelliJ IDEA.
 * User: tw
 * Date: 03.09.13
 * Time: 15:49
 * To change this template use File | Settings | File Templates.
 */
public interface HttpCodeRepository extends JpaRepository<HttpCode,Long> {
    HttpCode findByCode(String code);
}
