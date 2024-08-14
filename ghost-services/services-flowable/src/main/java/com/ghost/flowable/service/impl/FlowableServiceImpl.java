package com.ghost.flowable.service.impl;

import com.ghost.flowable.factory.FlowServiceFactory;
import com.ghost.flowable.service.FlowableService;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.task.api.Task;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlowableServiceImpl extends FlowServiceFactory implements FlowableService {

    @Override
    public ProcessInstance startProcessInstance(String processDefinitionKey, Object variables) {
        return runtimeService.startProcessInstanceByKey(processDefinitionKey, (String) variables);
    }

    @Override
    public List<ProcessInstance> findProcessDefinitions() {
        return runtimeService.createProcessInstanceQuery().list();
    }

    @Override
    public List<Task> findPersonalTasks(String assignee) {
        return taskService.createTaskQuery().taskAssignee(assignee).list();
    }

    @Override
    public void completeTask(String taskId, Object variables) {
        taskService.complete(taskId);
    }
}