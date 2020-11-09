package com.bhhan.tram.common;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by hbh5274@gmail.com on 2020-11-02
 * Github : http://github.com/bhhan5274
 */

//@Configuration
public class CommonConfiguration {
    @Bean
    public CommonJsonMapperInitializer commonJsonMapperInitializer(){
        return new CommonJsonMapperInitializer();
    }
}
