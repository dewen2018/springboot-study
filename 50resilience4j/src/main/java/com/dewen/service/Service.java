package com.dewen.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.concurrent.CompletableFuture;

public interface Service {
    String failure();

    String failureWithFallback();

    String success() throws InterruptedException;

    String successException();

    String ignoreException();

    Flux<String> fluxSuccess();

    Flux<String> fluxFailure();

    Flux<String> fluxTimeout();

    Mono<String> monoSuccess() throws InterruptedException;

    Mono<String> monoFailure();

    Mono<String> monoTimeout();

    CompletableFuture<String> futureSuccess() throws InterruptedException;

    CompletableFuture<String> futureFailure();

    CompletableFuture<String> futureTimeout();

}
