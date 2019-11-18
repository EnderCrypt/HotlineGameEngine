package endercrypt.hotline.engine.room;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JFrame;

import endercrypt.hotline.engine.entities.GameEntity;


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
		room.attach(new View(jFrame));
		this.room = room;
	}
	
	public boolean hasRoom()
	{
		return (room != null);
	}
	
	public Room getRoom()
	{
		if (hasRoom() == false)
		{
			throw new RoomException("no room present");
		}
		return room;
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
			throw new RoomException("cant save the game whitout a room present");
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
