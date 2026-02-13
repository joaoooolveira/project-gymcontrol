package com.joaooliveira.gymcontrol.model;

import com.joaooliveira.gymcontrol.model.Member;
import java.time.LocalDateTime;

public class Access {
    private Long id;
    private Member member;
    LocalDateTime entry;
    LocalDateTime outgoing;
}
