package com.ulfric.plugin.entities;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import com.ulfric.dragoon.rethink.Document;
import com.ulfric.plugin.entities.components.Component;
import com.ulfric.plugin.entities.components.ComponentKey;
import com.ulfric.plugin.entities.components.ComponentKeys;

public class Entity extends Document { // TODO thread safety?

	private Map<ComponentKey<?>, Component> components = new HashMap<>();

	public Map<ComponentKey<?>, Component> getComponents() {
		return components;
	}

	public void setComponents(Map<ComponentKey<?>, Component> components) {
		this.components = components;
	}

	public <T extends Component> T getComponent(ComponentKey<T> key) { // TODO not a bean method but in a bean
		Objects.requireNonNull(key, "key");

		return components == null ? null : key.getComponentType().cast(components.get(key));
	}

	public void addComponent(Component component) {
		Objects.requireNonNull(component, "component");

		if (components == null) {
			components = new HashMap<>();
		}

		components.put(ComponentKeys.getByRepresentedType(component.getClass()), component); // TODO validate getByRepresentedType
	}

}
