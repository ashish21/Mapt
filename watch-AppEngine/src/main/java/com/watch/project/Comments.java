package com.watch.project;

import java.lang.String;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Arrays;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by dexter on 18/2/14.
 */
@Entity
public class Comments {

    public Long getId() {
        return id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    public Integer getTdwns() {
        return Tdwns;
    }

    public void setTdwns(Integer tdwns) {
        Tdwns = tdwns;
    }

    Integer Tdwns;

    public Integer getTups() {
        return Tups;
    }

    public void setTups(Integer tups) {
        Tups = tups;
    }

    Integer Tups;

    public Integer getFlags() {
        return Flags;
    }

    public void setFlags(Integer flags) {
        Flags = flags;
    }

    Integer Flags;

    public Date getDOS() {
        return DOS;
    }
    public void setDOS(Date DOS) {
        this.DOS = DOS;
    }

    Date DOS;

    String user, comment;
    public List<String> getData() {return Arrays.asList(user, comment);}
    public void setData(List<String> data) {

        user = data.get(0);
        comment = data.get(1);
    }
}
