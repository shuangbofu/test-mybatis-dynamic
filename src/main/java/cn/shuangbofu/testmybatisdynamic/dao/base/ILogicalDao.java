package cn.shuangbofu.testmybatisdynamic.dao.base;

import cn.shuangbofu.testmybatisdynamic.dao.base.handler.UpdateWhereHandler;
import cn.shuangbofu.testmybatisdynamic.dao.base.table.BaseTable;
import cn.shuangbofu.testmybatisdynamic.domain.entity.BaseModel;

public interface ILogicalDao<M extends BaseMapper<T>, T extends BaseModel, B extends BaseTable> extends IDao<M, T, B> {
    int logicalDeleteBy(UpdateWhereHandler updateWhereHandler);

    int logicalDeleteById(Long id);
}
