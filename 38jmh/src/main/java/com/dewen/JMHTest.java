package com.dewen;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.LongAdder;

// 测试完成时间
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
// 预热 1 轮，每次 1s
@Warmup(iterations = 1, time = 1, timeUnit = TimeUnit.SECONDS)
// 测试 5 轮，每次 3s
@Measurement(iterations = 5, time = 5, timeUnit = TimeUnit.SECONDS)
// fork 1 个进程
@Fork(1)
// 并发线程
@Threads(6)
@State(Scope.Benchmark)
public class JMHTest {

    public static void main(String[] args) throws RunnerException {
        // 启动基准测试
        Options opt = new OptionsBuilder()
                // 要导入的测试类
                .include(JMHTest.class.getSimpleName())
                .output("/tmp/json_benchmark.log")
                .build();
        new Runner(opt).run();
    }

    @Benchmark
    public int atomicTest(Blackhole blackhole) throws InterruptedException {
        AtomicInteger atomicInteger = new AtomicInteger();
        for (int i = 0; i < 1024; i++) {
            atomicInteger.addAndGet(1);
        }
        // 为了避免 JIT 忽略未被使用的结果
        return atomicInteger.intValue();
    }

    @Benchmark
    public int longAdderTest(Blackhole blackhole) throws InterruptedException {
        LongAdder longAdder = new LongAdder();
        for (int i = 0; i < 1024; i++) {
            longAdder.add(1);
        }
        return longAdder.intValue();
    }
}