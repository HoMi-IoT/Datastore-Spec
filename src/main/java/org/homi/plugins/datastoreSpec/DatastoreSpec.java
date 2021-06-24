package org.homi.plugins.datastoreSpec;
import org.homi.plugin.specification.*;
import org.homi.plugin.specification.types.TypeDef;
import org.homi.plugin.api.observer.*;
import java.util.Map;

import static org.homi.plugin.specification.Constraints.notNull;
import static org.homi.plugin.specification.SpecificationHelper.defineType;

import java.util.List;

class Types{
	static TypeDef<String> key = defineType(String.class, notNull());
	static TypeDef<Object> value = defineType(Object.class, notNull());
	static TypeDef<IObserver> observer = defineType(IObserver.class, notNull());
	static TypeDef<Map> dataset = defineType(Map.class, notNull(), (m)->{return m.keySet().stream().allMatch((k)->{return k instanceof String;});});
}

@SpecificationID(id = "DatastoreSpec")
public enum DatastoreSpec implements ISpecification {
	CREATE(Void.class, Types.key, Types.value),
	READ(Object.class, Types.key),
	UPDATE(Void.class, Types.key, Types.value),
	DELETE(Void.class, Types.key),
	GET_ALL(Types.dataset),
	OBSERVE(Void.class, Types.key, Types.observer);

	private List<TypeDef<?>> parameterTypes;
	private TypeDef<?> returnType;
	DatastoreSpec(Object returnType, Object...parameterTypes ) {
		try {
			this.returnType = SpecificationHelper.processType(returnType);
			this.parameterTypes = SpecificationHelper.processTypes(parameterTypes);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public List<TypeDef<?>> getParameterTypes() {
		return this.parameterTypes;
	}
	
	@Override
	public TypeDef<?> getReturnType() {
		return this.returnType;
	}
}