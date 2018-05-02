package net.ddns.endercrypt.game.room;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Optional;

import javax.swing.JFrame;

import net.ddns.endercrypt.game.entities.GameEntity;

public class RoomManager
{
	private Room room = null;

	private JFrame jFrame;

	public RoomManager(JFrame jFrame)
	{
		this.jFrame = jFrame;
	}

	public Room startRoom(GameEntity initEntity)
	{
		Room newRoom = new Room();
		newRoom.entities().add(initEntity);
		setRoom(newRoom);
		return newRoom;
	}

	public void setRoom(Room room)
	{
		this.room = room;
		if (room.view == null)
		{
			room.view = new View(jFrame);
		}
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
			save(output);
		}
	}

	public void save(ObjectOutputStream output) throws IOException
	{
		if (room == null)
		{
			throw new NoRoomPresentException("cant save the game whitout a room present");
		}
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
