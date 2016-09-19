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
		System.out.println("�����������������~~");

	}

	/**
	 * ���ݶ����xml��ʽ������xml�ַ��� ����:������ ����:Mar 18, 2009
	 * 
	 * @return
	 */
	public String buildStrXml() throws Exception {
		String strXml = "";
		
		List list = baseDao.findWithQuery(Equipment.class, "isupload=0 and group by ");
		for (int i = 0; i < list.size(); i++) {
			Equipment equipment = (Equipment) list.get(i);
			strXml = "<ROOT><HEAD><SG_ID>--��Ϣ���</SG_ID>"
					+ "<SG_TYPE>F001</SG_TYPE>"
					+ "<SG_NAME>IT����</SG_NAME>"
					+ "<SG_IN_DATE>"+DateUnit.getSimpleFormatToday()+"</SG_IN_DATE>"
					+ "<SG_HEADER>��Ϣͷ</SG_HEADER>"
					+ "<SG_DO_DATE>"+DateUnit.getSimpleFormatToday()+"</SG_DO_DATE>"
					+ "<SG_MANAGE_DEPART>--�豸���ܲ���</SG_MANAGE_DEPART>"
					+ "<SG_REMARK></SG_REMARK></HEAD>"
					+ "<SG_DATA><T1>"
					+ "<GE_USER>--�豸������</GE_USER>"
					+ "<GE_NEXT_USER>--�ʲ�������</GE_NEXT_USER>"
					+ "<GE_LAST_USER>--���Ѿ�����</GE_LAST_USER>"
					+ "<GE_TOTAL_MONEY>---�ܼ�</GE_TOTAL_MONEY>"
					+ "<GE_TOTAL_NUM>---������</GE_TOTAL_NUM></T1>"
					
					
					+ "<T2><GE_ID>Ψһ��ʶEQNum<GE_ID>"
					+ "<INURE_DATE>--¼������</INURE_DATE>"
					+ "<IS_WHOLE_SET>>--�Ƿ��ǳ����豸</IS_WHOLE_SET>"
					+ "<ASSET_CODE>--�ʲ��������</ASSET_CODE>"
					+ "<ASSET_NAME>--�ʲ�����</ASSET_NAME>"
					+ "<GE_MODEL>---����ͺ�</GE_MODEL>"
					+ "<GE_MONEY>---����</GE_MONEY>"
					+ "<USER_DEPART>--ʹ�ò���</USER_DEPART>"
					+ "<PROPERTY_RIGHT></PROPERTY_RIGHT>"
					+ "<ASSET_SOURCE>--�ʲ���Դ</ASSET_SOURCE>"
					+ "<INVOICE_NO>--��Ʊ��</INVOICE_NO>"
					+ "<GE_COMPANY>--������</GE_COMPANY>"
					+ "<GE_P_BARGAIN_NO></GE_P_BARGAIN_NO></T2>" +		
					"</SG_DATA></ROOT>";
			
			
		}
		return null;
	}

	public void readStrXml(String strXml) throws Exception {
		String sg_id;// ��Ϣ��
		String sg_name;// ��Ϣ����
		String sg_time;// ����ʱ��
		String cardno;// ��Ƭ��(EQNUM)
		String ge_id;// ����Ψһ��ʶ
		String sg_success;// �Ƿ�ɹ�
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
