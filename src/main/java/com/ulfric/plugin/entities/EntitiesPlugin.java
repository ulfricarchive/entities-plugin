package com.ulfric.plugin.entities;

import com.ulfric.plugin.Plugin;

public class EntitiesPlugin extends Plugin {

	public EntitiesPlugin() {
		install(EntitySerializationContainer.class);
	}

}
