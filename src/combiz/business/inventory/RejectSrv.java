package combiz.business.inventory;

import java.util.List;

import combiz.system.IBOBaseSrv;

public interface RejectSrv  extends IBOBaseSrv
{
//	在库存报废应用程序里将仓库中选中的库存行生成报废明细
	public void createdel(List list,String s) throws Exception;
//	对报废单下的报废明细行进行批准报废。
	public void verify(List list) throws Exception;
//	在资产报损报废应用程序里将从台帐中选择的明细行生成报废明细
	public void genrejectline(List list,String s) throws Exception;
//	领导审批时候，从报废申请中选择不通过审批的资产。
	public void noapproveeq(List list) throws Exception;
//	领导审批时候，修改记录为报废。
	public void noyesapproveeq(List list) throws Exception;	
//	领导审批时候，同意报废资产。
	public void approverej(List list,Object obj) throws Exception;
}
