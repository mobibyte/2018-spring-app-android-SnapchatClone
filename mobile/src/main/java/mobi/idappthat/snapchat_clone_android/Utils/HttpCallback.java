package mobi.idappthat.snapchat_clone_android.Utils;

import okhttp3.Response;

public interface HttpCallback  {

    /**
     * called when the server response was not 2xx or when an exception was thrown in the process
     * @param response - in case of server error (4xx, 5xx) this contains the server response
     *                 in case of IO exception this is null
     * @param throwable - contains the exception. in case of server error (4xx, 5xx) this is null
     */
    void onFailure(Response response, Throwable throwable);

    /**
     * contains the server response
     * @param response
     */
    void onSuccess(Response response);
}
