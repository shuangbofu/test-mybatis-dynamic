package cn.shuangbofu.testmybatisdynamic.dao.base.handler;

import cn.shuangbofu.testmybatisdynamic.dao.base.table.BaseTable;
import org.mybatis.dynamic.sql.select.QueryExpressionDSL;
import org.mybatis.dynamic.sql.select.SelectModel;
import org.mybatis.dynamic.sql.util.Buildable;

public interface SelectHandler<T extends BaseTable> extends Handler<T, QueryExpressionDSL<SelectModel>.QueryExpressionWhereBuilder, Buildable<SelectModel>> {
}