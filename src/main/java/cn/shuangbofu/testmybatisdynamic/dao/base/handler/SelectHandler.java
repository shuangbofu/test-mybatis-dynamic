package cn.shuangbofu.testmybatisdynamic.dao.base.handler;

import cn.shuangbofu.testmybatisdynamic.dao.base.table.BaseTable;
import org.mybatis.dynamic.sql.select.QueryExpressionDSL;
import org.mybatis.dynamic.sql.select.SelectModel;
import org.mybatis.dynamic.sql.util.Buildable;

import java.util.function.BiFunction;

public interface SelectHandler<T extends BaseTable> extends BiFunction<T, QueryExpressionDSL<SelectModel>.QueryExpressionWhereBuilder, Buildable<SelectModel>> {
}
