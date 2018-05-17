package com.sn.my;

import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.IRichBolt;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.tuple.Tuple;

import java.util.HashMap;
import java.util.Map;

public class Demo4  implements IRichBolt{
    OutputCollector outputCollector;
    Map<String,Integer> counters;
    @Override
    public void prepare(Map map, TopologyContext topologyContext, OutputCollector outputCollector) {
        this.counters = new HashMap<>();
        this.outputCollector = outputCollector;
    }

    @Override
    public void execute(Tuple tuple) {
        String str = tuple.getStringByField("word");
        if(!this.counters.containsKey(str)) {
            this.counters.put(str,1);
        } else
        {
            this.counters.put(str,this.counters.get(str)+1);
        }

        this.outputCollector.ack(tuple);

    }

    @Override
    public void cleanup() {

    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {

    }

    @Override
    public Map<String, Object> getComponentConfiguration() {
        return null;
    }
}
