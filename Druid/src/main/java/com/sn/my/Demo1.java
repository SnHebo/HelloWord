package com.sn.my;

import com.google.common.collect.Lists;
import com.suning.druid.Context;
import com.suning.druid.QueryBuilderFactory;
import com.suning.druid.builder.TopNBuilder;
import com.suning.druid.builder.subBuilder.*;
import com.suning.druid.result.TopNResult;
import com.suning.druid.result.bean.TopNBean;

import java.util.List;

public class Demo1 {
    private static Context context = new Context("platformAccount","ip:port","ak",
            "sk");

    public static void main(String[] args) throws Exception {
        TopNBuilder builder = QueryBuilderFactory.topNBuilder()
                .dataSource(DataSourceBuilder.dataSource("huatuo_gauges"))
                .aggregations(Lists.newArrayList(
                        AggregationBuilder.doubleMaxAggregation("value","value")))
                .postAggregations(Lists.<PostAggregationBuilder>newArrayList(PostAggregationBuilder.arithmeticPostAggregation("plus",
                        "*",Lists.<PostAggregationBuilder>newArrayList(PostAggregationBuilder.fieldAccessPostAggregation("value","value"),
                                PostAggregationBuilder.constantPostAggregation("ratio",100)))))
                .filter(FilterBuilder.andDimFilter(Lists.<FilterBuilder>newArrayList(FilterBuilder.selectorDimFilter("role","executor"),
                        FilterBuilder.selectorDimFilter("name","executor.filesystem.hdfs"))))
                .dimension(DimensionBuilder.dimension("appId"))
                .granularity(GranularityBuilder.dayGranularity())
                .intervals(IntervalBuilder.interval("2017-05-23T00:00:00.000/2017-05-25T00:00:00.000"))
                .metric(TopNMetricSpecBuilder.numericTopNMetricSpec("value"))
                .threshold(5)
                .build();

        TopNResult topNResult = builder.query(context);
        List<TopNBean> beanList = topNResult.get();
        String json = topNResult.getJson();
    }
}
