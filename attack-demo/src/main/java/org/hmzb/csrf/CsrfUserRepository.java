package org.hmzb.csrf;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by ptzhuf on 2016/8/3.
 */
public interface CsrfUserRepository extends JpaRepository<CsrfUser, Integer> {
}
