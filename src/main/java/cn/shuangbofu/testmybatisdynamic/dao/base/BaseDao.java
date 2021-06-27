package cn.shuangbofu.testmybatisdynamic.dao.base;

import cn.shuangbofu.testmybatisdynamic.dao.base.handler.*;
import cn.shuangbofu.testmybatisdynamic.dao.base.table.BaseTable;
import cn.shuangbofu.testmybatisdynamic.domain.entity.BaseModel;
import cn.shuangbofu.testmybatisdynamic.trash.PropertyFunc;
import cn.shuangbofu.testmybatisdynamic.trash.ReflectionUtils;
import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.mybatis.dynamic.sql.BasicColumn;
import org.mybatis.dynamic.sql.SqlBuilder;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.delete.DeleteModel;
import org.mybatis.dynamic.sql.delete.render.DeleteStatementProvider;
import org.mybatis.dynamic.sql.insert.BatchInsertDSL;
import org.mybatis.dynamic.sql.insert.BatchInsertModel;
import org.mybatis.dynamic.sql.insert.InsertDSL;
import org.mybatis.dynamic.sql.insert.InsertModel;
import org.mybatis.dynamic.sql.insert.render.InsertStatementProvider;
import org.mybatis.dynamic.sql.render.RenderingStrategies;
import org.mybatis.dynamic.sql.render.RenderingStrategy;
import org.mybatis.dynamic.sql.select.QueryExpressionDSL;
import org.mybatis.dynamic.sql.select.SelectModel;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.mybatis.dynamic.sql.update.UpdateDSL;
import org.mybatis.dynamic.sql.update.UpdateModel;
import org.mybatis.dynamic.sql.update.render.UpdateStatementProvider;
import org.mybatis.dynamic.sql.util.Buildable;
import org.mybatis.dynamic.sql.util.StringUtilities;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;

import static org.mybatis.dynamic.sql.SqlBuilder.*;

/**
 * 1、from table
 * 2、render
 * 3、查询和更新的全局where
 * 4、columns为空时补为allColumns
 * 5、selectOne避免报错强制limit 1
 * 6、orderBy默认id
 * 7、insert mapping映射匹配
 * <p>
 * <p>
 * 8、常用方法
 * countBy, countAll
 * selectOneBy, selectOneById, selectOneValueBy
 * selectManyBy, selectManyInIdsAndBy, selectManyInIds, selectAll
 * selectPageBy, selectPage
 * updateBy, updateById,
 * deleteBy, deleteById
 * insert, insertBatch
 *
 * @param <M>
 * @param <T>
 */
public abstract class BaseDao<M extends BaseMapper<T>, T extends BaseModel, B extends BaseTable> implements IDao<M, T, B> {

    protected final SqlColumn<Long> idSqlColumn;
    protected M mapper;
    protected B table;

    public BaseDao(M mapper, B table) {
        this.mapper = mapper;
        this.table = table;
        idSqlColumn = table.getIdSqlColumn();
    }

    /**
     * 渲染策略
     *
     * @return
     */
    protected RenderingStrategy getRenderingStrategy() {
        return RenderingStrategies.MYBATIS3;
    }

    /**
     * select时全局where条件
     *
     * @return
     */
    protected SelectWhereHandler getDefaultSelectWhereHandler() {
        return builder -> builder;
    }

    /**
     * update时全局where条件
     *
     * @return
     */
    protected UpdateWhereHandler getDefaultUpdateWhereHandler() {
        return updateWhereBuilder -> updateWhereBuilder;
    }

    protected SelectWhereHandler getFinalSelectWhereHandler(SelectHandler<B> handler) {
        return (builder) -> {
            handler.handle(table, builder);
            builder.orderBy(idSqlColumn);
            return Optional.ofNullable(getDefaultSelectWhereHandler()).orElse(i -> i).apply(builder);
        };
    }

    private QueryExpressionDSL.FromGatherer<SelectModel> select0(BasicColumn... selectList) {
        // columns没选择为*
        if (selectList.length == 0) {
            return select(Lists.newArrayList(table.getAllColumns()));
        } else {
            return select(selectList);
        }
    }

    @Override
    public Optional<T> selectOneBy(SelectHandler<B> handler, BasicColumn... selectList) {
        Buildable<SelectModel> buildable = getFinalSelectWhereHandler((table, i) -> {
            handler.handle(table, i);
            // select one 强制增加limit 1
            i.limit(1);
            return i;
        }).apply((select0(selectList).from(table).where()));
        return mapper.selectOne(renderSelect(buildable));
    }

    @Override
    public long countBy(SelectHandler<B> handler) {
        Long count = mapper.count(renderSelect(getFinalSelectWhereHandler(handler).apply(select(count(idSqlColumn)).from(table).where())));
        return Optional.of(count).orElse(0L);
    }

    @Override
    public long countAll() {
        return countBy((table, i) -> i);
    }

    @Override
    public <V> Optional<V> selectOneValueBy(SelectHandler<B> handler, PropertyFunc<T, V> function) {
        String fieldName = ReflectionUtils.getFieldName(function);
        String sqlFieldName = ReflectionUtils.HumpToUnderline(fieldName);
        return selectOneBy(handler, table.column(sqlFieldName)).map(function);
    }

    @Override
    public List<T> selectManyBy(SelectHandler<B> handler, BasicColumn... selectList) {
        return mapper.selectMany(renderSelect(getFinalSelectWhereHandler(handler).apply(select0(selectList).from(table).where())));
    }

    @Override
    public List<T> selectAll(BasicColumn... selectList) {
        return selectManyBy((table, i) -> i, selectList);
    }

    @Override
    public Optional<T> selectOneById(Long id, BasicColumn... selectList) {
        return selectOneBy((table, i) -> i.and(idSqlColumn, isEqualTo(id)), selectList);
    }

    @Override
    public List<T> selectManyInIds(List<Long> ids, BasicColumn... selectList) {
        return selectManyInIdsAndBy((table, i) -> i, ids, selectList);
    }

    @Override
    public List<T> selectManyInIdsAndBy(SelectHandler<B> handler, List<Long> ids, BasicColumn... selectList) {
        return selectManyBy((table, i) -> handler.handle(table, i.and(idSqlColumn, isIn(ids))), selectList);
    }

    @Override
    public Page<T> selectPageBy(SelectHandler<B> handler, int pageNum, int pageSize, BasicColumn... selectList) {
        long count = countBy(handler);
        List<T> list = Lists.newArrayList();
        if (count > 0) {
            SelectHandler<B> selectHandler = (t, i) -> {
                handler.handle(t, i);
                i.limit(Math.max(pageSize, 1)).offset(Math.max(pageNum - 1, 0));
                return i;
            };
            list = selectManyBy(selectHandler);
        }
        return Page.of(pageNum, pageSize, count, list);
    }

    @Override
    public Page<T> selectPage(int pageNum, int pageSize, BasicColumn... selectList) {
        return selectPageBy((a, i) -> i, pageNum, pageSize, selectList);
    }

    @Override
    public int updateBy(UpdateHandler<B> handler) {
        UpdateDSL<UpdateModel> update = SqlBuilder.update(table);
        Function<UpdateDSL<UpdateModel>, UpdateDSL<UpdateModel>> function = dsl -> {
            long now = System.currentTimeMillis();
            dsl.set(table.getGmtCreateSqlColumn()).equalTo(now).set(table.getGmtModifiedSqlColumn()).equalTo(now);
            handler.handle(table, dsl);
            return dsl;
        };
        return mapper.update(renderUpdate(Optional.ofNullable(getDefaultUpdateWhereHandler()).orElse(i -> i)
                .apply(function.apply(update).where())));
    }

    @Override
    public int updateById(Long id, UpdateHandler<B> handler) {
        UpdateHandler<B> h = (t, i) -> {
            i.where(idSqlColumn, isEqualTo(id));
            return handler.handle(t, i);
        };
        return updateBy(h);
    }

    @Override
    public int deleteBy(DeleteWhereHandler<B> handler) {
        return mapper.delete(renderDelete(handler.handle(table, deleteFrom(table).where())));
    }

    @Override
    public int deleteById(Long id) {
        return deleteBy((t, i) -> i.and(idSqlColumn, isEqualTo(id)));
    }

    @Override
    public int insert(T row) {
        long now = System.currentTimeMillis();
        row.setGmtCreate(now);
        row.setGmtModified(now);
        InsertDSL<T> intoDsl = SqlBuilder.insert(row).into(table);
        table.getAllColumns().forEach(column -> intoDsl.map(column).toProperty(StringUtilities.toCamelCase(column.asCamelCase().name())));
        return mapper.insert(renderInsert(intoDsl));
    }

    @Override
    public int insertBatch(Collection<T> records) {
        long now = System.currentTimeMillis();
        records.forEach(i -> {
            i.setGmtModified(now);
            i.setGmtModified(now);
        });
        BatchInsertDSL<T> into = SqlBuilder.insertBatch(records).into(table);
        List<InsertStatementProvider<T>> providers = renderInsertBatch(into);
        providers.forEach(i -> mapper.insert(i));
        return providers.size();
    }

    private SelectStatementProvider renderSelect(Buildable<SelectModel> buildable) {
        return buildable.build().render(getRenderingStrategy());
    }

    private UpdateStatementProvider renderUpdate(Buildable<UpdateModel> buildable) {
        return buildable.build().render(getRenderingStrategy());
    }

    private DeleteStatementProvider renderDelete(Buildable<DeleteModel> buildable) {
        return buildable.build().render(getRenderingStrategy());
    }

    private InsertStatementProvider<T> renderInsert(Buildable<InsertModel<T>> buildable) {
        return buildable.build().render(getRenderingStrategy());
    }

    private List<InsertStatementProvider<T>> renderInsertBatch(Buildable<BatchInsertModel<T>> buildable) {
        return buildable.build().render(getRenderingStrategy()).insertStatements();
    }

    @AllArgsConstructor
    @Getter
    public static class WhereSupplier<T> {
        private SqlColumn<T> sqlColumn;
        private Supplier<T> value;
    }
}
