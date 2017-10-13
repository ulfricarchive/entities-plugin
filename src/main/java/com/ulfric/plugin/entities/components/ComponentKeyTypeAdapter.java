package com.ulfric.plugin.entities.components;

import java.io.IOException;

import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.ulfric.dragoon.gson.ComparisonStrategies;
import com.ulfric.dragoon.gson.DynamicTypeAdapter;

public class ComponentKeyTypeAdapter extends DynamicTypeAdapter {

	public ComponentKeyTypeAdapter() {
		super(ComparisonStrategies.POLYMORPHIC, TypeToken.get(ComponentKey.class));
	}

	@Override
	public Object read(JsonReader in) throws IOException {
		String name = in.nextString();

		return ComponentKeys.getByName(name);
	}

	@Override
	public void write(JsonWriter out, Object value) throws IOException {
		ComponentKey<?> key = (ComponentKey<?>) value;
		out.value(key.getName());
	}

}
