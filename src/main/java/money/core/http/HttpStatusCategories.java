package money.core.http;

import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.Arrays;

public class HttpStatusCategories {
    private static Integer[] normalCategories = {
            HttpStatus.CONTINUE.value(),
            HttpStatus.SWITCHING_PROTOCOLS.value(),
            HttpStatus.PROCESSING.value(),
            HttpStatus.CHECKPOINT.value(),
            HttpStatus.OK.value(),
            HttpStatus.CREATED.value(),
            HttpStatus.ACCEPTED.value(),
            HttpStatus.NON_AUTHORITATIVE_INFORMATION.value(),
            HttpStatus.NO_CONTENT.value(),
            HttpStatus.RESET_CONTENT.value(),
            HttpStatus.PARTIAL_CONTENT.value(),
            HttpStatus.MULTI_STATUS.value(),
            HttpStatus.ALREADY_REPORTED.value(),
            HttpStatus.IM_USED.value(),
            HttpStatus.MULTIPLE_CHOICES.value(),
            HttpStatus.MOVED_PERMANENTLY.value(),
            HttpStatus.FOUND.value(),
            HttpStatus.SEE_OTHER.value(),
            HttpStatus.NOT_MODIFIED.value(),
            HttpStatus.TEMPORARY_REDIRECT.value(),
            HttpStatus.PERMANENT_REDIRECT.value()
    };

    public static ArrayList<Integer> getNormalHttpStatus() {
        return new ArrayList<>(Arrays.asList(normalCategories));
    }
}
