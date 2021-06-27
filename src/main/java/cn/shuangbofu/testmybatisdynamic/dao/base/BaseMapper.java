package cn.shuangbofu.testmybatisdynamic.dao.base;

import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.mybatis.dynamic.sql.util.SqlProviderAdapter;
import org.mybatis.dynamic.sql.util.mybatis3.CommonCountMapper;
import org.mybatis.dynamic.sql.util.mybatis3.CommonDeleteMapper;
import org.mybatis.dynamic.sql.util.mybatis3.CommonInsertMapper;
import org.mybatis.dynamic.sql.util.mybatis3.CommonUpdateMapper;

import java.util.List;
import java.util.Optional;

public interface BaseMapper<T> extends CommonCountMapper, CommonDeleteMapper, CommonInsertMapper<T>, CommonUpdateMapper {

    Class<?> DEFAULT_TYPE = SqlProviderAdapter.class;
    String SELECT = "select";

    Optional<T> selectOne(SelectStatementProvider selectStatement);

    List<T> selectMany(SelectStatementProvider selectStatement);
}
