package com.dewen;


import com.influxdb.client.InfluxDBClient;
import com.influxdb.client.QueryApi;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class ProjectnameApplicationTests {
    @Autowired
    private InfluxDBClient influxDBClient;

    @Test
    void contextLoads() {
//        try (WriteApi writeApi = influxDBClient.makeWriteApi()) {
//            InfluxDB2Example.Temperature temperature = new InfluxDB2Example.Temperature();
//            temperature.location = "south";
//            temperature.value = 100D;
//            temperature.time = Instant.now();
//            writeApi.writeMeasurement(WritePrecision.NS, temperature);
//        }

        // Asynchronous query
        String flux = "from(bucket: \"dewen\")" +
//                " |> range(start: 0)" +
                "  |> range(start: 2022-04-20T01:41:40.943Z, stop: 2022-04-20T01:41:47.393Z)" +
                "  |> filter(fn: (r) => r[\"_measurement\"] == \"temperature\")" +
                "  |> filter(fn: (r) => r[\"location\"] == \"south\")";
//        influxDBClient.getQueryApi().query(flux, InfluxDB2Example.Temperature.class, (cancellable, record) -> {
//
//            // process the flux query result record
//            System.out.println(record.time + ": " + record.value);
//
//        }, error -> {
//
//            // error handling while processing result
//            System.out.println("Error occurred: " + error.getMessage());
//
//        }, () -> {
//
//            // on complete
//            System.out.println("Query completed");
//            influxDBClient.close();
//        });
        QueryApi queryApi = influxDBClient.getQueryApi();

        List<InfluxDB2Example.Temperature> temperatures = queryApi.query(flux, InfluxDB2Example.Temperature.class);
        for (InfluxDB2Example.Temperature temperature1 : temperatures) {
            System.out.println(temperature1);
        }
        influxDBClient.close();
    }

}
