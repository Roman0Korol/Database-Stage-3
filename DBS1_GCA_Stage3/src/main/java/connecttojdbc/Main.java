package connecttojdbc;

import java.sql.*;

public class Main {
    private static final String DB_PATH = "src/main/resources/db/data.sqlite";
    private Connection conn;

    public static void main(String[] args) {
        Main app = new Main();
        app.run();
    }

    public void run() {
        try {
            conn = DBConnect.connect(DB_PATH);
            if (conn == null) {
                System.err.println("Failed to connect to database");
                return;
            }

            verifyDatabaseContents();
            executeAllQueries();

        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
            e.printStackTrace();
        } finally {
            DBConnect.disconnect(conn);
        }
    }

    private void verifyDatabaseContents() throws SQLException {
        System.out.println("\n=== VERIFYING DATABASE CONTENTS ===");
        String[] tables = {"player", "profile", "game", "developer", "studio", "gaming_system"};
        for (String table : tables) {
            String query = "SELECT COUNT(*) FROM " + table;
            try (ResultSet rs = DBCommand.executeQuery(conn, query)) {
                if (rs.next()) {
                    System.out.printf("%-15s: %d rows%n", table, rs.getInt(1));
                }
            }
        }
    }

    private void executeAllQueries() throws SQLException {
        executeQuery1();  // Players with <50 hours
        executeQuery2();  // Consoles by manufacturer
        executeQuery3();  // Latest game per genre
        executeQuery4();  // Top 5 players by completion
        executeQuery5();  // Ranked Sony consoles
        executeQuery6();  // Players above avg playtime
        executeQuery7();  // Player classification
        executeQuery8();  // Directors
        executeQuery9();  // Developer performance
        executeQuery10(); // Studio stats
    }

    private void executeQuery1() throws SQLException {
        String query = "SELECT p.id, p.username, SUM(pr.playtime_hours) as total_playtime, p.date_joined, p.region " +
                "FROM player p JOIN profile pr ON p.id = pr.player_id " +
                "GROUP BY p.id, p.username, p.date_joined, p.region " +
                "HAVING total_playtime < 50";

        System.out.println("\nExecuting Query 1: Players With <50 Hours Playtime\n" + query);
        try (ResultSet rs = DBCommand.executeQuery(conn, query)) {
            DBOutputFormatter.showGenericQueryResult("PLAYERS WITH <50 HOURS PLAYTIME", rs);
        } catch (SQLException e) {
            System.err.println("Failed to execute Query 1");
            throw e;
        }
    }

    private void executeQuery2() throws SQLException {
        String query = "SELECT manufacturer, COUNT(name) AS total_consoles " +
                "FROM gaming_system " +
                "GROUP BY manufacturer " +
                "ORDER BY total_consoles DESC";

        System.out.println("\nExecuting Query 2: Consoles By Manufacturer\n" + query);
        try (ResultSet rs = DBCommand.executeQuery(conn, query)) {
            DBOutputFormatter.showGenericQueryResult("CONSOLES BY MANUFACTURER", rs);
        } catch (SQLException e) {
            System.err.println("Failed to execute Query 2");
            throw e;
        }
    }

    private void executeQuery3() throws SQLException {
        String query = "SELECT g.game_id, g.game_name, g.release_date, g.genre " +
                "FROM game g " +
                "WHERE g.release_date = (SELECT MAX(release_date) FROM game WHERE genre = g.genre) " +
                "ORDER BY g.genre";

        System.out.println("\nExecuting Query 3: Latest Game Per Genre\n" + query);
        try (ResultSet rs = DBCommand.executeQuery(conn, query)) {
            DBOutputFormatter.showGenericQueryResult("LATEST GAME PER GENRE", rs);
        } catch (SQLException e) {
            System.err.println("Failed to execute Query 3");
            throw e;
        }
    }

    private void executeQuery4() throws SQLException {
        String query = "SELECT p.id, p.username, " +
                "ROUND(AVG(pr.completion_percentage), 2) AS avg_completion, " +
                "p.date_joined, p.region " +
                "FROM player p JOIN profile pr ON p.id = pr.player_id " +
                "GROUP BY p.id, p.username, p.date_joined, p.region " +
                "ORDER BY avg_completion DESC LIMIT 5";

        System.out.println("\nExecuting Query 4: Top 5 Players By Completion %\n" + query);
        try (ResultSet rs = DBCommand.executeQuery(conn, query)) {
            DBOutputFormatter.showGenericQueryResult("TOP 5 PLAYERS BY COMPLETION %", rs);
        } catch (SQLException e) {
            System.err.println("Failed to execute Query 4");
            throw e;
        }
    }
    private void executeQuery5() throws SQLException {
        String query = "SELECT name, manufacturer, release_year, " +
                "RANK() OVER (ORDER BY release_year DESC) AS release_rank " +
                "FROM gaming_system " +
                "WHERE manufacturer = 'Sony' " +
                "AND CAST(release_year AS INTEGER) BETWEEN 2015 AND 2023";

        System.out.println("\nExecuting Query 5: Ranked Sony Consoles (2015-2023)\n" + query);
        try (ResultSet rs = DBCommand.executeQuery(conn, query)) {
            DBOutputFormatter.showGenericQueryResult("RANKED SONY CONSOLES", rs);
        } catch (SQLException e) {
            System.err.println("Failed to execute Query 5");
            throw e;
        }
    }
    private void executeQuery10() throws SQLException {
        try {
            // Create the view if it doesn't exist
            DBCommand.executeUpdate(conn, "CREATE VIEW IF NOT EXISTS Studio_Game_Stats AS " +
                    "SELECT s.studio_id, s.studio_name, " +
                    "COUNT(g.game_id) AS number_of_games, " +
                    "MIN(g.release_date) AS first_release, " +
                    "MAX(g.release_date) AS latest_release " +
                    "FROM studio s LEFT JOIN game g ON s.studio_id = g.studio_id " +
                    "GROUP BY s.studio_id, s.studio_name");

            // Main query for active studios
            String query = "SELECT studio_name, number_of_games, " +
                    "strftime('%Y-%m-%d', first_release) as first_release, " +
                    "strftime('%Y-%m-%d', latest_release) as latest_release, " +
                    "(strftime('%Y', 'now') - strftime('%Y', first_release)) AS years_active " +
                    "FROM Studio_Game_Stats " +
                    "WHERE number_of_games >= 2 AND latest_release >= date('2020-01-01') " +  // Relaxed date to 2020
                    "ORDER BY number_of_games DESC";

            System.out.println("\nExecuting Query 10: Active Studio Statistics\n" + query);

            try (ResultSet rs = DBCommand.executeQuery(conn, query)) {
                if (!rs.isBeforeFirst()) {
                    System.out.println("No active studios found with 2+ games released since 2020.");
                } else {
                    DBOutputFormatter.showGenericQueryResult("ACTIVE STUDIO STATISTICS", rs);
                }
            }
        } catch (SQLException e) {
            System.err.println("Failed to execute Query 10");
            throw e;
        }
    }
//    private void executeQuery5() throws SQLException {
//        debugSonyConsoles();
//        String query = "SELECT name, manufacturer, release_year, " +
//                "RANK() OVER (ORDER BY release_year DESC) AS release_rank " +
//                "FROM gaming_system " +
//                "WHERE manufacturer = 'Sony' " +
//                "AND release_year BETWEEN 2015 AND 2023";
//
//        System.out.println("\nExecuting Query 5: Ranked Sony Consoles (2015-2023)\n" + query);
//        try (ResultSet rs = DBCommand.executeQuery(conn, query)) {
//            DBOutputFormatter.showGenericQueryResult("RANKED SONY CONSOLES", rs);
//        } catch (SQLException e) {
//            System.err.println("Failed to execute Query 5");
//            throw e;
//        }
//    }

    private void executeQuery6() throws SQLException {
        double avgPlaytime = 0;
        String avgQuery = "SELECT AVG(playtime_hours) FROM profile";
        try (ResultSet rs = DBCommand.executeQuery(conn, avgQuery)) {
            if (rs.next()) {
                avgPlaytime = rs.getDouble(1);
            }
        }

        String query = "SELECT p.id, p.username, SUM(pr.playtime_hours) as total_playtime, " +
                "p.date_joined, p.region " +
                "FROM player p JOIN profile pr ON p.id = pr.player_id " +
                "GROUP BY p.id, p.username, p.date_joined, p.region " +
                "HAVING total_playtime > ?";

        System.out.println("\nExecuting Query 6: Players Above Average Playtime\n" + query);
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setDouble(1, avgPlaytime);
            try (ResultSet rs = pstmt.executeQuery()) {
                DBOutputFormatter.showGenericQueryResult("PLAYERS ABOVE AVERAGE PLAYTIME", rs);
            }
        } catch (SQLException e) {
            System.err.println("Failed to execute Query 6");
            throw e;
        }
    }

    private void executeQuery7() throws SQLException {
        String query = "SELECT p.username, SUM(pr.playtime_hours) AS total_playtime, " +
                "CASE " +
                "  WHEN SUM(pr.playtime_hours) > 100 THEN 'Hardcore' " +
                "  WHEN SUM(pr.playtime_hours) > 50 THEN 'Regular' " +
                "  WHEN SUM(pr.playtime_hours) > 10 THEN 'Casual' " +
                "  ELSE 'Newbie' " +
                "END AS player_category " +
                "FROM player p " +
                "JOIN profile pr ON p.id = pr.player_id " +
                "GROUP BY p.username " +
                "ORDER BY total_playtime DESC";

        System.out.println("\nExecuting Query 7: Player Classification\n" + query);
        try (ResultSet rs = DBCommand.executeQuery(conn, query)) {
            DBOutputFormatter.showGenericQueryResult("PLAYER CLASSIFICATION", rs);
        } catch (SQLException e) {
            System.err.println("Failed to execute Query 7");
            throw e;
        }
    }

    private void executeQuery8() throws SQLException {
        String query = "SELECT d.developer_id, d.developer_name, pr.role, dp.project " +
                "FROM developer d " +
                "JOIN developer_project dp ON d.developer_id = dp.developer_id " +
                "JOIN project_role pr ON dp.project = pr.project " +
                "WHERE pr.role LIKE '%Director%' " +
                "ORDER BY d.developer_name";

        System.out.println("\nExecuting Query 8: Developers With Director Roles\n" + query);
        try (ResultSet rs = DBCommand.executeQuery(conn, query)) {
            DBOutputFormatter.showGenericQueryResult("DEVELOPERS WITH DIRECTOR ROLES", rs);
        } catch (SQLException e) {
            System.err.println("Failed to execute Query 8");
            throw e;
        }
    }

    private void executeQuery9() throws SQLException {
        String query = "WITH GameRatings AS ( " +
                "  SELECT g.game_id, g.game_name, " +
                "  ROUND(AVG(p.completion_percentage), 2) AS avg_completion, " +
                "  ROUND(AVG(p.playtime_hours), 2) AS avg_playtime, " +
                "  ROUND((AVG(p.completion_percentage) + AVG(p.playtime_hours))/2, 2) AS game_rating " +
                "  FROM profile p " +
                "  JOIN game g ON p.game_id = g.game_id " +
                "  GROUP BY g.game_id, g.game_name " +
                ") " +
                "SELECT d.developer_name, s.studio_name, " +
                "  gr.game_name, gr.avg_completion, " +
                "  gr.avg_playtime, gr.game_rating " +
                "FROM developer d " +
                "JOIN developer_studio ds ON d.developer_id = ds.developer_id " +
                "JOIN studio s ON ds.studio_id = s.studio_id " +
                "JOIN game g ON s.studio_id = g.studio_id " +
                "JOIN GameRatings gr ON g.game_id = gr.game_id " +
                "ORDER BY gr.game_rating DESC";

        System.out.println("\nExecuting Query 9: Developer Performance Ratings\n" + query);
        try (ResultSet rs = DBCommand.executeQuery(conn, query)) {
            DBOutputFormatter.showGenericQueryResult("DEVELOPER PERFORMANCE RATINGS", rs);
        } catch (SQLException e) {
            System.err.println("Failed to execute Query 9");
            throw e;
        }
    }

//    private void executeQuery10() throws SQLException {
//        try {
//            DBCommand.executeUpdate(conn, "DROP VIEW IF EXISTS Studio_Game_Stats");
//
//            String createView = "CREATE VIEW Studio_Game_Stats AS " +
//                    "SELECT s.studio_id, s.studio_name, " +
//                    "COUNT(g.game_id) AS number_of_games, " +
//                    "MIN(g.release_date) AS first_release, " +
//                    "MAX(g.release_date) AS latest_release " +
//                    "FROM studio s LEFT JOIN game g ON s.studio_id = g.studio_id " +
//                    "GROUP BY s.studio_id, s.studio_name";
//
//            DBCommand.executeUpdate(conn, createView);
//
//            String query = "SELECT studio_name, number_of_games, " +
//                    "strftime('%Y-%m-%d', first_release) as first_release, " +
//                    "strftime('%Y-%m-%d', latest_release) as latest_release, " +
//                    "(strftime('%Y', 'now') - strftime('%Y', first_release)) AS years_active " +
//                    "FROM Studio_Game_Stats " +
//                    "WHERE number_of_games >= 2 AND latest_release >= date('2022-01-01') " +
//                    "ORDER BY number_of_games DESC";
//
//            System.out.println("\nExecuting Query 10: Active Studio Statistics\n" + query);
//            try (ResultSet rs = DBCommand.executeQuery(conn, query)) {
//                DBOutputFormatter.showGenericQueryResult("ACTIVE STUDIO STATISTICS", rs);
//            }
//        } catch (SQLException e) {
//            System.err.println("Failed to execute Query 10");
//            throw e;
//        }
//    }
    private void debugSonyConsoles() throws SQLException {
        String query = "SELECT name, manufacturer, release_year " +
                "FROM gaming_system " +
                "WHERE manufacturer = 'Sony' " +
                "ORDER BY release_year DESC";

        System.out.println("\nDEBUG: All Sony Consoles in Database");
        try (ResultSet rs = DBCommand.executeQuery(conn, query)) {
            DBOutputFormatter.showGenericQueryResult("ALL SONY CONSOLES", rs);
        }
    }
    private void debugStudioGameData() throws SQLException {
        System.out.println("\nDEBUG: Studio Game Counts");
        String query = "SELECT s.studio_name, COUNT(g.game_id) as num_games, " +
                "MIN(g.release_date) as first_release, " +
                "MAX(g.release_date) as last_release " +
                "FROM studio s LEFT JOIN game g ON s.studio_id = g.studio_id " +
                "GROUP BY s.studio_id " +
                "ORDER BY num_games DESC";

        try (ResultSet rs = DBCommand.executeQuery(conn, query)) {
            DBOutputFormatter.showGenericQueryResult("STUDIO GAME STATS", rs);
        }
    }
    }