package com.crowdfunding.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.gas.ContractGasProvider;
import org.web3j.tx.gas.StaticGasProvider;
import java.math.BigInteger;

@Configuration
public class Web3Config {

    @Value("${web3j.client-address}")
    private String clientAddress;

    @Value("${web3j.gas-price}")
    private BigInteger gasPrice;

    @Value("${web3j.gas-limit}")
    private BigInteger gasLimit;

    @Bean
    public Web3j web3j() {
        return Web3j.build(new HttpService(clientAddress));
    }

    @Bean
    public ContractGasProvider gasProvider() {
        return new StaticGasProvider(gasPrice, gasLimit);
    }
} 