/*package combiz.ui.test;

import java.util.Date;
import java.util.TimeZone;

import com.inbasis.zul.CategoryModel;
import com.inbasis.zul.Chart;
import com.inbasis.zul.HiLoModel;
import com.inbasis.zul.PieModel;
import com.inbasis.zul.SimpleCategoryModel;
import com.inbasis.zul.SimpleHiLoModel;
import com.inbasis.zul.SimplePieModel;
import com.inbasis.zul.SimpleXYModel;
import com.inbasis.zul.XYModel;

public class MyChart extends Chart
{
	*//**
	 * ����Ҫͼ����ʾʱ�Զ�����KPIͼ��
	 * ͼ�οؼ���ʾʱ����
	 * brianhong  2009-8-28
	 *//*
	public void onCreate()
	{ 
		try
		{
			this.createLine("����");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		try
		{
			this.createBar("");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	*//**
	 * ������״ͼ
	 * brianhong  2009-8-28
	 * @param param
	 * @throws Exception
	 *//*
	public void createLine(String param) 
	throws Exception 
	{
		CategoryModel categoryModel = new SimpleCategoryModel();
		
		//IBOBaseSrv baseSrv = IBOSrvUtil.getBaseSrv();  //�������Դ����ݿ�ȡ��������ͨ������������ҵ���ഫ�ݹ���
		this.setType("line");
		this.setThreeD(true); //3D��ʾ
		this.setFgAlpha(128); //͸����
		
		this.setTitle("����CHART");

		categoryModel.setValue("��һ����","��һ��ֵ", 10);
		categoryModel.setValue("��һ����","�ڶ���ֵ", 32);
		categoryModel.setValue("��һ����","������ֵ", 42);
		categoryModel.setValue("��һ����","���ĸ�ֵ", 476);
		
		categoryModel.setValue("�ڶ�����","��һ��ֵ", 64);
		categoryModel.setValue("�ڶ�����","�ڶ���ֵ", 32);
		categoryModel.setValue("�ڶ�����","������ֵ", 23);
		categoryModel.setValue("�ڶ�����","���ĸ�ֵ", 43);
		
		this.setModel(categoryModel);
		
		
		///XYLine
		XYModel xymodel = new SimpleXYModel();
		xymodel.addValue("2001", new Integer(20), new Integer(120));
		xymodel.addValue("2001", new Integer(40), new Integer(135));
		xymodel.addValue("2001", new Integer(60), new Integer(140));
		xymodel.addValue("2001", new Integer(80), new Integer(160));

		xymodel.addValue("2002", new Integer(30), new Integer(120));
		xymodel.addValue("2002", new Integer(50), new Integer(135));
		xymodel.addValue("2002", new Integer(70), new Integer(140));
		xymodel.addValue("2002", new Integer(90), new Integer(160));
		
		this.setModel(xymodel);
	}
	
	*//**
	 * ������ͼ
	 * brianhong  2009-8-28
	 * @param param
	 * @throws Exception
	 *//*
	public void createPieOrRing(String param) 
	throws Exception 
	{
		PieModel pieModel = new SimplePieModel();

		//IBOBaseSrv baseSrv = IBOSrvUtil.getBaseSrv();  //�������Դ����ݿ�ȡ��������ͨ������������ҵ���ഫ�ݹ���
		this.setType("pie"); //��ͼ
		//this.setType("ring");  //Ȧͼ
		this.setFgAlpha(128); //͸����
		this.setThreeD(true);
			
		this.setTitle("����CHART");

		pieModel.setValue("�ֿ�1", 10);
		pieModel.setValue("�ֿ�2", 10);
		pieModel.setValue("�ֿ�3", 10);
		pieModel.setValue("�ֿ�4", 10);
		
		this.setModel(pieModel);
	}
	
	*//**
	 * ������״ͼ
	 * brianhong  2009-8-28
	 * @param param
	 * @throws Exception
	 *//*
	public void createBar(String param) 
	throws Exception 
	{
		CategoryModel categoryModel = new SimpleCategoryModel();

		//IBOBaseSrv baseSrv = IBOSrvUtil.getBaseSrv();  //�������Դ����ݿ�ȡ��������ͨ������������ҵ���ഫ�ݹ���
		this.setType("bar"); //
		this.setFgAlpha(128); //͸����
		this.setThreeD(true);
		this.setTitle("����CHART");

		categoryModel.setValue("��Ʒ1","��һ��", 10);
		categoryModel.setValue("��Ʒ1","�ڶ���", 32);
		categoryModel.setValue("��Ʒ1","������", 42);
		categoryModel.setValue("��Ʒ1","������", 476);
		
		categoryModel.setValue("��Ʒ2","��һ��", 64);
		categoryModel.setValue("��Ʒ2","�ڶ���", 32);
		categoryModel.setValue("��Ʒ2","������", 23);
		categoryModel.setValue("��Ʒ2","������", 43);
		
		
		categoryModel.setValue("��Ʒ1","��һ��", 10);
		categoryModel.setValue("��Ʒ2","��һ��", 32);
		categoryModel.setValue("��Ʒ3","��һ��", 42);
		categoryModel.setValue("��Ʒ4","��һ��", 476);
		
		categoryModel.setValue("��Ʒ1","�ڶ���", 64);
		categoryModel.setValue("��Ʒ2","�ڶ���", 32);
		categoryModel.setValue("��Ʒ3","�ڶ���", 23);
		categoryModel.setValue("��Ʒ4","�ڶ���", 43);
		
		this.setModel(categoryModel);
		
		
		XYbar
		XYModel datemodel = new SimpleXYModel();
		datemodel.addValue("2001", new Long(DateUtil.GetTime(2001, 5, 2, 1, 10, 15).getTime()), new Integer(120));
		datemodel.addValue("2001", new Long(DateUtil.GetTime(2001, 5, 2, 2, 10, 15).getTime()), new Integer(135));
		datemodel.addValue("2001", new Long(DateUtil.GetTime(2001, 5, 2, 3, 10, 15).getTime()), new Integer(140));
		datemodel.addValue("2001", new Long(DateUtil.GetTime(2001, 5, 2, 4, 10, 15).getTime()), new Integer(160));

		datemodel.addValue("2002", new Long(DateUtil.GetTime(2001, 5, 2, 1, 10, 20).getTime()), new Integer(125));
		datemodel.addValue("2002", new Long(DateUtil.GetTime(2001, 5, 2, 2, 10, 20).getTime()), new Integer(130));
		datemodel.addValue("2002", new Long(DateUtil.GetTime(2001, 5, 2, 3, 10, 20).getTime()), new Integer(120));
		datemodel.addValue("2002", new Long(DateUtil.GetTime(2001, 5, 2, 4, 10, 20).getTime()), new Integer(180));
		
		this.setModel(datemodel);
		
	}
	
	
	*//**
	 * ����StackedBar��״ͼ
	 * brianhong  2009-8-28
	 * @param param
	 * @throws Exception
	 *//*
	public void createStackedBar(String param) 
	throws Exception 
	{
		CategoryModel categoryModel = new SimpleCategoryModel();

		//IBOBaseSrv baseSrv = IBOSrvUtil.getBaseSrv();  //�������Դ����ݿ�ȡ��������ͨ������������ҵ���ഫ�ݹ���
		this.setType("stacked_bar"); //
		this.setFgAlpha(128); //͸����
		this.setThreeD(true);
		this.setTitle("����CHART");

		categoryModel.setValue("��Ʒ1","��һ��", 10);
		categoryModel.setValue("��Ʒ1","�ڶ���", 32);
		categoryModel.setValue("��Ʒ1","������", 42);
		categoryModel.setValue("��Ʒ1","������", 476);
		
		categoryModel.setValue("��Ʒ2","��һ��", 64);
		categoryModel.setValue("��Ʒ2","�ڶ���", 32);
		categoryModel.setValue("��Ʒ2","������", 23);
		categoryModel.setValue("��Ʒ2","������", 43);
		
		
		categoryModel.setValue("��Ʒ1","��һ��", 10);
		categoryModel.setValue("��Ʒ2","��һ��", 32);
		categoryModel.setValue("��Ʒ3","��һ��", 42);
		categoryModel.setValue("��Ʒ4","��һ��", 476);
		
		categoryModel.setValue("��Ʒ1","�ڶ���", 64);
		categoryModel.setValue("��Ʒ2","�ڶ���", 32);
		categoryModel.setValue("��Ʒ3","�ڶ���", 23);
		categoryModel.setValue("��Ʒ4","�ڶ���", 43);
		
		this.setModel(categoryModel);
	}
	
	*//**
	 * ����Area
	 * brianhong  2009-8-28
	 * @param param
	 * @throws Exception
	 *//*
	public void createArea(String param) 
	throws Exception 
	{
		CategoryModel categoryModel = new SimpleCategoryModel();

		//IBOBaseSrv baseSrv = IBOSrvUtil.getBaseSrv();  //�������Դ����ݿ�ȡ��������ͨ������������ҵ���ഫ�ݹ���
		this.setType("area"); //
		this.setFgAlpha(128); //͸����
		this.setThreeD(true);
		this.setTitle("����CHART");

		categoryModel.setValue("��Ʒ1","��һ��", 10);
		categoryModel.setValue("��Ʒ1","�ڶ���", 32);
		categoryModel.setValue("��Ʒ1","������", 42);
		categoryModel.setValue("��Ʒ1","������", 476);
		
		categoryModel.setValue("��Ʒ2","��һ��", 64);
		categoryModel.setValue("��Ʒ2","�ڶ���", 32);
		categoryModel.setValue("��Ʒ2","������", 23);
		categoryModel.setValue("��Ʒ2","������", 43);
		this.setModel(categoryModel);
		
		
		///XYarea
		XYModel xymodel = new SimpleXYModel();
		xymodel.addValue("2001", new Integer(20), new Integer(120));
		xymodel.addValue("2001", new Integer(40), new Integer(135));
		xymodel.addValue("2001", new Integer(60), new Integer(140));
		xymodel.addValue("2001", new Integer(80), new Integer(160));

		xymodel.addValue("2002", new Integer(30), new Integer(120));
		xymodel.addValue("2002", new Integer(50), new Integer(135));
		xymodel.addValue("2002", new Integer(70), new Integer(140));
		xymodel.addValue("2002", new Integer(90), new Integer(160));
		
		this.setModel(xymodel);
		
	}
	
	
	*//**
	 * ����stacked_area
	 * brianhong  2009-8-28
	 * @param param
	 * @throws Exception
	 *//*
	public void createStackedArea(String param) 
	throws Exception 
	{
		CategoryModel categoryModel = new SimpleCategoryModel();

		//IBOBaseSrv baseSrv = IBOSrvUtil.getBaseSrv();  //�������Դ����ݿ�ȡ��������ͨ������������ҵ���ഫ�ݹ���
		this.setType("stacked_area"); //
		this.setFgAlpha(128); //͸����
		this.setThreeD(true);
		this.setTitle("����CHART");

		categoryModel.setValue("��Ʒ1","��һ��", 10);
		categoryModel.setValue("��Ʒ1","�ڶ���", 32);
		categoryModel.setValue("��Ʒ1","������", 42);
		categoryModel.setValue("��Ʒ1","������", 476);
		
		categoryModel.setValue("��Ʒ2","��һ��", 64);
		categoryModel.setValue("��Ʒ2","�ڶ���", 32);
		categoryModel.setValue("��Ʒ2","������", 23);
		categoryModel.setValue("��Ʒ2","������", 43);
		
		
		categoryModel.setValue("��Ʒ1","��һ��", 10);
		categoryModel.setValue("��Ʒ2","��һ��", 32);
		categoryModel.setValue("��Ʒ3","��һ��", 42);
		categoryModel.setValue("��Ʒ4","��һ��", 476);
		
		categoryModel.setValue("��Ʒ1","�ڶ���", 64);
		categoryModel.setValue("��Ʒ2","�ڶ���", 32);
		categoryModel.setValue("��Ʒ3","�ڶ���", 23);
		categoryModel.setValue("��Ʒ4","�ڶ���", 43);
		
		this.setModel(categoryModel);
		
		xystackedarea
		XYModel datemodel = new SimpleXYModel();
		datemodel.addValue("2001", new Long(DateUtil.GetTime(2001, 5, 2, 1, 10, 15).getTime()), new Integer(120));
		datemodel.addValue("2001", new Long(DateUtil.GetTime(2001, 5, 2, 2, 10, 15).getTime()), new Integer(135));
		datemodel.addValue("2001", new Long(DateUtil.GetTime(2001, 5, 2, 3, 10, 15).getTime()), new Integer(140));
		datemodel.addValue("2001", new Long(DateUtil.GetTime(2001, 5, 2, 4, 10, 15).getTime()), new Integer(160));

		datemodel.addValue("2002", new Long(DateUtil.GetTime(2001, 5, 2, 1, 10, 20).getTime()), new Integer(125));
		datemodel.addValue("2002", new Long(DateUtil.GetTime(2001, 5, 2, 2, 10, 20).getTime()), new Integer(130));
		datemodel.addValue("2002", new Long(DateUtil.GetTime(2001, 5, 2, 3, 10, 20).getTime()), new Integer(120));
		datemodel.addValue("2002", new Long(DateUtil.GetTime(2001, 5, 2, 4, 10, 20).getTime()), new Integer(180));
		
		this.setModel(datemodel);
	}
	
	
	*//**
	 * ����waterfall
	 * brianhong  2009-8-28
	 * @param param
	 * @throws Exception
	 *//*
	public void createWaterfall(String param) 
	throws Exception 
	{
		CategoryModel categoryModel = new SimpleCategoryModel();

		//IBOBaseSrv baseSrv = IBOSrvUtil.getBaseSrv();  //�������Դ����ݿ�ȡ��������ͨ������������ҵ���ഫ�ݹ���
		this.setType("waterfall"); //
		this.setFgAlpha(128); //͸����
		this.setThreeD(true);
		this.setTitle("����CHART");

		categoryModel.setValue("��Ʒ1","��һ��", 10);
		categoryModel.setValue("��Ʒ1","�ڶ���", 32);
		categoryModel.setValue("��Ʒ1","������", 42);
		categoryModel.setValue("��Ʒ1","������", 476);
		
		categoryModel.setValue("��Ʒ2","��һ��", 64);
		categoryModel.setValue("��Ʒ2","�ڶ���", 32);
		categoryModel.setValue("��Ʒ2","������", 23);
		categoryModel.setValue("��Ʒ2","������", 43);
		
		
		categoryModel.setValue("��Ʒ1","��һ��", 10);
		categoryModel.setValue("��Ʒ2","��һ��", 32);
		categoryModel.setValue("��Ʒ3","��һ��", 42);
		categoryModel.setValue("��Ʒ4","��һ��", 476);
		
		categoryModel.setValue("��Ʒ1","�ڶ���", 64);
		categoryModel.setValue("��Ʒ2","�ڶ���", 32);
		categoryModel.setValue("��Ʒ3","�ڶ���", 23);
		categoryModel.setValue("��Ʒ4","�ڶ���", 43);
		
		this.setModel(categoryModel);
	}
	
	
	
	*//**
	 * ����Polar
	 * brianhong  2009-8-28
	 * @param param
	 * @throws Exception
	 *//*
	public void createPolar(String param) 
	throws Exception
	{
		//IBOBaseSrv baseSrv = IBOSrvUtil.getBaseSrv();  //�������Դ����ݿ�ȡ��������ͨ������������ҵ���ഫ�ݹ���
		this.setType("polar"); //
		this.setFgAlpha(128); //͸����
		this.setThreeD(true);
		this.setTitle("����CHART");
		
		///XYarea
		XYModel xymodel = new SimpleXYModel();
		xymodel.addValue("2001", new Integer(20), new Integer(120));
		xymodel.addValue("2001", new Integer(40), new Integer(135));
		xymodel.addValue("2001", new Integer(60), new Integer(140));
		xymodel.addValue("2001", new Integer(80), new Integer(160));

		xymodel.addValue("2002", new Integer(30), new Integer(120));
		xymodel.addValue("2002", new Integer(50), new Integer(135));
		xymodel.addValue("2002", new Integer(70), new Integer(140));
		xymodel.addValue("2002", new Integer(90), new Integer(160));
		
		this.setModel(xymodel);
		
	}
	
	
	*//**
	 * ����scatter
	 * brianhong  2009-8-28
	 * @param param
	 * @throws Exception
	 *//*
	public void createScatter(String param) 
	throws Exception
	{
		//IBOBaseSrv baseSrv = IBOSrvUtil.getBaseSrv();  //�������Դ����ݿ�ȡ��������ͨ������������ҵ���ഫ�ݹ���
		this.setType("scatter"); //
		this.setFgAlpha(128); //͸����
		this.setThreeD(true);
		this.setTitle("����CHART");
		
		///XYarea
		XYModel xymodel = new SimpleXYModel();
		xymodel.addValue("2001", new Integer(20), new Integer(120));
		xymodel.addValue("2001", new Integer(40), new Integer(135));
		xymodel.addValue("2001", new Integer(60), new Integer(140));
		xymodel.addValue("2001", new Integer(80), new Integer(160));

		xymodel.addValue("2002", new Integer(30), new Integer(120));
		xymodel.addValue("2002", new Integer(50), new Integer(135));
		xymodel.addValue("2002", new Integer(70), new Integer(140));
		xymodel.addValue("2002", new Integer(90), new Integer(160));
		
		this.setModel(xymodel);
		
	}
	
	*//**
	 * ����time_series
	 * brianhong  2009-8-28
	 * @param param
	 * @throws Exception
	 *//*
	public void createTimeSeries(String param) 
	throws Exception
	{
		//IBOBaseSrv baseSrv = IBOSrvUtil.getBaseSrv();  //�������Դ����ݿ�ȡ��������ͨ������������ҵ���ഫ�ݹ���
		this.setType("time_series"); //
		this.setFgAlpha(128); //͸����
		this.setThreeD(true);
		this.setTitle("����CHART");

		///XYarea
		XYModel xymodel = new SimpleXYModel();
		xymodel.addValue("2001", new Integer(20), new Integer(120));
		xymodel.addValue("2001", new Integer(40), new Integer(135));
		xymodel.addValue("2001", new Integer(60), new Integer(140));
		xymodel.addValue("2001", new Integer(80), new Integer(160));

		xymodel.addValue("2002", new Integer(30), new Integer(120));
		xymodel.addValue("2002", new Integer(50), new Integer(135));
		xymodel.addValue("2002", new Integer(70), new Integer(140));
		xymodel.addValue("2002", new Integer(90), new Integer(160));
		
		this.setModel(xymodel);
	}
	
	*//**
	 * ����step_area
	 * brianhong  2009-8-28
	 * @param param
	 * @throws Exception
	 *//*
	public void createStepArea(String param) 
	throws Exception
	{
		//IBOBaseSrv baseSrv = IBOSrvUtil.getBaseSrv();  //�������Դ����ݿ�ȡ��������ͨ������������ҵ���ഫ�ݹ���
		this.setType("step_area"); //
		this.setFgAlpha(128); //͸����
		this.setThreeD(true);
		this.setTitle("����CHART");
		
		///XYarea
		XYModel xymodel = new SimpleXYModel();
		xymodel.addValue("2001", new Integer(20), new Integer(120));
		xymodel.addValue("2001", new Integer(40), new Integer(135));
		xymodel.addValue("2001", new Integer(60), new Integer(140));
		xymodel.addValue("2001", new Integer(80), new Integer(160));

		xymodel.addValue("2002", new Integer(30), new Integer(120));
		xymodel.addValue("2002", new Integer(50), new Integer(135));
		xymodel.addValue("2002", new Integer(70), new Integer(140));
		xymodel.addValue("2002", new Integer(90), new Integer(160));
		
		this.setModel(xymodel);
	}
	
	
	
	*//**
	 * Step
	 * brianhong  2009-9-21
	 * @param param
	 * @throws Exception
	 *//*
	public void createStep(String param) 
	throws Exception
	{
		//IBOBaseSrv baseSrv = IBOSrvUtil.getBaseSrv();  //�������Դ����ݿ�ȡ��������ͨ������������ҵ���ഫ�ݹ���
		this.setType("step"); //
		this.setFgAlpha(128); //͸����
		this.setThreeD(true);
		this.setTitle("����CHART");
		
		XYModel datemodel = new SimpleXYModel();
		datemodel.addValue("2001", new Long(GetTime(2001, 5, 2, 1, 10, 15).getTime()), new Integer(120));
		datemodel.addValue("2001", new Long(GetTime(2001, 5, 2, 2, 10, 15).getTime()), new Integer(135));
		datemodel.addValue("2001", new Long(GetTime(2001, 5, 2, 3, 10, 15).getTime()), new Integer(140));
		datemodel.addValue("2001", new Long(GetTime(2001, 5, 2, 4, 10, 15).getTime()), new Integer(160));

		datemodel.addValue("2002", new Long(GetTime(2001, 5, 2, 1, 10, 20).getTime()), new Integer(125));
		datemodel.addValue("2002", new Long(GetTime(2001, 5, 2, 2, 10, 20).getTime()), new Integer(130));
		datemodel.addValue("2002", new Long(GetTime(2001, 5, 2, 3, 10, 20).getTime()), new Integer(120));
		datemodel.addValue("2002", new Long(GetTime(2001, 5, 2, 4, 10, 20).getTime()), new Integer(180));
		
		this.setModel(datemodel);
	}
	
	*//**
	 * Step
	 * brianhong  2009-9-21
	 * @param param
	 * @throws Exception
	 *//*
	public void createHistogram(String param) 
	throws Exception
	{
		//IBOBaseSrv baseSrv = IBOSrvUtil.getBaseSrv();  //�������Դ����ݿ�ȡ��������ͨ������������ҵ���ഫ�ݹ���
		this.setType("histogram"); //
		this.setFgAlpha(128); //͸����
		this.setThreeD(true);
		this.setTitle("����CHART");
		
		XYModel datemodel = new SimpleXYModel();
		datemodel.addValue("2001", new Long(GetTime(2001, 5, 2, 1, 10, 15).getTime()), new Integer(120));
		datemodel.addValue("2001", new Long(GetTime(2001, 5, 2, 2, 10, 15).getTime()), new Integer(135));
		datemodel.addValue("2001", new Long(GetTime(2001, 5, 2, 3, 10, 15).getTime()), new Integer(140));
		datemodel.addValue("2001", new Long(GetTime(2001, 5, 2, 4, 10, 15).getTime()), new Integer(160));

		datemodel.addValue("2002", new Long(GetTime(2001, 5, 2, 1, 10, 20).getTime()), new Integer(125));
		datemodel.addValue("2002", new Long(GetTime(2001, 5, 2, 2, 10, 20).getTime()), new Integer(130));
		datemodel.addValue("2002", new Long(GetTime(2001, 5, 2, 3, 10, 20).getTime()), new Integer(120));
		datemodel.addValue("2002", new Long(GetTime(2001, 5, 2, 4, 10, 20).getTime()), new Integer(180));
		
		this.setModel(datemodel);
	}
	
	
	
	
	*//**
	 * candlestick
	 * brianhong  2009-9-21
	 * @param param
	 * @throws Exception
	 *//*
	public void createCandlestick(String param) 
	throws Exception
	{
		//IBOBaseSrv baseSrv = IBOSrvUtil.getBaseSrv();  //�������Դ����ݿ�ȡ��������ͨ������������ҵ���ഫ�ݹ���
		this.setType("candlestick"); //
		this.setFgAlpha(128); //͸����
		this.setThreeD(true);
		this.setTitle("����CHART");
		
		HiLoModel hilomodel = new SimpleHiLoModel();
		long d = new Date().getTime();
		hilomodel.addValue(new Date(d),  new Double(45.5),  new Double(54.2), new Double(19.9), new Double(42.8), new Double(20));
		hilomodel.addValue(new Date(d+1000),  new Double(46.5),  new Double(55.2),  new Double(43.8), new Double(50.9),   new Double(32));
		hilomodel.addValue(new Date(d+2000),  new Double(47.5),  new Double(56.2),  new Double(44.8), new Double(51.9),   new Double(33));
		hilomodel.addValue(new Date(d+3000),  new Double(48.5),  new Double(57.2),  new Double(45.8), new Double(52.9),   new Double(34));
		hilomodel.addValue(new Date(d+4000),  new Double(49.5),  new Double(58.2),  new Double(46.8), new Double(53.9),   new Double(35));
		hilomodel.addValue(new Date(d+5000),  new Double(50.5),  new Double(59.2),  new Double(47.8), new Double(54.9),   new Double(36));
		hilomodel.addValue(new Date(d+6000),  new Double(51.5),  new Double(60.2),  new Double(48.8), new Double(55.9),   new Double(37));
		hilomodel.addValue(new Date(d+7000),  new Double(52.5),  new Double(61.2),  new Double(49.8), new Double(56.9),   new Double(38));
		hilomodel.addValue(new Date(d+8000),  new Double(53.5),  new Double(62.2),  new Double(50.8), new Double(57.9),   new Double(39));
		hilomodel.addValue(new Date(d+9000),  new Double(54.5),  new Double(63.2),  new Double(51.8), new Double(58.9),   new Double(40));
		hilomodel.addValue(new Date(d+10000),  new Double(55.5),  new Double(64.2),  new Double(52.8), new Double(59.9),   new Double(41));
		hilomodel.addValue(new Date(d+11000),  new Double(56.5),  new Double(65.2),  new Double(53.8), new Double(60.9),   new Double(42));
		hilomodel.addValue(new Date(d+12000),  new Double(57.5),  new Double(66.2),  new Double(54.8), new Double(61.9),   new Double(43));
		hilomodel.addValue(new Date(d+13000),  new Double(58.5),  new Double(67.2),  new Double(55.8), new Double(62.9),   new Double(44));
		hilomodel.addValue(new Date(d+14000),  new Double(59.5),  new Double(68.2),  new Double(56.8), new Double(63.9),   new Double(45));
		hilomodel.addValue(new Date(d+15000),  new Double(60.5),  new Double(69.2),  new Double(57.8), new Double(64.9),   new Double(46));
		hilomodel.addValue(new Date(d+16000),  new Double(61.5),  new Double(70.2),  new Double(58.8), new Double(65.9),   new Double(47));
		hilomodel.addValue(new Date(d+17000),  new Double(62.5),  new Double(71.2),  new Double(59.8), new Double(66.9),   new Double(48));
		hilomodel.addValue(new Date(d+18000),  new Double(63.5),  new Double(72.2),  new Double(60.8), new Double(67.9),   new Double(49));
	
		this.setModel(hilomodel);
	}
	
	
	
	*//**
	 * highlow
	 * brianhong  2009-9-21
	 * @param param
	 * @throws Exception
	 *//*
	public void createHighlow(String param) 
	throws Exception
	{
		//IBOBaseSrv baseSrv = IBOSrvUtil.getBaseSrv();  //�������Դ����ݿ�ȡ��������ͨ������������ҵ���ഫ�ݹ���
		this.setType("highlow"); //
		this.setFgAlpha(128); //͸����
		this.setThreeD(true);
		this.setTitle("����CHART");
		
		HiLoModel hilomodel = new SimpleHiLoModel();
		long d = new Date().getTime();
		hilomodel.addValue(new Date(d),  new Double(45.5),  new Double(54.2), new Double(19.9), new Double(42.8), new Double(20));
		hilomodel.addValue(new Date(d+1000),  new Double(46.5),  new Double(55.2),  new Double(43.8), new Double(50.9),   new Double(32));
		hilomodel.addValue(new Date(d+2000),  new Double(47.5),  new Double(56.2),  new Double(44.8), new Double(51.9),   new Double(33));
		hilomodel.addValue(new Date(d+3000),  new Double(48.5),  new Double(57.2),  new Double(45.8), new Double(52.9),   new Double(34));
		hilomodel.addValue(new Date(d+4000),  new Double(49.5),  new Double(58.2),  new Double(46.8), new Double(53.9),   new Double(35));
		hilomodel.addValue(new Date(d+5000),  new Double(50.5),  new Double(59.2),  new Double(47.8), new Double(54.9),   new Double(36));
		hilomodel.addValue(new Date(d+6000),  new Double(51.5),  new Double(60.2),  new Double(48.8), new Double(55.9),   new Double(37));
		hilomodel.addValue(new Date(d+7000),  new Double(52.5),  new Double(61.2),  new Double(49.8), new Double(56.9),   new Double(38));
		hilomodel.addValue(new Date(d+8000),  new Double(53.5),  new Double(62.2),  new Double(50.8), new Double(57.9),   new Double(39));
		hilomodel.addValue(new Date(d+9000),  new Double(54.5),  new Double(63.2),  new Double(51.8), new Double(58.9),   new Double(40));
		hilomodel.addValue(new Date(d+10000),  new Double(55.5),  new Double(64.2),  new Double(52.8), new Double(59.9),   new Double(41));
		hilomodel.addValue(new Date(d+11000),  new Double(56.5),  new Double(65.2),  new Double(53.8), new Double(60.9),   new Double(42));
		hilomodel.addValue(new Date(d+12000),  new Double(57.5),  new Double(66.2),  new Double(54.8), new Double(61.9),   new Double(43));
		hilomodel.addValue(new Date(d+13000),  new Double(58.5),  new Double(67.2),  new Double(55.8), new Double(62.9),   new Double(44));
		hilomodel.addValue(new Date(d+14000),  new Double(59.5),  new Double(68.2),  new Double(56.8), new Double(63.9),   new Double(45));
		hilomodel.addValue(new Date(d+15000),  new Double(60.5),  new Double(69.2),  new Double(57.8), new Double(64.9),   new Double(46));
		hilomodel.addValue(new Date(d+16000),  new Double(61.5),  new Double(70.2),  new Double(58.8), new Double(65.9),   new Double(47));
		hilomodel.addValue(new Date(d+17000),  new Double(62.5),  new Double(71.2),  new Double(59.8), new Double(66.9),   new Double(48));
		hilomodel.addValue(new Date(d+18000),  new Double(63.5),  new Double(72.2),  new Double(60.8), new Double(67.9),   new Double(49));
	
		this.setModel(hilomodel);
	}
	
	
	
	*//**
	 * ͨ�������ջ�ȡDate����
	 * brianhong  2009-9-21
	 * @param year
	 * @param month
	 * @param day
	 * @return
	 *//*
	public Date GetDate(int year, int month, int day) {
		final java.util.Calendar calendar = java.util.Calendar.getInstance();
		calendar.set(year, month-1, day);
		final Date result = calendar.getTime();
		return result;
	  }

	*//**
	 * ͨ��������ʱ�����ȡDate����
	 * brianhong  2009-9-21
	 * @param year
	 * @param month
	 * @param day
	 * @param hour
	 * @param minute
	 * @param second
	 * @return
	 *//*
	  public Date GetTime(int year, int month, int day, int hour, int minute, int second) {
		final java.util.Calendar calendar = java.util.Calendar.getInstance(TimeZone.getTimeZone("GMT"));
		calendar.set(year, month-1, day, hour, minute, second);
		final Date result = calendar.getTime();
		return result;
	  }
	
}
*/