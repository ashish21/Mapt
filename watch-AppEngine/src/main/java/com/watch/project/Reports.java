package com.watch.project;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by dexter on 24/1/14.
 */
@Entity
public class Reports implements Comparator<Reports> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String Address, Info, Lat, Long, User, Title;
    Integer Followers, Flags;
    Date DOS;
    ArrayList<String> tags;

    public String getTitle() {

        return Title;
    }

    public void setTitle(String title) {

        Title = title;
    }

    public Long getId() {

        return id;
    }

    public String getAddress() {

        return Address;
    }

    public void setAddress(String address) {

        Address = address;
    }

    public String getInfo() {
        return Info;
    }

    public void setInfo(String info) {
        Info = info;
    }

    public String getLat() {
        return Lat;
    }

    public void setLat(String lat) {
        Lat = lat;
    }

    public String getLong() {
        return Long;
    }

    public void setLong(String aLong) {
        Long = aLong;
    }

    public String getUser() {
        return User;
    }

    public void setUser(String user) {
        User = user;
    }

    public Integer getFollowers() {
        return Followers;
    }

    public void setFollowers(Integer followers) {
        Followers = followers;
    }

    public Integer getFlags() {
        return Flags;
    }

    public void setFlags(Integer flags) {
        Flags = flags;
    }

    public Date getDOS() {
        return DOS;
    }

    public void setDOS(Date DOS) {
        this.DOS = DOS;
    }

    public ArrayList<String> getTags() {return tags;}

    public void setTags(ArrayList<String> Tags) {tags = Tags; }

    public Reports() {}

    @Override
    public int compare(Reports o1, Reports o2) {
        return o1.getDOS().compareTo(o2.getDOS());
    }
}
