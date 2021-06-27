package cn.shuangbofu.testmybatisdynamic.dao.base;

import cn.shuangbofu.testmybatisdynamic.dao.base.handler.SelectWhereHandler;
import cn.shuangbofu.testmybatisdynamic.dao.base.handler.UpdateWhereHandler;
import cn.shuangbofu.testmybatisdynamic.dao.base.table.BaseTable;
import cn.shuangbofu.testmybatisdynamic.domain.entity.BaseModel;

import java.sql.JDBCType;

import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;

/**
 * 增加逻辑删除
 * 更新和查询增加逻辑删除字段
 *
 * @param <M>
 * @param <T>
 */
public abstract class BaseLogicalDao<M extends BaseMapper<T>, T extends BaseModel, B extends BaseTable> extends BaseDao<M, T, B> implements ILogicalDao<M, T, B> {

    private final WhereSupplier<Boolean> logical;

    public BaseLogicalDao(M mapper, B table) {
        super(mapper, table);
        logical = new WhereSupplier<>(table.column("status", JDBCType.TINYINT), () -> true);
    }

    @Override
    protected SelectWhereHandler getDefaultSelectWhereHandler() {
        return i -> i.and(logical.getSqlColumn(), isEqualTo(logical.getValue()));
    }

    @Override
    protected UpdateWhereHandler getDefaultUpdateWhereHandler() {
        return i -> i.and(logical.getSqlColumn(), isEqualTo(logical.getValue()));
    }

    @Override
    public int logicalDeleteBy(UpdateWhereHandler updateWhereHandler) {
        return updateBy((t, i) -> updateWhereHandler.apply(i.set(logical.getSqlColumn()).equalTo(!logical.getValue().get()).where()));
    }

    @Override
    public int logicalDeleteById(Long id) {
        return logicalDeleteBy(i -> i.and(idSqlColumn, isEqualTo(id)));
    }
}
