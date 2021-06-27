package cn.shuangbofu.testmybatisdynamic.controller;

import cn.shuangbofu.testmybatisdynamic.dao.PersonRecordDao;
import cn.shuangbofu.testmybatisdynamic.dao.base.Page;
import cn.shuangbofu.testmybatisdynamic.domain.entity.PersonRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;

@RequestMapping
@RestController
public class HelloController {

    @Autowired
    private PersonRecordDao personRecordDao;

    @GetMapping("hello")
    public String hello() {
        return "hello test";
    }

    @GetMapping("/person")
    public PersonRecord getOne(@RequestParam("id") Long recordId) {
        return personRecordDao.selectOneById(recordId).orElse(null);
    }

    @GetMapping("/person/list")
    public List<PersonRecord> list() {
        return personRecordDao.selectAll();
    }

    @PutMapping("/person")
    public boolean update(@RequestBody PersonRecord record) {
        return personRecordDao.updateById(record.getId(), (a, i) -> i.set(a.firstName).equalTo(record.getFirstName())) > 0;
    }

    @DeleteMapping("/person")
    public boolean delete(Long id) {
        return personRecordDao.logicalDeleteById(id) > 0;
    }

    @PostMapping("/person")
    public boolean insert(@RequestBody PersonRecord record) {
//        record.setBirthDate(new Date());
        boolean b = personRecordDao.insert(record) > 0;
        System.out.println(record.getId());
        return b;
    }

    @GetMapping("/test")
    public String test(String firstName) {
        return personRecordDao.getLikeFirstName(firstName);
    }

    @GetMapping("/person/count")
    public long count(boolean employed) {
        return personRecordDao.countBy((t, i) -> i.and(t.employed, isEqualTo(employed)));
    }

    @PostMapping("/person/page")
    public Page<PersonRecord> getPage(int pageNum, int pageSize) {
        return personRecordDao.selectPage(pageNum, pageSize);
    }
}
