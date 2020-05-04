package com.chen.spider.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.chen.spider.model.Image;

/**
 * ץȡ��ͼ���ϵ���ŮͼƬ
 * @author 26612
 */
public class SpiderUtil {
	/**
	  * ����Ҫ����ͼƬ����ַ
	 */
	private static Map<String,String> urls = new HashMap<String,String>();
	
	static {
		urls.put("fresh", "https://www.meitulu.com/t/qingxin/");
		urls.put("art", "https://www.meitulu.com/t/youhuo/");
	}
	
	/**
	 *   ��ȡָ�������Լ�ҳ���ͼƬ����
	 * @param category
	 * @param pageNum
	 * @return
	 */
	public static List<Image> queryImageList(String category,String pageNum){
		List<Image> images = new ArrayList<Image>();
		try {
			Document doc = Jsoup.connect(urls.get(category)+pageNum+".html")
							.timeout(3000)
							.get();
			Elements imgs = doc.getElementsByTag("img");
			Image image = null;
			for(Element img: imgs) {
				image = new Image();
				String shortUrl = img.attr("src");
				String title = img.attr("alt");
				if(shortUrl != null && !"".equals(shortUrl) && title != null && !"".equals(title)) {
					image.setShortUrl(shortUrl);
					image.setOriUrl(shortUrl);
					image.setTitle(title);
					images.add(image);
				}
			}		
		} catch (IOException e) {
			e.printStackTrace();
		}
		return images;
	}
}

































