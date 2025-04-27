package kadai_007;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Posts_Chapter07 {
    public static void main(String[] args) {
        Connection con = null;
        Statement stmt = null;

        try {
            // データベースに接続
            con = DriverManager.getConnection(
                "jdbc:mysql://localhost/challenge_java",
                "root",
                "yuto1016"
            );
            System.out.println("データベース接続成功：" + con);

            // レコード追加を実行する
            System.out.println("レコード追加を実行します");

            // SQL文を準備する（5件まとめて）
            String sqlInsert = """
                INSERT INTO posts (user_id, posted_at, post_content, likes)
                VALUES
                (1003, '2023-02-08', '昨日の夜は徹夜でした・・', 13),
                (1002, '2023-02-08', 'お疲れ様です！', 12),
                (1003, '2023-02-09', '今日も頑張ります！', 18),
                (1001, '2023-02-09', '無理は禁物ですよ！', 17),
                (1002, '2023-02-10', '明日から連休ですね！', 20)
                """;

            // 追加実行
            stmt = con.createStatement();
            int resultInsert = stmt.executeUpdate(sqlInsert);

            System.out.println(resultInsert + "件のレコードが追加されました");

            // ユーザーIDが1002のレコードを検索する
            System.out.println("ユーザーIDが1002のレコードを検索しました");

            String sqlSelect = "SELECT posted_at, post_content, likes FROM posts WHERE user_id = 1002";
            ResultSet resultSet = stmt.executeQuery(sqlSelect);

            int count = 1;
            while (resultSet.next()) {
                var postedAt = resultSet.getDate("posted_at");
                var postContent = resultSet.getString("post_content");
                var likes = resultSet.getInt("likes");

                System.out.println(count + "件目：投稿日時=" + postedAt + "／投稿内容=" + postContent + "／いいね数=" + likes);
                count++;
            }
        } catch (SQLException e) {
            System.out.println("データベース接続失敗：" + e.getMessage());
        } finally {
            // リソースを閉じる
            try {
                if (stmt != null) stmt.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                System.out.println("切断時にエラー発生：" + e.getMessage());
            }
        }
    }
}
