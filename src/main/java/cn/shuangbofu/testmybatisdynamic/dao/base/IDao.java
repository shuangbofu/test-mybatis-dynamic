package cn.shuangbofu.testmybatisdynamic.dao.base;

import cn.shuangbofu.testmybatisdynamic.dao.base.handler.DeleteWhereHandler;
import cn.shuangbofu.testmybatisdynamic.dao.base.handler.SelectHandler;
import cn.shuangbofu.testmybatisdynamic.dao.base.handler.UpdateHandler;
import cn.shuangbofu.testmybatisdynamic.dao.base.table.BaseTable;
import cn.shuangbofu.testmybatisdynamic.domain.entity.BaseModel;
import cn.shuangbofu.testmybatisdynamic.trash.PropertyFunc;
import org.mybatis.dynamic.sql.BasicColumn;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface IDao<M extends BaseMapper<T>, T extends BaseModel, B extends BaseTable> {

    long countBy(SelectHandler<B> handler);

    long countAll();

    Optional<T> selectOneBy(SelectHandler<B> handler, BasicColumn... selectList);

    Optional<T> selectOneById(Long id, BasicColumn... selectList);

    <V> Optional<V> selectOneValueBy(SelectHandler<B> handler, PropertyFunc<T, V> function);

    List<T> selectManyBy(SelectHandler<B> handler, BasicColumn... selectList);

    List<T> selectManyInIds(List<Long> ids, BasicColumn... selectList);

    List<T> selectManyInIdsAndBy(SelectHandler<B> handler, List<Long> ids, BasicColumn... selectList);

    List<T> selectAll(BasicColumn... selectList);

    Page<T> selectPageBy(SelectHandler<B> handler, int pageNum, int pageSize, BasicColumn... selectList);

    Page<T> selectPage(int pageNum, int pageSize, BasicColumn... selectList);

    int updateBy(UpdateHandler<B> handler);

    int updateById(Long id, UpdateHandler<B> handler);

    int deleteBy(DeleteWhereHandler<B> handler);

    int deleteById(Long id);

    int insert(T row);

    int insertBatch(Collection<T> records);
}
