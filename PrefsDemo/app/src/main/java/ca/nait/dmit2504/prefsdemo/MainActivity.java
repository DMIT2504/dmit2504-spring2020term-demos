package ca.nait.dmit2504.prefsdemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.preference.PreferenceManager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SharedPreferences.OnSharedPreferenceChangeListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
//        return super.onCreateOptionsMenu(menu);
        // Get the menu inflator
        MenuInflater inflater = getMenuInflater();
        // Inflate the menu
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull final MenuItem item) {
        if (item.getItemId() == R.id.menu_item_settings) {
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSharedPreferenceChanged(final SharedPreferences sharedPreferences, final String key) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        String username = prefs.getString("username_pref","user2504");
        String password = prefs.getString("password_pref", "Password2504");
        Boolean autologin = prefs.getBoolean("autologin_pref", false);
        TextView helloText = findViewById(R.id.hello_text);
        String message = String.format("username: %s,  password: %s, autologin: %s", username, password, autologin);
        helloText.setText(message);

        String colorName = prefs.getString("backgroundColor_pref", "BLUE");
        ConstraintLayout layout = findViewById(R.id.activity_main_layout);
        layout.setBackgroundColor(Color.parseColor(colorName));
//        getWindow().getDecorView().setBackgroundColor(Color.parseColor(colorName));
        // Set the minimum font size to 12
        int fontSize = prefs.getInt("fontsize_pref", 0) + 12;
        helloText.setTextSize(fontSize);

    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        prefs.registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        prefs.unregisterOnSharedPreferenceChangeListener(this);
    }
}
