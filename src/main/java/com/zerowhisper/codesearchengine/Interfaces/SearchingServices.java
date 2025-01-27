package com.zerowhisper.codesearchengine.Interfaces;

import com.zerowhisper.codesearchengine.models.MQueryRequest;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Map;

public interface SearchingServices {
    Map<String, Object> search(MQueryRequest queryRequest);
}
