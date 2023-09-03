package com.example;


import com.example.entity.Wallet;
import com.example.repo.WalletRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/wallet-service")
public class WalletController {
    private static Logger LOGGER = LoggerFactory.getLogger(WalletController.class);


    private static RestTemplate restTemplate = new RestTemplate();

    @Autowired
    private WalletRepo walletRepo;

    @GetMapping("/hello")
    ResponseEntity<String> getBalance(){
        LOGGER.info("Processing hello request");
        return ResponseEntity.ok("Hello from wallet-service");
    }

    @GetMapping("/add-money-status/{txnId}")
    ResponseEntity<String> addMoneyStatus(@PathVariable String txnId){
        LOGGER.info("Processing addMoneyStatus request {}",txnId);
        PGTxnDTO pgTxnDTO = restTemplate.getForObject("http://jbdlwallet.com/pg-service/payment-status/"+txnId,PGTxnDTO.class);
        if (pgTxnDTO.getStatus().equalsIgnoreCase("SUCCESS")){
            Wallet wallet = walletRepo.findByUserId(pgTxnDTO.getUserId());
            wallet.setBalance(wallet.getBalance()+ pgTxnDTO.getAmount());
            walletRepo.save(wallet);
            return ResponseEntity.ok("Wallet Updated");
        }
        // API Call to PG, to check payment status
        return ResponseEntity.ok("PG Txn Failed");
    }
}
