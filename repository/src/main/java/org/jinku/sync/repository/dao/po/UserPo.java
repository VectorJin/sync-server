package org.jinku.sync.repository.dao.po;

import lombok.Data;

@Data
public class UserPo {
    private Long id;
    private String userId;
    private String userName;
    private String nickName;
    private String email;
    private Integer valid;
    private Integer ctime;
    private Integer utime;
}
