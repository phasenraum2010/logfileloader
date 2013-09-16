package org.woehlke.logfileloader.core.services.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.woehlke.logfileloader.core.entities.Browser;
import org.woehlke.logfileloader.core.repositories.BrowserRepository;
import org.woehlke.logfileloader.core.services.BrowserService;

import javax.inject.Inject;

/**
 * Created with IntelliJ IDEA.
 * User: tw
 * Date: 15.09.13
 * Time: 22:07
 * To change this template use File | Settings | File Templates.
 */
@Service
public class BrowserServiceImpl implements BrowserService {

    @Inject
    private BrowserRepository browserRepository;

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
    public Browser createOrFetch(String browser) {
        Browser browserPers = new Browser();
        browserPers.setBrowser(browser);
        return browserRepository.save(browserPers);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = true)
    public Browser find(String browser) {
        return browserRepository.findByBrowser(browser);
    }
}
