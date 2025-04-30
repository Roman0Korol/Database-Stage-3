package connecttojdbc;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

public class DBOutputFormatter {
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    public static int showGenericQueryResult(String title, ResultSet resultSet) {
        try {
            if (resultSet == null || !resultSet.isBeforeFirst()) {
                System.out.println("\n" + title + "\nNo results found");
                return 0;
            }

            ResultSetMetaData meta = resultSet.getMetaData();
            int colCount = meta.getColumnCount();

            // Print header
            System.out.println("\n" + title);
            for (int i = 1; i <= colCount; i++) {
                System.out.printf("%-20s", meta.getColumnName(i));
            }
            System.out.println();

            // Print separator
            for (int i = 1; i <= colCount; i++) {
                System.out.print("--------------------");
            }
            System.out.println();

            // Print data
            int rowCount = 0;
            while (resultSet.next()) {
                rowCount++;
                for (int i = 1; i <= colCount; i++) {
                    Object value = resultSet.getObject(i);
                    String displayValue;
                    if (value instanceof java.sql.Date) {
                        displayValue = DATE_FORMAT.format((java.sql.Date) value);
                    } else {
                        displayValue = (value == null) ? "NULL" : value.toString();
                    }
                    System.out.printf("%-20s", displayValue);
                }
                System.out.println();
            }

            System.out.println("\nTotal rows: " + rowCount);
            return rowCount;
        } catch (SQLException e) {
            System.err.println("Error displaying results: " + e.getMessage());
            return -1;
        }
    }


    public static int showPlayerResults(String title, ResultSet resultSet) {
        try {
            if (resultSet == null || !resultSet.isBeforeFirst()) {
                System.out.println("\n" + title + "\nNo player results found");
                return 0;
            }

            System.out.println("\n" + title);
            System.out.printf("%-10s %-20s %-15s %-10s %-10s%n",
                    "ID", "Username", "Playtime", "Joined", "Region");
            System.out.println("------------------------------------------------------------");

            int rowCount = 0;
            while (resultSet.next()) {
                rowCount++;
                System.out.printf("%-10d %-20s %-15d %-10s %-10s%n",
                        resultSet.getInt("id"),
                        resultSet.getString("username"),
                        resultSet.getInt("playtime_hours"),
                        resultSet.getString("date_joined"),
                        resultSet.getString("region"));
            }

            System.out.println("\nTotal players: " + rowCount);
            return rowCount;
        } catch (SQLException e) {
            System.err.println("Error displaying players: " + e.getMessage());
            return -1;
        }
    }

    public static int showGameResults(String title, ResultSet resultSet) {
        try {
            if (resultSet == null || !resultSet.isBeforeFirst()) {
                System.out.println("\n" + title + "\nNo game results found");
                return 0;
            }

            System.out.println("\n" + title);
            System.out.printf("%-10s %-30s %-15s %-20s%n",
                    "Game ID", "Name", "Release Date", "Genre");
            System.out.println("------------------------------------------------------------------");

            int rowCount = 0;
            while (resultSet.next()) {
                rowCount++;
                System.out.printf("%-10d %-30s %-15s %-20s%n",
                        resultSet.getInt("game_id"),
                        resultSet.getString("game_name"),
                        resultSet.getDate("release_date"),
                        resultSet.getString("genre"));
            }

            System.out.println("\nTotal games: " + rowCount);
            return rowCount;
        } catch (SQLException e) {
            System.err.println("Error displaying games: " + e.getMessage());
            return -1;
        }
    }

    public static int showDeveloperResults(String title, ResultSet resultSet) {
        try {
            if (resultSet == null || !resultSet.isBeforeFirst()) {
                System.out.println("\n" + title + "\nNo developer results found");
                return 0;
            }

            System.out.println("\n" + title);
            System.out.printf("%-15s %-30s %-20s %-30s%n",
                    "Developer ID", "Name", "Role", "Project");
            System.out.println("---------------------------------------------------------------------------");

            int rowCount = 0;
            while (resultSet.next()) {
                rowCount++;
                System.out.printf("%-15d %-30s %-20s %-30s%n",
                        resultSet.getInt("developer_id"),
                        resultSet.getString("developer_name"),
                        resultSet.getString("role"),
                        resultSet.getString("project"));
            }

            System.out.println("\nTotal developers: " + rowCount);
            return rowCount;
        } catch (SQLException e) {
            System.err.println("Error displaying developers: " + e.getMessage());
            return -1;
        }
    }

    public static int showAggregationResults(String title, ResultSet resultSet) {
        return showGenericQueryResult("AGGREGATION: " + title, resultSet);
    }

    public static int showRankingResults(String title, ResultSet resultSet) {
        return showGenericQueryResult("RANKING: " + title, resultSet);
    }

    public static int showCaseStatementResults(String title, ResultSet resultSet) {
        return showGenericQueryResult("CASE RESULTS: " + title, resultSet);
    }

    public static int showCTEResults(String title, ResultSet resultSet) {
        return showGenericQueryResult("CTE RESULTS: " + title, resultSet);
    }

    public static int showViewResults(String title, ResultSet resultSet) {
        return showGenericQueryResult("VIEW RESULTS: " + title, resultSet);
    }
}