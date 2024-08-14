package com.ghost.flowable.service;

import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.task.api.Task;

import java.util.List;
import java.util.zip.ZipInputStream;

public interface FlowableService {


    /**
     * 部署流程定义。
     *
     * @param zipInputStream 流程定义的zip文件
     * @return id
     */
    String deployFlow(ZipInputStream zipInputStream);

    /**
     * 启动流程实例。
     *
     * @param processDefinitionKey 流程定义的key
     * @param variables            启动流程时传递的变量
     * @return 返回流程实例
     */
    ProcessInstance startProcessInstance(String processDefinitionKey, Object variables);

    /**
     * 查询流程定义列表。
     *
     * @return 返回流程定义列表
     */
    List<ProcessInstance> findProcessDefinitions();

    /**
     * 查询个人待办任务列表。
     *
     * @param assignee 任务的办理人
     * @return 返回任务列表
     */
    List<Task> findPersonalTasks(String assignee);

    /**
     * 完成任务。
     *
     * @param taskId    任务ID
     * @param variables 完成任务时传递的变量
     */
    void completeTask(String taskId, Object variables);
}