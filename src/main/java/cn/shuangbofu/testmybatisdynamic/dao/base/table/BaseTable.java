package cn.shuangbofu.testmybatisdynamic.dao.base.table;

import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

import java.sql.JDBCType;
import java.util.List;

public abstract class BaseTable extends SqlTable {

    public BaseTable(String tableName) {
        super(tableName);
    }

    public SqlColumn<Long> getGmtCreateSqlColumn() {
        return getBigIntSqlColumn("gmt_create");
    }

    public SqlColumn<Long> getGmtModifiedSqlColumn() {
        return getBigIntSqlColumn("gmt_modified");
    }

    public SqlColumn<Long> getIdSqlColumn() {
        return getBigIntSqlColumn("id");
    }

    private SqlColumn<Long> getBigIntSqlColumn(String name) {
        return column(name, JDBCType.BIGINT);
    }

    public SqlTable getTable() {
        return this;
    }

    public abstract List<SqlColumn<?>> getAllColumns();
}
