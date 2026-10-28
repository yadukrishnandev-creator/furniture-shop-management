package com.furnitureshop.branch;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateBranchRequest(

        @NotBlank(message = "Branch code is required")
        @Size(max = 20, message = "Branch code must not exceed 20 characters")
        String code,

        @NotBlank(message = "Branch name is required")
        @Size(max = 120, message = "Branch name must not exceed 120 characters")
        String name,

        @NotBlank(message = "Branch address is required")
        @Size(max = 500, message = "Branch address must not exceed 500 characters")
        String address
) {
}