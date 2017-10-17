package com.ulfric.plugin.entities;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import org.apache.commons.collections4.MapUtils;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.ulfric.dragoon.extension.inject.Inject;
import com.ulfric.dragoon.gson.ComparisonStrategies;
import com.ulfric.dragoon.gson.DynamicTypeAdapter;
import com.ulfric.plugin.entities.components.Component;
import com.ulfric.plugin.entities.components.ComponentKey;
import com.ulfric.plugin.entities.components.ComponentKeys;

public class EntityTypeAdapter extends DynamicTypeAdapter { // TODO cleanup

	@Inject
	private Gson gson;

	@Inject(optional = true)
	private Logger logger;

	public EntityTypeAdapter() {
		super(ComparisonStrategies.SAME_RAW, TypeToken.get(Entity.class));
	}

	@Override
	public Object read(JsonReader in) throws IOException {
		JsonElement json = gson.fromJson(in, JsonElement.class);

		if (json == null) {
			return null;
		}

		Entity entity = new Entity();

		if (!json.isJsonObject()) {
			return entity;
		}

		JsonObject componentsJson = json.getAsJsonObject().getAsJsonObject("components");
		if (componentsJson == null) {
			return entity;
		}

		Map<ComponentKey<?>, Component> components = new HashMap<>();

		for (Map.Entry<String, JsonElement> entry : componentsJson.entrySet()) {
			JsonElement componentJson = entry.getValue();
			if (componentJson == null) {
				continue;
			}

			ComponentKey<?> key = ComponentKeys.getByName(entry.getKey());
			if (key == null) {
				warn("Unknown component type " + entry.getKey());
				continue;
			}

			Component component = gson.fromJson(componentJson, key.getComponentType());
			if (component == null) {
				continue;
			}

			components.put(key, component);
		}

		entity.setComponents(components);

		return entity;
	}

	@Override
	public void write(JsonWriter out, Object value) throws IOException {
		if (value == null) {
			out.nullValue();
			return;
		}

		Entity entity = (Entity) value;
		Map<ComponentKey<?>, Component> components = entity.getComponents();
		if (MapUtils.isEmpty(components)) {
			return;
		}

		JsonObject json = new JsonObject();
		components.forEach((key, component) -> json.add(key.getName(), gson.toJsonTree(component)));

		out.beginObject();
		out.name("components")
			.jsonValue(gson.toJson(json));
		out.endObject();
	}

	private void warn(String message) {
		if (logger != null) {
			logger.warning(message);
		}
	}

}
