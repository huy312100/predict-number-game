package cybersoft.javabackend.game.doanso.model;

import java.time.LocalDateTime;

import cybersoft.javabackend.game.doanso.util.DateUtils;

public class GameRecord {
	private int id;
	private Player player;
	private int number;
	private int point;
	private String startDate;
	private String finishDate;
	private boolean isFinish;
	private static int _recordNumber=1;
	
	public GameRecord(Player player) {
		id=_recordNumber++;
		this.player=player;
		number=(int)(Math.random()*1000)+1;
		point=0;
		startDate=DateUtils.toString(LocalDateTime.now());
		this.isFinish=false;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Player getPlayer() {
		return player;
	}
	public void setPlayer(Player player) {
		this.player = player;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public int getPoint() {
		return point;
	}
	public void setPoint(int point) {
		this.point = point;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getFinishDate() {
		return finishDate;
	}
	public void setFinishDate(String finishDate) {
		this.finishDate = finishDate;
	}
	public boolean getIsFinish() {
		return isFinish;
	}
	public void setIsFinish(boolean isFinish) {
		this.isFinish = isFinish;
	}

}
