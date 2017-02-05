package org.apache.hadoop.examples.mapreduce.����.��������;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Partitioner;

public class ��������Partition extends Partitioner<CombinationKey, IntWritable> {

	@Override
	public int getPartition(CombinationKey combinationKey, IntWritable value, int numberPartitions) {
		// ����firstKey ���з���
		return combinationKey.getFirstKey().hashCode()&Integer.MAX_VALUE%numberPartitions;
	}

}
