package com.safframework.study.task.core.impl;

import com.safframework.study.task.core.common.ITask;
import io.reactivex.Completable;
import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

/**
 * Created by tony on 2017/11/19.
 */
@Slf4j
public class ConcurrentTasksExecutor implements ITask {

    private final int numberOfConcurrentThreads;
    private final Collection<ITask> tasks;

    public ConcurrentTasksExecutor(int numberOfConcurrentThreads, Collection<ITask> tasks) {
//        Preconditions.checkArgument(numberOfConcurrentThreads > 0, "Amount of threads must be higher than zero.");
        this.numberOfConcurrentThreads = numberOfConcurrentThreads;
        this.tasks = tasks;
    }

    public ConcurrentTasksExecutor(int numberOfConcurrentThreads, ITask... tasks) {
        this(numberOfConcurrentThreads, tasks == null ? null : Arrays.asList(tasks));
    }

    /**
     * Executes all tasks concurrent way only if collection of tasks is not empty.
     * Method completes when all of the tasks complete (or one of them fails).
     * If one of the tasks failed the the exception will be rethrown so that it can be handled by mechanism that calls this method.
     */
    @Override
    public void execute() {
        if (isTasksCollectionEmpty()) {
            log.warn("There are no tasks to be executed.");
            return;
        }
        log.debug("Executing #{} tasks concurrent way.", tasks.size());
        Completable.merge(getAsConcurrentTasks()).blockingAwait();
    }

    /**
     * Creates a scheduler that will be used for executing tasks concurrent way.
     * Scheduler will use number of threads defined in {@link #numberOfConcurrentThreads}
     *
     * @return scheduler
     */
    private Scheduler createScheduler() {
        return Schedulers.from(Executors.newFixedThreadPool(numberOfConcurrentThreads));
    }

    /**
     * Converts collection of tasks (except null tasks) to collection of completable actions.
     * Each action will be executed in thread according to the scheduler created with {@link #createScheduler()} method.
     *
     * @return list of completable actions
     */
    private List<Completable> getAsConcurrentTasks() {
        final Scheduler scheduler = createScheduler();

        return tasks.stream()
                .filter(task -> task != null)
                .map(task -> Completable
                        .fromAction(task::execute)
                        .subscribeOn(scheduler))
                .collect(Collectors.toList());
    }

    /**
     * Checks whether tasks collection is empty.
     *
     * @return true if tasks collection is null or empty, false otherwise
     */
    private boolean isTasksCollectionEmpty() {
        return CollectionUtils.isEmpty(tasks);
    }
}
