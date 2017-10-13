package com.ulfric.plugin.entities.components;

import com.ulfric.commons.naming.Named;

public interface ComponentKey<T extends Component> extends Named {

	Class<T> getComponentType();

}
