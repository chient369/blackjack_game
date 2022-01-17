package com.blackjack.entity.room;

import java.util.HashMap;
import java.util.Map;

public class RoomStorage {
	private Map<String, Room> rooms;
	private static RoomStorage instance;

	private RoomStorage() {
		rooms = new HashMap<String, Room>();
	}

	public synchronized static RoomStorage getInstance() {
		if (instance == null) {
			instance = new RoomStorage();
		}
		return instance;
	}

	public Map<String, Room> getRooms() {
		return rooms;
	}

	public void setRoom(Room room) {
		rooms.put(room.getRoomId(), room);
	}
	public boolean isExist(String id) {
		return rooms.containsKey(id);
	}
}
