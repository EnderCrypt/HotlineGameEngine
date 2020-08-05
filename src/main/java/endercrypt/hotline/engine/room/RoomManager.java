package endercrypt.hotline.engine.room;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Path;
import java.util.Optional;

import endercrypt.hotline.engine.core.HotlineGameEngine;
import endercrypt.hotline.engine.entities.GameEntity;


public class RoomManager
{
	private final HotlineGameEngine hotline;
	
	private Room room = null;
	
	public RoomManager(HotlineGameEngine hotline)
	{
		this.hotline = hotline;
	}
	
	public Room startRoom(GameEntity initEntity)
	{
		Room newRoom = new Room();
		setRoom(newRoom);
		newRoom.getEntities().add(initEntity);
		return newRoom;
	}
	
	public void setRoom(Room room)
	{
		room.attach(hotline, new View());
		this.room = room;
	}
	
	public boolean hasRoom()
	{
		return (room != null);
	}
	
	public Optional<Room> getRoom()
	{
		return Optional.ofNullable(room);
	}
	
	public void save(Path path) throws FileNotFoundException, IOException
	{
		if (room == null)
		{
			throw new RoomException("cant save the game whitout a room present");
		}
		try (ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(path.toFile())))
		{
			output.writeObject(room);
		}
	}
	
	public void load(Path path) throws FileNotFoundException, IOException, ClassNotFoundException
	{
		setRoom(readRoom(path));
	}
	
	private Room readRoom(Path path) throws FileNotFoundException, IOException, ClassNotFoundException
	{
		try (ObjectInputStream input = new ObjectInputStream(new FileInputStream(path.toFile())))
		{
			return (Room) input.readObject();
		}
	}
}
