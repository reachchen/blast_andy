package geendao.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;

/**
 * Created by Alan on 2015/12/15.
 * 用户表
 */
@Entity(nameInDb = "com_yhbc_tablet_bean_UserInfo")
public class UserInfo   {

    /**
     * 主键自增 uid
     */

    @Id
    private Long uid;

    /**
     * 店铺 id
     */
    @Property(nameInDb = "id")
    private int id;

    /**
     * 用户名username
     */
    @Property(nameInDb = "username")
    private String username;
    /**
     * 密码 password
     */
    @Property(nameInDb = "password")
    private String password;
    /**
     * 订单堂食序号 index1
     */
    @Property(nameInDb = "index1")
    private int index1;
    /**
     * 订单外带序号 index2
     */
    @Property(nameInDb = "index2")
    private int index2;
    /**
     *  日期
     */
    @Property(nameInDb = "date1")
    private String date1;

    @Property(nameInDb = "big_logo")
    private String big_logo;

    @Property(nameInDb = "min_logo")
    private String min_logo;
    /**
     * 用户昵称 nick
     */
    @Property(nameInDb = "nick")
    private String nick;
    /**
     * 店铺地址 address
     */
    @Property(nameInDb = "address")
    private String address;

    /**
     * 主店铺Id
     */
    @Property(nameInDb = "parentId")
    private Long parentId;

    /**
     * 支付宝用户Id
     */
    @Property(nameInDb = "alipayUserId")
    private String alipayUserId;

    /**
     *
     */
    @Property(nameInDb = "isActive")
    private Integer isActive;

    /**
     * 手机号
     */
    @Property(nameInDb = "contact_phone")
    private String contact_phone;





    public UserInfo(int id, String username, String password, String nick, Long parentId,
                    String alipayUserId, Integer isActive, String contact_phone) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.nick = nick;
        this.parentId = parentId;
        this.alipayUserId = alipayUserId;
        this.isActive = isActive;
        this.contact_phone = contact_phone;
    }



    @Generated(hash = 145842535)
    public UserInfo(Long uid, int id, String username, String password, int index1,
            int index2, String date1, String big_logo, String min_logo, String nick,
            String address, Long parentId, String alipayUserId, Integer isActive,
            String contact_phone) {
        this.uid = uid;
        this.id = id;
        this.username = username;
        this.password = password;
        this.index1 = index1;
        this.index2 = index2;
        this.date1 = date1;
        this.big_logo = big_logo;
        this.min_logo = min_logo;
        this.nick = nick;
        this.address = address;
        this.parentId = parentId;
        this.alipayUserId = alipayUserId;
        this.isActive = isActive;
        this.contact_phone = contact_phone;
    }



    @Generated(hash = 1279772520)
    public UserInfo() {
    }



    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getIndex1() {
        return index1;
    }

    public void setIndex1(int index1) {
        this.index1 = index1;
    }

    public int getIndex2() {
        return index2;
    }

    public void setIndex2(int index2) {
        this.index2 = index2;
    }

    public String getDate1() {
        return date1;
    }

    public void setDate1(String date1) {
        this.date1 = date1;
    }

    public String getBig_logo() {
        return big_logo;
    }

    public void setBig_logo(String big_logo) {
        this.big_logo = big_logo;
    }

    public String getMin_logo() {
        return min_logo;
    }

    public void setMin_logo(String min_logo) {
        this.min_logo = min_logo;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getAlipayUserId() {
        return alipayUserId;
    }

    public void setAlipayUserId(String alipayUserId) {
        this.alipayUserId = alipayUserId;
    }

    public Integer getIsActive() {
        return isActive;
    }

    public void setIsActive(Integer isActive) {
        this.isActive = isActive;
    }

    public String getContact_phone() {
        return contact_phone;
    }

    public void setContact_phone(String contact_phone) {
        this.contact_phone = contact_phone;
    }
}
