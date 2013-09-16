package org.woehlke.logfileloader.core.services;

import org.woehlke.logfileloader.core.entities.Browser;

/**
 * Created with IntelliJ IDEA.
 * User: tw
 * Date: 15.09.13
 * Time: 22:04
 * To change this template use File | Settings | File Templates.
 */
public interface BrowserService {
    Browser createOrFetch(String browser);

    Browser find(String browser);
}
