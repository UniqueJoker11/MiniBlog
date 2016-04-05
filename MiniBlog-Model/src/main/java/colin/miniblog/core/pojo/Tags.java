package colin.miniblog.core.pojo;

import org.beetl.sql.core.annotatoin.AutoID;
import org.beetl.sql.core.annotatoin.Table;

/**
 * 创建人 LinQiang
 * 创建日期 2016/3/14
 * 项目名称 MiniBlog
 * 当前包名 colin.miniblog.core.pojo
 */
@Table(name="tags")
public class Tags {
  @AutoID
  private int tid;
  private String tname;
  private int aid;
  private int cid;

  public int getTid() {
    return tid;
  }

  public void setTid(int tid) {
    this.tid = tid;
  }

  public String getTname() {
    return tname;
  }

  public void setTname(String tname) {
    this.tname = tname;
  }

  public int getAid() {
    return aid;
  }

  public void setAid(int aid) {
    this.aid = aid;
  }

  public int getCid() {
    return cid;
  }

  public void setCid(int cid) {
    this.cid = cid;
  }
}
