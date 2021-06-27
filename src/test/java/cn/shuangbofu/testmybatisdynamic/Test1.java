package cn.shuangbofu.testmybatisdynamic;

import org.junit.Test;

public class Test1{
    @Test
    void testGeneralSelect() {
//        try (SqlSession session = sqlSessionFactory.openSession()) {
//            PersonMapper mapper = session.getMapper(PersonMapper.class);
//        Function<Long, SelectStatementProvider> GET_BY_ID = value -> select(id.as("A_ID"), firstName, birthDate, employed, occupation, addressId)
//                .from(person)
//                .where(id, isEqualTo(1))
//                .or(occupation, isNull())
//                .build()
//                .render(RenderingStrategies.MYBATIS3);
//        ;
//        PersonMapper mapper;
//        List<PersonRecord> personRecords = mapper.selectMany(GET_BY_ID.apply(1L));

//
//            List<PersonRecord> rows = mapper.selectMany(selectStatement);
////            assertThat(rows).hasSize(3);
//        }
    }

    @Test
    void testGeneralDelete() {
//        try (SqlSession session = sqlSessionFactory.openSession()) {
//            PersonMapper mapper = session.getMapper(PersonMapper.class);
//
//            DeleteStatementProvider deleteStatement = deleteFrom(person)
//                    .where(occupation, isNull())
//                    .build()
//                    .render(RenderingStrategies.MYBATIS3);
//
//            int rows = mapper.delete(deleteStatement);
//            assertThat(rows).isEqualTo(2);
//        }
    }

}
