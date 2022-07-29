package com.dewen;

import com.alibaba.fastjson.JSONObject;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
// 预热 5 轮，每次 1s
@Warmup(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
// 预热 5 轮，每次 3s
@Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Fork(1)
@Threads(6)
@State(Scope.Benchmark)
public class JsonBenchmark {


    // 指定参数有三个值
    @Param(value = {
            "{\"startDate\":\"2020-04-01 16:00:00\",\"endDate\":\"2020-05-20 13:00:00\",\"flag\":true,\"threads\":5,\"shardingIndex\":0}"
    })
    private String jsonStr;


    @Benchmark
    public JsonTestModel testJackson() {
        return JSONObject.parseObject(jsonStr, JsonTestModel.class);
    }


    public static void main(String[] args) throws RunnerException {
        // 启动基准测试
        Options opt = new OptionsBuilder()
                // 要导入的测试类
                .include(JsonBenchmark.class.getSimpleName())
                // .exclude(JsonBenchmark.class.getSimpleName())
                // .output("/tmp/json_benchmark2.log")
                .build();
        new Runner(opt).run();
    }
}