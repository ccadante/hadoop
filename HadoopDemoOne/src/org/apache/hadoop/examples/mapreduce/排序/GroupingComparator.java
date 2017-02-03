package org.apache.hadoop.examples.mapreduce.����;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

/**
 * ���飬��reduceִ��ǰ������ͬkey��value�ŵ�һ��������
 * @author zhangkaishun
 *
 */
public class GroupingComparator extends WritableComparator {

	/**
	 * ����key��ǰ��λ���з���
	 */
	@Override
	public int compare(WritableComparable a, WritableComparable b) {
		return super.compare(a.toString().substring(0, 5), b.toString().substring(0, 5));
	}
}
