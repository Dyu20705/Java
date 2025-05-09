package Utils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SQL {
    private static final String URL = "jdbc:mysql://localhost:3306/cube";
    private static final String USER = "root";
    private static final String PASSWORD = "";
    
    // Khoi tạo driver MySQL
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("MySQL Driver missing!");
            e.printStackTrace();
        }
    }

    /** Đọc tất cả score từ bảng Score */
    public static List<String[]> selectScores(boolean ascending) {
        List<String[]> scores = new ArrayList<>();
        String order = ascending ? "ASC" : "DESC";
        String sql = "SELECT * FROM Score ORDER BY score " + order;
        try (
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            Statement stmt = conn.createStatement(
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = stmt.executeQuery(sql)
        ) {
            while (rs.next()) {
            	int id = rs.getInt("id");
                int score = rs.getInt("score");
                String status = rs.getString("status");
                scores.add(new String[]{ String.valueOf(id), String.valueOf(score), status });
            }
        } catch (SQLException e) {
            System.err.println("Error when selecting scores.");
            e.printStackTrace();
        }
        return scores;
    }
    
    /** Thêm 1 record score mới vào bảng Score */
    public static void insertScore(int score, String status) {
        String sql = "INSERT INTO Score(id, score, status) VALUES(?, ?, ?)";
        try (
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {
        	ps.setInt(1, generateNewId()); 
            ps.setInt(2, score);
            ps.setString(3, status);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error when inserting score.");
            e.printStackTrace();
        }
    }
    
    // Tìm kiếm id lớn nhất trong bảng Score để tạo id mới
    private static int generateNewId() {
		int id = 0;
		String sql = "SELECT MAX(id) AS max_id FROM Score";
		try (
			Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql)
		) {
			if (rs.next()) {
				id = rs.getInt("max_id") + 1;
			}
		} catch (SQLException e) {
			System.err.println("Error when generating new ID.");
			e.printStackTrace();
		}
		return id;
	}
}
