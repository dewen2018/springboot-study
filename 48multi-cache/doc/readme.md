# Caffeine
（1）缓存在日常开发中启动至关重要的作用，由于是存储在内存中，数据的读取速度是非常快的，能大量减少对数据库的访问，减少数据库的压力。我们把缓存分为两类：

## 分布式缓存，例如Redis：
优点：存储容量更大、可靠性更好、可以在集群间共享
缺点：访问缓存有网络开销
场景：缓存数据量较大、可靠性要求较高、需要在集群间共享
## 进程本地缓存，例如HashMap、GuavaCache：
优点：读取本地内存，没有网络开销，速度更快
缺点：存储容量有限、可靠性较低、无法共享
场景：性能要求较高，缓存数据量较小

## 参考文档：
https://blog.csdn.net/dark159735/article/details/122288931



## @Cacheable
@Cacheable可以标记在一个方法上，也可以标记在一个类上。当标记在一个方法上时表示该方法是支持缓存的，
当标记在一个类上时则表示该类所有的方法都是支持缓存的。对于一个支持缓存的方法，Spring会在其被调用
后将其返回值缓存起来，以保证下次利用同样的参数来执行该方法时可以直接从缓存中获取结果，而不需要再次
执行该方法。Spring在缓存方法的返回值时是以键值对进行缓存的，值就是方法的返回结果，至于键的话，
Spring又支持两种策略，默认策略和自定义策略，这个稍后会进行说明。需要注意的是当一个支持缓存的方法
在对象内部被调用时是不会触发缓存功能的。@Cacheable可以指定三个属性，value、key和condition。

参数	解释	例子

value	缓存的名称，在 spring 配置文件中定义，必须指定至少一个	例如:@Cacheable(value=”mycache”)
key	缓存的key，可以为空，如果指定要按照SpEL表达式编写，如不指定，则按照方法所有参数组合	@Cacheable(value=”testcache”,key=”#userName”)
condition	缓存的条件，可以为空，使用 SpEL 编写，返回 true 或者 false，只有为 true 才进行缓存	@Cacheable(value=”testcache”,condition=”#userName.length()>2”)
```java
// key 是指传入时的参数
@Cacheable(value="users", key="#id")
public Integer find(Integer id) {
    return id;
}
// 表示第一个参数
@Cacheable(value="users", key="#p0")
public Long find(Long id) {
    return id;
}
// 表示User中的id值
@Cacheable(value="users", key="#user.id")
public User find(User user) {
    return user;
}
// 表示第一个参数里的id属性值
@Cacheable(value="users", key="#p0.id")
public User find(User user) {
    return user;
}
```


## condition
除了上面的案例使用方法，还有以下几种：

属性名称	描述	示例
methodName	当前方法名	#root.methodName
method	当前方法	#root.method.name
target	当前被调用的对象	#root.target
targetClass	当前被调用的对象的class	#root.targetClass
args	当前方法参数组成的数组	#root.args[0]
caches	当前被调用的方法使用的Cache	#root.caches[0].name
condition属性指定发生的条件
有的时候我们可能并不希望缓存一个方法所有的返回结果。通过condition属性可以实现这一功能。condition属性默认为空，表示将缓存所有的调用情形。其值是通过SpringEL表达式来指定的，当为true时表示进行缓存处理；当为false时表示不进行缓存处理，即每次调用该方法时该方法都会执行一次。如下示例表示只有当user的id为偶数时才会进行缓存。

// 根据条件判断是否缓存
@Cacheable(value="users", key="#user.id", condition="#user.id%2==0")
public User find(User user) {
return user;
}

## CacheEvict
2. CacheEvict
   @CacheEvict是用来标注在需要清除缓存元素的方法或类上的。当标记在一个类上时表示其中所有的方法的执行都会触发缓存的清除操作。@CacheEvict可以指定的属性有value、key、condition、allEntries和beforeInvocation。其中value、key和condition的语义与@Cacheable对应的属性类似。即value表示清除操作是发生在哪些Cache上的（对应Cache的名称）；key表示需要清除的是哪个key，如未指定则会使用默认策略生成的key；condition表示清除操作发生的条件。下面我们来介绍一下新出现的两个属性allEntries和beforeInvocation。

allEntries属性
allEntries是boolean类型，表示是否需要清除缓存中的所有元素。默认为false，表示不需要。当指定了allEntries为true时，Spring Cache将忽略指定的key。有的时候我们需要Cache一下清除所有的元素，这比一个一个清除元素更有效率。

@CacheEvict(value="user", allEntries=true)
public void delete(Integer id) {
System.out.println(id);
}



beforeInvocation属性
清除操作默认是在对应方法成功执行之后触发的，即方法如果因为抛出异常而未能成功返回时也不会触发清除操作。使用beforeInvocation可以改变触发清除操作的时间，当我们指定该属性值为true时，Spring会在调用该方法之前清除缓存中的指定元素。

@CacheEvict(value="user", beforeInvocation=true)
public void delete(Integer id) {
System.out.println(id);
}



@Caching
@Caching注解可以让我们在一个方法或者类上同时指定多个Spring Cache相关的注解。其拥有三个属性：cacheable、put和evict，分别用于指定@Cacheable、@CachePut和@CacheEvict。

```java
@Caching(
cacheable = @Cacheable("user"),
evict = {
@CacheEvict(value = "user1", key = "#id"),
@CacheEvict(value = "user", allEntries = true)})
    public Integer find(Integer id) {
    return id;
}
```

## 自定义注解
Spring允许我们在配置可缓存的方法时使用自定义的注解，前提是自定义的注解上必须使用对应的注解进行标注。
如我们有如下这么一个使用@Cacheable进行标注的自定义注解


## Caffeine相关知识点
Caffeine常用配置说明：
initialCapacity=[integer]: 初始的缓存空间大小
maximumSize=[long]: 缓存的最大条数
maximumWeight=[long]: 缓存的最大权重
expireAfterAccess=[duration]: 最后一次写入或访问后经过固定时间过期
expireAfterWrite=[duration]: 最后一次写入后经过固定时间过期
refreshAfterWrite=[duration]: 创建缓存或者最近一次更新缓存后经过固定的时间间隔，刷新缓存
注意点：
expireAfterWrite和expireAfterAccess同事存在时，以expireAfterWrite为准
maximumSize和maximumWeight不可以同时使用