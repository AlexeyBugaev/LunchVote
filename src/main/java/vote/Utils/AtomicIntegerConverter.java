package vote.Utils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

@Converter
public class AtomicIntegerConverter extends JsonDeserializer implements AttributeConverter<AtomicInteger, Integer> {

    @Override
    public Integer convertToDatabaseColumn(AtomicInteger attribute) {
        return attribute.get();
    }

    @Override
    public AtomicInteger convertToEntityAttribute(Integer dbData) {
        return new AtomicInteger(dbData);
    }

    @Override
    public AtomicInteger deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        return new AtomicInteger(ctxt.getParser().getValueAsInt());
    }
}
