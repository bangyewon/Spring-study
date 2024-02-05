package com.example.productorderservice.payment.adapter;

import com.example.productorderservice.payment.domain.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
//결제 저장소
interface PaymentRepository extends JpaRepository<Payment, Long> {
}
