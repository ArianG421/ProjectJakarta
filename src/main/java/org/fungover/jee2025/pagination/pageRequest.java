

package org.fungover.jee2025.pagination;


public class pageRequest implements Pageable {

    private final int pageNumber;
    private final int pageSize;

    public pageRequest(int pageNumber, int pageSize) {
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
    }

    @Override
    public int getPageNumber() {
        return pageNumber;
    }

    @Override
    public int getPageSize() {
        return pageSize;
    }
}