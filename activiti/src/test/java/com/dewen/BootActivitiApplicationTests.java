package com.dewen;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.zip.ZipInputStream;

import com.dewen.config.SecurityUtil;
import org.activiti.api.process.model.ProcessInstance;
import org.activiti.api.process.model.builders.ProcessPayloadBuilder;
import org.activiti.api.process.runtime.ProcessRuntime;
import org.activiti.api.runtime.shared.query.Page;
import org.activiti.api.runtime.shared.query.Pageable;
import org.activiti.api.task.model.Task;
import org.activiti.api.task.model.builders.TaskPayloadBuilder;
import org.activiti.api.task.runtime.TaskRuntime;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.DeploymentBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * activiti7
 * 主要是对以前项目做了一些封装
 * ProcessRuntime taskRuntime:本质还是以前的各种service;
 * 版本模糊，个版本都有一定的bug。团队实力有待考察。
 * 
 */

@SpringBootTest
public class BootActivitiApplicationTests {

	@Autowired
	private ProcessRuntime processRuntime;

	@Autowired
	private TaskRuntime taskRuntime;

	@Autowired
	private SecurityUtil securityUtil;

	@Autowired
	private RepositoryService repositoryService;

	@Test // 查看流程
	public void deploy() {
		securityUtil.logInAs("salaboy");
		String bpmnName = "MyProcess";
		DeploymentBuilder deploymentBuilder = repositoryService.createDeployment().name("请假流程");
		Deployment deployment = null;
		try {
			deployment = deploymentBuilder.addClasspathResource("processes/" + bpmnName + ".bpmn")
					.addClasspathResource("processes/" + bpmnName + ".png").deploy();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test // 远程外部BPMN
	public void deploy2() {
		securityUtil.logInAs("salaboy");
		try {
			Deployment deployment = null;
			InputStream in = new FileInputStream(new File("C:\\Users\\飞牛\\git\\SpringBoot2_Activiti7\\src\\main\\resources\\processes\\leaveProcess.zip"));
			ZipInputStream zipInputStream = new ZipInputStream(in);
			deployment = repositoryService.createDeployment().name("请假流程2")
					// 指定zip格式的文件完成部署
					.addZipInputStream(zipInputStream).deploy();// 完成部署
			zipInputStream.close();
		} catch (Exception e) {
			// TODO 上线时删除
			e.printStackTrace();
		}
	}

	@Test // 查看流程
	public void contextLoads() {
		securityUtil.logInAs("salaboy");
		Page processDefinitionPage = processRuntime.processDefinitions(Pageable.of(0, 10));
		System.err.println("已部署的流程个数：" + processDefinitionPage.getTotalItems());
		for (Object obj : processDefinitionPage.getContent()) {
			System.err.println("流程定义：" + obj);
		}
	}

	@Test // 启动流程
	public void startInstance() {
		securityUtil.logInAs("salaboy");
		ProcessInstance processInstance = processRuntime
				.start(ProcessPayloadBuilder.start().withProcessDefinitionKey("myProcess_1").build());
		System.err.println("流程实例ID：" + processInstance.getId());
	}

	@Test // 执行流程
	public void testTask() {
		securityUtil.logInAs("salaboy");
		Page<Task> page = taskRuntime.tasks(Pageable.of(0, 10));
		if (page.getTotalItems() > 0) {
			for (Task task : page.getContent()) {
				System.err.println("当前任务有1：" + task);
				// 拾取
				taskRuntime.claim(TaskPayloadBuilder.claim().withTaskId(task.getId()).build());
				// 执行
				taskRuntime.complete(TaskPayloadBuilder.complete().withTaskId(task.getId()).build());
			}
		} else {
			System.err.println("没的任务1");
		}

		page = taskRuntime.tasks(Pageable.of(0, 10));
		if (page.getTotalItems() > 0) {
			for (Task task : page.getContent()) {
				System.err.println("当前任务有2：" + task);
			}
		} else {
			System.err.println("没的任务2");
		}
	}

}
