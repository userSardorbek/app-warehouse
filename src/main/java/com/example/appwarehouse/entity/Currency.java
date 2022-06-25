package com.example.appwarehouse.entity;

import com.example.appwarehouse.entity.teplate.AbsEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class Currency extends AbsEntity {

}
