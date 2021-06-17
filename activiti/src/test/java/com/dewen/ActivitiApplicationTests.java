package com.dewen;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
class ActivitiApplicationTests {
    @Autowired
    private RepositoryService repositoryService;
    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private TaskService taskService;


    @Test
    void contextLoads() {
    }

    /**
     * 部署流程
     */
    @Test
    void prepare() {
//        //获取引擎实例
//        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
//        //得到repositoryService实例
//        RepositoryService repositoryService = processEngine.getRepositoryService();
        //得到部署实例
        Deployment deployment = repositoryService.createDeployment()
                .name("请假流程")
                .addClasspathResource("processes/test.bpmn")
                .addClasspathResource("processes/test_bpmn20.png")
                .deploy();
        System.out.println("部署ID：" + deployment.getId());
        System.out.println("部署名称：" + deployment.getName());
    }

    /**
     * 启动流程实例
     */
    @Test
    void startProcess() {
        String processDefinitionKey = "myProcess_2";
        Map<String, Object> map = new HashMap<>();

        //使用UEL 表达式设置

        // 学生填写申请单Assignee：${student}
        map.put("student", "lucy");

        // 班主任审批 Assignee：${teacher}
        map.put("teacher", "jack");

        ProcessInstance instance = runtimeService.startProcessInstanceByKey(processDefinitionKey, map);
        System.out.println("流程实例ID:" + instance.getId());
        System.out.println("流程定义ID:" + instance.getProcessDefinitionId());
    }

    /**
     * 任务查询
     */
    @Test
    List<String> searchTask() {
        //流程启动后，各各任务的负责人就可以查询自己当前需要处理的任务，查询出来的任务都是该用户的待办任务。
        List<Task> list = taskService.createTaskQuery()
                //流程实例key
                .processDefinitionKey("myProcess_2")
                //查询谁的任务
                //.taskAssignee("")
                .list();
        List<String> idList = new ArrayList<String>();

        for (Task task : list) {
            idList.add(task.getId());
            System.out.println("任务ID:" + task.getId());
            System.out.println("任务名称:" + task.getName());
            System.out.println("任务的创建时间:" + task.getCreateTime());
            System.out.println("任务的办理人:" + task.getAssignee());
            System.out.println("流程实例ID：" + task.getProcessInstanceId());
            System.out.println("执行对象ID:" + task.getExecutionId());
            System.out.println("流程定义ID:" + task.getProcessDefinitionId());
        }
        return idList;
    }

    /**
     * 处理任务
     */
    @Test
    void disposeTask() {
        List<String> list = searchTask();
        for (String id : list) {
            // 任务id
            taskService.complete(id);
            System.out.println("处理任务id：" + id);
        }
    }
}
