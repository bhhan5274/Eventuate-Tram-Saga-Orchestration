package com.bhhan.tram.common;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.deser.std.StdScalarDeserializer;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.StdScalarSerializer;

import java.io.IOException;

/**
 * Created by hbh5274@gmail.com on 2020-11-02
 * Github : http://github.com/bhhan5274
 */
public class MoneyModule extends SimpleModule {
    static class MoneyDeserializer extends StdScalarDeserializer<Money>{
        MoneyDeserializer() {
            super(Money.class);
        }

        @Override
        public Money deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
            JsonToken token = jsonParser.getCurrentToken();
            if(token == JsonToken.VALUE_STRING){
                String str = jsonParser.getText().trim();
                if(str.isEmpty()){
                    return null;
                }else{
                    return new Money(str);
                }
            }else {
                throw JsonMappingException.from(jsonParser, "Money deserialize fail!");
            }
        }
    }

    static class MoneySerializer extends StdScalarSerializer<Money>{
        MoneySerializer() {
            super(Money.class);
        }

        @Override
        public void serialize(Money value, JsonGenerator gen, SerializerProvider provider) throws IOException {
            gen.writeString(value.asString());
        }
    }

    public MoneyModule(){
        addDeserializer(Money.class, new MoneyDeserializer());
        addSerializer(Money.class, new MoneySerializer());
    }

    @Override
    public String getModuleName() {
        return "tramCommonModule";
    }
}
