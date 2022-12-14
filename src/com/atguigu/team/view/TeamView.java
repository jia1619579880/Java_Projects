package com.atguigu.team.view;

import com.atguigu.team.domain.Employee;
import com.atguigu.team.domain.Programmer;
import com.atguigu.team.service.NameListService;
//import com.atguigu.team.service.TeamException;
import com.atguigu.team.service.TeamException;
import com.atguigu.team.service.TeamService;

public class TeamView {

    private NameListService listSvc = new NameListService();
    private TeamService teamSvc = new TeamService();
    char c = 0;

    private void enterMainMenu() {
        boolean loopFlag = true;
        while (loopFlag) {
            if (c != '1') {
                listAllEmployees();
            }
            System.out.print("1-团队列表 2-添加团队 3-删除团队成员 4-退出 请选择（1-4）:");
            c = TSUtility.readMenuSelection();
            switch (c) {
                case '1':
                    getTeam();
                    break;
                case '2':
                    addMember();
                    break;

                case '3':
                    deleteMember();
                    break;
                case '4':
                    System.out.println("确认是否退出(Y/N):");
                    char isExit = TSUtility.readConfirmSelection();
                    if (isExit == 'Y') {
                        System.exit(0);
                    }
                    break;
            }
        }

    }

    /**
     * 显示所有的员工信息
     */
    private void listAllEmployees() {
        System.out.println("==========开发团队调度软件==========");
        Employee[] allEmployees = listSvc.getAllEmployees();
        if (allEmployees == null || allEmployees.length == 0) {
            System.out.println("公司中没有任何员工信息!");
        } else {
            System.out.println("ID\t姓名\t\t年龄\t\t工资\t\t职位\t\t状态\t\t奖金\t\t股票\t\t领用设备");
            for (Employee allEmployee : allEmployees) {
                System.out.println(allEmployee);
            }

        }
        System.out.println("============================");

    }

    /**
     *
     */
    private void getTeam() {
//        System.out.println("产看开发团队情况");
        System.out.println("==========团队成员列表==========");

        Programmer[] team = teamSvc.getTeam();
        if (team == null || team.length == 0) {
            System.out.println("开发团队目前没有成员!");
        } else {
            System.out.println("TID/ID\t姓名\t\t年龄\t\t工资\t\t职位\t\t奖金\t\t股票\n");
            for (int i = 0; i < team.length; i++) {
                System.out.println(team[i].getDetailsForTeam());
            }
        }
        System.out.println("============================");

    }

    private void addMember() {
//        System.out.println("添加团队成员");
        System.out.println("==========添加团队成员==========");

        System.out.println("输入要添加的员工ID:");
        int id = TSUtility.readInt();

        try {
            Employee emp = listSvc.getEmployee(id);
            teamSvc.addMember(emp);
            System.out.println("添加成功!");
        } catch (TeamException e) {
            System.out.println("添加失败，原因:" + e.getMessage());
        }
        //按回车键继续
        TSUtility.readReturn();

    }

    private void deleteMember() {
//        System.out.println("删除团队成员");
        System.out.println("==========删除成员==========");
        System.out.println("请输入要删除的TID:");
        int memberId = TSUtility.readInt();

        System.out.println("确认是否删除(Y/N):");
        char isDelete = TSUtility.readConfirmSelection();
        if (isDelete=='N'){
            return;
        }
        try {
            teamSvc.removeMember(memberId);
            System.out.println("删除成功!");
        } catch (TeamException e) {
            System.out.println("删除失败，原因："+e.getMessage());
        }
        TSUtility.readReturn();
    }

    public static void main(String[] args) {
        TeamView view = new TeamView();
        view.enterMainMenu();
    }

}
