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
 * �ڸ�����дҵ������� ���ߣ���С�� ���ڣ�2006-12-17
 * 
 */
public class InvoiceImpl extends IBOBaseImpl implements InvoiceSrv {
	/**
	 * ɾ���û��飬ͬʱɾ���û�������û������Ӧ����Ȩ���� ��д�����ɾ��������ɾ���û���ʱע��ɾ������
	 */
	@Override
	public void delete(Object obj) throws Exception {
		if (!(obj instanceof Invoice))
			throw new Exception("Ҫɾ���Ķ��󴫵ݲ���ȷ��");

		// ɾ������
		super.delete(obj);

		// ɾ���������󣭸��෽��
		this.deleteAllChild(obj, "invoiceline");
	}

	/**
	 * 
	 * @TODO ͨ�������list(�û�ѡ��Ĳɹ����м�¼)�����ɷ�Ʊ��
	 * @throws Exception
	 * @����� 2007-8-27 ����10:14:10
	 */
	public void CreateInvoiceline(List list, Object obj) throws Exception {
		if (!(obj instanceof Invoice))
			throw new Exception("���󴫵ݴ���");
		Invoice iv = (Invoice) obj;
		InvoicelineSrv invoicelineSrv;
		invoicelineSrv = (InvoicelineSrv) IBOSrvUtil.getSrv("invoiceline");
		double coun = 0D;
		for (int i = 0; i < list.size(); i++) {
			// ���½���invoiceline�����½����ݣ�
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
			il.setReceiptreqd("��");
			il.setService(pl.getService());
			il.setLoadedcost(pl.getLoadedcost());
			il.setProratetoinv("��");
			il.setProrated(pl.getProrated());
			il.setProratecost(pl.getProratecost());
			il.setState("��У��");
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
	 * @author ���� ���ܣ�У�鷢Ʊ�����˱��Ƶķ�Ʊ�Ƿ񳬳�������������������������������±��ƣ�
	 * @param list
	 * @param obj
	 * @throws Exception
	 *             2008-1-25����12:46:00
	 */
	public void verify(List list, Object obj) throws Exception {
		Invoice invoice = (Invoice) obj;
		for (int i = 0; i < list.size(); i++) {
			Invoiceline invline = (Invoiceline) list.get(i);
			Double receiveqty = (Double) this
			.getBaseDao()
			.selectSumByHql(
					"select sum(t.quantity) from Invrectrans t  where t.status = '�Ѽ���' and t.ponum = '"
					+ invline.getPonum()
					+ "' and t.polinenum = '"
					+ invline.getPolinenum() + "'");
			Double verifyqty = (Double) this
			.getBaseDao()
			.selectSumByHql(
					"select sum(t.invoiceqty) from Invoiceline t  where t.state= '��У��' and t.ponum = '"
					+ invline.getPonum()
					+ "' and t.polinenum = '"
					+ invline.getPolinenum() + "'");
			if (receiveqty == null)
				receiveqty = 0d;
			if (verifyqty == null)
				verifyqty = 0d;

			Double Passverifyqty = receiveqty - verifyqty;
			if (invline.getInvoiceqty() <= Passverifyqty) {
				invline.setState("��У��");
				super.save(invline);
			} else {
				throw new Exception("��Ʊ�������ڽ�������!");

			}

			// List invstatus =
			// this.getBaseDao().findWithQuery(Invoiceline.class, "invoicenum =
			// '" + invline.getInvoicenum() + "' and invoicelinenum = '" +
			// invline.getInvoicenum() +"'");
			int count = this.getBaseDao().selectCountByHql(
					"select count(*)  from Invoiceline t  where t.state = '��У��' and invoicenum = '"
					+ invline.getInvoicenum()
					+ "' and invoicelinenum = '"
					+ invline.getInvoicelinenum() + "'");
			if (count == 0) {
				invoice.setStatus("У�����");
				super.save(invoice);
			} else {
				invoice.setStatus("����У��");
				super.save(invoice);

			}

		}
	}

	/*
	 * ���ܣ�����У��ĺ�ͬ��Ʊ�������׼�󣬷�Ʊ��Ϊ��׼״̬��ͬʱ����ͬ����ĺ�ͬ����Ӧ�ó�������������ݣ� ���ߣ����� ���ڣ�Oct 23, 2008
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
			contpay.setHaspay("��");
			contpay.setRequestby(this.getUserInfo().getLabornum());
			contpay.setRequestdate(new Date());
			contpay.setPaydate(new Date());
			//contpay.setCorpnum(invline.getCorpnum());
			//contpay.setSitenum(invline.getSitenum());
			this.getBaseDao().saveObject(contpay);
		}
	}

	/**
	 * @author ���� ���ܣ�����У��Ĳɹ���Ʊ�������׼�󣬷�Ʊ��Ϊ��׼״̬��ͬʱ��invoicematch��������ݣ�
	 * @param list
	 * @param obj
	 * @throws Exception
	 *             2008-1-25����02:02:34
	 */
	public void approveinvoice(List list, Object obj) throws Exception {
		Invoice invoice = (Invoice) obj;
		for (int i = 0; i < list.size(); i++) {
			Invoiceline invline = (Invoiceline) list.get(i);
			Double invqty = invline.getInvoiceqty();// ȡ����Ʊ�����һ����¼��

			// Long invrectransid = 0l;
			// System.out.println("ponum = '" + invline.getPonum() + "' and
			// polinenum = '" + invline.getPolinenum() + "' order by transdate
			// asc");
			List invrectrans = this.getBaseDao().findWithQuery(
					Invrectrans.class,
					"ponum = '" + invline.getPonum() + "' and polinenum = '"
					+ invline.getPolinenum() + "'", "transdate asc");
			// + invline.getPolinenum() + "' order by transdate asc");
			// �ڽ��ձ�����Ҹ÷�Ʊ������Ӧ�Ĳɹ������ڽ��ձ���Ľ��ռ�¼��Ϣ��
			ArrayList partmatchlist = new ArrayList();// ����Ѿ�����ƥ��invmatch��invrectrans��¼��
			ArrayList nomatchlist = new ArrayList();// �����δƥ��invmatch��invrectrans��¼��
			ArrayList firstmatchlist = new ArrayList();// ��nomatchlist���������ƥ�䷢Ʊ�е�����ƥ�䣻
			ArrayList lastmatchlist = new ArrayList();// ���nomatchlist��û������ƥ��ģ����ս���ʱ���������ƥ�䣻
			// Double nomatchqty = invline.getInvoiceqty();
			for (int j = 0; j < invrectrans.size(); j++)// for
				// ѭ��ȡ��ÿһ�ν��ռ�¼����invoicematch�����ѯ���Ƿ��Ѿ�ƥ�����
			{

				Invrectrans inv = (Invrectrans) invrectrans.get(j);
				{
					List invmat = this.getBaseDao().findWithQuery(
							Invoicematch.class,
							"invrectransid = '" + inv.getId() + "'");

					if (invmat.size() > 0) {
						for (int m = 0; m < invmat.size(); m++) {
							Invoicematch invm = (Invoicematch) invmat.get(m);
							if (inv.getQuantity().equals(invm.getQuantity()))// �жϸý������Ƿ��Ѿ�ƥ��������У�
							{
								System.out.println("@@@@@@@@@@@@@@@@@@@");

							} else
								partmatchlist.add(invrectrans.get(j));// �������ƥ��Ļ�������Щ���ռ�¼��ѯ�����ŵ�
							// partmatchlist�
						}
					} else {
						nomatchlist.add(invrectrans.get(j));
					}

				}

			}
			// ������ڽ��ռ�¼����ƥ�䷢Ʊ�е�invrectrans��¼�����Ƚ���ƥ�䣻
			if (partmatchlist.size() > 0) // �ж�partmachlist��С�Ƿ�����㣬��������㣬˵���Ըòɹ�������û��ƥ����Ľ��ռ�¼������ƥ�䡣
			{
				for (int y = 0; y < partmatchlist.size(); y++) {
					Invrectrans invre2 = (Invrectrans) partmatchlist.get(y);
					Double matchqty = (Double) this.getBaseDao()
					.selectSumByHql(
							"select sum(t.quantity) from Invoicematch t where t.invrectransid='"
							+ invre2.getId() + "'");
					if (matchqty == null)
						matchqty = 0d;
					Double reqty = invre2.getQuantity();// ȡ�������Ѿ�����Ʊ�Ľ������еĽ�������
					if ((reqty - matchqty) >= invqty)// ���ռ�¼����ƥ�䷢Ʊ�е�Invrectrans���жϸý�����δƥ��������Ƿ�С�ڸ÷�Ʊ�еļ�¼�����С�ڵĻ����ȰѸý��ռ�¼���н��㡣
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

			// �ٶԽ��ռ�¼��ȫû��ƥ�䷢Ʊ�е�invrectrans��¼����ƥ�䣻
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
					poline.setReceiptscomplete("�ѽ���");
				}

			}
			Integer jiesuancount = this.getBaseDao().selectCountByHql(
					"Select count(*) from Poline t where t.ponum = '" + ponum
					+ "' and t.receiptscomplete = '�ѽ���'");
			List polist = this.getBaseDao().findWithQuery(Po.class,
					"ponum = '" + ponum + "'");
			Po po = (Po) polist.get(0);
			if (linecount - jiesuancount == 0) {
				po.setStatus("�ر�");
				super.getBaseDao().updateObject(po);

			}

		}

		invoice.setStatus("����׼");
		super.getBaseDao().updateObject(invoice);

	}
	/**
	 * ����
	 * 
	 * ���� ���
	 * ���ܣ����ɷ�Ʊ������
	 * ���ڣ�8 08 , 2009 5:09:59 PM
	 *
	 */
	public void createinvtrans(List list) throws Exception {
		// TODO Auto-generated method stub
		if (list.size()>0) {
			Invoiceline invoiceline =  null;
			for (int i=0;i<list.size();i++) {
				invoiceline = (Invoiceline)list.get(i);
				//�õ�ѡ��Ķ���
				//�õ���Ʊ��������Ʊ���ۣ����ʱ���
				Double invoiceqty = invoiceline.getInvoiceqty();
				Double taxunitcost = invoiceline.getTaxunitcost();
				String itemnum = invoiceline.getItemnum();
				//��Ӧ �Ĳɹ������ɹ�����
				String ponum = invoiceline.getPonum();

				//�õ����յ���(�վݵ���),����ʱ��,��������
				//��ѯ���ж�Ӧ�Ľ�����
				List invoicematchlist = this.getBaseDao().findWithQuery(Invoicematch.class, "ponum='"+ponum+"' and invoicenum='"+invoiceline.getInvoicenum()+"' and invoicelinenum='"+invoiceline.getInvoicelinenum()+"'");
				if (invoicematchlist.size()>0) {
					Invoicematch invoicematch =(Invoicematch) invoicematchlist.get(0);

					Invrectrans invrectrans = (Invrectrans) this.getBaseDao().findUniqueBy(Invrectrans.class, "id",invoicematch.getInvrectransid());
					Double unitcost = invrectrans.getUnitcost();//�վݵ���
					Double quantity = invrectrans.getQuantity();//��������
					String warehouse = invrectrans.getTowarehouse();//�õ����ղֿ�
					//�õ���ǰ�������
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

					//�������ƽ���ɱ�
					//�жϿ�������Ƿ���ڷ�Ʊ����
					if ((taxunitcost-unitcost)!=0) {//�����Ʊ�۸���վݼ۸���ڲ���
						Double newavgcost = 0D;//������ƽ���ɱ�����
						if ((curbal - invoiceqty)>=0) {//��������Ƿ���ڵ��ڷ�Ʊ����,�������ɷ�Ʊ�����¼
							newavgcost = avgcost + (taxunitcost-unitcost)*invoiceqty/curbal;
							//���¿��ƽ���ɱ�
							inventory.setAvgcost(newavgcost);
							this.getBaseDao().updateObject(inventory);
						}else{//��������Ƿ�С�ڷ�Ʊ����
							newavgcost = avgcost+(taxunitcost-unitcost);
							//���¿��ƽ���ɱ�
							inventory.setAvgcost(newavgcost);
							this.getBaseDao().updateObject(inventory);
							//������в��죬�������¼
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


/////////////////////ҵ�񷽷�//////////////////////////////////
