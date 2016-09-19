package combiz.ui.equipment;

import combiz.domain.corp.Corpaddress;
import combiz.domain.equipment.Eqdowntime;
import combiz.domain.equipment.Equipment;
import combiz.domain.user.Ibsusers;
import combiz.domain.workorder.Workorder;
import combiz.system.ui.CommonListWindow;
import combiz.system.ui.ListWindow;
import combiz.system.ui.RecWindow;

import java.util.Date;

import com.inbasis.zul.Listitem;
import com.inbasis.zul.Messagebox;
/**
 * 
	*
 * @author 陈明锐
 * 09-02-06
 *
 */
public class EqdowntimeList extends CommonListWindow {
	public EqdowntimeList() {
		// TODO Auto-generated constructor stub
		super();
	}

	@Override
	public void onCreate() throws Exception {
		// TODO Auto-generated method stub
		RecWindow recWnd = this.getOwnerWnd();// 获得父窗体对象
		String eqnum =""; //设备编号
		String location=""; //设备位置
		if(recWnd.mainObject instanceof Workorder){
			Workorder workorder = (Workorder) recWnd.mainObject;
			eqnum = workorder.getEqnum();
			location=workorder.getLocation();
		}
		if(recWnd.mainObject instanceof Equipment){
			Equipment equip = (Equipment) recWnd.mainObject;
			eqnum = equip.getEqnum();
			location=equip.getLocation();
		}
		this.setQueryString("eqnum='"+eqnum+"' or location='"+location+"'");
		super.onCreate();
	}

	@Override
	public boolean addNew() throws Exception {
		RecWindow recWnd = this.getOwnerWnd();// 获得父窗体对象
		Eqdowntime eqdowntime = new Eqdowntime();
		if(recWnd.mainObject instanceof Workorder){
			Workorder workorder = (Workorder) recWnd.mainObject;
			eqdowntime.setEqnum(workorder.getEqnum());
			eqdowntime.setWonum(workorder.getWonum());
			eqdowntime.setLocation(workorder.getLocation());
		}
		if(recWnd.mainObject instanceof Equipment){
			Equipment equip = (Equipment)recWnd.mainObject;
			eqdowntime.setEqnum(equip.getEqnum());
			eqdowntime.setLocation(equip.getLocation());
		}
		Ibsusers ibsusers = (Ibsusers) this.getDesktop().getSession()
				.getAttribute("userinfo");
		eqdowntime.setReportby(ibsusers.getLabornum());
		eqdowntime.setReportdate(new Date());
		this.mainObject = eqdowntime;
		return true;
	}

	@Override
	public void remove() throws Exception {
		// TODO Auto-generated method stub
		Messagebox.show("历史记录不能被删除");
	}
	
	public void initRowData(Listitem arg0, Object arg1) throws Exception {
		// TODO Auto-generated method stub
		Eqdowntime eqdowntime = (Eqdowntime)arg1;
		if(eqdowntime.getId()!=null){
			this.setReadonlyObject(arg0, true);
		}
	}
}
