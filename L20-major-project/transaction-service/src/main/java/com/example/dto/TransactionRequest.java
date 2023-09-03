package com.example.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class TransactionRequest {

    private Long fromUserId;

    private Long toUserId;

    private Double amount;

    private String remark;
}
