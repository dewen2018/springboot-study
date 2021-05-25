package com.dewen.controller;

import com.dewen.vo.DataSetSaveVO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/dewen")
public class DewenController {

    @PostMapping("/createDataSet")
    public String createDataSet(@Valid @RequestBody DataSetSaveVO dataSetVO) {
        return "hello,hibernate...";
    }
}
