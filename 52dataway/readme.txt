启动命令
java -Dfile.encoding=utf-8 -jar E:\code2\dataway\target\dataway-0.0.1-SNAPSHOT.jar





在@RequestMapping注解中增加produces="application/json;charset=UTF-8"即可
例如：@RequestMapping(value ="/user", produces="application/json;charset=UTF-8")