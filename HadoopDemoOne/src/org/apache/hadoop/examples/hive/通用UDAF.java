package org.apache.hadoop.examples.hive;

import org.apache.hadoop.hive.ql.metadata.HiveException;
import org.apache.hadoop.hive.ql.parse.SemanticException;
import org.apache.hadoop.hive.ql.udf.generic.AbstractGenericUDAFResolver;
import org.apache.hadoop.hive.ql.udf.generic.GenericUDAFEvaluator;
import org.apache.hadoop.hive.ql.udf.generic.GenericUDAFParameterInfo;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.PrimitiveObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.PrimitiveObjectInspectorFactory;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.PrimitiveObjectInspectorUtils;
import org.apache.hadoop.hive.serde2.typeinfo.TypeInfo;
import org.apache.hadoop.io.LongWritable;


/**
 * �Զ���UDAFͳ��b�ֶδ���30�ļ�¼���� countbigthan(b,30)ʵ�ִ���
 * @author zhangkaishun
 *
 */
public class ͨ��UDAF extends AbstractGenericUDAFResolver{

	@Override
	public GenericUDAFEvaluator getEvaluator(GenericUDAFParameterInfo info)
			throws SemanticException {
		return super.getEvaluator(info);
	}

	@Override
	public GenericUDAFEvaluator getEvaluator(TypeInfo[] info)
			throws SemanticException {
		return super.getEvaluator(info);
	}

	public static class GenericUDAFCountBigThanEvalutor extends GenericUDAFEvaluator
	{
		private LongWritable result;
		
		private PrimitiveObjectInspector inputOI1;
		private PrimitiveObjectInspector inputOI2;
		
		/**
		 * init ����map��reduce�׶ζ���ִ��
		 * map�׶� parameters������UDAF����Ĳ��������й�
		 * reduce�׶� parameters����Ϊ1
		 */
		@Override
		public ObjectInspector init(Mode m, ObjectInspector[] parameters)
				throws HiveException {
			result=new LongWritable();
			inputOI1=(PrimitiveObjectInspector) parameters[0];
			if(parameters.length>1){
				inputOI2=(PrimitiveObjectInspector) parameters[1];
			}
			//���ս����������
			return PrimitiveObjectInspectorFactory.writableLongObjectInspector;
		}
		@Override
		public AggregationBuffer getNewAggregationBuffer() throws HiveException {
			CountAgg agg=new CountAgg(); //��Ų��־ۺ�ֵ
			reset(agg);
			return agg;
		}

		/**
		 * ������߼�������ҵ����Ҫ���˴�ֻ��map������
		 */
		@Override
		public void iterate(AggregationBuffer aggregationbuffer, Object[] parameters)
				throws HiveException {
			if(parameters==null||parameters.length<2){
				return;
			}
			double base=PrimitiveObjectInspectorUtils.getDouble(parameters[0], inputOI1);
			double tmp=PrimitiveObjectInspectorUtils.getDouble(parameters[1], inputOI2);
			if(base>tmp){
				((CountAgg)aggregationbuffer).count++;
			}
		}

		/**
		 * �ϲ����ֽ�� map�׶Σ�����combiner����reduce��ִ��
		 * parial ����terminatePartial �õ��Ĳ��ֽ��
		 */
		@Override
		public void merge(AggregationBuffer aggregationbuffer, Object obj)
				throws HiveException {
			if(obj!=null){
				long p=PrimitiveObjectInspectorUtils.getLong(obj, inputOI1);
				((CountAgg)aggregationbuffer).count+=p;
			}
				
		}

		@Override
		public void reset(AggregationBuffer aggregationbuffer)
				throws HiveException {
			CountAgg countagg=(CountAgg) aggregationbuffer;
			countagg.count=0;
		}

		
		@Override
		public Object terminate(AggregationBuffer aggregationbuffer)
				throws HiveException {
			result.set(((CountAgg)aggregationbuffer).count);
			return result;
		}

			/**
			 * map �׶η��ز��ֽ��
			 */
		@Override
		public Object terminatePartial(AggregationBuffer aggregationbuffer)
				throws HiveException {
			result.set(((CountAgg)aggregationbuffer).count);
			return result;
		}
		
		public class CountAgg implements AggregationBuffer{
			long count;
		}
		
	}
}
