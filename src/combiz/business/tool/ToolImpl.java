package combiz.business.tool;

import combiz.domain.tool.Tool;
import combiz.domain.tool.Toolissue;
import combiz.domain.tool.Toolreserve;
import combiz.domain.tool.Tooltrans;
import combiz.system.IBOBaseImpl;

import java.util.Date;
import java.util.List;

import com.inbasis.zul.Messagebox;
/**
 * 在该类中写业务处理过程
 * 作者：洪小林 日期：2006-12-17
 *
 */
public class ToolImpl extends IBOBaseImpl
implements ToolSrv {
	/**
	 * 删除用户组，同时删除用户组关联用户、组对应的授权数据
	 * 重写父类的删除方法，删除用户组时注意删除对象
	 */
	@Override
	public void delete(Object obj) 
	throws Exception 
	{
		if(!(obj instanceof Tool))
			throw new Exception("要删除的对象传递不正确！");		
		//删除本身
		super.delete(obj);
		//删除关联对象－父类方法		
		this.deleteAllChild(obj, "toolTable");
	}	
/////////////////////业务方法//////////////////////////////////	
	/**
	 * @author ljh
	 * @功能：在工具发放应用程序中点中"发放工具"按钮后，触发该事件，往tooltrans表里查数据；
	 * @throws Exception
	 * @2008-03-18下午02:12:39
	 */
	public void geninvuse(List list) throws Exception {
		Toolreserve toolreserve = null;
		for(int i = 0;i<list.size();i++){
		toolreserve =(Toolreserve)list.get(i);
			if (!(toolreserve instanceof Toolreserve))
				throw new Exception("对象传递不正确1!");								
				/***************判断工具是否已经全部发放完毕*******************************/
				String sql = "select sum(trans.toolqty) from Tooltrans trans where trans.sendnum = '"+toolreserve.getId()+"' and trans.transtype='借用'";
				Long setcnt = (Long) this.getBaseDao().selectSumByHql(sql);
				if (setcnt == null)
				{
					setcnt = 0L;
				}
				System.out.println("=发放数量:="+toolreserve.getSendcount());
				if(toolreserve.getSendcount() <0)
				{
					throw new Exception("发放数量不能为负值，请核实！");
				}
				if (toolreserve.getReservedqty() < (toolreserve.getSendcount()))
				{	
					throw new Exception("工具发放数量不能大于预留数量，请核实！");
				}					
				/***************得到统计表中的数据******************/
				List toolissuelist = this.getBaseDao().findWithQuery(Toolissue.class, "toolnum = '"+toolreserve.getToolnum()+"' and reqlabor = '"+toolreserve.getReqlabor()+"'");
				Toolissue toolissue=null;
				if (toolissuelist.size()>0)
				{
					toolissue = (Toolissue) toolissuelist.get(0);
					toolissue.setToolqty(toolissue.getToolqty()+toolreserve.getSendcount());
					this.getBaseDao().updateObject(toolissue);
				}else {
					toolissue = new Toolissue();
					toolissue.setReqlabor(toolreserve.getReqlabor());
					toolissue.setToolnum(toolreserve.getToolnum());
					toolissue.setToolqty(toolreserve.getSendcount());
					this.getBaseDao().saveObject(toolissue);
				}				
				Tooltrans tooltrans = new Tooltrans();				
				List toollist = this.getBaseDao().findWithQuery(Tool.class, "toolnum = '"+toolreserve.getToolnum()+"'");
				Tool tool = (Tool) toollist.get(0);
				
				Long toolqty = tool.getQty();//工具余量
				Long qty = toolreserve.getReservedqty();
				Long count = toolreserve.getSendcount();//输入的工具待发出数量
				
				tooltrans.setLinecost(toolreserve.getToolhrs());
				tooltrans.setToolhrs(toolreserve.getToolhrs());
				tooltrans.setUserdept(toolreserve.getUserdept());
				tooltrans.setEnterby(this.getLaborInfo().getLaborname());
				tooltrans.setEnterdate(new Date());
				tooltrans.setOutside(tool.getOutside());
				tooltrans.setReqlabor(toolreserve.getReqlabor());
				tooltrans.setToolnum(toolreserve.getToolnum());
				if (count == null || count == 0)
				{
					tooltrans.setToolqty(0L);
				}else {
					tooltrans.setToolqty(count);
				}
				tooltrans.setToolrate(tool.getToolrate());
				tooltrans.setTransdate(new Date());
				tooltrans.setTransid(toolreserve.getToolnum());//交易编号
				tooltrans.setTranstype("借用");
				tooltrans.setSendnum(toolreserve.getId());
				tooltrans.setWonum(toolreserve.getReqnum());
				
				super.save(tooltrans);
											
				/*******************更新仓库的工具数量******************************/
				if ((toolqty - count) >= 0) 
				{
					tool.setQty(toolqty - count);
					this.getBaseDao().updateObject(tool);
					
					/*****************更新工具预留表数据********************************************/
					if ((qty - count) > 0)
					{
						toolreserve.setReservedqty(qty - count);
						this.getBaseDao().updateObject(toolreserve);
					}else if ((qty - count)==0){
						this.getBaseDao().deleteObject(toolreserve);
					}else{
						throw new  Exception("工具数量错误，请检查！");
						//return;
					}
					
				}else {
					throw new  Exception("仓库中工具余量不足，请执行采购或等待归还后再借出!");
					//return;
				}		
		Messagebox.show("完成发放!");
		}
	}
	
//	 ///////////////////业务方法////////在借用工具的时候处理的对象//////////////////////////

	public void genreturn(List list) throws Exception {		
		System.out.println(list+"------------------"+list.size());
		Toolissue toolissue = null;
		Long getcount = 0L;
			for(int i = 0;i<list.size();i++){
				toolissue =(Toolissue)list.get(i);
			
			if (!(toolissue instanceof Toolissue))
				throw new  Exception("对象传递不正确2!");	
			//得到用户实际归还的数量
			getcount = toolissue.getSendcount();
			if (getcount==null)
			{
				getcount = 0L;
			}else if (getcount == 0){
				throw new  Exception("归还数量不能为零!");
			}
			}
		
		//得到对应的借用记录统计
		Long setcnt = (Long) this.getBaseDao().selectSumByHql("select sum(t.toolqty) from Tooltrans t where toolnum = '"+toolissue.getToolnum()+"' and reqlabor = '"+toolissue.getReqlabor()+"' and transtype='借用'");
		Long setcount = (Long) this.getBaseDao().selectSumByHql("select sum(t.toolqty) from Tooltrans t where toolnum = '"+toolissue.getToolnum()+"' and reqlabor = '"+toolissue.getReqlabor()+"' and transtype='归还'");
		if (setcnt == null)
		{
			setcnt = 0L;
		}
//		得到对应的归还记录统计
		if (setcount == null)
		{
			setcount = 0L;
		}
		
		List translist = this.getBaseDao().findWithQuery(Tooltrans.class, " toolnum = '"+toolissue.getToolnum()+"' and reqlabor = '"+toolissue.getReqlabor()+"' and transtype='借用'");
		Tooltrans tooltrans = null;
		if (translist.size()>0)
		{
			tooltrans = (Tooltrans) translist.get(0);
		}
		
		Long toolqty = toolissue.getToolqty();//得到统计的需归还数量
		System.out.print("得到统计的需归还数量"+toolqty);

		System.out.print("实际归还数量"+getcount);
		if (getcount == null)
		{
			getcount = 0L;
		}
		if (toolqty == null)
		{
			toolqty = 0L;
		}
		if (setcnt == setcount)
		{
			throw new  Exception("该工具记录已经归还完毕！");
		}else if (setcnt < getcount || toolqty <getcount )
		{
			throw new  Exception("归还数量不能大于借出数量，请核实！");
		}
		
		List toollist = this.getBaseDao().findWithQuery(Tool.class, "toolnum = '"+tooltrans.getToolnum()+"'");
		Tool tool = (Tool) toollist.get(0);
		
		Long count = tool.getQty();	
		Tooltrans trans = new Tooltrans();
			trans.setTransid(tooltrans.getToolnum());
			trans.setToolqty(getcount);
			trans.setEnterby(this.getLaborInfo().getLaborname());
			trans.setEnterdate(new Date());
			trans.setLinecost(tooltrans.getLinecost());
			trans.setOutside(tooltrans.getOutside());
			trans.setReqlabor(tooltrans.getReqlabor());
			//trans.setSendnum(tooltrans.getId());	//不关联借用id
			trans.setToolhrs(tooltrans.getToolhrs());
			trans.setToolnum(tooltrans.getToolnum());
			trans.setToolrate(tooltrans.getToolrate());
			trans.setTransdate(new Date());
			trans.setTransid(tooltrans.getToolnum());
			trans.setTranstype("归还");
			trans.setUserdept(tooltrans.getUserdept());
			trans.setWonum(tooltrans.getWonum());
			
			this.getBaseDao().saveObject(trans);
			
			//更新工具数量
			tool.setQty(count + getcount);
			this.getBaseDao().updateObject(tool);
			
			//更新工具统计表记录
			if ((toolissue.getToolqty() - getcount)==0)
			{
				this.getBaseDao().deleteObject(toolissue);
			}else{
			toolissue.setToolqty(toolissue.getToolqty()- getcount);
			this.getBaseDao().updateObject(toolissue);
			}
	}	
}


