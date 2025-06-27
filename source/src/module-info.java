module source {
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.core;
    requires java.base;

    exports com.stockmarket.main;
    exports com.stockmarket.market;
    exports com.stockmarket.model;
    exports com.stockmarket.persistence;
    exports com.stockmarket.portfolio;
    exports com.stockmarket.ui;
    exports com.stockmarket.exception;
    opens com.stockmarket.model to com.fasterxml.jackson.databind;
    opens com.stockmarket.portfolio to com.fasterxml.jackson.databind;
}