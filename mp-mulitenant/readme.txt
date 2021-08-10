1.忽略表，该表下所有的方法都不加租户id
2.忽略方法
    方法一：方法名
    方法二：直接在mapper文件方法上添加    @SqlParser(filter = true)