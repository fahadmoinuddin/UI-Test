package com.test.solution;

/**
 * Interface which the main fragments implement. This interface contains methods to show progress, toast and other common actions.
 *
 * created by fahad on 07/08/2018
 */
public interface FragmentListener {
    void showProgress(boolean show); // show progress dialog
    void showToastInActivity(String message); // display a toast with message
}
