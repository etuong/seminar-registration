package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Accommodation;
import util.ConnectionPool;
import util.DBUtil;

public class AccommodationDB {

	public static int insert(Accommodation accommodation) {
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = pool.getConnection();
		PreparedStatement ps = null;

		String query = "INSERT INTO Accommodation (isHotel, isParking, UserId) " + "VALUES (?, ?, ?)";
		try {
			ps = connection.prepareStatement(query);
			ps.setBoolean(1, accommodation.getIsParking());
			ps.setBoolean(2, accommodation.getIsHotel());
			ps.setInt(3, accommodation.getUserId());
			return ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e);
			return 0;
		} finally {
			DBUtil.closePreparedStatement(ps);
			pool.freeConnection(connection);
		}
	}

	public static int update(Accommodation accommodation) {
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = pool.getConnection();
		PreparedStatement ps = null;

		String query = "UPDATE Accommodation SET " + "isParking = ?, " + "isHotel = ? " + "WHERE UserId = ?";
		try {
			ps = connection.prepareStatement(query);
			ps.setBoolean(1, accommodation.getIsParking());
			ps.setBoolean(2, accommodation.getIsHotel());
			ps.setInt(3, accommodation.getUserId());

			return ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e);
			return 0;
		} finally {
			DBUtil.closePreparedStatement(ps);
			pool.freeConnection(connection);
		}
	}

	public static int delete(Integer userId) {
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = pool.getConnection();
		PreparedStatement ps = null;

		String query = "DELETE FROM Accommodation " + "WHERE UserId = ?";
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

	public static Accommodation select(Integer userId) {
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = pool.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;

		String query = "SELECT * FROM Accommodation " + "WHERE UserId = ?";
		try {
			ps = connection.prepareStatement(query);
			ps.setInt(1, userId);
			rs = ps.executeQuery();
			Accommodation accommodation = null;
			if (rs.next()) {
				accommodation = new Accommodation();
				accommodation.setHotel(rs.getBoolean("isHotel"));
				accommodation.setParking(rs.getBoolean("isParking"));
				accommodation.setUserId(userId);
			}
			return accommodation;
		} catch (SQLException e) {
			System.out.println(e);
			return null;
		} finally {
			DBUtil.closeResultSet(rs);
			DBUtil.closePreparedStatement(ps);
			pool.freeConnection(connection);
		}
	}

	public static boolean userIdExists(int userId) {
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = pool.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;

		String query = "SELECT UserId FROM Accommodation WHERE UserId = ? limit 1";
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