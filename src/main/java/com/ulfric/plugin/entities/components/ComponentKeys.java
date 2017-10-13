package com.ulfric.plugin.entities.components;

import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.Map;

import com.ulfric.plugin.entities.components.name.NameComponent;

public class ComponentKeys {

	private static final Map<String, ComponentKey<?>> KEYS_BY_NAME = new HashMap<>();
	private static final Map<Class<?>, ComponentKey<?>> KEYS_BY_TYPE = new IdentityHashMap<>();

	static {
		register(NameComponent.KEY);
	}

	@SuppressWarnings("unchecked")
	public static <T extends Component> ComponentKey<T> getByRepresentedType(Class<T> type) {
		return (ComponentKey<T>) KEYS_BY_TYPE.get(type);
	}

	public static ComponentKey<?> getByName(String name) {
		return KEYS_BY_NAME.get(name);
	}

	public static void register(ComponentKey<?> key) {
		KEYS_BY_NAME.put(key.getName(), key);
		KEYS_BY_TYPE.put(key.getComponentType(), key);
	}

	public static void unregister(ComponentKey<?> key) {
		KEYS_BY_NAME.remove(key.getName(), key);
		KEYS_BY_TYPE.remove(key.getComponentType(), key);
	}

	private ComponentKeys() {
	}

}
