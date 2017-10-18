package com.ulfric.plugin.entities.components.name.locale;

import com.ulfric.i18n.function.Function;
import com.ulfric.plugin.entities.Entity;
import com.ulfric.plugin.entities.components.name.NameComponent;

public class NameFunction extends Function<Entity> {

	public NameFunction() {
		super("name", Entity.class);
	}

	@Override
	public Object apply(Entity entity) {
		NameComponent name = entity.getComponent(NameComponent.KEY);
		return name == null ? null : name.getName();
	}

}
