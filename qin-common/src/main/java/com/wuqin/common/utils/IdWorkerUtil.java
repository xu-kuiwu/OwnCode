package com.wuqin.common.utils;

public class IdWorkerUtil {
    private final long twepoch = 1420041688888L;
    /** 机器id所占的位数 **/
    private final long workerIdBits = 8L;
    /** 数据标识id所占的位数 **/
    private final long datacenterBits = 2L;
    /** 支持的最大机器id，结果是31（这个移位算法可以很快的计算出几位二进制数所能表示的最大十进制数） **/
    private final long maxWorkerId = -1L ^ (-1L << workerIdBits);
    /** 支持最大数据标识，结果是31 **/
    private final long maxDatacenterId = -1L ^ (-1L << datacenterBits);
    /** 序列在id中占的位数 **/
    private final long sequenceBits = 12L;
    /** 机器ID向左移12位 **/
    private final long workerIdShift = sequenceBits;
    /** 数据表示id 向左移17位（12+5） **/
    private final long datacenterIdShift = sequenceBits + workerIdBits;
    /** 时间戳向左移22位（5+5+12） **/
    private final long timestampLeftShift = sequenceBits + workerIdBits + datacenterBits;
    /** 生成序列的掩码，这里为4095 **/
    private final long sequenceMask = -1L ^ (-1L << sequenceBits);

    //工作机器ID
    private long workerId;
    //数据中心
    private long datacenterId;
    //毫秒内序列
    private long sequence = 0L;
    //上次生成ID的时间戳
    private long lastTimestamp = -1L;

    /**
     * 构造函数
     */
    public IdWorkerUtil(long workerId,long datacenterId){
        if(workerId > maxWorkerId || workerId < 0){
            throw new IllegalArgumentException(String.format("worker Id can't be greater than %d or less than 0",maxWorkerId));
        }
        if(datacenterId > maxDatacenterId || datacenterId < 0){
            throw new IllegalArgumentException(String.format("datacenter Id can't be greater than %d or less than 0",maxDatacenterId));
        }
        this.workerId = workerId;
        this.datacenterId = datacenterId;
    }

    //=========================================Metthods======================================

    /**
     * 获得下一个ID（该方法是线程安全）
     * @return
     */
    public synchronized long nextId(){
        long timestamp = timeGen();
        if(timestamp < lastTimestamp){
            throw new RuntimeException(String.format("Clock moved backwards. Refusing to generate id for %d milliseconds",lastTimestamp-timestamp));
        }

        //如果是同一时间生成的，则进行毫秒内序列
        if(lastTimestamp == timestamp){
            sequence = (sequence + 1) & sequenceMask;
            //毫秒内序列溢出
            if(sequence == 0){
                //阻塞到下一个毫秒，获得新的时间戳
                timestamp = tilNextMillis(lastTimestamp);
            }
        }else {
            sequence = 0L;
        }

        //上次生成ID的时间戳
        lastTimestamp = timestamp;

        return ((timestamp - twepoch) << timestampLeftShift)
                | (datacenterId << datacenterIdShift)
                | (workerId << workerIdShift)
                | sequence;
    }

    //返回以毫秒为单位的当前时间
    protected long timeGen(){
        return System.currentTimeMillis();
    }

    protected long tilNextMillis(long lastTimestamp){
        long timestamp = timeGen();
        while(timestamp < lastTimestamp){
            timestamp = timeGen();
        }
        return timestamp;
    }
}
