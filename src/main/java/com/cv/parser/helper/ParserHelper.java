package com.cv.parser.helper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cv.parser.RegEx;

/**
 * Helper class for {@link} FetchApplicant, {@link} FetchApplicantEducation, and
 * {@link} FetchApplicantExperience
 * 
 * @author RAYMARTHINKPAD
 *
 */
public class ParserHelper {
    Logger logger = LoggerFactory.getLogger(ParserHelper.class);

    public ParserHelper() {
	
    }
    
    public int getIndexOfThisSection(RegEx regEx, String line) {
	RegEx[] sectionRegex = {RegEx.OBJECTIVE, RegEx.EDUCATION, RegEx.EXPERIENCE};
	List<Integer> indexOfThisSection = new ArrayList<Integer>();
	for (RegEx r : sectionRegex) {
	    if (r.equals(regEx)) {
		Pattern pattern = Pattern.compile(r.toString(), Pattern.MULTILINE | Pattern.DOTALL);
		Matcher matcher = pattern.matcher(line);
		while(matcher.find()) {
		    indexOfThisSection.add(matcher.start()); 
		}
	    }
	}
	if (!indexOfThisSection.isEmpty()) {
	    return indexOfThisSection.get(0);
	}
	return -1;
    }
    
    /**
     * @param regEx get section indexes but not this
     * @param line the string to parse for
     * @return indexes that follows regEx section
     */
    public List<Integer> getIndexesOfSection(RegEx regEx, String line) {
	RegEx[] sectionRegex = {RegEx.OBJECTIVE, RegEx.EDUCATION, RegEx.EXPERIENCE};
	List<Integer> indexesOfSection = new ArrayList<Integer>();
	for (RegEx r : sectionRegex) {
	    if (!r.equals(regEx)) {
		Pattern pattern = Pattern.compile(r.toString(), Pattern.MULTILINE | Pattern.DOTALL);
		Matcher matcher = pattern.matcher(line);
		while(matcher.find()) {
		    indexesOfSection.add(matcher.start()); 
		}
	    }
	}
	Collections.sort(indexesOfSection);
	return indexesOfSection;
    }
    
}
