package com.sn.my;

import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.IRichBolt;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Values;

import java.util.HashMap;
import java.util.Map;

public class Demo2 implements IRichBolt {
    private Map<String,Integer> counters;
    private OutputCollector outputCollector;
    @Override
    public void prepare(Map map, TopologyContext topologyContext, OutputCollector outputCollector) {
        this.counters = new HashMap<String,Integer>();
        this.outputCollector = outputCollector;
    }

    @Override
    public void execute(Tuple tuple) {
        String sentence = tuple.getStringByField("line");
        String[] words = sentence.split(" ");
        for(String word : words) {
            word = word.trim();
            if(!word.isEmpty()) {
                word = word.toLowerCase();
                this.outputCollector.emit(tuple,new Values(word));
            }
        }
        this.outputCollector.ack(tuple);
    }

    @Override
    public void cleanup() {

    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {
        outputFieldsDeclarer.declare(new Fields("word"));
    }

    @Override
    public Map<String, Object> getComponentConfiguration() {
        return null;
    }
}
