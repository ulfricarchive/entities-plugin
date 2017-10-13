package com.ulfric.plugin.entities;

import com.ulfric.plugin.Plugin;
import com.ulfric.plugin.entities.components.ComponentSerializationContainer;

public class EntitiesPlugin extends Plugin {

	public EntitiesPlugin() {
		install(ComponentSerializationContainer.class);
	}

}
