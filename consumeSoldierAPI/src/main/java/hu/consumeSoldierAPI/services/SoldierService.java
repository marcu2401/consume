package hu.consumeSoldierAPI.services;

import hu.consumeSoldierAPI.domain.Category;
import hu.consumeSoldierAPI.domain.Soldier;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class SoldierService {

    @Autowired
    private RestTemplate restTemplate;
    private final String REST_URL = "http://localhost:8080/soldiers";

    public List<Soldier> getSoldiers() {
        String url = REST_URL;
        Soldier[] soldiers = restTemplate.getForObject(url, Soldier[].class);
        return Arrays.asList(soldiers);
    }

    public Soldier getSoldier(int id) {
        String url = REST_URL+"/{id}";
        Soldier soldier = restTemplate.getForObject(url, Soldier.class, id);
        return soldier;
    }

    public int addSoldier(String rank, String birth, String weapon, int shotpeople) {
        String url = REST_URL;
        Soldier soldier = new Soldier(rank, birth, weapon, shotpeople);
        HttpEntity<Soldier> requestEntity = new HttpEntity<>(soldier);
        ResponseEntity<Soldier> responseEntity = restTemplate.exchange(
                url,
                HttpMethod.POST,
                requestEntity,
                Soldier.class);
        return responseEntity.getStatusCodeValue();
    }

    public void disarmSoldier(int id) {
        String url = REST_URL+"/{id}";
        restTemplate.delete(url, id);
    }

    public int updateSoldier(int id, int shot) {
        System.out.println("update - service: "+id+", "+shot);
        String url = REST_URL+"/{id}/{shotpeople}";
        Map<String, Object> values = new HashMap<String, Object>();
        values.put("id", id);
        values.put("shotpeople", shot);
        Set<String> keys= values.keySet();
        for (String key : keys) {
            System.out.println(key+" "+values.get(key));
        }
        // az alábbi két sor azért kell, hogy a patch-el át tudja adni az adatokat a back-end félé
        CloseableHttpClient client = HttpClientBuilder.create().build();
        restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory(client));

        HttpEntity<Soldier> requestEntity = new HttpEntity<>(new Soldier());
        ResponseEntity<Soldier> responseEntity = restTemplate.exchange(url, HttpMethod.PATCH, requestEntity, Soldier.class, values);
        return responseEntity.getStatusCodeValue();
    }

    public int replaceSoldier(int id, int rank, String birth, String weapon, int shotpeople) {
        return 200;
    }
}
