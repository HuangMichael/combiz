package combiz.ui.inventory;
import java.util.List;

import combiz.business.corp.LaborSrv;
import combiz.business.inventory.InvrectransSrv;
import combiz.business.inventory.WarehouseSrv;
import combiz.domain.inventory.Warehouse;
import combiz.system.IBOSrvUtil;
import combiz.system.ui.SearchWindow;
import combiz.system.ui.common.SBandbox;
import combiz.system.ui.common.SCombobox;
import combiz.system.util.Util;
public class InvrecprotSearch extends SearchWindow {

	@Override
	public void search() throws Exception {
		// TODO Auto-generated method stub
		int sumcount = 0;
		LaborSrv laborSrv;
 		laborSrv = (LaborSrv)IBOSrvUtil.getSrv("labor");
 		InvrectransSrv invusetranssrv = (InvrectransSrv)IBOSrvUtil.getSrv("invrectrans");
		SBandbox sband = (SBandbox) this.getFellow("invrectrans.towarehouse");
		String entersite = sband.getValue();
		if(Util.isNull(entersite))
		{
			throw new Exception("��������'�ֿ�'����Ϊ�գ���ȷ�ϣ�");
		}
 		WarehouseSrv warehousesrv = (WarehouseSrv)IBOSrvUtil.getSrv("warehouse");
 		List warelist = warehousesrv.getBaseDao().findWithQuery(Warehouse.class, "warehouse = '"+entersite+"'");
 		if (warelist.size()<0) {
 			throw new Exception("ѡ���'�ֿ�'�����ڣ���ȷ�ϣ�");
 		}
 		
 		SCombobox syear = (SCombobox) this.getFellow("invrectrans.transdate");
		String soyear = syear.getValue();
		if(Util.isNull(soyear))
		{
			throw new Exception("��������'��ѯ���'����Ϊ�գ���ȷ�ϣ�");
		}
 		
		SCombobox smon = (SCombobox) this.getFellow("invrectrans.actualdate");
		String somon = smon.getValue();
		if(Util.isNull(somon))
		{
			throw new Exception("��������'��ѯ�·�'����Ϊ�գ���ȷ�ϣ�");
		}
 		/*SBandbox sesitenum = (SBandbox) this.getFellow("invreport.sitenum");
		String sitenum = sesitenum.getValue();
		if(Util.isNull(sitenum))
		{
			throw new Exception("��������'�ص�'����Ϊ�գ���ȷ�ϣ�");
		}
		SEDatebox setime = (SEDatebox) this.getFellow("s_invreport.thisdate");
		Date thistime = setime.getValue();
		if(thistime == null)
		{
			throw new Exception("��������'��ѯ�·�'����Ϊ�գ���ȷ�ϣ�");
		}*/
		super.search();
	}
	

}
