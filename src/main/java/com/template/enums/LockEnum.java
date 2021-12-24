package com.template.enums;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 重入锁枚举类
 * @author dufa
 * @date 2020-12-24
 */
public enum LockEnum {
    /**
     * 任务1
     */
    LOCK_APP_INFO("report_app_info", new ReentrantLock()),
    /**
     * 任务2
     */
    LOCK_APP_STATUS("report_app_status", new ReentrantLock()),
    /**
     * 任务3
     */
    LOCK_EVENT_INFO("report_event_info", new ReentrantLock()),
    /**
     * 任务4
     */
    LOCK_HOST_INFO("report_host_info", new ReentrantLock());

    /**
     * 定时任务key
     */
    private final String logName;
    /**
     * 定时任务 执行实现类
     */
    private final ReentrantLock lock;

    LockEnum(String logName, ReentrantLock lock) {
        this.logName = logName;
        this.lock = lock;
    }

    public String getLogName() {
        return logName;
    }

    public ReentrantLock getLock() {
        return lock;
    }

    public static ReentrantLock getReentrantLock(String logName) {
        for (LockEnum lockTaskEnum : values()) {
            if (lockTaskEnum.getLogName().equals(logName)) {
                return lockTaskEnum.getLock();
            }
        }
        return null;
    }
}
