package com.kevinbigler.mariomakertracker.scraper;

import com.google.common.collect.ImmutableMap;
import com.kevinbigler.mariomakertracker.exception.MissingScrapeValueException;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.jsoup.helper.StringUtil;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.*;

/**
 * Created by Kevin on 3/14/2017.
 */
public class ScrapeHelper {
    public String firstElementText(Elements el) {
        if (el != null && ! el.isEmpty()) {
            return el.first().text();
        }

        return "";
    }

    public String firstElementAttribute(Elements el, String attr) {
        if (el != null && ! el.isEmpty()) {
            return el.first().attr(attr);
        }

        return "";
    }

    public String getPlayerNintendoIdFromProfileLink(Elements linkElements) {
        if (linkElements != null && ! linkElements.isEmpty()) {
            Element linkElement = linkElements.get(0);
            return getPlayerNintendoIdFromProfileUrl( linkElement.attr("abs:href") );
        }

        return "";
    }

    public String getPlayerNintendoIdFromProfileUrl(String url) {
        if (url != null && ! url.isEmpty()) {
            // example: href="/profile/thek3vinator?type=posted"
            if (url.contains("?")) {
                return StringUtils.substringBetween(url, "/profile/", "?");
            } else {
                return StringUtils.substringAfter(url, "/profile/");
            }
        }

        return "";
    }

    public String getTypographyNumber(Elements els) {
        if (els == null || els.isEmpty()) {
            return "0";
        }

        Element element = els.first();
        /*
          Example (6.45%)

          <div class="clear-rate">
            <div class="clear-flag"></div>
            <div class="typography typography-6"><svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 14.6 22"><path fill="#231815" d="M14.6 12.8v5.5L11 22H3.7L0 18.3V3.7L3.7 0h9.2v3.7H5.5L3.7 5.5v3.7H11l3.6 3.6zM11 16.5v-1.9l-1.8-1.8H3.7v3.7l1.8 1.8h3.7l1.8-1.8z"></path></svg></div>
            <div class="typography typography-second"><svg xmlns="http://www.w3.org/2000/svg" viewBox="293.3 0 213.3 1280"><path fill="#A58C26" d="M293.3 1066.7h213.3V1280H293.3v-213.3z"></path></svg></div>
            <div class="typography typography-4"><svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 14.5 22"><path fill="#231815" d="M14.5 18.3h-1.7V22H9.2v-3.7H0V9.2L9.2 0h3.7v14.7h1.7v3.6zm-5.3-3.6V5.5L3.7 11v3.7h5.5z"></path></svg></div>
            <div class="typography typography-5"><svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 14.5 22"><path fill="#231815" d="M3.7 3.7v5.5h7.5l3.4 3.7v5.5L11.1 22H0v-3.7h9.3l1.8-1.8v-1.8l-1.8-1.8H1.8L0 11V0h14.5v3.7H3.7z"></path></svg></div>
            <div class="typography typography-percent"><svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 41 45"><path fill="#A58C26" d="M41.2.1v7.5L3.7 45H0v-7.5L37.4.1h3.8zM3.7 18.8L0 15.1V3.8L3.7.1h11.2l3.8 3.7v11.3l-3.8 3.7H3.7zm2.2-6h6.8V6H5.9v6.8zm31.5 13.5l3.8 3.8v11.2L37.4 45H26.2l-3.8-3.7V30.1l3.8-3.8h11.2zm-2.2 5.9h-6.8V39h6.8v-6.8z"></path></svg></div>
          </div>
        */

        Map<String, String> typographyTokenMap = new ImmutableMap.Builder()
                .put("second", ".")
                .put("slash", "/")
                .put("minute", ":")
                .build();

        List<String> resultTokens = new ArrayList<>();
        Elements typographyElements = els.select(".typography");

        typographyElements.forEach((el) -> {
            List<String> classes = getClasses(el);
            classes.forEach((cls) -> {
                if (StringUtils.contains(cls, "-")) {
                    String token = StringUtils.substringAfter(cls, "-");
                    if (NumberUtils.isDigits(token)) {
                        resultTokens.add(token);
                    } else if (typographyTokenMap.containsKey(token)) {
                        resultTokens.add(typographyTokenMap.get(token));
                    }
                }
            });
        });

        String resultString = StringUtils.join(resultTokens, "");

        return resultString;
    }

    public Double getTypographyNumberAsDouble(Elements els) {
        return Double.valueOf(getTypographyNumber(els));
    }

    public Integer getTypographyNumberAsInteger(Elements els) {
        return Integer.valueOf(getTypographyNumber(els));
    }

    private List<String> getClasses(Element el) {
        return Arrays.asList(StringUtils.split(el.attr("class"), " "));
    }

    public List<String> getAllPlayerNintendoIdsFromProfileLinks(Elements linkElements) {
        List<String> playerNintendoIds = new ArrayList<>();
        linkElements.forEach((el) -> {
            playerNintendoIds.add(getPlayerNintendoIdFromProfileUrl(el.attr("abs:href")));
        });
        return playerNintendoIds;
    }

    public String getGameskin(Element element) throws MissingScrapeValueException {
        // -- mode (SMB1, SMB2, SMW, NSMB)
        Map<String, String> gameskinSelectors = new HashMap<>(new ImmutableMap.Builder<String, String>()
                .put("SMB1", ".course-meta-info .common_gs_sb")
                .put("SMB3", ".course-meta-info .common_gs_sb3")
                .put("SMW", ".course-meta-info .common_gs_sw")
                .put("NSMB", ".course-meta-info .common_gs_sbu")
                .build());

        return gameskinSelectors.entrySet().stream()
                .filter(map -> ! element.select(map.getValue()).isEmpty())
                .map(map -> map.getKey())
                .findFirst()
                .orElseThrow(() -> new MissingScrapeValueException("Gameskin could not be determined."));
    }
}
