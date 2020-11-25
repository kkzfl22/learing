package com.liujun.code.refactoring.refactoring.ten.order106.replaceparameterwithexplicitmethods.refactor;

/**
 * @author liujun
 * @version 0.0.1
 */
public class DataClient {

    void dataCreate()
    {
        Employee engineer = Engineer.createEngineer();
        Employee manager = Manager.createManager();
        Employee salesman = SalesMan.createSalesMan();
    }
}
