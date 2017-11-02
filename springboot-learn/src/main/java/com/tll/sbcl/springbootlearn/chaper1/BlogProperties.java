package com.tll.sbcl.springbootlearn.chaper1;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * Created by tll on 2017/10/31 13:41
 **/
@Component
@Data
//@PropertySource(value = {"classpath:application.properties"}, encoding = "utf-8")
public class BlogProperties {

    @Value("${com.didispace.blog.name}")
    private String name;
    @Value("${com.didispace.blog.title}")
    private String title;
    @Value("${com.didispace.blog.desc}")
    private String desc;

}
