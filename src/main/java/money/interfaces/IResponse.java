package money.interfaces;

import java.util.List;

public interface IResponse<T> {
    List<T> getResponses();

    T getResponse();
}
