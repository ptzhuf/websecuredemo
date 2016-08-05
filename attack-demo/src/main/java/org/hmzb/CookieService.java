/**
 *
 */
package org.hmzb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ptzhuf
 */
@Service
public class CookieService {


    @Autowired
    private CookieRepository cookieRepository;

    public Cookie save(String cookie) {
        Cookie cookieEntity = new Cookie(cookie);
        return cookieRepository.save(cookieEntity);
    }
}
