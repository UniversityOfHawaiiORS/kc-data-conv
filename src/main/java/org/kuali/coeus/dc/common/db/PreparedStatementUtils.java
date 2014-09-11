package org.kuali.coeus.dc.common.db;


import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

public final class PreparedStatementUtils {

    private PreparedStatementUtils() {
        throw new UnsupportedOperationException("do not call");
    }

    public static PreparedStatement setString(int index, String string, PreparedStatement stmt) throws SQLException {
        stmt.setString(index, string);
        return stmt;
    }

    public static PreparedStatement setLong(int index, Long l, PreparedStatement stmt) throws SQLException {
        stmt.setLong(index, l);
        return stmt;
    }

    public static PreparedStatement setTimestamp(int index, Timestamp t, PreparedStatement stmt) throws SQLException {
        stmt.setTimestamp(index, t);
        return stmt;
    }
}
