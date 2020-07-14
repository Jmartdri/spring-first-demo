package com.example.demouser;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;

import javax.annotation.PostConstruct;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Arrays;
import java.util.List;

import static java.nio.charset.StandardCharsets.UTF_8;

@Component
public class DataLoader {
   @Autowired
   private UserRepository userRepository;

   @Autowired
   private ResourceLoader resourceLoader;

   @PostConstruct
   public void initData(){
      List<User> userList = asUser(resourceLoader.getResource("classpath:user-data.json"));
      userRepository.saveAll(userList);
   }

   public static List<User> asUser(Resource resource) {
      try (Reader reader = new InputStreamReader(resource.getInputStream(), UTF_8)) {
         String raw =  FileCopyUtils.copyToString(reader);
         ObjectMapper objectMapper = new ObjectMapper();
         SimpleModule module = new SimpleModule();
         module.addDeserializer(User.class, new CustumSerializer());
         objectMapper.registerModule(module);

         JsonNode jsonNode = objectMapper.readTree(raw);
         if(jsonNode.isArray()) {
            TypeReference<List<User>> listUserType = new TypeReference<List<User>>() {
            };
            return objectMapper.convertValue(jsonNode, listUserType);
         }else{
            return Arrays.asList(objectMapper.convertValue(jsonNode, User.class ));
         }
      } catch (Exception e) {
         throw new RuntimeException(e);
      }
   }

}
