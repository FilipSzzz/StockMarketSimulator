module com.stockmarket.persistence {
    requires com.stockmarket.model;
    requires com.stockmarket.portfolio;
    requires com.fasterxml.jackson.databind;
    requires com.opencsv;
    exports com.stockmarket.persistence;
}