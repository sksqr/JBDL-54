package com.example;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PGTxnDTO {
    private String status;
    private Long userId;
    private Double amount;
}
