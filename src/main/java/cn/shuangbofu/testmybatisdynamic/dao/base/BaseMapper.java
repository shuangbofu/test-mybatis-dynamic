package cn.shuangbofu.testmybatisdynamic.dao.base;

import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.mybatis.dynamic.sql.util.mybatis3.CommonCountMapper;
import org.mybatis.dynamic.sql.util.mybatis3.CommonDeleteMapper;
import org.mybatis.dynamic.sql.util.mybatis3.CommonInsertMapper;
import org.mybatis.dynamic.sql.util.mybatis3.CommonUpdateMapper;

import java.util.List;
import java.util.Optional;

public interface BaseMapper<T> extends CommonCountMapper, CommonDeleteMapper, CommonInsertMapper<T>, CommonUpdateMapper {

    String SELECT = "select";

    /**
     * selectOne
     *
     * @param selectStatement
     * @return
     */
    Optional<T> selectOne(SelectStatementProvider selectStatement);

    /**
     * selectMany
     *
     * @param selectStatement
     * @return
     */
    List<T> selectMany(SelectStatementProvider selectStatement);
}
