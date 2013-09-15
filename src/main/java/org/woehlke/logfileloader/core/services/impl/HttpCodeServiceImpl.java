package org.woehlke.logfileloader.core.services.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.woehlke.logfileloader.core.entities.HttpCode;
import org.woehlke.logfileloader.core.repositories.HttpCodeRepository;
import org.woehlke.logfileloader.core.services.HttpCodeService;

import javax.inject.Inject;

/**
 * Created with IntelliJ IDEA.
 * User: tw
 * Date: 15.09.13
 * Time: 22:07
 * To change this template use File | Settings | File Templates.
 */
@Service
public class HttpCodeServiceImpl implements HttpCodeService {

    @Inject
    private HttpCodeRepository httpCodeRepository;

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
    public HttpCode createOrFetch(String httpCode) {
        HttpCode httpCodePers = new HttpCode();
        httpCodePers.setCode(httpCode);
        return httpCodeRepository.saveAndFlush(httpCodePers);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = true)
    public HttpCode find(String httpCode) {
        return httpCodeRepository.findByCode(httpCode);
    }
}
