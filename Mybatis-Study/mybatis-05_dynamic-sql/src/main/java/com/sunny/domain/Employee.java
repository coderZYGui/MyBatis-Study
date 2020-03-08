package com.sunny.domain;

import java.math.BigDecimal;


public class Employee {
   private Long id;
   private String name;
   private String sn;
   private BigDecimal salary;
   private Long deptId;

   public Employee() {
   }

   public Employee(Long id, String name, String sn, BigDecimal salary, Long deptId) {
      this.id = id;
      this.name = name;
      this.sn = sn;
      this.salary = salary;
      this.deptId = deptId;
   }

   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getSn() {
      return sn;
   }

   public void setSn(String sn) {
      this.sn = sn;
   }

   public BigDecimal getSalary() {
      return salary;
   }

   public void setSalary(BigDecimal salary) {
      this.salary = salary;
   }

   public Long getDeptId() {
      return deptId;
   }

   public void setDeptId(Long deptId) {
      this.deptId = deptId;
   }

   @Override
   public String toString() {
      return "Employee{" +
              "id=" + id +
              ", name='" + name + '\'' +
              ", sn='" + sn + '\'' +
              ", salary=" + salary +
              ", deptId=" + deptId +
              '}';
   }
}
