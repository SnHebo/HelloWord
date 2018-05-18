package com.sn.my

import org.apache.flink.streaming.api.scala.{DataStream, StreamExecutionEnvironment}


object WindowWordCount {
  case class WordWithCount(word: String, count: Long)
  def main(args: Array[String]): Unit = {
    val env: StreamExecutionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment
    val text: DataStream[String] = env.socketTextStream("localhost",9999,'\n')


    val windowCounts = text.flatMap(w => w.split("\\s"))
      .map(w => WordWithCount(w,1))
      .keyBy("word")
      .timeWindow(org.apache.flink.streaming.api.windowing.time.Time.seconds(5),
            org.apache.flink.streaming.api.windowing.time.Time.seconds(1))
      .sum("count")

    windowCounts.print().setParallelism(1)

    env.execute("Socket Window WordCount")




  }

}
