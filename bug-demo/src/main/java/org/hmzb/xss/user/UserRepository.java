/**
 *
 */
package org.hmzb.xss.user;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author ptzhuf
 */
public interface UserRepository extends JpaRepository<User, Integer> {

    User findByUsername(String username);
}
