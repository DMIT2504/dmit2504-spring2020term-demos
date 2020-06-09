package ca.nait.dmit2504.servicesbroadcastreceiverdemo;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class JittersIntentService extends IntentService {
    // TODO: Rename actions, choose action names that describe tasks that this
    // IntentService can perform, e.g. ACTION_FETCH_NEW_ITEMS
    private static final String ACTION_POST = "ca.nait.dmit2504.servicesbroadcastreceiverdemo.action.POST";
//    private static final String ACTION_BAZ = "ca.nait.dmit2504.servicesbroadcastreceiverdemo.action.BAZ";

    public static final String ACTION_POST_COMPLETE = "ca.nait.dmit2504.servicesbroadcastreceiverdemo.POST_NOTIFICATION";

    // TODO: Rename parameters
    private static final String EXTRA_URL = "ca.nait.dmit2504.servicesbroadcastreceiverdemo.extra.URL";
    private static final String EXTRA_NAME = "ca.nait.dmit2504.servicesbroadcastreceiverdemo.extra.NAME";
    private static final String EXTRA_MESSAGE = "ca.nait.dmit2504.servicesbroadcastreceiverdemo.extra.MESSAGE";

    public JittersIntentService() {
        super("JittersIntentService");
    }

    /**
     * Starts this service to perform action Foo with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    // TODO: Customize helper method
    public static void startActionPost(Context context, String url, String name, String message) {
        Intent intent = new Intent(context, JittersIntentService.class);
        intent.setAction(ACTION_POST);
        intent.putExtra(EXTRA_URL, url);
        intent.putExtra(EXTRA_NAME, name);
        intent.putExtra(EXTRA_MESSAGE, message);
        context.startService(intent);
    }

    /**
     * Starts this service to perform action Baz with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    // TODO: Customize helper method
//    public static void startActionBaz(Context context, String param1, String param2) {
//        Intent intent = new Intent(context, JittersIntentService.class);
//        intent.setAction(ACTION_BAZ);
//        intent.putExtra(EXTRA_PARAM1, param1);
//        intent.putExtra(EXTRA_PARAM2, param2);
//        context.startService(intent);
//    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_POST.equals(action)) {
                final String url = intent.getStringExtra(EXTRA_URL);
                final String name = intent.getStringExtra(EXTRA_NAME);
                final String message = intent.getStringExtra(EXTRA_MESSAGE);
                handleActionPost(url, name, message);
//            } else if (ACTION_BAZ.equals(action)) {
//                final String param1 = intent.getStringExtra(EXTRA_PARAM1);
//                final String param2 = intent.getStringExtra(EXTRA_PARAM2);
//                handleActionBaz(param1, param2);
            }
        }
    }

    /**
     * Handle action Foo in the provided background thread with the provided
     * parameters.
     */
    private void handleActionPost(String url, String name, String message) {
        // Create a NetworkAPI object
        NetworkAPI networkAPI = new NetworkAPI();
        // Create a Map with the data to send
        Map<String, String> formData = new HashMap<>();
        formData.put("LOGIN_NAME", name);
        formData.put("DATA", message);
        try {
            networkAPI.postFormData(url, formData);
            Intent intent = new Intent();
            intent.setAction(ACTION_POST_COMPLETE);
            intent.putExtra("post_result","Post successful");
            sendBroadcast(intent);

        } catch (Exception e) {
            e.printStackTrace();
            Intent intent = new Intent();
            intent.setAction(ACTION_POST_COMPLETE);
            intent.putExtra("post_result","Post not successful");
            sendBroadcast(intent);

        }
    }

    /**
     * Handle action Baz in the provided background thread with the provided
     * parameters.
     */
//    private void handleActionBaz(String param1, String param2) {
//        // TODO: Handle action Baz
//        throw new UnsupportedOperationException("Not yet implemented");
//    }
}
