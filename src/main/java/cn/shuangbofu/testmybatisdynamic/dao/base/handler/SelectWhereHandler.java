package cn.shuangbofu.testmybatisdynamic.dao.base.handler;

import org.mybatis.dynamic.sql.select.QueryExpressionDSL;
import org.mybatis.dynamic.sql.select.SelectModel;

import java.util.function.Function;

public interface SelectWhereHandler extends Function<QueryExpressionDSL<SelectModel>.QueryExpressionWhereBuilder, QueryExpressionDSL<SelectModel>.QueryExpressionWhereBuilder> {
}
