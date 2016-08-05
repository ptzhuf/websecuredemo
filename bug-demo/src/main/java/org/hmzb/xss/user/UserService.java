/**
 *
 */
package org.hmzb.xss.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ptzhuf
 */
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> findAlll() {
        List<User> result = userRepository.findAll();
        return result;
    }

    public User save(User user) {
        User result = userRepository.save(user);
        return result;
    }

    public User update(User user) {
        User result = userRepository.saveAndFlush(user);
        return result;
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public void delete(Integer id) {
        userRepository.delete(id);
    }
}
