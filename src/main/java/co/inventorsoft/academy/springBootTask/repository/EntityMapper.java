package co.inventorsoft.academy.springBootTask.repository;

import java.sql.ResultSet;

public interface EntityMapper<T> {
    T mapRow(ResultSet rs);
}
