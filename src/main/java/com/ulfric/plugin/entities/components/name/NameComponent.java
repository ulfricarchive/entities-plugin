package com.ulfric.plugin.entities.components.name;

import com.ulfric.plugin.entities.components.Component;
import com.ulfric.plugin.entities.components.ComponentKey;

public class NameComponent extends Component {

	public static final ComponentKey<NameComponent> KEY = NameComponentKey.INSTANCE;

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
