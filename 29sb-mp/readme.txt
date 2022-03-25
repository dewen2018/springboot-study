现象： 集成druid数据源，使用3.1.0之前版本没问题，升级mp到3.1.1+后，运行时报错:java.sql.SQLFeatureNotSupportedException

原因： mp3.1.1+使用了新版jdbc，LocalDateTime等新日期类型处理方式升级，但druid在1.1.21版本之前不支持