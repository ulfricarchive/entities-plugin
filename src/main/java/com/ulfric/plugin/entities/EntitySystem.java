package com.ulfric.plugin.entities;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import com.ulfric.dragoon.rethink.Instance;
import com.ulfric.dragoon.rethink.Location;
import com.ulfric.dragoon.rethink.RuntimeStore;
import com.ulfric.dragoon.rethink.response.Response;

public abstract class EntitySystem extends RuntimeStore<Entity> {

	public CompletableFuture<Instance<Entity>> createEntity(Object key) {
		Location location = Location.key(key);

		return store().get(location);
	}

	public Instance<Entity> getCachedEntity(Object key) {
		Location location = Location.key(key);

		return store().getFromLocalCache(location);
	}

	public CompletableFuture<Response> persist(Entity entity) {
		return store().insert(entity);
	}

	public CompletableFuture<Response> delete(Entity entity) {
		return store().delete(entity);
	}

	public CompletableFuture<List<Instance<Entity>>> getAllEntities() {
		return store().listAllFromDatabase();
	}

}
