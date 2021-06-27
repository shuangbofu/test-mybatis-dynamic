package cn.shuangbofu.testmybatisdynamic.dao.table;

import cn.shuangbofu.testmybatisdynamic.dao.base.table.BaseTable;
import com.google.common.collect.Lists;
import org.mybatis.dynamic.sql.SqlColumn;
import org.springframework.stereotype.Component;

import java.sql.JDBCType;
import java.util.Date;
import java.util.List;

@Component
public class PersonRecordTable extends BaseTable {

    public SqlColumn<Long> id = getIdSqlColumn();
    public SqlColumn<Long> gmtCreate = getGmtCreateSqlColumn();
    public SqlColumn<Long> gmtModified = getGmtModifiedSqlColumn();
    public SqlColumn<String> firstName = column("first_name", JDBCType.VARCHAR);
    public SqlColumn<String> lastName = column("last_name", JDBCType.VARCHAR);
    public SqlColumn<Date> birthDate = column("birth_date", JDBCType.TIMESTAMP);
    public SqlColumn<Boolean> employed = column("employed", JDBCType.TINYINT);
    public SqlColumn<String> occupation = column("occupation", JDBCType.VARCHAR);
    public SqlColumn<Integer> addressId = column("address_id", JDBCType.BIGINT);

    public PersonRecordTable() {
        super("person");
    }

    @Override
    public List<SqlColumn<?>> getAllColumns() {
        return Lists.newArrayList(id, gmtCreate, gmtModified, firstName, lastName, birthDate, employed, occupation, addressId);
    }
}
