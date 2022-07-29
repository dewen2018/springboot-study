package com.dewen;

public class JsonTestModel {
    private String startDate;

    private String endDate;

    private boolean flag;

    private int threads;

    private int shardingIndex;

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getStartDate() {
        return this.startDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getEndDate() {
        return this.endDate;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public boolean getFlag() {
        return this.flag;
    }

    public void setThreads(int threads) {
        this.threads = threads;
    }

    public int getThreads() {
        return this.threads;
    }

    public void setShardingIndex(int shardingIndex) {
        this.shardingIndex = shardingIndex;
    }

    public int getShardingIndex() {
        return this.shardingIndex;
    }

}