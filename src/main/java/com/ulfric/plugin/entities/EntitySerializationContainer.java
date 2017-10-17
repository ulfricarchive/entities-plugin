package com.ulfric.plugin.entities;

import com.ulfric.dragoon.application.Container;
import com.ulfric.dragoon.extension.inject.Inject;
import com.ulfric.dragoon.gson.DynamicTypeAdapterFactory;

public class EntitySerializationContainer extends Container {

	@Inject
	private DynamicTypeAdapterFactory factory;

	@Inject
	private EntityTypeAdapter entityAdapter;

	public EntitySerializationContainer() {
		addBootHook(this::registerSerializer);
		addShutdownHook(this::unregisterSerializer);
	}

	private void registerSerializer() {
		factory.register(entityAdapter);
	}

	private void unregisterSerializer() {
		factory.unregister(entityAdapter);
	}

}
