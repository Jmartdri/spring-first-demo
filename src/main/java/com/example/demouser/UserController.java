package com.example.demouser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
public class UserController {

   @Autowired
   private UserService userService;

   @GetMapping("/")
   public String index(Model model){
      List<User> userList = userService.getAllUser(0, 10, "id");
      model.addAttribute("userList", userList);
      return "listUser";
   }

   @GetMapping("/addNewUser")
   public String addNewUser(User user){
      return "addNewUser";
   }

   @PostMapping("/addNewUser")
   public   String saveNewUser(@Validated User user, BindingResult result, Model model){
      if (result.hasErrors()) {
         return "addNewUser";
      }
      userService.save(user);
      model.addAttribute("user", new User());
      return  "addNewUser";
   }

   @GetMapping("/getAllUser")
   public String findAll(@RequestParam(defaultValue = "0") Integer pageNo,
         @RequestParam(defaultValue = "10") Integer pageSize,
         @RequestParam(defaultValue = "id") String sortBy,
         Model model
         ){
      List<User> userList = userService.getAllUser(pageNo, pageSize, sortBy);
      model.addAttribute("userList", userList);
      return "listUser";
   }

   @PostMapping("/saveUser/{id}")
   public   String saveUser(@PathVariable("id") long id,
         @Valid User user, BindingResult result,
         Model model){
      if (result.hasErrors()) {
         user.setId(id);
         return "saveUser";
      }
      userService.save(user);
      List<User> userList = userService.getAllUser(0, 10, "id");
      model.addAttribute("userList", userList);
      return "listUser";
   }

   @GetMapping("/editUser/{id}")
   public String editUser(@PathVariable("id") Long id, Model model){
      Optional<User> user = userService.findById(id);
      if(user.isPresent()) {
         model.addAttribute("user", user.get());
         return "saveUser";
      }
      List<User> userList = userService.getAllUser(0, 10, "id");
      model.addAttribute("userList", userList);
      return "listUser";
   }

   @GetMapping("/deleteUser/{id}")
   public String deleteUser(@PathVariable("id") Long id, Model model){
      Optional<User> user = userService.findById(id);
      if(user.isPresent()) {
        userService.delete(user.get());
      }
      List<User> userList = userService.getAllUser(0, 10, "id");
      model.addAttribute("userList", userList);
      return "listUser";
   }

}
