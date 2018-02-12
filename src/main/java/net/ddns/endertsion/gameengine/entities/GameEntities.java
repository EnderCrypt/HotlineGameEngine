package net.ddns.endertsion.gameengine.entities;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class GameEntities implements Serializable
{
	private static final long serialVersionUID = -4603230297232749703L;

	/**
	 * 
	 */

	private Map<Class<? extends GameEntity>, Set<? extends GameEntity>> entities = new HashMap<>();

	// ADD //

	public void add(GameEntity entity)
	{
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

	public <T extends GameEntity> Set<? extends GameEntity> getEntitiesOfSubtype(Class<T> entityClass)
	{
		Set<Class<? extends GameEntity>> classes = entities.keySet();
		classes.removeIf(c -> c.isAssignableFrom(entityClass));
		Set<? extends GameEntity> result = classes.stream().flatMap(c -> getCollection(c).stream()).collect(Collectors.toSet());
		return result;
	}

	// MISC //

	public boolean remove(GameEntity entity)
	{
		return getCollection(entity).remove(entity);
	}

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
