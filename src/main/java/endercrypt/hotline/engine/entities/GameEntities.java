package endercrypt.hotline.engine.entities;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import endercrypt.hotline.engine.room.Room;

public class GameEntities implements Serializable
{
	private static final long serialVersionUID = -4603230297232749703L;

	/**
	 * 
	 */

	private Map<Class<? extends GameEntity>, Set<? extends GameEntity>> entities = new HashMap<>();

	private Room roomContext;

	public GameEntities(Room roomContext)
	{
		this.roomContext = roomContext;
	}

	// ADD //

	public void add(GameEntity entity)
	{
		if (entity.getRoomContext() != null)
		{
			throw new IllegalArgumentException("Cannot add entity " + entity + " as its already been placed in a room");
		}
		entity.setRoomContext(roomContext);
		getCollection(entity).add(entity);
	}

	public void addAll(GameEntity... entitiesArray)
	{
		Arrays.stream(entitiesArray).forEach(this::add);
	}

	public void addAll(Collection<GameEntity> entitiesCollection)
	{
		entitiesCollection.stream().forEach(this::add);
	}

	// GET //

	public <T extends GameEntity> Optional<T> getEntity(Class<T> entityClass)
	{
		return getCollection(entityClass).stream().findAny();
	}

	public <T extends GameEntity> Set<T> getEntitiesOf(Class<T> entityClass)
	{
		return Collections.unmodifiableSet(getCollection(entityClass));
	}

	@SuppressWarnings("unchecked")
	public <T extends GameEntity> List<? extends T> getEntitiesOfSubtype(Class<T> entityClass)
	{
		Set<Class<? extends GameEntity>> classes = entities.keySet();
		classes.removeIf(c -> !entityClass.isAssignableFrom(c));
		List<? extends T> result = (List<? extends T>) classes.stream().flatMap(c -> getCollection(c).stream()).collect(Collectors.toList());
		return Collections.unmodifiableList(result);
	}

	@SuppressWarnings("unchecked")
	public Stream<GameEntity> getAllEntities()
	{
		return (Stream<GameEntity>) entities.values().stream().flatMap(e -> e.stream());
	}

	public Stream<GameEntity> getAllEntitiesByDepth()
	{
		return getAllEntities().sorted(new Comparator<GameEntity>()
		{
			@Override
			public int compare(GameEntity e1, GameEntity e2)
			{
				return Integer.compare(e2.depth, e1.depth);
			}
		});
	}

	// COUNT //

	public int countEntities()
	{
		return entities.values().stream().mapToInt(e -> e.size()).sum();
	}

	public int countEntities(Class<? extends GameEntity> entityClass)
	{
		return getCollection(entityClass).size();
	}

	// REMOVE //

	public boolean remove(GameEntity entity)
	{
		if (entity.getRoomContext() == null)
		{
			throw new IllegalArgumentException("Cannot remove entity " + entity + " as its currently not in a room");
		}
		return getCollection(entity).remove(entity);
	}

	public void clear(Class<? extends GameEntity> entityClass)
	{
		getCollection(entityClass).forEach(this::remove);
	}

	public void clearAll()
	{
		for (Class<? extends GameEntity> entityClass : entities.keySet())
		{
			clear(entityClass);
		}
	}

	// MISC //

	public boolean contains(GameEntity entity)
	{
		return getCollection(entity).contains(entity);
	}

	// COLLECTION //

	@SuppressWarnings("unchecked")
	private <T extends GameEntity> Set<T> getCollection(T entity)
	{
		return (Set<T>) getCollection(entity.getClass());
	}

	@SuppressWarnings("unchecked")
	private <T extends GameEntity> Set<T> getCollection(Class<T> entityClass)
	{
		Set<T> list = (Set<T>) entities.get(entityClass);
		if (list == null)
		{
			list = new HashSet<>();
			entities.put(entityClass, list);
		}
		return list;
	}
}
