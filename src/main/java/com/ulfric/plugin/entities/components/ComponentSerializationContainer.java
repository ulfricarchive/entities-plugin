package com.ulfric.plugin.entities.components;

import com.ulfric.dragoon.application.Container;
import com.ulfric.dragoon.extension.inject.Inject;
import com.ulfric.dragoon.gson.DynamicTypeAdapterFactory;

public class ComponentSerializationContainer extends Container {

	@Inject
	private DynamicTypeAdapterFactory factory;

	@Inject
	private ComponentTypeAdapter componentAdapter;

	@Inject
	private ComponentKeyTypeAdapter componentKeyAdapter;

	public ComponentSerializationContainer() {
		addBootHook(this::registerSerializer);
		addShutdownHook(this::unregisterSerializer);
	}

	private void registerSerializer() {
		factory.register(componentAdapter);
		factory.register(componentKeyAdapter);
	}

	private void unregisterSerializer() {
		factory.unregister(componentAdapter);
		factory.unregister(componentKeyAdapter);
	}

}
