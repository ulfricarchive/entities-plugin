package com.ulfric.plugin.entities.components;

import java.io.IOException;
import java.util.Objects;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.ulfric.dragoon.extension.inject.Inject;
import com.ulfric.dragoon.gson.ComparisonStrategies;
import com.ulfric.dragoon.gson.DefaultGson;
import com.ulfric.dragoon.gson.DynamicTypeAdapter;

public class ComponentTypeAdapter extends DynamicTypeAdapter {

	@Inject
	private Gson gson;

	public ComponentTypeAdapter() {
		super(ComparisonStrategies.SAME_RAW, TypeToken.get(Component.class));
	}

	@Override
	public Object read(JsonReader in) throws IOException {
		JsonElement json = DefaultGson.INSTANCE.fromJson(in, JsonElement.class);

		if (!json.isJsonObject()) {
			throw new IllegalArgumentException(json + " must be a JsonObject");
		}

		JsonElement typeJson = json.getAsJsonObject().get("type");
		Objects.requireNonNull(typeJson, "type is a required field in " + json);

		ComponentKey<?> key = gson.fromJson(typeJson, ComponentKey.class);

		return DefaultGson.INSTANCE.fromJson(json, key.getComponentType());
	}

	@Override
	public void write(JsonWriter out, Object value) throws IOException {
		Component component = (Component) value;
		if (component.getType() == null) {
			component.setType(ComponentKeys.getByRepresentedType(component.getClass()));
		}

		genericAdapter(value).write(out, value);
	}

	@SuppressWarnings("unchecked")
	private TypeAdapter<Object> genericAdapter(Object object) {
		return (TypeAdapter<Object>) gson.getAdapter(object.getClass());
	}

}
