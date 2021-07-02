package cn.shuangbofu.testmybatisdynamic.dao.base.handler;

import cn.shuangbofu.testmybatisdynamic.dao.base.table.BaseTable;
import org.mybatis.dynamic.sql.delete.DeleteDSL;
import org.mybatis.dynamic.sql.delete.DeleteModel;

import java.util.function.BiFunction;

public interface DeleteWhereHandler<B extends BaseTable> extends BiFunction<B, DeleteDSL<DeleteModel>.DeleteWhereBuilder, DeleteDSL<DeleteModel>.DeleteWhereBuilder> {
}
