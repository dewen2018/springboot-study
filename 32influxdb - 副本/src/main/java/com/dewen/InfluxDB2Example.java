package com.dewen;

import com.influxdb.annotations.Column;
import com.influxdb.annotations.Measurement;
import com.influxdb.client.InfluxDBClient;
import com.influxdb.client.InfluxDBClientFactory;
import com.influxdb.client.QueryApi;

import java.time.Instant;
import java.util.List;

public class InfluxDB2Example {
    private static String url = "http://localhost:8086";
    // You can generate an API token from the "API Tokens Tab" in the UI
    private static char[] token = "jZzwLs9MhcYCC1xX4zWbIR2T5MM_quQ8rm5awQEdO7Pz4sOl4PJ2I5lgvgMBUzU0WHhhuezBmHtCZa0k7eah3Q==".toCharArray();
    private static String bucket = "dewen";
    private static String org = "dewen";


    public static void main(final String[] args) throws InterruptedException {
//        InfluxDBClient client = InfluxDBClientFactory.create("http://localhost:8086", token);
//        String data = "mem,host=host1 used_percent=23.43234543";
//        Point point = Point.measurement("mem")
//                .addTag("host", "host3")
//                .addField("used_percent", 23.43234543)
////                .time(Instant.now().toEpochMilli(), WritePrecision.MS)
//                .time(Instant.now(), WritePrecision.NS);
//        WriteApiBlocking writeApi = client.getWriteApiBlocking();
//        writeApi.writePoint(bucket, org, point);

        InfluxDBClient influxDBClient = InfluxDBClientFactory.create(url, token, org, bucket);

//        WriteApiBlocking writeApi = influxDBClient.getWriteApiBlocking();

        // 1.Write by Data Point
//        Point point = Point.measurement("temperature")
//                .addTag("location", "west")
//                .addField("value", 55D)
//                .time(Instant.now().toEpochMilli(), WritePrecision.MS);
//
//        writeApi.writePoint(point);

        // 2.Write by LineProtocol
//        writeApi.writeRecord(WritePrecision.NS, "temperature,location=north value=60.0");

        // 3.Write by POJO
//        Temperature temperature;
//        for (int i = 0; i < 100; i++) {
//            temperature = new Temperature();
//            temperature.location = "south";
//            temperature.value = 62D + i;
//            temperature.time = Instant.now();
//            writeApi.writeMeasurement(WritePrecision.NS, temperature);
//            Thread.sleep(500);
//        }


        // Query data
//        String flux = "from(bucket:\"" + bucket + "\") |> range(start: 0)";
//
        QueryApi queryApi = influxDBClient.getQueryApi();
//
//        List<FluxTable> tables = queryApi.query(flux);
//        for (FluxTable fluxTable : tables) {
//            List<FluxRecord> records = fluxTable.getRecords();
//            for (FluxRecord fluxRecord : records) {
//                System.out.println(fluxRecord.getTime() + ": " + fluxRecord.getValueByKey("_value"));
//            }
//        }
        // Query data from measurement
        String flux = "from(bucket: \"dewen\")" +
//                " |> range(start: 0)" +
                "  |> range(start: 2022-04-20T01:41:40.943Z, stop: 2022-04-20T01:41:47.393Z)" +
                "  |> filter(fn: (r) => r[\"_measurement\"] == \"temperature\")" +
                "  |> filter(fn: (r) => r[\"location\"] == \"south\")";
//                "  |> filter(fn: (r) => r[\"_field\"] == \"value\")" +
//                + "  |> aggregateWindow(every: v.windowPeriod, fn: mean, createEmpty: false)"
//                + "  |> yield(name: \"mean\")";

        List<Temperature> temperatures = queryApi.query(flux, Temperature.class);
        for (Temperature temperature1 : temperatures) {
            System.out.println(temperature1);
        }
        influxDBClient.close();
    }

    @Measurement(name = "temperature")
    public static class Temperature {

        @Column(tag = true)
        String location;

        @Column
        Double value;

        @Column(timestamp = true)
        Instant time;

        @Override
        public String toString() {
            return "Temperature{" +
                    "location='" + location + '\'' +
                    ", value=" + value +
                    ", time=" + time +
                    '}';
        }
    }

    /**
     * #示例：取最近24小时，含有owner标签且值为wxm的记录
     * from(bucket: "wxm-influx")
     *   |> range(start: -24h)
     *   |> filter(fn:(r)=>(r.owner=="wxm"))
     *
     *
     * #示例：取最近24小时，含有owner标签且值为wx或含有标签key且值为indoor的记录
     * from(bucket: "wxm-influx")
     *   |> range(start: -24h)
     *   |> filter(fn:(r)=>(r.owner=="wxm" or r.key=="indoor"))
     *
     *
     * #示例：判断value是否包含在set中。
     * fiels = ["wxm","ali"]
     * from(bucket: "wxm-influx")
     *   |> range(start: 2022-03-12T08:14:04Z,stop:2022-03-13T08:19:05Z)
     *   |> filter(fn:(r)=>contains(value:r.owner,set:fiels))
     */
}
