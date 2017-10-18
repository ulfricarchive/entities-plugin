package com.ulfric.plugin.entities.components.name.locale;

import java.util.ArrayList;
import java.util.Map;

import org.apache.commons.collections4.MapUtils;

import com.ulfric.i18n.function.Function;
import com.ulfric.plugin.entities.Entity;
import com.ulfric.plugin.entities.components.Component;
import com.ulfric.plugin.entities.components.ComponentKey;

public class ComponentsFunction extends Function<Entity> {

	public ComponentsFunction() {
		super("components", Entity.class);
	}

	@Override
	public Object apply(Entity entity) {
		Map<ComponentKey<?>, Component> components = entity.getComponents();
		if (MapUtils.isEmpty(components)) {
			return null;
		}
		return new ArrayList<>(components.values());
	}

}
