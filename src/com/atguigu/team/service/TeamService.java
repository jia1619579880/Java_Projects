package com.atguigu.team.service;

import com.atguigu.team.domain.Architect;
import com.atguigu.team.domain.Designer;
import com.atguigu.team.domain.Employee;
import com.atguigu.team.domain.Programmer;

/**
 * 关于
 */
public class TeamService {

    private static int counter = 1;//for memberId setVarriable

    private final int MAX_MENBER = 5;//max member of dev_team

    private Programmer[] team = new Programmer[MAX_MENBER];//save dev_team member

    private int total = 0;//record dev_team realize member

    public TeamService() {
    }

    /**
     * get all Employees of dev_team
     *
     * @return
     */
    public Programmer[] getTeam() {
        Programmer[] team = new Programmer[total];
        for (int i = 0; i < team.length; i++) {
            team[i] = this.team[i];
        }
        return team;
    }

    public void addMember(Employee e) throws TeamException {
        //成员已满，无法添加
        if (total >= MAX_MENBER) {
            throw new TeamException("成员已满，无法添加");
        }
        //该成员不是开发人员，无法添加
        if (!(e instanceof Programmer)) {
            throw new TeamException("该成员不是开发人员，无法添加");
        }
        //该员工已在本开发团队中
        if (isExist(e)) {
            throw new TeamException("该员工已在本开发团队中");
        }
        //该员工已是某团队成员

        //该员工正在休假
        Programmer p = (Programmer) e;
        if ("BUSY".equals(p.getStatus().getNAME())) {
            throw new TeamException("该员工已在本开发团队中");
        } else if ("VOCATION".equals(p.getStatus().getNAME())) {
            throw new TeamException("该员工正在休假");
        }
        //团队中之多只能有一名架构师
        //团队中至多只能有两名设计师
        //团队中至多只能有三名程序员

        int numOfArch = 0, numOfDes = 0, numOfPro = 0;
        for (int i = 0; i < total; i++) {
            if (team[i] instanceof Architect) {
                numOfArch++;
            } else if (team[i] instanceof Designer) {
                numOfDes++;
            } else {
                numOfPro++;
            }
        }

        if (p instanceof Architect) {
            if (numOfArch >= 1) {
                throw new TeamException("团队中之多只能有一名架构师");
            }
        } else if (p instanceof Designer) {
            if (numOfDes >= 2) {
                throw new TeamException("团队中至多只能有两名设计师");
            }
        } else {
            if (numOfPro >= 3) {
                throw new TeamException("团队中至多只能有三名程序员");
            }
        }

        //什么测试都通过了,可以开始赋值了
        team[total++] = p;
        p.setStatus(Status.BUSY);
        p.setMemberId(counter++);

    }

    private boolean isExist(Employee e) {
        for (int i = 0; i < total; i++) {
            if (team[i].getId()==e.getId()){
                return true;
            }
        }
        return false;
    }

    public void removeMember(int memberId) throws TeamException {
        //把要删除的memberid的Status改为FREE状态
        int i;
        for (i=0;i<total;i++){
            if (team[i].getMemberId()==memberId){
                team[i].setStatus(Status.FREE);
                break;
            }
        }
        if (i==total){
            throw new TeamException("找不到指定memberId的员工，删除失败");
        }
        int j;
        for (j=i;j<total-1;j++){
            team[j]=team[j+1];
        }

        team[--total]=null;
    }

}
