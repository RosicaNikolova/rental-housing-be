package org.example.domain.Responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.controller.DTO.PropertyDTO;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetAllPropertiesResponse {

    private List<PropertyDTO> properties;
}
