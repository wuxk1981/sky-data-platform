package com.sky.log.persist.model;

import org.springframework.data.annotation.Id;

/**
 * Created by lh on 2016/11/24.
 */
public class AddLog {
    @Id
    private String id;

    private Long timeMillis;
    private String thread;
    private String level;
    private String loggerName;
    private String message;
    private Boolean endOfBatch;
    private String loggerFqcn;
    private Integer threadId;
    private Integer threadPriority;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getTimeMillis() {
        return timeMillis;
    }

    public void setTimeMillis(Long timeMillis) {
        this.timeMillis = timeMillis;
    }

    public String getThread() {
        return thread;
    }

    public void setThread(String thread) {
        this.thread = thread;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getLoggerName() {
        return loggerName;
    }

    public void setLoggerName(String loggerName) {
        this.loggerName = loggerName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getEndOfBatch() {
        return endOfBatch;
    }

    public void setEndOfBatch(Boolean endOfBatch) {
        this.endOfBatch = endOfBatch;
    }

    public String getLoggerFqcn() {
        return loggerFqcn;
    }

    public void setLoggerFqcn(String loggerFqcn) {
        this.loggerFqcn = loggerFqcn;
    }

    public Integer getThreadId() {
        return threadId;
    }

    public void setThreadId(Integer threadId) {
        this.threadId = threadId;
    }

    public Integer getThreadPriority() {
        return threadPriority;
    }

    public void setThreadPriority(Integer threadPriority) {
        this.threadPriority = threadPriority;
    }

}
