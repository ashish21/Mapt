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

@Api(name = "reportsendpoint", namespace = @ApiNamespace(ownerDomain = "watch.com", ownerName = "watch.com", packagePath = "project"))
public class ReportsEndpoint {

    /**
     * This method lists all the entities inserted in datastore.
     * It uses HTTP GET method and paging support.
     *
     * @return A CollectionResponse class containing the list of all entities
     * persisted and a cursor to the next page.
     */
    @SuppressWarnings({"unchecked", "unused"})
    @ApiMethod(name = "listReports")
    public CollectionResponse<Reports> listReports(
            @Nullable @Named("cursor") String cursorString,
            @Nullable @Named("limit") Integer limit) {

        EntityManager mgr = null;
        List<Reports> execute = null;

        try {
            mgr = getEntityManager();
            Query query = mgr.createQuery("select from Reports as Reports");
            Cursor cursor;
            if (cursorString != null && cursorString.trim().length() > 0) {
                cursor = Cursor.fromWebSafeString(cursorString);
                query.setHint(JPACursorHelper.CURSOR_HINT, cursor);
            }

            if (limit != null) {
                query.setFirstResult(0);
                query.setMaxResults(limit);
            }

            execute = (List<Reports>) query.getResultList();
            cursor = JPACursorHelper.getCursor(execute);
            if (cursor != null) cursorString = cursor.toWebSafeString();

            // Tight loop for fetching all entities from datastore and accomodate
            // for lazy fetch.
            for (Reports obj : execute) ;
        } finally {
            if (mgr != null) {
                mgr.close();
            }
        }

        return CollectionResponse.<Reports>builder()
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
    @ApiMethod(name = "getReports")
    public Reports getReports(@Named("id") Long id) {
        EntityManager mgr = getEntityManager();
        Reports reports = null;
        try {
            reports = mgr.find(Reports.class, id);
        } finally {
            mgr.close();
        }
        return reports;
    }

    /**
     * This inserts a new entity into App Engine datastore. If the entity already
     * exists in the datastore, an exception is thrown.
     * It uses HTTP POST method.
     *
     * @param reports the entity to be inserted.
     * @return The inserted entity.
     */
    @ApiMethod(name = "insertReports")
    public Reports insertReports(Reports reports) {

        EntityManager mgr = getEntityManager();
        mgr.persist(reports);
        mgr.close();
        return reports;
    }

    /**
     * This method is used for updating an existing entity. If the entity does not
     * exist in the datastore, an exception is thrown.
     * It uses HTTP PUT method.
     *
     * @param reports the entity to be updated.
     * @return The updated entity.
     */
    @ApiMethod(name = "updateReports")
    public Reports updateReports(Reports reports) {
        EntityManager mgr = getEntityManager();
        try {
            if (!containsReports(reports)) {
                throw new EntityNotFoundException("Object does not exist");
            }
            mgr.persist(reports);
        } finally {
            mgr.close();
        }
        return reports;
    }

    /**
     * This method removes the entity with primary key id.
     * It uses HTTP DELETE method.
     *
     * @param id the primary key of the entity to be deleted.
     * @return The deleted entity.
     */
    @ApiMethod(name = "removeReports")
    public Reports removeReports(@Named("id") Long id) {
        EntityManager mgr = getEntityManager();
        Reports reports = null;
        try {
            reports = mgr.find(Reports.class, id);
            mgr.remove(reports);
        } finally {
            mgr.close();
        }
        return reports;
    }

    private boolean containsReports(Reports reports) {
        EntityManager mgr = getEntityManager();
        boolean contains = true;
        try {
            Reports item = mgr.find(Reports.class, reports.getId());
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
