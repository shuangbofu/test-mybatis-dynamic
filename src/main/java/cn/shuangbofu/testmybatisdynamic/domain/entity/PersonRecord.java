package cn.shuangbofu.testmybatisdynamic.domain.entity;

import lombok.Data;

import java.util.Date;

@Data
public class PersonRecord implements BaseModel {
    private Long id;
    private String firstName;
    private String lastName;
    private Date birthDate;
    private Boolean employed;
    private String occupation;
    private Integer addressId;
    private Long gmtCreate;
    private Long gmtModified;
}
