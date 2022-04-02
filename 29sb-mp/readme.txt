现象： 集成druid数据源，使用3.1.0之前版本没问题，升级mp到3.1.1+后，运行时报错:java.sql.SQLFeatureNotSupportedException

原因： mp3.1.1+使用了新版jdbc，LocalDateTime等新日期类型处理方式升级，但druid在1.1.21版本之前不支持






rs.absolute(...)允许你指定结果集的绝对位置
如果没有它，假如你要取第1000条记录的数据，就只能rs.next()1000次了：）
rs.isAfterLast()是一种特殊的状态，表征已到达结果集的结尾，此时你如果调rs.next()就会抛出异常
rs.next();//向后滚动
rs.getRow();//得到当前行号
rs.absolute(n);//光标定位到n行
rs.relative(int n);//相对移动n行
rs.first();//将光标定位到结果集中第一行。
rs.last();//将光标定位到结果集中最后一行。
rs.beforeFirst()//将光标定位到结果集中第一行之前。
rs.afterLast();//将光标定位到结果集中最后一行之后。
rs.moveToInsertRow()；//光标移到插入行
rs.moveToCurrentRow();//光标移回到调用rs.moveToInsertRow()