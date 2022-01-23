package cybersoft.javabackend.game.doanso.service;

import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.util.ArrayList;

import cybersoft.javabackend.game.doanso.model.GameRecord;
import cybersoft.javabackend.game.doanso.model.Player;
import cybersoft.javabackend.game.doanso.util.DateUtils;

public class GameService {
	private ArrayList<Player> player;
	private ArrayList<GameRecord> records;

	public GameService() {
		player = (ArrayList<Player>) StoreService.players;
		records = (ArrayList<GameRecord>) StoreService.records;
	}

	public GameRecord loadGame(String username, String password) {
		Player loggerUser = null;
		// Check exist player
		for (Player p : player) {
			if (p.getUsername().equalsIgnoreCase(username)) {
				if (p.getPassword().equals(password)) {
					loggerUser = p;
					break;
				}
				return null;
			}
		}
		if (loggerUser == null) {
			loggerUser= new Player(username, password);
			player.add(loggerUser);
		}
		GameRecord gamer = null;
		// Check record
		for (GameRecord re : records) {
			if (re.getPlayer().getUsername().equalsIgnoreCase(username)) {
				gamer = re;
				return gamer;
			}
		}
		gamer = new GameRecord(loggerUser);
		records.add(gamer);
		return gamer;
	}
	
	public GameRecord playgame(int recordID, int tryNumber) {
		GameRecord record=null;
		for(GameRecord re:records) {
			if(re.getId()==recordID) {
				record=re;
				break;
			}
		}
		if(record==null) {
			return null;
		}
		record.setPoint(record.getPoint()+1);
		if(record.getNumber()==tryNumber) {
			record.setIsFinish(true);
			record.setFinishDate(DateUtils.toString(LocalDateTime.now()));
		}
		return record;
	}
}
