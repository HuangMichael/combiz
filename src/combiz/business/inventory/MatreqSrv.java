package combiz.business.inventory;
import combiz.system.IBOBaseSrv;
import java.util.List;

public interface MatreqSrv extends IBOBaseSrv
{
//  在发放申请中，选择预留，将预留库存项目插入到发放行，等待确认
	public void geninvuse(List list,Object obj) throws Exception;
//  发放申请中创建预留信息
	public void createinvr(Object obj) throws Exception;
//  确认发放
	public void verify(List list,Object obj) throws Exception;
//	发放过后的库存项目可以退库
	public void returnissue(List list,Object obj) throws Exception;
//	对物料申请退库物料行进行校验
	public void verifyreturn(List list,Object obj) throws Exception;
//	对选中的周转件进行发放
	public void genequse(List list,Object obj) throws Exception;
//	对选中的领用申请中物资进行编码
	public int createitem(List list) throws Exception;
//将所有未标识为已打印的发放记录，标识为已打印。。
	public void hasprint(List list) throws Exception;
}
