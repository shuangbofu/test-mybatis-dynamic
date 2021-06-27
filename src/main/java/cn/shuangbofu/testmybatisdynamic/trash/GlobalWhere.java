package cn.shuangbofu.testmybatisdynamic.trash;

import java.sql.JDBCType;
import java.util.function.Supplier;

public interface GlobalWhere {
    Field<Boolean> getLogical();
    Field<String> getEnv();

    class Field<T> {
        private String fieldName;
        private JDBCType jdbcType;
        private Supplier<T> supplier;
    }
}
