package com.ulfric.plugin.entities.components.name;

import com.ulfric.commons.naming.Name;
import com.ulfric.plugin.entities.components.ComponentKey;

@Name("generic/name")
enum NameComponentKey implements ComponentKey<NameComponent> {

	INSTANCE;

	@Override
	public Class<NameComponent> getComponentType() {
		return NameComponent.class;
	}

	@Override
	public String toString() {
		return getName();
	}

}
