package org.woehlke.logfileloader.core.services;

import org.woehlke.logfileloader.core.entities.HttpCode;

/**
 * Created with IntelliJ IDEA.
 * User: tw
 * Date: 15.09.13
 * Time: 22:05
 * To change this template use File | Settings | File Templates.
 */
public interface HttpCodeService {

    HttpCode createOrFetch(String httpCode);

    HttpCode find(String httpCode);
}
