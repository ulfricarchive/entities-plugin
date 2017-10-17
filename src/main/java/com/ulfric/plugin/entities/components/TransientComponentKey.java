package com.ulfric.plugin.entities.components;

import com.ulfric.commons.naming.Name;

@Name("transient")
public enum TransientComponentKey implements ComponentKey<Component> {

	INSTANCE;

	@Override
	public Class<Component> getComponentType() {
		return Component.class;
	}

}
