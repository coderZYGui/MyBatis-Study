package com.sunny.query;

import lombok.Data;
import java.math.BigDecimal;

/**
 * 封装员工的高级查询信息--->封装查询条件
 */
@Data
public class EmployeeQueryObject {
    private String keyword; // 根据keyword来查询,员工名字或编号
    private BigDecimal minSalary; // 最低工资
    private BigDecimal maxSalary; // 最高工资
    private Long deptId = -1L; // 部门ID,缺省为-1;表示所有部门

    /**
     * 重写keyword的get方法,如果
     * @return
     */
    public String getKeyword(){
        return empty2null(keyword);
    }

    // 如果字符串为空串,也应该设置为null
    private String empty2null(String str){
        return hasLength(str) ? str : null;
    }

    // 判断这个字符串是否有数据
    private boolean hasLength(String str){
        // str不为空 并且 str trim()后不和""相等
        /**
         * 判断非空,假如str为zy
         * zy!=null ---> true
         * "".equals(zy.trim()) --> false
         * !"".equals(zy.trim()) ---> !false ---> true
         * 所以
         * true && true ---> true 不为空
         */
        return str!=null && !"".equals(str.trim());
    }
}
