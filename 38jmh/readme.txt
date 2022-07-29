Java Microbenchmark Harness
基准测试Benchmark是测量、评估软件性能指标的一种测试，对某个特定目标场景的某项性能指标进行定量的和可对比的测试。

注意事项：
    1.idea测试不能使用的debug
    2.使用@BenchmarkMode注解指定测试维度为Mode.AverageTime。
    3.@Measurement测试配置
    4.@Warmup预热参数
    5.@OutputTimeUnit 指定输出的耗时时间单位为秒；如果方法执行耗时为毫秒级别，
    为了便于观察结果，我们可以使用@OutputTimeUnit指定输出的耗时时间单位为毫秒，
    否则使用默认的秒做单位。
    6.@Fork用于指定fork出多少个子进程来执行同一基准测试方法
    7.@Threads注解用于指定使用多少个线程来执行基准测试方法
    8.@Benchmark标记
    9.@State注解指定字段的共享域。我们使用@Threads注解声明创建两个线程来执行基准测试方法，
    假设我们配置@State(Scope.Thread)，那么在不同线程中，gsonParser、jacksonParser这两个字段都是不同的实例。
    10.也可使用非注解
        public class BenchmarkTest{

            @Test
            public void test() throws RunnerException {
                Options options = new OptionsBuilder()
                        .include(JsonBenchmark.class.getSimpleName())
                        .exclude("testJackson")
                        .forks(1)
                        .threads(2)
                        .timeUnit(TimeUnit.NANOSECONDS)
                        .warmupIterations(5)
                        .warmupTime(TimeValue.seconds(1))
                        .measurementIterations(5)
                        .measurementTime(TimeValue.seconds(1))
                        .mode(Mode.AverageTime)
                        .build();
                new Runner(options).run();
            }

        }
官方代码
http://hg.openjdk.java.net/code-tools/jmh/file/tip/jmh-samples/src/main/java/org/openjdk/jmh/samples/