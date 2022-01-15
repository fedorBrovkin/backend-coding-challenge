package com.orange.auto.complete.common.exception;

public class NecessaryQueryParameterMissingException extends RuntimeException{

    public NecessaryQueryParameterMissingException(){

        super("City suggestion request necessary URL parameters missing. Main query parameter 'q' is empty or missing");
    }
}
