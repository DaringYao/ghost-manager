//package com.ghost.flowable.controller;
//
//import jakarta.servlet.http.HttpServletResponse;
//import lombok.extern.slf4j.Slf4j;
//import org.flowable.bpmn.model.BpmnModel;
//import org.flowable.engine.*;
//import org.flowable.engine.runtime.Execution;
//import org.flowable.engine.runtime.ProcessInstance;
//import org.flowable.image.ProcessDiagramGenerator;
//import org.flowable.task.api.Task;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.io.InputStream;
//import java.io.OutputStream;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//@Slf4j
//@RestController
//@RequestMapping("askForLeave")
//public class AskForLeaveFlowableController {
//    private final RuntimeService runtimeService;
//    private final TaskService taskService;
//    private final RepositoryService repositoryService;
//    private final ProcessEngine processEngine;
//
//    public AskForLeaveFlowableController(RuntimeService runtimeService, TaskService taskService, RepositoryService repositoryService, ProcessEngine processEngine) {
//        this.runtimeService = runtimeService;
//        this.taskService = taskService;
//        this.repositoryService = repositoryService;
//        this.processEngine = processEngine;
//    }
//
//
//    /**
//     * 员工提交请假申请
//     *
//     * @param employeeNo 员工工号
//     * @param name       姓名
//     * @param reason     原因
//     * @param days       天数
//     * @return
//     */
//    @GetMapping("employeeSubmit")
//    public String employeeSubmitAskForLeave(
//            @RequestParam(value = "employeeNo") String employeeNo,
//            @RequestParam(value = "name") String name,
//            @RequestParam(value = "reason") String reason,
//            @RequestParam(value = "days") Integer days) {
//        HashMap<String, Object> map = new HashMap<>();
//        /**
//         * 员工编号字段来自于配置文件
//         */
//        map.put("employeeNo", employeeNo);
//        map.put("name", name);
//        map.put("reason", reason);
//        map.put("days", days);
//        /**
//         *      key：配置文件中的下个处理流程id
//         *      value：默认领导工号为002
//         */
//        map.put("leaderNo", "002");
//
//        /**
//         * askForLeave：为开启流程的id  与配置文件中的一致
//         */
//        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("askForLeave", map);
//        log.info("{},提交请假申请，流程id：{}", name, processInstance.getId());
//        return "提交成功，流程id：" + processInstance.getId();
//    }
//
//    /**
//     * 领导审核通过
//     *
//     * @param employeeNo 员工工号
//     * @return
//     */
//    @GetMapping("leaderExaminePass")
//    public String leaderExamine(@RequestParam(value = "employeeNo") String employeeNo) {
//        List<Task> taskList = taskService.createTaskQuery().taskAssignee(employeeNo).orderByTaskId().desc().list();
//        if (null == taskList) {
//            throw new RuntimeException("当前员工没有任何申请");
//        }
//        for (Task task : taskList) {
//            if (task == null) {
//                log.info("任务不存在 ID：{}；", task.getId());
//                continue;
//            }
//            log.info("任务 ID：{}；任务处理人：{}；任务是否挂起：{}", task.getId(), task.getAssignee(), task.isSuspended());
//            Map<String, Object> map = new HashMap<>();
//            /**
//             *      key：配置文件中的下个处理流程id
//             *      value：默认老板工号为001
//             */
//            map.put("bossNo", "001");
//            /**
//             *      key：指定配置文件中的条件判断id
//             *      value：指定配置文件中的审核条件
//             */
//            map.put("outcome", "通过");
//            taskService.complete(task.getId(), map);
//        }
//        return "领导审核通过";
//    }
//
//
//    /**
//     * 老板审核通过
//     *
//     * @param leaderNo 领导工号
//     * @return
//     */
//    @GetMapping("bossExaminePass")
//    public String bossExamine(@RequestParam(value = "leaderNo") String leaderNo) {
//        List<Task> taskList = taskService.createTaskQuery().taskAssignee(leaderNo).orderByTaskId().desc().list();
//        if (null == taskList) {
//            throw new RuntimeException("当前员工没有任何申请");
//        }
//        for (Task task : taskList) {
//            if (task == null) {
//                log.info("任务不存在 ID：{}；", task.getId());
//                continue;
//            }
//            log.info("任务 ID：{}；任务处理人：{}；任务是否挂起：{}", task.getId(), task.getAssignee(), task.isSuspended());
//            Map<String, Object> map = new HashMap<>();
//            /**
//             *     老板是最后的审批人   无需指定下个流程
//             */
////            map.put("boss", "001");
//            /**
//             *      key：指定配置文件中的条件判断id
//             *      value：指定配置文件中的审核条件
//             */
//            map.put("outcome", "通过");
//            taskService.complete(task.getId(), map);
//        }
//        return "领导审核通过";
//    }
//
//    /**
//     * 驳回
//     *
//     * @param employeeNo 员工工号
//     * @return
//     */
//    @GetMapping("reject")
//    public String reject(@RequestParam(value = "employeeNo") String employeeNo) {
//        List<Task> taskList = taskService.createTaskQuery().taskAssignee(employeeNo).orderByTaskId().desc().list();
//        if (null == taskList) {
//            throw new RuntimeException("当前员工没有任何申请");
//        }
//        for (Task task : taskList) {
//            if (task == null) {
//                log.info("任务不存在 ID：{}；", task.getId());
//                continue;
//            }
//            log.info("任务 ID：{}；任务处理人：{}；任务是否挂起：{}", task.getId(), task.getAssignee(), task.isSuspended());
//            Map<String, Object> map = new HashMap<>();
//            /**
//             *      key：指定配置文件中的领导id
//             *      value：指定配置文件中的审核条件
//             */
//            map.put("outcome", "驳回");
//            taskService.complete(task.getId(), map);
//        }
//        return "申请被驳回";
//    }
//
//
//    /**
//     * 生成流程图
//     *
//     * @param processId 任务ID
//     */
//    @GetMapping(value = "processDiagram")
//    public void genProcessDiagram(HttpServletResponse httpServletResponse, String processId) throws Exception {
//        ProcessInstance pi = runtimeService.createProcessInstanceQuery().processInstanceId(processId).singleResult();
//
//        //流程走完的不显示图
//        if (pi == null) {
//            return;
//        }
//        Task task = taskService.createTaskQuery().processInstanceId(pi.getId()).singleResult();
//        //使用流程实例ID，查询正在执行的执行对象表，返回流程实例对象
//        String InstanceId = task.getProcessInstanceId();
//        List<Execution> executions = runtimeService
//                .createExecutionQuery()
//                .processInstanceId(InstanceId)
//                .list();
//
//        //得到正在执行的Activity的Id
//        List<String> activityIds = new ArrayList<>();
//        List<String> flows = new ArrayList<>();
//        for (Execution exe : executions) {
//            List<String> ids = runtimeService.getActiveActivityIds(exe.getId());
//            activityIds.addAll(ids);
//        }
//
//        //获取流程图
//        BpmnModel bpmnModel = repositoryService.getBpmnModel(pi.getProcessDefinitionId());
//        ProcessEngineConfiguration engconf = processEngine.getProcessEngineConfiguration();
//        ProcessDiagramGenerator diagramGenerator = engconf.getProcessDiagramGenerator();
//        InputStream in = diagramGenerator.generateDiagram(bpmnModel, "png", activityIds, flows,
//                engconf.getActivityFontName(), engconf.getLabelFontName(),
//                engconf.getAnnotationFontName(), engconf.getClassLoader(), 1.0, true);
//        OutputStream out = null;
//        byte[] buf = new byte[1024];
//        int legth = 0;
//        try {
//            out = httpServletResponse.getOutputStream();
//            while ((legth = in.read(buf)) != -1) {
//                out.write(buf, 0, legth);
//            }
//        } finally {
//            if (in != null) {
//                in.close();
//            }
//            if (out != null) {
//                out.close();
//            }
//        }
//    }
//}
