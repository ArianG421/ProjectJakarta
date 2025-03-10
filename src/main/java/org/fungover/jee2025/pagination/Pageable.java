package org.fungover.jee2025.pagination;

import jakarta.data.page.Page;

public interface Pageable {
    int getPageNumber();
    int getPageSize();

}
