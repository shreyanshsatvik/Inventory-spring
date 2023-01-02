package com.orderservice.orderservice.service;

import com.orderservice.orderservice.dto.OrderLineItemsDto;
import com.orderservice.orderservice.dto.OrderRequest;
import com.orderservice.orderservice.model.Order;
import com.orderservice.orderservice.model.OrderLineItems;
import com.orderservice.orderservice.repository.OrderRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;




@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {

    private final OrderRepository orderRepository;

    public void placeOrder(OrderRequest orderRequest){
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());
        System.out.println(UUID.randomUUID().toString());

        List<OrderLineItems> orderLineItems = orderRequest.getOrderLineItemsDtoList()
                .stream()
                .map(this::mapToDto)
                .toList();
        System.out.println("-->>>>>>>>>>>>>>>>>>>>");
        for (OrderLineItems i : orderLineItems )
        {
            System.out.println(i.toString());
        }
        System.out.println(Arrays.toString(orderLineItems.toArray()));
        order.setOrderLineItemsList(orderLineItems);


        System.out.println("-----------------------------------");
        System.out.println("Saving Order");
        System.out.println("-----------------------------------");
        orderRepository.save(order);
        System.out.println("-----------------------------------");
        System.out.println("Order Saved Successfully");
        System.out.println("-----------------------------------");
    }

    private OrderLineItems mapToDto(OrderLineItemsDto orderLineItemsDto) {

        OrderLineItems orderLineItems = new OrderLineItems();
        System.out.println("Price-> "+orderLineItemsDto.getPrice());
        System.out.println("SkuCode-> "+orderLineItemsDto.getSkuCode());
        System.out.println("Quantity-> "+orderLineItemsDto.getQuantity());
        orderLineItems.setPrice(orderLineItemsDto.getPrice());
        orderLineItems.setQuantity(orderLineItemsDto.getQuantity());
        orderLineItems.setSkuCode(orderLineItemsDto.getSkuCode());

        return orderLineItems;
    }
}
