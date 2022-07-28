// package com.dewen.config;
//
// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.beans.factory.annotation.Value;
// import org.springframework.jdbc.core.JdbcTemplate;
// import org.springframework.stereotype.Component;
//
// @Component("taskItemProcessor")
// public class TaskItemProcessor {
//
//     public static final int STATUS_DISPATCH_FAILED = 2;
//     public static final int STATUS_DISPATCH_SUCC = 1;
//
//     private static final Logger log = LoggerFactory.getLogger(TaskItemProcessor.class);
//
//     @Value("${upgrade-dispatch-base-url:http://localhost/api/v2/rpc/dispatch/command/}")
//     private String dispatchUrl;
//
//     @Autowired
//     JdbcTemplate jdbcTemplate;
//
//     /**
//      * 在这里，执行 下发更新指令 的操作
//      *
//      * @param dispatchRequest
//      * @return
//      * @throws Exception
//      */
//     public void process(final DispatchRequest dispatchRequest) {
//         // 调用接口，下发指令
//         String url = dispatchUrl + dispatchRequest.getDeviceId() + "/" + dispatchRequest.getUserId();
//
//         log.info("request url:" + url);
//         // RestTemplate restTemplate = new RestTemplate();
//         // HttpHeaders headers = new HttpHeaders();
//         // headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
//         //
//         // MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
//         //
//         // JSONObject jsonOuter = new JSONObject();
//         // JSONObject jsonInner = new JSONObject();
//         // try {
//         //     jsonInner.put("jobId",dispatchRequest.getTaskId());
//         //     jsonInner.put("name",dispatchRequest.getName());
//         //     jsonInner.put("composeFile", Base64Util.bytesToBase64Str(dispatchRequest.getComposeFile()));
//         //     jsonInner.put("policy",new JSONObject().put("startTime",dispatchRequest.getPolicy()));
//         //     jsonInner.put("timestamp",dispatchRequest.getTimestamp());
//         //
//         //     jsonOuter.put("method","updateApp");
//         //     jsonOuter.put("params",jsonInner);
//         // } catch (JSONException e) {
//         //     log.info("JSON convert Exception :" + e);
//         // }catch (IOException e) {
//         //     log.info("Base64Util bytesToBase64Str :" + e);
//         // }
//         //
//         // log.info("request body json :" + jsonOuter);
//         // HttpEntity<String> requestEntity = new HttpEntity<String>(jsonOuter.toString(),headers);
//         // int status;
//         // try {
//         //     ResponseEntity<String> response = restTemplate.postForEntity(url,requestEntity,String.class);
//         //     log.info("response :" + response);
//         //     if (response.getStatusCode() == HttpStatus.OK) {
//         //         status = STATUS_DISPATCH_SUCC;
//         //     } else {
//         //         status = STATUS_DISPATCH_FAILED;
//         //     }
//         //
//         // }catch (Exception e){
//         //     status = STATUS_DISPATCH_FAILED;
//         // }
//
//         return;
//     }
// }