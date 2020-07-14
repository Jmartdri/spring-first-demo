package com.example.demouser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
   @Autowired
   UserRepository userRepository;

   public User save(User user){
      return userRepository.save(user);
   }

   public List<User> getAllUser(Integer pageNo, Integer pageSize, String sortBy)
   {
      Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

      Page<User> pagedResult = userRepository.findAll(paging);

      if(pagedResult.hasContent()) {
         return pagedResult.getContent();
      } else {
         return new ArrayList<User>();
      }
   }

   public Optional<User> findById(Long id){
      return userRepository.findById(id);
   }

   public void  delete(User user){
       userRepository.delete(user);;
   }

}
