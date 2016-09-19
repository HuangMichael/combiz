package combiz.ui.smsg;

import java.text.SimpleDateFormat;
import java.util.Date;

import combiz.domain.smsg.Phrase;
import combiz.system.IBOBaseDao;
import combiz.system.ui.InfWindow;
import combiz.system.ui.MainWindow;

public class PhraseWindow extends MainWindow
implements InfWindow{
	/**
	 * 构造函数，初始化数据
	 * 
	 * 作者：张成林 日期：2007-10-14
	 */
	public PhraseWindow()
	{
		super();
	}

	@Override
	public boolean addNew() throws Exception {
		// TODO Auto-generated method stub
		Phrase phrase = new Phrase();
		phrase.setPhrasenum(this.getAutoNum(this.getMainSrv().getBaseDao(),"Phrase"));
		this.setMainObject(phrase);
		return true;
	}
	/**
	 * 自动生成计划单号
	 * 
	 */
	public String getAutoNum(IBOBaseDao baseDao, String tableName) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String s1 = sdf.format(new Date());
		long maxid = (baseDao.selectLongMaxByHql("select max(cp.id) from " + tableName + " cp") + 1);
		return "CYDY" + s1 + "_" + Long.toString(maxid);
	}
}
