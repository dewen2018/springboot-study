Spring Batch运行的基本单位是一个Job，一个Job就做一件批处理的事情。
一个Job包含很多Step，step就是每个job要执行的单个步骤。

如下图所示，Step里面，会有Tasklet，Tasklet是一个任务单元，它是属于可以重复利用的东西。
然后是Chunk，chunk就是数据块，你需要定义多大的数据量是一个chunk。

Chunk里面就是不断循环的一个流程，读数据，处理数据，然后写数据。Spring Batch会不断的循环这个流程，直到批处理数据完成。

