package connecttojdbc;

import java.sql.*;

public class DBCommand {
    public static ResultSet executeQuery(Connection conn, String query) throws SQLException {
        try {
            Statement stmt = conn.createStatement();
            return stmt.executeQuery(query);
        } catch (SQLException e) {
            System.err.println("Error executing query: " + query);
            System.err.println("SQL Error: " + e.getMessage());
            throw e;
        }
    }

    public static ResultSet executePreparedQuery(Connection conn, String query, Object[] params) throws SQLException {
        try {
            PreparedStatement pstmt = conn.prepareStatement(query);
            for (int i = 0; i < params.length; i++) {
                pstmt.setObject(i + 1, params[i]);
            }
            return pstmt.executeQuery();
        } catch (SQLException e) {
            System.err.println("Error executing prepared query: " + query);
            System.err.println("SQL Error: " + e.getMessage());
            throw e;
        }
    }

    public static int executeUpdate(Connection conn, String query) throws SQLException {
        try (Statement stmt = conn.createStatement()) {
            return stmt.executeUpdate(query);
        } catch (SQLException e) {
            System.err.println("Error executing update: " + query);
            System.err.println("SQL Error: " + e.getMessage());
            throw e;
        }
    }
}