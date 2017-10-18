package com.ulfric.plugin.entities;

import com.ulfric.i18n.function.Function;
import com.ulfric.plugin.Plugin;
import com.ulfric.plugin.entities.components.name.locale.NameFunction;

public class EntitiesPlugin extends Plugin {

	public EntitiesPlugin() {
		install(EntitySerializationContainer.class);

		Function.register(new NameFunction()); // TODO feature wrapper
	}

}
