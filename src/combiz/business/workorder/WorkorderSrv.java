package combiz.business.workorder;

import combiz.domain.workorder.Workorder;
import combiz.system.IBOBaseSrv;

import java.util.List;

public interface WorkorderSrv extends IBOBaseSrv
{
//	删除标准作业计划
	public void deljbplan(Object obj) throws Exception;
//  生成预留
	public void genequse(List list, Object obj) throws Exception; 
//	生成工单发放物料行
	public void geninvuse(List list,Object obj) throws Exception;
//	对工单发放物料行进行校验
	public void verify(List list,Object obj) throws Exception;
//	为工单生成物料预留
	public void createinvr(Object obj) throws Exception;
//	生成标准作业计划
	public void genJobplan(Workorder workorder,String jpnum) throws Exception;
//	拷贝工单，将选中的工单拷贝成为一个新的工单
	public Workorder copywo(Object obj) throws Exception;
//	发放过后的库存项目可以退库
	public void returnissue(List list,Object obj) throws Exception;
//	对工单退库物料行进行校验
	public void verifyreturn(List list,Object obj) throws Exception;
//	在工单编制后，创建标准作业计划.
	public void createjbplan(Object obj,Object obj2) throws Exception;
//  创建标准作业计划物料、工具、人工、承包商等信息。	
	public void createjoplandetail(Workorder workorder,String jpnum) throws Exception;
//  创建故障代码，将当前工单下该故障代码的故障问题、原因代码和故障措施添加到故障代码的模板里。		
	public void createfailcode(Object obj) throws Exception;
}
