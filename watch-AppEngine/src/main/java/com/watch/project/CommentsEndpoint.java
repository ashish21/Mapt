package com.watch.project;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.config.Named;
import com.google.api.server.spi.config.Nullable;
import com.google.api.server.spi.response.CollectionResponse;
import com.google.appengine.api.datastore.Cursor;
import com.google.appengine.datanucleus.query.JPACursorHelper;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Query;

@Api(name = "commentsendpoint", namespace = @ApiNamespace(ownerDomain = "watch.com", ownerName = "watch.com", packagePath = "project"))
public class CommentsEndpoint {

    /**
     * This method lists all the entities inserted in datastore.
     * It uses HTTP GET method and paging support.
     *
     * @return A CollectionResponse class containing the list of all entities
     * persisted and a cursor to the next page.
     */
    @SuppressWarnings({"unchecked", "unused"})
    @ApiMethod(name = "listComments")
    public CollectionResponse<Comments> listComments(
            @Nullable @Named("cursor") String cursorString,
            @Nullable @Named("limit") Integer limit) {

        EntityManager mgr = null;
        List<Comments> execute = null;

        try {
            mgr = getEntityManager();
            Query query = mgr.createQuery("select from Comments as Comments");
            Cursor cursor;
            if (cursorString != null && cursorString.trim().length() > 0) {
                cursor = Cursor.fromWebSafeString(cursorString);
                query.setHint(JPACursorHelper.CURSOR_HINT, cursor);
            }

            if (limit != null) {
                query.setFirstResult(0);
                query.setMaxResults(limit);
            }

            execute = (List<Comments>) query.getResultList();
            cursor = JPACursorHelper.getCursor(execute);
            if (cursor != null) cursorString = cursor.toWebSafeString();

            // Tight loop for fetching all entities from datastore and accomodate
            // for lazy fetch.
            for (Comments obj : execute) ;
        } finally {
            if (mgr != null) {
                mgr.close();
            }
        }

        return CollectionResponse.<Comments>builder()
                .setItems(execute)
                .setNextPageToken(cursorString)
                .build();
    }

    /**
     * This method gets the entity having primary key id. It uses HTTP GET method.
     *
     * @param id the primary key of the java bean.
     * @return The entity with primary key id.
     */
    @ApiMethod(name = "getComments")
    public Comments getComments(@Named("id") Long id) {
        EntityManager mgr = getEntityManager();
        Comments comments = null;
        comments = mgr.find(Comments.class, id);
        mgr.close();
        return comments;
    }

    /**
     * This inserts a new entity into App Engine datastore. If the entity already
     * exists in the datastore, an exception is thrown.
     * It uses HTTP POST method.
     *
     * @param comments the entity to be inserted.
     * @return The inserted entity.
     */
    @ApiMethod(name = "insertComments")
    public Comments insertComments(Comments comments) {
        EntityManager mgr = getEntityManager();
        mgr.persist(comments);
        mgr.close();
        return comments;
    }

    /**
     * This method is used for updating an existing entity. If the entity does not
     * exist in the datastore, an exception is thrown.
     * It uses HTTP PUT method.
     *
     * @param comments the entity to be updated.
     * @return The updated entity.
     */
    @ApiMethod(name = "updateComments")
    public Comments updateComments(Comments comments) {
        EntityManager mgr = getEntityManager();
        try {
            if (!containsComments(comments)) {
                throw new EntityNotFoundException("Object does not exist");
            }
            mgr.persist(comments);
        } finally {
            mgr.close();
        }
        return comments;
    }

    /**
     * This method removes the entity with primary key id.
     * It uses HTTP DELETE method.
     *
     * @param id the primary key of the entity to be deleted.
     * @return The deleted entity.
     */
    @ApiMethod(name = "removeComments")
    public Comments removeComments(@Named("id") Long id) {
        EntityManager mgr = getEntityManager();
        Comments comments = null;
        try {
            comments = mgr.find(Comments.class, id);
            mgr.remove(comments);
        } finally {
            mgr.close();
        }
        return comments;
    }

    private boolean containsComments(Comments comments) {
        EntityManager mgr = getEntityManager();
        boolean contains = true;
        try {
            Comments item = mgr.find(Comments.class, comments.getId());
            if (item == null) {
                contains = false;
            }
        } finally {
            mgr.close();
        }
        return contains;
    }

    private static EntityManager getEntityManager() {
        return EMF.get().createEntityManager();
    }

}
