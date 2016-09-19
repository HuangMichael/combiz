package combiz.business.invoice;

import combiz.business.corp.LaborSrv;
import combiz.business.user.IbsgroupusersSrv;
import combiz.domain.contract.Contpay;
import combiz.domain.inventory.Inventory;
import combiz.domain.inventory.Invrectrans;
import combiz.domain.inventory.Invusetrans;
import combiz.domain.invoice.Invoice;
import combiz.domain.invoice.Invoiceline;
import combiz.domain.invoice.Invoicematch;
import combiz.domain.invoice.Invoicetrans;
import combiz.domain.po.Po;
import combiz.domain.po.Poline;
import combiz.system.IBOBaseImpl;
import combiz.system.IBOSrvUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.inbasis.zul.Messagebox;

/**
 * 在该类中写业务处理过程 作者：洪小林 日期：2006-12-17
 * 
 */
public class InvoiceImpl extends IBOBaseImpl implements InvoiceSrv {
	/**
	 * 删除用户组，同时删除用户组关联用户、组对应的授权数据 重写父类的删除方法，删除用户组时注意删除对象
	 */
	@Override
	public void delete(Object obj) throws Exception {
		if (!(obj instanceof Invoice))
			throw new Exception("要删除的对象传递不正确！");

		// 删除本身
		super.delete(obj);

		// 删除关联对象－父类方法
		this.deleteAllChild(obj, "invoiceline");
	}

	/**
	 * 
	 * @TODO 通过传入的list(用户选择的采购单行记录)来生成发票行
	 * @throws Exception
	 * @蒋祖兵 2007-8-27 上午10:14:10
	 */
	public void CreateInvoiceline(List list, Object obj) throws Exception {
		if (!(obj instanceof Invoice))
			throw new Exception("对象传递错误");
		Invoice iv = (Invoice) obj;
		InvoicelineSrv invoicelineSrv;
		invoicelineSrv = (InvoicelineSrv) IBOSrvUtil.getSrv("invoiceline");
		double coun = 0D;
		for (int i = 0; i < list.size(); i++) {
			// 往新建的invoiceline表里新建数据；
			Invoiceline il = new Invoiceline();
			long count = this.getBaseDao().selectLongMaxByHql(
					"select max(il.invoicelinenum) from Invoiceline il where invoicenum='"
					+ iv.getInvoicenum() + "'") + 1;
			Poline pl = (Poline) list.get(i);
			il.setInvoicelinenum(count);
			il.setInvoicenum(iv.getInvoicenum());
			il.setPonum(pl.getPonum());
			il.setPolinenum(pl.getPolinenum());
			il.setVendor(iv.getVendor());
			il.setItemnum(pl.getItemnum());
			il.setDescription(pl.getDescription());
			//il.setSitenum(iv.getSitenum());
			//il.setCorpnum(iv.getCorpnum());

			Double waitinvqty = 0.0D;
			Double invqty = (Double) this.getBaseDao().selectSumByHql(
					"select sum(t.invoiceqty) from Invoiceline t where t.ponum ='"
					+ pl.getPonum() + "' and t.polinenum ='"
					+ pl.getPolinenum() + "'");
			if (invqty == null)
				invqty = 0d;
			Double reqty = (Double) this.getBaseDao().selectSumByHql(
					"select sum(t.quantity) from Invrectrans t where t.ponum = '"
					+ pl.getPonum() + "' and t.polinenum ='"
					+ pl.getPolinenum() + "'");
			if (reqty == null)
				reqty = 0d;
			waitinvqty = reqty - invqty;
			il.setInvoiceqty(waitinvqty);

			if (pl.getUnitcost() == null) {
				il.setUnitcost(0.0);
			} else {
				il.setUnitcost(pl.getUnitcost());
			}

			if (pl.getTaxunitcost() == null) {
				il.setTaxunitcost(0.0);
			} else {
				il.setTaxunitcost(pl.getTaxunitcost());
			}
			il.setTaxlinecost(il.getTaxunitcost() * il.getInvoiceqty());
			il.setLinecost(il.getInvoiceqty() * il.getUnitcost());
			il.setConversion(pl.getConversion());
			il.setTaxcode(pl.getTaxcode());
			il.setTax(pl.getTax());
			il.setTaxrate(pl.getTaxrate());
			il.setEnterby(pl.getEnterby());
			il.setEnterdate(pl.getEnterdate());
			il.setReceiptreqd("是");
			il.setService(pl.getService());
			il.setLoadedcost(pl.getLoadedcost());
			il.setProratetoinv("否");
			il.setProrated(pl.getProrated());
			il.setProratecost(pl.getProratecost());
			il.setState("待校验");
			coun = coun+il.getTaxlinecost();
			invoicelineSrv.save(il);

		}
		if (iv.getBasetotalcost() == null) {
			iv.setBasetotalcost(coun);
		}else{
			iv.setBasetotalcost(coun+iv.getBasetotalcost());
		}
		this.getBaseDao().updateObject(iv);

	}

	/**
	 * @author 李阳 功能：校验发票编制人编制的发票是否超出接收数量，如果超过接收数量，重新编制；
	 * @param list
	 * @param obj
	 * @throws Exception
	 *             2008-1-25下午12:46:00
	 */
	public void verify(List list, Object obj) throws Exception {
		Invoice invoice = (Invoice) obj;
		for (int i = 0; i < list.size(); i++) {
			Invoiceline invline = (Invoiceline) list.get(i);
			Double receiveqty = (Double) this
			.getBaseDao()
			.selectSumByHql(
					"select sum(t.quantity) from Invrectrans t  where t.status = '已检验' and t.ponum = '"
					+ invline.getPonum()
					+ "' and t.polinenum = '"
					+ invline.getPolinenum() + "'");
			Double verifyqty = (Double) this
			.getBaseDao()
			.selectSumByHql(
					"select sum(t.invoiceqty) from Invoiceline t  where t.state= '已校验' and t.ponum = '"
					+ invline.getPonum()
					+ "' and t.polinenum = '"
					+ invline.getPolinenum() + "'");
			if (receiveqty == null)
				receiveqty = 0d;
			if (verifyqty == null)
				verifyqty = 0d;

			Double Passverifyqty = receiveqty - verifyqty;
			if (invline.getInvoiceqty() <= Passverifyqty) {
				invline.setState("已校验");
				super.save(invline);
			} else {
				throw new Exception("发票数量大于接收数量!");

			}

			// List invstatus =
			// this.getBaseDao().findWithQuery(Invoiceline.class, "invoicenum =
			// '" + invline.getInvoicenum() + "' and invoicelinenum = '" +
			// invline.getInvoicenum() +"'");
			int count = this.getBaseDao().selectCountByHql(
					"select count(*)  from Invoiceline t  where t.state = '待校验' and invoicenum = '"
					+ invline.getInvoicenum()
					+ "' and invoicelinenum = '"
					+ invline.getInvoicelinenum() + "'");
			if (count == 0) {
				invoice.setStatus("校验完成");
				super.save(invoice);
			} else {
				invoice.setStatus("部分校验");
				super.save(invoice);

			}

		}
	}

	/*
	 * 功能：经过校验的合同发票，点击批准后，发票变为批准状态，同时往合同管理的合同付款应用程序中里插入数据； 作者：李阳 日期：Oct 23, 2008
	 * 4:53:41 PM
	 */
	public void apprcntinv(List list, Object obj) throws Exception {
		Invoice invoice = (Invoice) obj;
		for (int i = 0; i < list.size(); i++) {
			Invoiceline invline = (Invoiceline) list.get(i);
			Contpay contpay = new Contpay();
			contpay.setCntnum(invoice.getCntnum());
			int ContInt = this.getRowCountByWhere(contpay, "cntnum='"
					+ invoice.getCntnum() + "'");
			contpay.setPayline((long) ContInt + 1);
			contpay.setPaytype(invline.getPaytype());
			contpay.setPayamount(invline.getLinecost());
			contpay.setHaspay("是");
			contpay.setRequestby(this.getUserInfo().getLabornum());
			contpay.setRequestdate(new Date());
			contpay.setPaydate(new Date());
			//contpay.setCorpnum(invline.getCorpnum());
			//contpay.setSitenum(invline.getSitenum());
			this.getBaseDao().saveObject(contpay);
		}
	}

	/**
	 * @author 李阳 功能：经过校验的采购发票，点击批准后，发票变为批准状态，同时往invoicematch里插入数据；
	 * @param list
	 * @param obj
	 * @throws Exception
	 *             2008-1-25下午02:02:34
	 */
	public void approveinvoice(List list, Object obj) throws Exception {
		Invoice invoice = (Invoice) obj;
		for (int i = 0; i < list.size(); i++) {
			Invoiceline invline = (Invoiceline) list.get(i);
			Double invqty = invline.getInvoiceqty();// 取出发票行里的一条记录；

			// Long invrectransid = 0l;
			// System.out.println("ponum = '" + invline.getPonum() + "' and
			// polinenum = '" + invline.getPolinenum() + "' order by transdate
			// asc");
			List invrectrans = this.getBaseDao().findWithQuery(
					Invrectrans.class,
					"ponum = '" + invline.getPonum() + "' and polinenum = '"
					+ invline.getPolinenum() + "'", "transdate asc");
			// + invline.getPolinenum() + "' order by transdate asc");
			// 在接收表里查找该发票行所对应的采购单行在接收表里的接收记录信息；
			ArrayList partmatchlist = new ArrayList();// 存放已经部分匹配invmatch的invrectrans记录；
			ArrayList nomatchlist = new ArrayList();// 存放尚未匹配invmatch的invrectrans记录；
			ArrayList firstmatchlist = new ArrayList();// 将nomatchlist里的能正好匹配发票行的优先匹配；
			ArrayList lastmatchlist = new ArrayList();// 如果nomatchlist里没有正好匹配的，按照接收时间排序后再匹配；
			// Double nomatchqty = invline.getInvoiceqty();
			for (int j = 0; j < invrectrans.size(); j++)// for
				// 循环取出每一次接收记录，在invoicematch表里查询看是否已经匹配过。
			{

				Invrectrans inv = (Invrectrans) invrectrans.get(j);
				{
					List invmat = this.getBaseDao().findWithQuery(
							Invoicematch.class,
							"invrectransid = '" + inv.getId() + "'");

					if (invmat.size() > 0) {
						for (int m = 0; m < invmat.size(); m++) {
							Invoicematch invm = (Invoicematch) invmat.get(m);
							if (inv.getQuantity().equals(invm.getQuantity()))// 判断该接收行是否已经匹配过接收行；
							{
								System.out.println("@@@@@@@@@@@@@@@@@@@");

							} else
								partmatchlist.add(invrectrans.get(j));// 如果部分匹配的话，将这些接收记录查询出来放到
							// partmatchlist里。
						}
					} else {
						nomatchlist.add(invrectrans.get(j));
					}

				}

			}
			// 如果存在接收记录部分匹配发票行的invrectrans记录，则先进行匹配；
			if (partmatchlist.size() > 0) // 判断partmachlist大小是否大于零，如果大于零，说明对该采购单行有没有匹配完的接收记录，继续匹配。
			{
				for (int y = 0; y < partmatchlist.size(); y++) {
					Invrectrans invre2 = (Invrectrans) partmatchlist.get(y);
					Double matchqty = (Double) this.getBaseDao()
					.selectSumByHql(
							"select sum(t.quantity) from Invoicematch t where t.invrectransid='"
							+ invre2.getId() + "'");
					if (matchqty == null)
						matchqty = 0d;
					Double reqty = invre2.getQuantity();// 取出部分已经开发票的接收行中的接收数量
					if ((reqty - matchqty) >= invqty)// 接收记录部分匹配发票行的Invrectrans，判断该接收行未匹配的数量是否小于该发票行的记录，如果小于的话，先把该接收记录进行结算。
					{
						Invoicematch invm = new Invoicematch();
						invm.setPonum(invline.getPonum());
						invm.setInvrectransid(invre2.getId());
						invm.setPolinenum(invline.getPolinenum());
						invm.setTransdate(new Date());
						invm.setTranstype("PURCHORDR");
						invm.setInvoicenum(invline.getInvoicenum());
						invm.setInvoicelinenum(invline.getInvoicelinenum());
						invm.setLinecost(invline.getLinecost());
						invm.setVendor(invline.getVendor());
						invm.setQuantity(invqty);
						invqty = invqty - reqty + matchqty;
						super.save(invm);
					}

				}
			}

			// 再对接收记录完全没有匹配发票行的invrectrans记录进行匹配；
			if (invqty > 0) {
				if (nomatchlist.size() > 0) {
					int a = 0;
					for (int x = 0; x < nomatchlist.size(); x++) {
						Invrectrans invre = (Invrectrans) nomatchlist.get(x);
						Double reqty = invre.getQuantity();
						System.out.println("@@@@@@@@@@@@@@@@@reqty" + reqty
								+ "@@@@@@@@@@@@@@@@@" + invqty);
						if ((reqty - invqty) == 0 && a == 0) {
							firstmatchlist.add(invre);
							a = a + 1;
						} else {
							lastmatchlist.add(invre);
						}
					}
				}
				System.out.println("@@@@@@@@@@@@@firstmatchlist"
						+ firstmatchlist.size()
						+ "@@@@@@@@@@@@@@@@@@@@@@lastmatchlist"
						+ lastmatchlist.size());
				if (firstmatchlist.size() > 0) {
					Invoicematch invm = new Invoicematch();
					Invrectrans invre = (Invrectrans) firstmatchlist.get(0);
					invm.setPonum(invline.getPonum());
					invm.setInvrectransid(invre.getId());
					invm.setPolinenum(invline.getPolinenum());
					invm.setTransdate(new Date());
					invm.setTranstype("PURCHORDR");
					invm.setInvoicenum(invline.getInvoicenum());
					invm.setInvoicelinenum(invline.getInvoicelinenum());
					invm.setLinecost(invline.getLinecost());
					invm.setVendor(invline.getVendor());
					invm.setQuantity(invre.getQuantity());
					super.save(invm);
				} else {
					for (int p = 0; p < lastmatchlist.size(); p++) {
						Invrectrans invre = (Invrectrans) lastmatchlist.get(p);
						Double reqty = invre.getQuantity();

						if (reqty < invqty) {
							Invoicematch invm = new Invoicematch();
							invm.setPonum(invline.getPonum());
							invm.setInvrectransid(invre.getId());
							invm.setPolinenum(invline.getPolinenum());
							invm.setTransdate(new Date());
							invm.setTranstype("PURCHORDR");
							invm.setInvoicenum(invline.getInvoicenum());
							invm.setInvoicelinenum(invline.getInvoicelinenum());
							invm.setLinecost(invline.getLinecost());
							invm.setVendor(invline.getVendor());
							invm.setQuantity(reqty);
							invqty = invqty - reqty;
							super.save(invm);
							if (invqty == 0)
								break;

						} else {
							Invoicematch invm = new Invoicematch();
							invm.setPonum(invline.getPonum());
							invm.setInvrectransid(invre.getId());
							invm.setPolinenum(invline.getPolinenum());
							invm.setTransdate(new Date());
							invm.setTranstype("PURCHORDR");
							invm.setInvoicenum(invline.getInvoicenum());
							invm.setInvoicelinenum(invline.getInvoicelinenum());
							invm.setLinecost(invline.getLinecost());
							invm.setVendor(invline.getVendor());
							invm.setQuantity(invqty);
							super.save(invm);
							break;

						}

					}

				}
			}
			String ponum = invline.getPonum();
			Long polinenum = invline.getPolinenum();
			List polinelist = this.getBaseDao().findWithQuery(
					Poline.class,
					"ponum = '" + ponum + "' and polinenum = '" + polinenum
					+ "'");
			Integer linecount = this.getBaseDao().selectCountByHql(
					"Select count(*) from Poline t where t.ponum = '" + ponum
					+ "'");
			Double invocieqty = (Double) this.getBaseDao().selectSumByHql(
					"select sum(t.invoiceqty) from Invoiceline t where t.ponum='"
					+ ponum + "' and polinenum = '" + polinenum + "'");

			if (polinelist.size() > 0) {
				Poline poline = (Poline) polinelist.get(0);
				if (poline.getOrderqty() - invocieqty == 0) {
					poline.setReceiptscomplete("已结算");
				}

			}
			Integer jiesuancount = this.getBaseDao().selectCountByHql(
					"Select count(*) from Poline t where t.ponum = '" + ponum
					+ "' and t.receiptscomplete = '已结算'");
			List polist = this.getBaseDao().findWithQuery(Po.class,
					"ponum = '" + ponum + "'");
			Po po = (Po) polist.get(0);
			if (linecount - jiesuancount == 0) {
				po.setStatus("关闭");
				super.getBaseDao().updateObject(po);

			}

		}

		invoice.setStatus("已批准");
		super.getBaseDao().updateObject(invoice);

	}
	/**
	 * 方法
	 * 
	 * 作者 李建红
	 * 功能：生成发票差异行
	 * 日期：8 08 , 2009 5:09:59 PM
	 *
	 */
	public void createinvtrans(List list) throws Exception {
		// TODO Auto-generated method stub
		if (list.size()>0) {
			Invoiceline invoiceline =  null;
			for (int i=0;i<list.size();i++) {
				invoiceline = (Invoiceline)list.get(i);
				//得到选择的对象
				//得到发票数量，发票单价，物资编码
				Double invoiceqty = invoiceline.getInvoiceqty();
				Double taxunitcost = invoiceline.getTaxunitcost();
				String itemnum = invoiceline.getItemnum();
				//对应 的采购单，采购单行
				String ponum = invoiceline.getPonum();

				//得到接收单价(收据单价),接收时间,接收数量
				//查询到行对应的接收行
				List invoicematchlist = this.getBaseDao().findWithQuery(Invoicematch.class, "ponum='"+ponum+"' and invoicenum='"+invoiceline.getInvoicenum()+"' and invoicelinenum='"+invoiceline.getInvoicelinenum()+"'");
				if (invoicematchlist.size()>0) {
					Invoicematch invoicematch =(Invoicematch) invoicematchlist.get(0);

					Invrectrans invrectrans = (Invrectrans) this.getBaseDao().findUniqueBy(Invrectrans.class, "id",invoicematch.getInvrectransid());
					Double unitcost = invrectrans.getUnitcost();//收据单价
					Double quantity = invrectrans.getQuantity();//接收数量
					String warehouse = invrectrans.getTowarehouse();//得到接收仓库
					//得到当前库存余量
					Double curbal = (Double)this.getBaseDao().selectSumByHql("select sum(i.curbal) from Invstock i where i.itemnum='"+itemnum+"' and i.warehouse = '"+invrectrans.getTowarehouse()+"'");
					if (curbal == null) {
						curbal = 0D;
					}
					Double avgcost = 0d;
					List inventorylist = this.getBaseDao().findWithQuery(Inventory.class, "itemnum='"+itemnum+"' and warehouse='"+warehouse+"'");
					Inventory inventory = null;
					if (inventorylist.size()>0) {
						inventory = (Inventory) inventorylist.get(0);
						avgcost = inventory.getAvgcost();
						if (avgcost == null) {
							avgcost = 0D;
						}
					}

					//计算出新平均成本
					//判断库存余量是否大于发票数量
					if ((taxunitcost-unitcost)!=0) {//如果发票价格和收据价格存在差异
						Double newavgcost = 0D;//定义新平均成本变量
						if ((curbal - invoiceqty)>=0) {//库存余量是否大于等于发票数量,无需生成发票差异记录
							newavgcost = avgcost + (taxunitcost-unitcost)*invoiceqty/curbal;
							//更新库存平均成本
							inventory.setAvgcost(newavgcost);
							this.getBaseDao().updateObject(inventory);
						}else{//库存余量是否小于发票数量
							newavgcost = avgcost+(taxunitcost-unitcost);
							//更新库存平均成本
							inventory.setAvgcost(newavgcost);
							this.getBaseDao().updateObject(inventory);
							//计算出行差异，并插入记录
							Double difcost = (invoiceqty-curbal)*(taxunitcost-unitcost);
							Invoicetrans invoicetrans = new Invoicetrans();
							invoicetrans.setEnterby(this.getLaborInfo().getLabornum());
							invoicetrans.setInvoicelinenum(invoiceline.getInvoicelinenum());
							invoicetrans.setInvoicenum(invoiceline.getInvoicenum());
							invoicetrans.setLinecost(difcost);
							invoicetrans.setTransdate(new Date());
							invoicetrans.setTranstype(invoiceline.getPaytype());
							invoicetrans.setVendor(invoiceline.getVendor());
							this.getBaseDao().saveObject(invoicetrans);
						}
					}
				}
			}
		}
	}


}


/////////////////////业务方法//////////////////////////////////
