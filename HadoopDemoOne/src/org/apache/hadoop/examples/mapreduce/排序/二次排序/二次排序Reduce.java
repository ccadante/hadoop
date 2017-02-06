package org.apache.hadoop.examples.mapreduce.����.��������;

import java.io.IOException;
import java.util.List;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class ��������Reduce extends Reducer<CombinationKey, IntWritable, Text, Text>{

	
	@Override
	protected void reduce(CombinationKey combinationKey, Iterable<IntWritable> iter,
			Context context)
			throws IOException, InterruptedException {
		String valueAll="";
		for(IntWritable value:iter){
			valueAll=valueAll+value+",";
		}
		context.write(combinationKey.getFirstKey(), new Text(valueAll));
	}
	

}
