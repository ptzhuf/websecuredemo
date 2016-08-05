package org.hmzb.csrf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by ptzhuf on 2016/8/3.
 */
@Service
public class CsrfUserService {
    @Autowired
    private CsrfUserRepository csrfUserRepository;

    public CsrfUser save(String username, String password) {
        CsrfUser csrfUser = new CsrfUser();
        csrfUser.setUsername(username);
        csrfUser.setPassword(password);
        return csrfUserRepository.save(csrfUser);
    }
}
