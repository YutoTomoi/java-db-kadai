package kadai_004;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Employees_Chapter04 {
    public static void main(String[] args) {
        Connection con = null;
        Statement stmt = null;

        try {
            // データベースに接続
            con = DriverManager.getConnection(
                "jdbc:mysql://localhost/challenge_java",
                "root",
                "yuto1016" // ★ここを自分のパスワードに書き換えてね
            );
            System.out.println("データベース接続成功：" + con);

            // SQLの送信
            stmt = con.createStatement();
            String sql = """
                    CREATE TABLE employees (
                        id INT(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
                        name VARCHAR(60) NOT NULL,
                        email VARCHAR(255) NOT NULL,
                        age INT(11),
                        address VARCHAR(255)
                    )
                    """;
            int result = stmt.executeUpdate(sql);
            System.out.println("社員テーブルを作成しました:更新レコード数=" + result);

        } catch (SQLException e) {
            System.out.println("データベース接続失敗：" + e.getMessage());

        } finally {
            // 接続を閉じる
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    System.out.println("ステートメント切断失敗：" + e.getMessage());
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    System.out.println("データベース切断失敗：" + e.getMessage());
                }
            }
        }
    }
}
