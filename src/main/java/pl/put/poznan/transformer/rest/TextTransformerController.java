package pl.put.poznan.transformer.rest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import pl.put.poznan.transformer.logic.TextTransformerService;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/transform")
public class TextTransformerController {

    private static final Logger logger = LoggerFactory.getLogger(TextTransformerController.class);

    private String response;

    @RequestMapping(value = "/{text}", method = RequestMethod.GET, produces = "application/json")
    public Map get(@PathVariable String text,
                   @RequestParam(value="transforms", defaultValue="upper") String[] transforms) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        TextTransformerService textTransformerService = new TextTransformerService();

        // log the parameters
        logger.debug(text);
        logger.debug(Arrays.toString(transforms));
        return Collections.singletonMap("text", textTransformerService.transformText(text, transforms));
    }

    public static class Body{
        public String text;
        public String[] transforms;
    }

    @CrossOrigin
    @RequestMapping(method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public Map post(@RequestBody Body body) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        TextTransformerService textTransformerService = new TextTransformerService();

        // log the parameters
        return Collections.singletonMap("text", textTransformerService.transformText(body.text, body.transforms));

        // perform the transformation, you should run your logic here, below is just a silly example
    }


}


