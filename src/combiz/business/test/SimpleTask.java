package combiz.business.test;

import combiz.system.schedultask.Task;

import java.util.Date;
import java.util.Map;

/**
 * @author hp520
 * 任务实例
 */
public class SimpleTask extends Task
{
	public void execute(Map map)
	{
		System.out.println("执行任务调用" + new Date());
	}
	
}
