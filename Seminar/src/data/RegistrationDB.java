package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import util.ConnectionPool;
import util.DBUtil;

public class RegistrationDB {

	public static int insert(int UserId, int CourseId) {
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = pool.getConnection();
		PreparedStatement ps = null;

		String query = "INSERT INTO Registration (UserID, CourseID) " + "VALUES (?, ?, ?)";
		try {
			ps = connection.prepareStatement(query);
			ps.setInt(1, UserId);
			ps.setInt(2, CourseId);
			return ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e);
			return 0;
		} finally {
			DBUtil.closePreparedStatement(ps);
			pool.freeConnection(connection);
		}
	}

	public static int delete(int userId) {
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = pool.getConnection();
		PreparedStatement ps = null;

		String query = "DELETE FROM Registration " + "WHERE UserId = ?";
		try {
			ps = connection.prepareStatement(query);
			ps.setInt(1, userId);
			return ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e);
			return 0;
		} finally {
			DBUtil.closePreparedStatement(ps);
			pool.freeConnection(connection);
		}
	}

	public static boolean userIdExists(int userId) {
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = pool.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;

		String query = "SELECT UserID FROM Accommodation WHERE UserID = ? limit 1";
		try {
			ps = connection.prepareStatement(query);
			ps.setInt(1, userId);
			rs = ps.executeQuery();
			return rs.next();
		} catch (SQLException e) {
			System.out.println(e);
			return false;
		} finally {
			DBUtil.closeResultSet(rs);
			DBUtil.closePreparedStatement(ps);
			pool.freeConnection(connection);
		}
	}
}