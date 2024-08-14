package com.ghost.flowable.controller;

import org.flowable.engine.ProcessEngine;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.engine.repository.Deployment;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author yaolsœ
 * @version 1.0
 * @since 2024/8/15 00:42
 */
public class TestController {

    @Autowired
    private ProcessEngine processEngine;
    @Autowired
    RepositoryService repositoryService;
    @Autowired
    RuntimeService runtimeService;
    @Autowired
    TaskService taskService;

    void deployFlow(){
        //流程引擎的配置对象，关联相关数据源
        Deployment deploy = repositoryService.createDeployment()
                //一次部署所有processes文件夹内的流程
                .name("第一次部署")
                .deploy();
        System.out.println("deploy.getId()="+deploy.getId());
    }

    /**
     * 启动流程实例
     * 在流程定义表中动态维护 act_re_procdef
     */
    void startFlow(){
        String processId="ask_for_leave.bpmn20:1:4";
        String processKey="ask_for_leave.bpmn20";
        //1.根据流程定义di启动流程实例
        runtimeService.startProcessInstanceById(processId);
        //2.根据流程定义key启动流程实例
        //runtimeService.startProcessInstanceByKey(processKey);
    }
    /**
     * 任务的审批
     * 需要数据：任务id
     */
    void compeleteTask(){
        taskService.complete("2506");
    }

}
