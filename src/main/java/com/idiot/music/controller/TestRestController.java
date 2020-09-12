package com.idiot.music.controller;
/**
 * @auther idiot
 * @date 2019/12/13 - 15:14
 **/

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebRequest;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.util.Cookie;
import com.idiot.music.annotation.UserLoginToken;
import com.idiot.music.entity.Configs;
import com.idiot.music.service.ConfigsService;
import com.idiot.music.service.TestDataService;
import com.idiot.music.utils.AjaxResponse;
import com.idiot.music.utils.HttpRequest;
import com.idiot.music.utils.IdiotUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @ClassName:TestRestController
 * @Description:TODO
 * @Version:1.0
 **/
@RestController
@RequestMapping("/api")
public class TestRestController {
    @Autowired
    private TestDataService service;
    @Autowired
    private ConfigsService configsService;


    /**
     * 获取数据
     *
     * @return
     */
    @UserLoginToken
    @PostMapping("/getWebData")
    public Object musicUtil(@RequestBody JSONObject jsonParam) {
        String result = null;
        try {
            String url = jsonParam.getString("url");
            if (jsonParam.getString("url") == null) {
                return AjaxResponse.error("目标url地址为空！");
            }
            //System.out.println(url);
            JSONObject headerJson = jsonParam.getJSONObject("header");
            JSONObject paramsJson = jsonParam.getJSONObject("params");
            HashMap<String, String> header = IdiotUtils.jsonToHaMap(headerJson);
            HashMap<String, String> params = IdiotUtils.jsonToHaMap(paramsJson);
            result = HttpRequest.sendGet(url, header, params);
        } catch (Exception e) {
            return AjaxResponse.error("error");
        }
        if (result != null) {
            return AjaxResponse.success(result);
        }
        return AjaxResponse.error("无法获取到数据");
    }

    @RequestMapping(value = "/updateCookie")
    public Object upc(String cookies) {
        Pattern p = Pattern.compile("\\s*|\t|\r|\n");
        Matcher m = p.matcher(cookies);
        String cookieStr = m.replaceAll("");
        configsService.deleteByMark("cookies");
        configsService.save(new Configs("cookies", cookieStr));
        return AjaxResponse.success(configsService.findByMark("cookies"));
    }

    @UserLoginToken
    @RequestMapping(value = "/qqq")
    public Object qqq() throws IOException, InterruptedException {
        WebClient webClient = new WebClient(BrowserVersion.CHROME);
        // 等待后台 JS 执行
        webClient.waitForBackgroundJavaScript(10000);
        webClient.getOptions().setThrowExceptionOnScriptError(false);//当JS执行出错的时候是否抛出异常, 这里选择不需要
        webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);//当HTTP的状态非200时是否抛出异常, 这里选择不需要
        webClient.getOptions().setActiveXNative(false);
        webClient.getOptions().setCssEnabled(false);//是否启用CSS, 因为不需要展现页面, 所以不需要启用
        webClient.getOptions().setJavaScriptEnabled(true); //很重要，启用JS
        webClient.setAjaxController(new NicelyResynchronizingAjaxController());//很重要，设置支持AJAX

        HtmlPage page = webClient.getPage("https://graph.qq.com/oauth2.0/show?which=Login&display=pc&response_type=code" +
                "&client_id=100497308&redirect_uri=https%3A%2F%2Fy.qq.com%2Fportal%2Fwx_redirect.html%3Flogin_type%3D1%26surl%" +
                "3Dhttps%253A%252F%252Fy.qq.com%252F%26use_customer_cb%3D0&state=state&display=pc");
        //DomElement loginFrame = page.getElementById("login_frame");
        // page = webClient.getPage(loginFrame.getAttribute("src"));

        // page.getElementById("switcher_plogin").click();

        // 获取用户名和密码 Input 并输入
       /* DomElement usernameInput = null;
        DomElement passwordInput = null;
        for (int i = 0; i < 20; i++) {
            usernameInput = page.getElementById("u");
            if (usernameInput != null) {
                break;
            }
            synchronized (page) {
                page.wait(500);
            }
        }
        passwordInput = page.getElementById("p");
        usernameInput.setAttribute("value", "1195188852");
        passwordInput.setAttribute("value", "idiot.19990229");

        // 获取并点击登陆按钮
        DomElement loginBtn = page.getElementById("login_button");
        loginBtn.click();

        // 获取 Cookie
        Set<Cookie> cookies = webClient.getCookieManager().getCookies();
        for (Cookie cookie : cookies) {
            System.out.println(cookie.getName() + ": " + cookie.getValue());
        }*/
        return page;
    }
 /*   @RequestMapping("/getMusic")
    public Object getMusic(String songId, String quality) {
        //M50普通 M80高
        HashMap<String, String> header = new HashMap<>();
        //header.put("Cookie",configsService.findByMark("cookies").getData());
        String loginUin = "1195188852";
        String sign = "zza358jisf8lc1e6e6d95a3fe10dc31e0b25ed1dd6dee2d";
        String guid = "2273141038";
        String url = "https://u.y.qq.com/cgi-bin/musics.fcg?sign=" +
                sign + "&loginUin=" +
                loginUin +
                "&format=json&inCharset=utf8&outCharset=utf-8&data=" +
                "%7B%22req%22%3A%7B%22module%22%3A%22CDN.SrfCdnDispatchServer%22%2C%22" +
                "method%22%3A%22GetCdnDispatch%22%2C%22param%22%3A%7B%22guid%22%3A%22" + guid +
                "%22%2C%22calltype%22%3A0%2C%22userip%22%3A%22%22%7D%7D%2C%22req_0%22%3A%7B%22module%22%3A%22" +
                "vkey.GetVkeyServer%22%2C%22method%22%3A%22CgiGetVkey%22%2C%22param%22%3A%7B%22guid%22%3A%22" + guid +
                "%22%2C%22songmid%22%3A%5B%22" + songId +
                "%22%5D%2C%22songtype%22%3A%5B0%5D%2C%22uin%22%3A%22" + loginUin +
                "%22%2C%22loginflag%22%3A1%2C%22platform" +
                "%22%3A%2220%22%7D%7D%2C%22comm%22%3A%7B%22uin%22%3A" + loginUin +
                "%2C%22format%22%3A%22json%22%2C%22ct%22%3A24%2C%22cv%22%3A0%7D%7D";
        String result = HttpRequest.sendGet(url,
                header, null);
        //System.out.println(url);
        //JSONObject jsonObject = JSONObject.parseObject(result);
        //JSONArray jsonArray = jsonObject.getJSONObject("req_0").getJSONObject("data").getJSONArray("midurlinfo");
        // String purl = jsonObject.getJSONObject("req_0").getJSONObject("data").getJSONArray("midurlinfo").getJSONObject(0).getString("purl");
        //return purl;
        return AjaxResponse.success(result);
    }*/

    @UserLoginToken
    @RequestMapping("/getMusic")
    public Object getMusic(@RequestBody JSONObject jsonParam) {
        String result = null;
        try {
            String url = jsonParam.getString("url");
            if (jsonParam.getString("url") == null) {
                return AjaxResponse.error("目标url地址为空！");
            }
            //System.out.println(url);
            JSONObject headerJson = jsonParam.getJSONObject("header");
            JSONObject paramsJson = jsonParam.getJSONObject("params");
            HashMap<String, String> header;
            if (headerJson == null) {
                header = new LinkedHashMap<>();
            } else {
                header = IdiotUtils.jsonToHaMap(headerJson);
            }
            String cookie = configsService.findByMark("cookies").getData();
            if (cookie != null) {
                header.put("Cookie", cookie);
            }
            HashMap<String, String> params = IdiotUtils.jsonToHaMap(paramsJson);
            result = HttpRequest.sendGet(url, header, params);
            if (result != null) {
                JSONObject jsonObject = JSONObject.parseObject(result);
                JSONObject data = jsonObject.getJSONObject("req_0").getJSONObject("data");
                jsonObject=null;
                JSONArray midurlinfo = data.getJSONArray("midurlinfo");
                JSONArray sip = data.getJSONArray("sip");
                String purl = midurlinfo.getJSONObject(0).getString("purl");
                String adr = sip.getString(0);
                midurlinfo=null;
                sip=null;
                if(purl.indexOf("mp3")>0){
                    //更新cookie
                }
                //System.out.println("====");
               // System.out.println(result);
                if(purl==null||purl.equals("\"\"")||purl.equals("")){
                    //更新cookie
                    System.out.println("???");
                    return AjaxResponse.error("vip缓存失效 请等待更新");
                }
                return AjaxResponse.success(adr+purl);
            }
        } catch (Exception e) {
            return AjaxResponse.error("error");
        }

        return AjaxResponse.error("无法获取到数据");
    }

    @UserLoginToken
    @RequestMapping("/getVipMusic")
    public Object getVipMusic(String songId) throws MalformedURLException {
        //System.out.println(songId);
        final WebClient webClient = new WebClient();//新建一个模拟谷歌Chrome浏览器的浏览器客户端对象
        URL link = new URL("https://i.y.qq.com/v8/playsong.html?ADTAG=newyqq.song&songmid=" + songId + "#webchat_redirect");
        WebRequest request = new WebRequest(link);
        ////设置请求报文头里的User-Agent字段
        request.setAdditionalHeader("User-Agent", "Mozilla/5.0 (Linux; U; Android 8.1.0; zh-cn; BLA-AL00 Build/HUAWEIBLA-AL00) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/57.0.2987.132 MQQBrowser/8.9 Mobile Safari/537.36");
        //wc.addRequestHeader("User-Agent", "Mozilla/5.0 (Windows NT 5.1; rv:6.0.2) Gecko/20100101 Firefox/6.0.2");
        //wc.addRequestHeader和request.setAdditionalHeader功能应该是一样的。选择一个即可。
        webClient.getOptions().setThrowExceptionOnScriptError(false);//当JS执行出错的时候是否抛出异常
        webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);//当HTTP的状态非200时是否抛出异常
        webClient.getOptions().setActiveXNative(false);
        webClient.getOptions().setCssEnabled(false);//是否启用CSS
        webClient.getOptions().setJavaScriptEnabled(true); //启用JS
        webClient.getOptions().setDoNotTrackEnabled(false);
        webClient.getOptions().setUseInsecureSSL(true);// 接受任何主机连接 无论是否有有效证书
        webClient.waitForBackgroundJavaScript(10000);//异步JS执行需要耗时,所以这里线程要阻塞30秒,等待异步JS执行结束
        webClient.setAjaxController(new NicelyResynchronizingAjaxController());//很重要，设置支持AJAX

        webClient.getCookieManager().setCookiesEnabled(true);//开启cookie管理

        String[] strArr = configsService.findByMark("cookies").getData().split(";");
        for (int i = 0; i < strArr.length; ++i) {
            String key = strArr[i].substring(0, strArr[i].indexOf("="));
            String data = strArr[i].substring(strArr[i].indexOf("=") + 1);
            webClient.getCookieManager().addCookie(new Cookie("i.y.qq.com", key, data));
        }

        HtmlPage page = null;
        try {
            page = webClient.getPage(request);//加载上网页
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            webClient.close();
        }
        String pageXml = page.asXml();//直接将加载完成的页面转换成xml格式的字符串
        Element body = Jsoup.parse(pageXml).body();//获取html文档
        pageXml = null;
        Elements scripts = body.select("script");
        body = null;

        String result = null;
        for (Element script : scripts) {
            if (script.html().indexOf("window.songlist") != -1) {
                result = script.html();
            }
        }
        if (result != null) {
            int s = 0;
            int d = 0;
            try {
                s = result.indexOf("[{\"songid") - 1;
                d = result.indexOf("\"}];") + 3;

            } catch (Exception e) {
                return AjaxResponse.error();
            }
            if (s == 0 || d == 0 || s > d) {
                return AjaxResponse.error();
            }
            return AjaxResponse.success(result.substring(s, d));
        }
        return AjaxResponse.error();

//        int s=0;
//        int d=0;
//        String rough="";
//        try {
//            rough = pageXml.substring(29300, 36300);
//            s = rough.indexOf("[{\"songid") - 1;
//            d = rough.indexOf("\"}];") + 3;
//
//        }catch (Exception e){
//            return AjaxResponse.error();
//        }
//        System.out.println("s=" + s);
//        System.out.println("d=" + d);
//        return AjaxResponse.success(rough.substring(s, d));
    }

//    @PostMapping(value = "/test")
//    public AjaxResponse saveArticle(@RequestBody TestData data) {
//        try {
//            service.save(data);
//            return AjaxResponse.success("保存成功");
//        } catch (Exception e) {
//            return AjaxResponse.error(e.getMessage());
//        }
//    }
//
//    @DeleteMapping(value = "/test/{id}")
//    public AjaxResponse deleteUser(@PathVariable Integer id) {
//        try {
//            service.deleteById(id);
//            return AjaxResponse.success("删除成功");
//        } catch (Exception e) {
//            return AjaxResponse.error(e.getMessage());
//        }
//    }
//
//    @PutMapping(value = "/test/{id}")
//    public AjaxResponse updateUser(@PathVariable Integer id, @RequestBody TestData data) {
//        try {
//            TestData testData = service.findById(id);
//            BeanUtils.copyProperties(data, testData, "id");
//            service.save(testData);
//            return AjaxResponse.success();
//        } catch (Exception e) {
//            return AjaxResponse.error(e.getMessage());
//        }
//    }
//
//    @GetMapping(value = "/test/{id}")
//    public AjaxResponse getUser(@PathVariable Integer id) {
//        try {
//            return AjaxResponse.success(service.findById(id));
//        } catch (Exception e) {
//            return AjaxResponse.error(e.getMessage());
//        }
//    }

}
