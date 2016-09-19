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
 * �ڸ�����дҵ�������
 * ���ߣ���С�� ���ڣ�2006-12-17
 *
 */
public class WohazardImpl extends IBOBaseImpl
implements WohazardSrv {
	/**
	 * ɾ���û��飬ͬʱɾ���û�������û������Ӧ����Ȩ����
	 * ��д�����ɾ��������ɾ���û���ʱע��ɾ������
	 */
	@Override
	public void delete(Object obj) 
	throws Exception 
	{
		if(!(obj instanceof Wohazard))
			throw new Exception("Ҫɾ���Ķ��󴫵ݲ���ȷ��");
		
		//ɾ������
		super.delete(obj);
		//ɾ���������󣭸��෽��
		this.deleteAllChild(obj, "woisotagTable");
		this.deleteAllChild(obj, "wohazprecTable");
	}


	/* @see combiz.system.IBOBaseImpl#save(java.lang.Object)
	 * @author:yuanjq
	 * @description:
	 * @ 2007-7-10 ����10:53:51
	 */
	@Override
	public void save(Object obj) throws Exception
	{
		// TODO �Զ����ɷ������
		if(obj instanceof Wohazard)
		{
			Wohazard wohazard = (Wohazard)obj;
			//���
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

					//���������¼����Ӧ��
					//������Σ��Ԥ����ʩ
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

					//�����İ�ȫ��ǲ��� ͨ��hazardtag�����
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
							//��ȫ������������һ�������в���WOISOLOCKOUT
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
			//����
			else
			{
				super.save(obj);
			}
		} else
		{

		}
	}
/////////////////////ҵ�񷽷�//////////////////////////////////
}
