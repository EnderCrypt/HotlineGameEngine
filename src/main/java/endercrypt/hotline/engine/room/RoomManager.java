package endercrypt.hotline.engine.room;


import java.awt.Dimension;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Path;
import java.util.Optional;
import java.util.function.Supplier;

import endercrypt.hotline.engine.entities.GameEntity;


public class RoomManager
{
	private Room room = null;
	
	private Supplier<Dimension> screenDimensionSupplier;
	
	public RoomManager(Supplier<Dimension> screenDimensionSupplier)
	{
		this.screenDimensionSupplier = screenDimensionSupplier;
	}
	
	public Room startRoom(GameEntity initEntity)
	{
		Room newRoom = new Room();
		setRoom(newRoom);
		newRoom.entities().add(initEntity);
		return newRoom;
	}
	
	public void setRoom(Room room)
	{
		room.attach(new View(screenDimensionSupplier));
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
		try (ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(path.toFile())))
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
	
	public void load(Path path) throws FileNotFoundException, IOException, ClassNotFoundException
	{
		try (ObjectInputStream input = new ObjectInputStream(new FileInputStream(path.toFile())))
		{
			load(input);
		}
	}
	
	public void load(ObjectInputStream input) throws ClassNotFoundException, IOException
	{
		setRoom((Room) input.readObject());
	}
}
