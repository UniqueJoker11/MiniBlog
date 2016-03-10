package colin.miniblog.core.pojo;

import org.beetl.sql.core.annotatoin.AutoID;
import org.beetl.sql.core.annotatoin.Table;

import java.sql.Timestamp;

/**
 * 创建人 LinQiang
 * 创建日期 2016/3/10
 * 项目名称 MiniBlog
 * 当前包名 colin.miniblog.core.pojo
 */
@Table(name = "user_info")
public class UserInfo {

  @AutoID
  private int id;
  private String username;
  private String pwd;
  private String nickname;
  private String sign;
  /**
   * 头像
   */
  private String face;
  /**
   * 金币
   */
  private int coin;
  private int sex;
  private int cur_area;
  private int email_validate;
  private int role;
  private int is_del;
  private int daren_tree;
  private Timestamp mtime;

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

  public String getPwd() {
    return pwd;
  }

  public void setPwd(String pwd) {
    this.pwd = pwd;
  }

  public String getNickname() {
    return nickname;
  }

  public void setNickname(String nickname) {
    this.nickname = nickname;
  }

  public String getSign() {
    return sign;
  }

  public void setSign(String sign) {
    this.sign = sign;
  }

  public String getFace() {
    return face;
  }

  public void setFace(String face) {
    this.face = face;
  }

  public int getCoin() {
    return coin;
  }

  public void setCoin(int coin) {
    this.coin = coin;
  }

  public int getSex() {
    return sex;
  }

  public void setSex(int sex) {
    this.sex = sex;
  }

  public int getCur_area() {
    return cur_area;
  }

  public void setCur_area(int cur_area) {
    this.cur_area = cur_area;
  }

  public int getEmail_validate() {
    return email_validate;
  }

  public void setEmail_validate(int email_validate) {
    this.email_validate = email_validate;
  }

  public int getRole() {
    return role;
  }

  public void setRole(int role) {
    this.role = role;
  }

  public int getIs_del() {
    return is_del;
  }

  public void setIs_del(int is_del) {
    this.is_del = is_del;
  }

  public int getDaren_tree() {
    return daren_tree;
  }

  public void setDaren_tree(int daren_tree) {
    this.daren_tree = daren_tree;
  }

  public Timestamp getMtime() {
    return mtime;
  }

  public void setMtime(Timestamp mtime) {
    this.mtime = mtime;
  }
}
