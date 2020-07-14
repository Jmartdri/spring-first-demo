package com.example.demouser;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Entity
public class User {
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private long id;

   @NotBlank(message = "Le nom est obligatoire")
   private String name;

   @NotBlank(message = "Le sexe est obligatoire")
   private String gender;

   private boolean state;

   @NotBlank(message = "La matrimoniale est obligatoire")
   private String matrimonial;

   public User() {
   }

   public User(long id, String name, String gender, boolean state,  String matrimonial) {
      this.id = id;
      this.name = name;
      this.gender = gender;
      this.state = state;
      this.matrimonial = matrimonial;
   }

   public long getId() {
      return id;
   }

   public void setId(long id) {
      this.id = id;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getGender() {
      return gender;
   }

   public void setGender(String gender) {
      this.gender = gender;
   }

   public boolean isState() {
      return state;
   }

   public void setState(boolean state) {
      this.state = state;
   }

   public String getMatrimonial() {
      return matrimonial;
   }

   public void setMatrimonial(String matrimonial) {
      this.matrimonial = matrimonial;
   }
}
