package com.safframework.study.task.web.controller;

import com.safframework.study.task.core.common.ITask;
import com.safframework.study.task.core.impl.ConcurrentTasksExecutor;
import com.safframework.study.task.core.impl.MockTask;
import com.safframework.study.task.web.dto.ApiResponseDTO;
import com.safframework.study.task.web.dto.ErrorResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by tony on 2017/11/19.
 */
@Slf4j
@RestController
@RequestMapping("/tasks")
public class TasksController {

    @GetMapping("/concurrent")
    public ApiResponseDTO checkConcurrent(@RequestParam("task") int[] taskDelaysInSeconds, @RequestParam("threads") int numberOfConcurrentThreads) {
        StopWatch watch = new StopWatch();
        watch.start();

        List<ITask> delayedTasks = IntStream.of(taskDelaysInSeconds).mapToObj(MockTask::new).collect(Collectors.toList());
        new ConcurrentTasksExecutor(numberOfConcurrentThreads, delayedTasks).execute();

        watch.stop();
        return new ApiResponseDTO(watch.getTotalTimeSeconds());
    }

    @GetMapping("/sequential")
    public ApiResponseDTO checkSequential(@RequestParam("task") int[] taskDelaysInSeconds) {
        StopWatch watch = new StopWatch();
        watch.start();

        IntStream.of(taskDelaysInSeconds).mapToObj(MockTask::new).forEach(MockTask::execute);

        watch.stop();
        return new ApiResponseDTO(watch.getTotalTimeSeconds());
    }

    @ExceptionHandler({IllegalArgumentException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponseDTO handleException(IllegalArgumentException e) {
        return new ErrorResponseDTO(e.getMessage());
    }
}
