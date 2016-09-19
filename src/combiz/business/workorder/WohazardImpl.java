package combiz.business.workorder;

import combiz.domain.stdplan.Hazard;
import combiz.domain.stdplan.Hazardprec;
import combiz.domain.stdplan.Hazardtag;
import combiz.domain.stdplan.Isolockout;
import combiz.domain.stdplan.Isotag;
import combiz.domain.workorder.Wohazard;
import combiz.domain.workorder.Wohazardprec;
import combiz.domain.workorder.Wohazardtag;
import combiz.domain.workorder.Woisolockout;
import combiz.domain.workorder.Woisotag;
import combiz.system.IBOBaseImpl;

import java.util.List;

/**
 * 在该类中写业务处理过程
 * 作者：洪小林 日期：2006-12-17
 *
 */
public class WohazardImpl extends IBOBaseImpl
implements WohazardSrv {
	/**
	 * 删除用户组，同时删除用户组关联用户、组对应的授权数据
	 * 重写父类的删除方法，删除用户组时注意删除对象
	 */
	@Override
	public void delete(Object obj) 
	throws Exception 
	{
		if(!(obj instanceof Wohazard))
			throw new Exception("要删除的对象传递不正确！");
		
		//删除本身
		super.delete(obj);
		//删除关联对象－父类方法
		this.deleteAllChild(obj, "woisotagTable");
		this.deleteAllChild(obj, "wohazprecTable");
	}


	/* @see combiz.system.IBOBaseImpl#save(java.lang.Object)
	 * @author:yuanjq
	 * @description:
	 * @ 2007-7-10 上午10:53:51
	 */
	@Override
	public void save(Object obj) throws Exception
	{
		// TODO 自动生成方法存根
		if(obj instanceof Wohazard)
		{
			Wohazard wohazard = (Wohazard)obj;
			//添加
			if(wohazard.getId() == null || wohazard.getId().equals(""))
			{
				List hazards = this.getBaseDao().findWithQuery(Hazard.class, "hazardid='" + wohazard.getHazardid() + "'");
				if (hazards.size() == 1)
				{
//					Hazard hazard = (Hazard)hazards.get(0);
//					wohazard.setDescription(hazard.getDescription());
//					wohazard.setHazmat(hazard.getHazmat());
//					wohazard.setHazprec(hazard.getHazprec());
//					wohazard.setHaztagout(hazard.getHaztagout());
//					wohazard.setHealth(hazard.getHealth());
					super.save(wohazard);

					//保存关联记录到相应表
					//工单的危险预防措施
					List hazardprecs = this.getBaseDao().findWithQuery(Hazardprec.class, "hazardid='" + wohazard.getHazardid() + "'");
					for (int i = 0; i < hazardprecs.size(); i++)
					{
						Hazardprec hazardprec =(Hazardprec)hazardprecs.get(i);
						Wohazardprec wohazardprec =new Wohazardprec();
						wohazardprec.setWonum(wohazard.getWonum());
						wohazardprec.setHazardid(wohazard.getHazardid());
						wohazardprec.setHazardprec(hazardprec.getHazardprec());
						wohazardprec.setDescription(hazardprec.getDescription());	
						super.save(wohazardprec);
					}

					//工单的安全标记步骤 通过hazardtag表关联
					List hazardtags = this.getBaseDao().findWithQuery(Hazardtag.class, "hazardid='" + wohazard.getHazardid() + "'");
					for (int i = 0; i < hazardtags.size(); i++)
					{
						Hazardtag hazardtag = (Hazardtag)hazardtags.get(i);
						List isotags = this.getBaseDao().findWithQuery(Isotag.class, "isotagid='" + hazardtag.getIsotagid() + "'");
						if(isotags.size() == 1)
						{
							Isotag isotag =(Isotag)isotags.get(0);
							Woisotag woisotag =new Woisotag();
							woisotag.setWonum(wohazard.getWonum());
							woisotag.setHazardid(wohazard.getHazardid());
							woisotag.setIsotagid(isotag.getIsotagid());
							woisotag.setDescription(isotag.getDescription());
							woisotag.setAplyseq(isotag.getAplyseq());
							woisotag.setEqnum(isotag.getEqnum());
							woisotag.setLocation(isotag.getLocation());
							woisotag.setState(isotag.getState());
							super.save(woisotag);
							//安全隔离操作步骤的一个可运行步骤WOISOLOCKOUT
							List isolockouts = this.getBaseDao().findWithQuery(Isolockout.class, "isotagid='" + woisotag.getIsotagid() + "'");
							for (int j = 0; j < isolockouts.size(); j++)
							{
								Isolockout isolockout =(Isolockout)isolockouts.get(j);
								Woisolockout woisolockout =new Woisolockout();
								woisolockout.setWonum(wohazard.getWonum());
								woisolockout.setIsotagid(isotag.getIsotagid());
								woisolockout.setIsolockid(isolockout.getIsolockid());
								woisolockout.setAplyseq(isolockout.getAplyseq());
								woisolockout.setDescription(isolockout.getDescription());
								woisolockout.setEqdescriptioin(isolockout.getEqdescriptioin());
								woisolockout.setEqnum(isolockout.getEqnum());
								woisolockout.setState(isolockout.getState());
								woisolockout.setLocation(isolockout.getLocation());
								woisolockout.setPerform("");
								super.save(woisolockout);						
							}
						}
					}
				}
			}
			//更新
			else
			{
				super.save(obj);
			}
		} else
		{

		}
	}
/////////////////////业务方法//////////////////////////////////
}
