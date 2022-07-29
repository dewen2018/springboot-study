// import com.dewen.JsonBenchmark;
// import org.junit.Test;
// import org.openjdk.jmh.annotations.Mode;
// import org.openjdk.jmh.runner.Runner;
// import org.openjdk.jmh.runner.RunnerException;
// import org.openjdk.jmh.runner.options.Options;
// import org.openjdk.jmh.runner.options.OptionsBuilder;
// import org.openjdk.jmh.runner.options.TimeValue;
//
// import java.util.concurrent.TimeUnit;
//
// public class BenchmarkTest {
//
//     @Test
//     public void test() throws RunnerException {
//         Options options = new OptionsBuilder()
//                 .include(JsonBenchmark.class.getSimpleName())
//                 .exclude("testJackson")
//                 // .forks(1)
//                 .threads(2)
//                 .timeUnit(TimeUnit.NANOSECONDS)
//                 .warmupIterations(5)
//                 .warmupTime(TimeValue.seconds(1))
//                 .measurementIterations(5)
//                 .measurementTime(TimeValue.seconds(1))
//                 .mode(Mode.AverageTime)
//                 // .output("/tmp/json_benchmark.log")
//                 .build();
//         new Runner(options).run();
//     }
//
// }