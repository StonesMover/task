package com.zzx.task.pojo;


import cn.hutool.core.annotation.Alias;
import lombok.Data;

@Data
public class F560431 {

    @Alias("机台号")
    private String medrid2;
    @Alias("实际重量")
    private String mesoqs;
    @Alias("排程重量")
    private String meuorg;
    @Alias("排程达成率")
    private String medl01;
    @Alias("组别")
    private String medl02;
    @Alias("工序码")
    private String meurc1;

}
