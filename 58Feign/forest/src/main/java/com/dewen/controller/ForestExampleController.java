package com.dewen.controller;

import com.dewen.client2.Amap;
import com.dewen.client2.Cn12306;
import com.dewen.client2.Gitee;
import com.dewen.model.GiteeBranch;
import com.dewen.model.GiteeReadme;
import com.dtflys.forest.callback.OnError;
import com.dtflys.forest.callback.OnSuccess;
import com.dtflys.forest.config.ForestConfiguration;
import com.dtflys.forest.exceptions.ForestRuntimeException;
import com.dtflys.forest.http.ForestRequest;
import com.dtflys.forest.http.ForestResponse;
import com.thebeastshop.forest.springboot.annotation.EnableForest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicReference;

@RestController
public class ForestExampleController {

    @Autowired
    private ForestConfiguration configuration;

    @Autowired
    private Amap amap;

    @Autowired
    private Gitee gitee;

    @Autowired
    private Cn12306 cn12306;


    @GetMapping("/amap/location")
    public Map amapLocation(@RequestParam BigDecimal longitude, @RequestParam BigDecimal latitude) {
        Map result = amap.getLocation(longitude.toEngineeringString(), latitude.toEngineeringString());
        return result;
    }

    @GetMapping("/gitee")
    public String gitee() {
        String result = gitee.index();
        return result;
    }


    @GetMapping("/gitee/async")
    public String aysncGitee() throws ExecutionException, InterruptedException {
        Future<String> future = gitee.asyncIndex();
        return future.get();
    }

    @GetMapping("/gitee/async2")
    public String aysncGitee2() throws ExecutionException, InterruptedException {
        AtomicReference<String> ref = new AtomicReference<>(new String(""));
        CountDownLatch latch = new CountDownLatch(1);
        gitee.asyncIndex2((result, request, response) -> {
            ref.set(result);
            latch.countDown();
        }, (ex, request, response) -> {
            ref.set(ex.getMessage());
            latch.countDown();
        });
        latch.await();
        return ref.get();
    }


    @GetMapping("/12306")
    public String cn12306() {
        String result = cn12306.index();
        return result;
    }

    @GetMapping("/gitee/branches")
    public List<GiteeBranch> giteeBranches(@RequestParam String accessToken,
                                           @RequestParam String owner,
                                           @RequestParam String repo) {
        List<GiteeBranch> branches = gitee.branches(accessToken, owner, repo);
        return branches;
    }

    @GetMapping("/gitee/readme")
    public GiteeReadme giteeReadme(@RequestParam String accessToken,
                                   @RequestParam String owner,
                                   @RequestParam String repo,
                                   @RequestParam String ref) {
        GiteeReadme readme = gitee.readme(accessToken, owner, repo, ref);
        return readme;
    }


}