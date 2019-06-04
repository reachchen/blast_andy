package com.yhbc.base.common.utils;

public class AppConfig {
    public static String APP_IMAGE_CACHE = "imageCache";
    public static String msgprintTitle;
    //SplashActivity是否结束
    public static boolean contextFlag = false;
    //MianActivity 判断是否退出
    public static boolean exitFlag = false;
    public static boolean imprestSign = false;
    public static boolean scanstart = false;
    //扫码扫码轮循累计空订单；
    public static int getScanOrderRound = 0;
    //订单上传开关
    public static boolean updataOrderFlag = true;
    //添加数据上传
    public static boolean updataAddDataFlag = true;
    //是否打印后厨订单金额订单
    public static boolean isPrintPrice = false;
    //退菜原因,默认开启
    public static int backReason = 1;
    //退菜需要密码
    public static int backPassWord = 1;
    public static int TAKE_SCREEN_MACHINE_TYPE = 6;

    /**
     * -----------系统设置:双屏设置:------------------------
     */
    public static boolean isViceScreenShow = false;//显示副屏开关
    public static int viceScreenSetting = 0;//显示副屏
    public static boolean isViceScreenShowFirst = false;//开启副屏标记

    public static String MAILNAME = "service@blibao.cn";
    public static String MAILPASSWORD = "hjjeYWmtTfJ2VrBZ";

    //会员
    public static String CUSTOMCOMMONCODE = "yCt6bSNhbcYVIg";
    public static String CUSTOMSPECIALCODE = "*#06#8e";
    public static String CUSTOMPUBLICKEY = "8er45fUfD15545qwwt23G";

    public static String cloud_front = "http://cloud.blibao.com/server/";

    /**
     * ------------------------------云端------------------------------
     */

    //会员
    public static String CUSTOMURL = "http://i.blibao.com/";
    //支付宝支付1
    public static String alipay_url1 = "http://pay.blibao.com/alipay_service/";
    //支付宝支付2
    public static String alipay_url2 = "http://pay.blibao.com/alipay2_service/";
    //百度支付
    public static String BAIFUBAOURL = "http://pay.blibao.com/baidu_service/";
    //微信支付
    public static String WEIXINPAY = "http://pay.blibao.com/wechat_service/";
    //QQ支付
    public static String TENCENTPAY = "http://pay.blibao.com/tenpay_service/";
    //聚合支付收款
    public static String AGGREGATE_PAY = "http://myblank.payment.blibao.com/payment/1/mybank/pay.htm";
    //退款查询订单接口
    public static String SEARCH_REFUND = "https://statis.blibao.com/ajax/jyf_getAlipayTradeRecord.htm";
    public static String findOrder = "https://statis.blibao.com/ajax/findAlipayOrderByOrderNum.htm";
    // 交接班员工收银统计接口
    public static String Employee_shifting_duty = "https://statis.blibao.com/ajax/turnoverStatisticsByEmployee2.htm";

    /**
     * ----------------------------云端完结----------------------------
     */
    //打印异常上传接口
    public static String upload_print_exception = "http://upload.blibao.com/ajax/uploadExceptionInfo.htm";

    //同步餐台数据

    //注册加密参数
    public static String commonCode = "yCt6bSNhbcYVIg";
    public static String publicKey = "8er45fUfD15545qwwt23G";
    public static String specialCode = "*#06#8e";


    public static String CREATETABLE = "http://b2b.blibao.com/sweep/alipayWxAuth/auth.htm";

    //会员注册
    public static final String REGISTER_BY_PHYSICAL_CAR = AppConfig.CUSTOMURL + "interface/registerByPhysicalCard.htm";


}
