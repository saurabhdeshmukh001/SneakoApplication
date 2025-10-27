package org.genc.app.SneakoAplication.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import org.genc.app.SneakoAplication.enums.RoleType;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private RoleType name;

    private String description;
}
