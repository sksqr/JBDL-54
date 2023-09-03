package com.example.payload;


import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransactionInitPayload {
    private Long id;

    private Long fromUserId;

    private Long toUserId;

    private Double amount;

    private String remark;
    private String requestId;
}
