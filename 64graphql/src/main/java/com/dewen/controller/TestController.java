package com.dewen.controller;

import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author dewen
 * @date 2023/1/3 10:04
 */
@RestController
@RequestMapping("body")
@Api(tags = "TestController")
@Tag(name = "body参数")
public class TestController {
    @Operation(summary = "普通body请求")
    @PostMapping("/body")
    public ResponseEntity<FileResp> body(@RequestBody FileResp fileResp) {
        return ResponseEntity.ok(fileResp);
    }

    @Operation(summary = "普通body请求+Param+Header+Path")
    @Parameters({
            @Parameter(name = "id", description = "文件id", in = ParameterIn.PATH),
            @Parameter(name = "token", description = "请求token", required = true, in = ParameterIn.HEADER),
            @Parameter(name = "name", description = "文件名称", required = true, in = ParameterIn.QUERY)
    })
    @PostMapping("/bodyParamHeaderPath/{id}")
    public ResponseEntity<FileResp> bodyParamHeaderPath(@PathVariable("id") String id,
                                                        @RequestHeader("token") String token,
                                                        @RequestParam("name") String name,
                                                        @RequestBody FileResp fileResp) {
        fileResp.setName(fileResp.getName() + ",receiveName:" + name + ",token:" + token + ",pathID:" + id);
        return ResponseEntity.ok(fileResp);
    }
}

@Data
class FileResp {
    private String name;
}
