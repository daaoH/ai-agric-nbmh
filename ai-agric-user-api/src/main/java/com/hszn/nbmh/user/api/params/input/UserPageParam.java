package com.hszn.nbmh.user.api.params.input;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper=false)
@Builder(toBuilder=true)
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain=true)
@Schema(name="用户分页参数对象")
public class UserPageParam implements Serializable {

    /**
     * 用户分页参数对象
     */
    @Schema(name="用户分页参数对象", description="preventStationId")
    private Long preventStationId;

    /**
     * 用户类型-3站长 4防疫员 5养殖户  7稽查员
     */
    @Schema(name="用户类型 4防疫员+稽查员 5养殖户", description="type")
    private int type;

    /**
     * 用户名字
     */
    @Schema(name="用户名字", description="userName")
    private String userName;

    /**
     * 用户地址
     */
    @Schema(name="用户地址", description="userAddress")
    private String userAddress;

}