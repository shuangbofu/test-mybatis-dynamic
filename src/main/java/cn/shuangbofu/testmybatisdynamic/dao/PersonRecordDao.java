package cn.shuangbofu.testmybatisdynamic.dao;

import cn.shuangbofu.testmybatisdynamic.dao.base.BaseLogicalWithEnvDao;
import cn.shuangbofu.testmybatisdynamic.dao.base.annotation.Dao;
import cn.shuangbofu.testmybatisdynamic.dao.mapper.PersonRecordMapper;
import cn.shuangbofu.testmybatisdynamic.dao.table.PersonRecordTable;
import cn.shuangbofu.testmybatisdynamic.domain.entity.PersonRecord;
import org.springframework.beans.factory.annotation.Autowired;

import static org.mybatis.dynamic.sql.SqlBuilder.isLike;

@Dao(table = PersonRecordTable.class, mapper = PersonRecordMapper.class)
public class PersonRecordDao extends BaseLogicalWithEnvDao<PersonRecordMapper, PersonRecord, PersonRecordTable> {

    public PersonRecordDao(@Autowired PersonRecordMapper mapper, @Autowired PersonRecordTable table) {
        super(mapper, table);
    }

    public String getLikeFirstName(String name) {
        return selectOneValueBy((t, i) -> i.and(t.firstName, isLike(name).map(j -> String.format("%%%s%%", j))),
                PersonRecord::getLastName)
                .orElse(null);
    }
}
