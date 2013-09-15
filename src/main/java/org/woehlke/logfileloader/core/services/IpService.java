package org.woehlke.logfileloader.core.services;

import org.woehlke.logfileloader.core.entities.Ip;

/**
 * Created with IntelliJ IDEA.
 * User: tw
 * Date: 15.09.13
 * Time: 22:05
 * To change this template use File | Settings | File Templates.
 */
public interface IpService {

    Ip createOrFetch(String ip);

    Ip find(String ip);
}
