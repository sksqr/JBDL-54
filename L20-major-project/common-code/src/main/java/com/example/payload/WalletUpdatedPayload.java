package com.example.payload;


import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WalletUpdatedPayload {

    private String userName;

    private String userEmail;

    private Double balance;

    private String requestId;

}
