// package com.dewen.config;
//
// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;
// import org.springframework.batch.core.*;
// import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
// import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
// import org.springframework.batch.core.configuration.annotation.StepScope;
// import org.springframework.batch.item.database.JdbcCursorItemReader;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.beans.factory.annotation.Value;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.jdbc.core.JdbcTemplate;
// import org.springframework.transaction.annotation.Transactional;
//
// import javax.sql.DataSource;
// import java.util.HashMap;
// import java.util.Map;
// import java.util.UUID;
//
// @Configuration
// public class BatchConfiguration {
//
//     private static final Logger log = LoggerFactory.getLogger(BatchConfiguration.class);
//
//     @Value("${batch-size:5000}")
//     private int batchSize;
//
//     @Autowired
//     public JobBuilderFactory jobBuilderFactory;
//
//     @Autowired
//     public StepBuilderFactory stepBuilderFactory;
//
//     // 数据过滤器，对从数据库读出来的数据，注意进行操作
//     @Autowired
//     public TaskItemProcessor taskItemProcessor;
//
//     // 接收job参数
//     public Map<String, JobParameter> parameters;
//
//     public Object taskId;
//
//     @Autowired
//     private JdbcTemplate jdbcTemplate;
//
//     // 读取数据库操作
//     @Bean
//     @StepScope
//     public JdbcCursorItemReader<DispatchRequest> itemReader(DataSource dataSource) {
//
//         String querySql = " SELECT " +
//                 " e. ID AS taskId, " +
//                 " e.user_id AS userId, " +
//                 " e.timing_startup AS startTime, " +
//                 " u.device_id AS deviceId, " +
//                 " d.app_name AS appName, " +
//                 " d.compose_file AS composeFile, " +
//                 " e.failure_retry AS failureRetry, " +
//                 " e.tetry_times AS retryTimes, " +
//                 " e.device_managered AS deviceManagered " +
//                 " FROM " +
//                 " eiot_upgrade_task e " +
//                 " LEFT JOIN eiot_upgrade_device u ON e. ID = u.upgrade_task_id " +
//                 " LEFT JOIN eiot_app_detail d ON e.app_id = d. ID " +
//                 " WHERE " +
//                 " ( " +
//                 " u.device_upgrade_status = 0 " +
//                 " OR u.device_upgrade_status = 2" +
//                 " )" +
//                 " AND e.tetry_times > u.retry_times " +
//                 " AND e. ID = ?";
//
//         return new JdbcCursorItemReaderBuilder<DispatchRequest>()
//                 .name("itemReader")
//                 .sql(querySql)
//                 .dataSource(dataSource)
//                 .queryArguments(new Object[]{parameters.get("taskId").getValue()})
//                 .rowMapper(new DispatchRequest.DispatchRequestRowMapper())
//                 .build();
//     }
//
//     // 将结果写回数据库
//     @Bean
//     @StepScope
//     public ItemWriter<ProcessResult> itemWriter() {
//         return new ItemWriter<ProcessResult>() {
//
//             private int updateTaskStatus(DispatchRequest dispatchRequest, int status) {
//                 log.info("update taskId: {}, deviceId: {} to status {}", dispatchRequest.getTaskId(), dispatchRequest.getDeviceId(), status);
//
//                 Integer retryTimes = jdbcTemplate.queryForObject(
//                         "select retry_times from eiot_upgrade_device where device_id = ? and upgrade_task_id = ?",
//                         new Object[]{dispatchRequest.getDeviceId(), dispatchRequest.getTaskId()}, Integer.class
//                 );
//                 retryTimes += 1;
//                 int updateCount = jdbcTemplate.update("update eiot_upgrade_device set device_upgrade_status = ?, retry_times = ? " +
//                         "where device_id = ? and upgrade_task_id = ?", status, retryTimes, dispatchRequest.getDeviceId(), dispatchRequest.getTaskId());
//                 if (updateCount <= 0) {
//                     log.warn("no task updated");
//                 } else {
//                     log.info("count of {} task updated", updateCount);
//                 }
//
//                 // 最后一次重试
//                 if (status == STATUS_DISPATCH_FAILED && retryTimes == dispatchRequest.getRetryTimes()) {
//                     log.info("the last retry of {} failed, inc deviceManagered", dispatchRequest.getTaskId());
//                     return 1;
//                 } else {
//                     return 0;
//                 }
//             }
//
//             @Override
//             @Transactional
//             public void write(List<? extends ProcessResult> list) throws Exception {
//                 Map taskMap = jdbcTemplate.queryForMap(
//                         "select device_managered, device_count, task_status from eiot_upgrade_task where id = ?",
//                         list.get(0).getDispatchRequest().getTaskId() // 我们认定一个批量里面，taskId都是一样的
//                 );
//                 int deviceManagered = (int) taskMap.get("device_managered");
//                 Integer deviceCount = (Integer) taskMap.get("device_count");
//                 if (deviceCount == null) {
//                     log.warn("deviceCount of task {} is null", list.get(0).getDispatchRequest().getTaskId());
//                 }
//                 int taskStatus = (int) taskMap.get("task_status");
//                 for (ProcessResult result : list) {
//                     deviceManagered += updateTaskStatus(result.getDispatchRequest(), result.getStatus());
//                 }
//                 if (deviceCount != null && deviceManagered == deviceCount) {
//                     taskStatus = 2; //任务状态 0:待升级，1:升级中，2:已完成
//                 }
//                 jdbcTemplate.update("update eiot_upgrade_task set device_managered = ?, task_status = ? " +
//                         "where id = ?", deviceManagered, taskStatus, list.get(0).getDispatchRequest().getTaskId());
//             }
//         };
//     }
//
//     /**
//      * 定义一个下发更新的 jobdemo
//      *
//      * @return
//      */
//     @Bean
//     public Job updateDeviceJob(Step updateDeviceStep) {
//         return jobBuilderFactory.get(UUID.randomUUID().toString().replace("-", ""))
//                 .listener(new JobListener()) // 设置Job的监听器
//                 .flow(updateDeviceStep)// 执行下发更新的Step
//                 .end()
//                 .build();
//     }
//
//     /**
//      * 定义一个下发更新的 step
//      *
//      * @return
//      */
//     @Bean
//     public Step updateDeviceStep(JdbcCursorItemReader<DispatchRequest> itemReader, ItemWriter<ProcessResult> itemWriter) {
//         return stepBuilderFactory.get(UUID.randomUUID().toString().replace("-", ""))
//                 .<DispatchRequest, ProcessResult>chunk(batchSize)
//                 .reader(itemReader) //根据taskId从数据库读取更新设备信息
//                 .processor(taskItemProcessor) // 每条更新信息，执行下发更新接口
//                 .writer(itemWriter)
//                 .build();
//     }
//
//     // jobdemo 监听器
//     public class JobListener implements JobExecutionListener {
//
//         @Override
//         public void beforeJob(JobExecution jobExecution) {
//             log.info(jobExecution.getJobInstance().getJobName() + " before... ");
//             parameters = jobExecution.getJobParameters().getParameters();
//             taskId = parameters.get("taskId").getValue();
//             log.info("jobdemo param taskId : " + parameters.get("taskId"));
//         }
//
//         @Override
//         public void afterJob(JobExecution jobExecution) {
//
//             log.info(jobExecution.getJobInstance().getJobName() + " after... ");
//             // 当所有job执行完之后，查询设备更新状态，如果有失败，则要定时重新执行job
//             String sql = " SELECT " +
//                     " count(*) " +
//                     " FROM " +
//                     " eiot_upgrade_device d " +
//                     " LEFT JOIN eiot_upgrade_task u ON d.upgrade_task_id = u. ID " +
//                     " WHERE " +
//                     " u. ID = ? " +
//                     " AND d.retry_times < u.tetry_times " +
//                     " AND ( " +
//                     " d.device_upgrade_status = 0 " +
//                     " OR d.device_upgrade_status = 2 " +
//                     " ) ";
//
//             // 获取更新失败的设备个数
//             Integer count = jdbcTemplate.queryForObject(sql, new Object[]{taskId}, Integer.class);
//
//             log.info("update device failure count : " + count);
//
//             // 下面是使用Quartz触发定时任务
//             // 获取任务时间,单位秒
//             // String time = jdbcTemplate.queryForObject(sql, new Object[]{taskId}, Integer.class);
//             // 此处方便测试，应该从数据库中取taskId对应的重试间隔，单位秒
//             Integer millSecond = 10;
//
//             if (count != null && count > 0) {
//                 String jobName = "UpgradeTask_" + taskId;
//                 String reTaskId = taskId.toString();
//                 Map<String, Object> params = new HashMap<>();
//                 params.put("jobName", jobName);
//                 params.put("taskId", reTaskId);
//                 // if (QuartzManager.checkNameNotExist(jobName)) {
//                 //     QuartzManager.scheduleRunOnceJob(jobName, RunOnceJobLogic.class, params, millSecond);
//                 // }
//             }
//
//         }
//     }
// }