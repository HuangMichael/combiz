package combiz.business.test;

import java.io.StringReader;
import java.util.List;
import java.util.Map;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;

import combiz.domain.equipment.Equipment;
import combiz.system.IBOBaseDao;
import combiz.system.schedultask.Task;
import combiz.util.DateUnit;

public class WsItstdTask extends Task {
	IBOBaseDao baseDao = null;

	@Override
	public void execute(Map map) throws Exception {
		baseDao = (IBOBaseDao) map.get("baseDao");
		System.out.println("新增任务调度已启动~~");

	}

	/**
	 * 根据定义的xml格式，构造xml字符串 作者:陈明锐 日期:Mar 18, 2009
	 * 
	 * @return
	 */
	public String buildStrXml() throws Exception {
		String strXml = "";
		
		List list = baseDao.findWithQuery(Equipment.class, "isupload=0 and group by ");
		for (int i = 0; i < list.size(); i++) {
			Equipment equipment = (Equipment) list.get(i);
			strXml = "<ROOT><HEAD><SG_ID>--消息编号</SG_ID>"
					+ "<SG_TYPE>F001</SG_TYPE>"
					+ "<SG_NAME>IT增加</SG_NAME>"
					+ "<SG_IN_DATE>"+DateUnit.getSimpleFormatToday()+"</SG_IN_DATE>"
					+ "<SG_HEADER>消息头</SG_HEADER>"
					+ "<SG_DO_DATE>"+DateUnit.getSimpleFormatToday()+"</SG_DO_DATE>"
					+ "<SG_MANAGE_DEPART>--设备主管部门</SG_MANAGE_DEPART>"
					+ "<SG_REMARK></SG_REMARK></HEAD>"
					+ "<SG_DATA><T1>"
					+ "<GE_USER>--设备经办人</GE_USER>"
					+ "<GE_NEXT_USER>--资产经办人</GE_NEXT_USER>"
					+ "<GE_LAST_USER>--经费经办人</GE_LAST_USER>"
					+ "<GE_TOTAL_MONEY>---总价</GE_TOTAL_MONEY>"
					+ "<GE_TOTAL_NUM>---总数量</GE_TOTAL_NUM></T1>"
					
					
					+ "<T2><GE_ID>唯一标识EQNum<GE_ID>"
					+ "<INURE_DATE>--录入日期</INURE_DATE>"
					+ "<IS_WHOLE_SET>>--是否是成套设备</IS_WHOLE_SET>"
					+ "<ASSET_CODE>--资产分类编码</ASSET_CODE>"
					+ "<ASSET_NAME>--资产名称</ASSET_NAME>"
					+ "<GE_MODEL>---规格型号</GE_MODEL>"
					+ "<GE_MONEY>---单价</GE_MONEY>"
					+ "<USER_DEPART>--使用部门</USER_DEPART>"
					+ "<PROPERTY_RIGHT></PROPERTY_RIGHT>"
					+ "<ASSET_SOURCE>--资产来源</ASSET_SOURCE>"
					+ "<INVOICE_NO>--发票号</INVOICE_NO>"
					+ "<GE_COMPANY>--供應商</GE_COMPANY>"
					+ "<GE_P_BARGAIN_NO></GE_P_BARGAIN_NO></T2>" +		
					"</SG_DATA></ROOT>";
			
			
		}
		return null;
	}

	public void readStrXml(String strXml) throws Exception {
		String sg_id;// 消息号
		String sg_name;// 消息名称
		String sg_time;// 返回时间
		String cardno;// 卡片号(EQNUM)
		String ge_id;// 固资唯一标识
		String sg_success;// 是否成功
		SAXBuilder builder = new SAXBuilder();
		Document doc = builder.build(new StringReader(strXml));
		List list = doc.getRootElement().getChildren();
		if (list != null && list.size() != 0) {
			Element element = (Element) list.get(0);
			sg_id=element.getChild("SG_ID").getValue();
			sg_name = element.getChild("SG_NAME").getValue();
			sg_time = element.getChild("SG_TIME").getValue();
			List dataList = element.getChildren("SG_DATA");
			Element dataElement = (Element) dataList.get(0);
			if (dataElement != null && list.size() != 0) {
				List t1List = dataElement.getChildren();
				for (int i = 0; i < dataList.size(); i++) {
					Element t1Element = (Element) t1List.get(i);
					cardno= t1Element.getChild("CARDNO").getValue();
					ge_id = t1Element.getChild("GE_ID").getValue();
				}
			}
		}

	}

}
