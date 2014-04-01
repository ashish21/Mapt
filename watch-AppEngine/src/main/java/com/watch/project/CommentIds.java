package com.watch.project;

import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by dexter on 18/2/14.
 */

@Entity
public class CommentIds {

    public CommentIds() {};

    @Id
    Long Id;
    public void setId(Long ID) {Id = ID;}
    public Long getId() {return Id;}

    public ArrayList<Long> commentsIds;
    public void setCommentsIds(ArrayList<Long> CommentsIds) {commentsIds = CommentsIds;}
    public ArrayList<Long> getCommentsIds() {return commentsIds;}
}
