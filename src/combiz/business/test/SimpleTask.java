package combiz.business.test;

import combiz.system.schedultask.Task;

import java.util.Date;
import java.util.Map;

/**
 * @author hp520
 * ����ʵ��
 */
public class SimpleTask extends Task
{
	public void execute(Map map)
	{
		System.out.println("ִ���������" + new Date());
	}
	
}
