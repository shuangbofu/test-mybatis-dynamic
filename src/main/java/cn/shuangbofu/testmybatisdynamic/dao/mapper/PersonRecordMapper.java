package cn.shuangbofu.testmybatisdynamic.dao.mapper;

import cn.shuangbofu.testmybatisdynamic.dao.base.BaseMapper;
import cn.shuangbofu.testmybatisdynamic.domain.entity.PersonRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.SelectProvider;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;

import java.util.List;
import java.util.Optional;

@Mapper
public interface PersonRecordMapper extends BaseMapper<PersonRecord> {

    @Override
    @SelectProvider(method = SELECT)
    @ResultType(PersonRecord.class)
    List<PersonRecord> selectMany(SelectStatementProvider selectStatement);

    @Override
    @SelectProvider(method = SELECT)
    @ResultType(PersonRecord.class)
    Optional<PersonRecord> selectOne(SelectStatementProvider selectStatement);
}