package com.atguigu.team.service;

import com.atguigu.team.domain.*;
//import sun.java2d.pipe.OutlineTextRenderer;

import static com.atguigu.team.service.Data.*;

/**
 * 负责将Data中的数据封装到Employee[]数组中
 */
public class NameListService {
    private Employee[] employees;

    /**
     * let Employees and array elements init
     */
    public NameListService() {
        employees = new Employee[EMPLOYYES.length];

        for (int i = 0; i < employees.length; i++) {
            int type = Integer.parseInt(EMPLOYYES[i][0]);

            //get Employee 4 basics info
            int id = Integer.parseInt(EMPLOYYES[i][1]);
            String name = EMPLOYYES[i][2];
            int age = Integer.parseInt(EMPLOYYES[i][3]);
            double salary = Double.parseDouble(EMPLOYYES[i][4]);

            Equipment equipment;
            double bonus;
            int stock;

            switch (type) {
                case EMPLOYEE:
                    employees[i] = new Employee(id, name, age, salary);
                    break;
                case PROGRAMMER:
                    equipment = createEquipment(i);
                    employees[i] = new Programmer(id, name, age, salary, equipment);
                    break;
                case DESIGNER:
                    equipment = createEquipment(i);
                    bonus = Double.parseDouble(EMPLOYYES[i][5]);
                    employees[i] = new Designer(id, name, age, salary, equipment, bonus);
                    break;
                case ARCHITECT:
                    equipment = createEquipment(i);
                    bonus = Double.parseDouble(EMPLOYYES[i][5]);
                    stock = Integer.parseInt(EMPLOYYES[i][6]);
                    employees[i] = new Architect(id, name, age, salary, equipment, bonus, stock);
                    break;
            }
        }
    }





    private Equipment createEquipment(int index) {
        int key = Integer.parseInt(EQUIPMENTS[index][0]);
        String modelOrName = EQUIPMENTS[index][1];

        switch (key) {

            case PC://21
                String display = EQUIPMENTS[index][2];
                return new PC(modelOrName, display);
            case NOTEBOOK://22
                double price = Double.parseDouble(EQUIPMENTS[index][2]);
                return new NoteBook(modelOrName, price);
            case PRINTER://23
                String tpye = EQUIPMENTS[index][2];
                return new Printer(modelOrName, tpye);
        }
        return null;
    }

    public Employee[] getAllEmployees() {
        return employees;
    }



    public Employee getEmployee(int id) throws TeamException {
        for (Employee employee : employees) {
            if (employee.getId() == id) {
                return employee;
            }
        }
        throw new TeamException("找不到指定的員工");

    }



}
