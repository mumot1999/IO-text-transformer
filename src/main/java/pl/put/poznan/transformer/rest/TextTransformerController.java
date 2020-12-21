package pl.put.poznan.transformer.rest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import pl.put.poznan.transformer.logic.TextTransformerService;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;


@RestController
@RequestMapping("/{text}")
public class TextTransformerController {

    private static final Logger logger = LoggerFactory.getLogger(TextTransformerController.class);

    private String response;

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public Map get(@PathVariable String text,
                   @RequestParam(value="transforms", defaultValue="upper,escape") String[] transforms) {
        TextTransformerService textTransformerService = new TextTransformerService();

        // log the parameters
        logger.debug(text);
        logger.debug(Arrays.toString(transforms));
        return Collections.singletonMap("text", textTransformerService.transformText(text, transforms));
    }

    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    public void post(@PathVariable String text,
                     @RequestBody String[] transforms) {

        // log the parameters
        logger.debug(text);
        logger.debug(Arrays.toString(transforms));

        // perform the transformation, you should run your logic here, below is just a silly example
    }



}


