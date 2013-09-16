package org.woehlke.logfileloader.core.services.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.woehlke.logfileloader.core.entities.Request;
import org.woehlke.logfileloader.core.repositories.RequestRepository;
import org.woehlke.logfileloader.core.services.RequestService;

import javax.inject.Inject;

/**
 * Created with IntelliJ IDEA.
 * User: tw
 * Date: 15.09.13
 * Time: 22:08
 * To change this template use File | Settings | File Templates.
 */
@Service
public class RequestServiceImpl implements RequestService {

    @Inject
    private RequestRepository requestRepository;

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
    public Request createOrFetch(String requestLine) {
        Request requestPers = new Request();
        requestPers.setRequest(requestLine);
        return requestRepository.saveAndFlush(requestPers);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = true)
    public Request find(String requestLine) {
        return requestRepository.findByRequest(requestLine);
    }
}
