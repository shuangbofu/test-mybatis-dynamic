package cn.shuangbofu.testmybatisdynamic.dao.base.handler;

import cn.shuangbofu.testmybatisdynamic.dao.base.table.BaseTable;
import org.mybatis.dynamic.sql.update.UpdateDSL;
import org.mybatis.dynamic.sql.update.UpdateModel;
import org.mybatis.dynamic.sql.util.Buildable;

import java.util.function.BiFunction;

public interface UpdateHandler<T extends BaseTable> extends BiFunction<T, UpdateDSL<UpdateModel>, Buildable<UpdateModel>> {
}
