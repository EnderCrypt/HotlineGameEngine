package net.ddns.endercrypt.game.room;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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

	public void save(File file) throws FileNotFoundException, IOException
	{
		try (ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(file)))
		{
			output.writeObject(room);
		}
	}

	public void save(ObjectOutputStream output) throws IOException
	{
		Room room = getRoom().orElseThrow(() -> new NoRoomPresentException("cant save the game whitout a room present"));
		output.writeObject(room);
	}

	public void load(File file) throws FileNotFoundException, IOException, ClassNotFoundException
	{
		try (ObjectInputStream input = new ObjectInputStream(new FileInputStream(file)))
		{
			load(input);
		}
	}

	public void load(ObjectInputStream input) throws ClassNotFoundException, IOException
	{
		room = (Room) input.readObject();
	}
}
