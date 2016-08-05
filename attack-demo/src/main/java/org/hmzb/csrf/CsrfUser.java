package org.hmzb.csrf;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by ptzhuf on 2016/8/3.
 */
@Entity
public class CsrfUser {
    @Id
    @GeneratedValue
    private Integer id;
    private String username;
    private String password;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("CsrfUser{");
        sb.append("id=").append(id);
        sb.append(", username='").append(username).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append('}');
        return sb.toString();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
