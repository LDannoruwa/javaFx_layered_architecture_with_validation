package dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import db.DbConnection;

public class CrudUtil {

        @SuppressWarnings("unchecked")
        public static <T> T execute(String sql, Object... ar) throws ClassNotFoundException, SQLException {

        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement(sql);
        for (int i = 0; i < ar.length; i++) {
            stm.setObject((i + 1), ar[i]);
        }

        if (sql.startsWith("SELECT")) {
            return (T) stm.executeQuery();
        }
        return (T) (Boolean) (stm.executeUpdate() > 0);

    }
    
}
