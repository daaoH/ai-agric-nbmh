package com.hszn.nbmh.common.core.mould;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitorWrapper;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.ser.std.StdScalarSerializer;
import com.hszn.nbmh.common.core.constant.DataMaskingConstant;
import com.hszn.nbmh.common.core.enums.DataMaskingEnum;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Objects;

/**
 * @Author：袁德民
 * @Description:
 * @Date:下午8:16 22/8/26
 * @Modified By:
 */
public class DataMaskingSerializer extends StdScalarSerializer<Object> {

    private final DataMaskingConstant operation;

    public DataMaskingSerializer() {
        super(String.class, false);
        this.operation = null;
    }

    public DataMaskingSerializer(DataMaskingConstant operation) {
        super(String.class, false);
        this.operation = operation;
    }


    public boolean isEmpty(SerializerProvider prov, Object value) {
        String str = (String)value;
        return str.isEmpty();
    }

    public void serialize(Object value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        if (Objects.isNull(operation)) {
            String content = DataMaskingEnum.ALL_MASK.operation().mask((String) value, null);
            gen.writeString(content);
        } else {
            String content = operation.mask((String) value, null);
            gen.writeString(content);
        }
    }

    public final void serializeWithType(Object value, JsonGenerator gen, SerializerProvider provider, TypeSerializer typeSer) throws IOException {
        this.serialize(value, gen, provider);
    }

    public JsonNode getSchema(SerializerProvider provider, Type typeHint) {
        return this.createSchemaNode("string", true);
    }

    public void acceptJsonFormatVisitor(JsonFormatVisitorWrapper visitor, JavaType typeHint) throws JsonMappingException {
        this.visitStringFormat(visitor, typeHint);
    }

}
