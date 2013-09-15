package org.woehlke.logfileloader.core.services.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.woehlke.logfileloader.core.entities.Ip;
import org.woehlke.logfileloader.core.repositories.IpRepository;
import org.woehlke.logfileloader.core.services.IpService;

import javax.inject.Inject;

/**
 * Created with IntelliJ IDEA.
 * User: tw
 * Date: 15.09.13
 * Time: 22:07
 * To change this template use File | Settings | File Templates.
 */
@Service
public class IpServiceImpl implements IpService {

    @Inject
    private IpRepository ipRepository;

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
    public Ip createOrFetch(String ip) {
        Ip ipPers = new Ip();
        ipPers.setIp(ip);
        return ipRepository.saveAndFlush(ipPers);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = true)
    public Ip find(String ip) {
        return ipRepository.findByIp(ip);
    }
}
