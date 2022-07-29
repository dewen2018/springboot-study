package com.dewen;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@State(Scope.Benchmark)
@OutputTimeUnit(TimeUnit.SECONDS)
@Threads(Threads.MAX)
public class LinkedListIterationBenchMark {
    private static final int SIZE = 10000;

    private List<String> list = new LinkedList<>();

    @Setup
    public void setUp() {
        for (int i = 0; i < SIZE; i++) {
            list.add(String.valueOf(i));
        }
    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    public void forIndexIterate() {
        for (int i = 0; i < list.size(); i++) {
            list.get(i);
            System.out.print("");
        }
    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    public void forEachIterate() {
        for (String s : list) {
            System.out.print("");
        }
    }


    /**
     * 仅限于IDE中运行
     * 命令行模式 则是 build 然后 java -jar 启动
     * <p>
     * 1. 这是benchmark 启动的入口
     * 2. 这里同时还完成了JMH测试的一些配置工作
     * 3. 默认场景下，JMH会去找寻标注了@Benchmark的方法，可以通过include和exclude两个方法来完成包含以及排除的语义
     */
    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                // 包含语义
                // 可以用方法名，也可以用XXX.class.getSimpleName()
                .include(LinkedListIterationBenchMark.class.getSimpleName())
                // 排除语义
                // .exclude("Pref")
                // 预热10轮
                .warmupIterations(10)
                // 代表正式计量测试做10轮，
                // 而每次都是先执行完预热再执行正式计量，
                // 内容都是调用标注了@Benchmark的代码。
                .measurementIterations(10)
                // forks(3)指的是做3轮测试，
                // 因为一次测试无法有效的代表结果，
                // 所以通过3轮测试较为全面的测试，
                // 而每一轮都是先预热，再正式计量。
                .forks(3)
                .output("D:\\codes\\code2\\springboot-study\\38jmh\\src\\main\\java\\com\\dewen/Benchmark.log")
                .build();

        new Runner(opt).run();
    }
}
