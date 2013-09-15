package org.woehlke.logfileloader.core.services;

import org.woehlke.logfileloader.core.entities.Request;

/**
 * Created with IntelliJ IDEA.
 * User: tw
 * Date: 15.09.13
 * Time: 22:06
 * To change this template use File | Settings | File Templates.
 */
public interface RequestService {

    Request createOrFetch(String requestLine);

    Request find(String requestLine);
}
