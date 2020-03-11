package com.sunny.domain;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Employee {
   private Long id;
   private String name;
   private Department dept; // 员工所在部门对象

   @Override
   public String toString() {
      return "Employee{" +
              "id=" + id +
              ", name='" + name + '\'' +
              '}';
   }
}
