/**
 *
 */
package org.hmzb.xss.user;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author ptzhuf
 */
@Entity
public class User {
    @Id
    @GeneratedValue
    private Integer id;
    private String username;
    private String nickname;
    private String password;

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("User [id=");
        builder.append(id);
        builder.append(", username=");
        builder.append(username);
        builder.append(", nickname=");
        builder.append(nickname);
        builder.append(", password=");
        builder.append(password);
        builder.append("]");
        return builder.toString();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

}
