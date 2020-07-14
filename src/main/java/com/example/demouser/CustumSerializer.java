package com.example.demouser;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;

public class CustumSerializer  extends StdDeserializer<User> {

   public CustumSerializer() {
      this(null);
   }

   public CustumSerializer(Class<?> vc) {
      super(vc);
   }

   @Override public User deserialize(JsonParser jsonParser,
         DeserializationContext deserializationContext) throws IOException {
      JsonNode node = jsonParser.getCodec().readTree(jsonParser);
      long id = node.get("id").asLong();
      String name = node.get("name").textValue();
      String gender = node.get("gender").textValue();
      String matrimonial = node.get("matrimonial").textValue();
      boolean state = StringUtils.equalsIgnoreCase(node.get("state").textValue(), "1") ? true : false;
      return new User(id, name, gender,state, matrimonial);
   }
}
