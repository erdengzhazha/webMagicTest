package processor;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;

import java.util.List;

public class CaiPiao500PageProcessor implements PageProcessor {

    public static final String URL_LIST = "http://blog\\.sina\\.com\\.cn/s/articlelist_1487828712_0_\\d+\\.html";

    public static final String URL_POST = "http://blog\\.sina\\.com\\.cn/s/blog_\\w+\\.html";

    // --------------------------------------- 部分一：抓取网站的相关配置，包括编码、抓取间隔、重试次数等  start -----------------------
    // 地点；位置；场所
    private Site site = Site
            //获取Site实例
            .me()
            /**
             * 设置域名
             */
            .setDomain("trade.500.com")
            /** 设置sleep时间*/
            .setSleepTime(3000)
            /**
             * User Agent中文名为用户代理，简称 UA，它是一个特殊字符串头，使得服务器能够识别客户使用的操作系统及版本、CPU 类型、浏览器及版本、浏览器渲染引擎、浏览器语言、浏览器插件等。
             *一些网站常常通过判断 UA 来给不同的操作系统、不同的浏览器发送不同的页面，因此可能造成某些页面无法在某个浏览器中正常显示，但通过伪装 UA 可以绕过检测。
             */
            .setUserAgent(
                    "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/85.0.4183.102 Safari/537.36");
    // --------------------------------------- 部分一：抓取网站的相关配置，包括编码、抓取间隔、重试次数等  end -----------------------

    // ---------------------------------------  process是定制爬虫逻辑的核心接口，在这里编写抽取逻辑 start -------------------
    public void process(Page page) {
        //列表页
//        if (page.getUrl().regex(URL_LIST).match()) {
//            //部分三：从页面发现后续的url地址来抓取
//            page.addTargetRequests(page.getHtml().xpath("//div[@class=\"articleList\"]").links().regex(URL_POST).all());
//            page.addTargetRequests(page.getHtml().links().regex(URL_LIST).all());
//            //文章页
//        } else {
//            // 部分二：定义如何抽取页面信息，并保存下来
//            page.putField("title", page.getHtml().xpath("//div[@class='articalTitle']/h2"));
//            page.putField("content", page.getHtml().xpath("//div[@id='articlebody']//div[@class='articalContent']"));
//            page.putField("date",
//                    page.getHtml().xpath("//div[@id='articlebody']//span[@class='time SG_txtc']").regex("\\((.*)\\)"));
//        }
        List<String> all = page.getHtml().xpath("//tr[@class='bet-tb-tr']").all();
        System.out.printf("总共有"+ all.size() + "条数据");
        for(String l : all){
            System.out.printf(l.toString());
        }
    }
    // ---------------------------------------  process是定制爬虫逻辑的核心接口，在这里编写抽取逻辑 end -------------------


    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        Spider.create(new CaiPiao500PageProcessor())
                //从哪里开始抓
                .addUrl("https://trade.500.com/jczq/")
                //开启几个线程抓取
                .thread(5)
                //启动爬虫
                .run();
    }
}