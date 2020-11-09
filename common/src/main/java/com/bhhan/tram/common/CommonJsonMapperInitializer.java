package com.bhhan.tram.common;

import io.eventuate.common.json.mapper.JSonMapper;

import javax.annotation.PostConstruct;

/**
 * Created by hbh5274@gmail.com on 2020-11-02
 * Github : http://github.com/bhhan5274
 */
class CommonJsonMapperInitializer {
    @PostConstruct
    public void initialize(){
        registerMoneyModule();
    }

    public void registerMoneyModule(){
        JSonMapper.objectMapper.registerModule(new MoneyModule());
    }
}
