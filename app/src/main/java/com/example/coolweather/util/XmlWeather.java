package com.example.coolweather.util;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.dom4j.tree.DefaultAttribute;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
/**
* Java递归遍历XML所有元素  
*   
* @author  Administrator  
* @version  [版本号, Apr 13, 2010]  
* @see  [相关类/方法]  
* @since  [产品/模块版本]  
*/  
public class XmlWeather
{   
    // private static Map xmlmap = new HashMap();
    //存储xml元素信息的容器
    private static ArrayList<Leaf> elemList = new ArrayList<Leaf>();

    /*public static void main(String args[])
        throws DocumentException
    {
    	xmltest test = new xmltest();
        String path = "http://flash.weather.com.cn/wmaps/xml/hubei.xml";
        // 读取XML文件
        SAXReader reader = new SAXReader();
        Document doc = reader.read(path);
        // 获取XML根元素
        Element root = doc.getRootElement();
        test.getElementList(root);
        String x = test.getListString(elemList);
        @SuppressWarnings({ "unchecked", "unused" })
		List<weather> weathers = JsonUtil.getListFromJsonArrStr(x, weather.class);
		weather areaWeather = new weather();
		areaWeather = test.getCityWeather("hubei", "十堰");
		Date now = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd ");
        System.out.println(areaWeather.toString()+"\n"+dateFormat.format(now)+areaWeather.getTime()+":00");
        System.out.println("-----------解析结果------------\n" + x);
    }*/
    /**
     * 获取城市天气
     * <功能详细描述>
     * @param province
     * @param city
     */
    public static void getCityWeather(final String province,final String city,
                                         final HttpCallbackListener listener){
        new Thread(new Runnable() {
            @Override
            public void run() {
                XmlWeather test = new XmlWeather();
                String x = "";
                String path = "http://flash.weather.com.cn/wmaps/xml/"+province+".xml";
                // 读取XML文件
                SAXReader reader = new SAXReader();
                Document doc = null;
                try {
                    doc = reader.read(path);
                } catch (DocumentException e) {
                    if(listener != null){
                        listener.onError(e);
                    }
                }
                // 获取XML根元素
                Element root = doc.getRootElement();
                test.getElementList(root);
                x = test.getListString(elemList);
                @SuppressWarnings("unchecked")
                List<weather> weathers = JsonUtil.getListFromJsonArrStr(x, weather.class);
                List<weather> cityWeather = new ArrayList<weather>();
                for(weather i : weathers){
                    if(city.equals(i.getCityname())){
                        cityWeather.add(i);
                        break;
                    }
                }
                if(listener != null){
                    listener.onFinish(cityWeather);
                }
            }
        }).start();
    }
    /**
     * 获取地区天气列表
     * <功能详细描述>
     * @param city
     */
    public static void getAreaWeather(final String city,final HttpCallbackListener listener){
    	new Thread(new Runnable() {
            @Override
            public void run() {
                XmlWeather test = new XmlWeather();
                List<weather> weathers = new ArrayList<weather>();
                String path = "http://flash.weather.com.cn/wmaps/xml/"+city+".xml";
                // 读取XML文件
                SAXReader reader = new SAXReader();
                Document doc = null;
                try {
                    doc = reader.read(path);
                } catch (DocumentException e) {
                    if(listener != null){
                        listener.onError(e);
                    }
                }
                // 获取XML根元素
                Element root = doc.getRootElement();
                test.getElementList(root);
                String x = test.getListString(elemList);

                //getElementList(root);
                try {
                    JSONArray jsonArray = new JSONArray(x);
                    for(int i = 0;i < jsonArray.length(); i++){
                        weather weather = new weather();
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        if (city.equals("china")) {
                            weather.setCityname(jsonObject.getString("quName"));
                        }else {
                            weather.setCityname(jsonObject.getString("cityname"));
                        }
                        weather.setPyName(jsonObject.getString("pyName"));
                        weathers.add(weather);
                    }
                    System.out.println(weathers);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                System.out.println(x);
                //List<weather> weathers = JsonUtil.getListFromJsonArrStr(x, weather.class);
                if(listener != null){
                    listener.onFinish(weathers);
                }
            }
        }).start();

    }
    /**
     * 获取节点所有属性值
     * <功能详细描述>
     * @param element
     * @return
     * @see [类、类#方法、类#成员]
     */
    @SuppressWarnings("rawtypes")
	public static String getNoteAttribute(Element element)
    {
        String xattribute = "{";
        DefaultAttribute e = null;
        List list = element.attributes();
        for (int i = 0; i < list.size(); i++)
        {
            e = (DefaultAttribute)list.get(i);
            //System.out.println("name = " + e.getName() + ", value = " + e.getText());
            if(i < list.size()-1) {
            	xattribute += "\"" + e.getName()+"\"" + ":" +"\""+ e.getText() + "\",";
            }else {
				xattribute += "\"" + e.getName()+"\"" + ":" +"\""+ e.getText() + "\"";
			}
        }
        xattribute += "}";
        //System.out.println(xattribute);
        return xattribute;
    }

    /**
     * 递归遍历方法
     * <功能详细描述>
     * @param element
     * @see [类、类#方法、类#成员]
     */
    @SuppressWarnings("rawtypes")
	public static void getElementList(Element element)
    {
        List elements = element.elements();
        // 没有子元素
        if (elements.isEmpty())
        {
            String xpath = element.getPath();
            String value = element.getTextTrim();
            elemList.add(new Leaf(getNoteAttribute(element), xpath, value));
        }
        else
        {
            // 有子元素
            Iterator it = elements.iterator();
            while (it.hasNext())
            {
                Element elem = (Element)it.next();
                getNoteAttribute(elem);
                // 递归遍历
                getElementList(elem);
            }
        }
    }

    @SuppressWarnings("rawtypes")
	public String getListString(List elemList)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("[");
        for (Iterator it = elemList.iterator(); it.hasNext();)
        {
            Leaf leaf = (Leaf)it.next();
            //sb.append("xpath: " + leaf.getXpath()).append(", value: ").append(leaf.getValue());
            if (!"".equals(leaf.getXattribute()))
            {
                sb.append(leaf.getXattribute());
            }
            if(it.hasNext()){
            	sb.append(",");
            }
        }
        sb.append("]");
        return sb.toString();
    }

}      

