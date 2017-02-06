package org.apache.hadoop.examples.mapreduce.����.��������;

import org.apache.hadoop.io.WritableComparator;


/**
 * ������ָ��Щkeyһ���ģ���value��װΪһ������
 * �� 1 2��1 3 ��װΪ1 ��2��3��
 * 
 * 
 * @author hasee
 *
 */
public class ��������GroupSort extends WritableComparator {

	public ��������GroupSort() {
		super(CombinationKey.class,true);
	}
	@Override
	public int compare(Object a, Object b) {
		CombinationKey keyOne = (CombinationKey) a;
		CombinationKey keyTwo = (CombinationKey) b;
		return keyOne.getFirstKey().compareTo(keyTwo.getFirstKey());
	}
}
