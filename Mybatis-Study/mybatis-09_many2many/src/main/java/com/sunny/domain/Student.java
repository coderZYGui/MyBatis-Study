package com.sunny.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Student {
   private Long id;
   private String name;
   private List<Teacher> teachers = new ArrayList<Teacher>();

   @Override
   public String toString() {
      return "Student{" +
              "id=" + id +
              ", name='" + name + '\'' +
              '}';
   }
}
