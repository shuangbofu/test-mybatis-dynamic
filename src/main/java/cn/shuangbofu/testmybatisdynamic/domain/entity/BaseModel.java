package cn.shuangbofu.testmybatisdynamic.domain.entity;

public interface BaseModel {
    Long getId();
    void setGmtModified(Long now);
    void setGmtCreate(Long now);
}
