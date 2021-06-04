package org.homi.plugins.datastoreSpec;
import org.homi.plugin.specification.*;
import org.homi.plugin.specification.types.TypeDef;
import org.homi.plugin.api.observer.*;

import static org.homi.plugin.specification.Constraints.notNull;
import static org.homi.plugin.specification.SpecificationHelper.defineType;

import java.util.List;

class Types{
	static TypeDef<String> key = defineType(String.class, notNull());
	static TypeDef<Object> value = defineType(Object.class, notNull());
	static TypeDef<IObserver> observer = defineType(IObserver.class, notNull());
}

@SpecificationID(id = "DatastoreSpec")
public enum DatastoreSpec implements ISpecification {
	CREATE(Void.class, Types.key, Types.value),
	READ(Object.class, Types.key),
	UPDATE(Void.class, Types.key, Types.value),
	DELETE(Void.class, Types.key),
	OBSERVE(Void.class, Types.key, Types.observer);
	

	/*RETURN_NULL(Void.class),
	RETURN_STRING(String.class),
	RETURN_INTEGER(Integer. class),
	RETURN_FLOAT(Float.class),
	RETURN_OBJECT(Object.class),
	RETURN_WRONG_TYPE(Float.class),
	SEND_STRING(String.class, String.class),
	SEND_CONSTRAINED_STRING(String.class, defineType(String.class, notNull(), contains("18"))),
	SEND_CONSTRAINED_Integer(Integer.class, 
			defineType(
				Integer.class, 
				notNull(), 
				or(isEqualTo(14), isOneOf(List.of(1,2,3,4)))
				)),
	SEND_INTEGER(Void.class, Integer.class),
	SEND_CUSTOM(Void.class, defineSerializableType(Custom.class));*/

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