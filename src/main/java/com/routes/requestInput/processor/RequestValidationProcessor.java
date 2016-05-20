package com.routes.requestInput.processor;

import com.routes.requestInput.routes.RouteRestFormInput;
import com.routes.requestInput.exception.RequestValidationException;
import com.routes.requestInput.model.RestFormInputModel;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.log4j.Logger;

import java.util.regex.Pattern;

/**
 * Validation of RequestInput
 */
public class RequestValidationProcessor implements Processor {

    static Logger logger = Logger.getLogger(RouteRestFormInput.class.getName());

    @Override
    public void process(Exchange exchange) throws RequestValidationException {
        logger.info("Starting Validation");
        RestFormInputModel restFormInputModel = exchange.getIn().getBody(RestFormInputModel.class);
        logger.debug(" of FormRequest: "+ restFormInputModel);

        if(restFormInputModel.getEmail() == null || restFormInputModel.getEmail().length() == 0){
            throw new RequestValidationException("No Email set");
        }

        if(restFormInputModel.getEmail() != null && restFormInputModel.getEmail().length()>0){
            String regex = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+(?:\\.[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+)*@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$";
            Pattern pattern = Pattern.compile(regex);
            if(!pattern.matcher(restFormInputModel.getEmail()).matches()){
                throw new RequestValidationException("Not a correct Email address");
            }
        }

        /*
        if(restFormInputModel.getDateFrom().getTime() > restFormInputModel.getDateTo().getTime()){
            throw new RequestValidationException("Start date is bigger than endDate");
        }
        */





    }
}
