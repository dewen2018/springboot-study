package com.dewen;

import com.influxdb.client.InfluxDBClient;
import com.influxdb.client.InfluxDBClientFactory;
import com.influxdb.client.WriteApiBlocking;
import com.influxdb.client.domain.WritePrecision;
import com.influxdb.client.write.Point;

import java.time.Instant;

public class InfluxDB2Example {
    public static void main(final String[] args) {

        // You can generate an API token from the "API Tokens Tab" in the UI
        String token = "jZzwLs9MhcYCC1xX4zWbIR2T5MM_quQ8rm5awQEdO7Pz4sOl4PJ2I5lgvgMBUzU0WHhhuezBmHtCZa0k7eah3Q==";
        String bucket = "dewen";
        String org = "dewen";

        InfluxDBClient client = InfluxDBClientFactory.create("http://localhost:8086", token.toCharArray());
        String data = "mem,host=host1 used_percent=23.43234543";
        Point point = Point
                .measurement("mem")
                .addTag("host", "host1")
                .addField("used_percent", 23.43234543)
                .time(Instant.now(), WritePrecision.NS);
        WriteApiBlocking writeApi = client.getWriteApiBlocking();
        writeApi.writePoint(bucket, org, point);
    }
}
