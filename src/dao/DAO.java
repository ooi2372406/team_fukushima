package dao;

import java.sql.Connection;
//import なにかくる１
//import なにかくる２
//import なにかくる３

import javax.sql.DataSource;

public class DAO {
	static DataSource ds;

	public Connection getConnection() throws Exception {
		// データベースへのコネクションを返却
		return ds.getConnection();
	}
}
