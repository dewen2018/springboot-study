1.ENC() 是固定写法.

2.部署时配置salt(盐)值
    java -jar xxx.jar  -Djasypt.encryptor.password=Y6M9fAJQdU7jNp5MW

3.打开/etc/profile文件
    添加export JASYPT_PASSWORD = Y6M9fAJQdU7jNp5MW
    source /etc/profile

4.java -jar -Djasypt.encryptor.password=${JASYPT_PASSWORD} xxx.jar