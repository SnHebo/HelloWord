package com.sn.my;

import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.StormSubmitter;
import org.apache.storm.generated.AlreadyAliveException;
import org.apache.storm.generated.AuthorizationException;
import org.apache.storm.generated.InvalidTopologyException;
import org.apache.storm.topology.TopologyBuilder;
import org.apache.storm.tuple.Fields;

public class Demo3 {
    public static void main(String[] args) {
         TopologyBuilder builder = new TopologyBuilder();
         builder.setSpout("word-reader",new Demo1(),1);
         builder.setBolt("word-analyser",new Demo2(),3).shuffleGrouping("word-reader");
         builder.setBolt("word-counter",new Demo4(),3).fieldsGrouping("word-analyser"
         ,new Fields("word"));

        Config config = new Config();
        config.setNumWorkers(2);
        config.setMaxSpoutPending(1000);
        config.setMessageTimeoutSecs(180);

        LocalCluster localCluster = new LocalCluster();
        localCluster.submitTopology("word-count-test",config,builder.createTopology());
//        try {
//            StormSubmitter.submitTopology("word-count-test",config,builder.createTopology());
//        } catch (AlreadyAliveException e) {
//            e.printStackTrace();
//        } catch (InvalidTopologyException e) {
//            e.printStackTrace();
//        } catch (AuthorizationException e) {
//            e.printStackTrace();
//        }
    }
}
