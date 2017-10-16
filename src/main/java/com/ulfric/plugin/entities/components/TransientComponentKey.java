package com.ulfric.plugin.entities.components;

public enum TransientComponentKey implements ComponentKey<Component> {

	INSTANCE;

	@Override
	public Class<Component> getComponentType() {
		return Component.class;
	}

}
