package com.sn.my;

import org.apache.storm.spout.SpoutOutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.IRichSpout;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Values;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class Demo1 implements IRichSpout {
    /**
     * Map:storm的配置信息
     * TopologyContext:组件id,worker id,worker port
     *SpoutOutputCollector:提供了发射tuple的方法
     * */


    private SpoutOutputCollector collector;
    private ConcurrentHashMap<UUID,Values> ackMap;
    private String[] sentences;

    private int index=0;

    @Override
    public void open(Map map, TopologyContext topologyContext, SpoutOutputCollector spoutOutputCollector) {
        this.collector = spoutOutputCollector;
        this.ackMap = new ConcurrentHashMap<UUID,Values>();
        this.sentences = new String[]{"my dog has fleas",
                "I like cold beverages",
                "the dog ate my homework",
                "don't have a cow man",
                "I don't think I like fleas"};

    }

    @Override
    public void close() {

    }

    @Override
    public void activate() {

    }

    @Override
    public void deactivate() {

    }

    @Override
    public void nextTuple() {
        Values values = new Values(sentences[index]);
        UUID msgId = UUID.randomUUID();
        this.ackMap.put(msgId,values);
        this.collector.emit(values,msgId);
        index++;
        if(index >= sentences.length) {
            index = 0;
        }
    }

    @Override
    public void ack(Object o) {
        this.ackMap.remove(o);
    }

    @Override
    public void fail(Object o) {
        this.collector.emit(this.ackMap.get(o),o);
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {
        outputFieldsDeclarer.declare(new Fields("line"));
    }

    @Override
    public Map<String, Object> getComponentConfiguration() {
        return null;
    }
}
