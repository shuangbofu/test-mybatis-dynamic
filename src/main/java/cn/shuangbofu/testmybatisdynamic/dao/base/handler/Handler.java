package cn.shuangbofu.testmybatisdynamic.dao.base.handler;

import cn.shuangbofu.testmybatisdynamic.dao.base.table.BaseTable;

public interface Handler<T extends BaseTable, P,R> {
    R handle(T t, P p);
}
