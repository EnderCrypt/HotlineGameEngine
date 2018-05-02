package net.ddns.endercrypt.game.room;

import java.io.File;
import java.util.Optional;

import net.ddns.endercrypt.game.entities.GameEntity;

public class RoomManager
{
	private Room room = null;

	public RoomManager()
	{

	}

	public Room startRoom(GameEntity initEntity)
	{
		Room room = new Room();
		room.entities().add(initEntity);
		setRoom(room);
		return room;
	}

	public void setRoom(Room room)
	{
		this.room = room;
	}

	public Optional<Room> getRoom()
	{
		return Optional.ofNullable(room);
	}

	public boolean hasRoom()
	{
		return (room != null);
	}

	public void save(File file)
	{
		// TODO: implement
	}

	public void load(File file)
	{
		// TODO: implement
	}
}
