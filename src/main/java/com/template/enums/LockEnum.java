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
    LOCK_HOST_INFO("report_host_info", new ReentrantLock()),
    /**
     * 任务5
     */
    LOCK_APP_DAILY("report_mobile_app_daily", new ReentrantLock()),
    /**
     * 任务6
     */
    LOCK_ORG_INF("report_org_info", new ReentrantLock()),
    /**
     * 任务7
     */
    LOCK_PLATFORM("report_platform_info", new ReentrantLock()),
    /**
     * 任务8
     */
    LOCK_SERVICE_INFO("report_service_info", new ReentrantLock()),
    /**
     * 任务9
     */
    LOCK_SERVICE_DAILY("report_service_daily", new ReentrantLock()),
    /**
     * 任务10
     */
    LOCK_TERMINAL_INFO("report_terminal_info", new ReentrantLock()),
    /**
     * 任务11
     */
    LOCK_TERMINAL_STATUS("report_terminal_status", new ReentrantLock()),
    /**
     * 任务12
     */
    LOCK_USER_INFO("report_user_info", new ReentrantLock()),
    /**
     * 任务13
     */
    LOCK_USER_DAILY("report_user_app_daily", new ReentrantLock());

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
