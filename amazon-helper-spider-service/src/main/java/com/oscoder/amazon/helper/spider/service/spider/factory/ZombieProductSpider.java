package com.oscoder.amazon.helper.spider.service.spider.factory;

import com.oscoder.amazon.helper.spider.service.spider.processor.AmazonProcessor;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Spider;

/**
 * @author
 * @create 2021-02-21 12:57 PM
 **/
@Component
public class ZombieProductSpider {
	public static void main(String[] args) {
		Spider.create(new AmazonProcessor()).addUrl("https://github.com/code4craft").thread(5).run();
	}
}
