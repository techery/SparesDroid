package com.techery.spares.db;

/**
 * Created with IntelliJ IDEA.
 * User: dimson
 * Date: 23.06.13
 * Time: 09:08
 * To change this template use File | Settings | File Templates.
 */
public class DbRuntimeException extends RuntimeException {
    public DbRuntimeException(String detailMessage) {
        super(detailMessage);
    }
}
