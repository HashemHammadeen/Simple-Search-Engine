package com.zerowhisper.codesearchengine.services;

import com.zerowhisper.codesearchengine.Interfaces.SearchingServices;
import com.zerowhisper.codesearchengine.models.MFilter;
import com.zerowhisper.codesearchengine.models.MPagination;
import com.zerowhisper.codesearchengine.models.MQueryRequest;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class JavaSearchService implements SearchingServices {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public JavaSearchService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Map<String, Object> search(MQueryRequest queryRequest) {

        StringBuilder query = new StringBuilder();
        List<String> tables = queryRequest.getTables();
        List<MFilter> filters = queryRequest.getFilters();
        MPagination pagination = queryRequest.getPagination();

        query.append("SELECT * FROM ");
        query.append(String.join(", ", tables));

        // Apply filters if any
        if (filters != null && !filters.isEmpty()) {
            query.append(" WHERE ");
            StringJoiner filterJoiner = new StringJoiner(" AND ");
            for (MFilter filter : filters) {
                filterJoiner.add(filter.getField() + " " + filter.getOperator() + " '" + filter.getValue() + "'");
            }
            query.append(filterJoiner.toString());
        }

        if (pagination != null) {
            int offset = (pagination.getPage() - 1) * pagination.getSize();
            query.append(" LIMIT ").append(pagination.getSize()).append(" OFFSET ").append(offset);
        }

        List<Map<String, Object>> queryResults = jdbcTemplate.queryForList(query.toString());

        Map<String, Object> resultWithTypes = new HashMap<>();

        for (String table : tables) {
            List<Map<String, Object>> tableResult = new ArrayList<>();

            for (Map<String, Object> row : queryResults) {
                Map<String, Object> rowWithType = new HashMap<>(row);
                rowWithType.put("type", table);
                tableResult.add(rowWithType);
            }

            resultWithTypes.put(table, tableResult);
        }

        return resultWithTypes;
    }
}
