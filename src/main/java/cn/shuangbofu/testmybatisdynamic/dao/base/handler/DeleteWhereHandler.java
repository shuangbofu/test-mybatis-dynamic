package cn.shuangbofu.testmybatisdynamic.dao.base.handler;

import cn.shuangbofu.testmybatisdynamic.dao.base.table.BaseTable;
import org.mybatis.dynamic.sql.delete.DeleteDSL;
import org.mybatis.dynamic.sql.delete.DeleteModel;

public interface DeleteWhereHandler<B extends BaseTable> extends Handler<B, DeleteDSL<DeleteModel>.DeleteWhereBuilder, DeleteDSL<DeleteModel>.DeleteWhereBuilder> {
}
