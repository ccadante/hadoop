package org.apache.hadoop.examples.mapreduce.singlerelation;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;


public class RelationMain {

	public static void main(String[] args) throws Exception {
		Job job=Job.getInstance();
		job.setJobName("�������");
		job.setJarByClass(RelationMain.class); // ͨ�������class �ҵ�job��jar��
		job.setMapperClass(RelationMap.class);
		job.setReducerClass(RelationReduce.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(Text.class);
		FileInputFormat.setInputPaths(job, new Path("hdfs://namenode:9000/input/people"));
		FileOutputFormat.setOutputPath(job, new Path("hdfs://namenode:9000/output/outpeople"));
		job.waitForCompletion(true);
	}

}
