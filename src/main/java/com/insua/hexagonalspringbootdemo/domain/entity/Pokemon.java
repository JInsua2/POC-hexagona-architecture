package com.insua.hexagonalspringbootdemo.domain.entity;

import java.util.UUID;
import lombok.Builder;

@Builder
public record Pokemon(
    UUID id,
    String name,
    String type,
    String hp) {


}
