package org.apache.hadoop.examples.mapreduce.����;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.partition.InputSampler;
import org.apache.hadoop.mapreduce.lib.partition.InputSampler.RandomSampler;
import org.apache.hadoop.mapreduce.lib.partition.TotalOrderPartitioner;

/**
 * 1) ��Math.min(10, splits.length)��split�������Ƭ���������ȡ������ÿ��splitȡ10000�������ܹ�10����� 
2) 10��������򣬸���reducer������(n)��ȡ�����ƽ����n-1���� 
3) �����n-1����д��partitionFile(_partition.lst����һ��SequenceFile)��key��ȡ������ֵ��nullValue 
4) ��partitionFileд��DistributedCache 
 * @author zhangkaishun
 *
 */
public class ȫ���� {

	public static void main(String[] args) throws Exception{
		Configuration conf=new Configuration();
		Job job=Job.getInstance(conf);
															//ѡ�еĸ���  һ��ѡȡ��������  ����ȡinputSplit����
		RandomSampler<Text, Text> sampler=new RandomSampler<Text,Text>(0.1, 1000, 10);
		job.setPartitionerClass(TotalOrderPartitioner.class);
		//д������ļ�
		InputSampler.writePartitionFile(job, sampler);
		
	}
}
