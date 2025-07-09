package com.example.demo.DTO;

public record SchoolDto(
        String code,
        String name,
        String district,
        String ward,
        String street,
        String accreditationLevel,
        Short foundedYear,
        String phone,
        String website
) {
    @Override
    public String code() {
        return code;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public String district() {
        return district;
    }

    @Override
    public String ward() {
        return ward;
    }

    @Override
    public String street() {
        return street;
    }

    @Override
    public String accreditationLevel() {
        return accreditationLevel;
    }

    @Override
    public Short foundedYear() {
        return foundedYear;
    }

    @Override
    public String phone() {
        return phone;
    }

    @Override
    public String website() {
        return website;
    }
}
