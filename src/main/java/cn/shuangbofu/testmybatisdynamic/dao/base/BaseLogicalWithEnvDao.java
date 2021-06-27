package cn.shuangbofu.testmybatisdynamic.dao.base;

import cn.shuangbofu.testmybatisdynamic.dao.base.handler.SelectWhereHandler;
import cn.shuangbofu.testmybatisdynamic.dao.base.handler.UpdateWhereHandler;
import cn.shuangbofu.testmybatisdynamic.dao.base.table.BaseTable;
import cn.shuangbofu.testmybatisdynamic.domain.entity.BaseModel;

import java.sql.JDBCType;

import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;

/**
 * 查询和更新增加环境字段
 *
 * @param <M>
 * @param <T>
 */
public abstract class BaseLogicalWithEnvDao<M extends BaseMapper<T>, T extends BaseModel, B extends BaseTable> extends BaseLogicalDao<M, T, B> {

    private final WhereSupplier<String> env;

    public BaseLogicalWithEnvDao(M mapper, B table) {
        super(mapper, table);
        env = new WhereSupplier<>(table.column("env", JDBCType.VARCHAR), () -> "dev");
    }

    @Override
    protected SelectWhereHandler getDefaultSelectWhereHandler() {
        return i -> super.getDefaultSelectWhereHandler().apply(i).and(env.getSqlColumn(), isEqualTo(env.getValue()));
    }

    @Override
    protected UpdateWhereHandler getDefaultUpdateWhereHandler() {
        return i -> super.getDefaultUpdateWhereHandler().apply(i).and(env.getSqlColumn(), isEqualTo(env.getValue()));
    }
}
