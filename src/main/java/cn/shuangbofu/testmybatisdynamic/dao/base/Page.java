package cn.shuangbofu.testmybatisdynamic.dao.base;

import cn.shuangbofu.testmybatisdynamic.domain.entity.BaseModel;
import lombok.Data;

import java.util.List;

@Data
public class Page<T extends BaseModel> {
    private long pageNum;
    private long pageSize;
    private long total;
    private List<T> list;

    public Page() {
    }

    private Page(long pageNum, long pageSize, long total, List<T> list) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.total = total;
        this.list = list;
    }

    public static <T extends BaseModel> Page<T> of(long pageNum, long pageSize, long total, List<T> list) {
        return new Page<>(pageNum, pageSize, total, list);
    }
}
