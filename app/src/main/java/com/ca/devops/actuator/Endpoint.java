package com.ca.devops.actuator;

import java.net.Socket;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import javax.annotation.PostConstruct;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLEngine;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509ExtendedTrustManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustAllStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.TrustStrategy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.ca.devops.actuator.response.Info;
import com.ca.devops.actuator.response.BuildInfo;
import com.ca.devops.actuator.response.GitInfo;

public class Endpoint {

    private static final Logger LOG = LoggerFactory.getLogger(Endpoint.class);

    private RestTemplate theRestTemplate;


    private RestTemplate getRestTemplate() throws InternalException {
        if (theRestTemplate == null) {
            try {
                SSLContext sslContext = SSLContext.getInstance("SSL");

                sslContext.init(
                    null,
                    new TrustManager[] {
                        new X509ExtendedTrustManager() {
                            @Override public X509Certificate[] getAcceptedIssuers() { return null; }

                            @Override public void checkClientTrusted(X509Certificate[] chain, String authType)                   {}
                            @Override public void checkClientTrusted(X509Certificate[] chain, String authType, Socket    socket) {}
                            @Override public void checkClientTrusted(X509Certificate[] chain, String authType, SSLEngine engine) {}
                            @Override public void checkServerTrusted(X509Certificate[] chain, String authType)                   {}
                            @Override public void checkServerTrusted(X509Certificate[] chain, String authType, Socket    socket) {}
                            @Override public void checkServerTrusted(X509Certificate[] chain, String authType, SSLEngine engine) {}
                        }
                    },
                    new SecureRandom());

//                SSLContext sslContext = org.apache.http.ssl.SSLContexts.custom()
//                    .loadTrustMaterial(null, new TrustAllStrategy())
//                    .build();

                SSLConnectionSocketFactory csf = new SSLConnectionSocketFactory(sslContext, SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);

                CloseableHttpClient httpClient = HttpClients.custom()
                    .setSSLSocketFactory(csf)
                    .build();

                HttpComponentsClientHttpRequestFactory requestFactory =
                    new HttpComponentsClientHttpRequestFactory();

                requestFactory.setConnectionRequestTimeout(1000);
                requestFactory.setConnectTimeout(1000);
                requestFactory.setReadTimeout(5000);
                requestFactory.setHttpClient(httpClient);

                theRestTemplate = new RestTemplate(requestFactory);

            } catch (NoSuchAlgorithmException | KeyManagementException e) {
                LOG.error("Could not construct RestTemplate.");
                theRestTemplate = null;

                throw new InternalException(e.getMessage());
            }
        }

        return theRestTemplate;
    }

    public Info hitAcceptJson(String url) throws InternalException {
        try {
            return getRestTemplate().getForObject(url, Info.class);

        } catch (RestClientException e) {
            LOG.error("Could not hit url " + url + " : " + e.getMessage());
        }

        return null;
    }

    public Info hitAcceptPing(String url) throws InternalException {

        try {
            String pingResult = getRestTemplate().getForObject(url, String.class);

            return infoFromPing(pingResult);

        } catch (RestClientException e) {
            LOG.error("Could not hit url " + url + " : " + e.getMessage());
        }

        return null;
    }

    public Info hitAcceptServerStatus(String url) throws InternalException {

        try {
            String serverStatusResult = getRestTemplate().getForObject(url, String.class);

            return infoFromServerStatus(serverStatusResult);

        } catch (RestClientException e) {
            LOG.error("Could not hit url " + url + " : " + e.getMessage());
        }

        return null;
    }

    public Info hitForHttpStatus(String url) throws InternalException {

        try {
            String textResult = getRestTemplate().getForObject(url, String.class);

            return new Info();

        } catch (RestClientException e) {
            LOG.error("Could not hit url " + url + " : " + e.getMessage());
        }

        return null;
    }


    /** Looks like this:
     * Server Name :tu-mtorig002-01.cac.com
     * Server No :0
     * Server Port :80
     * Logs URL :tu-mtorig002-01.cac.com:7777/logs
     *
     * Manifest-Version: 1.0
     * Archiver-Version: Plexus Archiver
     * Created-By: Apache Maven
     * Built-By: devops
     * Build-Jdk: 1.8.0_202
     * Implementation-Title: EContractClientWar
     * Implementation-Version: 1.2.431-SNAPSHOT
     * Implementation-Vendor-Id: com.cac.originations.caps
     * Implementation-Build: steps-econtract_05-02-2019_14:56:52
     * Implemented-By: devops
     */
    private Info infoFromServerStatus(String serverStatusResult) {

        Info      result    = new Info();
        BuildInfo buildInfo = new BuildInfo();

        for (String line : serverStatusResult.split("\n")) {
            String[] parts = line.split(":");
            if (parts.length == 2) {
                String value = stripHtml(parts[1]);
                if ("Implementation-Title".equals(parts[0])) {
                    buildInfo.setArtifact(value);
                    buildInfo.setName(value);

                } else if ("Implementation-Version".equals(parts[0])) {
                    buildInfo.setVersion(value);

                } else if ("Implementation-Vendor-Id".equals(parts[0])) {
                    buildInfo.setGroup(value);
                }
            }
        }

        result.setBuild(buildInfo);
        return result;
    }

    private Info infoFromPing(String pingResult) {

        Info    result  = new Info();
        GitInfo gitInfo = new GitInfo();

        for (String line : pingResult.split("\\<br\\>")) {
            String[] parts = line.split(":");
            if (parts.length == 2) {
                String value = stripHtml(parts[1]);
                if ("version.scm-branch".equals(parts[0])) {
                    gitInfo.setBranch(value);
                }
            }
        }

        result.setGit(gitInfo);
        return result;
    }

    private String stripHtml(String str) {
        return str.replaceAll("<br>", "");
    }

    /***
    class TrustingTrustStrategy implements TrustStrategy {

        public TrustingTrustStrategy() {}

        @Override
        boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
            return true;
        }
    }
    ***/

}
