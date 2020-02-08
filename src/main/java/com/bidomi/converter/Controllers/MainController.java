package com.bidomi.converter.Controllers;

import com.bidomi.converter.Models.Convertation;
import com.bidomi.converter.Models.DaylyCurs;
import com.bidomi.converter.Repositories.ConvertationRepository;
import com.bidomi.converter.Repositories.CursRepository;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;

@Controller
@RequestMapping(path = "/api")
public class MainController {
    private CursRepository cursRepository;
    private ConvertationRepository convertationRepository;

    public MainController(
            CursRepository cursRepository, ConvertationRepository convertationRepository) {
        this.cursRepository = cursRepository;
        this.convertationRepository = convertationRepository;
    }

    @GetMapping("/sign_in")
    @ResponseBody
    public DaylyCurs login(String login, String password) {
//        System.out.println("/login: \n" + login + "\n" + password);
        try {
            if (login.equals("user@mail.ru") && password.equals("Password1")) {
                return getCurs();
            } else {
                return null;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw (e);
        }
    }

    @GetMapping("/get_curs")
    @ResponseBody
    public DaylyCurs getCurs() {
        try {
            LocalDate date = LocalDate.now();
            DateTimeFormatter fmt = DateTimeFormat.forPattern("dd.MM.yyyy");
            String ld = date.toString(fmt);
            DaylyCurs dc = cursRepository.findByDate(ld);
//                System.out.println("LocalDate: " + ld + "\nDaylyCurs: " + dc);
            if (dc == null) {
                dc = getValCurs();
                cursRepository.save(dc);
            }
            return dc;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw (e);
        }

    }

    public static DaylyCurs getValCurs() {
        DaylyCurs daylyCurs = new DaylyCurs();
        try {
            String charset = "UTF-8";
            LocalDate date = LocalDate.now();
            DateTimeFormatter fmt = DateTimeFormat.forPattern("dd/MM/yyyy");
            String ld = date.toString(fmt);
            URL url = new URL("http://www.cbr.ru/scripts/XML_daily.asp?date_req=" + ld);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Accept-Charset", charset);
            con.setFollowRedirects(false);
            int responseCode = con.getResponseCode();
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream(), "windows-1251"));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            XmlMapper xmlMapper = new XmlMapper();
            daylyCurs = xmlMapper.readValue(response.toString(), DaylyCurs.class);
//            System.out.println(daylyCurs.toString());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return daylyCurs;
    }

    @PostMapping("/save_convertation")
    @ResponseBody
    public Convertation saveConvertation(@RequestBody Convertation conv) {
        try {
            conv = convertationRepository.save(conv);
            return conv;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw (e);
        }

    }

    @PostMapping("/find_convertations")
    @ResponseBody
    public ArrayList<Convertation> findConvertations(@RequestBody Convertation conv) {
        try {
            ArrayList<Convertation> convertationArrayList = convertationRepository
                    .findAllByDateAndSourceCurrencyAndTargetCurrency(conv.getDate(),
                            conv.getSourceCurrency(), conv.getTargetCurrency());
            return convertationArrayList;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw (e);
        }

    }

}
