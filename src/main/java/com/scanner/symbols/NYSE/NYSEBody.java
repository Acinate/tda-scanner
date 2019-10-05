package com.scanner.symbols.NYSE;

import kong.unirest.ObjectMapper;

public class NYSEBody {
    public String instrumentType;
    public int pageNumber;
    public String sortColumn;
    public String sortOrder;
    public int maxResultsPerPage;
    public String filterToken;
}
