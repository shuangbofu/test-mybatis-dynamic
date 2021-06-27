package cn.shuangbofu.testmybatisdynamic.dao.base.handler;

import org.mybatis.dynamic.sql.update.UpdateDSL;
import org.mybatis.dynamic.sql.update.UpdateModel;

import java.util.function.Function;

public interface UpdateWhereHandler extends Function<UpdateDSL<UpdateModel>.UpdateWhereBuilder,UpdateDSL<UpdateModel>.UpdateWhereBuilder> {
}
