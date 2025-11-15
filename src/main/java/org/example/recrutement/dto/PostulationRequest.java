package org.example.recrutement.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostulationRequest {
    private Long offreId;
    private Long userId;
    private String cv;
    private String motivation;
}
