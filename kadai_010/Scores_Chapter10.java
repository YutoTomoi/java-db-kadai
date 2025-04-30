package kadai_010;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Scores_Chapter10 {
    public static void main(String[] args) {
        Connection con = null;
        Statement stmt = null;

        try {
            
            con = DriverManager.getConnection(
                "jdbc:mysql://localhost/challenge_java",
                "root",
                "yuto1016"
            );
            System.out.println("データベース接続成功：" + con);

            
            System.out.println("レコード更新を実行します");

            String sqlUpdate = """
                UPDATE scores
                SET score_math = 95, score_english = 80
                WHERE id = 5
            """;

            stmt = con.createStatement();
            int result = stmt.executeUpdate(sqlUpdate);
            System.out.println(result + "件のレコードが更新されました");

            
            System.out.println("数学・英語の点数が高い順に並べ替えました");

            String sqlSelect = """
                SELECT id, name, score_math, score_english
                FROM scores
                ORDER BY score_math DESC, score_english DESC
            """;

            ResultSet rs = stmt.executeQuery(sqlSelect);
            int count = 1;
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int math = rs.getInt("score_math");
                int english = rs.getInt("score_english");
                System.out.println(count + "件目：生徒ID=" + id +
                                   "／氏名=" + name +
                                   "／数学=" + math +
                                   "／英語=" + english);
                count++;
            }

        } catch (SQLException e) {
            System.out.println("データベース接続失敗：" + e.getMessage());
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                System.out.println("切断エラー：" + e.getMessage());
            }
        }
    }
}
