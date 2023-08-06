package com.khesam.health.exporter.helper;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

@Singleton
class SslHelper {

    @Inject
    SslHelper() {
    }

    SSLContext buildSslContext(String caFilePath) {
        try {
            InputStream is = new FileInputStream(caFilePath);
            CertificateFactory cf = CertificateFactory.getInstance("X.509");
            X509Certificate caCert = (X509Certificate)cf.generateCertificate(is);

            TrustManagerFactory tmf = TrustManagerFactory
                    .getInstance(TrustManagerFactory.getDefaultAlgorithm());
            KeyStore ks = KeyStore.getInstance(KeyStore.getDefaultType());
            ks.load(null);
            ks.setCertificateEntry("caCert", caCert);

            tmf.init(ks);

            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, tmf.getTrustManagers(), null);
            return sslContext;
        } catch (KeyManagementException | IOException | KeyStoreException | NoSuchAlgorithmException |
                 CertificateException e) {
            throw new RuntimeException(e);
        }
    }
}
