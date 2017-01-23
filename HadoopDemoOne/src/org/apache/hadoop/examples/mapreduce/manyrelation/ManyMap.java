package org.apache.hadoop.examples.mapreduce.manyrelation;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class ManyMap extends Mapper<LongWritable, Text, IntWritable, Text>{

	@Override
	protected void map(LongWritable key, Text value,
			Context context)
			throws IOException, InterruptedException {
		//String[] split=value.toString().replaceAll("\\s", "++").split("++");
		String[] split=value.toString().split("\\t");

			if(split[0].trim().endsWith("factoryname")||split[0].trim().endsWith("addressID")){
				return;
		}
		if(split[0].matches("^[0-9]*$")){//��������ʽ����split[0]ȫ������
			//������address�ļ��е�����
			context.write(new IntWritable(Integer.parseInt(split[0])), new Text("A-"+split[1]));
		}else{
			//������factory�е�����
			context.write(new IntWritable(Integer.parseInt(split[1])), new Text("F-"+split[0]));
		}
			
	}

}
