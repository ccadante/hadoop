package org.apache.hadoop.examples.mapreduce.����.��������;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

public class ��������ComparatorSort extends WritableComparator {

	@Override
	public int compare(WritableComparable a, WritableComparable b) {
		CombinationKey combinationKeyOne = (CombinationKey) a;
		CombinationKey combinationKeyTwo = (CombinationKey) b;
		// ���firstkey ����ͬ�Ͱ��� firstKey����
		if (!combinationKeyOne.getFirstKey().equals(
				combinationKeyTwo.getFirstKey())) {
			return combinationKeyOne.getFirstKey().compareTo(
					combinationKeyTwo.getFirstKey());
		} else {
			// firstKey ��ͬ�ٰ���secondKey����
			return combinationKeyOne.getSecondKey().get()
					- combinationKeyTwo.getSecondKey().get();
		}
	}
}
