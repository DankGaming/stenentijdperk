module hsleiden.stenentijdperk.stenentijdperk {
    requires javafx.controls;
    requires javafx.graphics;
    requires javafx.base;
    requires google.cloud.firestore;
    requires com.google.auth.oauth2;
    requires com.google.api.apicommon;
    requires com.google.common;
    requires firebase.admin;
    requires com.google.auth;
    requires google.cloud.core;
    exports hsleiden.stenentijdperk.stenentijdperk;

    opens hsleiden.stenentijdperk.stenentijdperk.Models to google.cloud.firestore;
    opens hsleiden.stenentijdperk.stenentijdperk.Helpers to google.cloud.firestore;
}