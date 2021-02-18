package com.github.atomic.model.entity;

import com.github.atomic.utils.Constants;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity(name = Constants.TABLE_ROLE)
@Data
@NoArgsConstructor
@AllArgsConstructor
@With
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class Role extends IdEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "tag")
    private String tag;


}
