package com.bhhan.tram.customer.service;

import com.bhhan.tram.customer.api.command.ReserveCreditCommand;
import com.bhhan.tram.customer.api.reply.CustomerCreditLimitExceeded;
import com.bhhan.tram.customer.api.reply.CustomerCreditReserved;
import com.bhhan.tram.customer.api.reply.CustomerNotFound;
import com.bhhan.tram.customer.domain.CustomerCreditLimitExceededException;
import com.bhhan.tram.customer.domain.CustomerNotFoundException;
import io.eventuate.tram.commands.consumer.CommandHandlers;
import io.eventuate.tram.commands.consumer.CommandMessage;
import io.eventuate.tram.messaging.common.Message;
import io.eventuate.tram.sagas.participant.SagaCommandHandlersBuilder;
import lombok.RequiredArgsConstructor;

import static io.eventuate.tram.commands.consumer.CommandHandlerReplyBuilder.withFailure;
import static io.eventuate.tram.commands.consumer.CommandHandlerReplyBuilder.withSuccess;

/**
 * Created by hbh5274@gmail.com on 2020-11-09
 * Github : http://github.com/bhhan5274
 */

@RequiredArgsConstructor
public class CustomerCommandHandler {
    private final CustomerService customerService;

    public CommandHandlers commandHandler(){
        return SagaCommandHandlersBuilder
                .fromChannel("customerService")
                .onMessage(ReserveCreditCommand.class, this::reserveCredit)
                .build();
    }

    public Message reserveCredit(CommandMessage<ReserveCreditCommand> cm){
        ReserveCreditCommand cmd = cm.getCommand();

        try{
            customerService.reserveCredit(cmd.getCustomerId(), cmd.getOrderId(), cmd.getOrderTotal());
            return withSuccess(new CustomerCreditReserved());
        }catch(CustomerNotFoundException e){
            return withFailure(new CustomerNotFound());
        }catch(CustomerCreditLimitExceededException e){
            return withFailure(new CustomerCreditLimitExceeded());
        }
    }
}
