package cybersoft.javabackend.game.doanso.service;

import java.util.List;

import cybersoft.javabackend.game.doanso.model.Player;
import cybersoft.javabackend.game.doanso.repository.PlayerRepository;

public class PlayerService {
	private PlayerRepository repository;

	public PlayerService() {
		repository = new PlayerRepository();
	}

	public List<Player> findAll() {
		return repository.findAll();
	}

	public boolean save(Player player) {
		return repository.save(player);
	}

	public boolean delete(String username) {
		return repository.delete(username);
	}
}
