package org.hmzb;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by ptzhuf on 2016/8/2.
 */
@Entity
public class Cookie {
    @Id
    @GeneratedValue
    private Integer id;
    private String value;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Cookie{");
        sb.append("id=").append(id);
        sb.append(", value='").append(value).append('\'');
        sb.append('}');
        return sb.toString();
    }

    public Cookie() {
    }

    public Cookie(String value) {
        this.value = value;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
