package com.dewen.consts;

public interface ReqConst {
    String prefixAuthorization = "Bearer ";
//    public String Authorization = prefixAuthorization + "t-3d40f2a46ec42c1986e192572f345007c82c1364";

    String APP_ID = "cli_a046d9447b399013";
    String APP_SECRET = "X8qUshSmqYGKOK7UriA1TcSCKWwI1JOH";

    String DEPARTMENTS_LIST = "https://open.feishu.cn/open-apis/contact/v3/departments";

    String USER_LIST = "https://open.feishu.cn/open-apis/contact/v3/users";

    // 多维表格apptoken
    String MULT_TABLE_TASK_APP_TOKEN = "bascnI5QGzT1TM7nQlcv9mM9kPf";
    // 多维表格任务配置表格id
    String MULT_TABLE_TASK_CONFIG_TABLE_ID = "tbld3K8zSHUz9mpa";
    // 多维表格任务表格id
    String MULT_TABLE_TASK_TABLE_ID = "tblyUVcRLE6M98L4";

    //多维表格列出记录
    String MULI_TABLES_RECORD = "https://open.feishu.cn/open-apis/bitable/v1/apps/%s/tables/%s/records";

    // 发送消息
    String SEND_MSG = "https://open.feishu.cn/open-apis/im/v1/messages";

    // 批量发送消息
    String BATCH_SEND_MSG = "https://open.feishu.cn/open-apis/message/v4/batch_send/";

    // 获取tenant_access_token
    String TENANT_ACCESS_TOKEN = "https://open.feishu.cn/open-apis/auth/v3/tenant_access_token/internal/";

    // 列出数据表
    String BITABLE_TABLES = "https://open.feishu.cn/open-apis/bitable/v1/apps/:app_token/tables";
}
