package cybersoft.javabackend.game.doanso.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import cybersoft.javabackend.game.doanso.model.Player;

public class PlayerRepository {
	public List<Player> findAll() {
		List<Player> players = null;
		try (Connection connection = MySQLConnection.getConnection()) {
			String query = "SELECT username, password FROM player";
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet results = statement.executeQuery();
			players = new ArrayList<>();
			while (results.next()) {
				Player player = new Player(results.getString("username"), results.getString("password"));
				players.add(player);
			}
		} catch (SQLException e) {
			System.out.println("Kết nối đến cơ sở dữ liệu thất bại.");
			e.printStackTrace();
		}
		return players;
	}

	public boolean save(Player player) {

		try (Connection connection = MySQLConnection.getConnection()) {
			String query = "INSERT INTO player(username,password) VALUES (?,?)";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, player.getUsername());
			statement.setString(2, player.getPassword());
			int result = statement.executeUpdate();
			if (result > 0) {
				return true;
			}
		} catch (SQLException e) {
			System.out.println("Kết nối đến cơ sở dữ liệu thất bại.");
			e.printStackTrace();
		}
		return false;
	}

	public boolean delete(String username) {

		try (Connection connection = MySQLConnection.getConnection()) {
			String query = "DELETE FROM player WHERE username like ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, username);
			int result = statement.executeUpdate();
			if (result > 0) {
				return true;
			}
		} catch (SQLException e) {
			System.out.println("Kết nối đến cơ sở dữ liệu thất bại.");
			e.printStackTrace();
		}
		return false;
	}
}
