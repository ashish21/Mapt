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

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Query;

@Api(name = "commentidsendpoint", namespace = @ApiNamespace(ownerDomain = "watch.com", ownerName = "watch.com", packagePath = "project"))
public class CommentIdsEndpoint {

    /**
     * This method lists all the entities inserted in datastore.
     * It uses HTTP GET method and paging support.
     *
     * @return A CollectionResponse class containing the list of all entities
     * persisted and a cursor to the next page.
     */
    @SuppressWarnings({"unchecked", "unused"})
    @ApiMethod(name = "listCommentIds")
    public CollectionResponse<CommentIds> listCommentIds(
            @Nullable @Named("cursor") String cursorString,
            @Nullable @Named("limit") Integer limit) {

        EntityManager mgr = null;
        List<CommentIds> execute = null;

        try {
            mgr = getEntityManager();
            Query query = mgr.createQuery("select from CommentIds as CommentIds");
            Cursor cursor;
            if (cursorString != null && cursorString.trim().length() > 0) {
                cursor = Cursor.fromWebSafeString(cursorString);
                query.setHint(JPACursorHelper.CURSOR_HINT, cursor);
            }

            if (limit != null) {
                query.setFirstResult(0);
                query.setMaxResults(limit);
            }

            execute = (List<CommentIds>) query.getResultList();
            cursor = JPACursorHelper.getCursor(execute);
            if (cursor != null) cursorString = cursor.toWebSafeString();

            // Tight loop for fetching all entities from datastore and accomodate
            // for lazy fetch.
            for (CommentIds obj : execute) ;
        } finally {
            if (mgr != null) {
                mgr.close();
            }
        }

        return CollectionResponse.<CommentIds>builder()
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
    @ApiMethod(name = "getCommentIds")
    public CommentIds getCommentIds(@Named("id") Long id) {
        EntityManager mgr = getEntityManager();
        CommentIds commentIds = null;
        commentIds = mgr.find(CommentIds.class, id);
        mgr.close();
        return commentIds;
    }

    /**
     * This inserts a new entity into App Engine datastore. If the entity already
     * exists in the datastore, an exception is thrown.
     * It uses HTTP POST method.
     *
     * @param commentIds the entity to be inserted.
     * @return The inserted entity.
     */
    @ApiMethod(name = "insertCommentIds")
    public CommentIds insertCommentIds(CommentIds commentIds) {
        EntityManager mgr = getEntityManager();

        try {
            if (containsCommentIds(commentIds)) {
                throw new EntityExistsException("Object already exists");
            }
            mgr.persist(commentIds);
        } finally {
            mgr.close();
        }
        return commentIds;
    }

    /**
     * This method is used for updating an existing entity. If the entity does not
     * exist in the datastore, an exception is thrown.
     * It uses HTTP PUT method.
     *
     * @param commentIds the entity to be updated.
     * @return The updated entity.
     */
    @ApiMethod(name = "updateCommentIds")
    public CommentIds updateCommentIds(CommentIds commentIds) {
        EntityManager mgr = getEntityManager();
        try {
            if (!containsCommentIds(commentIds)) {
                throw new EntityNotFoundException("Object does not exist");
            }
            mgr.persist(commentIds);
        } finally {
            mgr.close();
        }
        return commentIds;
    }

    /**
     * This method removes the entity with primary key id.
     * It uses HTTP DELETE method.
     *
     * @param id the primary key of the entity to be deleted.
     * @return The deleted entity.
     */
    @ApiMethod(name = "removeCommentIds")
    public CommentIds removeCommentIds(@Named("id") Long id) {
        EntityManager mgr = getEntityManager();
        CommentIds commentIds = null;
        try {
            commentIds = mgr.find(CommentIds.class, id);
            mgr.remove(commentIds);
        } finally {
            mgr.close();
        }
        return commentIds;
    }

    private boolean containsCommentIds(CommentIds commentIds) {
        EntityManager mgr = getEntityManager();
        boolean contains = true;
        try {
            CommentIds item = mgr.find(CommentIds.class, commentIds.getId());
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
