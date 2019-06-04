package com.yhbc.base.common.utils;


/**
 * author : lfc
 * time   : 2018/09/11
 * esc   : 不同类型支付方式的判断类
 * version: 1.0
 */
public class PayModelUtil {

    /**
     * 自助现金,0,
     支付宝,1,
     人工现金,9,
     百度钱包,11,
     会员卡,20,
     微信支付,21,
     手Q钱包,31,
     电子支付,301,
     银行卡,1000,
     大众闪惠,1001,
     大众团购,1002,
     大众代金券,1004,
     实体卡,1003,
     百度外卖,1005,
     百度糯米,1006,
     美团团购,1007,
     代金券,1008,
     抵用券,1009,
     免单,1010,
     员工卡,1011,
     再惠,1012,
     我团,1013,
     校园卡,1014,
     优惠券,1015,
     兑换券,1016,
     混合支付,50,
     蚂蚁支付宝,5201,
     蚂蚁微信,5202,
     支付宝转账,401,
     微信转账,402,
     挂帐,4200,
     美团外卖,30101,
     饿了么外卖,30102,
     美团支付宝,6001,
     美团微信,6002,
     口碑支付宝,5000,
     扫呗微信,8002,
     */

    /**
     * 自助现金
     */
    public static final int PAY_TYPE_SELF_MONEY = 0;
    /**
     * 支付宝
     */
    public static final int PAY_TYPE_ALI_PAY = 1;
    /**
     * 现金
     */
    public static final int PAY_TYPE_MONEY = 9;
    /**
     * 挂单
     */
    public static final int PAY_TYPE_PRE_ORDER = 10;
    /**
     * 百度钱包
     */
    public static final int PAY_TYPE_BAIDU = 11;
    /**
     * 会员卡
     */
    public static final int PAY_TYPE_MEMBER = 20;
    /**
     * 微信支付
     */
    public static final int PAY_TYPE_WX = 21;
    /**
     * 手Q钱包
     */
    public static final int PAY_TYPE_QQ = 31;
    /**
     * 混合支付
     */
    public static final int PAY_TYPE_GROUP_PAY = 50;
    /**
     * 其他支付
     */
    public static final int PAY_TYPE_OTHER_PAY = 201;
    /**
     * 电子支付
     */
    public static final int PAY_TYPE_ELECTRON = 301;
    /**
     * 支付宝转账
     */
    public static final int PAY_TYPE_ALIPAY_TRANSFER = 401;
    /**
     * 微信转账
     */
    public static final int PAY_TYPE_WECHAT_TRANSFER = 402;
    /**
     * 银行卡
     */
    public static final int PAY_TYPE_BANK = 1000;
    /**
     * 大众闪惠
     */
    public static final int PAY_TYPE_COMMON_SHANHUI = 1001;
    /**
     * 大众团购
     */
    public static final int PAY_TYPE_COMMON_GROUP_BUYING = 1002;
    /**
     * 实体卡
     */
    public static final int PAY_TYPE_ENTITY_CARD = 1003;
    /**
     * 大众代金券
     */
    public static final int PAY_TYPE_COMMON_VOUCHERS = 1004;
    /**
     * 百度外卖
     */
    public static final int PAY_TYPE_BAIDU_TAKEAWAY = 1005;
    /**
     * 百度糯米
     */
    public static final int PAY_TYPE_BAIDU_NUOMI = 1006;
    /**
     * 美团团购
     */
    public static final int PAY_TYPE_MEITUAN_GROUP_BUYING = 1007;
    /**
     * 代金券
     */
    public static final int PAY_TYPE_VOUCHERS = 1008;
    /**
     * 抵用券
     */
    public static final int PAY_TYPE_E_COUPON = 1009;
    /**
     * 免单
     */
    public static final int PAY_TYPE_FREE_OF_CHARGE = 1010;
    /**
     * 员工卡
     */
    public static final int PAY_TYPE_EMPLOYEE_CARD = 1011;
    /**
     * 再惠
     */
    public static final int PAY_TYPE_AGIN_FAVORABLE = 1012;
    /**
     * 我团
     */
    public static final int PAY_TYPE_ME_ROLL = 1013;
    /**
     * 校园卡
     */
    public static final int PAY_TYPE_CAMPUS_CARD = 1014;
    /**
     * 优惠券
     */
    public static final int PAY_TYPE_COUPONS = 1015;
    /**
     * 兑换券
     */
    public static final int PAY_TYPE_CONVERSION_TICKET = 1016;
    /**
     * 口碑支付宝
     */
    public static final int PAY_TYPE_KOUBEI_ALI_PAY = 5000;
    /**
     * 蚂蚁支付宝
     */
    public static final int PAY_TYPE_ANT_ALIPAY = 5201;
    /**
     * 蚂蚁微信
     */
    public static final int PAY_TYPE_ANT_WECHAT = 5202;
    /**
     * 美团支付宝
     */
    public static final int PAY_TYPE_MEITUAN_ALI_PAY = 6001;
    /**
     * 美团微信
     */
    public static final int PAY_TYPE_MEITUAN_WX = 6002;
    /**
     * 扫呗微信
     */
    public static final int PAY_TYPE_SAOBEI_WEIXIN = 8002;
    /**
     * 美团外卖
     */
    public static final int PAY_TYPE_WAIMAI_MEITUAN = 30101;

    /**
     * 饿了吗外卖
     */
    public static final int PAY_TYPE_WAIMAI_ELEMA = 30102;

    /**
     * 是否是其他支付方式
     *
     * @param type 支付方式prop_value
     * @return true 是其他支付方式，false不是其他支付方式
     */
    public static boolean getifShowPayMode(int type) {
        if (type != 1 && type != 9 && type != 20 && type != 0 && type != 1000 && type != 21 && type != 301 && type != 50
                && type != PAY_TYPE_BAIDU && type != 31 && type != 6001 && type != 6002 && type != 5201 && type != 5202 && type != 8002) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 是否是不需要扫码的支付方式
     *
     * @param type 支付方式prop_value
     * @return true 不是需要扫码的支付方式，false是需要扫码的支付方式
     */
    public static boolean getIsNoScanPayMode(int type) {
        //301：所有电子支付，20：会员支付,其他按照现金支付来结算
        if (type != PAY_TYPE_ELECTRON && type != PAY_TYPE_MEMBER) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 判断是否是统一电子支付
     */
    public static boolean isUnityPay(int payModel) {
        switch (payModel) {
            case PayModelUtil.PAY_TYPE_ALI_PAY:
            case PAY_TYPE_BAIDU:
            case PAY_TYPE_WX:
            case PAY_TYPE_QQ:
            case PAY_TYPE_ANT_ALIPAY:
            case PAY_TYPE_ANT_WECHAT:
            case PAY_TYPE_MEITUAN_ALI_PAY:
            case PAY_TYPE_MEITUAN_WX:
            case PAY_TYPE_KOUBEI_ALI_PAY:
            case PAY_TYPE_SAOBEI_WEIXIN:
                return true;
            default:
                return false;
        }
    }

    /**
     * 是否是 会员支付 或者 电子支付
     * @param type 支付方式
     * @return
     */
    public static boolean isMemberOrElectronPay(int type) {
        return type == PayModelUtil.PAY_TYPE_MEMBER || type == PayModelUtil.PAY_TYPE_ELECTRON;
    }
}
