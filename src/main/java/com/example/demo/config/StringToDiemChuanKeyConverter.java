package com.example.demo.config;

import com.example.demo.entity.DiemChuanKey;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToDiemChuanKeyConverter implements Converter<String, DiemChuanKey> {

    @Override
    public DiemChuanKey convert(String source) {
        try {
            // Định dạng đầu vào: "maTruong-nam", ví dụ: "79000701-2023"
            String[] parts = source.split("-");
            if (parts.length != 2) {
                throw new IllegalArgumentException("Invalid format. Expected 'maTruong-nam'");
            }

            String maTruong = parts[0];
            int nam = Integer.parseInt(parts[1]);

            return new DiemChuanKey(maTruong, nam);

        } catch (Exception e) {
            throw new IllegalArgumentException("Could not convert String to DiemChuanKey: " + source, e);
        }
    }
}
