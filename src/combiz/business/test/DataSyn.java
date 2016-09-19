package combiz.business.test;

import combiz.system.IBSServer;
import combiz.system.schedultask.Task;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
import java.util.Map;

public class DataSyn  extends Task
{
	public void execute(Map map) throws Exception
	{
		System.out.println("["+new Date()+"]上报数据----------------------");
		Connection conn = IBSServer.getIBSServer().getDBConnection();
		Statement stm = conn.createStatement();
		Statement stm2 = conn.createStatement();
		ResultSet rs = stm.executeQuery("select * from ibsapps@stdd2");
		while(rs.next())
		{
			System.out.println("应用：" + rs.getString("app") + "  描述：" + rs.getString("description") );
			stm2.execute("insert into ibsapps2@stdd2(id,app,description) values("+rs.getLong("id")
					+",'"+rs.getString("app")+"','"+rs.getString("description")+"')");
		}
		rs.close();
		stm.close();
		stm2.close();
		conn.close();
	}
	
}
