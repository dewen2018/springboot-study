package com.dewen.consts;

public interface ReqConst {
    String prefixAuthorization = "Bearer ";
    String Authorization = prefixAuthorization + "t-e55bb5f6dd84fb16cd45f8ebcdda97a2b60ce71b";

    String DEPARTMENTS_LIST = "https://open.feishu.cn/open-apis/contact/v3/departments";

    String USER_LIST = "https://open.feishu.cn/open-apis/contact/v3/users";

    // 多维表格apptoken
    String MULT_TABLE_TASK_APP_TOKEN = "bascnI5QGzT1TM7nQlcv9mM9kPf";
    // 多维表格任务配置表格id
    String MULT_TABLE_TASK_CONFIG_TABLE_ID = "tbld3K8zSHUz9mpa";
    // 多维表格任务表格id
    String MULT_TABLE_TASK_TABLE_ID = "tbld3K8zSHUz9mpa";

    //多维表格列出记录
    String MULI_TABLES_RECORD = "https://open.feishu.cn/open-apis/bitable/v1/apps/%s/tables/%s/records";
}
